package qc;

public interface QCTemplatePanelListener {
	public void upDatePendingTests();
	public void generateQCTemplate(QCTemplateReportEvent ev);
	public void storeQCTemplate();
	public void addQCTemplateTableRow(QCTemplateFormEvent ev);
}
