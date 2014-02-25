package Models;

/**
 * This class is responsible to manage the data relative to a single time 
 * instant, with the x, y and z values + the timestamp
 * @author Matteo Ciman
 */
public class DataTime extends Object{
    
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
        return y;
    }
    
    public double getZ() {
        return z;
    }
    
    public double getTimestamp() {
        return timestamp;
    }
    
    public void setNewValues(double x, double y, double z) {
        this.x = x; this.y = y; this.z = z;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
