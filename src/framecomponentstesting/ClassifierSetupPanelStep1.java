/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framecomponentstesting;

import static framecomponentstesting.ClassifierSetupDialog.FOOTNOTE_LABEL;
import static framecomponentstesting.ClassifierSetupDialog.REGULAR_LABEL;
import static framecomponentstesting.ClassifierSetupDialog.STEP_TITLE_LABEL;
import static framecomponentstesting.ClassifierSetupDialog.SUB_TITLE_LABEL;
import static framecomponentstesting.ClassifierSetupDialog.TITLE_LABEL;
import static framecomponentstesting.ClassifierSetupDialog.blueBox1Y;
import static framecomponentstesting.ClassifierSetupDialog.blueBoxHeight;
import static framecomponentstesting.ClassifierSetupDialog.col2Buffer;
import static framecomponentstesting.ClassifierSetupDialog.confirmButtonsH;
import static framecomponentstesting.ClassifierSetupDialog.confirmButtonsW;
import static framecomponentstesting.ClassifierSetupDialog.fieldBuffer;
import static framecomponentstesting.ClassifierSetupDialog.footnoteBuffer;
import java.awt.Dimension;
import static framecomponentstesting.ClassifierSetupDialog.hugeFont;
import static framecomponentstesting.ClassifierSetupDialog.titleFont;
import static framecomponentstesting.ClassifierSetupDialog.subTitleFont;
import static framecomponentstesting.ClassifierSetupDialog.regularFont;
import static framecomponentstesting.ClassifierSetupDialog.footnoteFont;
import static framecomponentstesting.ClassifierSetupDialog.hozSpace;
import static framecomponentstesting.ClassifierSetupDialog.leftBuffer;
import static framecomponentstesting.ClassifierSetupDialog.smallBuffer;
import static framecomponentstesting.ClassifierSetupDialog.stepTitleLocY;
import static framecomponentstesting.ClassifierSetupDialog.subTitleBuffer;
import static framecomponentstesting.ClassifierSetupDialog.tfRegularHeight;
import static framecomponentstesting.ClassifierSetupDialog.tfRegularWidth;
import static framecomponentstesting.ClassifierSetupDialog.title2LocY;
import static framecomponentstesting.ClassifierSetupDialog.titleBoxBuffer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Alisa
 */
public class ClassifierSetupPanelStep1 extends javax.swing.JPanel {

    Dimension panelSize = ClassifierSetupDialog.panelSize;
    ClassifierSetupDialog dialog;

    final int sectionVerSpace = 180;
    final int imageSpecsTitleLocY = title2LocY + sectionVerSpace;
    final int terminalTitleLocY = imageSpecsTitleLocY + sectionVerSpace;

    JTextField nameTF = new JTextField("");
    JTextField terminalCommandTF = new JTextField("");
    String[] acceptedImageFormats = {"(None)", "jpg", "png", "tiff"};
    JComboBox requiredFormatCB = new JComboBox(acceptedImageFormats);
    JTextField widthTF = new JTextField("");
    JTextField heightTF = new JTextField("");
    JTextField parserPathTF = new JTextField("");

    JPanel me;

    /**
     * Creates new form ClassifierSetupPanelStep1
     */
    public ClassifierSetupPanelStep1(ClassifierSetupDialog d) {
        initComponents();
        me = this;
        dialog = d;
        fillData();
        setBackground(Color.white);
        setPreferredSize(panelSize);
        createComponents();
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
        Painter.paintBlueClassifierRect(g, blueBox1Y);
        Painter.paintBlueClassifierRect(g, imageSpecsTitleLocY);
        Painter.paintBlueClassifierRect(g, terminalTitleLocY);
        Painter.paintBlueClassifierBottomRect(g);
    }

    private void createComponents() {
        removeAll();
        createStepTitle();

        createNameAccessTitle();
        createNameAccessSubTitles();
        createNameAccessFields();
        createNameAccessFootnotes();

        createImageSpecsTitle();
        createImageSpecsSubTitles();
        createImageSpecsFields();
        createImageSpecsFootnotes();

        createTerminalOutputTitle();
        createTerminalOutputSubTitles();
        createTerminalFields();
        createTerminalFootnotes();

        createConfirmButtons();
        repaint();
    }

    private void createStepTitle() {
        JLabel titleLabel = new JLabel("STEP 1 OF 3: BASIC INFO");
        titleLabel.setBounds(leftBuffer, stepTitleLocY, 200, 20);
        add(formatLabel(titleLabel, STEP_TITLE_LABEL));
    }

    private void createNameAccessTitle() {
        JLabel titleLabel = new JLabel("NAME AND ACCESS");
        titleLabel.setBounds(leftBuffer, title2LocY + titleBoxBuffer, 200, 20);
        add(formatLabel(titleLabel, TITLE_LABEL));
    }

