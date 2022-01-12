package calculator;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.CalcRow;


public class TablePanel extends JPanel {
	private TablePanelListener tablePanelListener;
	private JTable slurryTable;
	private SlurryTableModel slurryTableModel;
	private JPopupMenu popup;
	
	public TablePanel() {
		
		slurryTableModel = new SlurryTableModel();
		slurryTable = new JTable(slurryTableModel);
		popup = new JPopupMenu();
		
		JMenuItem removeComponent = new JMenuItem("Remove Component");
		popup.add(removeComponent);
		
		slurryTable.addMouseListener(new MouseAdapter() {
			public void mousePressed (MouseEvent e) {
				int row = slurryTable.rowAtPoint(e.getPoint());
				
				slurryTable.getSelectionModel().setSelectionInterval(row, row);
				if(e.getButton() == MouseEvent.BUTTON3) {
					popup.show(slurryTable, e.getX(), e.getY());
				}
			}
		
		});
		
		removeComponent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = slurryTable.getSelectedRow();
				if(tablePanelListener != null) {
					tablePanelListener.rowDeleted(row);
					slurryTableModel.fireTableRowsDeleted(row, row);
				}
				
			}
			
			
		});
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(slurryTable), BorderLayout.CENTER);
	}
	
	public void setData(List<CalcRow> calcRows) {
		slurryTableModel.setData(calcRows);
	}
	
	public void refresh() {
		slurryTableModel.fireTableDataChanged();
	}
	
	public void setListener(TablePanelListener listener) {
		this.tablePanelListener = listener;
	}
}