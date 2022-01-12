package SlurryTesting;

import java.util.EventListener;

public interface TestPanelListener extends EventListener {
	public void generateReport(TestPanelEvent ev);
}
