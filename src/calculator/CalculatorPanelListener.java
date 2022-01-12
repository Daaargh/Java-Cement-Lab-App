package calculator;
import java.util.EventListener;

public interface CalculatorPanelListener extends EventListener {
	public void insertCementRow(FormEvent e);
	public void insertAdditiveRow(FormEvent e);
	public void insertWaterRow(FormEvent e);
	public void formEventOccurred(FormEvent e);
	public double getCementLbs(String cement);
	public double getCementGals(String cement); 
	public void calculate(double totalGals);
	public void populateAdditiveCombo(String additiveType);
	public void showReportPanel();
	public String getAdditiveState(String additive);
	public double getAdditiveDensity(String additive);
	public double waterGalsRequirement(String name, double totalGals, double totalLbs, double density);
	public double waterLbsRequirement(String name, double waterGals);
	public void setAdditiveCementLbs(double lbsOfCement);
}