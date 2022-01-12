package qc;

import java.awt.BorderLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JPanel;

import model.QCFormTableRow;

public class QCTestPanel extends JPanel {
	private QCTestPanelListener listener;
	private QCTestFormPanel qcTestFormPanel;
	private QCTestTablePanel qcTestTablePanel;
	private QCTestButtonPanel qcTestButtonPanel;
	private JPanel mainPanel;
	private int testNumber;
	
	public QCTestPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		qcTestFormPanel = new QCTestFormPanel();
		qcTestTablePanel = new QCTestTablePanel();
		qcTestButtonPanel = new QCTestButtonPanel();
		
		qcTestFormPanel.setFormListener(new QCTestFormListener() {
			public void clearQCTestArray() {
				if(listener != null) {
					listener.clearQCTestArray();
					}
				}
			public void uploadProductDetails(QCTestFormEvent ev) {
				if(listener != null) {
					listener.addQCTestTableRow(ev);
					qcTestTablePanel.refresh();
				}
			}

			public void setFilePath(String product) {
				if(listener != null) {
					String filePath = listener.getQCTemplateFilePath(product);
					qcTestFormPanel.setFilePath(filePath);
					}
				}
			
			@Override
			public void clearTable() {
				qcTestTablePanel.refresh();
			}

		});
		
		qcTestButtonPanel.setListener(new QCTestButtonListener() {
			
			@Override
			public void createPendingQCTest() {
				String productName = qcTestFormPanel.getProductName();
				String filePath = qcTestFormPanel.getFilePath();
				String testNumber = null;
				try {
					testNumber = String.valueOf(getQCTestNumber());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(listener != null) {
					listener.createPendingQCTest(productName, filePath, testNumber);
				}
			}
			
			public void uploadPendingQCTest() {
				if(listener != null) {
					listener.storePendingQCTest();
				}
			}
		});
		
		setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(qcTestFormPanel, BorderLayout.NORTH);
		mainPanel.add(qcTestTablePanel, BorderLayout.CENTER);
		mainPanel.add(qcTestButtonPanel, BorderLayout.SOUTH);
		}
	
	public int getQCTestNumber() throws FileNotFoundException {
		
        File file = new File("qcTestNumber.txt");
        // Checks if file does not exist. If it does not, it creates it
        if (!file.exists()) {
            FileWriter fWriter = null;
            testNumber = 1;
			try {
				fWriter = new FileWriter(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			
	        try (Writer writer = new BufferedWriter(
	                new OutputStreamWriter(new FileOutputStream(file), "utf-8"))) { // sets the output where to write
	                    writer.write(String.valueOf(testNumber));
	                    }

	        catch (IOException e) {
	        	//
	        	}
	        }
        
        else if(file.exists()){
            //////////////////////////////
            //creating File instance to reference text file in Java
            //File text = new File("qcTestNumber.txt");

            //Creating Scanner instnace to read File in Java
            Scanner scnr = new Scanner(file);

            //Reading each line of file using Scanner class
            testNumber = Integer.parseInt(scnr.nextLine());
            testNumber++;
	        try (Writer writer = new BufferedWriter(
	                new OutputStreamWriter(new FileOutputStream(file), "utf-8"))) { // sets the output where to write
	                    writer.write(String.valueOf(testNumber));
	                    }

	        catch (IOException e) {
	        	//
	        	}
	        
            }
        return testNumber;
        }
	
	public void populateTestFormCombo(ArrayList<String> products) {
		qcTestFormPanel.populateProductCombo(products);
		}
	
	public void setTestTableData(List<QCFormTableRow> qcFormTableRows) {
		qcTestTablePanel.setData(qcFormTableRows);
		}
	
	public void setListener(QCTestPanelListener listener) {
		this.listener = listener;
	}
	}