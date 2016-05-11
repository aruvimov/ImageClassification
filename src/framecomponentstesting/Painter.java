/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framecomponentstesting;

import static framecomponentstesting.ClassifierSetupDialog.blueBoxHeight;
import static framecomponentstesting.ClassifierSetupDialog.regularFont;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import static java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR;
import java.util.ArrayList;

/**
 *
 * @author Alisa
 */
public class Painter {

    //Title
    public static final int underlineSpace = -5; //pixels below label
    public static final Color underlineColor = Color.red;

    public static boolean underlineTitle = false;
    public static Point selectedLabelLoc = null;
    public static final Color selectedLabelColor = Color.yellow;
    //public static Color backgroundColor = new Color(102,153,255);
    public static Color backgroundColor = new Color(0, 102, 204);
    public static final int selectStrokeThickness = 13;
    public static final int underlineStrokeThickness = 5;
    public static final int classifierBorderStrokeThickness = 3;
    public static final int borderXBuffer = 5;
    public static final int borderYBuffer = 3;
    public static final int resultsVerLineStroke=3;

    public static int titleLetterWidth = 5; //font-based
    public static int titleHeight = 10; //font-based
    public static Color titleColor = Color.blue;

    public static int blueBoxY = (int) (SmartLabel.iconsTopBuffer + SmartLabel.iconsVerSpace * 2.2);

    public static void paintTitleUnderline(Graphics panelG, Point titleLabelLoc,
            Dimension titleLabelDim) {
        if (underlineTitle) {
            int w = titleLabelDim.width;
            int h = titleLabelDim.height;
            int x = titleLabelLoc.x;
            int y = titleLabelLoc.y;
            Graphics2D panelG2 = (Graphics2D) panelG;
            panelG2.setStroke(new BasicStroke(underlineStrokeThickness));
            panelG2.setColor(underlineColor);
            panelG2.drawLine(x, y + h + underlineSpace, x + w, y + h
                    + underlineSpace);

        }
    }

    static void setUnderlineTitle(Boolean b) {
        underlineTitle = b;
    }

    static void setSelectedLabel(Point p) {
        selectedLabelLoc = p;
    }

    static void paintLabelSelection(Graphics panelG) {
        if (selectedLabelLoc != null) {
            int w = SmartLabel.iconWidth + selectStrokeThickness * 2;
            int h = SmartLabel.iconHeight + selectStrokeThickness * 3 + 4;
            int x = selectedLabelLoc.x - selectStrokeThickness;
            int y = selectedLabelLoc.y - selectStrokeThickness;
            Graphics2D panelG2 = (Graphics2D) panelG;
            panelG2.setStroke(new BasicStroke(selectStrokeThickness));
            panelG2.setColor(selectedLabelColor);
            panelG2.fillRect(x, y, w, h);

//panelG2.drawRoundRect(x, y, w, h, w/2, h/2);
        }
    }

    static void paintBlueRect(Graphics panelG) {
        if (FileManager.displayImage) {
            return;
        }
        int w = 2000; //larger than screen
        int h = 2000;//larger than screen
        int x = 0;
        int y = blueBoxY;
        Graphics2D panelG2 = (Graphics2D) panelG;
        // panelG2.setStroke(new BasicStroke(selectStrokeThickness));
        panelG2.setColor(backgroundColor);
        panelG2.fillRect(x, y, w, h);
    }

    static void paintClassifierBorder(Graphics panelG, SmartLabel paintLabel) {
        if (paintLabel == null || FileManager.displayImage || FileManager.level == 0) {
            return;
        }
        int x = SmartLabel.iconsLeftBuffer + (paintLabel.labelNum - 1) * SmartLabel.iconsHozSpace;
        int y = paintLabel.getLocation().y - borderYBuffer;
        //System.out.println("Border Y:"+y);
        //System.out.println("YLocation: "+paintLabel.getLocation());
        int w = SmartLabel.iconWidth;
        int h = paintLabel.getPreferredSize().height + 2 * borderYBuffer;
        Graphics2D panelG2 = (Graphics2D) panelG;
        panelG2.setStroke(new BasicStroke(classifierBorderStrokeThickness));
        if (paintLabel.selected) {
            panelG2.setColor(Color.yellow);
        } else {
            panelG2.setColor(Color.white);
        }
        panelG2.drawRect(x, y, w, h);
        //panelG2.drawLine(0, y, 1000, y);
        //panelG2.drawLine(0, y+h, 1000, y+h);
    }

//    static void paintBlueClassifierTopRect(Graphics panelG) {
//        int w = ClassifierSetupDialog.panelSize.width; //larger than screen
//        int h = (ClassifierSetupDialog.instructionsY2 - ClassifierSetupDialog.instructionsY1);//larger than screen
//        int x = 0;
//        int y = ClassifierSetupDialog.instructionsY1;
//        Graphics2D panelG2 = (Graphics2D) panelG;
//        // panelG2.setStroke(new BasicStroke(selectStrokeThickness));
//        panelG2.setColor(backgroundColor);
//        panelG2.fillRect(x, y, w, h);
//    }
    static void paintBlueClassifierBottomRect(Graphics panelG) {
        int w = 2000; //larger than screen
        int h = 500;//larger than screen
        int x = 0;
        int y = ClassifierSetupDialog.bottomRectY1;
        Graphics2D panelG2 = (Graphics2D) panelG;
        // panelG2.setStroke(new BasicStroke(selectStrokeThickness));
        panelG2.setColor(backgroundColor);
        panelG2.fillRect(x, y, w, h);
    }

