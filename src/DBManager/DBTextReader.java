/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DBManager;

import Models.DataTime;
import Models.SlidingWindow;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matteo
 */
public class DBTextReader {
    
    private static String BASE_FOLDER = "";
    private static String TEST_FILE = "";
    
    /**
     * Retrieves the test sliding windows 
     * 
     * @param linear: retrieves only linear or not linear windows
     * @return a list of sliding windows
     */
    public List<SlidingWindow> extractTrunks(boolean linear) {
     
        List<SlidingWindow> listWindows = new ArrayList<>();
            
        try {
            
            BufferedReader reader = new BufferedReader(new FileReader(BASE_FOLDER + TEST_FILE));
            
            String line = "";
            while ((line = reader.readLine()).contains("@")) {}
            
            /**
             * From now line contains a row with data
             */
            int lastWindowId = -1;
            
            List<DataTime> windowValues = null, windowPMizellValues = null, 
                    windowHMizellValues = null;
            SlidingWindow window = null;
            
            while (line != null) {
                line = line.replace("(", "").replace(")", "");

                String[] elements = line.split(",");
                /**
                * elements[0]: timestamp *
                * elements[1,2,3]: x,y,z *
                * elements[4,5,6]: xPMitzell, yPMitzell, zPMitzell
                * elements[7,8,9]: xHMitzell, yHMitzell, zHMitzell
                * elements[10]: action *
                * elements[11]: mode *
                * elements[12]: trunk *
                * elements[13]: isLinear *
                */
                
                /**
                 * Checking if we have a new trunk (a new Sliding Window)
                 */
                if (lastWindowId != Integer.valueOf(elements[12])) {
                    
                    if (window != null) {
                        window.addValues(windowValues, windowPMizellValues, windowHMizellValues);
                        listWindows.add(window);
                    }
                    
                    lastWindowId = Integer.valueOf(elements[12]);
                    window = new SlidingWindow(elements[10], elements[11], 
                            Boolean.valueOf(elements[13]));
                    
                    windowValues = new ArrayList<>();
                    
                    if (!window.isLinear()) {
                        windowPMizellValues = new ArrayList<>();
                        windowHMizellValues = new ArrayList<>();
                    }
                    else {
                        windowPMizellValues = null; windowHMizellValues = null;
                    }
                }
                
                DataTime values = new DataTime(Double.valueOf(elements[0]), Double.valueOf(elements[1]),
                        Double.valueOf(elements[2]), Double.valueOf(elements[3]));
                windowValues.add(values);
                
                /**
                 * Adding Mizell values if not linear data is used
                 */
                if (!window.isLinear()) {
                    DataTime valuesPMizell = new DataTime(Double.valueOf(elements[0]), 
                        Double.valueOf(elements[4]), Double.valueOf(elements[5]), 
                        Double.valueOf(elements[6]));
                    
                    DataTime valuesHMizell = new DataTime(Double.valueOf(elements[0]), 
                        Double.valueOf(elements[7]), Double.valueOf(elements[8]), 
                        Double.valueOf(elements[9]));
                    
                    windowPMizellValues.add(valuesPMizell);
                    windowHMizellValues.add(valuesHMizell);
                }
            
                line = reader.readLine();
            }
            
            if (window != null) {
                window.addValues(windowValues, windowPMizellValues, windowHMizellValues);
                listWindows.add(window);
            }
            
        }
        catch(FileNotFoundException exc) {
            System.out.println("File not found " + exc.toString());
        }
        catch(IOException exc) {
            System.out.println("IOException " + exc.toString());
        }
        
        /**
         * Removing windows that are not linear if linear required or 
         * removing linear windows if not linear required
         */
        for (int i = 0; i < listWindows.size(); ) {
            if (listWindows.get(i).isLinear() != linear) {
                listWindows.remove(i);
            }
            else {
                i++;
            }
        }
        
        return listWindows;
    }
}
