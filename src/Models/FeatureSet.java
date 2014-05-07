/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matteo
 */

public class FeatureSet {
    
    private List<DataTime> allValues;
    private List<Double> means = new ArrayList<>();
    private List<Double> stds = new ArrayList<>();
    private List<Double> variances = new ArrayList<>();
    private List<Double> diffMinMax = new ArrayList<>();
    private List<Double> ratios = new ArrayList<>();
    private List<Double> intelligentRatios = new ArrayList<>();
    private List<Double> correlations = new ArrayList<>();
    private double magnitudeMean = 0.0;
    private double signalMagnitudeArea = 0.0;
    
    private static int FREQUENCY = 1;
    
    public FeatureSet(List<DataTime> values) {
        
        this.allValues = values;
        calculateFeatures();
    }
    
    /**
     * Retrieves only the right values for a given frequency
     * @return List<DataTime> values
     */
    private List<DataTime> getOnlyRightValues() {
        List<DataTime> rightValues = new ArrayList<>();
        rightValues.add(allValues.get(0));
        
        for (int i = 1; i < allValues.size(); i++) {
            if (allValues.get(i).getTimestamp() - 
                    rightValues.get(rightValues.size() - 1).getTimestamp() > getMinDelta()) {
                rightValues.add(allValues.get(i));
            }
        }
        
        return rightValues;
    }
    
    private void calculateFeatures() {
        
        List<DataTime> correctValues = getOnlyRightValues();
        
        calculateMeans(correctValues); calculateVariances(correctValues); calculateStds(correctValues);
        calculateDiffMinMax(correctValues); calculateRatios(); calculateCorrelations(correctValues);
        calculateMagnitudeMean(); calculateSignalMagnitudeArea(correctValues);
    }
    
    public static void SetFrequency(int frequency) {
        FeatureSet.FREQUENCY = frequency;
    }
    
    public static double getMinDelta() {
        if (FeatureSet.FREQUENCY != 0) {
            return (double)1000000000 / FeatureSet.FREQUENCY;
        }
        else return 0;
    }
    
    private double getV(List<DataTime> values, int index) {
        return Math.sqrt(Math.pow(values.get(index).getX(), 2) + Math.pow(values.get(index).getY(), 2) +
                        Math.pow(values.get(index).getZ(), 2));
    } 
    
    //TO DO: manca gestione frequenza
    private void calculateMeans(List<DataTime> values) {
        
        double meanX = 0.0, meanY = 0.0, meanZ = 0.0, meanV = 0.0, meanXAndY = 0.0;
        
        for (int i = 0; i < values.size(); i++) {
           
            meanX += values.get(i).getX();
            meanY += values.get(i).getY();
            meanZ += values.get(i).getZ();
            meanV += getV(values, i);
            meanXAndY += ((values.get(i).getX() + values.get(i).getY()) / 2.0);
           
        }
        
        meanX /= values.size(); meanY /= values.size(); meanZ /= values.size(); 
        meanV /= values.size(); meanXAndY /= values.size();
        
        means.add(meanX); means.add(meanY); means.add(meanZ); means.add(meanV);
        means.add(meanXAndY);   
    }
    
    /**
     * Calculates the variances of all the axis
     */
    private void calculateVariances(List<DataTime> values) {
        
        double varianceX = 0.0, varianceY = 0.0, varianceZ = 0.0, varianceV = 0.0, 
                varianceXAndY = 0.0;
        
        for (int i = 0; i < values.size(); i++) {
            
            varianceX += Math.pow(values.get(i).getX() - means.get(0), 2);
            varianceY += Math.pow(values.get(i).getY() - means.get(1), 2);
            varianceZ += Math.pow(values.get(i).getZ() - means.get(2), 2);
            varianceV += Math.pow(getV(values, i) - means.get(3), 2);
            varianceXAndY += Math.pow(((values.get(i).getX() + values.get(i).getY()) / 2.0) - means.get(4), 2);
        }
        
        varianceX /= values.size(); varianceY /= values.size(); varianceZ /= values.size();
        varianceV /= values.size(); varianceXAndY /= values.size();
        
        variances.add(varianceX); variances.add(varianceY); variances.add(varianceZ);
        variances.add(varianceV); variances.add(varianceXAndY);
    }
    
    private void calculateStds(List<DataTime> values) {
        
        for (int i = 0; i < variances.size(); i++) {
            stds.add(Math.sqrt(variances.get(i)));
        }
    }
    
    private void updateMinMax(double value, double[] mins, double[] maxes, int index) {
        if (value < mins[index]) {
            mins[index] = value;
        }
        if (value > maxes[index]) {
            maxes[index] = value;
        }
    }
    
