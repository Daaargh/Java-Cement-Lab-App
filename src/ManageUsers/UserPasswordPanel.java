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

public class UserPasswordPanel extends JPanel {
	private UserPasswordPanelListener listener;
	private JLabel userNameLabel;
	private JComboBox userNameCombo;
	private DefaultComboBoxModel comboModel;
	private JButton resetPasswordButton;
	
	public UserPasswordPanel() {
		userNameLabel = new JLabel("User Name: ");
		
		comboModel = new DefaultComboBoxModel();
		userNameCombo = new JComboBox();
		userNameCombo.setModel(comboModel);
		
		resetPasswordButton = new JButton("Reset Password");
		
		resetPasswordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = (String)userNameCombo.getSelectedItem();
				if(listener != null) {
					listener.resetPassword(userName);
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
		gc.gridwidth = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(resetPasswordButton, gc);
	}
	
	public void populateUsersCombo(ArrayList<String> userNames) {
		comboModel.removeAllElements();
		for(String userName : userNames) {
			comboModel.addElement(userName);
		}
	}
	
	public void setListener(UserPasswordPanelListener listener) {
		this.listener = listener;
	}
}
