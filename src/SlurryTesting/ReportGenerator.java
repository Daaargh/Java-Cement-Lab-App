package SlurryTesting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import calculator.ReportFileFilter;
import model.CalcRow;

public class ReportGenerator {
	
	private String reportPath;
	private String reportName;
	private String temp;
	private String depth;
	private ArrayList<CalcRow> tableRows;
	private List<String> mixRheoArray;
	private List<String> tempRheoArray;
	private List<String> fluidLossArray;
	private List<String> thickeningArray;
	private List<String> compressiveArray;
	
	
	public void setSlurryList(ArrayList<CalcRow> array) {
		this.tableRows = array;
	}
	
	public void setTemp(String temp) {
		this.temp = temp;
	}
	
	public void setDepth(String depth) {
		this.depth = depth;
	}
	
	public void setMixRheoArray(List<String> mixRheoArray) {
		this.mixRheoArray = mixRheoArray;
	}
	
	public void setTempRheoArray(List<String> tempRheoArray) {
		this.tempRheoArray = tempRheoArray;
	}
	
	public void setFluidLossArray(List<String> fluidLossArray) {
		this.fluidLossArray = fluidLossArray;
	}
	
	public void setThickeningArray(List<String> thickeningArray2) {
		this.thickeningArray = thickeningArray2;
	}
	
	public void setCompressiveArray(List<String> compressiveArray) {
		this.compressiveArray = compressiveArray;
	}
	
