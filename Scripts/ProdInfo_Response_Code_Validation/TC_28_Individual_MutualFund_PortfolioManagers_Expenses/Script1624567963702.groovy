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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import customKeywords.PortalTestingKeywords as ptk
import customKeywords.UIKeywords as uik
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject

try {
    WebUI.navigateToUrl((GlobalVariable.ENV + GlobalVariable.IndividualPath) + 'mutual-funds/')
	WebUI.waitForPageLoad(20)
    'Click the first fund on Fund screener page'
    WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/FundScreener/FundScreenerTable_FirstFund'))
    WebUI.waitForPageLoad(20)
	
	String PrevUrl
	String ManagerInfo
	String ExpenseRatio
	String SalesCharges
	String cdscSales
	//String trailComm
		
    'On the first fund prod info page, capture the Fund selection dropdown contents'
    NoOfFunds = WebUI.getNumberOfTotalOption(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundDropDown'))
    String AllFundName = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundDropDown'))
    List<String> AllFundList = AllFundName.split('\\r?\\n' //Remove CRLF from each dropdown entry
        )	
    'Select fund from dropdown'
    for (int index = 1; index <= (NoOfFunds - 1); index++) {
        WebUI.selectOptionByIndex(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundDropDown'), index, FailureHandling.CONTINUE_ON_FAILURE)
        WebUI.waitForPageLoad(20)
		String FundName = AllFundList[index].toString()
        String url = WebUI.getUrl(FailureHandling.CONTINUE_ON_FAILURE)
        RequestObject ro = new RequestObject()
        ro.setRestRequestMethod('GET')
        ro.setRestUrl(url)
        ResponseObject resp = WS.sendRequest(ro)
        String statusCode = resp.getStatusCode()
        	if (statusCode.equals('200')) {
				ManagerCount = uik.getCurrentSessionDriver().findElements(By.xpath('//*[@id=\'management-team\']//div[@class=\'manager d-flex\']')).size()
				if (ManagerCount > 0) {
					ManagerInfo = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/ManagerName'), FailureHandling.CONTINUE_ON_FAILURE)
				} else {
					ManagerInfo = 'No manager assigned to this fund.'
				}
				ExpenseRatio = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/ExpenseRatio'), FailureHandling.CONTINUE_ON_FAILURE)
				SalesCharges = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/SalesCharges'), FailureHandling.CONTINUE_ON_FAILURE)
				cdscSales = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/cdscSales'), FailureHandling.CONTINUE_ON_FAILURE)
				//trailComm = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/trailComm'), FailureHandling.CONTINUE_ON_FAILURE)
				CustomKeywords.'excelKeywords.WriteExcel.demoKey'(url,  'Server response is ' + statusCode, 'Passed', 'Ind Open end funds-PE', index, ManagerInfo, ExpenseRatio, SalesCharges, cdscSales, 'N/A', 'N/A', 'N/A', 'N/A')
				PrevUrl = url
			} else {
            	CustomKeywords.'excelKeywords.WriteExcel.demoKey'(url,  'Server response is ' + statusCode, 'Failed', 'Ind Open end funds-PE', index, 'page down', 'page down', 'page down', 'page down', 'page down', 'page down', 'page down', 'page down')
				WebUI.navigateToUrl(PrevUrl)
			}
    }
}
catch (Exception ex) {
    KeywordUtil.markFailed(ex.getMessage())
}