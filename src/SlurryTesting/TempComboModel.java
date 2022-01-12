package SlurryTesting;

import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

public class TempComboModel extends DefaultComboBoxModel {
	private List<String> temperatures;
	
	public TempComboModel() {
		List<String> temperatures = Arrays.asList("0", "50", "100", "150", "200");
		for(String str : temperatures) {
			addElement(str);
	}

	}
}
