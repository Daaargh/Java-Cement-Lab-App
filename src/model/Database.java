package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import calculator.MainFrame;

public class Database {
	private Connection con;

	public Database() {

	}

	public void connect() throws Exception {

		if (con != null)
			return;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}

		String url = "jdbc:mysql://localhost:3306/cement_app";

		con = DriverManager.getConnection(url, "root", "H65zD4Q2a9VG7");

		System.out.println("Connected");
	}

	public void saveAdditive(String name, double sg, int type, int state) throws SQLException {

		String checkSql = "select count(*) as count from additives where name=?";
		PreparedStatement checkStatement = con.prepareStatement(checkSql);

		String insertSql = "insert into additives (name, sg, type, state) values (?, ?, ?, ?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);

		String updateSql = "update additives set sg=?, type=?, state=? where name =?";
		PreparedStatement updateStatement = con.prepareStatement(updateSql);

		checkStatement.setString(1, name);
		// updateStatement.setString(1,name);

		ResultSet checkResult = checkStatement.executeQuery();
		checkResult.next();

		int count = checkResult.getInt(1);

		if (count == 0) {

			System.out.println("Inserting additive with name " + name);
			int col = 1;
			insertStatement.setString(col++, name);
			insertStatement.setDouble(col++, sg);
			insertStatement.setInt(col++, type);
			insertStatement.setInt(col++, state);

			insertStatement.executeUpdate();
		} else {

			System.out.println("Updating additive with name " + name);

			int col = 1;
			updateStatement.setDouble(col++, sg);
			updateStatement.setInt(col++, type);
			updateStatement.setInt(col++, state);
			updateStatement.setString(col++, name);

			updateStatement.executeUpdate();
		}

		updateStatement.close();
		insertStatement.close();
		checkStatement.close();
	}
	
	public void saveWaterType(String name, double sg) throws SQLException {

		String checkSql = "select count(*) as count from water_type where name=?";
		PreparedStatement checkStatement = con.prepareStatement(checkSql);

		String insertSql = "insert into water_type (name, sg) values (?, ?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);

		String updateSql = "update water_type set sg=? where name =?";
		PreparedStatement updateStatement = con.prepareStatement(updateSql);

		checkStatement.setString(1, name);

		ResultSet checkResult = checkStatement.executeQuery();
		checkResult.next();

		int count = checkResult.getInt(1);

		if (count == 0) {

			System.out.println("Inserting water type with name " + name);
			int col = 1;
			insertStatement.setString(col++, name);
			insertStatement.setDouble(col++, sg);

			insertStatement.executeUpdate();
		} else {

			System.out.println("Updating water type with name " + name);

			int col = 1;
			updateStatement.setDouble(col++, sg);
			updateStatement.setString(col++, name);

			updateStatement.executeUpdate();
		}

		updateStatement.close();
		insertStatement.close();
		checkStatement.close();
	}
	
	public void saveCementType(String name, double sg, double lbs) throws SQLException {

		String checkSql = "select count(*) as count from cement where name=?";
		PreparedStatement checkStatement = con.prepareStatement(checkSql);

		String insertSql = "insert into cement (name, lbs, sg) values (?, ?, ?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);

		String updateSql = "update cement set lbs =?, sg=? where name =?";
		PreparedStatement updateStatement = con.prepareStatement(updateSql);

		checkStatement.setString(1, name);

		ResultSet checkResult = checkStatement.executeQuery();
		checkResult.next();

		int count = checkResult.getInt(1);

		if (count == 0) {

			System.out.println("Inserting cement type with name " + name);
			int col = 1;
			insertStatement.setString(col++, name);
			insertStatement.setDouble(col++, lbs);
			insertStatement.setDouble(col++, sg);

			insertStatement.executeUpdate();
		} else {

			System.out.println("Updating cement type with name " + name);

			int col = 1;
			updateStatement.setDouble(col++, sg);
			updateStatement.setDouble(col++, lbs);
			updateStatement.setString(col++, name);

			updateStatement.executeUpdate();
		}

		updateStatement.close();
		insertStatement.close();
		checkStatement.close();
	}
	
	public ArrayList<String> populateCementCombo() throws SQLException {
		ArrayList<String> cementTypes = new ArrayList<String>();
		String cementQuery = "select name from cement";
		PreparedStatement cementStatement = con.prepareStatement(cementQuery);

		ResultSet checkResult = cementStatement.executeQuery();

		while (checkResult.next()) {
			cementTypes.add(new String(checkResult.getString("name")));
		}
		cementStatement.close();

		return cementTypes;
		
	}
	
	public ArrayList<String> populateAdditiveTypeCombo() throws SQLException {
		ArrayList<String> additiveTypes = new ArrayList<String>();

		String checkSql = "select type from additive_type";
		PreparedStatement checkStatement = con.prepareStatement(checkSql);

		ResultSet checkResult = checkStatement.executeQuery();

		while (checkResult.next()) {
			additiveTypes.add(new String(checkResult.getString("type")));
		}

		checkStatement.close();

		return additiveTypes;
	}

	public ArrayList<String> populateAdditiveCombo(String additiveType) throws SQLException {
		ArrayList<String> additives = new ArrayList<String>();

		String additiveQuery = "select name from additives left join additive_type on additives.type = additive_type.type_id where additive_type.type =?";
		PreparedStatement queryStatement = con.prepareStatement(additiveQuery);
		queryStatement.setString(1, additiveType);

		ResultSet checkResult = queryStatement.executeQuery();

		while (checkResult.next()) {
			additives.add(new String(checkResult.getString("name")));
		}
		queryStatement.close();

		return additives;
	}

	public double getAdditiveDensity(String additive) throws SQLException {
		String densityQuery = "select density from additives where name=?";
		PreparedStatement densityStatement = con.prepareStatement(densityQuery);
		densityStatement.setString(1, additive);

		ResultSet checkResult = densityStatement.executeQuery();
		checkResult.next();
		double density = checkResult.getDouble(1);

		densityStatement.close();
		return density;
	}
	
	public String getAdditiveState(String additive) throws SQLException {
		String stateQuery = "select additive_state.state from additive_state left join "
				+ "additives on additive_state.state_id = additives.state where additives.name =?";
		PreparedStatement stateStatement = con.prepareStatement(stateQuery);
		stateStatement.setString(1, additive);

		ResultSet checkResult = stateStatement.executeQuery();
		checkResult.next();
		String state = checkResult.getString(1);

		stateStatement.close();
		return state;
	}
	
	public double getCementLbs(String cement) throws SQLException {
		String cementLbsQuery = "select lbs from cement where name=?";
		PreparedStatement cementLbsStatement = con.prepareStatement(cementLbsQuery);
		cementLbsStatement.setString(1, cement);
		
		ResultSet checkResult = cementLbsStatement.executeQuery();
		checkResult.next();
		double cementLbs = checkResult.getDouble("lbs");
		
		cementLbsStatement.close();
		return cementLbs;
	}
	
	public double getCementGals(String cement) throws SQLException {
		String cementGalsQuery = "select gals from cement where name=?";
		PreparedStatement cementGalsStatement = con.prepareStatement(cementGalsQuery);
		cementGalsStatement.setString(1, cement);
		
		ResultSet checkResult = cementGalsStatement.executeQuery();
		checkResult.next();
		double cementGals = checkResult.getDouble("gals");
		
		cementGalsStatement.close();
		return cementGals;
	}
	
	public double getAdditiveSg(String additive) throws SQLException {
		String additiveSgQuery = "SELECT sg from additives where name =? UNION ALL select sg from cement where name=? UNION ALL select sg from water_type where name=?";
		PreparedStatement additiveSgStatement = con.prepareStatement(additiveSgQuery);
		additiveSgStatement.setString(1, additive);
		additiveSgStatement.setString(2, additive);
		additiveSgStatement.setString(3, additive);
		
		ResultSet checkResult = additiveSgStatement.executeQuery();
		checkResult.next();
		double additiveSg = checkResult.getDouble("sg");
		
		additiveSgStatement.close();
		return additiveSg;
	}
	
	public ArrayList<String> populateWaterType() throws SQLException {
		ArrayList<String> waterTypes = new ArrayList<String>();
		String waterTypeQuery = "select name from water_type";
		PreparedStatement waterTypeStatement = con.prepareStatement(waterTypeQuery);
		
		ResultSet checkResult = waterTypeStatement.executeQuery();
		while(checkResult.next()) {
			waterTypes.add(new String(checkResult.getString("name")));
		}
		
		waterTypeStatement.close();
		return waterTypes;
	}
	
	public ArrayList<String> populateStateBox() throws SQLException {
		ArrayList<String> statesArray = new ArrayList<String>();
		String statesQuery = "select state from additive_state";
		PreparedStatement statesStatement = con.prepareStatement(statesQuery);
		
		ResultSet checkResult = statesStatement.executeQuery();
		while(checkResult.next()) {
			statesArray.add(new String(checkResult.getString("state")));
		}
		
		statesStatement.close();
		return statesArray;
	}
	
	public ArrayList<String> getAdditiveNames() throws SQLException {
		ArrayList<String> namesArray = new ArrayList<String>();
		String namesQuery = "select name from additives";
		PreparedStatement namesStatement = con.prepareStatement(namesQuery);
		
		ResultSet checkResult = namesStatement.executeQuery();
		while(checkResult.next()) {
			namesArray.add(new String(checkResult.getString("name")));
		}
		
		namesStatement.close();
		return namesArray;
	}
	
	public ArrayList<String> getCementNames() throws SQLException {
		ArrayList<String> namesArray = new ArrayList<String>();
		String namesQuery = "select name from cement";
		PreparedStatement namesStatement = con.prepareStatement(namesQuery);
		
		ResultSet checkResult = namesStatement.executeQuery();
		while(checkResult.next()) {
			namesArray.add(new String(checkResult.getString("name")));
		}
		
		namesStatement.close();
		return namesArray;
	}
	
	public ArrayList<String> getWaterNames() throws SQLException {
		ArrayList<String> namesArray = new ArrayList<String>();
		String namesQuery = "select name from water_type";
		PreparedStatement namesStatement = con.prepareStatement(namesQuery);
		
		ResultSet checkResult = namesStatement.executeQuery();
		while(checkResult.next()) {
			namesArray.add(new String(checkResult.getString("name")));
		}
		
		namesStatement.close();
		return namesArray;
	}
	
	public double getWaterSg(String type) throws SQLException {
		String waterSgQuery = "select sg from water_type where name =?";
		PreparedStatement waterSgStatement = con.prepareStatement(waterSgQuery);
		waterSgStatement.setString(1, type);
		
		ResultSet checkResult = waterSgStatement.executeQuery();
		checkResult.next();
		double waterSg = checkResult.getDouble("sg");
		
		waterSgStatement.close();
		return waterSg;
	}
	
	public double getWaterDensity(String type) throws SQLException {
		String waterDensityQuery = "select density from water_type where name =?";
		PreparedStatement waterDensityStatement = con.prepareStatement(waterDensityQuery);
		waterDensityStatement.setString(1, type);
		
		ResultSet checkResult = waterDensityStatement.executeQuery();
		checkResult.next();
		double waterDensity = checkResult.getDouble("density");
		
		waterDensityStatement.close();
		return waterDensity;
	}
	
	public void deleteAdditive(String additiveName) throws SQLException{
		String deleteQuery = "delete from additives where name=?";
		PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);
		deleteStatement.setString(1, additiveName);
		deleteStatement.executeUpdate();
		deleteStatement.close();
	}
	
	public void deleteCementType(String cementName) throws SQLException{
		String deleteQuery = "delete from cement where name=?";
		PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);
		deleteStatement.setString(1, cementName);
		deleteStatement.executeUpdate();
		deleteStatement.close();
	}
	
	public void deleteWaterType(String waterName) throws SQLException{
		String deleteQuery = "delete from water_type where name=?";
		PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);
		deleteStatement.setString(1, waterName);
		deleteStatement.executeUpdate();
		deleteStatement.close();
	}
	
	public void storeReport(String reportPath, String reportName, String temp, String mixRheoYP, String tempRheoYP, String fluidLost, String setTime, String strengthTwelveHrs) throws SQLException {

		String checkSql = "select count(*) as count from slurry_reports where path=?";
		PreparedStatement checkStatement = con.prepareStatement(checkSql);

		String insertSql = "insert into slurry_reports (path, name, temperature, mix_yield_point, temp_yield_point, fluid_loss, set_time, twelve_hour_strength) values (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);

		String updateSql = "update slurry_reports set temperature=?, mix_yield_point=?, temp_yield_point=?, fluid_loss=?, set_time=?, twelve_hour_strength=? where path =?";
		PreparedStatement updateStatement = con.prepareStatement(updateSql);

		checkStatement.setString(1, reportPath);
		// updateStatement.setString(1,name);

		ResultSet checkResult = checkStatement.executeQuery();
		checkResult.next();

		int count = checkResult.getInt(1);

		if (count == 0) {

			System.out.println("Inserting report with path " + reportPath);
			int col = 1;
			insertStatement.setString(col++, reportPath);
			insertStatement.setString(col++, reportName);
			insertStatement.setString(col++, temp);
			insertStatement.setString(col++, mixRheoYP);
			insertStatement.setString(col++, tempRheoYP);
			insertStatement.setString(col++, fluidLost);
			insertStatement.setString(col++, setTime);
			insertStatement.setString(col++, strengthTwelveHrs);

			insertStatement.executeUpdate();
		} else {

			System.out.println("Updating report with path " + reportPath);

			int col = 1;
			updateStatement.setString(col++, temp);
			updateStatement.setString(col++, mixRheoYP);
			updateStatement.setString(col++, tempRheoYP);
			updateStatement.setString(col++, fluidLost);
			updateStatement.setString(col++, setTime);
			updateStatement.setString(col++, strengthTwelveHrs);
			updateStatement.setString(col++, reportPath);


			updateStatement.executeUpdate();
		}

		updateStatement.close();
		insertStatement.close();
		checkStatement.close();
	}
	
	public void storeQCTemplate(String reportPath, String reportName, String productName) throws SQLException {

		String checkSql = "select count(*) as count from qc_templates where product_name =?";
		PreparedStatement checkStatement = con.prepareStatement(checkSql);

		String insertSql = "insert into qc_templates (product_name, path, file_name) values (?, ?, ?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);

		String updateSql = "update qc_templates set path=?, file_name=? where product_name =?";
		PreparedStatement updateStatement = con.prepareStatement(updateSql);

		checkStatement.setString(1, productName);

		ResultSet checkResult = checkStatement.executeQuery();
		checkResult.next();

		int count = checkResult.getInt(1);

		if (count == 0) {

			System.out.println("Inserting qc template for " + productName);
			int col = 1;
			insertStatement.setString(col++, productName);
			insertStatement.setString(col++, reportPath);
			insertStatement.setString(col++, reportName);

			insertStatement.executeUpdate();
		} else {

			System.out.println("Updating qc template for product: " + productName);

			int col = 1;
			updateStatement.setString(col++, reportPath);
			updateStatement.setString(col++, reportName);
			updateStatement.setString(col++, productName);


			updateStatement.executeUpdate();
		}

		updateStatement.close();
		insertStatement.close();
		checkStatement.close();
	}
	
	public void storePendingQCTest(String testNumber, String productName, String reportName, String reportPath) throws SQLException {

		String insertSql = "insert into qc_pending_tests (qc_test_number, product_name, file_name, path) values (?, ?, ?, ?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);
		int col = 1;
		insertStatement.setString(col++, testNumber);
		insertStatement.setString(col++, productName);
		insertStatement.setString(col++, reportName);
		insertStatement.setString(col++, reportPath);
		insertStatement.executeUpdate();
		System.out.println("Inserting pending test for " + productName);
		insertStatement.close();
	}
	
	
	public void storeCompletedQCTest(String testNumber, String productName, String passStatus, String reportName, String reportPath, String date) throws SQLException {

		String insertSql = "insert into completed_qc_tests (qc_test_number, product_name, pass_status, file_name, path, date) values (?, ?, ?, ?, ?, ?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);
		int col = 1;
		insertStatement.setString(col++, testNumber);
		insertStatement.setString(col++, productName);
		insertStatement.setString(col++, passStatus);
		insertStatement.setString(col++, reportName);
		insertStatement.setString(col++, reportPath);
		insertStatement.setString(col++, date);
		insertStatement.executeUpdate();
		System.out.println("Inserting completed test for " + productName);
		insertStatement.close();
	}
	
	public HashMap<String, String> queryReports(String reportQuery) throws SQLException {
		HashMap<String, String> reports = new HashMap<String, String>();
		String reportSQL = reportQuery;
		PreparedStatement reportStatement = con.prepareStatement(reportSQL);
		ResultSet checkResult = reportStatement.executeQuery();
		while(checkResult.next()) {
			String path = checkResult.getString("path");
			String name = checkResult.getString("name");
			reports.put(name, path);
		}
		
		reportStatement.close();
		return reports;
	}
	
	public ArrayList<String> getProducts() throws SQLException {
		ArrayList<String> products = new ArrayList<String>();
		String productQuery = "select product_name from qc_templates";
		PreparedStatement productStatement = con.prepareStatement(productQuery);
		
		ResultSet checkResult = productStatement.executeQuery();
		while(checkResult.next()) {
			products.add(new String(checkResult.getString("product_name")));
		}
		
		productStatement.close();
		return products;
	}
	
	public String getQCTemplateFilePath(String product) throws SQLException {
		String filePathQuery = "select path from qc_templates where product_name = ?";
		PreparedStatement filePathStatement = con.prepareStatement(filePathQuery);
		filePathStatement.setString(1, product);
		ResultSet checkResult = filePathStatement.executeQuery();
		checkResult.next();
		String filePath = checkResult.getString("path");
		
		filePathStatement.close();
		return filePath;
	}
	
	public ArrayList<String> getQCComponents() throws SQLException {
		ArrayList<String> qcComponents = new ArrayList<String>();
		String componentQuery = "select name from qc_products";
		PreparedStatement componentStatement = con.prepareStatement(componentQuery);
		ResultSet checkResult = componentStatement.executeQuery();
		while(checkResult.next()) {
			String product = checkResult.getString("name");
			qcComponents.add(product);
		}
		
		componentStatement.close();
		
		return qcComponents;
		
	}
	
	public ArrayList<ArrayList<String>> getPendingTests() throws SQLException {
		ArrayList<ArrayList<String>> pendingTestsData = new ArrayList<ArrayList<String>>();
		ArrayList<String> qcNumbers = new ArrayList<String>();
		ArrayList<String> productNames = new ArrayList<String>();
		String testQuery = "select qc_test_number, product_name from qc_pending_tests";
		PreparedStatement testStatement = con.prepareStatement(testQuery);
		ResultSet checkResult = testStatement.executeQuery();
		while(checkResult.next()) {
			String testNumber = checkResult.getString("qc_test_number");
			qcNumbers.add(testNumber);
			String product = checkResult.getString("product_name");
			productNames.add(product);			
		}
		
		pendingTestsData.add(qcNumbers);
		pendingTestsData.add(productNames);
		testStatement.close();
		return pendingTestsData;
	}
	
	public String getPendingTestFilePath(int testNumber) throws SQLException {
		String procedureQuery = "select path from qc_pending_tests where qc_test_number=?";
		PreparedStatement procedureStatement = con.prepareStatement(procedureQuery);
		procedureStatement.setInt(1,  testNumber);
		ResultSet checkResult = procedureStatement.executeQuery();
		checkResult.next();
		String filePath = checkResult.getString("path");
		
		procedureStatement.close();
		return filePath;
	}
	
	public void deletePendingTest(int testNumber) throws SQLException {
		String deleteQuery = "delete from qc_pending_tests where qc_test_number=?";
		PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);
		deleteStatement.setInt(1, testNumber);
		deleteStatement.executeUpdate();
		deleteStatement.close();
	}
	
	public String getUserRole(String userName) throws SQLException {
		String role;
		String roleQuery = "select role from user_records where username=?";
		PreparedStatement checkStatement = con.prepareStatement(roleQuery);
		checkStatement.setString(1, userName);
		ResultSet checkResult = checkStatement.executeQuery();
		checkResult.next();
		role = checkResult.getString(1);
		
		checkStatement.close();
		return role;
	}
	
	public boolean addUser(String userName, String userRole) throws SQLException {
		boolean userExists;
		
		String checkSql = "select count(*) as count from user_records where username=?";
		PreparedStatement checkStatement = con.prepareStatement(checkSql);

		String insertSql = "insert into user_records (username, role) values (?, ?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);

		checkStatement.setString(1, userName);

		ResultSet checkResult = checkStatement.executeQuery();
		checkResult.next();

		int count = checkResult.getInt(1);

		if (count == 0) {

			System.out.println("Inserting user with name " + userName);
			int col = 1;
			insertStatement.setString(col++, userName);
			insertStatement.setString(col++, userRole);

			insertStatement.executeUpdate();
			userExists = false;
		} 
		
		else {
			userExists = true;
		}

		insertStatement.close();
		checkStatement.close();
		
		return userExists;
	}
	
	public ArrayList<String> getUserNames() throws SQLException {
		ArrayList<String> userNames = new ArrayList<String>();
		String userNameQuery = "select username from user_records";
		PreparedStatement userNameStatement = con.prepareStatement(userNameQuery);
		ResultSet checkResult = userNameStatement.executeQuery();
		while(checkResult.next()) {
			userNames.add(new String(checkResult.getString("username")));
			}
		
		return userNames;
		}
	
	public void deleteUser(String userName) throws SQLException {
		String deleteQuery = "delete from user_records where username=?";
		PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);
		deleteStatement.setString(1, userName);
		deleteStatement.executeUpdate();
		deleteStatement.close();
		}
	
	public void resetPassword(String userName) throws SQLException {
		String resetPasswordQuery = "update user_records set password =? where username =?";
		PreparedStatement resetPasswordStatement = con.prepareStatement(resetPasswordQuery);
		
		int col = 1;
		resetPasswordStatement.setString(col++, null);
		resetPasswordStatement.setString(col++, userName);

		resetPasswordStatement.executeUpdate();
		resetPasswordStatement.close();
		}
	
	public void setUserRole(String userName, String userRole) throws SQLException {
		String setRoleQuery = "update user_records set role=? where username=?";
		PreparedStatement roleQueryStatement = con.prepareStatement(setRoleQuery);
		roleQueryStatement.setString(1, userRole);
		roleQueryStatement.setString(2, userName);

		roleQueryStatement.executeUpdate();
		roleQueryStatement.close();
		}
	
	public boolean checkUserExists(String userName) throws SQLException {
		boolean userExists;
		String userQuery = "select count(*) as count from user_records where username=?";
		PreparedStatement checkStatement = con.prepareStatement(userQuery);
		checkStatement.setString(1, userName);
		ResultSet checkResult = checkStatement.executeQuery();
		checkResult.next();

		int count = checkResult.getInt(1);

		if (count == 0) {
			System.out.println("User does not exist");
			userExists = false;
		}
		
		else {
			userExists = true;
		}
		
		return userExists;	
	}
	
	public boolean checkPasswordNullStatus(String userName) throws SQLException {
		boolean passwordNull;
		
		String passwordQuery = "select password from user_records where username=?";
		PreparedStatement passwordQueryStatement = con.prepareStatement(passwordQuery);
		passwordQueryStatement.setString(1, userName);
		
		ResultSet checkResult = passwordQueryStatement.executeQuery();
		checkResult.next();
		String password = checkResult.getString("password");
		
		if(password == null) {
			passwordNull = true;
		}
		
		else {
			passwordNull = false;
		}
		passwordQueryStatement.close();
		
		return passwordNull;
		}
	
	public void setPassword(String userName, String password) throws SQLException {
		String setPassQuery = "update user_records set password=? where username =?";
		PreparedStatement setPassQueryStatement = con.prepareStatement(setPassQuery);		
		setPassQueryStatement.setString(1, password);
		setPassQueryStatement.setString(2, userName);
		
		setPassQueryStatement.executeUpdate();
		setPassQueryStatement.close();
		}
	
	public boolean checkValidPassword(String userName, String password) throws SQLException {
		boolean validPassword = false;
		
		String passwordQuery = "select password from user_records where username=?";
		PreparedStatement passwordStatement = con.prepareStatement(passwordQuery);
		passwordStatement.setString(1, userName);
		ResultSet resultSet = passwordStatement.executeQuery();
		resultSet.next();
		String result = resultSet.getString(1);
		if(!result.equals(password)) {
			System.out.println("denied");
			validPassword = false;
			passwordStatement.close();
			System.out.println("NOT EQUAL");
		}
		
		else if(result.equals(password)){
			System.out.println("allowed");
			validPassword = true;
			passwordStatement.close();
			System.out.println("EQUAL");
			}
		return validPassword;
		}
	
	public ArrayList<String> getTemplateReports() throws SQLException {
		ArrayList<String> templates = new ArrayList<String>();
		String templateQuery = "select product_name from qc_templates";
		PreparedStatement templateQueryStatement = con.prepareStatement(templateQuery);
		ResultSet checkResult = templateQueryStatement.executeQuery();
		while(checkResult.next()) {
			templates.add(new String(checkResult.getString("product_name")));
			}
		
		return templates;
		}
	
	public void deleteTemplate(String template) throws SQLException {
		String deleteQuery = "delete from qc_templates where product_name=?";
		PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);
		deleteStatement.setString(1, template);
		deleteStatement.executeUpdate();
		deleteStatement.close();
	}
	
	public String getTemplatePath(String template) throws SQLException {
		String pathQuery = "select path from qc_templates where product_name=?";
		PreparedStatement pathStatement = con.prepareStatement(pathQuery);
		pathStatement.setString(1, template);
		ResultSet checkResult = pathStatement.executeQuery();
		checkResult.next();
		String templatePath = checkResult.getString("path");
		
		return templatePath;
		
	}
	}
