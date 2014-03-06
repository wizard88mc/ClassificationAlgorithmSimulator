package application;

import DBManager.SqliteDatabaseExtractor;
import Models.DataTime;
import Models.DataTimeWithRotationValues;
import Models.Result;
import Models.SlidingWindow;
import Models.SmartphoneSimulator;
import classifier.Classifier;
import graphs.AllDataGraph;
import java.io.File;
import java.util.List;

/**
 *
 * @author Matteo
 */
public class App {
    
    private static String[] testDBS = {"accelbench_20140127113057.db"}; 
    private static SqliteDatabaseExtractor dbExtractor;
    private static SmartphoneSimulator smartphone;
    
    public static void main(String args[]) {
        
        try {
        Classifier.initializeParameters();
        
        for (String db: testDBS) {
            
            Double bufferDuration = 500000000.0;
            
            dbExtractor = new SqliteDatabaseExtractor(new File("test/" + db));
            /**
             * First test with non linear values
             */
            SlidingWindow.SetFrequency(30);
            List<DataTimeWithRotationValues> listValues = dbExtractor.getListPoints(false);
            
            Double[] historyCoefficientValues = new Double[]{0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
            
            //for (Double value: historyCoefficientValues) {
                
                smartphone = new SmartphoneSimulator(listValues, bufferDuration, false, true);
                smartphone.setHistoryCoefficient(0.5);
            
                List<Result> results = smartphone.actAsSmartphone();
            
                List<List<DataTime>> windows = smartphone.getSlidingWindowsValues();
                //new AllDataGraph(results, windows);
                
                System.out.println("Test coefficiente storia: " + 0.0);
                System.out.println("Numero di finestre analizzate: " + results.size());
                System.out.println("Numero gradini identificati: " + getTotalNumberOfStairs(results));
                System.out.println("Valore medio per gradini: " + calculateMeanValueForStair(results));
                System.out.println("Valore medio per non gradini: " + calculateMeanValueForNonStair(results));
                System.out.println("*************************************************************");
            //}
        }
        }
        catch(Exception exc) {
            System.out.println(exc.toString());
            exc.printStackTrace();
        }
    }
    
    private static int getTotalNumberOfStairs(List<Result> results) {
        int totalNumber = 0;
        
        for (Result result: results) {
            if (result.getClassificationInt() > 0) {
                totalNumber++;
            }
        }
        
        return totalNumber;
    }
    
    private static double calculateMeanValueForStair(List<Result> results) {
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
    
    private static double calculateMeanValueForNonStair(List<Result> results) {
        double mean = 0;
        int i = 0;
        
        for (Result result: results) {
            
            if (result.getClassificationInt() <= 0) {
                if (!Double.isInfinite(result.getClassificationCoefficient()) || 
                        Double.isNaN(result.getClassificationCoefficient()))
                mean += result.getClassificationCoefficient();
                i++;
            }
        }
        return mean / (double)i;
    }
    
}
