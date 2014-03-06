package classifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matteo
 */
public class Classifier {

    private static double delta = 0.0;
    private static List<Double> gamma = new ArrayList<>();
    private static List<List<Double>> matrixM = new ArrayList<>();
    private static double b = 0.0;
    private static double N; // number of examples of the training
    private static double R; // number of features
    private static List<Double> minF = new ArrayList<>();
    private static List<Double> maxF = new ArrayList<>();
    private static List<Double> differenceMaxMinF = new ArrayList<>();

    public static void initializeParameters() throws FileNotFoundException, IOException {

        BufferedReader reader = new BufferedReader(new FileReader(new File("models/model_VSW30OS_l0.1_cut.txt")));
        String line;
        
        /**
         * Read the first line with the delta value
         */
        line = reader.readLine();
        delta = Double.valueOf(line);
        
        /**
         * read the line with the gamma values
         */
        line = reader.readLine();
        String[] values = line.split(";");
        
        for (String value: values) {
            gamma.add(Double.valueOf(value));
        }
        
        /**
         * Read N and R values, N is the number of following lines to build the 
         * matrixM matrix
         */
        line = reader.readLine();
        values = line.split(";");
        
        N = Double.valueOf(values[0]); R = Double.valueOf(values[1]);
        
        /**
         * reads N lines to build the matrix matrixM
         */
        for (int i = 0; i < N; i++) {
            
            matrixM.add(i, new ArrayList<Double>());
            line = reader.readLine();
            values = line.split(";");
            
            for (String value: values) {
                matrixM.get(i).add(Double.valueOf(value));
            }
        }
        
        /**
         * Reads the b value
         */
        b = Double.valueOf(reader.readLine());
        
        /**
         * Read the line with the values for the minF vector
         */
        line = reader.readLine();
        values = line.split(";");
        
        for (String value: values) {
            minF.add(Double.valueOf(value));
        }
        
        /**
         * Reads the line with the values of the maxF vector
         */
        line = reader.readLine();
        values = line.split(";");
        
        for (String value: values) {
            maxF.add(Double.valueOf(value));
        }
        
        for (int i = 0; i < minF.size(); i++) {
            differenceMaxMinF.add(maxF.get(i) - minF.get(i));
        }

    }
    
    
    public static double classify(List<Double> w) {
        
        double classification = 0.0;
        
        for (int i = 0; i < N; i++) {
            
            double internalSum = 0.0;
            
            for (int j = 0; j < R; j++) {
                
                double valoreNormalizzato = ((w.get(j) - minF.get(j)) / (differenceMaxMinF.get(j))) 
                        * 2 - 1;
                
                internalSum += Math.pow(valoreNormalizzato  - matrixM.get(i).get(j), 2);
            }
            
            classification += (Math.exp(internalSum * (-delta)) * gamma.get(i));
        }
        classification -= b;
        return classification;
    }
    

