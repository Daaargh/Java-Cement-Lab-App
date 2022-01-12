package calculator;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Controller;
import model.CalcRow;

public class CalculatorMainPanel extends JPanel {
	
	private CardLayout cards;
	private JPanel mainPanel;
	private Toolbar toolbar;
	private CalculatorPanel calculatorPanel;
	private TablePanel tablePanel;
	private Controller controller;
	private AddEditDialog addEditDialog;
	private DeleteDialog deleteDialog;
	private JScrollPane scroller;
	private JFileChooser fileChooser;
	private CalculatorMainPanelListener calcMainPanelListener;
	private String userRole;
	
	public CalculatorMainPanel() {
	
	controller = Controller.getInstance();
	mainPanel = new JPanel();
	fileChooser = new JFileChooser();
	toolbar = new Toolbar();
	calculatorPanel = new CalculatorPanel();
	tablePanel = new TablePanel();
	addEditDialog = new AddEditDialog(null);
	deleteDialog = new DeleteDialog(null);
	
	
	
	tablePanel.setData(controller.getCalculatorRows());
	
	calculatorPanel.setFormListener(new CalculatorPanelListener() {
		
		public void populateAdditiveCombo(String additiveType) {
			ArrayList<String> addArray = new ArrayList<String>();
			addArray = controller.populateAdditiveCombo(additiveType);
			calculatorPanel.populateAdditiveNameCombo(addArray);
		}
		
		@Override
		public double getCementLbs(String cement) {
			double cementLbs = controller.getCementLbs(cement);
			return cementLbs;
		}

		@Override
		public double getCementGals(String cement) {
			double cementGals = controller.getCementGals(cement);
			return cementGals;
		}
		
		@Override
		public void insertCementRow(FormEvent e) {
			controller.insertCementRow(e);
			tablePanel.refresh();
		}

		public void insertAdditiveRow(FormEvent e) {
			connect();
			controller.insertAdditiveRow(e);
			tablePanel.refresh();
		}
		
		public void calculate(double totalGals) {
			try {
				controller.setLabQuantities(totalGals);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tablePanel.refresh();
		}

		@Override
		public void showReportPanel() {
			if(calcMainPanelListener != null) {
				calcMainPanelListener.showReportPanel();
			}
			
		}

		@Override
		public String getAdditiveState(String additive) {
			String additiveState = controller.getAdditiveState(additive);
			return additiveState;
		}

		@Override
		public double getAdditiveDensity(String additive) {
			double additiveDensity = controller.getAdditiveDensity(additive);
			return additiveDensity;
		}

		@Override
		public double waterGalsRequirement(String name, double totalGals, double totalLbs, double density) {
			double waterGalsRequired = controller.waterGalsRequirement(name, totalGals, totalLbs, density);
			return waterGalsRequired;
		}

		@Override
		public double waterLbsRequirement(String name, double waterGals) {
			double waterLbsRequired = controller.waterLbsRequirement(name, waterGals);
			return waterLbsRequired;
		}

		@Override
		public void insertWaterRow(FormEvent e) {
			connect();
			controller.insertWaterRow(e);
			tablePanel.refresh();
			}

		@Override
		public void formEventOccurred(FormEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setAdditiveCementLbs(double lbsOfCement) {
			controller.setAdditiveLbsAndGals(lbsOfCement);
			tablePanel.refresh();
			}
		});
	
	tablePanel.setListener(new TablePanelListener() {
		public void rowDeleted(int row) {
			ArrayList<Double> componentData = controller.removeComponentRow(row);
			calculatorPanel.setSlurryLbsAndGals(componentData);
		}
	});
	
	toolbar.setToolbarListener(new ToolbarListener() {
		public void showAdditiveDialog() {
			addEditDialog.setVisible(true);
		}
		
		public void showDeleteDialog() {
			deleteDialog.setVisible(true);
		}

		public void home() {
			if(calcMainPanelListener != null) {
				calcMainPanelListener.home();
			}
		}
	});
	
	addEditDialog.setDialogListener(new AddEditDialogListener() {
		public void saveAdditive(DialogEvent evDialog) {
			connect();
			
			try {
			controller.saveAdditive(evDialog);
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(CalculatorMainPanel.this, "Unable to save to database.", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
			}
			setNameBox();
		}

		public void saveWaterType(DialogEvent evDialog) {
			connect();
			
			try {
			controller.saveWaterType(evDialog);
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(CalculatorMainPanel.this, "Unable to save to database.", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
			}
			populateWaterType();
			setNameBox();
		}

		public void saveCementType(DialogEvent evDialog) {
			connect();
			
			try {
			controller.saveCementType(evDialog);
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(CalculatorMainPanel.this, "Unable to save to database.", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
			}
			populateCementCombo();
			setNameBox();
		}
	});
	
	deleteDialog.setDialogListener(new DeleteDialogListener() {

		public void deleteAdditive(DialogEvent evDialog) {
			connect();
			try {
			controller.deleteAdditive(evDialog);
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(CalculatorMainPanel.this, "Unable to delete from database.", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
			}
		}

		public void deleteCementType(DialogEvent evDialog) {
			connect();
			try {
			controller.deleteCementType(evDialog);
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(CalculatorMainPanel.this, "Unable to delete from database.", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
			}
			populateCementCombo();
		}

		public void deleteWaterType(DialogEvent evDialog) {
			connect();
			try {
			controller.deleteWaterType(evDialog);
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(CalculatorMainPanel.this, "Unable to delete from database.", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
			}
			populateWaterType();
		}

		public void populateNameBox(String componentType) {
			connect();
			setNameBox();
			}			
	});
	
	setAdditiveTypeBox();
	populateCementCombo();
	populateWaterType();
	populateStateBox();
	setNameBox();
	
	setLayout(new BorderLayout());
	add(toolbar, BorderLayout.NORTH);
	add(calculatorPanel, BorderLayout.WEST);
	add(tablePanel, BorderLayout.CENTER);
	setMinimumSize(new Dimension(700, 600));
	setSize(700, 550);
	setVisible(true);
	}
	
	private void connect() {
		try {
			controller.connect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(CalculatorMainPanel.this, "Cannot connect to database.", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void setAdditiveTypeBox() {
		connect();
		ArrayList<String> addArray = controller.populateAdditiveTypeCombo();
		calculatorPanel.additiveTypeBox(addArray);
		addEditDialog.additiveTypeBox(addArray);
	}
	
	public void populateCementCombo() {
		connect();
		ArrayList<String> cementArray = controller.populateCementCombo();
		calculatorPanel.cementSelect(cementArray);
	}
	
	public void populateStateBox() {
		connect();
		ArrayList<String> cementArray = controller.populateStateBox();
		addEditDialog.populateStateBox(cementArray);
	}
	
	public void setNameBox() {
		connect();
		String componentType = deleteDialog.getComponent();
		ArrayList<String> nameArray = controller.populateNameBox(componentType);
		deleteDialog.populateNameBox(nameArray);
	}
	
	public void populateWaterType() {
		connect();
		ArrayList<String> waterArray = controller.populateWaterType();
		calculatorPanel.populateWaterBox(waterArray);
	}
	
	public void setUserRole(String userRole) {
		toolbar.setUserRole(userRole);
	}
	
	public void setListener(CalculatorMainPanelListener listener) {
		this.calcMainPanelListener = listener;
	}
}
