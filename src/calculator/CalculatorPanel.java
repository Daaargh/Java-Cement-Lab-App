package calculator;

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
import javax.swing.JTextField;
import javax.swing.border.Border;

public class CalculatorPanel extends JPanel {

	private ArrayList<String> additiveTypes;
	private CalculatorPanelListener calcPanelListener;

	private JLabel densityLabel;
	private JTextField densityField;

	private JLabel cementLabel;
	private JComboBox cementCombo;
	private JButton cementAddButton;

	private JLabel additiveTypeLabel;
	private JComboBox additiveTypeCombo;

	private JLabel additiveNameLabel;
	private JComboBox additiveCombo;

	private JLabel quantityLabel;
	private JTextField quantityField;
	
	private JLabel waterTypeLabel;
	private JComboBox waterTypeCombo;
	
	private JButton addToSlurryBtn;
	private JButton calculateBtn;
	private DefaultComboBoxModel additiveTypeModel;
	private DefaultComboBoxModel additiveNameModel;
	private DefaultComboBoxModel cementSelectModel;
	private DefaultComboBoxModel waterSelectModel;
	
	double waterGals = 0;
	double waterLbs = 0;
	double totalSlurryGals;
	double totalSlurryLbs;
	
	private JButton createReportButton;

	public CalculatorPanel() {
		
		Dimension dimension = getPreferredSize();
		dimension.width = 250;
		dimension.height = 400;
		setPreferredSize(dimension);
		
		totalSlurryGals = 0;
		totalSlurryLbs = 0;
		
		densityLabel = new JLabel("Density: ");
		densityField = new JTextField(3);
		
		cementLabel = new JLabel("Cement: ");
		cementCombo = new JComboBox();
		cementAddButton = new JButton("Add");
		
		additiveTypeLabel = new JLabel("Additive type: ");
		additiveTypeLabel.setEnabled(true);
		additiveTypeCombo = new JComboBox();
		additiveTypeCombo.setEnabled(true);
		
		additiveNameLabel = new JLabel("Additive: ");
		additiveNameLabel.setEnabled(true);
		additiveCombo = new JComboBox();
		additiveCombo.setEnabled(true);
		
		quantityLabel = new JLabel("Quantity: ");
		quantityLabel.setEnabled(true);
		quantityField = new JTextField(3);
		quantityField.setEnabled(true);
		
		addToSlurryBtn = new JButton("Add");
		addToSlurryBtn.setEnabled(true);
		
		waterTypeLabel = new JLabel("Water Type: ");
		waterTypeCombo = new JComboBox();
		
		calculateBtn = new JButton("Calculate");
		
		createReportButton = new JButton("Create Report");
		
		//Set up cement combo box
		cementSelectModel = new DefaultComboBoxModel<String>();
		cementSelectModel.addElement("Cement type");
		cementCombo.setModel(cementSelectModel);
		cementCombo.setSelectedIndex(0);
		
		//Set up additive type combo box
		additiveTypeModel = new DefaultComboBoxModel();

		additiveTypeModel.addElement("Additive type");
		additiveTypeCombo.setModel(additiveTypeModel);
		additiveTypeCombo.setSelectedIndex(0);
		
		//Set up additive combo box
		additiveNameModel = new DefaultComboBoxModel();
		additiveNameModel.addElement("Additive");
		additiveCombo.setModel(additiveNameModel);
		additiveCombo.setSelectedIndex(0);
		
		//Set up water combo box
		waterSelectModel = new DefaultComboBoxModel();
		waterSelectModel.addElement("Water Type");
		waterTypeCombo.setModel(waterSelectModel);
		waterTypeCombo.setSelectedIndex(0);

		
		additiveTypeCombo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e ) {
				/*
				 * On selecting an additive type, the additives
				 * combo box is populated with all the additives
				 * of that type that are stored in the database.
				 */
				if(calcPanelListener != null) {
					calcPanelListener.populateAdditiveCombo(getAdditiveType());
				}
			}
		});
		
		cementAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e ) {
				
				String cement = getSelectedCement();
				String type = "cement";
				double lbsOfCement = 0;
				double galsOfCement = 0;
				
				if(calcPanelListener != null) {
					lbsOfCement = calcPanelListener.getCementLbs(cement);
					galsOfCement = calcPanelListener.getCementGals(cement);
				}

				totalSlurryLbs += lbsOfCement;
				totalSlurryGals += galsOfCement;
				
				FormEvent ev = new FormEvent(this, cement, lbsOfCement, galsOfCement,type);
				if (calcPanelListener != null) {
					calcPanelListener.insertCementRow(ev);
					calcPanelListener.setAdditiveCementLbs(lbsOfCement);
					}
				}
			});
		
		addToSlurryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String type = "additive";
				String additive = getSelectedAdditive();
				double additiveQuantity = 0;
				String state = null;
				double additiveDensity = 0;
				double lbsOfAdditive = 0;
				double galsOfAdditive = 0;
				double cementLbs = 0;
				String cement = getSelectedCement();
				
				try {
					additiveQuantity = getAdditiveQuantity();
					}
				catch(NumberFormatException err) {
					JOptionPane.showMessageDialog(CalculatorPanel.this, "Please enter a valid number!");
					return;
					}
					
				if(calcPanelListener != null) {
					cementLbs = calcPanelListener.getCementLbs(cement);
					state = calcPanelListener.getAdditiveState(additive);
					additiveDensity = calcPanelListener.getAdditiveDensity(additive);
					}
										
				if(state.equals("solid")) {
					lbsOfAdditive = (additiveQuantity * cementLbs) / 100;
					galsOfAdditive = lbsOfAdditive / additiveDensity;						
					}
					
				else if(state.equals("liquid")) {
					lbsOfAdditive = additiveQuantity * additiveDensity;
					galsOfAdditive = additiveQuantity;
					}
					
				FormEvent ev = new FormEvent(this, additive, lbsOfAdditive, galsOfAdditive, type, additiveQuantity, state, additiveDensity);
				if (calcPanelListener != null) {
					calcPanelListener.insertAdditiveRow(ev);
					}
				totalSlurryLbs += lbsOfAdditive;
				totalSlurryGals += galsOfAdditive;
				}
			});
		
		calculateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String type = "water";
				double slurryDensity = 0;
				if(waterLbs != 0 && waterGals != 0) {
					totalSlurryLbs = totalSlurryLbs - waterLbs;
					totalSlurryGals = totalSlurryGals - waterGals;
					waterLbs = 0;
					waterGals = 0;
				}
				
				try {
					slurryDensity = Double.parseDouble(densityField.getText());
				}
				catch(NumberFormatException err) {
					JOptionPane.showMessageDialog(CalculatorPanel.this, "Please enter a valid number!");
					}
				
				String waterType = getWaterType();
				
				if(calcPanelListener != null) {
					waterGals = calcPanelListener.waterGalsRequirement(waterType, totalSlurryGals, totalSlurryLbs, slurryDensity);
					waterLbs = calcPanelListener.waterLbsRequirement(waterType, waterGals);
				}

				totalSlurryGals+= waterGals;
				totalSlurryLbs+= waterLbs;
				
				FormEvent event = new FormEvent(this, waterType, waterLbs, waterGals, type);
				if (calcPanelListener != null) {
					calcPanelListener.insertWaterRow(event);
					calcPanelListener.calculate(totalSlurryGals);
				}
				System.out.println("Lbs: " + totalSlurryLbs);
				System.out.println("Gals: " + totalSlurryGals);
			}
		});
		
		createReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if(calcPanelListener != null) {
					calcPanelListener.showReportPanel();
				}
			}
		});
		
		
		Border innerBorder = BorderFactory.createTitledBorder("Quantity Calculator");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 0, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		layoutComponents();
		
	}
	
	private void layoutComponents() {
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		////////// First Row //////////
		gbc.gridy = 0;
		
		gbc.weightx = 4;
		gbc.weighty = 0.1;
		
		// Add the density label
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(densityLabel, gbc);
		
		// Add the density text field
		gbc.gridx = 1;
		gbc.weighty = 100;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(densityField, gbc);
		
		///// Next Row /////
		gbc.gridy ++;
		// Add the cement label
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(cementLabel, gbc);
		
		// Add the cement combo box
		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(cementCombo, gbc);
		
		///// Next Row /////
		gbc.gridy ++;
		// Add the cement add button
		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(cementAddButton, gbc);
		
		////////// Next Row //////////
		gbc.gridy++;
		//Add the additive type label		
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(additiveTypeLabel, gbc);
		
		//Add the additive type combo box
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(additiveTypeCombo, gbc);
		
		////////// Next Row //////////
		gbc.gridy++;
		
		//Add the additive select label
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(additiveNameLabel, gbc);
		
		//Add the additive select combo box
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(additiveCombo, gbc);
		
		////////// Next Row //////////
		gbc.gridy++;
		
		//Add the quantity label
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(quantityLabel, gbc);
		
		//Add the quantity field
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(quantityField, gbc);
		
		////////// Next Row //////////
		gbc.gridy++;
		
		//Add the add to slurry button
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(addToSlurryBtn, gbc);
		
		////////// Next Row //////////
		gbc.gridy++;
		
		//Add water type label
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(waterTypeLabel, gbc);
		
		//Add water type combo box
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(waterTypeCombo, gbc);
		
		////////// Next Row //////////
		gbc.gridy++;
		
		//Add the calculate button
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(calculateBtn, gbc);
		
		////////// Next Row //////////
		gbc.gridy++;
		
		//Add the create report button
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(createReportButton, gbc);
		
	}
	
	public void setFormListener(CalculatorPanelListener listener) {
		this.calcPanelListener = listener;
	}
	
	public void additiveTypeBox(ArrayList<String> array) {
	     for(String additiveType : array) { 		      
	    	 additiveTypeModel.addElement(new String(additiveType));
	     }
	}
	
	public void populateAdditiveNameCombo(ArrayList<String> array) {
		additiveNameModel.removeAllElements();
		for(String additive : array) {
			additiveNameModel.addElement(new String(additive));
		}
	}
	
	public void cementSelect(ArrayList<String> array) {
		cementSelectModel.removeAllElements();
		for(String cement : array) {
			cementSelectModel.addElement(new String(cement));
		}
	}
	
	public void populateWaterBox(ArrayList<String> array) {
		waterSelectModel.removeAllElements();
		for(String water : array) {
			waterSelectModel.addElement(new String(water));
			
		}
	}
	
	public void setComponentsVisible() {
		additiveTypeLabel.setEnabled(true);
		additiveTypeCombo.setEnabled(true);
		additiveNameLabel.setEnabled(true);
		additiveCombo.setEnabled(true);
		quantityLabel.setEnabled(true);
		quantityField.setEnabled(true);
		addToSlurryBtn.setEnabled(true);
		cementAddButton.setEnabled(false);
	}
	
	public String getAdditiveType() {
		/*
		 * Returns the selected item from the
		 * additive type combo box
		 */
		String additiveType = (String)additiveTypeCombo.getSelectedItem();
		return additiveType;
	}
	
	public String getSelectedCement() {
		String selectedCement = (String)cementCombo.getSelectedItem();
		return selectedCement;
	}
	
	public String getSelectedAdditive() {
		String selectedAdditive = (String)additiveCombo.getSelectedItem();
		return selectedAdditive;
	}
	
	public double getAdditiveQuantity() {
		double additiveQuantity = Double.parseDouble(quantityField.getText());

		return additiveQuantity;
	}
	
	public double getSlurryDensity() {
		double slurryDensity = Double.parseDouble(densityField.getText());
		return slurryDensity;
	}
	
	public String getWaterType() {
		String waterType = (String)waterTypeCombo.getSelectedItem();
		return waterType;
	}
	
	public void setSlurryLbsAndGals(ArrayList<Double> componentData) {
		double componentLbs = componentData.get(0);
		double componentGals = componentData.get(1);
		totalSlurryLbs = totalSlurryLbs - componentLbs;
		totalSlurryGals = totalSlurryGals - componentGals;
	}
}