    static void paintBlueClassifierRect(Graphics panelG, int blueBox1Y) {
        int w = 2000; //larger than screen
        int h = blueBoxHeight;
        int x = 0;
        int y = blueBox1Y;
        Graphics2D panelG2 = (Graphics2D) panelG;
        // panelG2.setStroke(new BasicStroke(selectStrokeThickness));
        panelG2.setColor(backgroundColor);
        panelG2.fillRect(x, y, w, h);
    }

    static void paintBlueClassifierTopRect(Graphics panelG) {
        int w = 2000; //larger than screen
        int h = blueBoxHeight;
        int x = 0;
        int y = 0;
        Graphics2D panelG2 = (Graphics2D) panelG;
        // panelG2.setStroke(new BasicStroke(selectStrokeThickness));
        panelG2.setColor(backgroundColor);
        panelG2.fillRect(x, y, w, h);
    }

    static void paintRect(Graphics panelG, Color c, int x, int y, int w, int h) {
        Graphics2D panelG2 = (Graphics2D) panelG;
        // panelG2.setStroke(new BasicStroke(selectStrokeThickness));
        int imageFrameBuffer = ClassifyImageFrame.imageFrameBuffer;
        panelG2.setColor(c);
        panelG2.fillRect(x - imageFrameBuffer, y - imageFrameBuffer,
                w + imageFrameBuffer * 2, h + imageFrameBuffer * 2);
    }

    static void paintFormatted(Graphics panelG, String[] ss) {
        for (int i = 0; i < ss.length; i++) {
            panelG.drawString(ss[i], 50 + i * 20, 50 + i * 20);
        }
    }
//String returnMe = "";
//        String format = "%" + buffer + "s";
//        returnMe+= String.format(format+" %32\n", dirName, getHighest());
//        int newBuffer = buffer + 4;
//        if (children == null) {
//            return returnMe;
//        }
//        for (Result child : children) {
//            returnMe += child.toString(newBuffer);
//        }
//        return returnMe;
    static int leftBuffer = 15;
    static int topBuffer = 30;
    static int col0 = leftBuffer+220;
    static int colSpace = 150;
    static int hozSpace = 40;
    static int verSpace = 18;
    static int verLineBuffer=3;
    static int hozBuffer=20;
   
    static int currY=0;
    static void paintResult(Graphics g, Result result, int x) {
        currY=currY+verSpace;
        g.setColor(getResultColor(x));
        g.setFont(regularFont);
        g.drawString(result.getDirName().toUpperCase(), x, currY);
        double[] highestList = result.getHighestList();
        for (int i = 0; i < highestList.length; i++) {
//            int high = highestList[i];
            g.drawString("" + highestList[i], col0+colSpace*(i), currY);
        }
        
        g.drawLine(0, currY+verLineBuffer, 1000, currY+verLineBuffer);
        ArrayList<Result> children = result.getChildren();
        if (children != null) {
            for (int i = 0; i < children.size(); i++) {
                //y+=yBuffer*i;
                paintResult(g, children.get(i), x + hozSpace);
            }
        }
    }

    static void resetCurrY() {
        currY=topBuffer;
    }
    
    static Color getResultColor(int x) {
        Color c = Color.black;
        if (x==leftBuffer) {
            c = Color.orange;
        } if (x==leftBuffer+hozSpace) {
            c = Color.red;
        }
        return c;
    }

    static void paintVerticalLines(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, VALUE_INTERPOLATION_BILINEAR);
        g2.setStroke(new BasicStroke(resultsVerLineStroke));
        int colX = col0;
        g2.drawLine(colX-hozBuffer, topBuffer, colX-hozBuffer, 1000);
    }

}
