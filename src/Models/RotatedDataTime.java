package Models;

/**
 *
 * @author Matteo
 */
public class RotatedDataTime extends DataTime {
    
    public RotatedDataTime(double timestamp, double x, double y, double z, 
            double rotationX, double rotationY, double rotationZ) {
        super(timestamp, x, y, z);
        
        rotateAxis(rotationX, rotationY, rotationZ);
    }
    
    private void rotateAxis(double rotationX, double rotationY, double rotationZ) {
        
        double norm = Math.sqrt(Math.pow(rotationX, 2) + Math.pow(rotationY, 2) + 
                Math.pow(rotationZ, 2));
        if (norm >1) {
            norm = 1;
        }
        double alpha = 2 * Math.asin(norm);
        
        double xForRotation = rotationX / norm, yForRotation = rotationY / norm, zForRotation = rotationZ / norm;
        double xSquare = Math.pow(xForRotation, 2), ySquare = Math.pow(yForRotation, 2), zSquare = Math.pow(zForRotation, 2);
        
        double sinAlpha = Math.sin(alpha), cosAlpha = Math.cos(alpha);
        
        double xFirst = x, yFirst = y, zFirst = z;
        
        x = ((xSquare + (1 - xSquare) * cosAlpha) * xFirst +
                (((1 - cosAlpha) * xForRotation * yForRotation) - sinAlpha * zForRotation) * yFirst +
                (((1 - cosAlpha) * xForRotation * zForRotation) + sinAlpha * y) * zFirst);
        
        y = ((((1 - cosAlpha) * yForRotation * xForRotation) + sinAlpha * zForRotation) * xFirst +
                (ySquare + (1 - ySquare) * cosAlpha) * yFirst +
                (((1 - cosAlpha) * yForRotation * zForRotation) - sinAlpha * xForRotation) * zFirst);
        
        z = ((((1 - cosAlpha) * zForRotation * xForRotation) - sinAlpha * yForRotation) * xFirst +
                        ((1 - cosAlpha) * zForRotation * yForRotation + sinAlpha * xForRotation) * yFirst +
                        (zSquare + (1 - zSquare) * cosAlpha) * zFirst);
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        RotatedDataTime clone = (RotatedDataTime)super.clone();
        return clone;
    }
}