    private void createImageSpecsTitle() {
        JLabel titleLabel = new JLabel("IMAGE SPECIFICATIONS");
        titleLabel.setBounds(leftBuffer, imageSpecsTitleLocY + titleBoxBuffer, 200, 20);
        add(formatLabel(titleLabel, TITLE_LABEL));
    }

    private void createTerminalOutputTitle() {
        JLabel titleLabel = new JLabel("TERMINAL OUTPUT PARSER");
        titleLabel.setBounds(leftBuffer, terminalTitleLocY + titleBoxBuffer, 200, 20);
        add(formatLabel(titleLabel, TITLE_LABEL));
    }

    private void createNameAccessSubTitles() {
        int y = title2LocY + subTitleBuffer;
        JLabel nameFormatLabel = new JLabel("CLASSIFIER NAME:");
        nameFormatLabel.setBounds(leftBuffer, y, 200, 20);
        add(formatLabel(nameFormatLabel, SUB_TITLE_LABEL));

        JLabel imageSizeLabel = new JLabel("TERMINAL COMMAND: ");
        imageSizeLabel.setBounds(col2Buffer, y, 200, 20);
        add(formatLabel(imageSizeLabel, SUB_TITLE_LABEL));
    }

    private void createImageSpecsSubTitles() {
        int y = imageSpecsTitleLocY + subTitleBuffer;
        JLabel imageFormatLabel = new JLabel("REQUIRED FORMAT:");
        imageFormatLabel.setBounds(leftBuffer, y, 200, 20);
        add(formatLabel(imageFormatLabel, SUB_TITLE_LABEL));

        JLabel imageSizeLabel = new JLabel("SHRINK TO SIZE:");
        imageSizeLabel.setBounds(col2Buffer, y, 200, 20);
        add(formatLabel(imageSizeLabel, SUB_TITLE_LABEL));

    }

    private void createTerminalOutputSubTitles() {
        int y = terminalTitleLocY + subTitleBuffer;
        JLabel terminalLabel = new JLabel("LOCATION OF PARSER FILE: ");
        terminalLabel.setBounds(leftBuffer, y, 200, 20);
        add(formatLabel(terminalLabel, SUB_TITLE_LABEL));
    }

    private void createNameAccessFields() {
        int y = title2LocY + fieldBuffer;
        nameTF.setBounds(leftBuffer, y, tfRegularWidth, tfRegularHeight);
        add(nameTF);

        terminalCommandTF.setBounds(col2Buffer, y, tfRegularWidth, tfRegularHeight);
        add(terminalCommandTF);
    }

