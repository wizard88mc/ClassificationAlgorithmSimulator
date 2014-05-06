package Models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matteo Ciman
 */
public class SlidingWindow {
    
    private List<DataTime> values;
    private List<DataTime> valuesPMizell;
    private List<DataTime> valuesHMizell;
    private String action = "";
    private String mode = "";
    private boolean linear;
    private FeatureSet featuresValues;
    private FeatureSet featuresPMizell;
    private FeatureSet featuresHMizell;
    
    public SlidingWindow(String action, String mode, boolean linear) {
        this.action = action; this.mode = mode; this.linear = linear;
    }
    
    public void addValues(List<DataTime> values, List<DataTime> valuesPMizell, 
            List<DataTime> valuesHMizell) {
        this.values = values; this.valuesPMizell = valuesPMizell; this.valuesHMizell = valuesHMizell;
        
        featuresValues = new FeatureSet(this.values);
        featuresPMizell = new FeatureSet(this.valuesPMizell);
        featuresHMizell = new FeatureSet(this.valuesHMizell);
    }
    
    public List<DataTime> getValues() {
        return this.values;
    }
    
    public boolean isLinear() {
        return this.linear;
    }
}
