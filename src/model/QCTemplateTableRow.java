package model;

import calculator.MainFrame;

public class QCTemplateTableRow {
	
	private String componentName;
	private String amount;
	
	public QCTemplateTableRow (String componentName, String amount) {
		
		this.componentName = componentName;
		this.amount = amount;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}


	
}
