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

    public static void initializeParameters() throws FileNotFoundException, IOException {

        BufferedReader reader = new BufferedReader(new FileReader(new File("inputSVM.txt")));
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

    }
    
    
    public static double classify(List<Double> w) {
        
        double classification = 0.0;
        
        for (int i = 0; i < N; i++) {
            
            double internalSum = 0.0;
            
            for (int j = 0; j < R; j++) {
                
                internalSum += ((w.get(j) - minF.get(j)) / (maxF.get(j) - minF.get(j))) 
                        * 2 - 1 - matrixM.get(i).get(j);
                
            }
            
            classification += Math.exp(internalSum * (-delta)) * gamma.get(i);
            
            classification -= b;
        }
        
        return classification;
    }
    

    public static double classifyTree(Object[] i) throws Exception {

        double p = Double.NaN;
        p = Classifier.N6d9d54e90(i);
        return p;
    }

    static double N6d9d54e90(Object[] i) {
        double p = Double.NaN;
        if (i[71] == null) {
            p = 0;
        } else if (((Double) i[71]).doubleValue() <= 4.65) {
            p = Classifier.N7431c5371(i);
        } else if (((Double) i[71]).doubleValue() > 4.65) {
            p = Classifier.N205d4a65119(i);
        }
        return p;
    }

    static double N7431c5371(Object[] i) {
        double p = Double.NaN;
        if (i[4] == null) {
            p = 0;
        } else if (((Double) i[4]).doubleValue() <= 0.6) {
            p = Classifier.N6f8ad9792(i);
        } else if (((Double) i[4]).doubleValue() > 0.6) {
            p = Classifier.N7d400c06111(i);
        }
        return p;
    }

    static double N6f8ad9792(Object[] i) {
        double p = Double.NaN;
        if (i[4] == null) {
            p = 0;
        } else if (((Double) i[4]).doubleValue() <= -0.55) {
            p = Classifier.N4cbd0253(i);
        } else if (((Double) i[4]).doubleValue() > -0.55) {
            p = Classifier.N7d6a0cb319(i);
        }
        return p;
    }

    static double N4cbd0253(Object[] i) {
        double p = Double.NaN;
        if (i[27] == null) {
            p = 1;
        } else if (((Double) i[27]).doubleValue() <= 0.48) {
            p = Classifier.N60ce2f984(i);
        } else if (((Double) i[27]).doubleValue() > 0.48) {
            p = Classifier.N5035d1f018(i);
        }
        return p;
    }

    static double N60ce2f984(Object[] i) {
        double p = Double.NaN;
        if (i[69] == null) {
            p = 1;
        } else if (((Double) i[69]).doubleValue() <= 0.4) {
            p = Classifier.N96d5cf5(i);
        } else if (((Double) i[69]).doubleValue() > 0.4) {
            p = 0;
        }
        return p;
    }

    static double N96d5cf5(Object[] i) {
        double p = Double.NaN;
        if (i[68] == null) {
            p = 1;
        } else if (((Double) i[68]).doubleValue() <= 0.31) {
            p = Classifier.N2eaf09566(i);
        } else if (((Double) i[68]).doubleValue() > 0.31) {
            p = Classifier.N7ac3da5f17(i);
        }
        return p;
    }

    static double N2eaf09566(Object[] i) {
        double p = Double.NaN;
        if (i[48] == null) {
            p = 1;
        } else if (((Double) i[48]).doubleValue() <= 0.29) {
            p = Classifier.N658e050b7(i);
        } else if (((Double) i[48]).doubleValue() > 0.29) {
            p = 0;
        }
        return p;
    }

    static double N658e050b7(Object[] i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 1;
        } else if (((Double) i[7]).doubleValue() <= 2.24) {
            p = Classifier.N4eed49438(i);
        } else if (((Double) i[7]).doubleValue() > 2.24) {
            p = Classifier.N85ee84a10(i);
        }
        return p;
    }

    static double N4eed49438(Object[] i) {
        double p = Double.NaN;
        if (i[33] == null) {
            p = 1;
        } else if (((Double) i[33]).doubleValue() <= 2.2) {
            p = 1;
        } else if (((Double) i[33]).doubleValue() > 2.2) {
            p = Classifier.N3c64f6309(i);
        }
        return p;
    }

    static double N3c64f6309(Object[] i) {
        double p = Double.NaN;
        if (i[65] == null) {
            p = 0;
        } else if (((Double) i[65]).doubleValue() <= -0.03) {
            p = 0;
        } else if (((Double) i[65]).doubleValue() > -0.03) {
            p = 1;
        }
        return p;
    }

    static double N85ee84a10(Object[] i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 1;
        } else if (((Double) i[1]).doubleValue() <= 0.98) {
            p = Classifier.Nee4569111(i);
        } else if (((Double) i[1]).doubleValue() > 0.98) {
            p = 0;
        }
        return p;
    }

    static double Nee4569111(Object[] i) {
        double p = Double.NaN;
        if (i[32] == null) {
            p = 1;
        } else if (((Double) i[32]).doubleValue() <= -0.44) {
            p = Classifier.N30ef513a12(i);
        } else if (((Double) i[32]).doubleValue() > -0.44) {
            p = Classifier.N569e6c6514(i);
        }
        return p;
    }

    static double N30ef513a12(Object[] i) {
        double p = Double.NaN;
        if (i[57] == null) {
            p = 0;
        } else if (((Double) i[57]).doubleValue() <= 1.81) {
            p = Classifier.N2fbdcfef13(i);
        } else if (((Double) i[57]).doubleValue() > 1.81) {
            p = 1;
        }
        return p;
    }

    static double N2fbdcfef13(Object[] i) {
        double p = Double.NaN;
        if (i[4] == null) {
            p = 0;
        } else if (((Double) i[4]).doubleValue() <= -0.74) {
            p = 0;
        } else if (((Double) i[4]).doubleValue() > -0.74) {
            p = 1;
        }
        return p;
    }

    static double N569e6c6514(Object[] i) {
        double p = Double.NaN;
        if (i[63] == null) {
            p = 0;
        } else if (((Double) i[63]).doubleValue() <= 0.41) {
            p = 0;
        } else if (((Double) i[63]).doubleValue() > 0.41) {
            p = Classifier.N3ade875015(i);
        }
        return p;
    }

    static double N3ade875015(Object[] i) {
        double p = Double.NaN;
        if (i[65] == null) {
            p = 1;
        } else if (((Double) i[65]).doubleValue() <= -0.35) {
            p = 1;
        } else if (((Double) i[65]).doubleValue() > -0.35) {
            p = Classifier.N6785b36116(i);
        }
        return p;
    }

    static double N6785b36116(Object[] i) {
        double p = Double.NaN;
        if (i[33] == null) {
            p = 0;
        } else if (((Double) i[33]).doubleValue() <= 1.8) {
            p = 0;
        } else if (((Double) i[33]).doubleValue() > 1.8) {
            p = 1;
        }
        return p;
    }

    static double N7ac3da5f17(Object[] i) {
        double p = Double.NaN;
        if (i[67] == null) {
            p = 0;
        } else if (((Double) i[67]).doubleValue() <= 0.47) {
            p = 0;
        } else if (((Double) i[67]).doubleValue() > 0.47) {
            p = 1;
        }
        return p;
    }

    static double N5035d1f018(Object[] i) {
        double p = Double.NaN;
        if (i[35] == null) {
            p = 0;
        } else if (((Double) i[35]).doubleValue() <= 3.21) {
            p = 0;
        } else if (((Double) i[35]).doubleValue() > 3.21) {
            p = 1;
        }
        return p;
    }

    static double N7d6a0cb319(Object[] i) {
        double p = Double.NaN;
        if (i[29] == null) {
            p = 0;
        } else if (((Double) i[29]).doubleValue() <= 0.63) {
            p = Classifier.N3d67fc1820(i);
        } else if (((Double) i[29]).doubleValue() > 0.63) {
            p = Classifier.Ne2da38f90(i);
        }
        return p;
    }

    static double N3d67fc1820(Object[] i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 0;
        } else if (((Double) i[5]).doubleValue() <= 1.31) {
            p = Classifier.N75d7ab2621(i);
        } else if (((Double) i[5]).doubleValue() > 1.31) {
            p = 0;
        }
        return p;
    }

    static double N75d7ab2621(Object[] i) {
        double p = Double.NaN;
        if (i[48] == null) {
            p = 0;
        } else if (((Double) i[48]).doubleValue() <= -0.31) {
            p = 0;
        } else if (((Double) i[48]).doubleValue() > -0.31) {
            p = Classifier.N2225883b22(i);
        }
        return p;
    }

    static double N2225883b22(Object[] i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 1;
        } else if (((Double) i[0]).doubleValue() <= -0.5) {
            p = Classifier.N4e12eb0023(i);
        } else if (((Double) i[0]).doubleValue() > -0.5) {
            p = Classifier.N2dfde9bd29(i);
        }
        return p;
    }

    static double N4e12eb0023(Object[] i) {
        double p = Double.NaN;
        if (i[23] == null) {
            p = 0;
        } else if (((Double) i[23]).doubleValue() <= 0.47) {
            p = 0;
        } else if (((Double) i[23]).doubleValue() > 0.47) {
            p = Classifier.N3c02750b24(i);
        }
        return p;
    }

    static double N3c02750b24(Object[] i) {
        double p = Double.NaN;
        if (i[37] == null) {
            p = 1;
        } else if (((Double) i[37]).doubleValue() <= 0.24) {
            p = 1;
        } else if (((Double) i[37]).doubleValue() > 0.24) {
            p = Classifier.N455243f425(i);
        }
        return p;
    }

    static double N455243f425(Object[] i) {
        double p = Double.NaN;
        if (i[68] == null) {
            p = 0;
        } else if (((Double) i[68]).doubleValue() <= 0.64) {
            p = Classifier.N1beb6bba26(i);
        } else if (((Double) i[68]).doubleValue() > 0.64) {
            p = Classifier.N7ced8ea727(i);
        }
        return p;
    }

    static double N1beb6bba26(Object[] i) {
        double p = Double.NaN;
        if (i[16] == null) {
            p = 1;
        } else if (((Double) i[16]).doubleValue() <= -0.42) {
            p = 1;
        } else if (((Double) i[16]).doubleValue() > -0.42) {
            p = 0;
        }
        return p;
    }

    static double N7ced8ea727(Object[] i) {
        double p = Double.NaN;
        if (i[8] == null) {
            p = 1;
        } else if (((Double) i[8]).doubleValue() <= 0.33) {
            p = 1;
        } else if (((Double) i[8]).doubleValue() > 0.33) {
            p = Classifier.N5030b60428(i);
        }
        return p;
    }

    static double N5030b60428(Object[] i) {
        double p = Double.NaN;
        if (i[38] == null) {
            p = 0;
        } else if (((Double) i[38]).doubleValue() <= 0.1) {
            p = 0;
        } else if (((Double) i[38]).doubleValue() > 0.1) {
            p = 1;
        }
        return p;
    }

    static double N2dfde9bd29(Object[] i) {
        double p = Double.NaN;
        if (i[28] == null) {
            p = 0;
        } else if (((Double) i[28]).doubleValue() <= 0.2) {
            p = Classifier.N78fc8ee130(i);
        } else if (((Double) i[28]).doubleValue() > 0.2) {
            p = Classifier.N2f42d25988(i);
        }
        return p;
    }

    static double N78fc8ee130(Object[] i) {
        double p = Double.NaN;
        if (i[43] == null) {
            p = 0;
        } else if (((Double) i[43]).doubleValue() <= 0.9) {
            p = Classifier.Nd0893d531(i);
        } else if (((Double) i[43]).doubleValue() > 0.9) {
            p = 0;
        }
        return p;
    }

    static double Nd0893d531(Object[] i) {
        double p = Double.NaN;
        if (i[70] == null) {
            p = 0;
        } else if (((Double) i[70]).doubleValue() <= 0.23) {
            p = Classifier.N2e218ba232(i);
        } else if (((Double) i[70]).doubleValue() > 0.23) {
            p = Classifier.N26bef3ab36(i);
        }
        return p;
    }

    static double N2e218ba232(Object[] i) {
        double p = Double.NaN;
        if (i[31] == null) {
            p = 0;
        } else if (((Double) i[31]).doubleValue() <= 0.36) {
            p = Classifier.N1c56505733(i);
        } else if (((Double) i[31]).doubleValue() > 0.36) {
            p = 0;
        }
        return p;
    }

    static double N1c56505733(Object[] i) {
        double p = Double.NaN;
        if (i[56] == null) {
            p = 0;
        } else if (((Double) i[56]).doubleValue() <= 31.14) {
            p = Classifier.N66b48e4934(i);
        } else if (((Double) i[56]).doubleValue() > 31.14) {
            p = 1;
        }
        return p;
    }

    static double N66b48e4934(Object[] i) {
        double p = Double.NaN;
        if (i[51] == null) {
            p = 1;
        } else if (((Double) i[51]).doubleValue() <= 1.62) {
            p = 1;
        } else if (((Double) i[51]).doubleValue() > 1.62) {
            p = Classifier.N57e98f4c35(i);
        }
        return p;
    }

    static double N57e98f4c35(Object[] i) {
        double p = Double.NaN;
        if (i[55] == null) {
            p = 0;
        } else if (((Double) i[55]).doubleValue() <= 8.49) {
            p = 0;
        } else if (((Double) i[55]).doubleValue() > 8.49) {
            p = 1;
        }
        return p;
    }

    static double N26bef3ab36(Object[] i) {
        double p = Double.NaN;
        if (i[8] == null) {
            p = 0;
        } else if (((Double) i[8]).doubleValue() <= 0.33) {
            p = Classifier.N426f736c37(i);
        } else if (((Double) i[8]).doubleValue() > 0.33) {
            p = Classifier.N40542fa780(i);
        }
        return p;
    }

    static double N426f736c37(Object[] i) {
        double p = Double.NaN;
        if (i[26] == null) {
            p = 0;
        } else if (((Double) i[26]).doubleValue() <= 0.08) {
            p = Classifier.N2302d98738(i);
        } else if (((Double) i[26]).doubleValue() > 0.08) {
            p = Classifier.N1d1d8ccb76(i);
        }
        return p;
    }

    static double N2302d98738(Object[] i) {
        double p = Double.NaN;
        if (i[61] == null) {
            p = 0;
        } else if (((Double) i[61]).doubleValue() <= 0.76) {
            p = Classifier.N1017400639(i);
        } else if (((Double) i[61]).doubleValue() > 0.76) {
            p = Classifier.N75192bda73(i);
        }
        return p;
    }

    static double N1017400639(Object[] i) {
        double p = Double.NaN;
        if (i[71] == null) {
            p = 0;
        } else if (((Double) i[71]).doubleValue() <= 4.43) {
            p = Classifier.N666c522a40(i);
        } else if (((Double) i[71]).doubleValue() > 4.43) {
            p = 0;
        }
        return p;
    }

    static double N666c522a40(Object[] i) {
        double p = Double.NaN;
        if (i[48] == null) {
            p = 0;
        } else if (((Double) i[48]).doubleValue() <= -0.09) {
            p = Classifier.N518677ee41(i);
        } else if (((Double) i[48]).doubleValue() > -0.09) {
            p = Classifier.Na59134945(i);
        }
        return p;
    }

    static double N518677ee41(Object[] i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 0;
        } else if (((Double) i[0]).doubleValue() <= 0.3) {
            p = Classifier.N532bd41242(i);
        } else if (((Double) i[0]).doubleValue() > 0.3) {
            p = 1;
        }
        return p;
    }

    static double N532bd41242(Object[] i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 1;
        } else if (((Double) i[0]).doubleValue() <= -0.38) {
            p = 1;
        } else if (((Double) i[0]).doubleValue() > -0.38) {
            p = Classifier.N6270146643(i);
        }
        return p;
    }

    static double N6270146643(Object[] i) {
        double p = Double.NaN;
        if (i[36] == null) {
            p = 0;
        } else if (((Double) i[36]).doubleValue() <= -0.62) {
            p = Classifier.N2c4b670744(i);
        } else if (((Double) i[36]).doubleValue() > -0.62) {
            p = 0;
        }
        return p;
    }

    static double N2c4b670744(Object[] i) {
        double p = Double.NaN;
        if (i[8] == null) {
            p = 1;
        } else if (((Double) i[8]).doubleValue() <= -0.3) {
            p = 1;
        } else if (((Double) i[8]).doubleValue() > -0.3) {
            p = 0;
        }
        return p;
    }

    static double Na59134945(Object[] i) {
        double p = Double.NaN;
        if (i[53] == null) {
            p = 0;
        } else if (((Double) i[53]).doubleValue() <= 4.34) {
            p = 0;
        } else if (((Double) i[53]).doubleValue() > 4.34) {
            p = Classifier.N5e0122ed46(i);
        }
        return p;
    }

    static double N5e0122ed46(Object[] i) {
        double p = Double.NaN;
        if (i[48] == null) {
            p = 0;
        } else if (((Double) i[48]).doubleValue() <= 0.06) {
            p = Classifier.N1c9c25d247(i);
        } else if (((Double) i[48]).doubleValue() > 0.06) {
            p = Classifier.N79139c0f66(i);
        }
        return p;
    }

    static double N1c9c25d247(Object[] i) {
        double p = Double.NaN;
        if (i[48] == null) {
            p = 1;
        } else if (((Double) i[48]).doubleValue() <= -0.08) {
            p = 1;
        } else if (((Double) i[48]).doubleValue() > -0.08) {
            p = Classifier.N4f770caa48(i);
        }
        return p;
    }

    static double N4f770caa48(Object[] i) {
        double p = Double.NaN;
        if (i[11] == null) {
            p = 0;
        } else if (((Double) i[11]).doubleValue() <= 11.9) {
            p = Classifier.N10e095a849(i);
        } else if (((Double) i[11]).doubleValue() > 11.9) {
            p = 0;
        }
        return p;
    }

    static double N10e095a849(Object[] i) {
        double p = Double.NaN;
        if (i[49] == null) {
            p = 1;
        } else if (((Double) i[49]).doubleValue() <= 2.1) {
            p = Classifier.N881514050(i);
        } else if (((Double) i[49]).doubleValue() > 2.1) {
            p = Classifier.N5488035c55(i);
        }
        return p;
    }

    static double N881514050(Object[] i) {
        double p = Double.NaN;
        if (i[64] == null) {
            p = 0;
        } else if (((Double) i[64]).doubleValue() <= -0.69) {
            p = 0;
        } else if (((Double) i[64]).doubleValue() > -0.69) {
            p = Classifier.N61fd451c51(i);
        }
        return p;
    }

    static double N61fd451c51(Object[] i) {
        double p = Double.NaN;
        if (i[26] == null) {
            p = 1;
        } else if (((Double) i[26]).doubleValue() <= 0.05) {
            p = 1;
        } else if (((Double) i[26]).doubleValue() > 0.05) {
            p = Classifier.N3ac4638652(i);
        }
        return p;
    }

    static double N3ac4638652(Object[] i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 0;
        } else if (((Double) i[3]).doubleValue() <= 2.62) {
            p = 0;
        } else if (((Double) i[3]).doubleValue() > 2.62) {
            p = Classifier.N3362108e53(i);
        }
        return p;
    }

    static double N3362108e53(Object[] i) {
        double p = Double.NaN;
        if (i[8] == null) {
            p = 0;
        } else if (((Double) i[8]).doubleValue() <= -0.06) {
            p = Classifier.N6b2cf4fc54(i);
        } else if (((Double) i[8]).doubleValue() > -0.06) {
            p = 1;
        }
        return p;
    }

    static double N6b2cf4fc54(Object[] i) {
        double p = Double.NaN;
        if (i[16] == null) {
            p = 0;
        } else if (((Double) i[16]).doubleValue() <= 0.1) {
            p = 0;
        } else if (((Double) i[16]).doubleValue() > 0.1) {
            p = 1;
        }
        return p;
    }

    static double N5488035c55(Object[] i) {
        double p = Double.NaN;
        if (i[40] == null) {
            p = 1;
        } else if (((Double) i[40]).doubleValue() <= -0.13) {
            p = Classifier.N2d94b85f56(i);
        } else if (((Double) i[40]).doubleValue() > -0.13) {
            p = Classifier.N7ed47c5957(i);
        }
        return p;
    }

    static double N2d94b85f56(Object[] i) {
        double p = Double.NaN;
        if (i[28] == null) {
            p = 0;
        } else if (((Double) i[28]).doubleValue() <= -0.05) {
            p = 0;
        } else if (((Double) i[28]).doubleValue() > -0.05) {
            p = 1;
        }
        return p;
    }

    static double N7ed47c5957(Object[] i) {
        double p = Double.NaN;
        if (i[31] == null) {
            p = 0;
        } else if (((Double) i[31]).doubleValue() <= 0.57) {
            p = Classifier.N302ff81c58(i);
        } else if (((Double) i[31]).doubleValue() > 0.57) {
            p = 0;
        }
        return p;
    }

    static double N302ff81c58(Object[] i) {
        double p = Double.NaN;
        if (i[20] == null) {
            p = 0;
        } else if (((Double) i[20]).doubleValue() <= 1.26) {
            p = Classifier.N1d4a0efb59(i);
        } else if (((Double) i[20]).doubleValue() > 1.26) {
            p = Classifier.N49c0357963(i);
        }
        return p;
    }

    static double N1d4a0efb59(Object[] i) {
        double p = Double.NaN;
        if (i[38] == null) {
            p = 0;
        } else if (((Double) i[38]).doubleValue() <= 0.06) {
            p = Classifier.N691d8fc260(i);
        } else if (((Double) i[38]).doubleValue() > 0.06) {
            p = 0;
        }
        return p;
    }

    static double N691d8fc260(Object[] i) {
        double p = Double.NaN;
        if (i[53] == null) {
            p = 1;
        } else if (((Double) i[53]).doubleValue() <= 5.24) {
            p = 1;
        } else if (((Double) i[53]).doubleValue() > 5.24) {
            p = Classifier.N13c93f7861(i);
        }
        return p;
    }

    static double N13c93f7861(Object[] i) {
        double p = Double.NaN;
        if (i[17] == null) {
            p = 1;
        } else if (((Double) i[17]).doubleValue() <= 0.35) {
            p = Classifier.N165e96e62(i);
        } else if (((Double) i[17]).doubleValue() > 0.35) {
            p = 0;
        }
        return p;
    }

    static double N165e96e62(Object[] i) {
        double p = Double.NaN;
        if (i[20] == null) {
            p = 0;
        } else if (((Double) i[20]).doubleValue() <= -0.55) {
            p = 0;
        } else if (((Double) i[20]).doubleValue() > -0.55) {
            p = 1;
        }
        return p;
    }

    static double N49c0357963(Object[] i) {
        double p = Double.NaN;
        if (i[24] == null) {
            p = 1;
        } else if (((Double) i[24]).doubleValue() <= 3.53) {
            p = Classifier.N69f6c0c264(i);
        } else if (((Double) i[24]).doubleValue() > 3.53) {
            p = 0;
        }
        return p;
    }

    static double N69f6c0c264(Object[] i) {
        double p = Double.NaN;
        if (i[31] == null) {
            p = 1;
        } else if (((Double) i[31]).doubleValue() <= 0.52) {
            p = 1;
        } else if (((Double) i[31]).doubleValue() > 0.52) {
            p = Classifier.N46e936e765(i);
        }
        return p;
    }

    static double N46e936e765(Object[] i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 0;
        } else if (((Double) i[0]).doubleValue() <= -0.26) {
            p = 0;
        } else if (((Double) i[0]).doubleValue() > -0.26) {
            p = 1;
        }
        return p;
    }

    static double N79139c0f66(Object[] i) {
        double p = Double.NaN;
        if (i[57] == null) {
            p = 1;
        } else if (((Double) i[57]).doubleValue() <= 2.19) {
            p = 1;
        } else if (((Double) i[57]).doubleValue() > 2.19) {
            p = Classifier.N766adae267(i);
        }
        return p;
    }

    static double N766adae267(Object[] i) {
        double p = Double.NaN;
        if (i[23] == null) {
            p = 0;
        } else if (((Double) i[23]).doubleValue() <= 1.73) {
            p = Classifier.N6148682a68(i);
        } else if (((Double) i[23]).doubleValue() > 1.73) {
            p = 1;
        }
        return p;
    }

    static double N6148682a68(Object[] i) {
        double p = Double.NaN;
        if (i[53] == null) {
            p = 0;
        } else if (((Double) i[53]).doubleValue() <= 9.67) {
            p = Classifier.N58aecf4b69(i);
        } else if (((Double) i[53]).doubleValue() > 9.67) {
            p = Classifier.N311377b972(i);
        }
        return p;
    }

    static double N58aecf4b69(Object[] i) {
        double p = Double.NaN;
        if (i[57] == null) {
            p = 0;
        } else if (((Double) i[57]).doubleValue() <= 2.78) {
            p = Classifier.N3cab726970(i);
        } else if (((Double) i[57]).doubleValue() > 2.78) {
            p = 0;
        }
        return p;
    }

    static double N3cab726970(Object[] i) {
        double p = Double.NaN;
        if (i[47] == null) {
            p = 0;
        } else if (((Double) i[47]).doubleValue() <= 1.44) {
            p = Classifier.N1be46a9d71(i);
        } else if (((Double) i[47]).doubleValue() > 1.44) {
            p = 0;
        }
        return p;
    }

    static double N1be46a9d71(Object[] i) {
        double p = Double.NaN;
        if (i[47] == null) {
            p = 0;
        } else if (((Double) i[47]).doubleValue() <= 1.23) {
            p = 0;
        } else if (((Double) i[47]).doubleValue() > 1.23) {
            p = 1;
        }
        return p;
    }

    static double N311377b972(Object[] i) {
        double p = Double.NaN;
        if (i[38] == null) {
            p = 1;
        } else if (((Double) i[38]).doubleValue() <= 0.05) {
            p = 1;
        } else if (((Double) i[38]).doubleValue() > 0.05) {
            p = 0;
        }
        return p;
    }

    static double N75192bda73(Object[] i) {
        double p = Double.NaN;
        if (i[26] == null) {
            p = 0;
        } else if (((Double) i[26]).doubleValue() <= 0.04) {
            p = 0;
        } else if (((Double) i[26]).doubleValue() > 0.04) {
            p = Classifier.N478e314574(i);
        }
        return p;
    }

    static double N478e314574(Object[] i) {
        double p = Double.NaN;
        if (i[32] == null) {
            p = 1;
        } else if (((Double) i[32]).doubleValue() <= 0.12) {
            p = 1;
        } else if (((Double) i[32]).doubleValue() > 0.12) {
            p = Classifier.N4844cdb675(i);
        }
        return p;
    }

    static double N4844cdb675(Object[] i) {
        double p = Double.NaN;
        if (i[61] == null) {
            p = 1;
        } else if (((Double) i[61]).doubleValue() <= 0.81) {
            p = 1;
        } else if (((Double) i[61]).doubleValue() > 0.81) {
            p = 0;
        }
        return p;
    }

    static double N1d1d8ccb76(Object[] i) {
        double p = Double.NaN;
        if (i[59] == null) {
            p = 0;
        } else if (((Double) i[59]).doubleValue() <= 2.42) {
            p = Classifier.N7f06725b77(i);
        } else if (((Double) i[59]).doubleValue() > 2.42) {
            p = 0;
        }
        return p;
    }

    static double N7f06725b77(Object[] i) {
        double p = Double.NaN;
        if (i[55] == null) {
            p = 0;
        } else if (((Double) i[55]).doubleValue() <= 3.35) {
            p = Classifier.N3df58478(i);
        } else if (((Double) i[55]).doubleValue() > 3.35) {
            p = 1;
        }
        return p;
    }

    static double N3df58478(Object[] i) {
        double p = Double.NaN;
        if (i[37] == null) {
            p = 0;
        } else if (((Double) i[37]).doubleValue() <= 0.24) {
            p = 0;
        } else if (((Double) i[37]).doubleValue() > 0.24) {
            p = Classifier.N63c1ad3b79(i);
        }
        return p;
    }

    static double N63c1ad3b79(Object[] i) {
        double p = Double.NaN;
        if (i[35] == null) {
            p = 0;
        } else if (((Double) i[35]).doubleValue() <= 1.19) {
            p = 0;
        } else if (((Double) i[35]).doubleValue() > 1.19) {
            p = 1;
        }
        return p;
    }

    static double N40542fa780(Object[] i) {
        double p = Double.NaN;
        if (i[50] == null) {
            p = 1;
        } else if (((Double) i[50]).doubleValue() <= 2.52) {
            p = Classifier.N57049def81(i);
        } else if (((Double) i[50]).doubleValue() > 2.52) {
            p = Classifier.N701ce28a82(i);
        }
        return p;
    }

    static double N57049def81(Object[] i) {
        double p = Double.NaN;
        if (i[39] == null) {
            p = 1;
        } else if (((Double) i[39]).doubleValue() <= 0.3) {
            p = 1;
        } else if (((Double) i[39]).doubleValue() > 0.3) {
            p = 0;
        }
        return p;
    }

    static double N701ce28a82(Object[] i) {
        double p = Double.NaN;
        if (i[31] == null) {
            p = 0;
        } else if (((Double) i[31]).doubleValue() <= 0.58) {
            p = Classifier.N785d0b8683(i);
        } else if (((Double) i[31]).doubleValue() > 0.58) {
            p = Classifier.N62594cca86(i);
        }
        return p;
    }

    static double N785d0b8683(Object[] i) {
        double p = Double.NaN;
        if (i[48] == null) {
            p = 0;
        } else if (((Double) i[48]).doubleValue() <= 0.24) {
            p = 0;
        } else if (((Double) i[48]).doubleValue() > 0.24) {
            p = Classifier.N249fc82684(i);
        }
        return p;
    }

    static double N249fc82684(Object[] i) {
        double p = Double.NaN;
        if (i[70] == null) {
            p = 1;
        } else if (((Double) i[70]).doubleValue() <= 0.62) {
            p = 1;
        } else if (((Double) i[70]).doubleValue() > 0.62) {
            p = Classifier.N760d499285(i);
        }
        return p;
    }

    static double N760d499285(Object[] i) {
        double p = Double.NaN;
        if (i[28] == null) {
            p = 0;
        } else if (((Double) i[28]).doubleValue() <= 0.12) {
            p = 0;
        } else if (((Double) i[28]).doubleValue() > 0.12) {
            p = 1;
        }
        return p;
    }

    static double N62594cca86(Object[] i) {
        double p = Double.NaN;
        if (i[67] == null) {
            p = 1;
        } else if (((Double) i[67]).doubleValue() <= 0.53) {
            p = Classifier.N54c0943787(i);
        } else if (((Double) i[67]).doubleValue() > 0.53) {
            p = 0;
        }
        return p;
    }

    static double N54c0943787(Object[] i) {
        double p = Double.NaN;
        if (i[25] == null) {
            p = 1;
        } else if (((Double) i[25]).doubleValue() <= 0.28) {
            p = 1;
        } else if (((Double) i[25]).doubleValue() > 0.28) {
            p = 0;
        }
        return p;
    }

    static double N2f42d25988(Object[] i) {
        double p = Double.NaN;
        if (i[8] == null) {
            p = 1;
        } else if (((Double) i[8]).doubleValue() <= 0.39) {
            p = 1;
        } else if (((Double) i[8]).doubleValue() > 0.39) {
            p = Classifier.N4bffe94c89(i);
        }
        return p;
    }

    static double N4bffe94c89(Object[] i) {
        double p = Double.NaN;
        if (i[13] == null) {
            p = 0;
        } else if (((Double) i[13]).doubleValue() <= 1.38) {
            p = 0;
        } else if (((Double) i[13]).doubleValue() > 1.38) {
            p = 1;
        }
        return p;
    }

    static double Ne2da38f90(Object[] i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 0;
        } else if (((Double) i[0]).doubleValue() <= 0.51) {
            p = Classifier.N564d088e91(i);
        } else if (((Double) i[0]).doubleValue() > 0.51) {
            p = Classifier.N6d36d4df105(i);
        }
        return p;
    }

    static double N564d088e91(Object[] i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 0;
        } else if (((Double) i[0]).doubleValue() <= -0.63) {
            p = Classifier.N5b6ccee592(i);
        } else if (((Double) i[0]).doubleValue() > -0.63) {
            p = Classifier.N6d644bde96(i);
        }
        return p;
    }

    static double N5b6ccee592(Object[] i) {
        double p = Double.NaN;
        if (i[49] == null) {
            p = 0;
        } else if (((Double) i[49]).doubleValue() <= 2.85) {
            p = Classifier.N44834b4793(i);
        } else if (((Double) i[49]).doubleValue() > 2.85) {
            p = 1;
        }
        return p;
    }

    static double N44834b4793(Object[] i) {
        double p = Double.NaN;
        if (i[70] == null) {
            p = 1;
        } else if (((Double) i[70]).doubleValue() <= 0.83) {
            p = Classifier.N7c3457594(i);
        } else if (((Double) i[70]).doubleValue() > 0.83) {
            p = 0;
        }
        return p;
    }

    static double N7c3457594(Object[] i) {
        double p = Double.NaN;
        if (i[36] == null) {
            p = 1;
        } else if (((Double) i[36]).doubleValue() <= 0.75) {
            p = 1;
        } else if (((Double) i[36]).doubleValue() > 0.75) {
            p = Classifier.N2505084e95(i);
        }
        return p;
    }

    static double N2505084e95(Object[] i) {
        double p = Double.NaN;
        if (i[4] == null) {
            p = 1;
        } else if (((Double) i[4]).doubleValue() <= -0.38) {
            p = 1;
        } else if (((Double) i[4]).doubleValue() > -0.38) {
            p = 0;
        }
        return p;
    }

    static double N6d644bde96(Object[] i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 0;
        } else if (((Double) i[5]).doubleValue() <= 0.57) {
            p = Classifier.N53b113ed97(i);
        } else if (((Double) i[5]).doubleValue() > 0.57) {
            p = 0;
        }
        return p;
    }

    static double N53b113ed97(Object[] i) {
        double p = Double.NaN;
        if (i[57] == null) {
            p = 0;
        } else if (((Double) i[57]).doubleValue() <= 2.41) {
            p = Classifier.Ne93578898(i);
        } else if (((Double) i[57]).doubleValue() > 2.41) {
            p = Classifier.N48250705100(i);
        }
        return p;
    }

    static double Ne93578898(Object[] i) {
        double p = Double.NaN;
        if (i[61] == null) {
            p = 0;
        } else if (((Double) i[61]).doubleValue() <= 0.69) {
            p = 0;
        } else if (((Double) i[61]).doubleValue() > 0.69) {
            p = Classifier.N6b57a93199(i);
        }
        return p;
    }

    static double N6b57a93199(Object[] i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 0;
        } else if (((Double) i[1]).doubleValue() <= 0.93) {
            p = 0;
        } else if (((Double) i[1]).doubleValue() > 0.93) {
            p = 1;
        }
        return p;
    }

    static double N48250705100(Object[] i) {
        double p = Double.NaN;
        if (i[69] == null) {
            p = 0;
        } else if (((Double) i[69]).doubleValue() <= 0.06) {
            p = Classifier.N76effe43101(i);
        } else if (((Double) i[69]).doubleValue() > 0.06) {
            p = Classifier.N23309add103(i);
        }
        return p;
    }

    static double N76effe43101(Object[] i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 0;
        } else if (((Double) i[0]).doubleValue() <= -0.35) {
            p = Classifier.N61e1db6102(i);
        } else if (((Double) i[0]).doubleValue() > -0.35) {
            p = 0;
        }
        return p;
    }

    static double N61e1db6102(Object[] i) {
        double p = Double.NaN;
        if (i[24] == null) {
            p = 1;
        } else if (((Double) i[24]).doubleValue() <= 1.02) {
            p = 1;
        } else if (((Double) i[24]).doubleValue() > 1.02) {
            p = 0;
        }
        return p;
    }

    static double N23309add103(Object[] i) {
        double p = Double.NaN;
        if (i[69] == null) {
            p = 1;
        } else if (((Double) i[69]).doubleValue() <= 0.51) {
            p = Classifier.N4c073537104(i);
        } else if (((Double) i[69]).doubleValue() > 0.51) {
            p = 0;
        }
        return p;
    }

    static double N4c073537104(Object[] i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 0;
        } else if (((Double) i[5]).doubleValue() <= 0.39) {
            p = 0;
        } else if (((Double) i[5]).doubleValue() > 0.39) {
            p = 1;
        }
        return p;
    }

    static double N6d36d4df105(Object[] i) {
        double p = Double.NaN;
        if (i[53] == null) {
            p = 0;
        } else if (((Double) i[53]).doubleValue() <= 2.79) {
            p = 0;
        } else if (((Double) i[53]).doubleValue() > 2.79) {
            p = Classifier.N2ad1b47d106(i);
        }
        return p;
    }

    static double N2ad1b47d106(Object[] i) {
        double p = Double.NaN;
        if (i[9] == null) {
            p = 0;
        } else if (((Double) i[9]).doubleValue() <= 1.81) {
            p = 0;
        } else if (((Double) i[9]).doubleValue() > 1.81) {
            p = Classifier.N2da09081107(i);
        }
        return p;
    }

    static double N2da09081107(Object[] i) {
        double p = Double.NaN;
        if (i[48] == null) {
            p = 1;
        } else if (((Double) i[48]).doubleValue() <= -0.12) {
            p = 1;
        } else if (((Double) i[48]).doubleValue() > -0.12) {
            p = Classifier.N86f1c8e108(i);
        }
        return p;
    }

    static double N86f1c8e108(Object[] i) {
        double p = Double.NaN;
        if (i[52] == null) {
            p = 0;
        } else if (((Double) i[52]).doubleValue() <= 1.89) {
            p = Classifier.N36bbb2f5109(i);
        } else if (((Double) i[52]).doubleValue() > 1.89) {
            p = 0;
        }
        return p;
    }

    static double N36bbb2f5109(Object[] i) {
        double p = Double.NaN;
        if (i[67] == null) {
            p = 0;
        } else if (((Double) i[67]).doubleValue() <= 0.32) {
            p = 0;
        } else if (((Double) i[67]).doubleValue() > 0.32) {
            p = Classifier.N5ce20ee5110(i);
        }
        return p;
    }

    static double N5ce20ee5110(Object[] i) {
        double p = Double.NaN;
        if (i[57] == null) {
            p = 1;
        } else if (((Double) i[57]).doubleValue() <= 1.99) {
            p = 1;
        } else if (((Double) i[57]).doubleValue() > 1.99) {
            p = 0;
        }
        return p;
    }

    static double N7d400c06111(Object[] i) {
        double p = Double.NaN;
        if (i[55] == null) {
            p = 0;
        } else if (((Double) i[55]).doubleValue() <= 2.62) {
            p = 0;
        } else if (((Double) i[55]).doubleValue() > 2.62) {
            p = Classifier.N77d59e27112(i);
        }
        return p;
    }

    static double N77d59e27112(Object[] i) {
        double p = Double.NaN;
        if (i[61] == null) {
            p = 0;
        } else if (((Double) i[61]).doubleValue() <= -0.43) {
            p = 0;
        } else if (((Double) i[61]).doubleValue() > -0.43) {
            p = Classifier.N69824fe7113(i);
        }
        return p;
    }

    static double N69824fe7113(Object[] i) {
        double p = Double.NaN;
        if (i[49] == null) {
            p = 0;
        } else if (((Double) i[49]).doubleValue() <= 2.09) {
            p = Classifier.N6a4bfcce114(i);
        } else if (((Double) i[49]).doubleValue() > 2.09) {
            p = Classifier.N1ab7e9e7118(i);
        }
        return p;
    }

    static double N6a4bfcce114(Object[] i) {
        double p = Double.NaN;
        if (i[4] == null) {
            p = 0;
        } else if (((Double) i[4]).doubleValue() <= 0.8) {
            p = Classifier.N22c26ee7115(i);
        } else if (((Double) i[4]).doubleValue() > 0.8) {
            p = 1;
        }
        return p;
    }

    static double N22c26ee7115(Object[] i) {
        double p = Double.NaN;
        if (i[70] == null) {
            p = 0;
        } else if (((Double) i[70]).doubleValue() <= 0.95) {
            p = Classifier.Naff1185116(i);
        } else if (((Double) i[70]).doubleValue() > 0.95) {
            p = 0;
        }
        return p;
    }

    static double Naff1185116(Object[] i) {
        double p = Double.NaN;
        if (i[70] == null) {
            p = 0;
        } else if (((Double) i[70]).doubleValue() <= 0.82) {
            p = Classifier.N6fd73866117(i);
        } else if (((Double) i[70]).doubleValue() > 0.82) {
            p = 1;
        }
        return p;
    }

    static double N6fd73866117(Object[] i) {
        double p = Double.NaN;
        if (i[23] == null) {
            p = 1;
        } else if (((Double) i[23]).doubleValue() <= 0.77) {
            p = 1;
        } else if (((Double) i[23]).doubleValue() > 0.77) {
            p = 0;
        }
        return p;
    }

    static double N1ab7e9e7118(Object[] i) {
        double p = Double.NaN;
        if (i[4] == null) {
            p = 1;
        } else if (((Double) i[4]).doubleValue() <= 0.92) {
            p = 1;
        } else if (((Double) i[4]).doubleValue() > 0.92) {
            p = 0;
        }
        return p;
    }

    static double N205d4a65119(Object[] i) {
        double p = Double.NaN;
        if (i[64] == null) {
            p = 0;
        } else if (((Double) i[64]).doubleValue() <= 0.52) {
            p = Classifier.N4cbf3d7c120(i);
        } else if (((Double) i[64]).doubleValue() > 0.52) {
            p = Classifier.N390bfd46123(i);
        }
        return p;
    }

    static double N4cbf3d7c120(Object[] i) {
        double p = Double.NaN;
        if (i[4] == null) {
            p = 0;
        } else if (((Double) i[4]).doubleValue() <= 0.54) {
            p = 0;
        } else if (((Double) i[4]).doubleValue() > 0.54) {
            p = Classifier.N1f5dbf41121(i);
        }
        return p;
    }

    static double N1f5dbf41121(Object[] i) {
        double p = Double.NaN;
        if (i[19] == null) {
            p = 1;
        } else if (((Double) i[19]).doubleValue() <= 2.8) {
            p = Classifier.N43b7547d122(i);
        } else if (((Double) i[19]).doubleValue() > 2.8) {
            p = 0;
        }
        return p;
    }

    static double N43b7547d122(Object[] i) {
        double p = Double.NaN;
        if (i[60] == null) {
            p = 0;
        } else if (((Double) i[60]).doubleValue() <= 0.17) {
            p = 0;
        } else if (((Double) i[60]).doubleValue() > 0.17) {
            p = 1;
        }
        return p;
    }

    static double N390bfd46123(Object[] i) {
        double p = Double.NaN;
        if (i[70] == null) {
            p = 0;
        } else if (((Double) i[70]).doubleValue() <= 0.68) {
            p = 0;
        } else if (((Double) i[70]).doubleValue() > 0.68) {
            p = Classifier.N42211bec124(i);
        }
        return p;
    }

    static double N42211bec124(Object[] i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 1;
        } else if (((Double) i[3]).doubleValue() <= 4.51) {
            p = Classifier.Nbb044df125(i);
        } else if (((Double) i[3]).doubleValue() > 4.51) {
            p = 0;
        }
        return p;
    }

    static double Nbb044df125(Object[] i) {
        double p = Double.NaN;
        if (i[49] == null) {
            p = 0;
        } else if (((Double) i[49]).doubleValue() <= 1.88) {
            p = 0;
        } else if (((Double) i[49]).doubleValue() > 1.88) {
            p = 1;
        }
        return p;
    }
}
