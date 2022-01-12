package reportSearch;

import java.util.EventObject;

public class ReportSearchEvent extends EventObject {
	private String reportQuery;
	
	public ReportSearchEvent (Object source) {
		super(source);
	}
	public ReportSearchEvent(Object source, String reportQuery) {
		super(source);
		
		this.reportQuery = reportQuery;
	}
	public String getReportQuery() {
		return reportQuery;
	}
}
