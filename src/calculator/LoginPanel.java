package calculator;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
	private JLabel userNameLabel;
	private JTextField userNameField;
	private JLabel passwordLabel;
	private JTextField passwordField;
	private JButton loginButton;
	private LoginPanelListener listener;
	
	public LoginPanel() {
		
		userNameLabel = new JLabel("User Name: ");
		userNameLabel.setFont(new Font("", Font.PLAIN, 20));
		userNameField = new JTextField(8);
		
		passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(new Font("", Font.PLAIN, 20));
		passwordField = new JTextField(8);
		
		loginButton = new JButton("Log In");
		loginButton.setFont(new Font("", Font.PLAIN, 20));
		
		loginButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String userName = userNameField.getText();
				String password = passwordField.getText();
				LoginPanelEvent ev = new LoginPanelEvent(this, userName, password);
				if(listener != null) {
					//listener.checkNullPassword(ev);
					listener.login(ev);
					}
				}
			});
		
		layoutComponents();
	}
	
	private void layoutComponents() {
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridy = 0;
		
		gc.weightx = 4;
		gc.weighty = 0.1;
		
		// Add the density label
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(150, 5, 5, 5);
		add(userNameLabel, gc);
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(156, 5, 5, 5);
		add(userNameField, gc);
		
		gc.gridy++;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(5, 5, 5, 5);
		add(passwordLabel, gc);
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(10, 5, 5, 5);
		add(passwordField, gc);
		
		gc.gridy++;
		
		gc.gridx = 0;
		gc.gridwidth = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5, 5, 150, 5);
		add(loginButton, gc);
	}
	
	public void clearCredentials() {
		userNameField.setText(null);
		passwordField.setText(null);
	}
	
	public void setListener(LoginPanelListener listener) {
		this.listener = listener;
	}
}
