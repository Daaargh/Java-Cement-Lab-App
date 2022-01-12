package qc;

public interface QCTestPanelListener {
	public void clearQCTestArray();
	public void addQCTestTableRow(QCTestFormEvent ev);
	public String getQCTemplateFilePath(String product);
	public void createPendingQCTest(String productName, String filePath, String testNumber);
	public void storePendingQCTest();
}
