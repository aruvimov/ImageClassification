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

        //FileManager.renameImageToHtmlInfo("C:\\Nastya\\School\\2016-01_Spring\\THESIS\\database\\ArtStor\\Italian Renaissance\\Botticcelli");
        //FileManager.init();
        //FileManager.createClassifierCombDirs(new File("src\\classifierImages\\classifier2\\art history"), "src\\classifierImages\\classifier2\\art history");
        //File newDir = new File("C:\\Nastya\\School\\2016-01_Spring\\THESIS\\database\\ArtStor\\Italian Renaissance\\Botticcelli");
        //FileManager.saveClassifierImages(newDir);
        // FileManager.createIcons(new File("src/images/art history"),false);
        //System.out.println("f="+SmartLabel.removeExt("f.txt"));
        //String classifierPath = "classifier1";
        //String userDirPath = "src\\classifierImages\\classifier1\\art history\\Post-Impressionism\\Gaugin";
        //System.out.println(FileManager.getTopClassfCombDir(userDirPath));
        String test = "#CLASSIFY CMD OPT##CLASSIFY CMD OPT#lalala";
        String s1 = test.split("#CLASSIFY CMD OPT#")[1];
        System.out.println("s1 = " + s1);
        String s2 = s1.split("#CLASSIFY CMD OPT#")[0];
        System.out.println("s2 = " + s2);
        GalleryFrame gf = new GalleryFrame();
        //ImageManager.convertImage();
        //
        
        
        
    }
    
}
