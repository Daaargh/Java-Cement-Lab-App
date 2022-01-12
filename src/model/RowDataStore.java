package model;
import java.util.ArrayList;
import java.util.Iterator;

public class RowDataStore {
	
	private ArrayList<CalcRow> calcRows;
	private String cement;
	private String waterType;
	
	public RowDataStore() {
		cement = null;
		waterType = null;
		calcRows = new ArrayList<CalcRow>();
		
	}
	
	public void insertCementRow(CalcRow cementRow) {
		if(cement == null) {
			cement = cementRow.getAdditive();
			calcRows.add(0, cementRow);
		}
		
		else {
			for(Iterator<CalcRow> itr = calcRows.iterator(); itr.hasNext();) {
				CalcRow calcRow = itr.next();
				if(calcRow.getAdditive() == cement) {
					itr.remove();// listOfPhones.remove(phone); // wrong again itr.remove(); // right call } }
				}
			}
			cement = cementRow.getAdditive();
			calcRows.add(0, cementRow);
		}
	}


		
	
	public void insertAdditiveRow(CalcRow additiveRow) {
		if(waterType != null) {
			calcRows.add(calcRows.size() -1, additiveRow);
		}
		
		else {
			calcRows.add(additiveRow);
		}

	}
	
	public void insertWaterRow(CalcRow waterRow) {
		if(waterType == null) {
			waterType = waterRow.getAdditive();
			calcRows.add(calcRows.size(), waterRow);
		}
		
		else {
			for(CalcRow calcRow : calcRows) {
				if(calcRow.getAdditive() == waterType) {
					calcRows.remove(calcRow);
					calcRows.add(calcRows.size(), waterRow);
					waterType = waterRow.getAdditive();
					}
				}
			}
		}
	
	public void clearLabQuantities() {
		for(CalcRow calcRow : calcRows) {
			calcRow.setLbs(0);
			calcRow.setGals(0);
			calcRow.setGrams(0);
			calcRow.setMls(0);
		}
	}
	
	
	public void addCalcRow(CalcRow calcRow) {
		calcRows.add(calcRow);
	}
	
	public ArrayList<CalcRow> getCalculatorRows() {
		return calcRows;
	}
	
	public ArrayList<Double> removeComponentRow(int row) {
		ArrayList<Double> componentData = new ArrayList<Double>();
		double componentLbs = calcRows.get(row).getLbs();
		double componentGals = calcRows.get(row).getGals();
		componentData.add(componentLbs);
		componentData.add(componentGals);
		calcRows.remove(row);
		return componentData;
		}
	
	}
