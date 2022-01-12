package ManageUsers;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ManageUsersDialog extends JDialog {
	
	private ManageUsersDialogListener listener;
	private CardLayout cards;
	private ManageUsersToolbar toolbar;
	private AddUserPanel addUserPanel;
	private DeleteUserPanel deleteUserPanel;
	private UserPasswordPanel userPasswordPanel;
	private UserRolePanel rolePanel;
	private JPanel mainPanel;
	
	public ManageUsersDialog(JFrame parent) {
		super(parent, "Manage Users", true);
		
		cards = new CardLayout();
		
		toolbar = new ManageUsersToolbar();
		addUserPanel = new AddUserPanel();
		deleteUserPanel = new DeleteUserPanel();
		userPasswordPanel = new UserPasswordPanel();
		rolePanel = new UserRolePanel();
		mainPanel = new JPanel(cards);
		
		mainPanel.add(addUserPanel, "addUserPanel");
		mainPanel.add(deleteUserPanel, "deleteUserPanel");
		mainPanel.add(userPasswordPanel, "userPasswordPanel");
		mainPanel.add(rolePanel, "rolePanel");
		
		setLayout(new BorderLayout());
		add(toolbar, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		
		//Dimension dim = new Dimension(510, 200);
		//setPreferredSize(dim);
		setSize(510, 200);
		
		toolbar.setListener(new ManageUsersToolbarListener() {

			@Override
			public void showAddUser() {
				showAddUserPanel();
				}

			@Override
			public void showDeleteUser() {
				if(listener != null) {
					listener.populateDeleteUsersCombo();
				}
				showDeleteUserPanel();
				}

			@Override
			public void showChangeRole() {
				if(listener != null) {
					listener.populateChangeRoleUsersCombo();
				}
				showChangeRolePanel();
				}

			@Override
			public void showResetPassword() {
				if(listener != null) {
					listener.populateResetPasswordUsersCombo();
				}
				showResetPasswordPanel();
				
			}
			
		});
		
		addUserPanel.setListener(new AddUserPanelListener() {

			public void addUser(AddUserEvent event) {
				if(listener != null) {
					listener.addUser(event);
				}
			}
			
		});
		
		deleteUserPanel.setListener(new DeleteUserPanelListener() {

			public void deleteUser(String userName) {
				if(listener != null) {
					listener.deleteUser(userName);
					listener.populateDeleteUsersCombo();
				}
				
			}
			
		});
		
		userPasswordPanel.setListener(new UserPasswordPanelListener() {

			public void resetPassword(String userName) {
				if(listener != null) {
					listener.resetPassword(userName);
					}
				}
			});
		
		rolePanel.setListener(new UserRolePanelListener() {

			public void setUserRole(UserRolePanelEvent ev) {
				if(listener != null) {
					listener.setUserRole(ev);
					}
				}
			});
		}
	
	public void showAddUserPanel() {
		CardLayout cardLayout = (CardLayout) (mainPanel.getLayout());
		cardLayout.show(mainPanel, "addUserPanel");
	}
	
	public void showDeleteUserPanel() {
		CardLayout cardLayout = (CardLayout) (mainPanel.getLayout());
		cardLayout.show(mainPanel, "deleteUserPanel");
	}
	
	public void showChangeRolePanel() {
		CardLayout cardLayout = (CardLayout) (mainPanel.getLayout());
		cardLayout.show(mainPanel, "rolePanel");
	}
	
	public void showResetPasswordPanel() {
		CardLayout cardLayout = (CardLayout) (mainPanel.getLayout());
		cardLayout.show(mainPanel, "userPasswordPanel");
	}
	
	public void populateDeleteUsersCombo(ArrayList<String> userNames) {
		deleteUserPanel.populateUsersCombo(userNames);
	}
	
	public void populateResetPasswordUsersCombo(ArrayList<String> userNames) {
		userPasswordPanel.populateUsersCombo(userNames);
	}
	
	public void populateChangeRoleUsersCombo(ArrayList<String> userNames) {
		rolePanel.populateUsersCombo(userNames);
	}
	
	public void setListener(ManageUsersDialogListener listener) {
		this.listener = listener;
	}
}
