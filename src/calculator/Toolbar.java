package calculator;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel {
	
	private ToolbarListener toolbarListener;
	private JButton addEditButton;
	private JButton deleteButton;
	private JButton homeButton;
	
	public Toolbar() {
		
		setBorder(BorderFactory.createEtchedBorder());
		
		addEditButton = new JButton("Insert/Update");
		deleteButton = new JButton("Delete");
		homeButton = new JButton("Home");

		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(addEditButton);
		add(deleteButton);
		add(homeButton);
		
		addEditButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(toolbarListener != null) {
					toolbarListener.showAdditiveDialog();
				}
			}
			
		});
		
		deleteButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(toolbarListener != null) {
					toolbarListener.showDeleteDialog();
				}
			}
		});
		
		homeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ev) {
				if(toolbarListener != null) {
					toolbarListener.home();
				}
			}
		});
	}
	
	public void setUserRole(String userRole) {
		if(userRole.equalsIgnoreCase("Manager")) {
			addEditButton.setVisible(true);
			deleteButton.setVisible(true);
			homeButton.setVisible(true);
		}
		else if(userRole.equalsIgnoreCase("Technician")) {
			addEditButton.setVisible(false);
			deleteButton.setVisible(false);
			homeButton.setVisible(true);
		}
	}
	public void setToolbarListener(ToolbarListener listener) {
		this.toolbarListener = listener;
	}
}