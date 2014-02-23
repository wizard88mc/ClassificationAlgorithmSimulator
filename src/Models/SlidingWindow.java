package Models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matteo Ciman
 */
public class SlidingWindow {
    
    private List<DataTime> values;
    private static int FREQUENCY = 1;
    private List<Double> means = new ArrayList<Double>();
    private List<Double> stds = new ArrayList<Double>();
    private List<Double> variances = new ArrayList<Double>();
    private List<Double> diffMinMax = new ArrayList<Double>();
    
    public SlidingWindow(List<DataTime> values) {
        this.values = values;
        
        calculateMeans();
        calculateVariances();
        calculateStds();
    }
    
    public static void SetFrequency(int frequency) {
        SlidingWindow.FREQUENCY = frequency;
    }
    
    public static double getMinDelta() {
        if (SlidingWindow.FREQUENCY != 0) {
            return (double)1000000000 / SlidingWindow.FREQUENCY;
        }
        else return 0;
    }
    
    private double getV(int index) {
        return Math.sqrt(Math.pow(values.get(index).getX(), 2) + Math.pow(values.get(index).getY(), 2) +
                        Math.pow(values.get(index).getZ(), 2));
    } 
    
    //TO DO: manca gestione frequenza
    private void calculateMeans() {
        
        double meanX = 0.0, meanY = 0.0, meanZ = 0.0, meanV = 0.0, meanXAndY = 0.0;
        double lastTimestamp = values.get(0).timestamp; int elements = 0;
        
        for (int i = 0; i < values.size(); i++) {
            
            if (i != 0 && values.get(0).timestamp - lastTimestamp >= getMinDelta()) {
                meanX += values.get(i).getX();
                meanY += values.get(i).getY();
                meanZ += values.get(i).getZ();
                meanV += getV(i);
                meanXAndY += (values.get(i).getX() + values.get(i).getY());
                
                lastTimestamp = values.get(i).timestamp;
                elements++;
            }
        }
        
        meanX /= elements; meanY /= elements; meanZ /= elements; 
        meanV /= elements; meanXAndY /= elements;
        
        means.add(meanX); means.add(meanY); means.add(meanZ); means.add(meanV);
        means.add(meanXAndY);   
    }
    
    private void calculateVariances() {
        
        double varianceX = 0.0, varianceY = 0.0, varianceZ = 0.0, varianceV = 0.0, 
                varianceXAndY = 0.0;
        int numberElements = 0; double lastTimestamp = values.get(0).timestamp;
        
        for (int i = 0; i < values.size(); i++) {
            
            if (i != 0 && values.get(i).timestamp - lastTimestamp >= getMinDelta()) {
                
                varianceX += Math.pow(values.get(i).getX() - means.get(0), 2);
                varianceY += Math.pow(values.get(i).getY() - means.get(1), 2);
                varianceZ += Math.pow(values.get(i).getZ() - means.get(2), 2);
                varianceV += Math.pow(getV(i) - means.get(3), 2);
                varianceXAndY += Math.pow(values.get(i).getX() + values.get(i).getY() - means.get(4), 2);
                lastTimestamp = values.get(i).timestamp;
                numberElements++;
            }
        }
        
        varianceX /= numberElements; varianceY /= numberElements; varianceZ /= numberElements;
        varianceV /= numberElements; varianceXAndY /= numberElements;
        
        variances.add(varianceX); variances.add(varianceY); variances.add(varianceZ);
        variances.add(varianceV); variances.add(varianceXAndY);
    }
    
    private void calculateStds() {
        
        for (int i = 0; i < variances.size(); i++) {
            stds.add(Math.sqrt(variances.get(i)));
        }
    }
    
    private void updateMinMax(int value, int[] mins, int maxes[], int index) {
        if (value < array[index]) {
            
        }
    }
    
    private void calculateDiffMinMax() {
        
        int[] mins = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}, 
                maxes = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
        
        for (int i = 0; i < values.size(); i++) {
            
        }
    }
    
}
