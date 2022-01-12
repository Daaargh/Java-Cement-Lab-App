package SlurryTesting;

import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

public class StrengthComboModel extends DefaultComboBoxModel {
	private List<String> strengths;
	
	public StrengthComboModel() {
		List<String> strengths = Arrays.asList("0", "2000", "4000", "6000", "8000", "10000", "12000");
		for(String str : strengths) {
			addElement(str);
			}
		}
}
