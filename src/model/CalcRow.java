package model;

import calculator.MainFrame;

public class CalcRow {
	
	private String additive;
	private double lbs;
	private double gals;
	private double grams;
	private double mls;
	private String componentType;
	private double quantity;
	private String state;
	private double density;
	
	public CalcRow (String additive, double lbs, double gals, String componentType) {
		
		this.additive = additive;
		this.lbs = lbs;
		this.gals = gals;
		this.componentType = componentType;
	}
	public CalcRow (String additive, double lbs, double gals, String componentType, double quantity, String state, double density) {
		
		this.additive = additive;
		this.lbs = lbs;
		this.gals = gals;
		this.componentType = componentType;
		this.quantity = quantity;
		this.state = state;
		this.density = density;
	}

	public double getGrams() {
		return grams;
		}

	public void setGrams(double grams) {
		this.grams = grams;
		}

	public double getMls() {
		return mls;
		}

	public void setMls(double mls) {
		this.mls = mls;
		}

	public String getAdditive() {
		return additive;
		}

	public void setAdditive(String additive) {
		this.additive = additive;
		}
	
	public double getLbs() {
		return lbs;
		}
	
	public void setLbs(double lbs) {
		this.lbs = lbs;
		}
	
	public double getGals() {
		return gals;
		}
	
	public void setGals(double gals) {
		this.gals = gals;
		}
	
	public String getState() {
		return this.state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getComponentType() {
		return this.componentType;
	}
	
	public double getQuantity() {
		return this.quantity;
	}
	
	public double getDensity() {
		return this.density;
	}
	
}
