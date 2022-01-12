package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QCTemplateRowStore {
	
	private ArrayList<QCTemplateTableRow> qcRows;
	
	public QCTemplateRowStore() {
		
		qcRows = new ArrayList<QCTemplateTableRow>();
		
	}
	
	public void addQCTableRow(QCTemplateTableRow row) {
		qcRows.add(row);
	}
	
	public ArrayList<QCTemplateTableRow> getQCTableRows() {
		return qcRows;
	}
}
