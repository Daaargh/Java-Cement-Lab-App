package qc;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class QCTestButtonPanel extends JPanel {
	private JButton uploadForm;
	private QCTestButtonListener buttonPanelListener;
	
	public QCTestButtonPanel() {
		uploadForm = new JButton("Upload Form");
		
		setLayout(new FlowLayout());
		add(uploadForm);
		
		uploadForm.addActionListener(new ActionListener () {

			public void actionPerformed(ActionEvent arg0) {
				if(buttonPanelListener != null) {
					buttonPanelListener.createPendingQCTest();
					buttonPanelListener.uploadPendingQCTest();
				}
			}
		});
	}
	
	public void setListener(QCTestButtonListener listener) {
		this.buttonPanelListener = listener;
	}
}
