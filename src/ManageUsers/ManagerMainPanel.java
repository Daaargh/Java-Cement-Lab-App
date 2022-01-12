package ManageUsers;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;

import calculator.CalculatorToolsDialog;
import qc.QCTemplatesPanel;

public class ManagerMainPanel extends JPanel {
	
	private CardLayout cards;
	private JPanel mainPanel;
	private ManagerMenuPanel managerMenuPanel;
	private CalculatorToolsDialog calculatorToolsDialog;
	private QCTemplatesPanel qcTemplatesPanel;
	//private ManagerUsersPanel manageUsersPanel;
	
	public ManagerMainPanel() {
		
		cards = new CardLayout();
		mainPanel = new JPanel(cards);
		managerMenuPanel = new ManagerMenuPanel();
		calculatorToolsDialog = new CalculatorToolsDialog(null);
		qcTemplatesPanel = new QCTemplatesPanel();
		//manageUsersPanel = new ManageUsersPanel();
		
		managerMenuPanel.setListener(new ManagerMenuListener() {
			
			@Override
			public void showUsersMenu() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void showQCTemplates() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void showMenu() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void showCalculatorToolsDialog() {
				calculatorToolsDialog.setVisible(true);
				
			}
		});
		
		setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
		
		mainPanel.add(managerMenuPanel, "managerMenuPanel");
		mainPanel.add(qcTemplatesPanel, "qcTemplatesPanel");
		//mainPanel.add(manageUsersPanel, "manageUsersPanel");
		
	}
	
}
