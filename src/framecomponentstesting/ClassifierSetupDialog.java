/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framecomponentstesting;

import static framecomponentstesting.SmartLabel.titleFontSize;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alisa
 */
public class ClassifierSetupDialog extends javax.swing.JDialog {

    ClassifierSetupPanelStep1 csPanelStep1;
    public static Dimension panelSize = new Dimension(800, 660);

    public static final Font hugeFont = SmartLabel.titleFont;
    public static final Font titleFont = SmartLabel.mediumLargeFont;
    public static final Font subTitleFont = SmartLabel.mediumFont;
    public static final Font regularFont = SmartLabel.smallFont;
    public static final Font footnoteFont = SmartLabel.tinyFont;

    public static final int leftBuffer = 50;
    public static final int hozSpace = 360;
    public static final int col2Buffer = leftBuffer + hozSpace;

    public static final int stepTitleLocY = 20;
    public static final int blueBox1Y = 70;
    public static final int blueBoxHeight = 50;
    public static final int titleBoxBuffer = 7;
    public static final int topTitleLocY = titleBoxBuffer;
    public static final int smallBuffer = 10;
    public static final int title2LocY = blueBox1Y;
    public static final int subTitleBuffer = blueBoxHeight + 20;
    public static final int fieldBuffer = subTitleBuffer + 30;
    public static final int footnoteBuffer = fieldBuffer + 30;
    public static final int tfRegularWidth = 150;
    public static final int tfRegularHeight = 20;
    public static final int bottomRectY1 = panelSize.height - blueBoxHeight;

    public static final int confirmButtonsY = 620;
    public static final int confirmButtonsW = 100;
    public static final int confirmButtonsH = 20;
    public static final int confirmButton0X = 440;
    public static final int confirmButton1X = 550;
    public static final int confirmButton2X = 660;

   

    public static final int TITLE_LABEL = 0;
    public static final int SUB_TITLE_LABEL = 1;
    public static final int REGULAR_LABEL = 2;
    public static final int FOOTNOTE_LABEL = 3;
    public static final int STEP_TITLE_LABEL = 4;
    public static final int REGULAR_BLUE_LABEL = 5;
    public static final int WHITE_TITLE_LABEL=6;
    public static final int REGULAR_RED_LABEL=7;
    public static final String TRAIN = "TRAIN";
    public static final String TEST = "TEST";
    public static final String CLASSIFY = "CLASSIFY";

    public static final int TRAIN_INT = 2;
    public static final int TEST_INT = 3;
    public static final int CLASSIFY_INT = 4;

    String classifierName = "";
    String imageFormat = "";
    Dimension imageSize = null;
    String classifierCommand = "";
    String pathToParser = "";
    String trainCommand = "";
    ArrayList<String> trainCommandOptions = new ArrayList<>();
    String testCommand = "";
    ArrayList<String> testCommandOptions = new ArrayList<>();
    String classifyCommand = "";
    ArrayList<String> classifyCommandOptions = new ArrayList<>();

   

