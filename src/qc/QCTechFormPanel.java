package qc;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class QCTechFormPanel extends JPanel {
	
	private ArrayList<String> productNameArray;
	private ArrayList<String> testNumbers;
	private QCTechFormListener listener;
	private int currentTestNumber;
	private String currentProduct;
	private String currentFilePath;
	
	private JLabel pendingTestsLabel;
	private JComboBox pendingTestsCombo;
	private DefaultComboBoxModel pendingComboModel;
	
	private JLabel resultsLabel;
	private JTextArea resultsText;
	
	private JLabel passFailLabel;
	private JComboBox passFailCombo;
	private DefaultComboBoxModel passFailModel;
	
	private JButton submitButton;
	
	public QCTechFormPanel() {

		pendingTestsLabel = new JLabel("Pending Tests");
		pendingComboModel = new DefaultComboBoxModel();
		pendingTestsCombo = new JComboBox();
		pendingTestsCombo.setModel(pendingComboModel);
		
		resultsLabel = new JLabel("Result");
		resultsText = new JTextArea(15, 15);
		resultsText.setLineWrap(true);
		resultsText.setWrapStyleWord(true);
		//resultsText.setBorder();
		
		passFailLabel = new JLabel("Pass/Fail");
		passFailModel = new DefaultComboBoxModel();
		passFailCombo = new JComboBox(passFailModel);
		
		passFailModel.addElement("Pass");
		passFailModel.addElement("Fail");
		
		submitButton = new JButton("Submit");
		
		pendingTestsCombo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String product = (String)pendingTestsCombo.getSelectedItem();
				if(product == null) {
					return;
				}
				// Convert the String in the test number String array to an int
				setCurrentTestNumber(testNumbers.get(pendingTestsCombo.getSelectedIndex()));
				setCurrentProduct(productNameArray.get(pendingTestsCombo.getSelectedIndex()));
				QCTechFormEvent ev = new QCTechFormEvent(this, currentTestNumber);
				if(listener != null) {
					listener.getCurrentTestPath(getCurrentTestNumber());
					listener.setTestProcedure(ev);
				}
				
			}});
		
		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String results = resultsText.getText();
				String passOrFail = (String)passFailCombo.getSelectedItem();
				String currentFilePath = getCurrentFilePath();
				ResultsEvent rev = new ResultsEvent(this, results, passOrFail, currentFilePath, currentProduct, currentTestNumber);
				if (listener != null) {
					listener.saveTestResults(rev);
				}
				//pendingComboModel.removeElement(pendingTestsCombo.getSelectedItem());
			}
		});
		
		Border innerBorder = BorderFactory.createTitledBorder("Quantity Calculator");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 0, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		Dimension dimension = getPreferredSize();
		dimension.width = 250;
		setPreferredSize(dimension);
		layoutComponents();
	}
	
	private void layoutComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		////// First Row ///////
		
		gc.gridy = 0;
		gc.weightx = 0;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(pendingTestsLabel, gc);
		
		///////// Next Row /////////
		
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 40;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(pendingTestsCombo, gc);
		
		///////// Next Row /////////
		gc.gridy++;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(resultsLabel, gc);
		
		///////// Next Row /////////
		
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 40;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(new JScrollPane(resultsText), gc);
		
		///////// Next Row /////////
		gc.gridy++;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(passFailLabel, gc);
		
		///////// Next Row /////////
		
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 40;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(passFailCombo, gc);
		
		///////// Next Row /////////
		gc.gridy++;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(submitButton, gc);
	}
	
	public void populatePendingTests(ArrayList<ArrayList<String>> pendingTestsData) {
		pendingComboModel.removeAllElements();
		if(!pendingTestsData.isEmpty()) {
			if(!pendingTestsData.get(0).isEmpty() && 
					!pendingTestsData.get(1).isEmpty()) {
				testNumbers = pendingTestsData.get(0);
				productNameArray = pendingTestsData.get(1);
				
				for(int i = 0; i < testNumbers.size(); i++) {
					String testNumber = "QC" + testNumbers.get(i);
					String product = productNameArray.get(i);
					
					pendingComboModel.addElement(testNumber + " - " + product);
				}
			}
		}

		

		//pendingTestsCombo.setSelectedIndex(0);
	}
	
	public void setCurrentTestNumber(String testNumber) {
		int intTestNumber = Integer.parseInt(testNumber);
		this.currentTestNumber = intTestNumber;
	}
	
	public int getCurrentTestNumber() {
		return currentTestNumber;
	}
	
	public void setCurrentFilePath(String path) {
		this.currentFilePath = path;
	}
	
	public String getCurrentFilePath() {
		return currentFilePath;
	}
	
	public void setCurrentProduct(String product) {
		this.currentProduct = product;
	}
	
	public void clearResultsText() {
		resultsText.setText(null);
	}
	
	public void setListener(QCTechFormListener listener) {
		this.listener = listener;
	}
	
}
