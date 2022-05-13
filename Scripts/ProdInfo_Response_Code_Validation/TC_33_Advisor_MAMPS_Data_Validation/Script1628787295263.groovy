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
    WebUI.navigateToUrl((GlobalVariable.ENV + GlobalVariable.AdvisorPath) + 'investments/model-portfolios/portfolios/824')
	WebUI.waitForPageLoad(20)
	String PrevUrl = WebUI.getUrl()
    
	String ManagerInfo
	String FundNameSite
	String FundDescription
	String StrategyAndProcess
	String FundFacts
	String FundNameDropdown
	String PerformanceAnnual
	String PerformanceCumulative
	String AnnualPerf
	String PerfSnapshot
	
    'Capture the Fund selection dropdown contents'
    NoOfFunds = WebUI.getNumberOfTotalOption(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundDropDown'))
    String AllFundName = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundDropDown'))
    List<String> AllFundList = AllFundName.split('\\r?\\n' //Remove CRLF from each dropdown entry
        )

    'Select fund from dropdown'
    for (int index = 1; index <= (NoOfFunds - 1); index++) {
        WebUI.selectOptionByIndex(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundDropDown'), index, 
            FailureHandling.CONTINUE_ON_FAILURE)

        WebUI.waitForPageLoad(20)
		FundNameDropdown = AllFundList[index].toString()
		String url = WebUI.getUrl(FailureHandling.CONTINUE_ON_FAILURE)
        RequestObject ro = new RequestObject()
        ro.setRestRequestMethod('GET')
        ro.setRestUrl(url)
        ResponseObject resp = WS.sendRequest(ro)
        String statusCode = resp.getStatusCode()

        if (statusCode.equals('200')) {
            KeywordUtil.markPassed((url + '  Server response is ') + statusCode)
			ManagerInfo = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/ManagerName'), FailureHandling.CONTINUE_ON_FAILURE)
			FundNameSite = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundName1'), FailureHandling.CONTINUE_ON_FAILURE)
			FundDescription = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundDescription'), FailureHandling.CONTINUE_ON_FAILURE)
			StrategyAndProcess = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/StrategyAndProcess'), FailureHandling.CONTINUE_ON_FAILURE)
			FundFacts = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundFacts'), FailureHandling.CONTINUE_ON_FAILURE)
			PerformanceAnnual = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/PerformanceAnnual'), FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.check(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/cumulativeButton'),
				FailureHandling.CONTINUE_ON_FAILURE)
			PerformanceCumulative = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/PerformanceCumulative'), FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/annualPerfTab'), FailureHandling.CONTINUE_ON_FAILURE)
			AnnualPerf = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/AnnualPerf'), FailureHandling.CONTINUE_ON_FAILURE)
			PerfSnapshot = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/PerfSnapshot'), FailureHandling.CONTINUE_ON_FAILURE)
			
			CustomKeywords.'excelKeywords.WriteExcel.demoKey'(url,  'Server response is ' + statusCode, 'Passed', 'Advisor Model Portfolios', index, FundNameSite, FundNameDropdown, ManagerInfo, FundDescription, 'N/A', StrategyAndProcess, FundFacts, 'N/A', PerformanceAnnual, PerformanceCumulative, AnnualPerf, PerfSnapshot)
			PrevUrl = url
		} else {
            KeywordUtil.markFailed('Server response is ' + statusCode)
			CustomKeywords.'excelKeywords.WriteExcel.demoKey'(url,  'Server response is ' + statusCode, 'Failed', 'Advisor Model Portfolios', index, 'page down', 'page down', 'page down', 'page down', 'page down', 'page down', 'page down', 'page down', 'page down', 'page down', 'page down', 'page down')
			WebUI.navigateToUrl(PrevUrl)
		}
    }
}
catch (Exception ex) {
    KeywordUtil.markFailed(ex.getMessage())
}