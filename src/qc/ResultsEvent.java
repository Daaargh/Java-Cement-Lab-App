package qc;

import java.util.EventObject;

public class ResultsEvent extends EventObject {
	private String results;
	private String passOrFail;
	private String filePath;
	private String product;
	private int testNumber;
	
	public ResultsEvent (Object source) {
		super(source);
	}
	
	public ResultsEvent(Object source, String results, String passOrFail, String filePath, String product, int testNumber) {
		super(source);
		
		this.results = results;
		this.passOrFail = passOrFail;
		this.filePath = filePath;
		this.product = product;
		this.testNumber = testNumber;
	}

	public String getResults() {
		return results;
	}

	public String getPassOrFail() {
		return passOrFail;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public String getProduct() {
		return product;
	}
	
	public int getTestNumber() {
		return testNumber;
	}
}
