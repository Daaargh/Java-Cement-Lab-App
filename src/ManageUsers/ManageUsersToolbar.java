package ManageUsers;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ManageUsersToolbar extends JPanel {
	
	private ManageUsersToolbarListener listener;
	private JButton createUser;
	private JButton deleteUser;
	private JButton resetPassword;
	private JButton changeRole;
	
	public ManageUsersToolbar() {
		createUser = new JButton("Create User");
		deleteUser = new JButton("Delete User");
		resetPassword = new JButton("Reset Password");
		changeRole = new JButton("Change User Role");
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(createUser);
		add(deleteUser);
		add(resetPassword);
		add(changeRole);
		
		createUser.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(listener != null) {
					listener.showAddUser();
				}
				}
			});
		
		deleteUser.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(listener != null) {
					listener.showDeleteUser();
				}
				}
			});
		
		resetPassword.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(listener != null) {
					listener.showResetPassword();
				}
				}
			});
		
		changeRole.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(listener != null) {
					listener.showChangeRole();
				}
				}
			});
		}
	
	public void setListener(ManageUsersToolbarListener listener) {
		this.listener = listener;
		}
	}
