package qc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;

public class QCTestGenerator extends JPanel {
	
	private String reportPath;
	private String reportName;
	private String qcTestNumber;
	private String productName;
	private String date;
	private String passStatus;
	private XWPFDocument doc;
	
	public void generatePendingTestReport(String name, ArrayList<String> batchNumbers, String filePath) throws InvalidFormatException, IOException {
		
		productName = name;
		FileInputStream fis = new FileInputStream(filePath);
		doc = new XWPFDocument(fis);
		ArrayList<String> reportBatchNumbers = batchNumbers;
		//doc = new XWPFDocument(new FileInputStream(filePath));
		//XWPFDocument doc = new XWPFDocument(OPCPackage.open(filePath));
		XWPFTable batchNumberTable = doc.getTableArray(0);
		int index = 0;
		for(int i = 1; i <= reportBatchNumbers.size(); i++) {
			batchNumberTable.getRow(i).getCell(1).setText(reportBatchNumbers.get(index));
			index++;
		}
		setHeaders();
		saveTestReport(doc, "pendingTest");
		fis.close();
		doc.close();
	}
	
	public void setTestNumber(String testNumber) {
		qcTestNumber = testNumber;
		}
	
	public void setHeaders() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ZoneId zonedId = ZoneId.of( "GMT" );
		LocalDate today = LocalDate.now( zonedId );
		date = today.format(formatter);
		
	    for (XWPFHeader header : doc.getHeaderList()) {
	    	for(XWPFParagraph p : header.getParagraphs()) {
	    		List<XWPFRun> runs = p.getRuns();
	    		for(XWPFRun r : runs) {
	    			String text = r.getText(0);
		            if(text != null) {
		            	if(text.contains("$date$")) {
		            		text = text.replace("$date$", date);
			                r.setText(text, 0);
		            	}
		            	else if(text.contains("$qcNumber$")) {
		            		text = text.replace("$qcNumber$", "QC" + qcTestNumber);
			                r.setText(text, 0);
			                }
		            	}
		            }
	    		}
	    	}
	    }
	
	public void saveTestReport(XWPFDocument doc, String type) throws IOException {
		String relativePath = null;
		if(type == "pendingTest") {
			relativePath = "../QCPendingTests/";
			
			OutputStream out = null;
			reportName = "QC" + qcTestNumber + " - " + productName + ".docx";
	        File file = new File(relativePath + reportName);
	        reportPath = file.getCanonicalPath();
	        file = new File(reportPath);
	        out = new FileOutputStream(file);
			doc.write(out);
			out.close();
			
			
			doc.close();
		}
		
		else if(type == "finalTest") {
			relativePath = "../QCCompletedTests/";
			
			OutputStream out = null;
			reportName = "QC" + qcTestNumber + " - " + productName + ".docx";
	        File file = new File(relativePath + reportName);
	        reportPath = file.getCanonicalPath();
	        file = new File(reportPath);
			
	        out = new FileOutputStream(file);
			doc.write(out);
			out.close();
			doc.close();
		}
		

		
		}
	
	public ArrayList<String> getPendingReportData() {
		/*
		 * Creates an ArrayList<String> containing:
		 * 0: Test number
		 * 1: Product name
		 * 2: File name
		 * 3: File path
		 */
		ArrayList<String> pendingReportData = new ArrayList<String>();
		pendingReportData.add(this.qcTestNumber);
		pendingReportData.add(this.productName);
		pendingReportData.add(this.reportName);
		pendingReportData.add(this.reportPath);
		
		return pendingReportData;
		}
	
	public ArrayList<String> getCompletedReportData() {
		/*
		 * Creates an ArrayList<String> containing:
		 * 0: Test number
		 * 1: Product name
		 * 2: File name
		 * 3: File path
		 */
		ArrayList<String> completedReportData = new ArrayList<String>();
		completedReportData.add(this.qcTestNumber);
		completedReportData.add(this.productName);
		completedReportData.add(this.passStatus);
		completedReportData.add(this.reportName);
		completedReportData.add(this.reportPath);
		completedReportData.add(this.date);
		
		return completedReportData;
		}
	
	public String getTestProcedure (String filePath) throws FileNotFoundException, IOException {
		
		FileInputStream fis = new FileInputStream(filePath);
		doc = new XWPFDocument(fis);
		
		//doc = new XWPFDocument(new FileInputStream(filePath));
		String testProcedure = doc.getParagraphArray(4).getText();
		/*for(XWPFParagraph paragraph : doc.getParagraphs()) {
    		List<XWPFRun> runs = paragraph.getRuns();
    		for(XWPFRun r : runs) {
    			String text = r.getText(0);
    			}
    		} */
		return testProcedure;
		}
	
	public void saveTestResults(String results, String passOrFail, String filePath, String product, int testNumber) throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(filePath);
		doc = new XWPFDocument(fis);
		
		this.productName = product;
		this.qcTestNumber = String.valueOf(testNumber);
		passStatus = passOrFail;
		XWPFTable batchNumberTable = doc.getTableArray(0);
		int index = 0;
		XWPFTable table = doc.getTableArray(1);
		table.getRow(1).getCell(0).setText(results);
		table.getRow(1).getCell(1).setText(passOrFail);
		fis.close();
		saveTestReport(doc, "finalTest");
		}
	}