    private void calculateDiffMinMax(List<DataTime> values) {
        
        double[] mins = {Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE}, 
                maxes = {Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE};
        
        for (int i = 0; i < values.size(); i++) {
            updateMinMax(values.get(i).x, mins, maxes, 0);
            updateMinMax(values.get(i).y, mins, maxes, 1);
            updateMinMax(values.get(i).z, mins, maxes, 2);
            updateMinMax(getV(values, i), mins, maxes, 3);
            updateMinMax((values.get(i).x + values.get(i).y) / 2.0, mins, maxes, 4);
        }
        
        diffMinMax.add(maxes[0] - mins[0]); diffMinMax.add(maxes[1] - mins[1]);
        diffMinMax.add(maxes[2] - mins[2]); diffMinMax.add(maxes[3] - mins[3]);
        diffMinMax.add(maxes[4] - mins[4]);
        
        intelligentRatios.add(maxes[2] / maxes[4]);
        intelligentRatios.add(Math.abs(mins[2] / mins[4]));
    }
    
    private void calculateRatios() {
        
        for (int i = 0; i < means.size() - 1; i++) {
            for (int j = i+1; j < means.size(); j++) {
                
                ratios.add(means.get(i) / means.get(j));
                ratios.add(stds.get(i) / stds.get(j));
                ratios.add(variances.get(i) / variances.get(j));
                ratios.add(diffMinMax.get(i) / diffMinMax.get(j));
            }
        }
        
        for (int i = 0; i < ratios.size(); i++) {
            if (Double.isNaN(ratios.get(i)) || Double.isInfinite(ratios.get(i))) {
                ratios.set(i, 0.0);
            }
        }
    }
    
    /**
     * Calculates the correlation between all the set of elements of the window, 
     * that are X, Y, Z, |V| and X+Y / 2
     * @param window: the sliding window with the set of values
     * @param frequency: The frequency at which calculate the correlations and use
     * data
     */
    private void calculateCorrelations(List<DataTime> values) {
        
        List<ArrayList<Double>> setOfValues = new ArrayList<ArrayList<Double>>();
        
        setOfValues.add(new ArrayList<Double>());
        setOfValues.add(new ArrayList<Double>());
        setOfValues.add(new ArrayList<Double>());
        setOfValues.add(new ArrayList<Double>());
        setOfValues.add(new ArrayList<Double>());
        
        for (int i = 0; i < values.size(); i++) {
            setOfValues.get(0).add(values.get(i).getX());
            setOfValues.get(1).add(values.get(i).getY());
            setOfValues.get(2).add(values.get(i).getZ());
            setOfValues.get(3).add(getV(values, i));
            setOfValues.get(4).add((values.get(i).getX() + values.get(i).getY()) / 2.0);
        }
        
        for (int i = 0; i < setOfValues.size() - 1; i++) {
            for (int j = i+1; j < setOfValues.size(); j++) {
                
                Double covariance = calculateCovariance(setOfValues.get(i), 
                        setOfValues.get(j));
                
                Double correlation = covariance / 
                        (stds.get(i) * stds.get(j));
                
                if (Double.isNaN(correlation) || Double.isInfinite(correlation)) {
                    correlation = 0.0;
                }
                
                correlations.add(correlation);
            }
        }
    }
    
    /**
     * Calculates the covariance between two List of values
     * 
     * @param first: first set of values
     * @param second: second set of values
     * @param frequency: frequency at which use data and calculate covariance
     * @return The covariance between the two sets of data
     */
    private Double calculateCovariance(List<Double> first, List<Double> second) {
        
        Double covariance = 0.0, sumX = 0.0, sumY = 0.0, product = 0.0; 
      
        for (int i = 0; i < first.size(); i++) {
            
            product += (first.get(i) * second.get(i));
            sumX += first.get(i);
            sumY += second.get(i);   
        }
        
        covariance = (product / first.size()) - 
                ((sumX * sumY) / Math.pow(first.size(), 2));
        
        return covariance;
    }
    
    public void calculateMagnitudeMean() {
        magnitudeMean = Math.sqrt(Math.pow(means.get(0), 2) + Math.pow(means.get(1), 2)
            + Math.pow(means.get(2), 2));
    }
    
    public void calculateSignalMagnitudeArea(List<DataTime> values) {
        
        for (int i = 0; i < values.size(); i++) {
            
            signalMagnitudeArea += Math.abs(values.get(i).getX() + Math.abs(values.get(i).getY())
                + Math.abs(values.get(i).getZ()));
        }
        
        signalMagnitudeArea /= values.size();
    }
    
    public List<Double> getAllFeatures() {
        List<Double> features = new ArrayList<Double>();
        
        for(int i = 0; i < means.size(); i++) {
            features.add(means.get(i));
            features.add(stds.get(i));
            features.add(variances.get(i));
            features.add(diffMinMax.get(i));
        }
        
        features.addAll(ratios);
        features.addAll(intelligentRatios);
        features.addAll(correlations);
        features.add(magnitudeMean);
        features.add(signalMagnitudeArea);
        
        return features;
    }
}
