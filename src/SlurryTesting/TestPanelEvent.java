package SlurryTesting;

import java.util.EventObject;
import java.util.List;

public class TestPanelEvent extends EventObject {
	
	private String temp;
	private String depth;
	private List<String> mixRheoArraymm;
	private List<String> tempRheoArray;
	private List<String> fluidLossArray;
	private List<String> thickeningArray;
	private List<String> compressiveArray;
	
	public TestPanelEvent (Object source) {
		super(source);
	}
	
	public TestPanelEvent(Object source, String temp, String depth, List<String> mixRheoArray, List<String> tempRheoArray, List<String> fluidLossArray, List<String> thickeningArray, List<String> compressiveArray) {
		super(source);
		
		this.temp = temp;
		this.depth = depth;
		this.mixRheoArraymm = mixRheoArray;
		this.tempRheoArray = tempRheoArray;
		this.fluidLossArray = fluidLossArray;
		this.thickeningArray = thickeningArray;
		this.compressiveArray = compressiveArray;
	}

	public String getTemp() {
		return temp;
	}

	public String getDepth() {
		return depth;
	}

	public List<String> getMixRheoArray() {
		return mixRheoArraymm;
	}

	public List<String> getTempRheoArray() {
		return tempRheoArray;
	}

	public List<String> getFluidLossArray() {
		return fluidLossArray;
	}

	public List<String> getThickeningArray() {
		return thickeningArray;
	}

	public List<String> getCompressiveArray() {
		return compressiveArray;
	}
}
