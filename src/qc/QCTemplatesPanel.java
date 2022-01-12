package qc;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import model.QCTemplateTableRow;

public class QCTemplatesPanel extends JPanel {
	
	private QCTemplateFormPanel qcTemplateFormPanel;
	private QCTemplateTablePanel qcTemplateTablePanel;
	private QCTemplatePanelListener listener;
	
	public QCTemplatesPanel() {
		qcTemplateFormPanel = new QCTemplateFormPanel();
		qcTemplateTablePanel = new QCTemplateTablePanel();
		
		qcTemplateFormPanel.setListener(new QCTemplateFormListener() {
			
			public void generateQCTemplate(QCTemplateReportEvent ev) {
				if(listener != null) {
					listener.generateQCTemplate(ev);
					listener.storeQCTemplate();
					}
				}
			
			public void addTemplateTableRow(QCTemplateFormEvent ev) {
				if(listener != null) {
					listener.addQCTemplateTableRow(ev);
					qcTemplateTablePanel.refresh();
					}
				}
			});
		
		setLayout(new BorderLayout());
		add(qcTemplateFormPanel, BorderLayout.WEST);
		add(qcTemplateTablePanel, BorderLayout.CENTER);
		//setVisible(true);
		}
	
	public void setQCTemplateTableData(List<QCTemplateTableRow> qcTemplateTableData) {
		/* sends the qc template table row data to the template table model */
		qcTemplateTablePanel.setQCTemplateTableData(qcTemplateTableData);
		}
	
	public void setQCComponents(ArrayList<String> qcProducts) {
		qcTemplateFormPanel.setQCComponents(qcProducts);
		}
	
	public void setListener(QCTemplatePanelListener listener) {
		this.listener = listener;
		}
	}
