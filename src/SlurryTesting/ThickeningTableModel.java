package SlurryTesting;

import java.util.Arrays;

import javax.swing.table.DefaultTableModel;

public class ThickeningTableModel extends DefaultTableModel {
	
	//private Object[] test = {new String(""), new String(""), new String(""), new Boolean(false)};
	private String[] columnBC = {"30", "50", "70", "100"};
	private String[] columnMinutes = new String[4];

	private String[] columnNames = {"Bc", "Minutes"};
	
	public ThickeningTableModel() {
		this.setNumRows(columnBC.length);
		this.setColumnIdentifiers(columnNames);
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
    public Object getValueAt(int row, int col) {
		if(col == 0) {
			return columnBC[row];
		}
		else {
			return columnMinutes[row];
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
    	columnMinutes[row] = String.valueOf(value);
    	}
    
    public String[] getMinutes() {
    	return this.columnMinutes;
    }
    }
