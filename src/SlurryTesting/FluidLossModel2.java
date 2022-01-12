package SlurryTesting;

import java.util.Arrays;

import javax.swing.table.DefaultTableModel;

public class FluidLossModel2 extends DefaultTableModel {
	
	//private Object[] test = {new String(""), new String(""), new String(""), new Boolean(false)};
	private Object[] test = {new String(""), new String(""), new String(""), new Boolean(false)};
	private String[] columnNames = { "Fluid Lost", "Time", "Calculated Loss"};
	
	public FluidLossModel2() {
		this.setNumRows(1);
		this.setColumnIdentifiers(columnNames);
		

	}
	
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
    public Object getValueAt(int row, int col) {
		return test[col];
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
    		test[0] = String.valueOf(value);
    	}
    	if(column == 1) {
    		test[1] = String.valueOf(value);
    	}
    	
		if( !((String) test[0]).isEmpty() && !((String) test[1]).isEmpty()) {
			double fluidLost = Double.valueOf((String) test[0]);
			double time = Double.valueOf((String) test[1]);
			if(time >= 30) {
				test[2] = String.valueOf(2 * fluidLost);
				fireTableCellUpdated(0, 2);
			}
			else if(time < 30) {
				test[2] = String.format("%.2f", ( (2 * fluidLost ) * (5.477 / Math.sqrt(time))));
				fireTableCellUpdated(0, 2);
				}
		}
		
		else {
			test[2] = String.valueOf("");
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
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    }
