package qc;

public interface QCTechFormListener {
	public void setTestProcedure(QCTechFormEvent ev);
	public void getCurrentTestPath(int testNumber);
	public void saveTestResults(ResultsEvent rev);
}
