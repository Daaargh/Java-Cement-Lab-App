package SlurryTesting;

import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;


public class FluidComboModel extends DefaultComboBoxModel {
	private List<String> fluidLosses;
	
	public FluidComboModel() {
		List<String> fluidLosses = Arrays.asList("0", "10", "20", "30", "40", "50");
		for(String str : fluidLosses) {
			addElement(str);
			}
		}
}
