package SlurryTesting;

import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

public class SetTimeComboModel extends DefaultComboBoxModel {
	private List<String> setTimes;
	
	public SetTimeComboModel() {
		List<String> setTimes = Arrays.asList("0", "180", "360,", "540", "720", "900", "1080");
		for(String str : setTimes) {
			addElement(str);
			}
		}
}
