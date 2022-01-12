package qc;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class QCTemplateFormPanel extends JPanel {
	private JLabel nameLabel;
	private JTextField nameField;
	
	private JLabel componentLabel;
	private JComboBox componentCombo;
	private DefaultComboBoxModel comboModel;
	
	private JLabel amountLabel;
	private JTextField amountField;
	private JButton addComponentBtn;
	
	private JLabel testLabel;
	private JTextArea testText;
	
	private JButton createQCFormBtn;
	
	private QCTemplateFormListener listener;
	
	
	public QCTemplateFormPanel() {
		
		nameLabel = new JLabel("Product Name: ");
		nameField = new JTextField(10);
		
		componentLabel = new JLabel("Component: ");
		comboModel = new DefaultComboBoxModel();
		componentCombo = new JComboBox(comboModel);
		comboModel.addElement("Component");
		comboModel.addElement("Jasprex M");
		
		amountLabel = new JLabel("Amount: ");
		amountField = new JTextField(2);
		
		addComponentBtn = new JButton("Add");
		
		testLabel = new JLabel("Test Procedure: ");
		testText = new JTextArea(15, 15);
		testText.setLineWrap(true);
		testText.setWrapStyleWord(true);
		
		createQCFormBtn = new JButton("Create Template");
		
		Border innerBorder = BorderFactory.createTitledBorder("Quantity Calculator");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 0, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		layoutComponents();
		
		addComponentBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(componentCombo.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(QCTemplateFormPanel.this, "Please select a valid component!");
				}
				
				if(amountField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(QCTemplateFormPanel.this, "Please enter a valid amount!");
				}
				else if(componentCombo.getSelectedIndex() != 0 && !amountField.getText().isEmpty()){
					String componentName = (String)componentCombo.getSelectedItem();
					String amount = amountField.getText();
					QCTemplateFormEvent ev = new QCTemplateFormEvent(this, componentName, amount);
					if (listener != null) {
						listener.addTemplateTableRow(ev);
						}
					}

				
			}
		});
		
		createQCFormBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String productName = nameField.getText();
				String testProcedure = testText.getText();
				QCTemplateReportEvent ev = new QCTemplateReportEvent(this, productName, testProcedure);
				if (listener != null) {
					listener.generateQCTemplate(ev);
					}
			}
		});

	}
	
	private void layoutComponents() {
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		////////// First Row //////////
		
		/// Add the name label and text field
		gc.gridy = 0;
		
		gc.weightx = 4;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(nameLabel, gc);
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(nameField, gc);
		
		////////// Next Row //////////
		
		/// Add the component label and combo box
		gc.gridy++;
		
		gc.weightx = 4;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(componentLabel, gc);
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(componentCombo, gc);
		
		////////// Next Row //////////
		
		/// Add the amount label and text field
		
		gc.gridy++;
		
		gc.weightx = 4;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(amountLabel, gc);
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(amountField, gc);
		
		////////// Next Row //////////
		
		/// Add the component add button
		
		gc.gridy++;
		
		gc.weightx = 4;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(addComponentBtn, gc);
		
		////////// Next Row //////////
		
		/// Add the test label
		
		gc.gridy++;
		
		gc.weightx = 4;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(testLabel, gc);
		
		////////// Next Row //////////
		
		/// Add the test text area
		gc.gridy++;
		gc.gridx = 0;
		gc.gridwidth = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(new JScrollPane(testText), gc);
		
		////////// Next Row //////////
		
		/// Add the create QC form button
		
		gc.gridy++;
		
		gc.weightx = 4;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridwidth = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(createQCFormBtn, gc);
		
		Dimension dimension = getPreferredSize();
		dimension.width = 250;
		dimension.height = 400;
		setPreferredSize(dimension);
	}
	
	public void setQCComponents(ArrayList<String> qcProducts) {
		ArrayList<String> qcTestProducts = qcProducts;
		for(String product : qcTestProducts) {
			comboModel.addElement(product);
		}
	}
	
	public void setListener(QCTemplateFormListener listener) {
		this.listener = listener;
	}
	
}
