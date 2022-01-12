package controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import ManageUsers.AddUserEvent;
import ManageUsers.UserRolePanelEvent;
import SlurryTesting.ReportGenerator;
import SlurryTesting.TestPanelEvent;
import calculator.DialogEvent;
import calculator.FormEvent;
import calculator.LoginPanelEvent;
import calculator.MainFrame;
import model.CalcRow;
import model.Database;
import model.QCFormRowStore;
import model.QCFormTableRow;
import model.QCTemplateRowStore;
import model.QCTemplateTableRow;
import model.RowDataStore;
import qc.QCTechFormEvent;
import qc.QCTemplateFormEvent;
import qc.QCTemplateGenerator;
import qc.QCTemplateReportEvent;
import qc.QCTestFormEvent;
import qc.QCTestGenerator;
import qc.ResultsEvent;
import reportSearch.ReportSearchEvent;

public class Controller {
	private volatile static Controller controller;
	RowDataStore dataStore = new RowDataStore();
	QCTemplateRowStore qcTemplateRowStore = new QCTemplateRowStore();
	Database db = new Database();
	ReportGenerator reportGenerator = new ReportGenerator();
	QCTemplateGenerator qcTemplateGenerator = new QCTemplateGenerator();
	QCFormRowStore qcFormRowStore = new QCFormRowStore();
	QCTestGenerator qcTestGenerator = new QCTestGenerator();
	
	private Controller() {}
	
	public static Controller getInstance() {
		if (controller == null) 
		{ 
			// To make thread safe 
			synchronized (Controller.class) 
			{ 
				// check again as multiple threads 
				// can reach above step 
				if (controller == null) {
					controller = new Controller(); 
					}
				} 
			} 
		return controller; 
		}
	
	public List<CalcRow> getCalculatorRows() {
		return dataStore.getCalculatorRows();
		}
	
