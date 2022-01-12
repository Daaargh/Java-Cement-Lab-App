package SlurryTesting;

import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FluidLossModel extends DefaultTableModel {
	
	//private Object[] test = {new String(""), new String(""), new String(""), new Boolean(false)};
	private String[] fluidLossValues = new String[3];
	private String[] columnNames = { "Fluid Lost", "Time", "Calculated Loss"};
	
	public FluidLossModel() {
		this.setNumRows(1);
		this.setColumnIdentifiers(columnNames);
	}
	
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
    public Object getValueAt(int row, int col) {
		return fluidLossValues[col];
    }
	
    public boolean isCellEditable(int row, int col) {
        if (col == 2) {
            return false;
        } else {
            return true;
        }
    }

    ////https://stackoverflow.com/questions/39066012/index-out-of-bound-exception-when-setting-value-to-jtable
    public void setValueAt(Object value, int row, int column) {
    	if(column == 0) {
    		fluidLossValues[0] = String.valueOf(value);
    	}
    	if(column == 1) {
    		fluidLossValues[1] = String.valueOf(value);
    	}
    	
		if( !(fluidLossValues[0] == null) && !fluidLossValues[0].isEmpty() && 
				!(fluidLossValues[1] == null) && !(fluidLossValues[1].isEmpty())) {
			try {
				double fluidLost = Double.valueOf((String) fluidLossValues[0]);
				double time = Double.valueOf((String) fluidLossValues[1]);
				
				if(time >= 30) {
					fluidLossValues[2] = String.valueOf(2 * fluidLost) + " cc";
					fireTableCellUpdated(0, 2);
				}
				else if(time < 30) {
					fluidLossValues[2] = String.format("%.2f", ( (2 * fluidLost ) * (5.477 / Math.sqrt(time)))) + " cc";
					fireTableCellUpdated(0, 2);
					}
			} catch (NumberFormatException nfe1) {
    			JOptionPane.showMessageDialog(null, "Please enter only valid numbers!");
    			fluidLossValues = new String[3];
    			fireTableRowsUpdated(0, 0);
			}
			}
		
		else {
			fluidLossValues[2] = String.valueOf("");
			fireTableCellUpdated(0, 2);
			}
    	
/*
    	if(Double.valueOf((String) test[0]) == 30) {
    		double fluidLost = Double.valueOf((String) test[0]);
    		test[2] = String.valueOf(fluidLost * 2);
    		fireTableCellUpdated(0, 2);
    		}
    	else if(Double.valueOf((String) test[0]) < 30){
    			test[2] = String.valueOf("");
    			fireTableCellUpdated(0, 2);
    			}
    		}

    	
    	if( (Boolean)test[3] == true ) {
    		if( !((String) test[0]).isEmpty() && !((String) test[1]).isEmpty()) {
    			double fluidLost = Double.valueOf((String) test[0]);
    			double time = Double.valueOf((String) test[1]);
    			test[2] = String.valueOf( (2 * fluidLost ) * (5.477 / Math.sqrt(time)));
    			fireTableCellUpdated(0, 2);
    		}
    		
    		else {
    			test[2] = String.valueOf("");
    			fireTableCellUpdated(0, 2);
    		}
    	}
    	/*
    	if(column == 0) {
    		test[0] = (String)value;
    		if( !((String) test[0]).isEmpty() && !((String) test[1]).isEmpty() && (Boolean)test[3] == false) {
        		double fluidLost = Double.valueOf((String) test[0]);
        		double time = Double.valueOf((String) test[1]);
        		test[2] = String.valueOf(fluidLost * 2);
        		fireTableCellUpdated(0, 2);
    		}
    		else if(((String) test[1]).isEmpty() || ((String) test[0]).isEmpty() && (Boolean)test[3] == false)
    		{
    			test[2] = String.valueOf("");
    			fireTableCellUpdated(0, 2);
    		}
    	}
    	if((Boolean)test[3] == false) {
    		if(column == 0) {
        		test[0] = (String)value;
        		if(!((String) test[0]).isEmpty()) {
        			double fluidLost = Double.valueOf((String) test[0]);
            		test[2] = String.valueOf(fluidLost * 2);
            		fireTableCellUpdated(0, 2);
        		}
        		else {
        			test[2] = String.valueOf(value);
        			}
        		}
    		}
    	
    		// If neither string is empty and boolean is false
    		/*if(!((String) test[1]).isEmpty() &&
    				!((String) test[0]).isEmpty() &&
    					(Boolean)test[3] == false) {
        		double fluidLost = Double.valueOf((String) test[0]);
        		double time = Double.valueOf((String) test[1]);
        		test[2] = String.valueOf(fluidLost * 2);
        		fireTableCellUpdated(0, 2); 
    		}
    		// if one or other string is empty and boolean is false
    		else if((((String) test[0]).isEmpty() ||
    					((String) test[1]).isEmpty()) &&
    						(Boolean)test[3] == false) {
    			test[2] = String.valueOf("");
    			fireTableCellUpdated(0, 2);
    			} 
    		
    
    	if(column == 3) {
    		test[3] = value;
    		System.out.println("Fartins col 3");
    	}
    	
    	if((test[0] != "" && test[1] != "") && (test[3] == Boolean.valueOf(false))) {
    		System.out.println("Fartins");
    		double fluidLost = Double.valueOf((String) test[0]);
    		double time = Double.valueOf((String) test[1]);
    		test[2] = String.valueOf(fluidLost * 2);
    		fireTableCellUpdated(0, 2);
    	} */

    	}
    public String[] getFluidLossValues() {
    	return this.fluidLossValues;
    }
    }
