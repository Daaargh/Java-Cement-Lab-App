package qc;

import java.awt.BorderLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.Scanner;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.QCTemplateTableRow;

public class QCTemplateTablePanel extends JTable {
	private JTable qcTemplateTable;
	private QCTemplateTableModel qcTemplateTableModel;
	
	public QCTemplateTablePanel() {
        

		
		qcTemplateTableModel = new QCTemplateTableModel();
		qcTemplateTable = new JTable(qcTemplateTableModel);
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(qcTemplateTable), BorderLayout.CENTER);
	}
	
	public void setQCTemplateTableData(List<QCTemplateTableRow> qcTemplateTableData) {
		// sets the source of the table data
		qcTemplateTableModel.setData(qcTemplateTableData);
	}
	
	public void refresh() {
		// refreshes the table model to reflect changes in the data
		qcTemplateTableModel.fireTableDataChanged();
	}
}
