package qc;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import model.QCFormTableRow;

public class QCTestTablePanel extends JTable {
	private JTable qcFormTable;
	private QCTestTableModel qcFormTableModel;
	
	public QCTestTablePanel() {
		
		qcFormTableModel = new QCTestTableModel();
		qcFormTable = new JTable(qcFormTableModel);
		
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for(int i = 0; i < qcFormTableModel.getColumnCount(); i++) {
			qcFormTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
		}
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(qcFormTable), BorderLayout.CENTER);
	}
	
	public void setData(List<QCFormTableRow> qcRows) {
		qcFormTableModel.setData(qcRows);
	}
	
	public void refresh() {
		qcFormTableModel.fireTableDataChanged();
	}
	
}
