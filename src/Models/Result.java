package Models;

/**
 * This class is responsible to keep track of the result of the classification, 
 * keeping track of the beginning and end of the sliding window, the output as
 * a String and the value of the coefficient used to weight the output of the 
 * classification algorithm with the history of the system. 
 * 
 * @author Matteo Ciman
 */
public class Result {
    
    private final double startTimestamp;
    private final double endTimestamp;
    private final String classificationOutput;
    private final double classificationOutputCoefficient;
    private final int classificationOutputInt;
    private final double correctClassificationCoefficient;
    private final static String STAIRS = "STAIRS";
    private final static String NON_STAIRS = "NON_STAIR";
    private final static String STAIR_UPSTAIR = "STAIR_UPSTAIRS";
    private final static String STAIR_DOWNSTAIRS = "STAIR_DOWNSTAIRS";
    
    public Result(double startTimestamp, double endTimestamp,
            double classificationOutputCoefficient, String windowCorrectClassification) {
        
        this.startTimestamp = startTimestamp; this.endTimestamp = endTimestamp;
        this.classificationOutputCoefficient = classificationOutputCoefficient;
        
        this.classificationOutput = getClassificationStringOutput(classificationOutputCoefficient);
        this.classificationOutputInt = getClassificationIntOutput(classificationOutputCoefficient);
        
        if (windowCorrectClassification.equals(Result.STAIR_UPSTAIR) || 
                windowCorrectClassification.equals(Result.STAIR_DOWNSTAIRS)) {
            correctClassificationCoefficient = 1.0;
        }
        else {
            correctClassificationCoefficient = -1.0;
        }
    }
    
    private String getClassificationStringOutput(double coefficientResult) {
        
        if (coefficientResult > 0) {
            return Result.STAIRS;
        }
        else {
            return Result.NON_STAIRS;
        }
    }
    
    private int getClassificationIntOutput(double coefficientResult) {
        
        if (coefficientResult > 0) {
            return 1;
        }
        else {
            return -1;
        }
    }
    
    public double getStartTimestamp() {
        return startTimestamp;
    }
    
    public double getEndTimestamp() {
        return endTimestamp;
    }
    
    public String getClassificationOutput() {
        return classificationOutput;
    }
    
    public double getClassificationCoefficient() {
        return classificationOutputCoefficient;
    }
    
    public int getClassificationInt() {
        return classificationOutputInt;
    }
    
    public boolean isTruePositive() {
        if (classificationOutputCoefficient > 0 && correctClassificationCoefficient > 0) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean isTrueNegative() {
        if (classificationOutputCoefficient < 0 && correctClassificationCoefficient < 0) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean isFalsePositive() {
        if (classificationOutputCoefficient > 0 && correctClassificationCoefficient < 0) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean isFalseNegative() {
        if (classificationOutputCoefficient < 0 && correctClassificationCoefficient > 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
