package SlurryTesting;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class TestPanel extends JPanel {
	
	private TestPanelListener testPanelListener;

	
	private JLabel tempLabel;
	private JTextField tempField;
	private JComboBox celsiusFahr;
	private DefaultComboBoxModel tempSelect;
	
	private JLabel pressureLabel;
	private JTextField pressureField;
	
	private JLabel rheologyLabel;
	
	private JLabel mixRheologyLabel;
	private RheologyTableModel mixRheoModel;
	private RheoTable mixRheoTable;
	
	private JLabel tempRheologyLabel;
	private RheologyTableModel tempRheoModel;
	private RheoTable tempRheoTable;
	
	private JLabel fluidLossLabel;
	private FluidLossModel fluidLossModel;
	private FluidLossTable fluidTable;
	
	private JLabel thickeningTimeLabel;
	private ThickeningTableModel thickeningModel;
	private ThickeningTimeTable thickeningTable;
	
	private JLabel compStrengthLabel;
	private CompressiveTableModel compTableModel;
	private CompStrengthTable compStrengthTable;	
	
	private JButton generateReportButton;

	public TestPanel() {
		//Dimension dimension = getPreferredSize();
		//dimension.height = 500;
		//setPreferredSize(dimension);


		Border innerBorder = BorderFactory.createTitledBorder("Testing");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 80, 5, 80);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		tempLabel = new JLabel("Temperature: ");
		tempField = new JTextField(4);
		celsiusFahr = new JComboBox();
		tempSelect = new DefaultComboBoxModel<String>();
		tempSelect.addElement("\u2103");
		tempSelect.addElement("\u2109");
		celsiusFahr.setModel(tempSelect);
		celsiusFahr.setSelectedIndex(0);
		celsiusFahr.setPreferredSize(new Dimension(44, 19));
		
		
		pressureLabel = new JLabel("Pressure (psi): ");
		pressureField = new JTextField(4);

		
		rheologyLabel = new JLabel("Rheology");
		
		mixRheologyLabel = new JLabel("Rheology at mix: ");
		mixRheoModel = new RheologyTableModel();
		mixRheoTable = new RheoTable(mixRheoModel);

		
		tempRheologyLabel = new JLabel("Rheology at temp");
		tempRheoModel = new RheologyTableModel();
		tempRheoTable = new RheoTable(tempRheoModel);
		
		fluidLossLabel = new JLabel("Fluid Loss");
		fluidLossModel = new FluidLossModel();
		fluidTable = new FluidLossTable(fluidLossModel);
		
		thickeningTimeLabel = new JLabel("TT");
		thickeningModel = new ThickeningTableModel();
		thickeningTable = new ThickeningTimeTable(thickeningModel);
		
		compStrengthLabel = new JLabel("Compressive Strength");
		compTableModel = new CompressiveTableModel();
		compStrengthTable = new CompStrengthTable(compTableModel);
		
		generateReportButton = new JButton("Generate Report");

		
		generateReportButton.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String temp = tempField.getText() + (String)celsiusFahr.getSelectedItem();
				String depth = pressureField.getText();
				List<String> mixRheoValues = mixRheoTable.getCellValues();
				List<String> tempRheoValues = tempRheoTable.getCellValues();
				List<String> fluidLossValues = fluidTable.getCellValues();
				List<String> thickeningValues = thickeningTable.getCellValues();
				List<String> compressiveValues = compStrengthTable.getCellValues();
				
				
				TestPanelEvent testPanEvent = new TestPanelEvent(this, temp, depth, 
						mixRheoValues, tempRheoValues, fluidLossValues, 
						thickeningValues, compressiveValues);
				if (testPanelListener != null) {
					testPanelListener.generateReport(testPanEvent);
					}
				}
			});
		
		layoutComponents();
	}
	
	private void layoutComponents() {
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		////////// First Row //////////
		gbc.gridy = 0;
		gbc.gridwidth = 1;

		gbc.weighty = 0.1;
		
		// Add the temperature label
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(tempLabel, gbc);
		
		// Add the temperature field
		gbc.gridx = 1;
		gbc.weighty = 0.4;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(tempField, gbc);
		
		// Add the temperature unit combo box
		gbc.gridx = 2;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(celsiusFahr, gbc);
		
		///// Next Row /////
		
		gbc.gridwidth = 1;
		gbc.gridy ++;
		
		// Add the depth label
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(pressureLabel, gbc);
		
		// Add the depth field
		gbc.weighty = 1000000000;
		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 5, 15, 5);
		add(pressureField, gbc);
		
		
		///// Next Row /////
		gbc.gridy++;
		
		// Add the mix rheology label
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(mixRheologyLabel, gbc);
		
		///// Next Row /////
		//gbc.gridy++;
		gbc.gridwidth = 5;
		// Add the mix rheology table
		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(new JScrollPane(mixRheoTable), gbc);

		
		///// Next Row /////
		gbc.gridy ++;
		gbc.gridwidth = 1;
		// Add the rheology at temperature label
		gbc.gridx = 0;
		gbc.weighty = 500000;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(tempRheologyLabel, gbc);
		
		///// Next Row /////
		//gbc.gridy++;
		gbc.gridwidth = 5;
		// Add the temp rheology table
		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 5, 15, 5);
		add(new JScrollPane(tempRheoTable), gbc);
		
		///// Next Row /////
		gbc.gridy ++;
		gbc.gridwidth = 1;
		// Add the fluid loss label
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(fluidLossLabel, gbc);
		
		///// Next Row /////
		//gbc.gridy ++;
		gbc.gridwidth = 4;
		// Add the fluid loss label
		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(5, 5, 15, 5);
		add(new JScrollPane(fluidTable), gbc);
		
		///// Next Row /////
		gbc.gridy ++;
		// Add the thickening time label
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(thickeningTimeLabel, gbc);
		
		///// Next Row /////
		//gbc.gridy ++;
		// Add the thickening time label
		gbc.gridwidth = 3;
		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(5, 5, 15, 5);
		add(new JScrollPane(thickeningTable), gbc);
		
		

		
		///// Next Row /////
		gbc.gridy ++;
		gbc.gridwidth = 1;
		// Add the compressive strength label
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(compStrengthLabel, gbc);
		
		// Add the 6 hours text field
		gbc.gridx = 1;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 5, 15, 5);
		add(new JScrollPane(compStrengthTable), gbc);
		
		///// Next Row /////
		gbc.gridy ++;
		gbc.gridwidth = 4;
		// Add the generate report button
		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(new JScrollPane(generateReportButton), gbc);
	}
	
	public void setTestPanelListener(TestPanelListener listener) {
		this.testPanelListener = listener;
	}
}
