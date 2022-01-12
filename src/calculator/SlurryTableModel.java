package calculator;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.CalcRow;

public class SlurryTableModel extends AbstractTableModel {
	
	private List<CalcRow> calcRows;
	
	private String[] colNames = { "Additive", "lbs", "gals", "grams", "mls"};
	
	public SlurryTableModel() {
	}
	
	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}
	
	public void setData(List<CalcRow> calcRows) {
		this.calcRows = calcRows;
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return calcRows.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		CalcRow calcRow = calcRows.get(row);
		
		switch(col) {
		
		case 0:
			return calcRow.getAdditive();
		case 1:
			return String.format("%.2f", calcRow.getLbs());
		case 2:
			return String.format("%.2f", calcRow.getGals());
		case 3:
			return String.format("%.2f", calcRow.getGrams());
		case 4:
			return String.format("%.2f", calcRow.getMls());
		}
		
		return null;
	}
}