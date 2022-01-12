package qc;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

public class QCTestFormPanel extends JPanel {
	private JLabel productLabel;
	private JComboBox productCombo;
	private QCTestFormListener testFormPanelListener;
	private DefaultComboBoxModel productComboModel;
	private String filePath;
	
	public QCTestFormPanel() {
		productLabel = new JLabel("Product: ");
		productLabel.setFont(new Font("", Font.PLAIN, 16));
		productComboModel = new DefaultComboBoxModel();
		productCombo = new JComboBox();
		productCombo.setModel(productComboModel);
		productComboModel.addElement("Product");
		productCombo.setSelectedIndex(0);
		
		layoutComponents();
		
		productCombo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String product = getProductName();
				if(product == null || product == "Product") {
					if (testFormPanelListener != null) {
						testFormPanelListener.clearQCTestArray();
						testFormPanelListener.clearTable();
					}
					return;
				}
				if (testFormPanelListener != null) {
					testFormPanelListener.clearQCTestArray();
					testFormPanelListener.setFilePath(product);
				}
				
				ArrayList<ArrayList<String>> productDetails = new ArrayList<ArrayList<String>>();
				try {
					productDetails = getProductDetails(filePath);
				} catch (InvalidFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				ArrayList<String> components = productDetails.get(0);
				ArrayList<String> amounts = productDetails.get(1);
				
				for(int i = 0; i < components.size(); i++) {
					String component = components.get(i);
					String amount = amounts.get(i);
					
					QCTestFormEvent ev = new QCTestFormEvent(this, component, amount);
					if (testFormPanelListener != null) {
						testFormPanelListener.uploadProductDetails(ev);
					}
				}
			}
			
		});
	}
	
	private void layoutComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		//////// First Row /////////
		
		gc.gridy = 0;
		gc.weightx = 0;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(5, 5, 5, 0);
		add(productLabel, gc);
		
		gc.gridx++;
		gc.weightx = 40;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(5, 0, 5, 5);
		add(productCombo, gc);
		}
	
	public ArrayList<ArrayList<String>> getProductDetails(String filePath) throws InvalidFormatException, IOException {
		
		ArrayList<String> components = new ArrayList<String>();
		ArrayList<String> amounts = new ArrayList<String>();
		ArrayList<ArrayList<String>> compsAndAmounts = new ArrayList<ArrayList<String>>();
		
		//XWPFDocument doc = new XWPFDocument(OPCPackage.open(filePath));
		XWPFDocument doc = new XWPFDocument(new FileInputStream(filePath));
		XWPFTable table = doc.getTableArray(0);
		for(int i = 1; i < table.getRows().size(); i++ ) {
		
			String component = table.getRow(i).getCell(0).getText();
			components.add(component);
				
			String amount = table.getRow(i).getCell(2).getText();
			amounts.add(amount);
			}
		
		compsAndAmounts.add(components);
		compsAndAmounts.add(amounts);
		doc.close();
		return compsAndAmounts;
		}
	
	public void populateProductCombo(ArrayList<String> productArray) {
		productComboModel.removeAllElements();
		ArrayList<String> products = productArray;
		products.add(0, "Product");
		for(String product : products) {
			productComboModel.addElement(product);
		}
		productCombo.setSelectedIndex(0);
	}
	
	public void setFilePath(String path) {
		this.filePath = path;
		}
	
	public String getProductName() {
		return (String)productCombo.getSelectedItem();
		}
	
	public String getFilePath() {
		return filePath;
		}
	
	public void setFormListener(QCTestFormListener listener) {
		this.testFormPanelListener = listener;
		}
	}

