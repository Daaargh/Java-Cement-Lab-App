package SlurryTesting;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

public class ThickeningTimeTable extends JTable {

	public ThickeningTimeTable(ThickeningTableModel thickeningModel) {
		this.setModel(thickeningModel);
		for(int i = 0; i < 2; i++) {
			this.getColumnModel().getColumn(i).setPreferredWidth(55);
		}
		
	    this.setPreferredScrollableViewportSize(
	    		new Dimension(this.getPreferredSize().width, this.getRowHeight() * 4));
	}
	
	public List<String> getCellValues() {
		List<String> thickeningValues = new ArrayList<String>();
		for(int i = 0; i < this.getRowCount(); i++) {
				thickeningValues.add((String)this.getValueAt(i, 1));
			}
		
		return thickeningValues;
	}
}
