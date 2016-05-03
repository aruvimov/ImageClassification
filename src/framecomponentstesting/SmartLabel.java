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
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Alisa
 */
public class SmartLabel extends JLabel {

    File linkedFile;
    Point loc;

    final Font titleFont = new Font("Tw Cen MT Condensed", 1, 48);
    final Font smallFont = new Font("Tw Cen MT Condensed", 1, 18);

    final int titleLocX = 70;
    final int titleLocY = 70;
    final int returnLocX = 30;
    final int returnLocY = 30;
    public static final int iconsLeftBuffer = 200;
    public static final int iconsTopBuffer = 200;
    public static int iconsHozSpace = 150;
    public static int iconsVerSpace = 150;
    final Dimension defaultDim = new Dimension(iconsHozSpace, iconsVerSpace);
    public static final int iconCols = 5;
    public static final int iconRows = 5;

    public SmartLabel(File file, Point colRow, boolean isImage) {
        super();
        linkedFile = file;
        loc = getLoc(colRow);

        setFont(smallFont);
        setIcon(getImageIcon());
        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);
        setBounds(loc.x, loc.y, defaultDim.width, defaultDim.height);

        setText(file.getName());
        
        //setSize(getPreferredSize());
    }

    public SmartLabel(File file, boolean hasTitle) { //Title
        setForeground(Color.BLUE);
        setFont(titleFont);
        if (!hasTitle) { //Databases
            setText("Databases");
        } else {
            linkedFile = file;
            setText(file.getName());
        }
        loc = new Point(titleLocX, titleLocY);
        setBounds(loc.x, loc.y, defaultDim.width, defaultDim.height);
        setSize(getPreferredSize());
    }

    public SmartLabel(String returnTo) { //return Label
        setFont(smallFont);
        loc = new Point(returnLocX, returnLocY);
        setBounds(loc.x, loc.y, defaultDim.width, defaultDim.height);
        setSize(getPreferredSize());
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

    protected static ImageIcon createImageIcon(File file) {
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

    static String getTextIfMouseOnIconLabel(int mouseX, int mouseY, ArrayList<SmartLabel> iconLabels) {
        String labelText = null;
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
                    return l.getText();
                }
            }

        }
        return labelText;
    }

    private ImageIcon getImageIcon() {
        ImageIcon icon = createImageIcon(linkedFile);
        Image image = icon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        icon = new ImageIcon(newimg);
        return icon;
    }
}
