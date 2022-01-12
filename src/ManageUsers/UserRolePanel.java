package ManageUsers;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserRolePanel extends JPanel {
	private UserRolePanelListener listener;
	private JLabel userNameLabel;
	private JComboBox userNameCombo;
	private DefaultComboBoxModel userComboModel;
	private JLabel userRole;
	private JComboBox userRoleCombo;
	private DefaultComboBoxModel userRoleModel;
	private JButton setRoleButton;
	
	public UserRolePanel() {
		userNameLabel = new JLabel("User Name: ");
		
		userComboModel = new DefaultComboBoxModel();
		userNameCombo = new JComboBox();
		userNameCombo.setModel(userComboModel);
		
		userRole = new JLabel("User Role: ");
		
		userRoleModel = new DefaultComboBoxModel();
		userRoleModel.addElement("Manager");
		userRoleModel.addElement("Technician");
		userRoleModel.addElement("Warehouse");
		userRoleCombo = new JComboBox();
		userRoleCombo.setModel(userRoleModel);
		
		setRoleButton = new JButton("Set Role");
		
		setRoleButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String userName = (String) userNameCombo.getSelectedItem();
				String userRole = (String) userRoleCombo.getSelectedItem();
				UserRolePanelEvent ev = new UserRolePanelEvent(this, userName, userRole);
				if(listener != null) {
					listener.setUserRole(ev);
				}
			}
		});
		
		layoutComponents();
	}
	
	private void layoutComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridy = 0;
		
		//////// First Row /////////
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 0);
		add(userNameLabel, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(userNameCombo, gc);
		
		//////// Next Row /////////
		
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 0);
		add(userRole, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(userRoleCombo, gc);
		
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(setRoleButton, gc);

	}
	
	public void populateUsersCombo(ArrayList<String> userNames) {
		userComboModel.removeAllElements();
		for(String userName : userNames) {
			userComboModel.addElement(userName);
		}
	}
	
	public void setListener(UserRolePanelListener listener) {
		this.listener = listener;
	}
}
