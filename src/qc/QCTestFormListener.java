package qc;

public interface QCTestFormListener {
	public void uploadProductDetails(QCTestFormEvent ev);
	public void clearQCTestArray();
	public void setFilePath(String product);
	public void clearTable();
}
