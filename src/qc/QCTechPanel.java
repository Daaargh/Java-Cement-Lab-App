package qc;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import controller.Controller;

public class QCTechPanel extends JPanel {
	private QCTechPanelListener listener;
	private QCTechProcedurePanel qcTechProcedurePanel;
	private QCTechFormPanel qcTechFormPanel;
	
	public QCTechPanel() {
		qcTechProcedurePanel = new QCTechProcedurePanel();
		qcTechFormPanel = new QCTechFormPanel();
		
		populatePendingTests();
		
		qcTechFormPanel.setListener(new QCTechFormListener() {

			@Override
			public void setTestProcedure(QCTechFormEvent ev) {
				if(listener != null) {
					String testProcedure = listener.getTestProcedure(ev);
					qcTechProcedurePanel.setTestProcedure(testProcedure);
				}
			}
			
			public void saveTestResults(ResultsEvent rev) {
				if(listener != null) {
					listener.saveTestResults(rev);
					listener.storeCompletedQCTest();
				}

				qcTechFormPanel.clearResultsText();
				qcTechProcedurePanel.clearTestProcedure();
				populatePendingTests();
			}

			@Override
			public void getCurrentTestPath(int testNumber) {
				String currentTestPath = null;
				if(listener != null) {
					currentTestPath = listener.getCurrentTestPath(testNumber);

				}
				qcTechFormPanel.setCurrentFilePath(currentTestPath);
				
			}
			
		});
		setLayout(new BorderLayout());
		add(qcTechFormPanel, BorderLayout.WEST);
		add(qcTechProcedurePanel, BorderLayout.CENTER);
	}
	
	public void populatePendingTests() {
		/* Calls on the controller class
		 * to retrieve a String array list of pending
		 * tests.  This is then sent to the
		 * qcTechFormPanel where it is used 
		 * to populate the pending tests combo box.
		 */
		
		ArrayList<ArrayList<String>> pendingTests = new ArrayList<ArrayList<String>>();

		if(listener != null) {
			pendingTests = listener.getPendingTests();
		}

		qcTechFormPanel.populatePendingTests(pendingTests);
	}
	
	public void setListener(QCTechPanelListener listener) {
		this.listener = listener;
	}
}
