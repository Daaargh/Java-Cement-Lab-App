package qc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import model.QCTemplateTableRow;

public class QCTemplateGenerator {
	
	private String productName;
	private String testText;
	private ArrayList<QCTemplateTableRow> qcTableRows;
	//private ArrayList<String> placeHolders = new ArrayList<String>();
	private String reportPath;
	private String reportName;
	public QCTemplateGenerator() {
		
	}
	
	public void setProductName(String productName) {
		this.productName = productName;		
	}
	
	public void setTestText(String testText) {
		this.testText = testText;
	}
	
	public void setQCTableRows(ArrayList<QCTemplateTableRow> qcTableRows) {
		this.qcTableRows = qcTableRows;
	}
	
	public void createTableWord() throws IOException, InvalidFormatException {
		
		//XWPFDocument doc = new XWPFDocument(new FileInputStream("C:\\Users\\pande\\OneDrive\\Desktop\\QCTEST.docx"));
		FileInputStream fis = new FileInputStream("C:\\Users\\pande\\OneDrive\\Desktop\\QCTEST.docx");
		XWPFDocument doc = new XWPFDocument(fis);
		
		//XWPFDocument doc = new XWPFDocument(OPCPackage.open("C:\\Users\\pande\\OneDrive\\Desktop\\QCTEST.docx"));

		for (XWPFParagraph p : doc.getParagraphs()) {
		    List<XWPFRun> runs = p.getRuns();
		    if (runs != null) {
		        for (XWPFRun r : runs) {
		            String text = r.getText(0);
		            if(text != null) {
		            	if(text.contains("$productName$")) {
		            		text = text.replace("$productName$", productName);
			                r.setText(text, 0);
		            	}
		            	else if(text.contains("$testProcedure$")) {
		            		text = text.replace("$testProcedure$", testText);
			                r.setText(text, 0);
		            	}		       
		            }
		        }
		    }
		}
		
		
		XWPFTable table = doc.getTableArray(0);
		for(int i = 0; i < qcTableRows.size(); i++) {
			table.createRow();
			table.getRow(i + 1).getCell(0).setText(qcTableRows.get(i).getComponentName());
			table.getRow(i + 1).getCell(2).setText(qcTableRows.get(i).getAmount());
		}
		
			OutputStream out = null;
			String relativePath = "../QCTemplates/";
			reportName = productName + ".docx";
	        File file = new File(relativePath + reportName);
	        reportPath = file.getCanonicalPath();
	        file = new File(reportPath);
			
	        fis.close();
	        out = new FileOutputStream(file);
			doc.write(out);
			out.close();
			doc.close();
	}
	
	public ArrayList<String> getReportData() {
		/*
		 * Creates an ArrayList<String> containing:
		 * 0: Report path
		 * 1: Report name
		 * 2: Product name
		 */
		ArrayList<String> reportData = new ArrayList<String>();
		reportData.add(this.reportPath);
		reportData.add(this.reportName);
		reportData.add(this.productName);
		
		return reportData;
	}
}
