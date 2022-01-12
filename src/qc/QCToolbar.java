package qc;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class QCToolbar extends JPanel {
	
	private QCToolbarListener toolbarListener;
	private JButton createTemplateButton;
	private JButton createFormButton;
	private JButton deleteTemplateButton;
	private JButton techButton;
	private JButton homeButton;

	
	public QCToolbar() {
		
		setBorder(BorderFactory.createEtchedBorder());
		
		createTemplateButton = new JButton("Create Template");
		createFormButton = new JButton("Create Form");
		deleteTemplateButton = new JButton("Delete Template");
		techButton = new JButton("Pending Tests");
		homeButton = new JButton("Home");

		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(createTemplateButton);
		add(deleteTemplateButton);
		add(createFormButton);
		add(techButton);
		add(homeButton);

		
		createTemplateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(toolbarListener != null) {
					toolbarListener.showQCTemplatePanel();
				}
			}
			
		});
		
		createFormButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(toolbarListener != null) {
					toolbarListener.showQCTestPanel();
				}
			}
			
		});
		
		deleteTemplateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(toolbarListener != null) {
					toolbarListener.showDeleteQCTemplatePanel();
				}
			}
			
		});
		
		techButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(toolbarListener != null) {
					toolbarListener.showTechPanel();
				}
			}
			
		});
		
		homeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(toolbarListener != null) {
					toolbarListener.home();
					}
				}
			});	
		}
	
	public void setUserRole(String userRole) {
		if(userRole.equalsIgnoreCase("Manager")) {
			createTemplateButton.setVisible(true);
			createFormButton.setVisible(true);
			deleteTemplateButton.setVisible(true);
			techButton.setVisible(true);
			homeButton.setVisible(true);
		}
		
		else if(userRole.equalsIgnoreCase("Technician")) {
			createTemplateButton.setVisible(false);
			createFormButton.setVisible(false);
			deleteTemplateButton.setVisible(false);
			techButton.setVisible(false);
			homeButton.setVisible(true);
		}
		
		else if(userRole.equalsIgnoreCase("Warehouse")) {
			createTemplateButton.setVisible(false);
			createFormButton.setVisible(false);
			deleteTemplateButton.setVisible(false);
			techButton.setVisible(false);
			homeButton.setVisible(true);
		}
	}
	
	public void setToolbarListener(QCToolbarListener listener) {
		this.toolbarListener = listener;
	}
}