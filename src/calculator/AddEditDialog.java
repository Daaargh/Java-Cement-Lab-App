package calculator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class AddEditDialog extends JDialog {
	
	private AddEditDialogListener dialogListener;
	
	private JLabel componentLabel;
	private JComboBox componentCombo;
	
	private JLabel nameLabel;
	private JLabel sgLabel;
	private JLabel additiveStateLabel;
	private JLabel additiveTypeLabel;
	
	private JTextField nameField;
	private JTextField sgField;
	
	private JLabel cementLbsLabel;
	private JTextField cementLbsField;
	
	private JComboBox additiveTypeBox;
	private JComboBox additiveStateBox;
	
	private JButton addEditButton;
	private JButton closeButton;
	
	private DefaultComboBoxModel componentComboModel;
	private DefaultComboBoxModel additiveTypeModel;
	private DefaultComboBoxModel additiveStateModel;
	
	public AddEditDialog(JFrame parent) {
		super(parent, "Edit Additives", true);
		componentLabel = new JLabel("Type: ");
		componentCombo = new JComboBox();
		
		nameLabel = new JLabel("Name: ");
		sgLabel = new JLabel("s.g: ");
		additiveTypeLabel = new JLabel("Type: ");
		additiveStateLabel = new JLabel("State: ");
		
		nameField = new JTextField(5);
		sgField = new JTextField(5);
		
		cementLbsLabel = new JLabel("lbs/sack");
		cementLbsField = new JTextField(5);
		
		additiveTypeBox = new JComboBox();
		additiveStateBox = new JComboBox();
		
		addEditButton = new JButton("Add/edit");
		closeButton = new JButton("Close");
		
		//Set up component type box
		componentComboModel = new DefaultComboBoxModel();
		componentComboModel.addElement("Additive");
		componentComboModel.addElement("Cement");
		componentComboModel.addElement("Water");
		componentCombo.setModel(componentComboModel);
		componentCombo.setSelectedIndex(0);
		
		// Set up additive type box
		additiveTypeModel = new DefaultComboBoxModel();
		additiveTypeBox.setModel(additiveTypeModel);
		
		//Set up additive state box
		additiveStateModel = new DefaultComboBoxModel();
		additiveStateBox.setModel(additiveStateModel);
		
		layoutComponents();
		defaultLayout();
		
		componentCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedComponent = (String)componentCombo.getSelectedItem();
				selectLayout(selectedComponent);
			}
		});
		
		addEditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String componentType = (String) componentCombo.getSelectedItem();
				
				if(componentType.equalsIgnoreCase("additive")) {
					try {
						String name = nameField.getText();
						double sg = Double.parseDouble(sgField.getText());
						int type = additiveTypeBox.getSelectedIndex();
						int state = additiveStateBox.getSelectedIndex();
						
						DialogEvent evDialog = new DialogEvent(this, name, sg, type, state);
						if (dialogListener != null) {
							dialogListener.saveAdditive(evDialog);
							}
						}
					catch(NumberFormatException err) {
						JOptionPane.showMessageDialog(AddEditDialog.this, "Please enter a valid number!");
					}
				}
				
				else if(componentType.equalsIgnoreCase("water")){
					try {
						String name = nameField.getText();
						double sg = Double.parseDouble(sgField.getText());
						
						DialogEvent evDialog = new DialogEvent(this, name, sg);
						
						if (dialogListener != null) {
							dialogListener.saveWaterType(evDialog);
							}
						}
					catch(NumberFormatException err) {
						JOptionPane.showMessageDialog(AddEditDialog.this, "Please enter a valid number!");
					}
				}
				
				else if(componentType.equalsIgnoreCase("cement")){
					try {
						String name = nameField.getText();
						double sg = Double.parseDouble(sgField.getText());
						double lbs = Double.parseDouble(cementLbsField.getText());
						
						DialogEvent evDialog = new DialogEvent(this, name, lbs, sg);
						
						if (dialogListener != null) {
							dialogListener.saveCementType(evDialog);
							}
						}
					catch(NumberFormatException err) {
						JOptionPane.showMessageDialog(AddEditDialog.this, "Please enter a valid number!");
					}
				}
			}
		});
		
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					setVisible(false);
			}
		});
		
		setSize(270, 270);
		setLocation(0, 0);
	}
	
	private void layoutComponents() {
		
		JPanel detailsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		int space = 15;
		Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
		Border titleBorder = BorderFactory.createTitledBorder("Database preferences");
		detailsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
		
		detailsPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridy = 0;
		
		Insets rightPadding = new Insets(0, 0, 0, 15);
		Insets noPadding = new Insets(0, 0, 0, 0);
		
		//////// First Row /////////
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		detailsPanel.add(componentLabel, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		detailsPanel.add(componentCombo, gc);
		
		//////// Next Row /////////
		
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		detailsPanel.add(nameLabel, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		detailsPanel.add(nameField, gc);
		
		/////// Next Row ///////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		detailsPanel.add(sgLabel, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		detailsPanel.add(sgField, gc);
		
		/////// Next Row ///////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		detailsPanel.add(cementLbsLabel, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		detailsPanel.add(cementLbsField, gc);
		
		
		/////// Next Row ///////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		detailsPanel.add(additiveTypeLabel, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		detailsPanel.add(additiveTypeBox, gc);
		
		/////// Next Row ///////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		detailsPanel.add(additiveStateLabel, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		detailsPanel.add(additiveStateBox, gc);
		
		/////// Button Panel ///////
		
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonsPanel.add(addEditButton);
		buttonsPanel.add(closeButton);
		
		Dimension buttonSize = addEditButton.getPreferredSize();
		closeButton.setPreferredSize(buttonSize);
		
		setLayout(new BorderLayout());
		add(detailsPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
		
	}
	
	public void setDialogListener(AddEditDialogListener listener) {
		this.dialogListener = listener;
	}
	
	public void additiveTypeBox(ArrayList<String> array) {
	     for(String additiveType : array) { 		      
	    	 additiveTypeModel.addElement(new String(additiveType));
	     }
	}
	
	public void populateStateBox(ArrayList<String> array) {
		for(String additive : array) {
			additiveStateModel.addElement(new String(additive));
		}
	}
	
	public void defaultLayout() {
		selectLayout("additive");
	}
	
	public void selectLayout(String component) {
		if(component.equalsIgnoreCase("additive")) {
			additiveTypeLabel.setVisible(true);
			additiveTypeBox.setVisible(true);
			additiveStateBox.setVisible(true);
			additiveStateLabel.setVisible(true);
			cementLbsLabel.setVisible(false);
			cementLbsField.setVisible(false);

		}
		else if(component.equalsIgnoreCase("cement")){

			cementLbsLabel.setVisible(true);
			cementLbsField.setVisible(true);
			additiveTypeLabel.setVisible(false);
			additiveTypeBox.setVisible(false);
			additiveStateBox.setVisible(false);
			additiveStateLabel.setVisible(false);

		}
		else if(component.equalsIgnoreCase("water")){

			cementLbsLabel.setVisible(false);
			cementLbsField.setVisible(false);
			additiveTypeLabel.setVisible(false);
			additiveTypeBox.setVisible(false);
			additiveStateBox.setVisible(false);
			additiveStateLabel.setVisible(false);
		}
	}
}