	public void createTableWord(final String fileName) {
		XWPFDocument doc = new XWPFDocument();
		
		try {
			XWPFParagraph titlePara = doc.createParagraph();
			XWPFRun run = titlePara.createRun();
			
			run.setFontSize(20);
			run.setFontFamily("Calibri");
			run.setBold(true);
			run.setText("Slurry Report");

			titlePara.setAlignment(ParagraphAlignment.CENTER);

			
			XWPFParagraph infoPara = doc.createParagraph();
			XWPFRun run2 = infoPara.createRun();
			
			run2.setText("Temperature: " + temp);
			run2.addCarriageReturn();
			run2.setText("Depth (feet): " + depth);
			run2.addCarriageReturn();
			
			XWPFParagraph tablePara = doc.createParagraph();
			XWPFRun run3 = tablePara.createRun();
			run3.setFontSize(15);
			run3.setFontFamily("Calibri");
			run3.setBold(true);
			run3.setText("Slurry Design");
			run3.addCarriageReturn();
			
			createSlurryTable(doc);			
			
			XWPFParagraph testPara = doc.createParagraph();
			XWPFRun run4 = testPara.createRun();
			
			run4.addCarriageReturn();
			run4.setFontSize(15);
			run4.setFontFamily("Calibri");
			run4.setBold(true);
			run4.setText("Test Results");
			run4.addCarriageReturn();
			
			XWPFParagraph mixRheoPara = doc.createParagraph();
			XWPFRun run5 = mixRheoPara.createRun();
			
			run5.setFontSize(12);
			run5.setFontFamily("Calibri");
			run5.setBold(true);
			run5.setText("Rheology at mix");
			run5.addCarriageReturn();
			
			createMixRheoTable(doc);
			
			XWPFParagraph tempRheoPara = doc.createParagraph();
			XWPFRun run6 = tempRheoPara.createRun();
			
			run6.addCarriageReturn();
			run6.setFontSize(12);
			run6.setFontFamily("Calibri");
			run6.setBold(true);
			run6.setText("Rheology at temp");
			
			createTempRheoTable(doc);
			
			XWPFParagraph fluidLossPara = doc.createParagraph();
			XWPFRun run7 = fluidLossPara.createRun();
			run7.addCarriageReturn();
			run7.setFontSize(12);
			run7.setFontFamily("Calibri");
			run7.setBold(true);
			run7.setText("Fluid Loss");
			
			createFluidLossTable(doc);
			
			XWPFParagraph thickeningPara = doc.createParagraph();
			thickeningPara.setPageBreak(true);
			XWPFRun run8 = thickeningPara.createRun();
			run8.setBold(true);
			run8.setFontSize(12);
			run8.setFontFamily("Calibri");
			run8.setText("Thickening Time");
			run8.addCarriageReturn();
			
			createThickeningTable(doc);
			
			XWPFParagraph compressivePara = doc.createParagraph();
			XWPFRun run9 = compressivePara.createRun();
			run9.setBold(true);
			run9.addCarriageReturn();
			run9.setFontSize(12);
			run9.setFontFamily("Calibri");
			run9.setText("Compressive Strength");
			run9.addCarriageReturn();
			
			createCompressiveTable(doc);
			
			
			
			OutputStream out = null;
			String p = "../Reports/";
			JFileChooser fileChooser = new JFileChooser(p){
			    @Override
			    public void approveSelection(){
			        File f = new File((String.valueOf(getSelectedFile())) + ".docx");
			        reportPath = (String.valueOf(getSelectedFile())) + ".docx";
			        reportName = (String.valueOf(getSelectedFile().getName()));
			        if(f.exists() && getDialogType() == SAVE_DIALOG){
			            int result = JOptionPane.showConfirmDialog(this,"The file exists, overwrite?","Existing file",JOptionPane.YES_NO_CANCEL_OPTION);
			            switch(result){
			                case JOptionPane.YES_OPTION:
			                    super.approveSelection();
			                    return;
			                case JOptionPane.NO_OPTION:
			                    return;
			                case JOptionPane.CLOSED_OPTION:
			                    return;
			                case JOptionPane.CANCEL_OPTION:
			                    cancelSelection();
			                    return;
			            }
			        }
			        super.approveSelection();
			    }        
			};
			//JFileChooser fileChooser = new JFileChooser(p);
			//fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	        fileChooser.addChoosableFileFilter(new ReportFileFilter());
			/*
			String r = "";
			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				
				r = String.valueOf(fileChooser.getSelectedFile().getName() + ".docx");
				File file = new File(r);
			} */
	        File file = new File("");
			String r = "";
			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				r = String.valueOf(fileChooser.getSelectedFile().getName() + ".docx");
				//reportPath = p + r;
				//reportPath = String.valueOf(fileChooser.getSelectedFile());
				file = new File(p + r);
			}
			try {
				out = new FileOutputStream(file);
				doc.write(out);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} finally {
			try {
				doc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void createSlurryTable(XWPFDocument doc) {
		int numRows = tableRows.size();
		int row = 1;
		int cell = 0;
		
		// create table with 3 rows and 4 columns

		XWPFTable slurryTable = doc.createTable(numRows + 1, 5);
		
		for(int i = 0; i <= tableRows.size(); i++) {
			for(int j = 0; j < 5; j++) {
				if(i == 0) {
					slurryTable.getRow(i).getCell(j).setColor("CFDA74");
				}
				else {
					slurryTable.getRow(i).getCell(j).setColor("F6E7CB");
				}
			}
		}
		// write to first row, first column
		XWPFParagraph rowOneCellOne = slurryTable.getRow(0).getCell(0).getParagraphs().get(0);
		rowOneCellOne.setAlignment(ParagraphAlignment.CENTER);
		//slurryTable.getRow(0).getCell(0).setColor("5B7B7A");
		XWPFRun r1 = rowOneCellOne.createRun();
		r1.setBold(true);
		r1.setText("Additive");
		
		// write to first row, second column
		XWPFParagraph rowOneCellTwo = slurryTable.getRow(0).getCell(1).getParagraphs().get(0);
		rowOneCellTwo.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r2 = rowOneCellTwo.createRun();
		r2.setBold(true);
		r2.setText("lbs");
		
		// write to first row, third column
		XWPFParagraph rowOneCellThree = slurryTable.getRow(0).getCell(2).getParagraphs().get(0);
		rowOneCellThree.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r3 = rowOneCellThree.createRun();
		r3.setBold(true);
		r3.setText("gals/sack");
		
		// write to first row, fourth column
		XWPFParagraph rowOneCellFour = slurryTable.getRow(0).getCell(3).getParagraphs().get(0);
		rowOneCellFour.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r4 = rowOneCellFour.createRun();
		r4.setBold(true);
		r4.setText("grams");
		
		// write to first row, fourth column
		XWPFParagraph rowOneCellFive = slurryTable.getRow(0).getCell(4).getParagraphs().get(0);
		rowOneCellFive.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r5 = rowOneCellFive.createRun();
		r5.setBold(true);
		r5.setText("mls");
		
		for(int i = 0; i < tableRows.size(); i++) {
			slurryTable.getRow(row).getCell(cell).setText(tableRows.get(i).getAdditive());
			cell++;
			
			/*slurryTable.getRow(row).getCell(cell).setText(String.valueOf((tableRows.get(i).getLbs())));
			cell++; */
			
			slurryTable.getRow(row).getCell(cell).setText(String.format("%.2f", tableRows.get(i).getLbs()));
			cell++;
			
			/*slurryTable.getRow(row).getCell(cell).setText(String.valueOf((tableRows.get(i).getGals())));
			cell++; */
			
			slurryTable.getRow(row).getCell(cell).setText(String.format("%.2f", tableRows.get(i).getGals()));
			cell++;
			
			/*slurryTable.getRow(row).getCell(cell).setText(String.valueOf((tableRows.get(i).getGrams())));
			cell++; */
			
			slurryTable.getRow(row).getCell(cell).setText(String.format("%.2f", tableRows.get(i).getGrams()));
			cell++;
			
			//slurryTable.getRow(row).getCell(cell).setText(String.valueOf((tableRows.get(i).getMls())));
			
			slurryTable.getRow(row).getCell(cell).setText(String.format("%.2f", tableRows.get(i).getMls()));
			
			row++;
			cell = 0;
		}
	}
	
	public void createMixRheoTable(XWPFDocument doc) {
		int numRows = 3;
		int row = 1;
		int cell = 0;
		
		// create table with 3 rows and 4 columns

		XWPFTable mixRheoTable = doc.createTable(numRows, 9);
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 9; j++) {
				if(i == 0) {
					mixRheoTable.getRow(i).getCell(j).setColor("CFDA74");
				}
				else {
					mixRheoTable.getRow(i).getCell(j).setColor("F6E7CB");
				}
			}
		}
		mixRheoTable.setCellMargins(0, 60, 0, 60);
		mixRheoTable.setWidth(150);

		// write to first row, first column
		XWPFParagraph rowOneCellOne = mixRheoTable.getRow(0).getCell(0).getParagraphs().get(0);
		rowOneCellOne.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r1 = rowOneCellOne.createRun();
		r1.setBold(true);
		r1.setText("3");
		
		// write to first row, second column
		XWPFParagraph rowOneCellTwo = mixRheoTable.getRow(0).getCell(1).getParagraphs().get(0);
		rowOneCellTwo.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r2 = rowOneCellTwo.createRun();
		r2.setBold(true);
		r2.setText("6");
		
		// write to first row, third column
		XWPFParagraph rowOneCellThree = mixRheoTable.getRow(0).getCell(2).getParagraphs().get(0);
		rowOneCellThree.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r3 = rowOneCellThree.createRun();
		r3.setBold(true);
		r3.setText("30");
		
		// write to first row, fourth column
		XWPFParagraph rowOneCellFour = mixRheoTable.getRow(0).getCell(3).getParagraphs().get(0);
		rowOneCellFour.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r4 = rowOneCellFour.createRun();
		r4.setBold(true);
		r4.setText("60");
		
		// write to first row, fourth column
		XWPFParagraph rowOneCellFive = mixRheoTable.getRow(0).getCell(4).getParagraphs().get(0);
		rowOneCellFive.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r5 = rowOneCellFive.createRun();
		r5.setBold(true);
		r5.setText("100");
		
		// write to first row, second column
		XWPFParagraph rowOneCellSix = mixRheoTable.getRow(0).getCell(5).getParagraphs().get(0);
		rowOneCellSix.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r6 = rowOneCellSix.createRun();
		r6.setBold(true);
		r6.setText("200");
		
		// write to first row, third column
		XWPFParagraph rowOneCellSeven = mixRheoTable.getRow(0).getCell(6).getParagraphs().get(0);
		rowOneCellSeven.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r7 = rowOneCellSeven.createRun();
		r7.setBold(true);
		r7.setText("300");
		
		// write to first row, fourth column
		XWPFParagraph rowOneCellEight = mixRheoTable.getRow(0).getCell(7).getParagraphs().get(0);
		rowOneCellEight.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r8 = rowOneCellEight.createRun();
		r8.setBold(true);
		r8.setText("PV");
		
		// write to first row, fourth column
		XWPFParagraph rowOneCellNine = mixRheoTable.getRow(0).getCell(8).getParagraphs().get(0);
		rowOneCellNine.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r9 = rowOneCellNine.createRun();
		r9.setBold(true);
		r9.setText("yP");

		for(int i = 0; i < 9; i++) {
			mixRheoTable.getRow(1).getCell(i).setText(String.valueOf((mixRheoArray.get(i))));
		}
		for(int i = 9; i < 18; i++) {
			mixRheoTable.getRow(2).getCell(i - 9).setText(String.valueOf((mixRheoArray.get(i))));
		}
		
	}
	
	public void createTempRheoTable(XWPFDocument doc) {
		int numRows = 3;
		int row = 1;
		int cell = 0;
		
		// create table with 3 rows and 4 columns

		XWPFTable tempRheoTable = doc.createTable(numRows, 9);
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 9; j++) {
				if(i == 0) {
					tempRheoTable.getRow(i).getCell(j).setColor("CFDA74");
				}
				else {
					tempRheoTable.getRow(i).getCell(j).setColor("F6E7CB");
				}
			}
		}
		tempRheoTable.setCellMargins(0, 60, 0, 60);
		tempRheoTable.setWidth(150);
		// write to first row, first column
		XWPFParagraph rowOneCellOne = tempRheoTable.getRow(0).getCell(0).getParagraphs().get(0);
		rowOneCellOne.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r1 = rowOneCellOne.createRun();
		r1.setBold(true);
		r1.setText("3");
		
