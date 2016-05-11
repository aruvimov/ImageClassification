/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framecomponentstesting;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Alisa
 */
public class FrameComponentsTesting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //FileManager.renameImageToHtmlInfo("C:\\Nastya\\School\\2016-01_Spring\\THESIS\\database\\ArtStor\\13 Pop Art");
        //FileManager.init();
        //FileManager.createClassifierCombDirs(new File("src\\classifierImages\\classifier2\\art history"), "src\\classifierImages\\classifier2\\art history");
        //File newDir = new File("C:\\Nastya\\School\\2016-01_Spring\\THESIS\\database\\ArtStor\\Italian Renaissance\\Botticcelli");
        //FileManager.saveClassifierImages(newDir);
        // FileManager.createIcons(new File("src/images/art history"),false);
        //System.out.println("f="+SmartLabel.removeExt("f.txt"));
        //String classifierPath = "classifier1";
        //String userDirPath = "src\\classifierImages\\classifier1\\art history\\Post-Impressionism\\Gaugin";
        //System.out.println(FileManager.getTopClassfCombDir(userDirPath));
        //new FileManager();
//        String name = "name";
//        double num = 5;
//        int buffer=4;
//        String format = "%" + buffer + "s";
////System.out.printf(format, num);
//        String s = String.format(format, name);
//        System.out.println(s);

       GalleryFrame gf = new GalleryFrame();

        
        
    }
    private static int calculateDirLength(File dir) {
        int returnInt = 1;
        if (dir.isFile()) {
            return returnInt;
        } else {
            for (File file : dir.listFiles()) {
                returnInt += calculateDirLength(file);
            }
        }
        return returnInt;
    }
    
}
