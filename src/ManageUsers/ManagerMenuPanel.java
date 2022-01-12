package ManageUsers;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ManagerMenuPanel extends JPanel {
	private ManagerMenuListener listener;
	private JLabel toolsLabel;
	private JButton calculatorTools;
	private JButton qcTools;
	private JButton manageUsers;
	private JButton home;
	
	public ManagerMenuPanel() {
		
		toolsLabel = new JLabel("Manager Tools");
		toolsLabel.setFont(new Font("", Font.PLAIN, 20));
		
		calculatorTools = new JButton("Calculator Tools");
		calculatorTools.setFont(new Font("", Font.PLAIN, 20));
		
		qcTools = new JButton("QC Tools");
		qcTools.setFont(new Font("", Font.PLAIN, 20));
		
		manageUsers = new JButton("Manage Users");
		manageUsers.setFont(new Font("", Font.PLAIN, 20));
		
		home = new JButton("Home");
		home.setFont(new Font("", Font.PLAIN, 20));
		
		layoutComponents();
		
		calculatorTools.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if(listener != null) {
					listener.showCalculatorToolsDialog();
				}
			}
		});
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
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5, 5, 5, 5);
		add(toolsLabel, gc);
		
		gc.gridy++;
		
		gc.weightx = 4;
		gc.weighty = 0.1;
		
		// Add the density label
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5, 5, 5, 5);
		add(calculatorTools, gc);
		
		gc.gridy++;
		
		gc.weightx = 4;
		gc.weighty = 0.1;
		
		// Add the density label
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5, 5, 5, 5);
		add(qcTools, gc);
		
		gc.gridy++;
		
		gc.weightx = 4;
		gc.weighty = 0.1;
		
		// Add the density label
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5, 5, 5, 5);
		add(manageUsers, gc);
		
		gc.gridy++;
		
		gc.weightx = 4;
		gc.weighty = 0.1;
		
		// Add the density label
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5, 5, 5, 5);
		add(home, gc);
		}
	
	public void setListener(ManagerMenuListener listener) {
		this.listener = listener;
		}
}
