package qc;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.QCTemplateTableRow;

public class QCTemplateTableModel extends AbstractTableModel {
	
	private List<QCTemplateTableRow> qcRows;
	
	private String[] colNames = { "Component", "Amount" };
	
	public QCTemplateTableModel() {
	}
	
	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}
	
	public void setData(List<QCTemplateTableRow> qcRows) {
		this.qcRows = qcRows;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return qcRows.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		QCTemplateTableRow qcRow = qcRows.get(row);
		
		switch(col) {
		
		case 0:
			return qcRow.getComponentName();
		case 1:
			return qcRow.getAmount();
		}
		
		return null;
	}
}