		// write to first row, second column
		XWPFParagraph rowOneCellTwo = tempRheoTable.getRow(0).getCell(1).getParagraphs().get(0);
		rowOneCellTwo.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r2 = rowOneCellTwo.createRun();
		r2.setBold(true);
		r2.setText("6");
		
		// write to first row, third column
		XWPFParagraph rowOneCellThree = tempRheoTable.getRow(0).getCell(2).getParagraphs().get(0);
		rowOneCellThree.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r3 = rowOneCellThree.createRun();
		r3.setBold(true);
		r3.setText("30");
		
		// write to first row, fourth column
		XWPFParagraph rowOneCellFour = tempRheoTable.getRow(0).getCell(3).getParagraphs().get(0);
		rowOneCellFour.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r4 = rowOneCellFour.createRun();
		r4.setBold(true);
		r4.setText("60");
		
		// write to first row, fourth column
		XWPFParagraph rowOneCellFive = tempRheoTable.getRow(0).getCell(4).getParagraphs().get(0);
		rowOneCellFive.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r5 = rowOneCellFive.createRun();
		r5.setBold(true);
		r5.setText("100");
		
		// write to first row, second column
		XWPFParagraph rowOneCellSix = tempRheoTable.getRow(0).getCell(5).getParagraphs().get(0);
		rowOneCellSix.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r6 = rowOneCellSix.createRun();
		r6.setBold(true);
		r6.setText("200");
		
