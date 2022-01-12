package ManageUsers;

public interface ManageUsersDialogListener {
	public void addUser(AddUserEvent ev);
	public void deleteUser(String userName);
	public void resetPassword(String userName);
	public void setUserRole(UserRolePanelEvent ev);
	
	public void populateDeleteUsersCombo();
	public void populateResetPasswordUsersCombo();
	public void populateChangeRoleUsersCombo();
}
