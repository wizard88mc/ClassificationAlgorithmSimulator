package Models;

/**
 * This class is responsible to keep truck of the result of the classification, 
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
    private final static String STAIRS = "STAIRS";
    private final static String NON_STAIRS = "NON_STAIRS";
    
    public Result(double startTimestamp, double endTimestamp,
            double classificationOutputCoefficient) {
        
        this.startTimestamp = startTimestamp; this.endTimestamp = endTimestamp;
        this.classificationOutputCoefficient = classificationOutputCoefficient;
        
        this.classificationOutput = getClassificationStringOutput(classificationOutputCoefficient);
        this.classificationOutputInt = getClassificationIntOutput(classificationOutputCoefficient);
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
}
