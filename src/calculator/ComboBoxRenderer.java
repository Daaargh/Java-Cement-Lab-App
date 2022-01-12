package calculator;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ComboBoxRenderer extends JLabel implements ListCellRenderer {
	private String placeHolder;

	public ComboBoxRenderer(String title) {
		placeHolder = title;
		}
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean hasFocus) {
		if (index == -1 && value == null)
			setText(placeHolder);
		else
			setText(value.toString());
		return this;
		}
	}
