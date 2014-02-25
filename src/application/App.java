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
            SlidingWindow.SetWindowDuration(500000000.0);
            List<DataTimeWithRotationValues> listValues = dbExtractor.getListPoints(false);
            
            
            smartphone = new SmartphoneSimulator(listValues, bufferDuration, false, true);
            
            List<Result> results = smartphone.actAsSmartphone();
            
            List<List<DataTime>> windows = smartphone.getSlidingWindowsValues();
            
            new AllDataGraph(results, windows);
        }
        
    }
    
}
