package model;

import calculator.MainFrame;

public class QCFormTableRow {
	
	private String componentName;
	private String batchNumber;
	private String amount;
	
	public QCFormTableRow (String componentName, String batchNumber, String amount) {
		
		this.componentName = componentName;
		this.batchNumber = batchNumber;
		this.amount = amount;
	}
	
	public QCFormTableRow (String componentName, String amount) {
		
		this.componentName = componentName;
		this.amount = amount;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	
	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}


	
}
