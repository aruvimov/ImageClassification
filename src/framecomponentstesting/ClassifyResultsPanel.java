/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framecomponentstesting;

import static framecomponentstesting.FileManager.isImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import javax.swing.Scrollable;

/**
 *
 * @author Alisa
 */
public class ClassifyResultsPanel extends javax.swing.JPanel implements Scrollable {

    public static Dimension panelSize = ClassifyResultsFrame.panelSize;
    String[] ss = {"hello", "bye", "see you"};
    Result result = null;
    static int dirListLength = 0;

    /**
     * Creates new form ClassifyResultsPanel
     */
    public ClassifyResultsPanel(Result result) {
        initComponents();
        setBackground(Color.white);
        setPreferredSize(panelSize);
        this.result=result;
        createComponents();
        
        //testResults();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
 @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (result != null) {
            Painter.resetCurrY();
            Painter.paintResult(g, result, Painter.leftBuffer);
            Painter.paintVerticalLines(g);
            //Painter.paintFormatted(g, ss);
        }

    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return new Dimension(1000, 6000);
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 50;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 50;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }

    private void createComponents() {
repaint();
    }

    Result getResults(File dir) {
        String dirName = dir.getName();
        if (isClass(dir)) {
            return new Result(null, dirName);
        }
        ArrayList<Result> children = new ArrayList<>();
        for (File next : dir.listFiles()) {
            if (next.isDirectory()) {
                children.add(getResults(next));
            }
        }
        return new Result(children, dirName);

    }

    private boolean isClass(File dir) {
        if (dir.list() == null) {
            System.out.println("");
        }
        for (String list : dir.list()) {
            if (isImage(list)) {
                return true;
            }
        }
        return false;
    }

    public void testResults() {
        File startDir = new File("src\\images\\art history");
        dirListLength = calculateDirLength(startDir);
        System.out.println("dirListLength = " + dirListLength);
        result = getResults(startDir);
        repaint();
        //System.out.println(""+res.toString(0));
    }

    private int calculateDirLength(File dir) {
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