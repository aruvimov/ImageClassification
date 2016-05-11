/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framecomponentstesting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Alisa
 */
public class SmartLabel extends JLabel {

    //instant variables:
    File linkedFile;
    Point loc;
    boolean selected = false;
    int labelNum = -1;

    static final int smallFontSize = 16;
    static final int titleFontSize = 48;
    static final int mediumFontSize = 24;
    static final int mediumLargeFontSize = 36;
    static final int tinyFontSize = 14;
    static final Font titleFont = new Font("Tw Cen MT Condensed", 1, titleFontSize);
    static final Font smallFont = new Font("Tw Cen MT Condensed", 1, smallFontSize);
    static final Font mediumFont = new Font("Tw Cen MT Condensed", 1, mediumFontSize);
    static final Font mediumLargeFont = new Font("Tw Cen MT Condensed", 1, mediumLargeFontSize);
    static final Font tinyFont = new Font("Tw Cen MT Condensed", 1, tinyFontSize);


    //item is shown as selected if mouse is within this distance
    final int mouseBuffer = 3;

    public static int UP_LABEL = 0;
    public static int DOWN_LABEL = 1;
    public static int TRASH_LABEL = 2;
    public static int TRAIN_LABEL = 3;
    public static int TEST_LABEL = 4;
    public static int CLASSIFY_LABEL = 5;

    //icon label location variables
    public static final int iconsLeftBuffer = 150;
    public static final int iconsTopBuffer = 140;
    public static final int iconsHozSpace = 150;
    public static final int iconsVerSpace = 150;
    public static final int iconWidth = 100;
    public static final int iconHeight = 100;
    public static final int iconsHozGap = iconsHozSpace - iconWidth;
    public static final int iconsVerGap = iconsVerSpace - iconHeight;

    //magic numbers which results in perfect icon label allignment 
    public static int xBoundBuff = 10;
    public static int yBoundBuff = 27;
    public static int wBuff = 7;
    public static int hBuff = 11;
    public static int xLocBuff = -3;
    public static int yLocBuff = -3;

    //label locations
    static final int titleLocX = 70;
    static final int titleLocY = 60;
    static final int returnLocX = 30;
    static final int returnLocY = 30;
    static final int pageNumLocX = titleLocX;
    static final int pageNumLocY = (int) (iconsTopBuffer + 109);
    //static final int classifierLocY = (int) (Painter.blueBoxY + iconsVerSpace * .2);
    static final int classifierLocY = (int) (GalleryPanel.panelSize.height-(GalleryPanel.panelSize.height-Painter.blueBoxY)/2)-mediumFontSize/2;
    static final int trainLabelX = iconsLeftBuffer;
    static final int testLabelX = iconsLeftBuffer + iconsHozSpace;
    static final int classifyLabelX = iconsLeftBuffer + iconsHozSpace * 2;
    final static int arrowLocX = (int) (iconsLeftBuffer * .5);
    final static int upArrowLocY = iconsTopBuffer;
    final static int arrowWidth = 30;
    final static int arrowSelectedWidth = 40;
    final static int arrowDiff = (int) (arrowSelectedWidth - arrowWidth) / 2;
    final static int downArrowLocY = (int) (iconsTopBuffer + iconsVerSpace * 2 - arrowWidth * 2.5);
    final static int trashWidth = 30;
    final static int trashSelectedWidth = 40;
    final static int trashDiff = (int) (trashSelectedWidth - trashWidth) / 2;
    final static int trashLocX = iconsLeftBuffer + 5 * iconsHozSpace - 2 * trashWidth;
    final static int trashLocY = iconsTopBuffer - 2 * trashWidth;

    final Dimension defaultDim = new Dimension(iconsHozSpace, iconsVerSpace);
    public static final int iconCols = 5;
    public static final int iconRows = 2;
    public static final int numOfVisibleIcons = iconCols * iconRows;

    public SmartLabel(File file, Point colRow, boolean isImage) { //icon labels
        super();
        linkedFile = file;
        loc = getLoc(colRow);

        setFont(smallFont);
        setIcon(getImageIcon());
        setBounds(loc.x + xLocBuff, loc.y + yLocBuff, iconWidth + xBoundBuff, iconHeight + yBoundBuff);
        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);

        setText(FileManager.removeExt(linkedFile.getName()));

