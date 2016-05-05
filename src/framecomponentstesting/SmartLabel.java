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
import java.util.ArrayList;
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

    final int smallFontSize = 16;
    final int titleFontSize = 48;
    final Font titleFont = new Font("Tw Cen MT Condensed", 1, titleFontSize);
    final Font smallFont = new Font("Tw Cen MT Condensed", 1, smallFontSize);

    //item is shown as selected if mouse is within this distance
    final int mouseBuffer = 10;



    public static int UP_LABEL = 0;
    public static int DOWN_LABEL = 1;
    public static int ADD_LABEL = 2;

    //icon label location variables
    public static final int iconsLeftBuffer = 150;
    public static final int iconsTopBuffer = 150;
    public static final int iconsHozSpace = 150;
    public static final int iconsVerSpace = 150;
    public static final int iconWidth = 100;
    public static final int iconHeight = 100;
    
    
    //magic numbers which results in perfect icon label allignment 
    public static int xBoundBuff = 10;
    public static int yBoundBuff = 27;
    public static int wBuff = 7;
    public static int hBuff = 11;
    public static int xLocBuff = -3;
    public static int yLocBuff = -3;
    
        //label locations
    final int titleLocX = 70;
    final int titleLocY = 70;
    final int returnLocX = 30;
    final int returnLocY = 30;
    final static int arrowLocX = (int) (iconsLeftBuffer*.5); 
    final static int upArrowLocY = iconsTopBuffer;
    final static int arrowWidth = 30;
    final static int downArrowLocY = (int) (iconsTopBuffer+iconsVerSpace*3-arrowWidth*2.5);
    final static int addWidth = 50;
    final static int addLocX = iconsLeftBuffer + 5*iconsHozSpace-2*addWidth;
    final static int addLocY = iconsTopBuffer-2*addWidth;

    final Dimension defaultDim = new Dimension(iconsHozSpace, iconsVerSpace);
    public static final int iconCols = 5;
    public static final int iconRows = 5;

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

        setText(FileManager.removeExt(linkedFile.getName()));
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
        if (labelNum == UP_LABEL) {
            createUpLabel();
        } else if (labelNum == DOWN_LABEL) {
            createDownLabel();
        } else if (labelNum == ADD_LABEL) {
            createAddLabel();
        }

    }

    void createUpLabel() {
        loc = new Point(arrowLocX, upArrowLocY);
        Icon icon = FileManager.createFunctionIconImage(UP_LABEL);
        setIcon(icon);
        setBounds(loc.x, loc.y, icon.getIconWidth(), icon.getIconHeight());
    }

    private void createDownLabel() {
        loc = new Point(arrowLocX, downArrowLocY);
        Icon icon = FileManager.createFunctionIconImage(DOWN_LABEL);
        setIcon(icon);
        setBounds(loc.x, loc.y, icon.getIconWidth(), icon.getIconHeight());
    }

    private void createAddLabel() {
        loc = new Point(addLocX, addLocY);
        Icon icon = FileManager.createFunctionIconImage(ADD_LABEL);
        setIcon(icon);
        setBounds(loc.x, loc.y, icon.getIconWidth(), icon.getIconHeight());
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
                int row = labI / iconRows;
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
                int row = labI / iconRows;
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

}
