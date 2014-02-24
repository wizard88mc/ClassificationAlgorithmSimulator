package Models;

/**
 * DataTime values plus the values from the rotation vector
 * 
 * @author Matteo Ciman
 */
public class DataTimeWithRotationValues extends DataTime {
    
    protected double rotationX;
    protected double rotationY;
    protected double rotationZ;
    
    public DataTimeWithRotationValues(double timestamp, double x, double y, double z,
            double rotationX, double rotationY, double rotationZ) {
        super(timestamp, x, y, z);
        this.rotationX = rotationX; this.rotationY = rotationY; this.rotationZ = rotationZ;
    }
}
