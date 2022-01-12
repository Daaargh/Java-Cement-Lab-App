package SlurryTesting;

import java.util.EventObject;
import java.util.List;

public class TestPanelReportData extends EventObject {
	
	private String temp;
	private String mixRheoYP;
	private String tempRheoYP;
	private String fluidLost;
	private String setTime;
	private String strengthTwelveHrs;
	
	public TestPanelReportData (Object source) {
		super(source);
	}
	
	public TestPanelReportData(Object source, String temp, String mixRheoYP, 
			String tempRheoYP, String fluidLost, String setTime, String strengthTwelveHours) {
		super(source);
		
		this.temp = temp;
		this.mixRheoYP = mixRheoYP;
		this.tempRheoYP = tempRheoYP;
		this.fluidLost = fluidLost;
		this.setTime = setTime;
		this.strengthTwelveHrs = strengthTwelveHrs;
	}

	public String getTemp() {
		return temp;
	}

	public String getMixRheoYP() {
		return mixRheoYP;
	}

	public String getTempRheoYP() {
		return tempRheoYP;
	}

	public String getFluidLost() {
		return fluidLost;
	}

	public String getSetTime() {
		return setTime;
	}

	public String getStrengthTwelveHrs() {
		return strengthTwelveHrs;
	}
}
