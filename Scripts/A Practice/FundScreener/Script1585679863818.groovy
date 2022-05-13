import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.SelectorMethod as SelectorMethod
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import internal.GlobalVariable as GlobalVariable
import groovy.json.JsonSlurper as JsonSlurper
import java.util.ArrayList
import java.util.Arrays
import java.util.LinkedHashSet

MFListResponse= WS.sendRequest(findTestObject('UserRestService/FundList'))

def MFListslurper= new groovy.json.JsonSlurper()
def MFListresults= MFListslurper.parseText(MFListResponse.getResponseBodyContent())

List<String> MFFundNames= MFListresults.fundName
int fund_count= MFFundNames.size()
WebUI.comment("Fund List: "+MFFundNames)
WebUI.comment("Fund Count: "+fund_count)

LinkedHashSet<String> hashSet = new LinkedHashSet<>(MFFundNames)
ArrayList<String> FundlistWithoutDuplicates = new ArrayList<>(hashSet)
int Unique_fund_count= FundlistWithoutDuplicates.size()

WebUI.comment("Unique Fund List: "+FundlistWithoutDuplicates)
WebUI.comment("Unique Fund count: "+Unique_fund_count)

'Launching Individual website'
CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'(GlobalVariable.ENV + GlobalVariable.IndividualPath)

'Navigate to Fund screener page'
CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Funds')
WebUI.waitForPageLoad(20)

WebDriver driver = DriverFactory.getWebDriver()
WebElement Table = driver.findElement(By.xpath('//*[@id=\'fund-screener-table\']'))
List<WebElement> rows_table = Table.findElements(By.xpath('//*[@id=\'fund-screener-table\']//div[@data-column-index=\'1\']//a'))
int rows_count = rows_table.size()

List<String> MFListWeb = new ArrayList<String>()

for (int row = 0; row < rows_count; row++) {

String MFName= rows_table.get(row).getText()

if (MFName.contains("(")) {
    String[] MFNameMod1= MFName.split("\\(");
	MFNameMod= MFNameMod1[0]
} else {
    MFNameMod= MFName;  
}
WebUI.comment(MFNameMod)

MFListWeb.add(MFNameMod)

}
WebUI.comment("Fund list: "+MFListWeb)
