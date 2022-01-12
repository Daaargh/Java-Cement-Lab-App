package qc;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import controller.Controller;
import model.QCTemplateTableRow;

public class QCPanel extends JPanel {
	private CardLayout cards;
	private QCPanelListener listener;
	private QCTemplatesPanel qcTemplatesPanel;
	private DeleteTemplateDialog deleteTemplateDialog;
	private QCTestPanel qcTestPanel;
	private QCTechPanel qcTechPanel;
	private QCToolbar qcToolbar;
	private JPanel mainPanel;
	private Controller controller;
	
	public QCPanel() {
		cards = new CardLayout();
		qcTemplatesPanel = new QCTemplatesPanel();
		deleteTemplateDialog = new DeleteTemplateDialog(null);
		qcTestPanel = new QCTestPanel();
		qcTechPanel = new QCTechPanel();
		qcToolbar = new QCToolbar();
		mainPanel = new JPanel(cards);
		controller = Controller.getInstance();
		
		cards = new CardLayout();
		setLayout(new BorderLayout());
		
		add(qcToolbar, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		mainPanel.add(qcTemplatesPanel, "qcTemplatesPanel");
		mainPanel.add(qcTestPanel, "qcTestPanel");
		mainPanel.add(qcTechPanel, "qcTechPanel");
		
		qcTemplatesPanel.setQCTemplateTableData(controller.setQCTemplateTableData());
		qcTemplatesPanel.setQCComponents(controller.getQCComponents());
		
		qcTestPanel.setTestTableData(controller.getQCTestTableRows());
		qcTestPanel.populateTestFormCombo(controller.getProducts());
		
		ArrayList<String> products = controller.getProducts();
		
		qcToolbar.setToolbarListener(new QCToolbarListener() {

			public void showQCTemplatePanel() {
				showTemplatePanel();
				
			}

			public void showQCTestPanel() {
				showTestPanel();
				
			}

			public void showDeleteQCTemplatePanel() {
				if(listener != null) {
					listener.populateDeleteDialogCombo();
				}
				showDeleteTemplatePanel();
				
			}

			public void showTechPanel() {
				showQCTechPanel();
				
			}

			public void home() {
				if(listener != null) {
					listener.home();
					}
				}
			});
		
		qcTemplatesPanel.setListener(new QCTemplatePanelListener() {

			@Override
			public void upDatePendingTests() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void generateQCTemplate(QCTemplateReportEvent ev) {
				controller.generateQCTemplate(ev);
				
			}

			@Override
			public void storeQCTemplate() {
				controller.storeQCTemplate();
			}

			@Override
			public void addQCTemplateTableRow(QCTemplateFormEvent ev) {
				controller.addQCTemplateTableRow(ev);
				}
			});
		
		qcTestPanel.setListener(new QCTestPanelListener() {

			@Override
			public void clearQCTestArray() {
				controller.clearQCTestArray();
				}

			@Override
			public void addQCTestTableRow(QCTestFormEvent ev) {
				controller.addQCTestTableRow(ev);
				}

			@Override
			public String getQCTemplateFilePath(String product) {
				String qcTemplateFilePath = controller.getQCTemplateFilePath(product);
				return qcTemplateFilePath;
			}

			@Override
			public void createPendingQCTest(String productName, String filePath, String testNumber) {
				controller.createPendingQCTest(productName, filePath, testNumber);
				}

			@Override
			public void storePendingQCTest() {
				controller.storePendingQCTest();
				}
			});
		
		qcTechPanel.setListener(new QCTechPanelListener() {

			@Override
			public String getTestProcedure(QCTechFormEvent ev) {
				String testProcedure = controller.getTestProcedure(ev);
				return testProcedure;
			}

			@Override
			public void saveTestResults(ResultsEvent rev) {
				controller.saveTestResults(rev);
			}

			@Override
			public void storeCompletedQCTest() {
				controller.storeCompletedQCTest();
			}

			@Override
			public String getCurrentTestPath(int testNumber) {
				String currentFilePath = controller.getCurrentTestPath(testNumber);
				return currentFilePath;
			}

			@Override
			public ArrayList<ArrayList<String>> getPendingTests() {
				ArrayList<ArrayList<String>> pendingTests = new ArrayList<ArrayList<String>>();
				pendingTests = controller.getPendingTests();
				return pendingTests;
			}
			
		});
		
		deleteTemplateDialog.setListener(new DeleteTemplateListener() {

			public void deleteTemplate(String templateReport) {
				if(listener != null) {
					listener.deleteTemplate(templateReport);
					}
				}
			});
		}
	
	public void showTemplatePanel() {
		CardLayout cardLayout = (CardLayout) (mainPanel.getLayout());
		cardLayout.show(mainPanel, "qcTemplatesPanel");
	}
	
	public void showTestPanel() {
		CardLayout cardLayout = (CardLayout) (mainPanel.getLayout());
		cardLayout.show(mainPanel, "qcTestPanel");
		qcTestPanel.populateTestFormCombo(controller.getProducts());
	}
	
	public void showQCTechPanel() {
		CardLayout cardLayout = (CardLayout) (mainPanel.getLayout());
		cardLayout.show(mainPanel, "qcTechPanel");
		qcTechPanel.populatePendingTests();
	}
	
	public void showDeleteTemplatePanel() {
		deleteTemplateDialog.setVisible(true);
	}
	
	public void setQCTemplateTableData() {
		List<QCTemplateTableRow> qcTemplateTableRows = controller.setQCTemplateTableData();
		qcTemplatesPanel.setQCTemplateTableData(qcTemplateTableRows);
	}
	
	public void setUserRole(String userRole) {
		if(userRole.equalsIgnoreCase("Technician")) {
			qcTechPanel.populatePendingTests();
			CardLayout cardLayout = (CardLayout) (mainPanel.getLayout());
			cardLayout.show(mainPanel, "qcTechPanel");
		}
		else if(userRole.equalsIgnoreCase("Warehouse")) {
			CardLayout cardLayout = (CardLayout) (mainPanel.getLayout());
			cardLayout.show(mainPanel, "qcTestPanel");
		}
		qcToolbar.setUserRole(userRole);
	}
	
	public void populateDeleteTemplateDialogCombo(ArrayList<String> templates) {
		deleteTemplateDialog.populateTemplateCombo(templates);
	}
	
	public void setListener(QCPanelListener listener) {
		this.listener = listener;
	}
}
