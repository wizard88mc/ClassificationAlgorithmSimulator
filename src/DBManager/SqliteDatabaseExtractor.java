package DBManager;

import Models.DataTime;
import Models.DataTimeWithRotationValues;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class manages the interaction with an sqlite database 
 * with the data used for the test
 * 
 * @author Matteo Ciman
 */
public class SqliteDatabaseExtractor {
    private File db_path;
    private Connection connection = null;
    private String dbAccelerometer = "samples_accelerometer";
    //private String dbAccelerometer = "samples";
    private String dbLinear = "samples_linear";
    
    private String getRightDB(boolean linear) {
        if (!linear) {
            return dbAccelerometer;
        }
        else {
            return dbLinear;
        }
    }
    
    public SqliteDatabaseExtractor(File db_path) {
        this.db_path = db_path;
    }
    
    private void connect() throws FileNotFoundException, ClassNotFoundException, SQLException {
        if (connection == null) {
            if (db_path.exists() == false) {
                throw new FileNotFoundException("No db found at " + db_path.getAbsolutePath());
            }
            Class.forName("org.sqlite.JDBC"); // ClassNotFoundException
            connection = DriverManager.getConnection("jdbc:sqlite:" + db_path.getAbsolutePath());
        }
    }
    
    /**
     * Retrieves the set of data to use as test
     * @param linear: use the linear values or not
     * @return the set of DataTime from the DB
     */
    public List<DataTimeWithRotationValues> getListPoints(boolean linear) {
        List<DataTimeWithRotationValues> points = new ArrayList<>();
        
        try {
            connect();
            
            //String query = "SELECT * FROM " + getRightDB(linear) + " ORDER BY timestamp";
            String query = "SELECT * FROM " + getRightDB(linear) + " WHERE action=\"NON_STAIR\" AND trunk=4";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                double timestamp = rs.getDouble("timestamp"),
                        x = rs.getDouble("x"),
                        y = rs.getDouble("y"),
                        z = rs.getDouble("z"),
                        rotationX = rs.getDouble("rotationX"),
                        rotationY = rs.getDouble("rotationY"),
                        rotationZ = rs.getDouble("rotationZ");
                
                points.add(new DataTimeWithRotationValues(timestamp, x, y, z, rotationX, rotationY, rotationZ));
            }
        }
        catch(Exception exc) {
            exc.printStackTrace();
            System.out.println(exc.toString());
        }
        
        return points;
    }
}
