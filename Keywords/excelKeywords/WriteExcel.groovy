package excelKeywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import internal.GlobalVariable
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {


	@Keyword
	public void demoKey(String url, String serverResponse, String status, String sheetName, int rowIndex, String fundName, String fundNameComp, String managerRows, String fundDescription, String objective, String strategyAndProcess, String fundFacts, String expenseRatio, String performanceAnnual, String performanceCumulative, String annualPerf, String perfSnapshot) throws IOException{

		FileInputStream fis = new FileInputStream("C:\\Users\\pdutta\\Desktop\\Prod_Info_Report.xlsm");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.createRow(rowIndex);
		Cell cellUrl = row.createCell(0);
		Cell cellCode = row.createCell(1);
		Cell cellStatus = row.createCell(2);
		Cell cellFundName = row.createCell(3);
		Cell cellFundNameComp = row.createCell(4);
		Cell cellManagerRows = row.createCell(5);
		Cell cellFundDescription = row.createCell(6);
		Cell cellObjective = row.createCell(7);
		Cell cellStrategyAndProcess = row.createCell(8);
		Cell cellFundFacts = row.createCell(9);
		Cell cellExpenseRatio = row.createCell(10);
		Cell cellPerformanceAnnual = row.createCell(11);
		Cell cellPerformanceCumulative = row.createCell(12);
		Cell cellAnnualPerf = row.createCell(13);
		Cell cellPerfSnapshot = row.createCell(14);
		cellUrl.setCellValue(url);
		cellCode.setCellValue(serverResponse);
		cellStatus.setCellValue(status);
		cellFundName.setCellValue(fundName);
		cellFundNameComp.setCellValue(fundNameComp);
		cellManagerRows.setCellValue(managerRows);
		cellFundDescription.setCellValue(fundDescription);
		cellObjective.setCellValue(objective);
		cellStrategyAndProcess.setCellValue(strategyAndProcess);
		cellFundFacts.setCellValue(fundFacts);
		cellExpenseRatio.setCellValue(expenseRatio);
		cellPerformanceAnnual.setCellValue(performanceAnnual);
		cellPerformanceCumulative.setCellValue(performanceCumulative);
		cellAnnualPerf.setCellValue(annualPerf);
		cellPerfSnapshot.setCellValue(perfSnapshot);
		FileOutputStream fos = new FileOutputStream("C:\\Users\\pdutta\\Desktop\\Prod_Info_Report.xlsm");
		workbook.write(fos);
		fos.close();
	}
}