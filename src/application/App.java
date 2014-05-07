package application;

import DBManager.DBTextReader;
import Models.Experiment;
import Models.FeatureSet;
import Models.Result;
import Models.SmartphoneSimulator;
import classifier.Classifier;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matteo Ciman
 */
public class App {
    
    private static String[] testDBS = {"testSamplesSlidingWindows.dsw"}; 
    private static DBTextReader dbReader;
    private static SmartphoneSimulator smartphone;
    private static Double[] valuesTradeOffG = new Double[]{0.000, 0.001, 0.0015, 0.002, 0.0025, 0.003};
    private static Integer[] historyLength = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static List<Experiment> listExperiments = new ArrayList<>();
    
    public static void main(String args[]) {
        
        try {
            Classifier.initializeParameters();

            for (String db: testDBS) {

                dbReader = new DBTextReader(db);
                /**
                 * First test with non linear values
                 */
                FeatureSet.SetFrequency(30);
                SmartphoneSimulator.setListWindows(dbReader.extractTrunks(false));


                //for (Integer history: historyLength) {
                    //for (Double valueG: valuesTradeOffG) {

                        SmartphoneSimulator.SetHistorySize(5);
                        SmartphoneSimulator.SetTradeOffG(0.000);
                        smartphone = new SmartphoneSimulator();

                        List<Result> results = smartphone.actAsSmartphone(false);

                        listExperiments.add(new Experiment(5, 0.000, results));
                    //}
                //}
            }
        }
        catch(IOException exc) {
            System.out.println(exc.toString());
            exc.printStackTrace();
        }
        
        for (Experiment experiment: listExperiments) {
            System.out.println(experiment);
        }
    }
    
}