        //setSize(getPreferredSize());
    }

    public SmartLabel(File file, boolean hasTitle) { //Title
        super();
        setForeground(Color.BLUE);
        setFont(titleFont);
        if (!hasTitle) { //Databases
            setText("Databases");
        } else {
            linkedFile = file;
            setText(FileManager.removeExt(file.getName()));
        }
        loc = new Point(titleLocX, titleLocY);
        setBounds(loc.x, loc.y, defaultDim.width, defaultDim.height);
        setSize(getPreferredSize());
    }

    public SmartLabel(boolean isImage, File file) { //displayImage
        super();
        linkedFile = file;
        loc = new Point(iconsLeftBuffer, iconsTopBuffer);

        setFont(smallFont);
        Icon icon = FileManager.createOrgIconImage(linkedFile);

        setIcon(icon);
        setBounds(loc.x, loc.y, icon.getIconWidth(), icon.getIconHeight() + 2 * hBuff);
        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);
        String imgInfo = FileManager.getImgInfo(linkedFile);
        setText(imgInfo);
    }

    public SmartLabel(String returnTo) { //return Label
        super();
        setForeground(Color.GRAY);
        setFont(smallFont);
        setText(returnTo);
        loc = new Point(returnLocX, returnLocY);
        setBounds(loc.x, loc.y, defaultDim.width, defaultDim.height);
        setSize(getPreferredSize());
    }

    public SmartLabel(int labelNum) { //up, down, add
        super();
        this.labelNum = labelNum;
        if (labelNum == UP_LABEL) {
            createUpLabel();
        } else if (labelNum == DOWN_LABEL) {
            createDownLabel();
        } else if (labelNum == TRASH_LABEL) {
            createTrashLabel();
        } else if (labelNum == TRAIN_LABEL) {
            createClassifierLabel("TRAIN");
        } else if (labelNum == TEST_LABEL) {
            createClassifierLabel("TEST");
        } else if (labelNum == CLASSIFY_LABEL) {
            createClassifierLabel("CLASSIFY");
        }
    }

    void createUpLabel() {
        loc = new Point(arrowLocX, upArrowLocY);
        boolean displayUP = FileManager.displayUp();
        setVisible(displayUP);
       // System.out.println("Created up label with visibility: " + displayUP);
        Icon icon = FileManager.createFunctionIconImage(UP_LABEL, selected, isVisible());
        setIcon(icon);
        setBounds(loc.x, loc.y, icon.getIconWidth(), icon.getIconHeight());

    }

    private void createDownLabel() {
        loc = new Point(arrowLocX, downArrowLocY);
        Icon icon = FileManager.createFunctionIconImage(DOWN_LABEL, selected, isVisible());
        setIcon(icon);
        setBounds(loc.x, loc.y, icon.getIconWidth(), icon.getIconHeight());
        setVisible(FileManager.displayDown());
       // System.out.println("Created down label with visibility: " + FileManager.displayDown());
    }

    private void createTrashLabel() {
        loc = new Point(trashLocX, trashLocY);
        Icon icon = FileManager.createFunctionIconImage(TRASH_LABEL, selected, isVisible());
        setIcon(icon);
        setBounds(loc.x, loc.y, icon.getIconWidth(), icon.getIconHeight());
        setVisible(FileManager.level!=0);
    }

    public void updateText(String newText) {
        setText(newText);
        setSize(getPreferredSize());
        //changeFileName        
    }

    public void setText(String newText) {
        super.setText(newText.toUpperCase());
    }

    private Point getLoc(Point colRow) {
        Point location;
        int x = iconsLeftBuffer + iconsHozSpace * colRow.x;
        int y = iconsTopBuffer + iconsVerSpace * (colRow.y);
        location = new Point(x, y);
        return location;
    }

    static Point getPointIfMouseOnIconLabel(int mouseX, int mouseY, int numOfLabels) {
        Point labelPoint = null;
        int iconsRightBoundary = iconsLeftBuffer + iconsHozSpace * (iconCols + 1);
        int iconsBottomBoundary = iconsTopBuffer + iconsVerSpace * (iconRows + 1);
        if ((mouseX > iconsLeftBuffer) && (mouseX < iconsRightBoundary) && (mouseY > iconsTopBuffer) && (mouseY < iconsBottomBoundary)) {
            for (int labI = 0; labI < numOfLabels; labI++) {
                int col = labI % iconCols;
                int row = labI / iconCols;
                int leftBoundary = iconsLeftBuffer + iconsHozSpace * col;
                int rightBoundary = leftBoundary + iconsHozSpace;
                int topBoundary = iconsTopBuffer + iconsVerSpace * row;
                int bottomBoundary = topBoundary + iconsVerSpace;
                if ((mouseX > leftBoundary) && (mouseX < rightBoundary) && (mouseY > topBoundary) && (mouseY < bottomBoundary)) {
                    return new Point(leftBoundary, topBoundary);
                }
            }

        }
        return labelPoint;
    }

    static File getFileIfMouseOnIconLabel(int mouseX, int mouseY, ArrayList<SmartLabel> iconLabels) {
        File file = null;
        if (iconLabels == null) {
            return file;
        }
        int iconsRightBoundary = iconsLeftBuffer + iconsHozSpace * (iconCols + 1);
        int iconsBottomBoundary = iconsTopBuffer + iconsVerSpace * (iconRows + 1);
        if ((mouseX > iconsLeftBuffer) && (mouseX < iconsRightBoundary) && (mouseY > iconsTopBuffer) && (mouseY < iconsBottomBoundary)) {
            for (int labI = 0; labI < iconLabels.size(); labI++) {
                int col = labI % iconCols;
                int row = labI / iconCols;
                int leftBoundary = iconsLeftBuffer + iconsHozSpace * col;
                int rightBoundary = leftBoundary + iconsHozSpace;
                int topBoundary = iconsTopBuffer + iconsVerSpace * row;
                int bottomBoundary = topBoundary + iconsVerSpace;
                if ((mouseX > leftBoundary) && (mouseX < rightBoundary) && (mouseY > topBoundary) && (mouseY < bottomBoundary)) {
                    SmartLabel l = iconLabels.get(labI);
                    return l.getLinkedFile();
                }
            }

        }
        return file;
    }

    public static void initializeImageIcons() {

    }

    private ImageIcon getImageIcon() {
        ImageIcon icon = FileManager.createImageIcon(linkedFile);
//        Image image = icon.getImage(); // transform it 
//        Image newimg = image.getScaledInstance(iconWidth + wBuff, iconHeight + hBuff, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
//        icon = new ImageIcon(newimg);
        return icon;
    }

    public boolean mouseOnLabel(int mouseX, int mouseY) {
        int labelX = loc.x - mouseBuffer;
        int labelY = loc.y - mouseBuffer;
        Dimension size = getSize();
        int labelX2 = labelX + size.width + 2 * mouseBuffer;
        int labelY2 = labelY + size.height + 2 * mouseBuffer;
        return ((mouseX > labelX) && (mouseX < labelX2) && (mouseY
                > labelY) && (mouseY < labelY2));
    }

    public File getLinkedFile() {
        return linkedFile;
    }

    void setLinkedFile(File newDir) {
        linkedFile = newDir;
    }

    void setSelected(boolean b) {
        selected = b;
        if (!isVisible()) {
            return;
        }
        if (labelNum == UP_LABEL || labelNum == DOWN_LABEL) {
            ImageIcon icon = FileManager.createFunctionIconImage(labelNum, selected, isVisible());
            setIcon(icon);
            if (selected) {
                setBounds(loc.x - arrowDiff, loc.y - arrowDiff, icon.getIconWidth(), icon.getIconHeight());
            } else {
                setBounds(loc.x, loc.y, icon.getIconWidth(), icon.getIconHeight());
            }
        } 
        if (labelNum==TRASH_LABEL) {
            ImageIcon icon = FileManager.createFunctionIconImage(labelNum, selected, isVisible());
            setIcon(icon);
            if (selected) {
                setBounds(loc.x - trashDiff, loc.y - trashDiff, icon.getIconWidth(), icon.getIconHeight());
            } else {
                setBounds(loc.x, loc.y, icon.getIconWidth(), icon.getIconHeight());
            }
        }
        else if (labelNum>=TRAIN_LABEL&&labelNum<=CLASSIFY_LABEL) {
            if (selected) {
                setForeground(Color.yellow);
            } else {
                setForeground(Color.white);
            }
        }

    }

    private void createClassifierLabel(String text) {
        setForeground(Color.WHITE);
        setFont(mediumFont);
        setText(text);
        Dimension size = getPreferredSize();
        int shiftToCenter = (int) (iconWidth - size.width) / 2;
        int x = iconsLeftBuffer + (labelNum - 1) * iconsHozSpace + shiftToCenter;
        loc = new Point(x, classifierLocY);
        setBounds(loc.x, loc.y, size.width, size.height);

    }
}
