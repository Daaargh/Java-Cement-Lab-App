package reportSearch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;

import SlurryTesting.FluidComboModel;
import SlurryTesting.RheoComboModel;
import SlurryTesting.SetTimeComboModel;
import SlurryTesting.StrengthComboModel;
import SlurryTesting.TempComboModel;
import calculator.ComboBoxRenderer;

public class ReportSearchPanel extends JPanel {
	
	private ReportSearchListener reportSearchListener;
	private Desktop desktop;
	private JPanel searchPanel;
	private JPanel resultsPanel;
	private JPanel reportSearchToolbar;
	private HashMap<String, String> reports;
	private JLabel tempLabel;
	private JComboBox tempComboMin;
	private TempComboModel tempMinModel;
	private TempComboModel tempMaxModel;
	private JComboBox tempComboMax;
	
	private JLabel mixYPLabel;
	private JComboBox mixYPComboMin;
	private RheoComboModel mixYPMinModel;
	private RheoComboModel mixYPMaxModel;
	private JComboBox mixYPComboMax;
	
	private JLabel tempYPLabel;
	private JComboBox tempYPComboMin;
	private RheoComboModel tempYPMinModel;
	private RheoComboModel tempYPMaxModel;
	private JComboBox tempYPComboMax;
	
	private JLabel fluidLabel;
	private JComboBox fluidComboMin;
	private FluidComboModel fluidMinModel;
	private FluidComboModel fluidMaxModel;
	private JComboBox fluidComboMax;
	
	private JLabel setTimeLabel;
	private JComboBox setTimeComboMin;
	private SetTimeComboModel setTimeMinModel;
	private SetTimeComboModel setTimeMaxModel;
	private JComboBox setTimeComboMax;

	private JLabel strengthLabel;
	private JComboBox strengthComboMin;
	private StrengthComboModel strengthMinModel;
	private StrengthComboModel strengthMaxModel;
	private JComboBox strengthComboMax;
	
	private JButton search;
	
	private JTextArea results;
	
	private JButton homeButton;
	
