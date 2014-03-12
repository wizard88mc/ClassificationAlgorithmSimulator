/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;

import classifier.Classifier;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matteo
 */
public class SmartphoneSimulator {
    
    private List<DataTimeWithRotationValues> values;
    private double bufferDuration = 0.0;
    private List<DataTime> buffer = new ArrayList<DataTime>();
    private boolean bufferFull = false;
    private boolean linear = false;
    private double tresholdForStep = 0.0;
    private List<RotatedDataTime> valuesForSlidingWindow = new ArrayList<RotatedDataTime>();
    private List<List<DataTime>> valuesSlidingWindows = new ArrayList<>();
    private static double tradeoffG = 0.001;
    private static double g = tradeoffG / (double)100;
    private static List<Double> history = new ArrayList<>();
    private static int historySize = 10;
    private static final Double MIN_VALUE_WINDOW = /*Double.MIN_VALUE;//*/300000000.0; // 300 ms
    private static final Double MAX_VALUE_WINDOW = /*Double.MAX_VALUE;//*/2000000000.0; // 2 secondi
    
    public SmartphoneSimulator(List<DataTimeWithRotationValues> values, double bufferDuration, 
            boolean linear, boolean shutDownClassificationAfterStairs) {
        this.values = values; this.bufferDuration = bufferDuration; 
        this.linear = linear;
    }
    
    public static void SetTradeOffG(double value) {
        tradeoffG = value;
        g = tradeoffG / (double) 100;
    }
    
    public static void SetHistorySize(int value) {
        historySize = value;
    }
    
    public List<Result> actAsSmartphone() {
        
        List<Result> listResults = new ArrayList<>();
        double lastConsideredTimestamp = 0.0;
        
        for (int i = 0; i < values.size(); i++) {
            
            if ((i == 0) || (values.get(i).timestamp - lastConsideredTimestamp >= SlidingWindow.getMinDelta())) {
            
                if (buffer.size() > 0 && (values.get(i).timestamp - buffer.get(0).timestamp 
                        > bufferDuration)) {

                    bufferFull = true;
                }
                else {
                    buffer.add(values.get(i));
                }

                if (bufferFull) {

                    double meanValueX = 0.0, meanValueY = 0.0, meanValueZ = 0.0;

                    for (DataTime value: buffer) {
                        meanValueX += value.getX();
                        meanValueY += value.getY();
                        meanValueZ += value.getZ();
                    }

                    meanValueX /= buffer.size();
                    meanValueY /= buffer.size();
                    meanValueZ /= buffer.size();

                    RotatedDataTime finalValue = new RotatedDataTime(values.get(i).timestamp, values.get(i).x - meanValueX, 
                            values.get(i).y - meanValueY, values.get(i).z - meanValueZ, 
                            values.get(i).rotationX, values.get(i).rotationY, values.get(i).rotationZ);

                    /**
                     * Sliding Window already completed, make classification
                     * with this data
                     */
                    if (valuesForSlidingWindow.size() > 0 && valuesForSlidingWindow.get(valuesForSlidingWindow.size() - 1).z
                            <= 0 && finalValue.z >= 0) {

                        double classificationOutput = -0.001, finalClassificationWithCorrection = -0.001;
                        Result result = null;
                            
                        SlidingWindow window = new SlidingWindow(valuesForSlidingWindow);

                        List<DataTime> valori = new ArrayList<>();

                        for (int j = 0; j < window.getValues().size(); j++) {
                            RotatedDataTime value = window.getValues().get(j);
                            valori.add(new DataTime(value.getTimestamp(), value.getX(), value.getY(), value.getZ()));
                        }

                        valuesSlidingWindows.add(valori);

                            if (valuesForSlidingWindow.get(valuesForSlidingWindow.size() - 1).timestamp 
                                - valuesForSlidingWindow.get(0).timestamp > MIN_VALUE_WINDOW && 
                                valuesForSlidingWindow.get(valuesForSlidingWindow.size() - 1).timestamp
                                - valuesForSlidingWindow.get(0).timestamp < MAX_VALUE_WINDOW) {
                            /**
                             * Get all features from the sliding window
                             */
                            List<Double> allFeatures = window.getAllFeatures();

                            try {

                                classificationOutput = Classifier.classify(allFeatures);

                                double correction = 0.0;
                                for (int indexHistory = 0; indexHistory < history.size(); indexHistory++) {
                                    correction += (100 / Math.pow(2, indexHistory + 1)) * (double)history.get(indexHistory) * g;
                                    //correction += (tradeoffG / historySize) * history.get(indexHistory);
                                }
                                if (Double.isNaN(correction)) {
                                    System.out.println(correction);
                                }

                                finalClassificationWithCorrection = classificationOutput + correction;

                                //classificationOutput = Classifier.classifyTree(allFeatures.toArray());
                            }
                            catch(Exception exc) {
                                System.out.println(exc.toString());
                                exc.printStackTrace();
                            }
                        }
                        result = new Result(valuesForSlidingWindow.get(0).timestamp, 
                                    valuesForSlidingWindow.get(valuesForSlidingWindow.size() - 1).timestamp,
                                         finalClassificationWithCorrection);

                        listResults.add(result);

                        /**
                         * No correction from the history
                         */
                        if (classificationOutput * finalClassificationWithCorrection >= 0) {
                            if (history.size() == historySize) {
                                history.remove(historySize - 1);
                                history.add(0, (double)result.getClassificationInt());
                            }
                            else {
                                history.add(0, (double)result.getClassificationInt());
                            }
                        }
                        /**
                         * There is a difference between the classification 
                         * output and the final output with correction
                         */
                        else {
                            history.clear();
                            if (classificationOutput > 0) {
                                history.add(0, 1.0);
                            }
                            else {
                                history.add(0, -1.0);
                            }
                        }

                        System.out.println("Inizio: " + result.getStartTimestamp() + ", Fine: " + result.getEndTimestamp() + ", Classificatione: " + 
                                result.getClassificationCoefficient());

                        valuesForSlidingWindow.clear();
                        valuesForSlidingWindow.add(finalValue);
                    }
                    else {
                        valuesForSlidingWindow.add(finalValue);
                    }

                    buffer.remove(0);
                    buffer.add(values.get(i));
                }
                
                lastConsideredTimestamp = values.get(i).timestamp;
            }
        }
        return listResults;
    }
    
    public List<List<DataTime>> getSlidingWindowsValues() {
        return valuesSlidingWindows;
    }
    
    public void setHistoryCoefficient(Double value) {
        this.tradeoffG = value;
    }
}
