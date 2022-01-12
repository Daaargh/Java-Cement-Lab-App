package SlurryTesting;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RheoTable extends JTable {
	//private String[] colHeadings = {"3", "6", "30", "60", "100", "200", "300", "PV", "yP"};
	//private int numRows = 2 ;
	//private DefaultTableModel aModel; 

	public RheoTable(RheologyTableModel mixRheoModel) {
		this.setModel(mixRheoModel);
	//	aModel = new DefaultTableModel(numRows, 9) ;
	//	aModel.setColumnIdentifiers(colHeadings);
	//	this.setModel(aModel);
	//	for(int i = 0; i < colHeadings.length; i++) {
	//		this.getColumnModel().getColumn(i).setPreferredWidth(35);
		for(int i = 0; i < mixRheoModel.getColumnCount(); i++) {
			this.getColumnModel().getColumn(i).setPreferredWidth(35);
		}
		
		
	    this.setPreferredScrollableViewportSize(
	    		new Dimension(this.getPreferredSize().width, this.getRowHeight() * 2));
	
	    this.setCellSelectionEnabled(true);
	    this.putClientProperty("terminateEditOnFocusLost", true);
	    /*
	    Action action = new AbstractAction()
	    {
	        public void actionPerformed(ActionEvent e)
	        {
	            TableCellListener tcl = (TableCellListener)e.getSource();
	            if(tcl.getRow() == 0 && tcl.getColumn() == 6) {
	            	setValueAt((Double.valueOf((String)getValueAt(0, 6))) - (Double.valueOf((String)getValueAt(0, 4))), 0, 7);
	            }
	            else if(tcl.getRow() == 0 && tcl.getColumn() == 4 && getValueAt(0, 6) != null) {
	            	setValueAt((Double.valueOf((String)getValueAt(0, 6))) - (Double.valueOf((String)getValueAt(0, 4))), 0, 7);
	            }
	            System.out.println(e.getSource());
	            System.out.println("Row   : " + tcl.getRow());
	            System.out.println("Column: " + tcl.getColumn());
	            System.out.println("Old   : " + tcl.getOldValue());
	            System.out.println("New   : " + tcl.getNewValue());
	        }
	    }; 

	    TableCellListener tcl = new TableCellListener(this, action); */
	    
	}
	/*
	public List<String> getCellValues() {
		List<String> rheoValues = new ArrayList<String>();
		for(int i = 0; i < this.getRowCount(); i++) {
			for(int j = 0; j <colHeadings.length; j++) {
				rheoValues.add((String)this.getValueAt(i, j));
			}
		}
		return rheoValues;
	}
	*/
	
	public List<String> getCellValues() {
		List<String> rheoValues = new ArrayList<String>();
		for(int i = 0; i < this.getRowCount(); i++) {
			for(int j = 0; j < getModel().getColumnCount(); j++) {
				rheoValues.add((String)this.getValueAt(i, j));
			}
		}
		return rheoValues;
	}
	
	
}