package calculator;

import java.util.EventObject;

public class LoginPanelEvent extends EventObject {
	private String userName;
	private String password;
	
	public LoginPanelEvent (Object source) {
		super(source);
	}
	
	public LoginPanelEvent(Object source, String userName, String password) {
		super(source);
		
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
}
