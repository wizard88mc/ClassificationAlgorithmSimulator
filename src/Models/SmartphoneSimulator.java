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
    private double coefficientValue = 0.0;
    private boolean bufferFull = false;
    private boolean linear = false;
    private List<RotatedDataTime> valuesForSlidingWindow = new ArrayList<RotatedDataTime>();
    private Classifier classifier = new Classifier();
    
    public SmartphoneSimulator(List<DataTimeWithRotationValues> values, double bufferDuration, boolean linear) {
        this.values = values; this.bufferDuration = bufferDuration; 
    }
    
    public List<Result> actAsSmartphone() {
        
        List<Result> listResults = new ArrayList<Result>();
        
        for (int i = 0; i < values.size(); i++) {
            
            if (buffer.size() > 0 && (values.get(i).timestamp - buffer.get(0).timestamp 
                    > bufferDuration) && 
                    (values.get(i).timestamp - buffer.get(buffer.size() - 1).timestamp) > SlidingWindow.getMinDelta()) {
                
                bufferFull = true;
            }
            else if (values.get(i).timestamp - buffer.get(buffer.size() - 1).timestamp > SlidingWindow.getMinDelta()) {
                buffer.add(values.get(i));
            }
            
            if (bufferFull && (values.get(i).timestamp - buffer.get(buffer.size() - 1).timestamp) > SlidingWindow.getMinDelta()) {
                
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
                if (finalValue.timestamp - valuesForSlidingWindow.get(0).timestamp
                        > bufferDuration) {
                    
                    SlidingWindow window = new SlidingWindow(valuesForSlidingWindow);
                    
                    /**
                     * Get all features from the sliding window
                     */
                    List<Double> allFeatures = window.getAllFeatures();
                    
                    double result = Classifier.classify(allFeatures.toArray());
                    listResults.add(new Result(valuesForSlidingWindow.get(0).timestamp, 
                        valuesForSlidingWindow.get(valuesForSlidingWindow.size() - 1).timestamp,
                             result));
                }
                else {
                    valuesForSlidingWindow.add(finalValue);
                }
                
                buffer.remove(0);
                buffer.add(values.get(i));
            }
        }
        
        return listResults;
    }
    
}
