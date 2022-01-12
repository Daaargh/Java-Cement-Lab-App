package calculator;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ChangePasswordDialog extends JDialog {
	private String userName;
	private PasswordDialogListener listener;
	private JLabel passwordLabel;
	private JTextField passwordField;
	private JButton setPasswordButton;
	
	public ChangePasswordDialog(JFrame parent) {
		super(parent, "Set Password", true);
		
		passwordLabel = new JLabel("New Password: ");
		passwordField = new JTextField(6);
		setPasswordButton = new JButton("Set Password");
		
		setPasswordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = passwordField.getText();
				if(listener != null) {
					listener.setPassword(userName, password);
				}
				passwordField.setText(null);
				setVisible(false);
			}
		});
		
		setSize(250, 200);
		
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
		gc.insets = new Insets(15, 0, 0, 0);
		add(passwordLabel, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(15, 0, 0, 0);
		add(passwordField, gc);
		
		//////// Next Row /////////
		
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		gc.gridwidth = 2;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(setPasswordButton, gc);
		}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setListener(PasswordDialogListener listener) {
		this.listener = listener;
		}
	}
