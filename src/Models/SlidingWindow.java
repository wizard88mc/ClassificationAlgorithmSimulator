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
    }
    
    public static void SetFrequency(int frequency) {
        SlidingWindow.FREQUENCY = frequency;
    }
    
    //TODO: manca gestione frequenza
    private void calculateMeans() {
        
        double meanX = 0.0, meanY = 0.0, meanZ = 0.0, meanV = 0.0, meanXAndY = 0.0;
        
        for (int i = 0; i < values.size(); i++) {
            meanX += values.get(i).getX();
            meanY += values.get(i).getY();
            meanZ += values.get(i).getZ();
            meanV += Math.sqrt(Math.pow(values.get(i).getX(), 2) + Math.pow(values.get(i).getY(), 2) +
                    Math.pow(values.get(i).getZ(), 2));
            meanXAndY += (values.get(i).getX() + values.get(i).getY());
        }
        
        meanX /= values.size(); meanY /= values.size(); meanZ /= values.size(); 
        meanV /= values.size(); meanXAndY /= values.size();
        
        means.add(meanX); means.add(meanY); means.add(meanZ); means.add(meanV);
        means.add(meanXAndY);   
    }
    
}
