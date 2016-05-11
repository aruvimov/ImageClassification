/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framecomponentstesting;

import static framecomponentstesting.FileManager.removeExt;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alisa
 */
public class ClassifierManager {

    public static final String NAME_MARKER = "#NAME#";
    public static final String IMG_FORMAT_MARKER = "#IMAGE FORMAT#";
    public static final String IMG_SIZE_MARKER = "#IMAGE SIZE#";
    public static final String IMG_SIZE_DELIMITER = ",";
    public static final String PARSER_PATH_MARKER = "#PARSER PATH#";
    public static final String CLASSIFIER_CMD_MARKER = "#CLASSIFIER COMMAND MARKER#";
    public static final String TR_CMD_MARKER = "#TRAIN CMD#";
    public static final String TR_CMD_OPT_MARKER = "#TRAIN CMD OPT#";
    public static final String TS_CMD_MARKER = "#TEST CMD#";
    public static final String TS_CMD_OPT_MARKER = "#TEST CMD OPT#";
    public static final String CL_CMD_MARKER = "#CLASSIFY CMD#";
    public static final String CL_CMD_OPT_MARKER = "#CLASSIFY CMD OPT#";
    public static final String OPT_KEY_MARKER = "#KEY#";
    public static final String OPT_VALUE_MARKER = "#VALUE#";
    public static final String OPT_VALUE_DELIMITER = ",";
    public static final String OPT_DELIMITER = "&&&";
    public static final String OPT_VALUE_FORMATTED_START = "<";
    public static final String OPT_VALUE_FORMATTED_END = ">";
    public static final String OPT_KEY_VALUE_SEPARATOR = " ";
    public static final String SPACE = "X";
    public static final String SPACE2 = "$";
    public static final String END_FILE_MARKER = "#END#";

    public static String classifierShortcut = "<classifier>";
    public static String optionsShortcut = "<options>";
    public static String datasetShortcut = "<dataset>";
    public static String featureFileShortcut = "<feature file>";
    public static String imageShorcut = "<image>";

    public static final String CLASSIFIER_DATA_ROOT = "src/data/classifierData";

    public static String markName(String unmarked) {
        return NAME_MARKER + unmarked + NAME_MARKER + "\n";
    }

    public static String markImgFormat(String unmarked) {
        return IMG_FORMAT_MARKER + unmarked + IMG_FORMAT_MARKER + "\n";
    }

    public static String markImgSize(Dimension unmarked) {
        if (unmarked == null) {
            return IMG_SIZE_MARKER + IMG_SIZE_MARKER;
        }
        return IMG_SIZE_MARKER + unmarked.width + IMG_SIZE_DELIMITER + unmarked.height + IMG_SIZE_MARKER + "\n";
    }

    public static String markParserPath(String unmarked) {
        return PARSER_PATH_MARKER + unmarked + PARSER_PATH_MARKER + "\n";
    }

    public static String markClassifierCmd(String unmarked) {
        return CLASSIFIER_CMD_MARKER + unmarked +CLASSIFIER_CMD_MARKER +"\n";
    }

    public static String markTrainCmd(String unmarked) {
        return TR_CMD_MARKER + unmarked + TR_CMD_MARKER + " \n";
    }

    public static String markTrainCmdOpt(ArrayList<String> unmarked) {
        String returnMe = "";
        returnMe += TR_CMD_OPT_MARKER;
        for (String next : unmarked) {
            String key = next.split(OPT_KEY_VALUE_SEPARATOR)[0];
            String values0 = next.split(OPT_KEY_VALUE_SEPARATOR)[1];
            String values1 = values0.split(OPT_VALUE_FORMATTED_START)[1];
            String values = values1.split(OPT_VALUE_FORMATTED_END)[0];
            returnMe += OPT_KEY_MARKER + key + OPT_KEY_MARKER;
            returnMe += OPT_VALUE_MARKER + values + OPT_VALUE_MARKER + SPACE + SPACE2 + OPT_DELIMITER;
        }
        returnMe += TR_CMD_OPT_MARKER + SPACE;
        return returnMe;
    }

