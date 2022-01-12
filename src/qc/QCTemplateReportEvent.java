package qc;
import java.util.EventObject;

public class QCTemplateReportEvent extends EventObject {
	
	private String productName;
	private String testText;
	
	public QCTemplateReportEvent(Object source) {
		super(source);
	}
	
	public QCTemplateReportEvent(Object source, String productName, String testText) {
		super(source);
		
		this.productName = productName;
		this.testText = testText;
	}

	public String getProductName() {
		return productName;
	}

	public String getTestText() {
		return testText;
	}
}