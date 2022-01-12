package SlurryTesting;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CompStrengthTable extends JTable {
	private String[] colHeadings = {"hours", "Strength (psi)"};
	private int numRows = 3;
	private DefaultTableModel aModel; 
	
	public CompStrengthTable(CompressiveTableModel compressiveTable) {
		this.setModel(compressiveTable);
		
		for(int i = 0; i < colHeadings.length; i++) {
			this.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
		
	    this.setPreferredScrollableViewportSize(
	    		new Dimension(this.getPreferredSize().width, this.getRowHeight() * 3));
	}
	
	public List<String> getCellValues() {
		List<String> compressiveValues = new ArrayList<String>();
		for(int i = 0; i < this.getRowCount(); i++) {
				compressiveValues.add((String)this.getValueAt(i, 1));
			}
		
		return compressiveValues;
	}
}