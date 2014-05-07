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
    
    private static List<SlidingWindow> listWindows;
    private static double tradeoffG = 0.001;
    private static double g = tradeoffG / (double)100;
    private static List<Double> history = new ArrayList<>();
    private static int historySize = 10;
    private static final Double MIN_VALUE_WINDOW = /*Double.MIN_VALUE;//*/300000000.0; // 300 ms
    private static final Double MAX_VALUE_WINDOW = /*Double.MAX_VALUE;//*/2000000000.0; // 2 secondi
    
    public static void setListWindows(List<SlidingWindow> listWindows) {
        SmartphoneSimulator.listWindows = listWindows;
    }
    
    public static void SetTradeOffG(double value) {
        tradeoffG = value;
        g = tradeoffG / (double) 100;
    }
    
    public static void SetHistorySize(int value) {
        historySize = value;
    }
    
    public List<Result> actAsSmartphone(boolean linear) {
        
        List<Result> listResults = new ArrayList<>();
        
        for (SlidingWindow currentWindow: listWindows) {
            
            if (currentWindow.getWindowDuration() > MIN_VALUE_WINDOW && 
                    currentWindow.getWindowDuration() < MAX_VALUE_WINDOW) {
                
                List<Double> allFeatures = currentWindow.getAllFeatures();
                
                double classificationOutput = 0.0;
                if (!linear) {
                    classificationOutput = Classifier.classifyWithoutGravity(allFeatures);
                }
                else {
                    classificationOutput = 0.0;
                }
                
                double correction = 0.0;
                for (int indexHistory = 0; indexHistory < history.size(); indexHistory++) {
                    correction += (100 / Math.pow(2, indexHistory + 1)) * (double)history.get(indexHistory) * g;
                    //correction += (tradeoffG / historySize) * history.get(indexHistory);
                }
                if (Double.isNaN(correction)) {
                    System.out.println(correction);
                }

                double finalClassificationWithCorrection = classificationOutput + correction;

                Result result = new Result(currentWindow.getBeginTimestamp(), 
                    currentWindow.getEndTimestamp(), finalClassificationWithCorrection, currentWindow.getAction());

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
            }
        }

        return listResults;
    }
    
    public void setHistoryCoefficient(Double value) {
        this.tradeoffG = value;
    }
}
