package calculator;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ManageUsers.AddUserEvent;
import ManageUsers.MainScreenListener;
import ManageUsers.MainScreenPanel;
import ManageUsers.ManageUsersDialog;
import ManageUsers.ManageUsersDialogListener;
import ManageUsers.UserRolePanelEvent;
import SlurryTesting.TestPanel;
import SlurryTesting.TestPanelEvent;
import SlurryTesting.TestPanelListener;
import controller.Controller;
import qc.QCPanel;
import qc.QCPanelListener;
import reportSearch.ReportSearchEvent;
import reportSearch.ReportSearchListener;
import reportSearch.ReportSearchPanel;

public class MainFrame extends JFrame {
	
	private CardLayout cards;
	private Controller controller;
	private LoginPanel loginPanel;
	private String userRole;
	private CalculatorMainPanel calculatorMainPanel;
	private TestPanel testPanel;
	private ReportSearchPanel reportSearchPanel;
	private QCPanel qcMain;
	private ManageUsersDialog manageUsersDialog;
	private ChangePasswordDialog changePasswordDialog;
	private MainScreenPanel mainScreenPanel;

	private JPanel mainPanel;
	
	public MainFrame() {
		super("TEST Lab App");
		
		cards = new CardLayout();
		controller = Controller.getInstance();
		calculatorMainPanel = new CalculatorMainPanel();
		testPanel = new TestPanel();
		reportSearchPanel = new ReportSearchPanel();
		qcMain = new QCPanel();
		mainScreenPanel = new MainScreenPanel();
		manageUsersDialog = new ManageUsersDialog(MainFrame.this);
		changePasswordDialog = new ChangePasswordDialog(MainFrame.this);
		loginPanel = new LoginPanel();
		userRole = new String();
		mainPanel = new JPanel(cards);
		
		//mainPanel.setLayout
		
		mainPanel.add(loginPanel, "loginPanel");
		mainPanel.add(mainScreenPanel, "mainScreenPanel");
		mainPanel.add(calculatorMainPanel, "calculatorMainPanel");
		mainPanel.add(testPanel, "testPanel");
		mainPanel.add(reportSearchPanel, "reportSearchPanel");
		mainPanel.add(qcMain, "qcPanel");
		setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
		
		loginPanel.setListener(new LoginPanelListener() {
			public void login(LoginPanelEvent ev) {
				
				String userName = ev.getUserName();
				boolean userExists = checkUserExists(userName);
				
				if(userExists == false) {
					JOptionPane.showMessageDialog(MainFrame.this,
						    "Invalid user name",
						    "Unable to log in",
						    JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				boolean nullStatus = checkPasswordNullStatus(ev);
				System.out.println(nullStatus);
				if(nullStatus == true) {
					changePasswordDialog.setUserName(userName);
					changePasswordDialog.setVisible(true);
					
					return;
				}
				
				else {
					boolean validPassword = checkValidPassword(ev);
					
					if(validPassword == true) {
						userLogin(ev);
					}
					
					else {
						JOptionPane.showMessageDialog(MainFrame.this,
							    "Invalid password",
							    "Unable to log in",
							    JOptionPane.WARNING_MESSAGE);
						
						return;
					}
				}

				}
			});
		
		mainScreenPanel.setListener(new MainScreenListener() {

			public void showCalculator() {
				showCalculatorScreen();
			}

			public void showReportSearch() {
				showReportSearchScreen();
				}

			public void showQC() {
				showQCScreen();
				}
			
			public void logOut() {
				showLogInScreen();
				}

			public void showManagerTools() {
				manageUsersDialog.setVisible(true);
				
			}
			});
		
		calculatorMainPanel.setListener(new CalculatorMainPanelListener() {

			public void home() {
				showHomeScreen();
			}

			@Override
			public void showReportPanel() {
				showSlurryTestScreen();
			}
		});
		
		testPanel.setTestPanelListener(new TestPanelListener() {

			@Override
			public void generateReport(TestPanelEvent ev) {
				controller.generateReport(ev);
				controller.storeReport();
			}		
		});
		
		reportSearchPanel.setReportSearchListener(new ReportSearchListener() {

			public void queryReports(ReportSearchEvent reportSearchEvent) {
				HashMap<String, String> reports = controller.queryReports(reportSearchEvent);
				
				reportSearchPanel.listReports(reports);
				}

			public void home() {
				showHomeScreen();
				}
			});
		
		qcMain.setListener(new QCPanelListener() {
			
			public void home() {
				showHomeScreen();
			}

			public void populateDeleteDialogCombo() {
				ArrayList<String> templates = new ArrayList<String>();
				templates = controller.getTemplateReports();
				populateDeleteTemplateDialogCombo(templates);
				}

			public void deleteTemplate(String template) {
				controller.deleteTemplate(template);
				ArrayList<String> templates = new ArrayList<String>();
				templates = controller.getTemplateReports();
				populateDeleteTemplateDialogCombo(templates);
				}
			});
		
		manageUsersDialog.setListener(new ManageUsersDialogListener() {

			public void addUser(AddUserEvent ev) {
				boolean userExists = controller.addUser(ev);
				String userName = ev.getUserName();
				if(userExists == true) {
					JOptionPane.showMessageDialog(MainFrame.this,
						    "User already exists",
						    "Unable to add user",
						    JOptionPane.WARNING_MESSAGE);
					}
				
				else if(userExists == false) {
					JOptionPane.showMessageDialog(MainFrame.this,
						    "User: " + userName + " successfully created",
						    "User added",
						    JOptionPane.INFORMATION_MESSAGE);
				}
				}

			public void populateDeleteUsersCombo() {
				ArrayList<String> userNames = controller.getUserNames();
				manageUsersDialog.populateDeleteUsersCombo(userNames);
				}

			public void deleteUser(String userName) {
				controller.deleteUser(userName);
				JOptionPane.showMessageDialog(MainFrame.this,
					    "User: " + userName + " was successfully deleted",
					    "User deleted",
					    JOptionPane.INFORMATION_MESSAGE);
				}

			public void resetPassword(String userName) {
				controller.resetPassword(userName);
				JOptionPane.showMessageDialog(MainFrame.this,
					    "Password reset for " + userName,
					    "Password reset",
					    JOptionPane.INFORMATION_MESSAGE);
				}

			public void setUserRole(UserRolePanelEvent ev) {
				controller.setUserRole(ev);
				}

			public void populateResetPasswordUsersCombo() {
				ArrayList<String> userNames = controller.getUserNames();
				manageUsersDialog.populateResetPasswordUsersCombo(userNames);
				
			}

			public void populateChangeRoleUsersCombo() {
				ArrayList<String> userNames = controller.getUserNames();
				manageUsersDialog.populateChangeRoleUsersCombo(userNames);
				
			}
			});
		
		changePasswordDialog.setListener(new PasswordDialogListener() {

			public void setPassword(String userName, String password) {
				controller.setPassword(userName, password);
				}
			});
		
		setMinimumSize(new Dimension(700, 600));
		setSize(700, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	protected void showHomeScreen() {
		CardLayout cardLayout = (CardLayout) (mainPanel.getLayout());
		cardLayout.show(mainPanel, "mainScreenPanel");
	}

	public void showCalculatorScreen() {
		CardLayout cardLayout = (CardLayout) (mainPanel.getLayout());
		cardLayout.show(mainPanel, "calculatorMainPanel");
	}
	
	public void showSlurryTestScreen() {
		CardLayout cardLayout = (CardLayout) (mainPanel.getLayout());
		cardLayout.show(mainPanel, "testPanel");
	}
	
	
	public void showReportSearchScreen() {
		CardLayout cardLayout = (CardLayout) (mainPanel.getLayout());
		cardLayout.show(mainPanel, "reportSearchPanel");
	}
	
	public void showQCScreen() {
		CardLayout cardLayout = (CardLayout) (mainPanel.getLayout());
		cardLayout.show(mainPanel, "qcPanel");
	}
	
	public void showLogInScreen() {
		CardLayout cardLayout = (CardLayout) (mainPanel.getLayout());
		cardLayout.show(mainPanel, "loginPanel");
		loginPanel.clearCredentials();
	}
	
	public void userLogin(LoginPanelEvent ev) {
			userRole = controller.getUserRole(ev);
			mainScreenPanel.setUserRole(userRole);
			calculatorMainPanel.setUserRole(userRole);
			qcMain.setUserRole(userRole);
			showHomeScreen();
		}
	
	public boolean checkPasswordNullStatus(LoginPanelEvent ev) {
		boolean status = controller.checkPasswordNullStatus(ev);
		return status;
	}
	
	public void populateDeleteTemplateDialogCombo(ArrayList<String> templates) {
		qcMain.populateDeleteTemplateDialogCombo(templates);
	}
	
	public boolean checkUserExists(String userName) {
		boolean userExists = controller.checkUserExists(userName);
		return userExists;
	}
	
	public boolean checkValidPassword(LoginPanelEvent ev) {
		boolean validPassword = controller.checkValidPassword(ev);
		
		return validPassword;
	}
}