		// write to first row, third column
		XWPFParagraph rowOneCellSeven = tempRheoTable.getRow(0).getCell(6).getParagraphs().get(0);
		rowOneCellSeven.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r7 = rowOneCellSeven.createRun();
		r7.setBold(true);
		r7.setText("300");
		
		// write to first row, fourth column
		XWPFParagraph rowOneCellEight = tempRheoTable.getRow(0).getCell(7).getParagraphs().get(0);
		rowOneCellEight.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r8 = rowOneCellEight.createRun();
		r8.setBold(true);
		r8.setText("PV");
		
		// write to first row, fourth column
		XWPFParagraph rowOneCellNine = tempRheoTable.getRow(0).getCell(8).getParagraphs().get(0);
		rowOneCellNine.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r9 = rowOneCellNine.createRun();
		r9.setBold(true);
		r9.setText("yP");
		
		for(int i = 0; i < 9; i++) {
			tempRheoTable.getRow(1).getCell(i).setText(String.valueOf((tempRheoArray.get(i))));
		}
		for(int i = 9; i < 18; i++) {
			tempRheoTable.getRow(2).getCell(i - 9).setText(String.valueOf((tempRheoArray.get(i))));
		}
	}
		
	public void createFluidLossTable(XWPFDocument doc) {
		int numRows = 2;
			
		// create table with 3 rows and 4 columns

		XWPFTable fluidLossTable = doc.createTable(numRows, 3);
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 3; j++) {
				if(i == 0) {
					fluidLossTable.getRow(i).getCell(j).setColor("CFDA74");
				}
				else {
					fluidLossTable.getRow(i).getCell(j).setColor("F6E7CB");

				}
			}
		}
		
		fluidLossTable.setCellMargins(0, 60, 0, 60);
		// write to first row, first column
		XWPFParagraph rowOneCellOne = fluidLossTable.getRow(0).getCell(0).getParagraphs().get(0);
		rowOneCellOne.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r1 = rowOneCellOne.createRun();
		r1.setBold(true);
		r1.setText("Fluid Lost");
		
		// write to first row, second column
		XWPFParagraph rowOneCellTwo = fluidLossTable.getRow(0).getCell(1).getParagraphs().get(0);
		rowOneCellTwo.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r2 = rowOneCellTwo.createRun();
		r2.setBold(true);
		r2.setText("Time (mins)");
		
		// write to first row, third column
		XWPFParagraph rowOneCellThree = fluidLossTable.getRow(0).getCell(2).getParagraphs().get(0);
		rowOneCellThree.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r3 = rowOneCellThree.createRun();
		r3.setBold(true);
		if(Double.parseDouble(fluidLossArray.get(1)) < 30) {
			r3.setText("Calculated API Fluid Loss");
		}
		else {
			r3.setText("API Fluid Loss");
		}
		
		
			
		fluidLossTable.getRow(1).getCell(0).setText(String.valueOf((fluidLossArray.get(0))));			
		fluidLossTable.getRow(1).getCell(1).setText(String.valueOf((fluidLossArray.get(1))));
		fluidLossTable.getRow(1).getCell(2).setText(String.valueOf((fluidLossArray.get(2))));
		
	}
	
	public void createThickeningTable(XWPFDocument doc) {
		int numRows = 5;
		int row = 1;
		int cell = 1;
			
		// create table with 3 rows and 4 columns

		XWPFTable thickeningTable = doc.createTable(numRows, 2);
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 2; j++) {
				if(i == 0) {
					thickeningTable.getRow(i).getCell(j).setColor("CFDA74");
				}
				else {
					thickeningTable.getRow(i).getCell(j).setColor("F6E7CB");
				}
			}
		}
		thickeningTable.setCellMargins(0, 60, 0, 60);
		// write to first row, first column
		XWPFParagraph rowOneCellOne = thickeningTable.getRow(0).getCell(0).getParagraphs().get(0);
		rowOneCellOne.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r1 = rowOneCellOne.createRun();
		r1.setBold(true);
		r1.setText("BC");
		
		// write to first row, second column
		XWPFParagraph rowOneCellTwo = thickeningTable.getRow(0).getCell(1).getParagraphs().get(0);
		rowOneCellTwo.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r2 = rowOneCellTwo.createRun();
		r2.setBold(true);
		r2.setText("Time (mins)");
		
		thickeningTable.getRow(1).getCell(0).setText("30");
		thickeningTable.getRow(2).getCell(0).setText("50");
		thickeningTable.getRow(3).getCell(0).setText("70");
		thickeningTable.getRow(4).getCell(0).setText("100");

		
		for(int i = 0; i < thickeningArray.size(); i++) {
			thickeningTable.getRow(row).getCell(cell).setText(String.valueOf((thickeningArray.get(i))));
			row++;
		}
	}
	
	public void createCompressiveTable(XWPFDocument doc) {
		int numRows = 4;
		int row = 1;
		int cell = 1;
			
		// create table with 3 rows and 4 columns

		XWPFTable compressiveTable = doc.createTable(numRows, 2);
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 2; j++) {
				if(i == 0) {
					compressiveTable.getRow(i).getCell(j).setColor("CFDA74");
				}
				else {
					compressiveTable.getRow(i).getCell(j).setColor("F6E7CB");
				}
			}
		}
		
		compressiveTable.setCellMargins(0, 60, 0, 60);
		// write to first row, first column
		XWPFParagraph rowOneCellOne = compressiveTable.getRow(0).getCell(0).getParagraphs().get(0);
		rowOneCellOne.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r1 = rowOneCellOne.createRun();
		r1.setBold(true);
		r1.setText("Hours");
		
		// write to first row, second column
		XWPFParagraph rowOneCellTwo = compressiveTable.getRow(0).getCell(1).getParagraphs().get(0);
		rowOneCellTwo.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r2 = rowOneCellTwo.createRun();
		r2.setBold(true);
		r2.setText("Compressive Strength (psi)");
		
		compressiveTable.getRow(1).getCell(0).setText("6");
		compressiveTable.getRow(2).getCell(0).setText("12");
		compressiveTable.getRow(3).getCell(0).setText("24");
		
		for(int i = 0; i < compressiveArray.size(); i++) {
			compressiveTable.getRow(row).getCell(cell).setText(String.valueOf((compressiveArray.get(i))));
			row++;
		} 
	}
	
	public ArrayList<String> getReportData() {
		/*
		 * Creates an ArrayList<String> containing:
		 * 0: Report path
		 * 1: Temperature
		 * 2: mixRheo yield point
		 * 3: tempRheo yield point
		 * 4: Fluid lost
		 * 5: Set time
		 * 6: Compressive strength after 12 hours
		 */
		ArrayList<String> reportData = new ArrayList<String>();
		reportData.add(this.reportPath);
		reportData.add(this.reportName);
		reportData.add(this.temp);
		reportData.add(mixRheoArray.get(8));
		reportData.add(tempRheoArray.get(8));
		reportData.add(fluidLossArray.get(2));
		reportData.add(thickeningArray.get(3));
		reportData.add(compressiveArray.get(1));
		
		return reportData;
	}
}