    public static String markTestCmd(String unmarked) {
        return TS_CMD_MARKER + unmarked + TS_CMD_MARKER;
    }

    public static String markTestCmdOpt(ArrayList<String> unmarked) {
        String returnMe = "";
        returnMe += TS_CMD_OPT_MARKER;
        for (String next : unmarked) {
            String key = next.split(OPT_KEY_VALUE_SEPARATOR)[0];
            String values0 = next.split(OPT_KEY_VALUE_SEPARATOR)[1];
            String values1 = values0.split(OPT_VALUE_FORMATTED_START)[1];
            String values = values1.split(OPT_VALUE_FORMATTED_END)[0];
            returnMe += OPT_KEY_MARKER + key + OPT_KEY_MARKER;
            returnMe += OPT_VALUE_MARKER + values + OPT_VALUE_MARKER + SPACE + SPACE2 + OPT_DELIMITER;
        }
        returnMe += TS_CMD_OPT_MARKER + SPACE;
        return returnMe;
    }

    public static String markClassifyCmd(String unmarked) {
        return CL_CMD_MARKER + unmarked + CL_CMD_MARKER + " \n";
    }

    public static String markClassifyCmdOpt(ArrayList<String> unmarked) {
        String returnMe = "";
        returnMe += CL_CMD_OPT_MARKER;
        for (String next : unmarked) {
            String key = next.split(OPT_KEY_VALUE_SEPARATOR)[0];
            String values0 = next.split(OPT_KEY_VALUE_SEPARATOR)[1];
            String values1 = values0.split(OPT_VALUE_FORMATTED_START)[1];
            String values = values1.split(OPT_VALUE_FORMATTED_END)[0];
            returnMe += OPT_KEY_MARKER + key + OPT_KEY_MARKER;
            returnMe += OPT_VALUE_MARKER + values + OPT_VALUE_MARKER + SPACE + SPACE2 + OPT_DELIMITER;
        }
        returnMe += CL_CMD_OPT_MARKER + SPACE;
        return returnMe;
    }

    public static String unmarkName(String marked) {
        if (!marked.contains(NAME_MARKER)) {
            return "";
        }
        return marked.split(NAME_MARKER)[1];
    }

    public static String unmarkImgFormat(String marked) {
        if (!marked.contains(IMG_FORMAT_MARKER)) {
            return "(None)";
        }
        return marked.split(IMG_FORMAT_MARKER)[1];
    }

    public static Dimension unmarkImgSize(String marked) {
        if (!marked.contains(IMG_SIZE_MARKER) || !marked.contains(",")) {
            return null;
        }
        String[] split = marked.split(IMG_SIZE_MARKER)[1].split(",");
        try {
            int w = Integer.parseInt(split[0]);
            int h = Integer.parseInt(split[1]);
            return new Dimension(w, h);
        } catch (NumberFormatException | NullPointerException e) {

        }
        return null;
    }

    public static String unmarkParserPath(String marked) {
        if (!marked.contains(PARSER_PATH_MARKER)) {
            return "";
        }
        return marked.split(PARSER_PATH_MARKER)[1];
    }

    public static String unmarkClassifierCmd(String marked) {
        if (!marked.contains(CLASSIFIER_CMD_MARKER)) {
            return "";
        }
        return marked.split(CLASSIFIER_CMD_MARKER)[1];
    }

    public static String unmarkTrainCmd(String marked) {
        if (!marked.contains(TR_CMD_MARKER)) {
            return "";
        }
        return marked.split(TR_CMD_MARKER)[1];
    }

