/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framecomponentstesting;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Alisa
 */
public class FileManager {

    public static File currentDir = new File("src/images");
    public static File dataDir = new File("src/data");
    public static String imageIconDir = "src/icons";
    public static final String iconMarker = "#####";
    public static String emptyImgPath = "src/data/emptyDir.jpg";
    public static String upArrowPath = "src/data/up.png";
    public static String downArrowPath = "src/data/down.png";
    public static String addArrowPath = "src/data/add.png";
    public static BufferedImage emptyDirIMG;
    public static int level = 0;
    public static int page = 0;
    public static boolean displayImage = false;

    public static void init() {
        try {
            emptyDirIMG = ImageIO.read(new File(emptyImgPath));
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void navigateTo(File file) {
        //fileName of null indicates going up a directory
        String navigatePath;
        if (file == null) {
            //if level = 0 (base), do nothing
            if (level == 0) {
                return;
            }
            //navigate to parent path
            navigatePath = currentDir.getParentFile().getPath();
            level--;
            displayImage = false;
            //navigate to user-chosen directory
        } else {
            navigatePath = currentDir.getPath() + "/" + file.getName();
            level++;
        }
        File newDir = new File(navigatePath);
        if (newDir.isFile()) {
            displayImage = true;
        }
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
            if (!file.getName().contains(iconMarker) && isImage(file.getName())) {
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

//    public static BufferedImage traverse(File dir) {
//        BufferedImage iconIMG = null;
//        System.out.println("Traversing " + dir.getName());
//        if (dir.isDirectory()) {
//            File iconFile = new File(dir + "\\" + dir.getName() + "icon.tiff");
//            String[] children = dir.list();
//            for (int i = 0; children != null && i < children.length; i++) {
//                String childName = children[i];
//                String childPath = dir.getPath() + "\\" + childName;
//                if (containsSubdirs(new File(childPath))) {
//                    System.out.println("\tContains subdirectories");
//                    iconIMG = traverse(new File(childPath));
//                } else {
//                    if (new File(childPath).list().length == 0) {//dir is empty
//                        System.out.println("\tThe directory is empty, setting img to empty icon");
//                        iconIMG = emptyDirIMG;
//                    } else {
//                        //set image icon to be first image in that dir
//                        File imageFile = new File(childPath);
//                        System.out.println("\tGetting first image in that directory");
//                        try {
//                            iconIMG = ImageIO.read(imageFile);
//                            break;
//                        } catch (IOException ex) {
//                            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//                } // else:
//                //iconIMG + first file
//            }
//            if (iconIMG != null) {
//                System.out.println("\tWriting iconIMG to file");
//                try {
//                    ImageIO.write(iconIMG, "png", iconFile);
//                } catch (IOException ex) {
//                    Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//        if (dir.isFile()) {
//            if (dir.getName().endsWith(".tiff")) {
//                try {
//                    System.out.println("WHY DID WE GET HERE??");
//                    iconIMG = ImageIO.read(dir);
//                } catch (IOException ex) {
//                    Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                System.out.println(dir.getAbsolutePath());//change it if needed
//            }
//        }
//
//        return iconIMG;
//    }
    public static String createIcons(File dir, boolean skipExisting) {
        String iconWritePath = imageIconDir + removeExt(dir.getPath().split("src")[1])
                + "\\" + iconMarker + removeExt(dir.getName());
        String iconReadPath = emptyImgPath;

        // if dir is file, then we need to make a thumnail of it
        if (dir.isFile()) {
            iconReadPath = dir.getPath();
        } //if directory is empty, icon is "empty folder" image
        else if (dir.listFiles().length == 0) {
            iconReadPath = emptyImgPath;
        } //if directory contains non-directory files (hopefully images), read 1st
        else if (!containsSubdirs(dir)) {
            File[] files = dir.listFiles();
            boolean foundUsableIcon = false;
            for (int i = 0; i < files.length; i++) {
                String name = files[i].getName();
                //if file isn't icon or empty folder image, use
                if (!name.contains("iconMarker") && (!name.contains("emptyDir")) && (isImage(name))) {
                    if (!foundUsableIcon) {
                        iconReadPath = dir.getPath() + "\\" + name;
                        foundUsableIcon = true;
                    }
                    createIcons(files[i], skipExisting);
                }
            }
            //create image icon for all 

            //directory contains subdirs, repeat process for all subdirectories
        } else {
            File[] subdirs = dir.listFiles();
            //take first image icon
            iconReadPath = createIcons(subdirs[0], skipExisting);
            boolean foundUsableIcon = false;
            for (File subdir : subdirs) {
                String subdirIconPath = createIcons(subdir, skipExisting);
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
            BufferedImage resizedImage = resizeIcon(iconImage);
            File iconWriteFile = new File(iconWritePath + ".png");
            if (!iconWriteFile.exists()) {
                iconWriteFile.mkdirs();
            } else {
                if (skipExisting) {
                    return iconReadPath;
                }
            }
            ImageIO.write(resizedImage, "png", iconWriteFile);
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
        //System.out.println("Is this an image? Name: " + name);
        if (name.contains(iconMarker)) {
            return false;
        }
        String imageExtensions = ".tiff .png .jpeg .jpg";
        String ext = getExtension(name);
        if (imageExtensions.contains(ext)) {
            return true;
        }

        return false;
    }

    private static String getExtension(String iconReadPath) {
        String ext = "";
        int i = iconReadPath.lastIndexOf('.');
        if (i > 0) {
            ext = "." + iconReadPath.substring(i + 1);
        }
        return ext;
    }

    static String getIconPath(File dir) {
        String dirPath = removeExt(dir.getPath().split("src")[1]);
        String iconRoot = imageIconDir.split("src")[1];
        dirPath = dirPath.replace('\\', '/');
        String iconPath = null;
        iconPath = iconRoot + dirPath + "/" + fileNameToIconImageName(dir.getName());

        return iconPath;
    }

    static Dimension getIconTargetDim() {
        int targetWidth = SmartLabel.iconWidth + SmartLabel.wBuff;
        int targetHeight = SmartLabel.iconHeight + SmartLabel.hBuff;
        return new Dimension(targetWidth, targetHeight);
    }

    public static BufferedImage getScaledInstance(BufferedImage img, Dimension target, boolean crop) {


        int targetWidth = (int) target.width;
        int targetHeight = (int) target.height;

        Object hint = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
        boolean higherQuality = true;
        int type = (img.getTransparency() == Transparency.OPAQUE)
                ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage ret = (BufferedImage) img;
        int w, h;
        if (higherQuality) {
            // Use multi-step technique: start with original size, then
            // scale down in multiple passes with drawImage()
            // until the target size is reached
            w = img.getWidth();
            h = img.getHeight();
        } else {
            // Use one-step technique: scale directly from original
            // size to target size with a single drawImage() call
            w = targetWidth;
            h = targetHeight;
        }

        do {
            if (higherQuality && w > targetWidth) {
                w /= 2;
                if (w < targetWidth) {
                    w = targetWidth;
                }
            }

            if (higherQuality && h > targetHeight) {
                h /= 2;
                if (h < targetHeight) {
                    h = targetHeight;
                }
            }

            BufferedImage tmp = new BufferedImage(w, h, type);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g2.drawImage(ret, 0, 0, w, h, null);
            g2.dispose();

            ret = tmp;
        } while (w != targetWidth || h != targetHeight);
        
        return ret;
    }

    static boolean renameDir(SmartLabel titleLabel, String newText) {
        String extension = getExtension(titleLabel.getLinkedFile().getName());
        newText = newText.toLowerCase() + extension;

        File imagesDir = titleLabel.getLinkedFile();
        String imagesDirPath = imagesDir.getPath();
        imagesDirPath = imagesDirPath.toLowerCase();

        String newDirPath = imagesDir.getParent() + "\\" + newText;
        newDirPath = newDirPath.toLowerCase();
        File newDir = new File(newDirPath);

        imagesDir.renameTo(newDir);
        titleLabel.setLinkedFile(newDir);
        currentDir = newDir;

        //rename in src/icons
        String iconDirPath = imagePathToIconPath(imagesDirPath);

        File iconDir = new File(iconDirPath);

        String newIconDirPath = iconDir.getParent() + "\\" + removeExt(newText);
        newIconDirPath = newIconDirPath.toLowerCase();
        File newIconDir = new File(newIconDirPath);
        iconDir.renameTo(newIconDir);

        String iconImageName = fileNameToIconImageName(iconDir.getName());
        String iconImagePath = newIconDir.getPath() + "\\" + iconImageName;
        File iconImage = new File(iconImagePath);
        String newIconImagePath = iconImage.getParent() + "\\" + fileNameToIconImageName(newText);

        File newIconImage = new File(newIconImagePath);
        iconImage.renameTo(newIconImage);

        boolean pause = true;
        while (pause) {
            try {
                //try reading the new icon image
                ImageIO.read(newIconImage);
                pause = false;
            } catch (IOException ex) {
                try {
                    //if couldn't read image, sleep half a sec
                    Thread.sleep(500);
                } catch (InterruptedException ex2) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        return true;

    }

    static String imagePathToIconPath(String imagePath) {
        System.out.println("imagePathToIconPath");
        String path = imageIconDir + imagePath.split("src")[1];
        path = removeExt(path);
        return path;
    }

    static String fileNameToIconImageName(String fileName) {
        return iconMarker + removeExt(fileName) + ".png";
    }

    static String removeExt(String name) {
        if (name.contains(".")) {
            name = name.substring(0, name.lastIndexOf('.'));
        }
        return name;

    }

    static SmartLabel createImageDisplayLabel() {
        return new SmartLabel(true, currentDir);
    }

    public static ImageIcon createOrgIconImage(File linkedFile) {
        BufferedImage bImg = null;
        try {
            bImg = ImageIO.read(linkedFile);
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        Dimension imageDim = new Dimension(bImg.getWidth(), bImg.getHeight());
        int targetWidth = GalleryPanel.panelSize.width
                - SmartLabel.iconsLeftBuffer * 2;
        int targetHeight = (int) (GalleryPanel.panelSize.height
                - SmartLabel.iconsTopBuffer * 1.4);
        Dimension targetDim = new Dimension(targetWidth, targetHeight);
        Dimension newSize = getScaledDimensionMax(imageDim, targetDim);
        bImg = getScaledInstance(bImg, newSize, false);
        return new ImageIcon(bImg);

    }

    public static BufferedImage resizeIcon(BufferedImage img) {
        Dimension imageDim = new Dimension(img.getWidth(), img.getHeight());
        Dimension targetDim = new Dimension(SmartLabel.iconWidth, SmartLabel.iconHeight);
        Dimension newSize = getScaledDimensionMin(imageDim, targetDim);
        BufferedImage resizedImg = getScaledInstance(img, newSize, true);
        Rectangle rect = new Rectangle(0, 0, targetDim.width, targetDim.height);
        BufferedImage cropImg = cropImage(resizedImg, rect);
        
        return cropImg;
    }
    public static Dimension getScaledDimensionMin(Dimension imgSize, Dimension boundary) {
         int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;
        
        if (original_width > original_height) {
            //scale height, maintaining aspect ratio
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        // first check if we need to scale width
        else {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height

        return new Dimension(new_width, new_height);       
    }

    public static Dimension getScaledDimensionMax(Dimension imgSize, Dimension boundary) {

        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;
        
        

        // first check if we need to scale width
        if (original_width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }

    static ImageIcon createFunctionIconImage(int labelNum) {
        File labelFile = null;
        if (labelNum == SmartLabel.UP_LABEL) {
            labelFile = new File(upArrowPath);
        } else if (labelNum == SmartLabel.DOWN_LABEL) {
            labelFile = new File(downArrowPath);
        } else { //label = add
            labelFile = new File(addArrowPath);
        }
        ImageIcon icon = getImageIcon(labelFile);
        Image image = icon.getImage(); // transform it 
        int w;
        if (labelNum == SmartLabel.ADD_LABEL) {
            w = SmartLabel.addWidth;
        } else {
            w = SmartLabel.arrowWidth;
        }

        Image newimg = image.getScaledInstance(w, w, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        icon = new ImageIcon(newimg);
        return icon;
    }

    static ImageIcon createImageIcon(File file) {
        String path = FileManager.getIconPath(file);
        java.net.URL imgURL = SmartLabel.class.getResource(path);
        if (imgURL != null) {
            System.out.println("Found path!");
            return new ImageIcon(imgURL, "description");
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    static ImageIcon getImageIcon(File file) {
        String path = file.getPath().split("src")[1];
        path = path.replace('\\', '/');
        java.net.URL imgURL = SmartLabel.class.getResource(path);
        if (imgURL != null) {
            System.out.println("Found path!");
            return new ImageIcon(imgURL, "description");
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private static BufferedImage cropImage(BufferedImage src, Rectangle rect) {
        BufferedImage dest = src.getSubimage(0, 0, rect.width, rect.height);
        return dest;
    }

}
