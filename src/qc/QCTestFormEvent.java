package qc;
import java.util.EventObject;

public class QCTestFormEvent extends EventObject {
	
	private String componentName;
	private String amount;
	
	public QCTestFormEvent(Object source) {
		super(source);
	}
	
	public QCTestFormEvent(Object source, String componentName, String amount) {
		super(source);
		
		this.componentName = componentName;
		this.amount = amount;
	}

	public String getComponentName() {
		return componentName;
	}

	public String getAmount() {
		return amount;
	}
}