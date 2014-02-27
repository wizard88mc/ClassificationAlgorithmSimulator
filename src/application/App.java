package application;

import DBManager.SqliteDatabaseExtractor;
import Models.DataTime;
import Models.DataTimeWithRotationValues;
import Models.Result;
import Models.RotatedDataTime;
import Models.SlidingWindow;
import Models.SmartphoneSimulator;
import graphs.AllDataGraph;
import static java.awt.SystemColor.window;
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
        
        for (String db: testDBS) {
            
            Double bufferDuration = 500000000.0;
            
            dbExtractor = new SqliteDatabaseExtractor(new File("test/" + db));
            /**
             * First test with non linear values
             */
            SlidingWindow.SetFrequency(40);
            List<DataTimeWithRotationValues> listValues = dbExtractor.getListPoints(false);
            
            Double[] historyCoefficientValues = new Double[]{0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
            
            for (Double value: historyCoefficientValues) {
                
                smartphone = new SmartphoneSimulator(listValues, bufferDuration, false, true);
                smartphone.setHistoryCoefficient(value);
            
                List<Result> results = smartphone.actAsSmartphone();
            
                List<List<DataTime>> windows = smartphone.getSlidingWindowsValues();
                new AllDataGraph(results, windows);
                
                System.out.println("Test coefficiente storia: " + value);
                System.out.println("Numero gradini identificati: " + getTotalNumberOfStairs(results));
                System.out.println("*************************************************************");
            }
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
    
}
