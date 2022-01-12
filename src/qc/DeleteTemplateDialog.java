package qc;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DeleteTemplateDialog extends JDialog {
	private DeleteTemplateListener listener;
	private JLabel templateLabel;
	private DefaultComboBoxModel comboModel;
	private JComboBox templateCombo;
	private JButton deleteButton;
	private JButton cancelButton;
	
	public DeleteTemplateDialog(JFrame parent) {
		super(parent, "Delete Template", true);
		templateLabel = new JLabel("Template");
		comboModel = new DefaultComboBoxModel();
		templateCombo = new JComboBox();
		templateCombo.setModel(comboModel);
		deleteButton = new JButton("Delete");
		cancelButton = new JButton("Cancel");
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String templateName = (String)templateCombo.getSelectedItem();
				if(listener != null) {
					listener.deleteTemplate(templateName);
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		setSize(250, 250);
		
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
		gc.insets = new Insets(16, 0, 0, 10);
		add(templateLabel, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(15, 0, 0, 0);
		add(templateCombo, gc);
		
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 0);
		add(deleteButton, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(cancelButton, gc);
	}
	
	public void populateTemplateCombo(ArrayList<String> templates) {
		comboModel.removeAllElements();
		for(String template : templates) {
			comboModel.addElement(template);
		}
	}
	
	public void setListener(DeleteTemplateListener listener) {
		this.listener = listener;
	}
}
