package Models;

/**
 *
 * @author Matteo
 */
public abstract class DataTime {
    
    protected double timestamp;
    protected double x;
    protected double y;
    protected double z;
    
    DataTime(double timestamp, double x, double y, double z) {
        this.timestamp =  timestamp;
        this.x = x; this.y = y; this.z = z;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return x;
    }
    
    public double getZ() {
        return z;
    }
    
}