	public ArrayList<String> populateAdditiveCombo(String additiveType) {
		ArrayList<String> additives = new ArrayList<String>();
		try {
			additives = db.populateAdditiveCombo(additiveType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return additives;
	}
	
	public double getCementLbs(String cement) {
		connect();
		double cementLbs = 0;
		try {
			cementLbs = db.getCementLbs(cement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cementLbs;
	}
	
	public double getCementGals(String cement) {
		connect();
		double cementGals = 0;
		try {
			cementGals = db.getCementGals(cement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cementGals;
	}
	
	public void insertCementRow(FormEvent ev) {
		String cementType = ev.getAdditive();
		double lbs = ev.getLbs();
		double gals = ev.getGals();
		String type = ev.getType();
		CalcRow cementRow = new CalcRow(cementType, lbs, gals, type);
		dataStore.insertCementRow(cementRow);
	}
	
	public void insertAdditiveRow(FormEvent ev) {
		String additive = ev.getAdditive();
		double lbs = ev.getLbs();
		double gals = ev.getGals();
		String type = ev.getType();
		double quantity = ev.getQuantity();
		String state = ev.getState();
		double density = ev.getDensity();
		
		CalcRow additiveRow = new CalcRow(additive, lbs, gals, type, quantity, state, density);
		
		dataStore.insertAdditiveRow(additiveRow);
	}
	
	public void insertWaterRow(FormEvent ev) {
		String waterType = ev.getAdditive();
		double lbs = ev.getLbs();
		double gals = ev.getGals();
		String type = ev.getType();
		
		CalcRow waterRow = new CalcRow(waterType, lbs, gals, type);
		
		dataStore.insertWaterRow(waterRow);
	}
	
	public String getAdditiveState(String additive) {
		connect();
		String state = null;
		try {
			state = db.getAdditiveState(additive);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	
	public double getAdditiveDensity(String additive) {
		connect();
		double density = 0;
		try {
			density = db.getAdditiveDensity(additive);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return density;
	}
	
	/*public void addCalculatorRow(FormEvent ev) {

		String additive = ev.getAdditive();
		double lbs = ev.getLbs();
		double gals = ev.getGals();
		
		CalcRow calcRow = new CalcRow(additive, lbs, gals);
		
		dataStore.addCalcRow(calcRow);
	}*/
	
	public ArrayList<String> getQCComponents() {

		connect();

		ArrayList<String> qcComponents = null;
		try {
			qcComponents = db.getQCComponents();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qcComponents;
	}
	
	public void createPendingQCTest(String productName, String filePath, String testNumber) {
		ArrayList<String> batchNumbers = new ArrayList<String>();
		for(int i = 0; i < qcFormRowStore.getQCFormTableRows().size(); i++) {
			String batchNumber = qcFormRowStore.getQCFormTableRows().get(i).getBatchNumber();
			batchNumbers.add(batchNumber);
			}
		try {
			qcTestGenerator.setTestNumber(testNumber);
			qcTestGenerator.generatePendingTestReport(productName, batchNumbers, filePath);
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	
	public List<QCTemplateTableRow> setQCTemplateTableData() {
		/* Retrieves the data from the qcTemplateRowStore object
		*/
		return qcTemplateRowStore.getQCTableRows();
	}
	
	public List<QCFormTableRow> getQCTestTableRows() {
		
		return qcFormRowStore.getQCFormTableRows();
	}
	
	public void setReportArray() {
		//reportGenerator.setArray(array);
	}
	
	public void generateReport(TestPanelEvent ev) {
		String temp = ev.getTemp();
		String depth = ev.getDepth();
		ArrayList<CalcRow> slurryList = dataStore.getCalculatorRows();
		List<String> mixRheoArray = ev.getMixRheoArray();
		List<String> tempRheoArray = ev.getTempRheoArray();
		List<String> fluidLossArray = ev.getFluidLossArray();
		List<String> thickeningArray = ev.getThickeningArray();
		List<String> compressiveArray = ev.getCompressiveArray();
		
		reportGenerator.setSlurryList(slurryList);
		reportGenerator.setTemp(temp);
		reportGenerator.setDepth(depth);
		reportGenerator.setMixRheoArray(mixRheoArray);
		reportGenerator.setTempRheoArray(tempRheoArray);
		reportGenerator.setFluidLossArray(fluidLossArray);
		reportGenerator.setThickeningArray(thickeningArray);
		reportGenerator.setCompressiveArray(compressiveArray);
		reportGenerator.createTableWord("fartins.docx");
	}
	
	public void generateQCTemplate(QCTemplateReportEvent ev) {
		String productName = ev.getProductName();
		String testText = ev.getTestText();
		ArrayList<QCTemplateTableRow> qcTableRows = qcTemplateRowStore.getQCTableRows();
		qcTemplateGenerator.setProductName(productName);
		qcTemplateGenerator.setTestText(testText);
		qcTemplateGenerator.setQCTableRows(qcTableRows);
		try {
			qcTemplateGenerator.createTableWord();
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void addQCTemplateTableRow(QCTemplateFormEvent ev) {

		String componentName = ev.getComponentName();
		String amount = ev.getAmount();
		
		QCTemplateTableRow qcTableRow = new QCTemplateTableRow(componentName, amount);
		
		qcTemplateRowStore.addQCTableRow(qcTableRow);
	}
	
	public void clearQCTestArray() {
		qcFormRowStore.clearArray();
	}
	public void addQCTestTableRow(QCTestFormEvent ev) {
		String componentName = ev.getComponentName();
		String amount = ev.getAmount();
		
		QCFormTableRow qcTableRow = new QCFormTableRow(componentName, amount);
		
		qcFormRowStore.addQCFormTableRow(qcTableRow);
	}
	
	public void saveAdditive(DialogEvent evDialog) throws SQLException {
		String name = evDialog.getName();
		double sg = evDialog.getSg();
		int type = evDialog.getType();
		int state = evDialog.getState();
		db.saveAdditive(name, sg, type, state);
	}
	
	public void saveCementType(DialogEvent evDialog) throws SQLException {
		String name = evDialog.getName();
		double sg = evDialog.getSg();
		double lbs = evDialog.getLbs();
		db.saveCementType(name, lbs, sg);
	}
	
	public void saveWaterType(DialogEvent evDialog) throws SQLException {
		String name = evDialog.getName();
		double sg = evDialog.getSg();
		db.saveWaterType(name, sg);
	}
	
	public void deleteAdditive(DialogEvent evDialog) throws SQLException {
		String additiveName = evDialog.getName();
		db.deleteAdditive(additiveName);
	}
	
	public void deleteCementType(DialogEvent evDialog) throws SQLException {
		String cementName = evDialog.getName();
		db.deleteCementType(cementName);
	}
	
	public void deleteWaterType(DialogEvent evDialog) throws SQLException {
		String waterName = evDialog.getName();
		db.deleteWaterType(waterName);
	}
	
	public void connect() {
		try {
			db.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<String> populateStateBox() {
		ArrayList<String> statesArray = new ArrayList<String>();
		try {
			statesArray = db.populateStateBox();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return statesArray;
	}
	
	public ArrayList<String> populateAdditiveTypeCombo() {
		ArrayList<String> additiveTypes = new ArrayList<String>();
		try {
			additiveTypes = db.populateAdditiveTypeCombo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return additiveTypes;
	}
	
	public ArrayList<String> populateCementCombo() {
		ArrayList<String> cementTypes = new ArrayList<String>();
		try {
			cementTypes = db.populateCementCombo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cementTypes;
	}
	
	public ArrayList<String> populateNameBox(String componentType) {
		ArrayList<String> names = new ArrayList<String>();
		
		connect();

		if(componentType.equalsIgnoreCase("additive")) {
			try {
				names = db.getAdditiveNames();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(componentType.equalsIgnoreCase("cement")) {
			try {
				names = db.getCementNames();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(componentType.equalsIgnoreCase("water")) {
			try {
				names = db.getWaterNames();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return names;
	}
	
	public void setAdditiveLbsAndGals(double cementLbs) {
		if(!dataStore.getCalculatorRows().isEmpty()) {
			for(CalcRow calcRow : dataStore.getCalculatorRows()) {
				if(calcRow.getComponentType().equalsIgnoreCase("additive")) {
					calcRow.setGrams(0);
					calcRow.setMls(0);
					if(calcRow.getState().equalsIgnoreCase("solid")) {
						calcRow.setLbs((calcRow.getQuantity() * cementLbs) / 100);
						calcRow.setGals(calcRow.getLbs() / calcRow.getDensity());
					}
					
					else if(calcRow.getState().equalsIgnoreCase("liquid")) {
						calcRow.setLbs(calcRow.getQuantity() * calcRow.getDensity());
						calcRow.setGals(calcRow.getQuantity());
					}
				}

			}
		}
	}
	
	public void setLabQuantities(double totalGals) throws SQLException {
		
		ArrayList<CalcRow> calcRows = dataStore.getCalculatorRows();
		double slurryGals = totalGals;
		
		for (CalcRow calcRow : calcRows) {
			
			String additiveName = calcRow.getAdditive();
			double additiveGals = calcRow.getGals();
			double mls = (additiveGals / slurryGals) * 600;
			
			double additiveSg = getAdditiveSg(additiveName);
			double grams = mls * additiveSg;
			calcRow.setMls(mls);
			calcRow.setGrams(grams);
		}
	}

	public double waterGalsRequirement(String type, double slurryGals, double slurryLbs, double slurryDensity) {
		String waterType = type;
		double waterDensity = getWaterDensity(waterType);
		double waterGals;
		double density = slurryDensity;
		double totalLbs = slurryLbs;
		double totalGals = slurryGals;
		
		waterGals = (totalLbs - (density * totalGals)) / (density - waterDensity);
		
		return waterGals;
	}
	
	public double waterLbsRequirement(String type, double waterGals) {
		String waterType = type;
		double waterDensity = getWaterDensity(waterType);
		double waterLbs = waterGals * waterDensity;
		return waterLbs;
	}
	
	public double getAdditiveSg(String additiveName) throws SQLException {
		double additiveSg = db.getAdditiveSg(additiveName);
		return additiveSg;
	}
	
	public ArrayList<String> populateWaterType() {
		ArrayList<String> waterTypes = new ArrayList<String>();
		try {
			waterTypes = db.populateWaterType();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return waterTypes;
	}
	
	public double getWaterSg(String waterName) {
		double waterSg = 0;
		try {
			waterSg = db.getWaterSg(waterName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return waterSg;
	}
	
	public double getWaterDensity(String waterName) {
		connect();
		double waterDensity = 0;
		try {
			waterDensity = db.getWaterDensity(waterName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return waterDensity;
	}
	
	public ArrayList<Double> removeComponentRow(int row) {
		ArrayList<Double> componentData = new ArrayList<Double>();
		componentData = dataStore.removeComponentRow(row);
		return componentData;
	}
	
	public void storeQCTemplate() {
		ArrayList<String> qcTemplateData = qcTemplateGenerator.getReportData();
		String reportPath = qcTemplateData.get(0);
		String reportName = qcTemplateData.get(1);
		String productName = qcTemplateData.get(2);

		connect();

		try {
			db.storeQCTemplate(reportPath, reportName, productName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void storePendingQCTest() {
		ArrayList<String> qcPendingTestData = qcTestGenerator.getPendingReportData();
		String testNumber = qcPendingTestData.get(0);
		String productName = qcPendingTestData.get(1);
		String reportName = qcPendingTestData.get(2);
		String reportPath = qcPendingTestData.get(3);
		
		connect();

		try {
			db.storePendingQCTest(testNumber, productName, reportName, reportPath);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void storeCompletedQCTest() {
		ArrayList<String> qcCompletedTestData = qcTestGenerator.getCompletedReportData();
		String testNumber = qcCompletedTestData.get(0);
		String productName = qcCompletedTestData.get(1);
		String passStatus = qcCompletedTestData.get(2);
		String reportName = qcCompletedTestData.get(3);
		String reportPath = qcCompletedTestData.get(4);
		String date = qcCompletedTestData.get(5);

		connect();

		try {
			db.storeCompletedQCTest(testNumber, productName, passStatus, reportName, reportPath, date);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int testNumberInt = Integer.parseInt(testNumber);
		try {
			deletePendingTest(testNumberInt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    File myObj = new File("C:\\Users\\pande\\eclipse-workspace\\QCPendingTests\\" + "QC" + testNumber + " - " + productName + ".docx"); 
	    System.gc();
	    boolean state = true;
	    while(state) {
	    	if(myObj.exists()) {
	    		myObj.delete();
	    	}
	    	else {
	    		state = false;
	    	}
	    }
	}
	
	public void deletePendingTest(int testNumber) throws SQLException {
		db.deletePendingTest(testNumber);
	}
	
	public void storeReport() {
		ArrayList<String> reportData = reportGenerator.getReportData();
		String reportPath = reportData.get(0);
		String reportName = reportData.get(1);
		String temp = reportData.get(2);
		String mixRheoYP = reportData.get(3);
		String tempRheoYP = reportData.get(4);
		String fluidLost = reportData.get(5);
		String setTime = reportData.get(6);
		String strengthTwelveHours = reportData.get(7);
		
		connect();

		try {
			db.storeReport(reportPath, reportName, temp, mixRheoYP, tempRheoYP, fluidLost, setTime, strengthTwelveHours);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public HashMap<String, String> queryReports(ReportSearchEvent reportSearchEvent) {
		String reportQuery = reportSearchEvent.getReportQuery();
		HashMap<String, String> reports = new HashMap<String, String>();
		
		connect();

		try {
			reports = db.queryReports(reportQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reports;
	}
	
	public ArrayList<String> getProducts() {
		ArrayList<String> products = new ArrayList<String>();
		
		connect();

		try {
			products = db.getProducts();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
	
	public String getQCTemplateFilePath(String product) {
		String filePath = new String();
		try {
			filePath = db.getQCTemplateFilePath(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath;
	}
	
	public ArrayList<ArrayList<String>> getPendingTests() {
		ArrayList<ArrayList<String>> pendingTests = null;
		
		connect();

		try {
			pendingTests = db.getPendingTests();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pendingTests;
	}
	
	public String getTestProcedure(QCTechFormEvent ev) {
		/* Retrieves the file path of the pending test
		 * corresponding to the testNumber.  This is then
		 * to the report generator object in order to
		 * retrieve the test procedure
		 */
		int testNumber = ev.getTestNumber();
		String filePath = null;
		String testProcedure = null;
		try {
			filePath = db.getPendingTestFilePath(testNumber);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			testProcedure = qcTestGenerator.getTestProcedure(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testProcedure;
	}
	
	public String getCurrentTestPath(int currentTestNumber) {
		String currentTestPath = null;
		try {
			currentTestPath = db.getPendingTestFilePath(currentTestNumber);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentTestPath;
	}
	
	public void saveTestResults(ResultsEvent rev) {
		String results = rev.getResults();
		String passOrFail = rev.getPassOrFail();
		String testFilePath = rev.getFilePath();
		String product = rev.getProduct();
		int testNumber = rev.getTestNumber();
		try {
			qcTestGenerator.saveTestResults(results, passOrFail, testFilePath, product, testNumber);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getUserRole(LoginPanelEvent ev) {
		String userName = ev.getUserName();
		String role = null;
		try {
			role = db.getUserRole(userName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return role;
	}
	
	public boolean addUser(AddUserEvent ev) {
		boolean userExists = false;
		String userName = ev.getUserName();
		String userRole = ev.getUserRole();
		
		try {
			userExists = db.addUser(userName, userRole);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		
		return userExists;
		}
	
	public ArrayList<String> getUserNames() {
		ArrayList<String> userNames = null;
		try {
			userNames = db.getUserNames();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return userNames;
		}
	
	public void deleteUser(String userName) {
		try {
			db.deleteUser(userName);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	
	public void resetPassword(String userName) {
		try {
			db.resetPassword(userName);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	
	public void setUserRole(UserRolePanelEvent ev) {
		String userName = ev.getUserName();
		String userRole = ev.getUserRole();
		
		try {
			db.setUserRole(userName, userRole);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	
	public boolean checkUserExists(String userName) {
		boolean userExists = false;
		connect();
		try {
			userExists = db.checkUserExists(userName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userExists;
	}
	
	public boolean checkPasswordNullStatus(LoginPanelEvent ev) {
		connect();

		String userName = ev.getUserName();
		boolean passwordNull = false;
		try {
			passwordNull = db.checkPasswordNullStatus(userName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return passwordNull;
		}
	
	public boolean checkValidPassword(LoginPanelEvent ev) {
		boolean validPassword = false;
		String userName = ev.getUserName();
		String password = ev.getPassword();
		
		try {
			validPassword = db.checkValidPassword(userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return validPassword;
	}
	
	public void setPassword(String userName, String password) {
		try {
			db.setPassword(userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	
	public ArrayList<String> getTemplateReports() {
		ArrayList<String> templateReports = new ArrayList<String>();
		try {
			templateReports = db.getTemplateReports();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return templateReports;
		}
	
	public void deleteTemplate(String template) {
		
		String templatePath = null;
		try {
			templatePath = db.getTemplatePath(template);
			} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		
		try {
			db.deleteTemplate(template);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		
	    File file = new File(templatePath); 
	    boolean state = true;
	    while(state) {
	    	if(file.exists()) {
	    		file.delete();
	    	}
	    	else {
	    		state = false;
	    		}
	    	}
		}
	}