	public ReportSearchPanel() {
		UIManager.put("ComboBox.background", new ColorUIResource(Color.white));
		UIManager.put("ComboBox.selectionBackground", new ColorUIResource(Color.white));
		//UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.WHITE));
		
		Dimension comboDimensions = new Dimension(80, 25);
		tempLabel = new JLabel("Temperature");
		tempLabel.setFont(new Font("", Font.PLAIN, 20));
		tempMinModel = new TempComboModel();
		
		
		tempComboMin = new JComboBox(tempMinModel);
		tempComboMin.setPreferredSize(comboDimensions);

		tempComboMin.setSelectedItem("Minimum");
		tempComboMin.setRenderer(new ComboBoxRenderer("Minimum"));
		tempComboMin.setSelectedIndex(-1);

		tempMaxModel = new TempComboModel();
		tempComboMax = new JComboBox(tempMaxModel);
		tempComboMax.setPreferredSize(comboDimensions);

		tempComboMax.setRenderer(new ComboBoxRenderer("Maximum"));
		tempComboMax.setSelectedIndex(-1);

		mixYPLabel = new JLabel("Mix yP");
		mixYPLabel.setFont(new Font("", Font.PLAIN, 20));
		
		mixYPMinModel = new RheoComboModel();
		mixYPComboMin = new JComboBox(mixYPMinModel);
		mixYPComboMin.setPreferredSize(comboDimensions);

		mixYPComboMin.setRenderer(new ComboBoxRenderer("Minimum"));
		mixYPComboMin.setSelectedIndex(-1);
		
		mixYPMaxModel = new RheoComboModel();
		mixYPComboMax = new JComboBox(mixYPMaxModel);
		mixYPComboMax.setPreferredSize(comboDimensions);

		mixYPComboMax.setRenderer(new ComboBoxRenderer("Maximum"));
		mixYPComboMax.setSelectedIndex(-1);
		
		tempYPLabel = new JLabel("Temp yP");
		tempYPLabel.setFont(new Font("", Font.PLAIN, 20));
		
		
		tempYPMinModel = new RheoComboModel();
		tempYPComboMin = new JComboBox(tempYPMinModel);
		tempYPComboMin.setPreferredSize(comboDimensions);

		tempYPComboMin.setRenderer(new ComboBoxRenderer("Minimum"));
		tempYPComboMin.setSelectedIndex(-1); //By default it selects first item, we don't want any selection
		
		tempYPMaxModel = new RheoComboModel();
		tempYPComboMax = new JComboBox(tempYPMaxModel);
		tempYPComboMax.setPreferredSize(comboDimensions);

		tempYPComboMax.setRenderer(new ComboBoxRenderer("Maximum"));
		tempYPComboMax.setSelectedIndex(-1);
		
		fluidLabel = new JLabel("Fluid Loss");
		fluidLabel.setFont(new Font("", Font.PLAIN, 20));
		
		fluidMinModel = new FluidComboModel();
		fluidComboMin = new JComboBox(fluidMinModel);
		fluidComboMin.setPreferredSize(comboDimensions);

		fluidComboMin.setRenderer(new ComboBoxRenderer("Minimum"));
		fluidComboMin.setSelectedIndex(-1);
		
		fluidMaxModel = new FluidComboModel();
		fluidComboMax = new JComboBox(fluidMaxModel);
		fluidComboMax.setPreferredSize(comboDimensions);

		fluidComboMax.setRenderer(new ComboBoxRenderer("Maximum"));
		fluidComboMax.setSelectedIndex(-1);
		
		setTimeLabel = new JLabel("Set Time");
		setTimeLabel.setFont(new Font("", Font.PLAIN, 20));
		
		setTimeMinModel = new SetTimeComboModel();
		setTimeComboMin = new JComboBox(setTimeMinModel);
		setTimeComboMin.setPreferredSize(comboDimensions);

		setTimeComboMin.setRenderer(new ComboBoxRenderer("Minimum"));
		setTimeComboMin.setSelectedIndex(-1);
		
		setTimeMaxModel = new SetTimeComboModel();
		setTimeComboMax = new JComboBox(setTimeMaxModel);
		setTimeComboMax.setPreferredSize(comboDimensions);

		setTimeComboMax.setRenderer(new ComboBoxRenderer("Maximum"));
		setTimeComboMax.setSelectedIndex(-1);

		strengthLabel = new JLabel("CS");
		strengthLabel.setFont(new Font("", Font.PLAIN, 20));
		
		strengthMinModel = new StrengthComboModel();
		strengthComboMin = new JComboBox(strengthMinModel);
		strengthComboMin.setPreferredSize(comboDimensions);

		strengthComboMin.setRenderer(new ComboBoxRenderer("Minimum"));
		strengthComboMin.setSelectedIndex(-1);
		
		strengthMaxModel = new StrengthComboModel();
		strengthComboMax = new JComboBox(strengthMaxModel);
		strengthComboMax.setPreferredSize(comboDimensions);

		strengthComboMax.setRenderer(new ComboBoxRenderer("Maximum"));
		strengthComboMax.setSelectedIndex(-1);
		
		search = new JButton("Search");
		
		reports = new HashMap<String, String>();
		results = new JTextArea();
		
		homeButton = new JButton("Home");
		
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> query = new ArrayList<String>();
				String startSQL = "select name, path from slurry_reports ";
				
				String startQuery;
				String tempQuery = getTempSQL();
				String mixYPQuery = getMixYPSQL();
				String tempYPQuery = getTempYPSQL();
				String fluidLossQuery = getFluidLossSQL();
				String setTimeQuery = getSetTimeSQL();
				String strengthQuery = getStrengthSQL();
				
				ArrayList<String> fullQuery = new ArrayList<String>();
				
				if(tempQuery != null) {
					fullQuery.add(tempQuery);
				}
				
				if(mixYPQuery != null) {
					fullQuery.add(mixYPQuery);
				}
				
				if(tempYPQuery != null) {
					fullQuery.add(tempYPQuery);
				}
				
				if(fluidLossQuery != null) {
					fullQuery.add(fluidLossQuery);
				}
				
				if(setTimeQuery != null) {
					fullQuery.add(setTimeQuery);
				}
				
				if(strengthQuery != null) {
					fullQuery.add(strengthQuery);
				}
				
				String fullSQLStatement = "select name, path from slurry_reports ";
				
				for(int i = 0; i < fullQuery.size(); i = i + 2) {
					if(i == 0) {
						fullQuery.add(i, "where");
					}
					
					else if(i >= fullQuery.size()) {
						break;
					}
					
					else {
						fullQuery.add(i, " &&");
					}
				}
				
				fullQuery.add(0, startSQL);
				
				StringBuilder sb = new StringBuilder();
				
				for(String str : fullQuery) {
					sb.append(str);
				}
				
				fullSQLStatement = sb.toString();
				System.out.println(fullSQLStatement);
				ReportSearchEvent reportSearchEvent = new ReportSearchEvent(this, fullSQLStatement);
				
				if(reportSearchListener != null) {
					reportSearchListener.queryReports(reportSearchEvent);
				}
			}
		});
		
		homeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ev) {
				if(reportSearchListener != null) {
					reportSearchListener.home();
				}
			}
		});
		
		layoutComponents();
		
	}
	
	private void layoutComponents() {
		searchPanel = new JPanel();
		Dimension searchDim = getPreferredSize();
		searchDim.width = 350;
		searchPanel.setPreferredSize(searchDim);
		resultsPanel = new JPanel();
		reportSearchToolbar = new JPanel();
		
		int space = 15;
		Border searchBorder = BorderFactory.createEtchedBorder();
		//Border titleBorder = BorderFactory.createTitledBorder("Database preferences");
		searchPanel.setBorder(searchBorder);
		
		searchPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		// FIRST ROW
		
		gc.gridy = 0;
		
		gc.weightx = 1;
		gc.weighty = 1;
		//gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		//gc.gridwidth = 2;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(20, 0, 0, 15);
		gc.fill = GridBagConstraints.NONE;
		searchPanel.add(tempLabel, gc);
		
		//gc.gridy++;
		//gc.gridwidth = 2;
		gc.gridx++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(20, 0, 0, 0);
		searchPanel.add(tempComboMin, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(20, 0, 0, 15);
		searchPanel.add(tempComboMax, gc);		
		
		// NEXT ROW
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		//gc.gridwidth = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 15);
		gc.fill = GridBagConstraints.NONE;

		searchPanel.add(mixYPLabel, gc);
		
		//gc.gridy++;
		//gc.gridwidth = 1;
		gc.gridx++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.NONE;
		searchPanel.add(mixYPComboMin, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.NONE;
		searchPanel.add(mixYPComboMax, gc);	
		
		
		// NEXT ROW
		
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		//gc.gridwidth = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 15);
		gc.fill = GridBagConstraints.NONE;

		searchPanel.add(tempYPLabel, gc);
		
		//gc.gridy++;
		//gc.gridwidth = 1;
		gc.gridx++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.NONE;
		searchPanel.add(tempYPComboMin, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.NONE;
		searchPanel.add(tempYPComboMax, gc);	
		
		// NEXT ROW
		
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		//gc.gridwidth = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 15);
		gc.fill = GridBagConstraints.NONE;

		searchPanel.add(fluidLabel, gc);
		
		//gc.gridy++;
		//gc.gridwidth = 1;
		gc.gridx++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.NONE;
		searchPanel.add(fluidComboMin, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.NONE;
		searchPanel.add(fluidComboMax, gc);	
		
		// NEXT ROW
		
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		//gc.gridwidth = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 15);
		gc.fill = GridBagConstraints.NONE;

		searchPanel.add(setTimeLabel, gc);
		
		//gc.gridy++;
		//gc.gridwidth = 1;
		gc.gridx++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.NONE;
		searchPanel.add(setTimeComboMin, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.NONE;
		searchPanel.add(setTimeComboMax, gc);	
		
		// NEXT ROW
		
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		//gc.gridwidth = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 15);
		gc.fill = GridBagConstraints.NONE;

		searchPanel.add(strengthLabel, gc);
		
		//gc.gridy++;
		//gc.gridwidth = 1;
		gc.gridx++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.NONE;
		searchPanel.add(strengthComboMin, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.NONE;
		searchPanel.add(strengthComboMax, gc);
		
		// new row
		
		gc.gridy++;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.NONE;
		searchPanel.add(search, gc);
		
		// Result Panel
		Border resultsBorder = BorderFactory.createEtchedBorder();
		resultsPanel.setBorder(searchBorder);
		//resultsPanel.setLayout(new BorderLayout());
		resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.PAGE_AXIS));
		
		reportSearchToolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
		reportSearchToolbar.add(homeButton);
		
		setLayout(new BorderLayout());
		add(searchPanel, BorderLayout.WEST);
		add(resultsPanel, BorderLayout.CENTER);
		add(reportSearchToolbar, BorderLayout.NORTH);
	}
	
	public String getTempSQL() {
		String tempSQL = null;
		if (tempComboMin.getSelectedIndex() == -1 && tempComboMax.getSelectedIndex() != -1) {
			tempSQL = " temperature <= " + (String)tempComboMax.getSelectedItem();
		}
		
		else if (tempComboMin.getSelectedIndex() != -1 && tempComboMax.getSelectedIndex() == -1) {
			tempSQL = " temperature >= " + (String)tempComboMin.getSelectedItem();
		}
		
		else if(tempComboMin.getSelectedIndex() != -1 && tempComboMax.getSelectedIndex() != -1){
			tempSQL = " temperature >= " + (String)tempComboMin.getSelectedItem() + " && temperature <= " + (String)tempComboMax.getSelectedItem();
		}
		
		return tempSQL;
	}
	
	public String getMixYPSQL() {
		String mixYPSQL = null;
		if(mixYPComboMin.getSelectedIndex() == -1 && mixYPComboMax.getSelectedIndex() != -1) {
			mixYPSQL = " mix_yield_point <= " + (String)mixYPComboMax.getSelectedItem();
		}
		
		else if(mixYPComboMin.getSelectedIndex() != -1 && mixYPComboMax.getSelectedIndex() == -1) {
			mixYPSQL = " mix_yield_point >= " + (String)mixYPComboMin.getSelectedItem();
		}
		
		else if(mixYPComboMin.getSelectedIndex() != -1 && mixYPComboMax.getSelectedIndex() != -1){
			mixYPSQL = " mix_yield_point >= " + (String)mixYPComboMin.getSelectedItem() + " && mix_yield_point <= " + (String)mixYPComboMax.getSelectedItem();
		}
		
		return mixYPSQL;
		
	}
	
	public String getTempYPSQL() {
		String tempYPSQL = null;
		if(tempYPComboMin.getSelectedIndex() == -1 && tempYPComboMax.getSelectedIndex() != -1) {
			tempYPSQL = " temp_yield_point <= " + (String)tempYPComboMax.getSelectedItem();
		}
		
		else if(tempYPComboMin.getSelectedIndex() != -1 && tempYPComboMax.getSelectedIndex() == -1) {
			tempYPSQL = " temp_yield_point >= " + (String)tempYPComboMin.getSelectedItem();
		}
		
		else if(tempYPComboMin.getSelectedIndex() != -1 && tempYPComboMax.getSelectedIndex() != -1){
			tempYPSQL = " temp_yield_point >= " + (String)tempYPComboMin.getSelectedItem() + " && temp_yield_point <= " + (String)tempYPComboMax.getSelectedItem();
		}
		
		return tempYPSQL;
	}
	
	public String getFluidLossSQL() {
		String fluidLossSQL = null;
		if(fluidComboMin.getSelectedIndex() == -1 && fluidComboMax.getSelectedIndex() != -1) {
			fluidLossSQL = " fluid_loss <= " + (String)fluidComboMax.getSelectedItem();
		}
		
		else if(fluidComboMin.getSelectedIndex() != -1 && fluidComboMax.getSelectedIndex() == -1) {
			fluidLossSQL = " fluid_loss >= " + (String)fluidComboMin.getSelectedItem();
		}
		
		else if(fluidComboMin.getSelectedIndex() != -1 && fluidComboMax.getSelectedIndex() != -1){
			fluidLossSQL = " fluid_loss >= " + (String)fluidComboMin.getSelectedItem() + " && fluid_loss <= " + (String)fluidComboMax.getSelectedItem();
		}
		
		return fluidLossSQL;
	}
	
	public String getSetTimeSQL() {
		String setTimeSQL = null;
		if(setTimeComboMin.getSelectedIndex() == -1 && setTimeComboMax.getSelectedIndex() != -1) {
			setTimeSQL = " set_time <= " + (String)setTimeComboMax.getSelectedItem();
		}
		
		else if(setTimeComboMin.getSelectedIndex() != -1 && setTimeComboMax.getSelectedIndex() == -1) {
			setTimeSQL = " set_time >= " + (String)setTimeComboMin.getSelectedItem();
		}
		
		else if(setTimeComboMin.getSelectedIndex() != -1 && setTimeComboMax.getSelectedIndex() != -1){
			setTimeSQL = " set_time >= " + (String)setTimeComboMin.getSelectedItem() + " && set_time <= " + (String)setTimeComboMax.getSelectedItem();
		}
		
		return setTimeSQL;
	}
	
	public String getStrengthSQL() {
		String strengthSQL = null;
		if(strengthComboMin.getSelectedIndex() == -1 && strengthComboMax.getSelectedIndex() != -1) {
			strengthSQL = " twelve_hour_strength <= " + (String)strengthComboMax.getSelectedItem();
		}
		
		else if(strengthComboMin.getSelectedIndex() != -1 && strengthComboMax.getSelectedIndex() == -1) {
			strengthSQL = " twelve_hour_strength >= " + (String)strengthComboMin.getSelectedItem();
		}
		
		else if(strengthComboMin.getSelectedIndex() != -1 && strengthComboMax.getSelectedIndex() != -1){
			strengthSQL = " twelve_hour_strength >= " + (String)strengthComboMin.getSelectedItem() + " && twelve_hour_strength <= " + (String)strengthComboMax.getSelectedItem();
		}
		
		return strengthSQL;
	}
	
	public void setReportSearchListener(ReportSearchListener listener) {
		this.reportSearchListener = listener;
	}
	
	public void listReports(HashMap<String, String> reports) {
		resultsPanel.removeAll();
		for(String key : reports.keySet()) {
			JLabel report = new JLabel(key);
			report.setFont(new Font("", Font.PLAIN, 20));
			report.setForeground(Color.BLUE);
			report.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			report.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				File file = new File(reports.get(key));
				try {
					desktop.getDesktop().open(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(report.getText());
				}
			
			
	       });
			resultsPanel.add(report);
		}
		resultsPanel.revalidate();
		resultsPanel.repaint();
	}
}