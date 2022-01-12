package SlurryTesting;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RheologyTableModel extends DefaultTableModel {
	private Object[] rheoValues = new String[18];
	private String[] columnNames = { "3", "6", "30", "60", "100", "200", "300", "PV", "yP"};
	//private List<Double> rheoUp = new ArrayList<Double>();
	public RheologyTableModel() {
		this.setNumRows(2);
		this.setColumnIdentifiers(columnNames);
		rheoValues[15] = "";
		rheoValues[16] = "";
		rheoValues[17] = "";
	}
	/*
	public void setValueAt(Double aValue, int rowIndex, int columnIndex) {
		if(rowIndex == 0) {
			rheoUp.add(columnIndex, aValue);
		}
        if(rowIndex == 0 && columnIndex == 6) {
        	rheoUp.
        	setValueAt((Double.valueOf((String)getValueAt(0, 6))) - (Double.valueOf((String)getValueAt(0, 4))), 0, 7);
        }
        else if(rowIndex == 0 && columnIndex == 4 && getValueAt(0, 6) != null) {
        	setValueAt((Double.valueOf((String)getValueAt(0, 6))) - (Double.valueOf((String)getValueAt(0, 4))), 0, 7);
        }
        
        this.getValueAt(rowIndex, columnIndex);
        
        fireTableCellUpdated(rowIndex, columnIndex);
        
        fireTableCellUpdated(rowIndex, columnIndex); 
	} */
	public int getColumnCount() {
		//Overridden method as the default is naive
		return columnNames.length;
		
	}
	
	@Override
    public Object getValueAt(int row, int col){
		if(row == 0) {
			return rheoValues[col];
			
		}
		else {
			return rheoValues[col + 9];
		}
		//return rheoUp.get(col);
    }
	
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if(col > 6) {
            return false;
        } 
        else if(col == 6 && row == 1) {
        	return false;
        }
        else {
            return true;
        }
    }

    ////https://stackoverflow.com/questions/39066012/index-out-of-bound-exception-when-setting-value-to-jtable
    public void setValueAt(Object value, int row, int column) {
    	if(row == 0) {
    		rheoValues[column] = (String)value;
    		try { Double.valueOf((String) (rheoValues[column]));
    		} catch (NumberFormatException nfe1) {
    			JOptionPane.showMessageDialog(null, "Please enter a valid number!");
    			rheoValues[column] = "";
    			}
    		}
    	if(row == 1) {
    		rheoValues[column + 9] = (String)value;

    		try { Double.valueOf((String) (rheoValues[column + 9]));
    		} catch (NumberFormatException nfe1) {
    			JOptionPane.showMessageDialog(null, "Please enter a valid number!");
				rheoValues[column + 9] = "";
				}
    		}
    	
    	if(!(rheoValues[4] == null) && 
				!((String) rheoValues[4]).isEmpty() && 
				!(rheoValues[6] == null) && 
				!(((String) rheoValues[6]).isEmpty()) && 
				!(rheoValues[13] == null) && 
				!(((String) rheoValues[13]).isEmpty())) {
        		double PV300 = Double.valueOf((String) rheoValues[6]);
        		double PV100 = (Double.valueOf((String) rheoValues[4]) + Double.valueOf((String) rheoValues[13])) / 2;
        		double PV = (PV300 - PV100) * 1.5;
            	rheoValues[7] = String.valueOf((PV));

            	fireTableCellUpdated(0, 7);
    		}
    	else {
    		rheoValues[7] = String.valueOf("");
    		fireTableCellUpdated(0, 7);
    	}
    	
    	if(!(rheoValues[6] == null) && 
				!((String) rheoValues[6]).isEmpty() && 
				!(rheoValues[7] == null) && 
				!(((String) rheoValues[7]).isEmpty())) {
        		double PV300 = Double.valueOf((String) rheoValues[6]);
        		double PV = (Double.valueOf((String) rheoValues[7]));
        		double yP = (PV300 - PV);
        		rheoValues[8] = String.valueOf(yP);

        		fireTableCellUpdated(0, 8);
        		}    	
    	else {
    		rheoValues[8] = String.valueOf("");
    		fireTableCellUpdated(0, 8);
    	}

    }
    public String[] getRheoValues() {
    	return (String[]) rheoValues;
    }
}