    public static ArrayList<String> unmarkTrainCmdOpt(String marked) {
        ArrayList<String> returnMe = new ArrayList<>();
        if (!marked.contains(TR_CMD_OPT_MARKER)) {
            return returnMe;
        }
        String s1 = marked.split(TR_CMD_OPT_MARKER)[1];
        if (s1.length() == 0) {
            return returnMe;
        }
        if (!s1.contains(OPT_DELIMITER)) {
            return returnMe;
        }
        String[] ss = s1.split(OPT_DELIMITER);
        for (String next : ss) {
            if (next.length() > 0 && next.contains(OPT_KEY_MARKER)) {
                String key = next.split(OPT_KEY_MARKER)[1];
                String values = next.split(OPT_VALUE_MARKER)[1];
                String formatted = key + OPT_KEY_VALUE_SEPARATOR
                        + OPT_VALUE_FORMATTED_START + values
                        + OPT_VALUE_FORMATTED_END + SPACE + SPACE2 + OPT_DELIMITER;
                returnMe.add(formatted);
            }
        }
        return returnMe;
    }

    public static String unmarkTestCmd(String marked) {
        if (!marked.contains(TS_CMD_MARKER)) {
            return "";
        }
        return marked.split(TS_CMD_MARKER)[1];
    }

    public static ArrayList<String> unmarkTestCmdOpt(String marked) {
        ArrayList<String> returnMe = new ArrayList<>();
        if (!marked.contains(TS_CMD_OPT_MARKER)) {
            return returnMe;
        }

        String s1 = marked.split(TS_CMD_OPT_MARKER)[1];
        if (!s1.contains(OPT_DELIMITER)) {
            return returnMe;
        }
        String[] ss = s1.split(OPT_DELIMITER);
        for (String next : ss) {
            if (next.length() > 0) {
                String key = next.split(OPT_KEY_MARKER)[1];
                String values = next.split(OPT_VALUE_MARKER)[1];
                String formatted = key + OPT_KEY_VALUE_SEPARATOR
                        + OPT_VALUE_FORMATTED_START + values
                        + OPT_VALUE_FORMATTED_END + SPACE + SPACE2 + OPT_DELIMITER;
                returnMe.add(formatted);
            }
        }

        return returnMe;
    }

    public static String unmarkClassifyCmd(String marked) {
        if (!marked.contains(CL_CMD_MARKER)) {
            return "";
        }
        return marked.split(CL_CMD_MARKER)[1];
    }

    public static ArrayList<String> unmarkClassifyCmdOpt(String marked) {
        ArrayList<String> returnMe = new ArrayList<>();
        if (!marked.contains(CL_CMD_OPT_MARKER)) {
            return returnMe;
        }
        //String s0 = marked.split(CL_CMD_OPT_MARKER)[1];
        String s1 = marked.split(CL_CMD_OPT_MARKER)[1];
        if (!s1.contains(OPT_DELIMITER)) {
            return returnMe;
        }
        String[] ss = s1.split(OPT_DELIMITER);
        for (String next : ss) {
            if (next.length() > 0) {
                String key = next.split(OPT_KEY_MARKER)[1];
                String values = next.split(OPT_VALUE_MARKER)[1];
                String formatted = key + OPT_KEY_VALUE_SEPARATOR
                        + OPT_VALUE_FORMATTED_START + values
                        + OPT_VALUE_FORMATTED_END + SPACE + SPACE2 + OPT_DELIMITER;
                returnMe.add(formatted);
            }
        }
        return returnMe;
    }

//    public ArrayList<StringDouble> void classify() {
//        
//    }
    public static void writeToClassifierFile(String data) {

        String name = unmarkName(data).toLowerCase();
        File file = new File(CLASSIFIER_DATA_ROOT);
        int numOfDefinedClassifiers = file.list().length;
        if (name.equals("")) {
            name = "classifier" + numOfDefinedClassifiers;
        }

        String classifierDataPath = CLASSIFIER_DATA_ROOT + "\\" + name + ".txt";
        String[] lines = data.split("\n");

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(classifierDataPath, "UTF-8");
            for (String line : lines) {
                writer.println(line);
            }
            writer.println(END_FILE_MARKER);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClassifierManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ClassifierManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
        }
    }

    static String[] getClassifierList() {
        File classifierDir = new File(CLASSIFIER_DATA_ROOT);
        String[] classifierFilesNames = classifierDir.list();
        String[] classifierNames = new String[classifierFilesNames.length];
        for (int i = 0; i < classifierFilesNames.length; i++) {
            classifierNames[i] = FileManager.removeExt(classifierFilesNames[i]);
        }
        return classifierNames;
    }

    static String loadData(String classifierName) {
        String path = CLASSIFIER_DATA_ROOT + "\\" + classifierName + ".txt";
        String data = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String str;
            while ((str = in.readLine()) != null) {
                data += str;
            }
            in.close();

        } catch (IOException e) {
            System.err.println("ClassifierManager loadData: Couldn't read file " + path);
        }
        return data;
    }

    static String getKeyFromUnmarked(String option) {
        if (!option.contains(OPT_KEY_VALUE_SEPARATOR)) {
            return "";
        }
        return option.split(OPT_KEY_VALUE_SEPARATOR)[0];
    }

    static String[] getValuesFromUnmarked(String option) {
        if (!option.contains(OPT_KEY_VALUE_SEPARATOR)) {
            return null;
        }
        String valuesLine0 = option.split(OPT_KEY_VALUE_SEPARATOR)[1];
        String valuesLine1 = valuesLine0.split(OPT_VALUE_FORMATTED_START)[1];
        String valuesLine2 = valuesLine1.split(OPT_VALUE_FORMATTED_END)[0];
        if (valuesLine2.length() == 0) {
            return null;
        }
        return valuesLine2.split(OPT_VALUE_DELIMITER);
    }
