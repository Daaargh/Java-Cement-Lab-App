package qc;

import java.util.ArrayList;

public interface QCTechPanelListener {
	public String getTestProcedure(QCTechFormEvent ev);
	public void saveTestResults(ResultsEvent rev);
	public void storeCompletedQCTest();
	public String getCurrentTestPath(int testNumber);
	public ArrayList<ArrayList<String>> getPendingTests();

}
