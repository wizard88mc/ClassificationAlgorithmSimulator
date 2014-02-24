package application;

import DBManager.SqliteDatabaseExtractor;
import Models.DataTime;
import java.io.File;
import java.util.List;

/**
 *
 * @author Matteo
 */
public class App {
    
    private static String[] testDBS = {}; 
    private static SqliteDatabaseExtractor dbExtractor;
    
    public static void main(String args[]) {
        
        for (String db: testDBS) {
            
            dbExtractor = new SqliteDatabaseExtractor(new File("test/" + db));
            /**
             * First test with non linear values
             */
            List<DataTime> listValues = dbExtractor.getListPoints(false);
            
        }
        
    }
    
}
