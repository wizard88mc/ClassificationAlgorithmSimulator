package application;

import DBManager.SqliteDatabaseExtractor;
import Models.DataTime;
import Models.FeatureSet;
import Models.Result;
import Models.SmartphoneSimulator;
import classifier.Classifier;
import graphs.AllDataGraph;
import java.io.File;
import java.util.List;

/**
 *
 * @author Matteo Ciman
 */
public class App {
    
    private static String[] testDBS = {"accelbench_prova_tasca.db"}; 
    private static SqliteDatabaseExtractor dbExtractor;
    private static SmartphoneSimulator smartphone;
    private static Double[] valuesTradeOffG = new Double[]{0.000, 0.001, 0.0015, 0.002, 0.0025, 0.003};
    private static Integer[] historyLength = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    
    public static void main(String args[]) {
        
        try {
        Classifier.initializeParameters();
        
        for (String db: testDBS) {
            
            dbExtractor = new SqliteDatabaseExtractor(new File("test/" + db));
            /**
             * First test with non linear values
             */
            FeatureSet.SetFrequency(30);
            List<DataTime> listValues = dbExtractor.getListPoints(false);
            
            //for (Integer history: historyLength) {
            //for (Double valueG: valuesTradeOffG) {
                
                SmartphoneSimulator.SetHistorySize(5);
                SmartphoneSimulator.SetTradeOffG(0.000);
                smartphone = new SmartphoneSimulator(listValues, bufferDuration, false, true);
            
                List<Result> results = smartphone.actAsSmartphone();
            
                List<List<DataTime>> windows = smartphone.getSlidingWindowsValues();
                new AllDataGraph(results, windows);
                
                //System.out.println("Valore storia: " + history);
                //System.out.println("Valore del tradeOff G: " + valueG);
                System.out.println("Numero di finestre analizzate: " + results.size());
                System.out.println("Numero gradini identificati: " + getTotalNumberOfStairs(results));
                System.out.println("Valore medio per gradini: " + calculateMeanValueForStair(results));
                System.out.println("Valore medio per non gradini: " + calculateMeanValueForNonStair(results));
                System.out.println("*************************************************************");
            //}
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
                if (!Double.isInfinite(result.getClassificationCoefficient()) && 
                        !Double.isNaN(result.getClassificationCoefficient()))
                mean += result.getClassificationCoefficient();
                i++;
            }
        }
        return mean / (double)i;
    }
    
}