//public static String classifierShortcut = "<classifier>";
//    public static String optionsShortcut = "<options>";
//    public static String datasetShortcut = "<dataset>";
//    public static String featureFileShortcut = "<feature file>";
//    public static String imageShorcut = "<images>";
    static void classifyNewImage(String classifierName, String imagePath, ArrayList<String> options) {
        String classifyCMD = constructClassifyCMD(classifierName, imagePath,options);
        System.out.println("classifyCMD = " + classifyCMD);
        
    }

    private static String constructClassifyCMD(String classifierName, String imagePath, ArrayList<String> options) {
        String classifyCMDForm = getClassifyCMD(classifierName);
         if (classifyCMDForm.contains(classifierShortcut)) {
            classifyCMDForm=classifyCMDForm.replaceAll(classifierShortcut, classifierName);
        } if (classifyCMDForm.contains(optionsShortcut)) {
            classifyCMDForm=classifyCMDForm.replaceAll(optionsShortcut, optionsToString(options));
        }if (classifyCMDForm.contains(datasetShortcut)){
            String[] ss = classifyCMDForm.split(datasetShortcut);
            classifyCMDForm=ss[0]+getDataset(classifierName)+ss[1];
        }if (classifyCMDForm.contains(featureFileShortcut)) {
            String[] ss = classifyCMDForm.split(featureFileShortcut);
            classifyCMDForm=ss[0]+getFeatureFile(classifierName)+ss[1];
        }if (classifyCMDForm.contains(imageShorcut)) {
            String[] ss = classifyCMDForm.split(imageShorcut);
            classifyCMDForm=ss[0]+imagePath+ss[1];
        }
        return classifyCMDForm;
    }

    private static String getClassifyCMD(String classifierName) {
        String data = loadData(classifierName);
        String classifyCMDForm = unmarkClassifyCmd(data);
        
       
        return classifyCMDForm;
    }

    public static String optionsToString(ArrayList<String> options) {
        String toString = "";
        for (int i = 0; i < options.size(); i++) {
            toString+="-"+options.get(i);
            if (i<options.size()-1) {
                toString+=" ";
            }
        }
        return toString;
    }

    private static String getDataset(String classifierName) {
        return FileManager.getDataset(classifierName);
    }

    
    private static String getFeatureFile(String classifierName) {
        String path = FileManager.getDataset(classifierName);
        File pathFile = new File(path);
        return path+ pathFile.getName()+".fit";
    }

}