    private void createImageSpecsFields() {
        int y = imageSpecsTitleLocY + fieldBuffer;

        JLabel requiredFormatLabel = new JLabel("CHOOSE ONE:");
        requiredFormatLabel.setBounds(leftBuffer, y, 200, 20);
        add(formatLabel(requiredFormatLabel, REGULAR_LABEL));

        requiredFormatCB.setBounds(leftBuffer + requiredFormatLabel.getWidth() + smallBuffer, y, 100, tfRegularHeight);
        add(requiredFormatCB);

        JLabel widthLabel = new JLabel("WIDTH: ");
        widthLabel.setBounds(col2Buffer, y, 100, 100);
        add(formatLabel(widthLabel, REGULAR_LABEL));

        widthTF.setBounds(col2Buffer + widthLabel.getWidth() + smallBuffer, y, tfRegularWidth / 2, tfRegularHeight);
        add(widthTF);

        JLabel heightLabel = new JLabel("HEIGHT: ");
        heightLabel.setBounds(col2Buffer + hozSpace / 2, y, 100, 100);
        add(formatLabel(heightLabel, REGULAR_LABEL));

        heightTF.setBounds(col2Buffer + hozSpace / 2 + widthLabel.getWidth() + smallBuffer, y, tfRegularWidth / 2, tfRegularHeight);
        add(heightTF);

    }
    Action parserPathButtonAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            parserPathTF.setText(promptUserForParserFile());
        }
    };

    private void createTerminalFields() {
        int y = terminalTitleLocY + fieldBuffer;

        JLabel pathLabel = new JLabel("PATH:");
        pathLabel.setBounds(leftBuffer, y, 200, 20);
        add(formatLabel(pathLabel, REGULAR_LABEL));

        parserPathTF.setBounds(leftBuffer + pathLabel.getWidth() + smallBuffer,
                y, tfRegularWidth * 2, tfRegularHeight);
        add(parserPathTF);

        JButton parserButton = new JButton("Browse");
        parserButton.setBounds(parserPathTF.getLocation().x
                + parserPathTF.getWidth() + smallBuffer, y, confirmButtonsW,
                confirmButtonsH);
        parserButton.addActionListener(parserPathButtonAction);
        add(parserButton);
    }

    private void createNameAccessFootnotes() {
        int y = title2LocY + footnoteBuffer;
        JLabel nameFootnoteLabel = new JLabel("For storage and display");
        nameFootnoteLabel.setBounds(leftBuffer, y, 200, 20);
        add(formatLabel(nameFootnoteLabel, FOOTNOTE_LABEL));

        JLabel commandFootnoteLabel = new JLabel("EX: \"classifier1\" (if installed), \"path/to/.exe\"");
        commandFootnoteLabel.setBounds(col2Buffer, y, 200, 20);
        add(formatLabel(commandFootnoteLabel, FOOTNOTE_LABEL));
    }

    private void createImageSpecsFootnotes() {
        int y = imageSpecsTitleLocY + footnoteBuffer;
        JLabel formatFootnoteLabel = new JLabel("Original images will be converted"
                + " to this format, if specified");
        formatFootnoteLabel.setBounds(leftBuffer, y, 200, 20);
        add(formatLabel(formatFootnoteLabel, FOOTNOTE_LABEL));

        JLabel sizeFootnoteLabel = new JLabel("(In Pixels) Aspect ratio is preserved");
        sizeFootnoteLabel.setBounds(col2Buffer, y, 200, 20);
        add(formatLabel(sizeFootnoteLabel, FOOTNOTE_LABEL));
    }

    private void createTerminalFootnotes() {
        int y = terminalTitleLocY + footnoteBuffer;
        JLabel parserFootnoteLabel = new JLabel("This class parses the terminal "
                + "output results of classification commands");
        parserFootnoteLabel.setBounds(leftBuffer, y, 200, 20);
        add(formatLabel(parserFootnoteLabel, FOOTNOTE_LABEL));
    }

    String promptUserForParserFile() {
        String filePath = "";
        JFileChooser chooser = new JFileChooser();
        //FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "jpeg", "png", "tiff");
        chooser.setCurrentDirectory(FileManager.lastOpenedDir);
        chooser.setDialogTitle("Select a File to Import");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(true);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            filePath = chooser.getSelectedFile().getPath();
        } else {
        }
        return filePath;
    }

    JLabel formatLabel(JLabel label, int labelType) {
        return ClassifierSetupDialog.formatLabel(label, labelType);
    }

    Action cancelAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Cancel!");
            dialog.cancel();
        }
    };
    Action nextAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("next!");
            dialog.recordData(combineData());
            dialog.goToPage(me, 2);
        }
    };

    private void createConfirmButtons() {
        JButton cancelButton = new JButton("CANCEL");
        cancelButton.setForeground(Color.black);
        cancelButton.setBounds(ClassifierSetupDialog.confirmButton1X,
                ClassifierSetupDialog.confirmButtonsY,
                ClassifierSetupDialog.confirmButtonsW,
                ClassifierSetupDialog.confirmButtonsH);
        cancelButton.setFont(regularFont);
        cancelButton.addActionListener(cancelAction);
        add(cancelButton);
        JButton nextButton = new JButton("NEXT");
        nextButton.setForeground(Color.green);
        nextButton.setBounds(ClassifierSetupDialog.confirmButton2X,
                ClassifierSetupDialog.confirmButtonsY,
                ClassifierSetupDialog.confirmButtonsW,
                ClassifierSetupDialog.confirmButtonsH);
        nextButton.setFont(regularFont);
        nextButton.addActionListener(nextAction);
        add(nextButton);

    }

    String combineData() {
        String combinedData = "";
        combinedData += ClassifierManager.markName(nameTF.getText());
        combinedData += ClassifierManager.markImgFormat("" + requiredFormatCB.getSelectedItem());
//        if (!widthTF.getText().equals("")&&!heightTF.getText().equals("")) {
        Dimension size = null;
        try {
            int w = Integer.parseInt(widthTF.getText());
            int h = Integer.parseInt(heightTF.getText());
            size = new Dimension(w,h);
        } catch (NumberFormatException | NullPointerException e) {
        }
        combinedData += ClassifierManager.markImgSize(size);
        combinedData += ClassifierManager.markParserPath(parserPathTF.getText());
        combinedData += ClassifierManager.markClassifierCmd(terminalCommandTF.getText());
        return combinedData;
    }

    private void fillData() {
        String data = dialog.dataToString();
        nameTF.setText(ClassifierManager.unmarkName(data));
        requiredFormatCB.setSelectedItem("" + ClassifierManager.unmarkImgFormat(data));
        Dimension size = ClassifierManager.unmarkImgSize(data);
        if (size != null) {
            widthTF.setText("" + size.width);
            heightTF.setText("" + size.height);
        }
        parserPathTF.setText(ClassifierManager.unmarkParserPath(data));
        terminalCommandTF.setText(ClassifierManager.unmarkClassifierCmd(data));

    }

}