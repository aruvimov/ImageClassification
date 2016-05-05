/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framecomponentstesting;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

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
    public static final Color selectedLabelColor = Color.cyan;
    public static final int selectStrokeThickness = 8;
    public static final int underlineStrokeThickness = 5;

    public static int titleLetterWidth = 5; //font-based
    public static int titleHeight = 10; //font-based
    public static Color titleColor = Color.blue;

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
            int w = SmartLabel.iconWidth+selectStrokeThickness+2;
            int h = SmartLabel.iconHeight+selectStrokeThickness+2;
            int x = selectedLabelLoc.x-selectStrokeThickness/2;
            int y = selectedLabelLoc.y-selectStrokeThickness/2;
            Graphics2D panelG2 = (Graphics2D) panelG;
            panelG2.setStroke(new BasicStroke(selectStrokeThickness));
            panelG2.setColor(selectedLabelColor);
            panelG2.drawRect(x, y, w, h);

            
            
//panelG2.drawRoundRect(x, y, w, h, w/2, h/2);
        }
    }

}
