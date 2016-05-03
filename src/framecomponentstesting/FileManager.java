/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framecomponentstesting;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Alisa
 */
public class FileManager {

    public static File currentDir = new File("src/images");
    public static File dataDir = new File("src/data");

    public static final String iconMarker = "#####";
    public static String emptyImgPath = "src/data/emptyDir.jpg";
    public static BufferedImage emptyDirIMG;
    public static int level = 0;

    public static void init() {
        try {
            emptyDirIMG = ImageIO.read(new File(emptyImgPath));
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void navigateTo(String fileName) {
        level++;
        String path = currentDir.getPath();
        File newDir = new File(path + "/" + fileName);
        currentDir = newDir;
        System.out.println("nagivate To: currentDir = " + currentDir.getPath());
    }

    public static void goUpOneLevel() {
        level--;
        currentDir = currentDir.getParentFile();
        System.out.println("go up " + currentDir.getPath());
    }

    static SmartLabel createTitleLabel() {
        SmartLabel sl;
        if (level == 0) {
            sl = new SmartLabel(null, false);
        } else {
            sl = new SmartLabel(currentDir, true);
        }
        return sl;
    }

    static ArrayList<SmartLabel> createIconLabels() {
        ArrayList<SmartLabel> labels = new ArrayList<SmartLabel>();
        int count = 0;
        for (File file : currentDir.listFiles()) {
            if (!file.getName().contains(iconMarker)) {
                int col = count % SmartLabel.iconCols;
                int row = count / SmartLabel.iconRows;
                Point colRow = new Point(col, row);
                SmartLabel sl = new SmartLabel(file, colRow, false);
                count++;
                labels.add(sl);
            }
        }

        return labels;
    }

    static SmartLabel createReturnLabel() {
        File parentFile = currentDir.getParentFile();
        String returnTo = "Return to " + parentFile.getName();
        return new SmartLabel(returnTo);
    }

    public static BufferedImage traverse(File dir) {
        BufferedImage iconIMG = null;
        System.out.println("Traversing " + dir.getName());
        if (dir.isDirectory()) {
            File iconFile = new File(dir + "\\" + dir.getName() + "icon.tiff");
            String[] children = dir.list();
            for (int i = 0; children != null && i < children.length; i++) {
                String childName = children[i];
                String childPath = dir.getPath() + "\\" + childName;
                if (containsSubdirs(new File(childPath))) {
                    System.out.println("\tContains subdirectories");
                    iconIMG = traverse(new File(childPath));
                } else {
                    if (new File(childPath).list().length == 0) {//dir is empty
                        System.out.println("\tThe directory is empty, setting img to empty icon");
                        iconIMG = emptyDirIMG;
                    } else {
                        //set image icon to be first image in that dir
                        File imageFile = new File(childPath);
                        System.out.println("\tGetting first image in that directory");
                        try {
                            iconIMG = ImageIO.read(imageFile);
                            break;
                        } catch (IOException ex) {
                            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } // else:
                //iconIMG + first file
            }
            if (iconIMG != null) {
                System.out.println("\tWriting iconIMG to file");
                try {
                    ImageIO.write(iconIMG, "png", iconFile);
                } catch (IOException ex) {
                    Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (dir.isFile()) {
            if (dir.getName().endsWith(".tiff")) {
                try {
                    System.out.println("WHY DID WE GET HERE??");
                    iconIMG = ImageIO.read(dir);
                } catch (IOException ex) {
                    Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(dir.getAbsolutePath());//change it if needed
            }
        }

        return iconIMG;
    }

    public static String createIcons(File dir) {
        String iconWritePath = dir.getPath() + "\\" + iconMarker + dir.getName();
        String iconReadPath = emptyImgPath;

        //if directory is empty, icon is "empty folder" image
        if (dir.isFile()) {
            return dir.getPath();
        }
        if (dir.listFiles().length == 0) {
            iconReadPath = emptyImgPath;
        } //if directory contains non-directory files (hopefully images), read 1st
        else if (!containsSubdirs(dir)) {
            String[] fileNames = dir.list();
            for (int i = 0; i < fileNames.length; i++) {
                String name = fileNames[i];
                //if file isn't icon or empty folder image, use
                if (!name.contains("iconMarker") && (!name.contains("emptyDir")) && (isImage(name))) {
                    iconReadPath = dir.getPath() + "\\" + name;
                    break;
                }
            }
            //if no normal images were found

        } else {
            File[] subdirs = dir.listFiles();
            //take first image icon
            iconReadPath = createIcons(subdirs[0]);
            boolean foundUsableIcon = false;
            for (File subdir : subdirs) {
                String subdirIconPath = createIcons(subdir);
                if (!foundUsableIcon && !subdirIconPath.contains(iconMarker)
                        && (!subdirIconPath.contains("emptyDir"))) {
                    iconReadPath = subdirIconPath;
                    foundUsableIcon = true;
                }
            }
        }
        //if image is null, icon becomes "empty folder" image

        //write image to file
        try {

            File iconReadFile = new File(iconReadPath);
            BufferedImage iconImage = ImageIO.read(iconReadFile);
            //ensure that read and write extensions are same
            //String extension = getExtension(iconReadPath);
            File iconWriteFile = new File(iconWritePath + ".png");
            ImageIO.write(iconImage, "png", iconWriteFile);
        } catch (IOException ex) {
            System.out.println("Read: " + iconReadPath);
            System.out.println("Write: " + iconWritePath);
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        //return for parent dir's use, if applicable
        return iconReadPath;
    }

    private static boolean containsSubdirs(File dir) {
        for (File subdir : dir.listFiles()) {
            if (subdir.isDirectory()) {
                return true;
            }
        }
        return false;

    }

    private static boolean isImage(String name) {
        System.out.println("Is this an image? Name: " + name);
        if (name.contains(iconMarker)) {
            return false;
        }
        String imageExtensions = "tiff png jpeg jpg";
        String ext = getExtension(name);
        if (imageExtensions.contains(ext)) {
            return true;
        }

        return false;
    }

    private static String getExtension(String iconReadPath) {
        String ext = "jpeg";
        int i = iconReadPath.lastIndexOf('.');
        if (i > 0) {
            ext = iconReadPath.substring(i + 1);
        }
        return ext;
    }

    static ArrayList<SmartLabel> createIconImageLabels() {
        ArrayList<SmartLabel> labels = new ArrayList<SmartLabel>();
        int count = 0;
        for (File file : currentDir.listFiles()) {
            if (!file.getName().contains(iconMarker)) {
                int col = count % SmartLabel.iconCols;
                int row = count / SmartLabel.iconRows;
                Point colRow = new Point(col, row);
                SmartLabel sl = new SmartLabel(file, colRow, true);
                count++;
                labels.add(sl);
            }
        }

        return labels;

    }

    static String getIconPath(File dir) {
        String dirPath = dir.getPath().split("src")[1];
        dirPath = dirPath.replace('\\', '/');
        String iconPath = null;
        if (dir.isDirectory()) {
            iconPath = dirPath + "/" + iconMarker + dir.getName() + ".png";
        } else {
            iconPath = dirPath;
        }
        return iconPath;
    }
}
