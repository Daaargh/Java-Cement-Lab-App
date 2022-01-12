package calculator;

public interface DeleteDialogListener {
	
	public void deleteAdditive(DialogEvent evDialog);
	public void deleteCementType(DialogEvent evDialog);
	public void deleteWaterType(DialogEvent evDialog);
	public void populateNameBox(String componentType);
}