    /**
     * Creates new form ClassifierSetupDialog
     */
    public ClassifierSetupDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Create a New Classifier");
        setLocation(100, 0);
        csPanelStep1 = new ClassifierSetupPanelStep1(this);
        getContentPane().add(csPanelStep1);
        setContentPane(csPanelStep1);
        pack();
        setVisible(true);

    }

    public ClassifierSetupDialog(java.awt.Frame parent, boolean modal, String classifierName) {
        super(parent, modal);
        initComponents();
        this.classifierName = classifierName;
        loadClassifierData();
        setTitle("Edit " + classifierName);
        setLocation(100, 0);
        csPanelStep1 = new ClassifierSetupPanelStep1(this);
        getContentPane().add(csPanelStep1);
        setContentPane(csPanelStep1);
        pack();
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClassifierSetupDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClassifierSetupDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClassifierSetupDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClassifierSetupDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ClassifierSetupDialog dialog = new ClassifierSetupDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    public void goToPage(JPanel previousPanel, int page) {
        getContentPane().remove(previousPanel);
        JPanel newCsPanel = null;
        switch (page) {
            case 1: {
                newCsPanel = new ClassifierSetupPanelStep1(this);
                break;
            }
            default:
                newCsPanel = new ClassifierSetupPanelStep2(this, page);

        }
        getContentPane().add(newCsPanel);
        setContentPane(newCsPanel);
        pack();
        repaint();
    }

    public static JLabel formatLabel(JLabel label, int labelType) {
        switch (labelType) {
            case TITLE_LABEL: {
                label.setForeground(Color.white);
                label.setFont(titleFont);
                break;
            }
            case SUB_TITLE_LABEL: {
                label.setForeground(Color.blue);
                label.setFont(subTitleFont);
                break;
            }
            case REGULAR_LABEL: {
                label.setForeground(Color.black);
                label.setFont(regularFont);
                break;
            }
            case FOOTNOTE_LABEL: {
                label.setForeground(Color.black);
                label.setFont(footnoteFont);
                break;
            }
            case STEP_TITLE_LABEL: {
                label.setForeground(Color.red);
                label.setFont(titleFont);
                break;
            }
            case REGULAR_BLUE_LABEL: {
                label.setForeground(Color.blue);
                label.setFont(regularFont);
                break;
            }
            case WHITE_TITLE_LABEL: {
                label.setForeground(Color.white);
                label.setFont(titleFont);
                break;
            } case REGULAR_RED_LABEL: {
                label.setForeground(Color.red);
                label.setFont(regularFont);
                break;
            }
        }
        label.setSize(label.getPreferredSize());
        return label;
    }

    void recordData(String newData) {
        if (newData.contains(ClassifierManager.NAME_MARKER)) {
            classifierName = ClassifierManager.unmarkName(newData);
        }
        if (newData.contains(ClassifierManager.IMG_FORMAT_MARKER)) {
            imageFormat = ClassifierManager.unmarkImgFormat(newData);
        }
        if (newData.contains(ClassifierManager.IMG_SIZE_MARKER)) {
            imageSize = ClassifierManager.unmarkImgSize(newData);
        }
        if (newData.contains(ClassifierManager.CLASSIFIER_CMD_MARKER)) {
            classifierCommand = ClassifierManager.unmarkClassifierCmd(newData);
        }
        if (newData.contains(ClassifierManager.PARSER_PATH_MARKER)) {
            pathToParser = ClassifierManager.unmarkParserPath(newData);
        }
        if (newData.contains(ClassifierManager.TR_CMD_MARKER)) {
            trainCommand = ClassifierManager.unmarkTrainCmd(newData);
        }
        if (newData.contains(ClassifierManager.TR_CMD_OPT_MARKER)) {
            trainCommandOptions = ClassifierManager.unmarkTrainCmdOpt(newData);
        }
        if (newData.contains(ClassifierManager.TS_CMD_MARKER)) {
            testCommand = ClassifierManager.unmarkTestCmd(newData);
        }
        if (newData.contains(ClassifierManager.TS_CMD_OPT_MARKER)) {
            testCommandOptions = ClassifierManager.unmarkTestCmdOpt(newData);
        }
        if (newData.contains(ClassifierManager.CL_CMD_MARKER)) {
            classifyCommand = ClassifierManager.unmarkClassifyCmd(newData);
        }
        if (newData.contains(ClassifierManager.CL_CMD_OPT_MARKER)) {
            classifyCommandOptions = ClassifierManager.unmarkClassifyCmdOpt(newData);
        }
        System.out.println("" + dataToString());
    }

    public String dataToString() {
        String rm = "";
        rm += ClassifierManager.markName(classifierName);
        rm += ClassifierManager.markImgFormat(imageFormat);
        rm += ClassifierManager.markImgSize(imageSize);
        rm += ClassifierManager.markClassifierCmd(classifierCommand);
        rm += ClassifierManager.markParserPath(pathToParser);
        rm += ClassifierManager.markTrainCmd(trainCommand);
        rm += ClassifierManager.markTrainCmdOpt(trainCommandOptions);
        rm += ClassifierManager.markTestCmd(testCommand);
        rm += ClassifierManager.markTestCmdOpt(testCommandOptions);
        rm += ClassifierManager.markClassifyCmd(classifyCommand);
        rm += ClassifierManager.markClassifyCmdOpt(classifyCommandOptions);
        return rm;
    }

    void cancel() {
        dispose();
    }

    void finish() {
        System.out.println("" + dataToString());
        ClassifierManager.writeToClassifierFile(dataToString());
        dispose();
    }

    private void loadClassifierData() {
        String data = ClassifierManager.loadData(classifierName);
        recordData(data);
    }
}
