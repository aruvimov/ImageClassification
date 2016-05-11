/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framecomponentstesting;

import static framecomponentstesting.FileManager.lastOpenedDir;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Alisa
 */
public class ImageManager {

    public static void convertImage() {
        JFileChooser chooser = new JFileChooser();
        //FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "jpeg", "png", "tiff");
        FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        chooser.setFileFilter(imageFilter);
        chooser.setCurrentDirectory(lastOpenedDir);
        chooser.setDialogTitle("Select a File to Import");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(true);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            lastOpenedDir = chooser.getSelectedFile();
            //System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            // System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
        } else {
            // System.out.println("No Selection ");
        }
        File imgFile = chooser.getSelectedFile();
        convertTo(imgFile, "jpg");
        convertTo(imgFile, "png");
        convertTo(imgFile, "bmp");
        convertTo(imgFile, "wbmp");
        convertTo(imgFile, "gif");
        convertTo(imgFile, "jpeg");

    }

    public static void convertTo(File imgFile, String format) {
        BufferedImage img = null;

        try {
            img = ImageIO.read(imgFile);
            String noExt = FileManager.removeExt(imgFile.getPath());
            String path = noExt+"."+format;
            File outputfile = new File(path);
            ImageIO.write(img, format, outputfile);

        } catch (IOException ex) {
            Logger.getLogger(ImageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
