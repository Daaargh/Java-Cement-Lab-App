package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QCFormRowStore {
	
	private ArrayList<QCFormTableRow> qcRows;
	
	public QCFormRowStore() {
		
		qcRows = new ArrayList<QCFormTableRow>();
		
	}
	
	public void addQCFormTableRow(QCFormTableRow row) {
		qcRows.add(row);
	}
	
	public ArrayList<QCFormTableRow> getQCFormTableRows() {
		return qcRows;
	}
	
	public void clearArray() {
		qcRows.clear();
	}
}
