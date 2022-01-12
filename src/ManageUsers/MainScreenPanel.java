package ManageUsers;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainScreenPanel extends JPanel {
	private JLabel mainTitleLabel;
	private JButton calculator;
	private JButton reports;
	private JButton qc;
	private JButton manageUsers;
	private JButton logOut;
	private MainScreenListener listener;
	
	public MainScreenPanel() {
		
		mainTitleLabel = new JLabel("Cement Lab App");
		mainTitleLabel.setFont(new Font("", Font.PLAIN, 40));
		
		calculator = new JButton("Slurry Calculator");
		calculator.setFont(new Font("", Font.PLAIN, 20));
		
		reports = new JButton("Slurry Reports");
		reports.setFont(new Font("", Font.PLAIN, 20));
		
		qc = new JButton("QC");
		qc.setFont(new Font("", Font.PLAIN, 20));
		
		manageUsers = new JButton("Manager Tools");
		manageUsers.setFont(new Font("", Font.PLAIN, 20));
		
		logOut = new JButton("Log Out");
		logOut.setFont(new Font("", Font.PLAIN, 20));
		
		calculator.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ev) {
				if(listener != null) {
					listener.showCalculator();
					}
				}
			});
		
		reports.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ev) {
				if(listener != null) {
					listener.showReportSearch();
					}
				}
			});
		
		qc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ev) {
				if(listener != null) {
					listener.showQC();
					}
				}
			});
		
		manageUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if(listener != null) {
					listener.showManagerTools();
				}
			}
		});
		
		logOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(listener != null) {
					listener.logOut();
					}
				}
			});
		
		
		Dimension dim = new Dimension(calculator.getPreferredSize());
		reports.setPreferredSize(dim);
		qc.setPreferredSize(dim);
		manageUsers.setPreferredSize(dim);
		
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
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5, 5, 5, 5);
		add(mainTitleLabel, gc);
		
		gc.gridy++;
		
		gc.weightx = 4;
		gc.weighty = 0.1;
		
		// Add the density label
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5, 5, 5, 5);
		add(calculator, gc);
		
		gc.gridy++;
		
		gc.weightx = 4;
		gc.weighty = 0.1;
		
		// Add the density label
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5, 5, 5, 5);
		add(reports, gc);
		
		gc.gridy++;
		
		gc.weightx = 4;
		gc.weighty = 0.1;
		
		// Add the density label
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5, 5, 5, 5);
		add(qc, gc);
		
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
		add(logOut, gc);
	}
	
	public void setUserRole(String userRole) {
		if(userRole.equalsIgnoreCase("Manager")) {
			calculator.setVisible(true);
			reports.setVisible(true);
			qc.setVisible(true);
			manageUsers.setVisible(true);
			logOut.setVisible(true);
		}
		else if(userRole.equalsIgnoreCase("Technician")) {
			calculator.setVisible(true);
			reports.setVisible(true);
			qc.setVisible(true);
			manageUsers.setVisible(false);
			logOut.setVisible(true);
		}
		
		else if(userRole.equalsIgnoreCase("Warehouse")) {
			calculator.setVisible(false);
			reports.setVisible(false);
			qc.setVisible(true);
			manageUsers.setVisible(false);
			logOut.setVisible(true);
		}
	}
	
	public void setListener(MainScreenListener listener) {
		this.listener = listener;
	}
}
