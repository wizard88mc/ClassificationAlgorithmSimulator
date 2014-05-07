package Models;

import java.util.List;

/**
 *
 * @author Matteo
 */
public class Experiment {

    private int historyLength;
    private double tradeOffG;
    private List<Result> results;
    
    public Experiment(int historyLength, double tradeOffG, List<Result> results) {
        
        this.historyLength = historyLength; this.tradeOffG = tradeOffG;
        this.results = results;
    }
    
    @Override
    public String toString() {
        String string = "*****************************" + "\n" + 
                "Lunghezza Storia: " + historyLength + " | " + "Valore g: " + tradeOffG + "\n" + 
                "VP: " + getTruePositive() + " | FP: " + getFalsePositive() + "\n" + 
                "FN: " + getFalseNegative() + " | TN: " + getTrueNegative() + "\n" + 
                "Valore medio per gradini: " + calculateMeanValueForStair() + "\n" + 
                "Valore medio per non gradini: " + calculateMeanValueForNonStair() + "\n" + 
                "*****************************";
        
        return string;
    }
    
    /**
     * Retrieves the total number of identified stairs
     * @param results
     * @return 
     */
    private int getTruePositive() {
        int totalNumber = 0;
        
        for (Result result: results) {
            if (result.isTruePositive()) {
                totalNumber++;
            }
        }
        
        return totalNumber;
    }
    
    private int getFalsePositive() {
        int totalNumber = 0; 
        for (Result result: results) {
            if (result.isFalsePositive()) {
                totalNumber++;
            }
        }
        return totalNumber;
    }
    
    private int getTrueNegative() {
        int totalNumber = 0; 
        for (Result result: results) {
            if (result.isTrueNegative()) {
                totalNumber++;
            }
        }
        return totalNumber;
    }
    
    private int getFalseNegative() {
        int totalNumber = 0;
        for (Result result: results) {
            if (result.isFalseNegative()) {
                totalNumber++;
            }
        }
        return totalNumber;
    }
    
    private double calculateMeanValueForStair() {
        double mean = 0;
        int i = 0;
        
        for (Result result: results) {
            
            if (result.getClassificationInt() > 0) {
                mean += result.getClassificationCoefficient();
                i++;
            }
        }
        return mean / (double)i;
    }
    
    private double calculateMeanValueForNonStair() {
        double mean = 0;
        int i = 0;
        
        for (Result result: results) {
            
            if (result.getClassificationInt() <= 0) {
                if (!Double.isInfinite(result.getClassificationCoefficient()) && 
                        !Double.isNaN(result.getClassificationCoefficient()))
                mean += result.getClassificationCoefficient();
                i++;
            }
        }
        return mean / (double)i;
    }
}
