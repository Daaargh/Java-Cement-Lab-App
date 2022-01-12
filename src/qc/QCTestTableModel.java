package qc;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.QCFormTableRow;
import model.QCTemplateTableRow;

public class QCTestTableModel extends AbstractTableModel {
	
	private List<QCFormTableRow> qcRows;
	
	private String[] colNames = { "Component", "Batch Number", "Amount" };
	
	@Override
	public String getColumnName(int column) {
		return colNames[column];
		}
	
	public void setData(List<QCFormTableRow> qcRows) {
		this.qcRows = qcRows;
		}

	@Override
	public int getColumnCount() {
		return 3;
		}

	@Override
	public int getRowCount() {
		return qcRows.size();
		}

	@Override
	public Object getValueAt(int row, int col) {
		QCFormTableRow qcRow = qcRows.get(row);
		
		switch(col) {
		
		case 0:
			return qcRow.getComponentName();
		case 1:
			return qcRow.getBatchNumber();
		case 2:
			return qcRow.getAmount();
			}
		
		return null;
		}
	
	public void setValueAt(Object value, int row, int column) {
		if(column == 1) {
    		qcRows.get(row).setBatchNumber((String)value);
    		}
		}
	
	public boolean isCellEditable(int row, int col) {
		if(col == 1) {
			return true;
			}
		return false;
		}
	}