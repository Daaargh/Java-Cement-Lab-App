package calculator;

import java.util.EventObject;

public class DialogEvent extends EventObject {

	private String name;
	private double sg;
	private int type;
	private int state;
	private double lbs;
	
	public DialogEvent(Object source) {
		super(source);
	}
	
	public DialogEvent(Object source, String name, double sg, int type, int state) {
		super(source);
		
		this.name = name;
		this.sg = sg;
		this.type = type;
		this.state = state;
	}
	
	public DialogEvent(Object source, String name, double sg) {
		super(source);
		
		this.name = name;
		this.sg = sg;
	}
	
	public DialogEvent(Object source, String name, double sg, double lbs) {
		super(source);
		
		this.name = name;
		this.sg = sg;
		this.lbs = lbs;
	}
	
	public DialogEvent(Object source, String name) {
		super(source);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public double getSg() {
		return sg;
	}
	
	public double getLbs() {
		return lbs;
	}

	public int getType() {
		return type;
	}
	
	public int getState() {
		return state;
	}
}