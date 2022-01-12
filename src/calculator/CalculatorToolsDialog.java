package calculator;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalculatorToolsDialog extends JDialog {
	private CardLayout cards;
	private AddEditDialog addEditDialog;
	private DeleteDialog deleteDialog;
	private JButton addEditComponents;
	private JButton deleteComponents;
	private JPanel toolbar;
	private JPanel mainPanel;
	
	public CalculatorToolsDialog(JFrame parent) {
		super(parent, "Component Tools", true);
		
		cards = new CardLayout();
		addEditDialog = new AddEditDialog(null);
		deleteDialog = new DeleteDialog(null);
		addEditComponents = new JButton("Add/Edit Components");
		deleteComponents = new JButton("Delete Components");
		toolbar = new JPanel();
		mainPanel = new JPanel(cards);
		
		toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolbar.add(addEditComponents);
		toolbar.add(deleteComponents);
		
		mainPanel.add(addEditDialog, "addEditDialog");
		mainPanel.add(deleteDialog, "deleteDialog");
		
		setLayout(new BorderLayout());
		add(toolbar, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		mainPanel.add(addEditDialog, "addEditDialog");
		mainPanel.add(deleteDialog, "deleteDialog");
		
		addEditComponents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				showAddEditPanel();
			}
		});
		
		deleteComponents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				showDeletePanel();
			}
		});
	}
	
	public void showAddEditPanel() {
		CardLayout cardLayout = (CardLayout) (mainPanel.getLayout());
		cardLayout.show(mainPanel, "addEditDialog");
	}
	
	public void showDeletePanel() {
		CardLayout cardLayout = (CardLayout) (mainPanel.getLayout());
		cardLayout.show(mainPanel, "deleteDialog");
	}
}
