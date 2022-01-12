package SlurryTesting;

import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

public class RheoComboModel extends DefaultComboBoxModel {
	private List<String> yieldPoints;
	
	public RheoComboModel() {
		List<String> yieldPoints = Arrays.asList("0", "5", "10", "15", "20");
		for(String str : yieldPoints) {
			addElement(str);
			}
		}
	}
