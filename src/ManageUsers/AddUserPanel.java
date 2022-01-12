package ManageUsers;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class AddUserPanel extends JPanel {
	private AddUserPanelListener listener;
	private JLabel userNameLabel;
	private JTextField userNameField;
	private JLabel roleLabel;
	private JComboBox roleCombo;
	private DefaultComboBoxModel comboModel;
	private JButton addUser;
	
	public AddUserPanel() {
		userNameLabel = new JLabel("User Name: ");
		userNameField = new JTextField(8);
		roleLabel = new JLabel("Role: ");
		
		comboModel = new DefaultComboBoxModel();
		comboModel.addElement("Manager");
		comboModel.addElement("Technician");
		comboModel.addElement("Warehouse");
		roleCombo = new JComboBox();
		roleCombo.setModel(comboModel);
		
		addUser = new JButton("Add User");
		
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = userNameField.getText();
				String userRole = (String) roleCombo.getSelectedItem();
				AddUserEvent ev = new AddUserEvent(this, userName, userRole);
				
				if(listener != null) {
					listener.addUser(ev);
				}
				userNameField.setText(null);
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
		add(userNameField, gc);
		
		//////// Next Row /////////
		
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 0);
		add(roleLabel, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(roleCombo, gc);
		
		//// next row ////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(addUser, gc);
	}
	
	public void setListener(AddUserPanelListener listener) {
		this.listener = listener;
	}
}
