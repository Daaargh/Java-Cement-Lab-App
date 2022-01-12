package SlurryTesting;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FluidLossTable extends JTable {
	
	public FluidLossTable(FluidLossModel fluidLossModel) {
		this.setModel(fluidLossModel);
			
		this.getColumnModel().getColumn(0).setMaxWidth(70);
		this.getColumnModel().getColumn(1).setPreferredWidth(55);
		this.getColumnModel().getColumn(2).setPreferredWidth(115);
	
	    this.setPreferredScrollableViewportSize(
	    		new Dimension(this.getPreferredSize().width, this.getRowHeight() * 1));
	}
	
	public List<String> getCellValues() {
		List<String> fluidLossValues = new ArrayList<String>();
		for(int i = 0; i < this.getColumnCount(); i++) {
			fluidLossValues.add((String)this.getValueAt(0, i));
			}
	//	for(int i = 0; i < fluidLossValues.size(); i++) {
		//	System.out.println(fluidLossValues.get(i));
		//}
		return fluidLossValues;
	}

}