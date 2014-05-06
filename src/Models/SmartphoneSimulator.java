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
    
    private List<DataTime> values;
    private boolean linear = false;
    private double tresholdForStep = 0.0;
    private static double tradeoffG = 0.001;
    private static double g = tradeoffG / (double)100;
    private static List<Double> history = new ArrayList<>();
    private static int historySize = 10;
    private static final Double MIN_VALUE_WINDOW = /*Double.MIN_VALUE;//*/300000000.0; // 300 ms
    private static final Double MAX_VALUE_WINDOW = /*Double.MAX_VALUE;//*/2000000000.0; // 2 secondi
    
    public SmartphoneSimulator(List<DataTime> values) {
        this.values = values;
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

        return listResults;
    }
    
    public List<List<DataTime>> getSlidingWindowsValues() {
        return valuesSlidingWindows;
    }
    
    public void setHistoryCoefficient(Double value) {
        this.tradeoffG = value;
    }
}
