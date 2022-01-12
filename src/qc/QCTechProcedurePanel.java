package qc;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class QCTechProcedurePanel extends JPanel {
	private JTextArea procedure;
	
	public QCTechProcedurePanel() {
		procedure = new JTextArea();
		procedure.setLineWrap(true);
		procedure.setWrapStyleWord(true);
		procedure.setEditable(false);
		setLayout(new BorderLayout());
		add(new JScrollPane(procedure), BorderLayout.CENTER);
		Border innerBorder = BorderFactory.createTitledBorder("Quantity Calculator");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 0, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
	}
	
	public void setTestProcedure(String testProcedure) {
		procedure.setText(testProcedure);
	}
	
	public void clearTestProcedure() {
		procedure.setText(null);
	}
}
