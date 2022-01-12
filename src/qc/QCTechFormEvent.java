package qc;

import java.util.EventObject;

public class QCTechFormEvent extends EventObject {
	private int testNumber;
	
	public QCTechFormEvent(Object source) {
		super(source);
	}
	
	public QCTechFormEvent(Object source, int testNumber) {
		super(source);
		
		this.testNumber = testNumber;
	}
	
	public int getTestNumber() {
		return this.testNumber;
	}
}
