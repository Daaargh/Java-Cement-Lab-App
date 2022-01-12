package qc;
import java.util.EventObject;

public class QCTemplateFormEvent extends EventObject {
	
	private String componentName;
	private String amount;
	
	public QCTemplateFormEvent(Object source) {
		super(source);
	}
	
	public QCTemplateFormEvent(Object source, String componentName, String amount) {
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