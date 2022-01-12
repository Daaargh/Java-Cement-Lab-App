package SlurryTesting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class CompressiveTableModel extends DefaultTableModel {
	
	private String[] hoursColumn = {"6", "12", "24"};
	private String[] psiColumn = new String[3];
	private String[] columnNames = {"Hours", "Strength (psi)"};
	
	public CompressiveTableModel() {
		this.setNumRows(psiColumn.length);
		this.setColumnIdentifiers(columnNames);
	}
	
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
    public Object getValueAt(int row, int col) {
		if(col == 0) {
			return hoursColumn[row];
		}
		else {
			return psiColumn[row];
		}
		
    }
	
    public boolean isCellEditable(int row, int col) {
        if (col == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void setValueAt(Object value, int row, int column) {
    	psiColumn[row] = String.valueOf(value);
    	}	
}