    public static double classifyTree(Object[] i) throws Exception {

    double p = Double.NaN;
    p = Classifier.N786412870(i);
    return p;
  }
  static double N786412870(Object []i) {
    double p = Double.NaN;
    if (i[73] == null) {
      p = 0;
    } else if (((Double) i[73]).doubleValue() <= 4.44) {
    p = Classifier.N71fc9ad01(i);
    } else if (((Double) i[73]).doubleValue() > 4.44) {
    p = Classifier.N62457cab32(i);
    } 
    return p;
  }
  static double N71fc9ad01(Object []i) {
    double p = Double.NaN;
    if (i[53] == null) {
      p = 0;
    } else if (((Double) i[53]).doubleValue() <= 4.15) {
    p = Classifier.N7f1808262(i);
    } else if (((Double) i[53]).doubleValue() > 4.15) {
    p = Classifier.N110ca87120(i);
    } 
    return p;
  }
  static double N7f1808262(Object []i) {
    double p = Double.NaN;
    if (i[63] == null) {
      p = 1;
    } else if (((Double) i[63]).doubleValue() <= -0.75) {
    p = Classifier.N2bf37fa3(i);
    } else if (((Double) i[63]).doubleValue() > -0.75) {
    p = Classifier.N540ad64e5(i);
    } 
    return p;
  }
  static double N2bf37fa3(Object []i) {
    double p = Double.NaN;
    if (i[48] == null) {
      p = 0;
    } else if (((Double) i[48]).doubleValue() <= 0.01) {
    p = Classifier.N57f3ff7e4(i);
    } else if (((Double) i[48]).doubleValue() > 0.01) {
      p = 1;
    } 
    return p;
  }
  static double N57f3ff7e4(Object []i) {
    double p = Double.NaN;
    if (i[47] == null) {
      p = 1;
    } else if (((Double) i[47]).doubleValue() <= 1.11) {
      p = 1;
    } else if (((Double) i[47]).doubleValue() > 1.11) {
      p = 0;
    } 
    return p;
  }
  static double N540ad64e5(Object []i) {
    double p = Double.NaN;
    if (i[66] == null) {
      p = 1;
    } else if (((Double) i[66]).doubleValue() <= -0.73) {
    p = Classifier.N137bc5fd6(i);
    } else if (((Double) i[66]).doubleValue() > -0.73) {
    p = Classifier.N23036f097(i);
    } 
    return p;
  }
  static double N137bc5fd6(Object []i) {
    double p = Double.NaN;
    if (i[32] == null) {
      p = 0;
    } else if (((Double) i[32]).doubleValue() <= 0.56) {
      p = 0;
    } else if (((Double) i[32]).doubleValue() > 0.56) {
      p = 1;
    } 
    return p;
  }
  static double N23036f097(Object []i) {
    double p = Double.NaN;
    if (i[43] == null) {
      p = 0;
    } else if (((Double) i[43]).doubleValue() <= 1.27) {
    p = Classifier.N366ec9d48(i);
    } else if (((Double) i[43]).doubleValue() > 1.27) {
      p = 0;
    } 
    return p;
  }
  static double N366ec9d48(Object []i) {
    double p = Double.NaN;
    if (i[43] == null) {
      p = 0;
    } else if (((Double) i[43]).doubleValue() <= 0.44) {
      p = 0;
    } else if (((Double) i[43]).doubleValue() > 0.44) {
    p = Classifier.N238499379(i);
    } 
    return p;
  }
  static double N238499379(Object []i) {
    double p = Double.NaN;
    if (i[60] == null) {
      p = 0;
    } else if (((Double) i[60]).doubleValue() <= 1.89) {
    p = Classifier.N5666fc1810(i);
    } else if (((Double) i[60]).doubleValue() > 1.89) {
    p = Classifier.N330b7f911(i);
    } 
    return p;
  }
  static double N5666fc1810(Object []i) {
    double p = Double.NaN;
    if (i[48] == null) {
      p = 1;
    } else if (((Double) i[48]).doubleValue() <= -0.85) {
      p = 1;
    } else if (((Double) i[48]).doubleValue() > -0.85) {
      p = 0;
    } 
    return p;
  }
  static double N330b7f911(Object []i) {
    double p = Double.NaN;
    if (i[65] == null) {
      p = 0;
    } else if (((Double) i[65]).doubleValue() <= 0.5) {
      p = 0;
    } else if (((Double) i[65]).doubleValue() > 0.5) {
    p = Classifier.N737e3e1112(i);
    } 
    return p;
  }
  static double N737e3e1112(Object []i) {
    double p = Double.NaN;
    if (i[16] == null) {
      p = 1;
    } else if (((Double) i[16]).doubleValue() <= -0.36) {
      p = 1;
    } else if (((Double) i[16]).doubleValue() > -0.36) {
    p = Classifier.N651d095313(i);
    } 
    return p;
  }
  static double N651d095313(Object []i) {
    double p = Double.NaN;
    if (i[29] == null) {
      p = 1;
    } else if (((Double) i[29]).doubleValue() <= 0.79) {
    p = Classifier.N554f5c0114(i);
    } else if (((Double) i[29]).doubleValue() > 0.79) {
    p = Classifier.N742988317(i);
    } 
    return p;
  }
  static double N554f5c0114(Object []i) {
    double p = Double.NaN;
    if (i[56] == null) {
      p = 0;
    } else if (((Double) i[56]).doubleValue() <= -16.16) {
      p = 0;
    } else if (((Double) i[56]).doubleValue() > -16.16) {
    p = Classifier.N4d21716815(i);
    } 
    return p;
  }
  static double N4d21716815(Object []i) {
    double p = Double.NaN;
    if (i[38] == null) {
      p = 0;
    } else if (((Double) i[38]).doubleValue() <= 0.07) {
      p = 0;
    } else if (((Double) i[38]).doubleValue() > 0.07) {
    p = Classifier.N4e9c8a6716(i);
    } 
    return p;
  }
  static double N4e9c8a6716(Object []i) {
    double p = Double.NaN;
    if (i[72] == null) {
      p = 0;
    } else if (((Double) i[72]).doubleValue() <= 0.34) {
      p = 0;
    } else if (((Double) i[72]).doubleValue() > 0.34) {
      p = 1;
    } 
    return p;
  }
  static double N742988317(Object []i) {
    double p = Double.NaN;
    if (i[49] == null) {
      p = 0;
    } else if (((Double) i[49]).doubleValue() <= 2.19) {
    p = Classifier.N252ac42e18(i);
    } else if (((Double) i[49]).doubleValue() > 2.19) {
      p = 0;
    } 
    return p;
  }
  static double N252ac42e18(Object []i) {
    double p = Double.NaN;
    if (i[32] == null) {
      p = 1;
    } else if (((Double) i[32]).doubleValue() <= -1.24) {
      p = 1;
    } else if (((Double) i[32]).doubleValue() > -1.24) {
    p = Classifier.N1ab5bb1219(i);
    } 
    return p;
  }
  static double N1ab5bb1219(Object []i) {
    double p = Double.NaN;
    if (i[14] == null) {
      p = 1;
    } else if (((Double) i[14]).doubleValue() <= 0.46) {
      p = 1;
    } else if (((Double) i[14]).doubleValue() > 0.46) {
      p = 0;
    } 
    return p;
  }
  static double N110ca87120(Object []i) {
    double p = Double.NaN;
    if (i[43] == null) {
      p = 1;
    } else if (((Double) i[43]).doubleValue() <= 0.73) {
    p = Classifier.N56069b7521(i);
    } else if (((Double) i[43]).doubleValue() > 0.73) {
    p = Classifier.Nb231c4428(i);
    } 
    return p;
  }
  static double N56069b7521(Object []i) {
    double p = Double.NaN;
    if (i[25] == null) {
      p = 1;
    } else if (((Double) i[25]).doubleValue() <= 0.41) {
    p = Classifier.N4bc84a7222(i);
    } else if (((Double) i[25]).doubleValue() > 0.41) {
    p = Classifier.N591f22c227(i);
    } 
    return p;
  }
  static double N4bc84a7222(Object []i) {
    double p = Double.NaN;
    if (i[35] == null) {
      p = 1;
    } else if (((Double) i[35]).doubleValue() <= 1.55) {
      p = 1;
    } else if (((Double) i[35]).doubleValue() > 1.55) {
    p = Classifier.N4a8fa93c23(i);
    } 
    return p;
  }
  static double N4a8fa93c23(Object []i) {
    double p = Double.NaN;
    if (i[33] == null) {
      p = 0;
    } else if (((Double) i[33]).doubleValue() <= 1.6) {
    p = Classifier.N1dafc86224(i);
    } else if (((Double) i[33]).doubleValue() > 1.6) {
    p = Classifier.N7f8ca92725(i);
    } 
    return p;
  }
  static double N1dafc86224(Object []i) {
    double p = Double.NaN;
    if (i[24] == null) {
      p = 1;
    } else if (((Double) i[24]).doubleValue() <= -1.58) {
      p = 1;
    } else if (((Double) i[24]).doubleValue() > -1.58) {
      p = 0;
    } 
    return p;
  }
  static double N7f8ca92725(Object []i) {
    double p = Double.NaN;
    if (i[65] == null) {
      p = 1;
    } else if (((Double) i[65]).doubleValue() <= 0.64) {
    p = Classifier.N6bb580dc26(i);
    } else if (((Double) i[65]).doubleValue() > 0.64) {
      p = 1;
    } 
    return p;
  }
  static double N6bb580dc26(Object []i) {
    double p = Double.NaN;
    if (i[65] == null) {
      p = 1;
    } else if (((Double) i[65]).doubleValue() <= 0.49) {
      p = 1;
    } else if (((Double) i[65]).doubleValue() > 0.49) {
      p = 0;
    } 
    return p;
  }
  static double N591f22c227(Object []i) {
    double p = Double.NaN;
    if (i[61] == null) {
      p = 1;
    } else if (((Double) i[61]).doubleValue() <= 3.6) {
      p = 1;
    } else if (((Double) i[61]).doubleValue() > 3.6) {
      p = 0;
    } 
    return p;
  }
  static double Nb231c4428(Object []i) {
    double p = Double.NaN;
    if (i[66] == null) {
      p = 1;
    } else if (((Double) i[66]).doubleValue() <= -0.74) {
      p = 1;
    } else if (((Double) i[66]).doubleValue() > -0.74) {
    p = Classifier.N2e14ba1229(i);
    } 
    return p;
  }
  static double N2e14ba1229(Object []i) {
    double p = Double.NaN;
    if (i[72] == null) {
      p = 0;
    } else if (((Double) i[72]).doubleValue() <= 0.4) {
      p = 0;
    } else if (((Double) i[72]).doubleValue() > 0.4) {
    p = Classifier.N52c40b6030(i);
    } 
    return p;
  }
  static double N52c40b6030(Object []i) {
    double p = Double.NaN;
    if (i[65] == null) {
      p = 0;
    } else if (((Double) i[65]).doubleValue() <= 0.2) {
      p = 0;
    } else if (((Double) i[65]).doubleValue() > 0.2) {
    p = Classifier.N44c6f61331(i);
    } 
    return p;
  }
  static double N44c6f61331(Object []i) {
    double p = Double.NaN;
    if (i[19] == null) {
      p = 0;
    } else if (((Double) i[19]).doubleValue() <= 0.84) {
      p = 0;
    } else if (((Double) i[19]).doubleValue() > 0.84) {
      p = 1;
    } 
    return p;
  }
  static double N62457cab32(Object []i) {
    double p = Double.NaN;
    if (i[63] == null) {
      p = 0;
    } else if (((Double) i[63]).doubleValue() <= 0.56) {
      p = 0;
    } else if (((Double) i[63]).doubleValue() > 0.56) {
    p = Classifier.N3ffbf0f433(i);
    } 
    return p;
  }
  static double N3ffbf0f433(Object []i) {
    double p = Double.NaN;
    if (i[3] == null) {
      p = 1;
    } else if (((Double) i[3]).doubleValue() <= 4.16) {
    p = Classifier.N358843fd34(i);
    } else if (((Double) i[3]).doubleValue() > 4.16) {
    p = Classifier.N7137f42436(i);
    } 
    return p;
  }
  static double N358843fd34(Object []i) {
    double p = Double.NaN;
    if (i[12] == null) {
      p = 1;
    } else if (((Double) i[12]).doubleValue() <= 3.62) {
      p = 1;
    } else if (((Double) i[12]).doubleValue() > 3.62) {
    p = Classifier.N527b28035(i);
    } 
    return p;
  }
  static double N527b28035(Object []i) {
    double p = Double.NaN;
    if (i[48] == null) {
      p = 0;
    } else if (((Double) i[48]).doubleValue() <= 0.02) {
      p = 0;
    } else if (((Double) i[48]).doubleValue() > 0.02) {
      p = 1;
    } 
    return p;
  }
  static double N7137f42436(Object []i) {
    double p = Double.NaN;
    if (i[8] == null) {
      p = 0;
    } else if (((Double) i[8]).doubleValue() <= 0.04) {
      p = 0;
    } else if (((Double) i[8]).doubleValue() > 0.04) {
    p = Classifier.N107da18e37(i);
    } 
    return p;
  }
  static double N107da18e37(Object []i) {
    double p = Double.NaN;
    if (i[42] == null) {
      p = 1;
    } else if (((Double) i[42]).doubleValue() <= 0.21) {
      p = 1;
    } else if (((Double) i[42]).doubleValue() > 0.21) {
      p = 0;
    } 
    return p;
  }
}
