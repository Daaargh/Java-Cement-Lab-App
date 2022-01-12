package ManageUsers;

import java.util.EventObject;

public class AddUserEvent extends EventObject {
	private String userName;
	private String userRole;
	
	public AddUserEvent (Object source) {
		super(source);
	}
	
	public AddUserEvent(Object source, String userName, String userRole) {
		super(source);
		
		this.userName = userName;
		this.userRole = userRole;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserRole() {
		return userRole;
	}
}
