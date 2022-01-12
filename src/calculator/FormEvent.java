package calculator;
import java.util.EventObject;

public class FormEvent extends EventObject {
	
	private String additive;
	private double lbs;
	private double gals;
	private String type;
	private double quantity;
	private String state;
	private double density;
	
	public FormEvent (Object source) {
		super(source);
	}
	
	public FormEvent(Object source, String additive, double lbs, double gals, String type) {
		super(source);
		
		this.additive = additive;
		this.lbs = lbs;
		this.gals = gals;
		this.type = type;
	}
	
	public FormEvent(Object source, String additive, double lbs, double gals, String type, double quantity, String state, double density) {
		super(source);
		
		this.additive = additive;
		this.lbs = lbs;
		this.gals = gals;
		this.type = type;
		this.quantity = quantity;
		this.state = state;
		this.density = density;
	}

	public String getAdditive() {
		return additive;
	}

	public double getLbs() {
		return lbs;
	}

	public double getGals() {
		return gals;
	}
	
	public String getType() {
		return type;
	}
	
	public double getQuantity() {
		return this.quantity;
	}
	
	public String getState() {
		return this.state;
	}
	
	public double getDensity() {
		return this.density;
	}
}