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
    private double historyCoefficientValue = 1.0;
    private boolean bufferFull = false;
    private boolean linear = false;
    private boolean shutDownClassificationAfterStair = false;
    private double lastTimestampOfStairWindow = 0.0;
    private double tresholdForStep = 0.0;
    private List<RotatedDataTime> valuesForSlidingWindow = new ArrayList<RotatedDataTime>();
    private List<List<DataTime>> valuesSlidingWindows = new ArrayList<>();
    private Classifier classifier = new Classifier();
    private int numberOfStairs = 0;
    
    
    public SmartphoneSimulator(List<DataTimeWithRotationValues> values, double bufferDuration, 
            boolean linear, boolean shutDownClassificationAfterStairs) {
        this.values = values; this.bufferDuration = bufferDuration; 
        this.linear = linear; this.shutDownClassificationAfterStair = shutDownClassificationAfterStairs;
    }
    
    public List<Result> actAsSmartphone() {
        
        List<Result> listResults = new ArrayList<Result>();
        
        for (int i = 0; i < values.size(); i++) {
            
            if (values.get(i).getTimestamp() > lastTimestampOfStairWindow && 
                    ((buffer.size() == 0) || (values.get(i).timestamp - buffer.get(buffer.size() - 1).timestamp) > SlidingWindow.getMinDelta())) {
            
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
                    if (valuesForSlidingWindow.size() > 0 && finalValue.timestamp - valuesForSlidingWindow.get(0).timestamp
                            > SlidingWindow.GetWindowDuration()) {

                        SlidingWindow window = new SlidingWindow(valuesForSlidingWindow);
                        
                        List<DataTime> values = new ArrayList<>();
                        
                        for (int j = 0; j < window.getValues().size(); j++) {
                            RotatedDataTime value = window.getValues().get(j);
                            values.add(new DataTime(value.getTimestamp(), value.getX(), value.getY(), value.getZ()));
                        }
                        
                        valuesSlidingWindows.add(values);

                        /**
                         * Get all features from the sliding window
                         */
                        List<Double> allFeatures = window.getAllFeatures();
                        
                        double classificationOutput = 0;
                        try {
                            classificationOutput = Classifier.classify(allFeatures.toArray()) * historyCoefficientValue;
                            listResults.add(new Result(valuesForSlidingWindow.get(0).timestamp, 
                                valuesForSlidingWindow.get(valuesForSlidingWindow.size() - 1).timestamp,
                                     classificationOutput));
                        
                            if (classificationOutput > tresholdForStep) {
                                numberOfStairs++;
                            }
                        
                        }
                        catch(Exception exc) {
                            System.out.println(exc.toString());
                            exc.printStackTrace();
                        }
                        if (classificationOutput > tresholdForStep) {
                            numberOfStairs++;
                        }
                        
                        if (classificationOutput > tresholdForStep && shutDownClassificationAfterStair) {
                            
                            lastTimestampOfStairWindow = valuesForSlidingWindow.get(valuesForSlidingWindow.size() - 1).getTimestamp();
                            valuesForSlidingWindow.clear();
                        }
                        else {

                            double minDelta = SlidingWindow.GetMinDistanceNextSlidingWindow();
                            boolean endResearch = false; int lastIndex = -1;
                            for (int j = 0; j < valuesForSlidingWindow.size() && !endResearch; j++) {
                                if (valuesForSlidingWindow.get(j).getTimestamp() - valuesForSlidingWindow.get(0).getTimestamp() > minDelta) {
                                    lastIndex = j;
                                    endResearch = true;
                                }
                            }
                            valuesForSlidingWindow = valuesForSlidingWindow.subList(lastIndex, valuesForSlidingWindow.size());
                        }
                    }
                    else {
                        valuesForSlidingWindow.add(finalValue);
                    }

                    buffer.remove(0);
                    buffer.add(values.get(i));
                }
            }
        }
        System.out.println("Number of steps: " + numberOfStairs);
        return listResults;
    }
    
    public List<List<DataTime>> getSlidingWindowsValues() {
        return valuesSlidingWindows;
    }
}
