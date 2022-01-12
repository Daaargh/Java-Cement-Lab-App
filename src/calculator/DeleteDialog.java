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
import javax.swing.JPanel;
import javax.swing.border.Border;

public class DeleteDialog extends JDialog {
	
	private DeleteDialogListener dialogListener;
	private JLabel componentLabel;
	private JComboBox componentCombo;
	private JLabel nameLabel;
	private JComboBox nameComboBox;
	
	private JButton deleteButton;
	private JButton cancelButton;
	
	private DefaultComboBoxModel componentComboModel;
	private DefaultComboBoxModel nameComboModel;
	
	public DeleteDialog(JFrame parent) {
		super(parent, "Delete Additives", true);
		
		componentLabel = new JLabel("Type");
		componentCombo = new JComboBox();
		
		nameLabel = new JLabel("Name: ");
		nameComboBox = new JComboBox();
		
		deleteButton = new JButton("Delete");
		cancelButton = new JButton("Cancel");
		
		//Set up component type box
		componentComboModel = new DefaultComboBoxModel();
		componentComboModel.addElement("Additive");
		componentComboModel.addElement("Cement");
		componentComboModel.addElement("Water");
		componentCombo.setModel(componentComboModel);
		componentCombo.setSelectedIndex(0);
		
		//Set up name combo box
		nameComboModel = new DefaultComboBoxModel();
		nameComboBox.setModel(nameComboModel);
		
		layoutComponents();
		
		componentCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String componentType = (String)componentCombo.getSelectedItem();
				
				if(dialogListener != null) {
					dialogListener.populateNameBox(componentType);
				}
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String componentType = (String)componentCombo.getSelectedItem();
				String componentName = (String)nameComboBox.getSelectedItem();
				DialogEvent evDialog = new DialogEvent(this, componentName);
				
				if(dialogListener != null) {
					if(componentType.equalsIgnoreCase("additive")) {
						dialogListener.deleteAdditive(evDialog);
						dialogListener.populateNameBox(componentType);
					}
					else if(componentType.equalsIgnoreCase("cement")) {
						dialogListener.deleteCementType(evDialog);
						dialogListener.populateNameBox(componentType);
					}
					else if(componentType.equalsIgnoreCase("water")) {
						dialogListener.deleteWaterType(evDialog);
						dialogListener.populateNameBox(componentType);
					}
				}

			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					setVisible(false);
			}
		});
		
		setSize(250, 200);
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
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		detailsPanel.add(componentLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = rightPadding;
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
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = rightPadding;
		detailsPanel.add(nameComboBox, gc);
		
		
		/////// Button Panel ///////
		
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonsPanel.add(deleteButton);
		buttonsPanel.add(cancelButton);
		
		Dimension buttonSize = cancelButton.getPreferredSize();
		deleteButton.setPreferredSize(buttonSize);
		
		setLayout(new BorderLayout());
		add(detailsPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
		
	} 
	
	public void setDialogListener(DeleteDialogListener listener) {
		this.dialogListener = listener;
	}
	
	public void populateNameBox(ArrayList<String> array) {
		nameComboModel.removeAllElements();
		for(String name : array) {
			nameComboModel.addElement(new String(name));
		}
	}
	
	public String getComponent() {
		String component = (String)componentCombo.getSelectedItem();
		return component;
	}
}
