package ManageUsers;

import java.util.EventObject;

public class UserRolePanelEvent extends EventObject {
	private String userName;
	private String userRole;
	
	public UserRolePanelEvent (Object source) {
		super(source);
	}
	
	public UserRolePanelEvent(Object source, String userName, String userRole) {
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
