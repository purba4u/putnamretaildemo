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
    //CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'((GlobalVariable.ENV + GlobalVariable.DCIOPath) + 'mutual-funds/')
	WebUI.navigateToUrl((GlobalVariable.ENV + GlobalVariable.DCIOPath) + 'mutual-funds/')
    WebUI.waitForPageLoad(20)
	
	TestObject LoginFrom = findTestObject('DCIO/LoginForm')
	if (WebUI.waitForElementPresent(LoginFrom, 10)) {
		uik.getCurrentSessionDriver().findElement(By.xpath('//input[@id=\'primaryEmailInput\']')).sendKeys(GlobalVariable.AdvisorEmail)
		uik.getCurrentSessionDriver().findElement(By.xpath('//button[@id=\'submit-email-form\']')).click()
		sleep(5000)
		WebUI.waitForPageLoad(20)		
	}

    'Click the first fund on Fund screener page'
    WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/FundScreener/FundScreenerTable_FirstFund'))
    WebUI.waitForPageLoad(20)
		
	String PrevUrl
	String FullFundNameSite
	String FundNameSite
	String FundNameComp
	String ManagerRows
	String FundDescription
	String Objective
	String StrategyAndProcess
	String FundFacts
	String PerformanceAnnual
	String PerformanceCumulative
	String AnnualPerf
	String PerfSnapshot
	
    'On the first fund prod info page, capture the Fund selection dropdown contents'
    NoOfFunds = WebUI.getNumberOfTotalOption(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundDropDown'))
    String AllFundName = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundDropDown'))
    List<String> AllFundList = AllFundName.split('\\r?\\n' //Remove CRLF from each dropdown entry
        )
		
	int rowIndex = 1	
    'Select fund from dropdown'
    for (int index = 32; index <= (NoOfFunds - 27); index++) {
        WebUI.selectOptionByIndex(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundDropDown'), index, 
            FailureHandling.CONTINUE_ON_FAILURE)

        WebUI.waitForPageLoad(20)
		String FundName = AllFundList[index].toString()

        'On the prod info page, capture the Share class selection dropdown contents'
        NoOfShareClass = WebUI.getNumberOfTotalOption(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/ShareClass'), 
            FailureHandling.CONTINUE_ON_FAILURE)

        String AllShareClass = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/ShareClass'), 
            FailureHandling.CONTINUE_ON_FAILURE)
		
		if (!AllShareClass.equals(null)) {
        List<String> AllSHClassList = AllShareClass.split('\\r?\\n' //Remove CRLF from each dropdown entry
            )
		
        'Select share class from dropdown'
        for (int sindex = 0; sindex <= (NoOfShareClass - 1); sindex++) {
            WebUI.selectOptionByIndex(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/ShareClass'), sindex, 
                FailureHandling.CONTINUE_ON_FAILURE)

            WebUI.waitForPageLoad(20)
            String url = WebUI.getUrl(FailureHandling.CONTINUE_ON_FAILURE)
            RequestObject ro = new RequestObject()
            ro.setRestRequestMethod('GET')
            ro.setRestUrl(url)
            ResponseObject resp = WS.sendRequest(ro)
            String statusCode = resp.getStatusCode()

            if (statusCode.equals('200')) {
            KeywordUtil.markPassed((url + '  Server response is ') + statusCode)
			FullFundNameSite = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundName1'), FailureHandling.CONTINUE_ON_FAILURE)
			if (!FullFundNameSite.equals(null)) {
				FundNameSite = FullFundNameSite.replaceAll('[ ]{2,}\\(.{5}\\)', '')
			}
			ManagerCount = uik.getCurrentSessionDriver().findElements(By.xpath('//*[@id=\'management-team\']//div[@class=\'manager d-flex\']')).size()
			if (ManagerCount > 0) {
				ManagerRows = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/ManagerName'), FailureHandling.CONTINUE_ON_FAILURE)
			} else {
				ManagerRows = 'No manager assigned to this fund.'
			}
			FundDescription = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundDescription'), FailureHandling.CONTINUE_ON_FAILURE)	
			Objective = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/Objective'), FailureHandling.CONTINUE_ON_FAILURE)			
			StrategyAndProcess = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/StrategyAndProcess'), FailureHandling.CONTINUE_ON_FAILURE)			
			FundFacts = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundFacts'), FailureHandling.CONTINUE_ON_FAILURE)			
			PerformanceAnnual = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/PerformanceAnnual'), FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.check(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/cumulativeButton'),
				FailureHandling.CONTINUE_ON_FAILURE)
			PerformanceCumulative = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/PerformanceCumulative'), FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/annualPerfTab'), FailureHandling.CONTINUE_ON_FAILURE)
			AnnualPerf = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/AnnualPerf'), FailureHandling.CONTINUE_ON_FAILURE)
			PerfSnapshot = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/PerfSnapshot'), FailureHandling.CONTINUE_ON_FAILURE)
			
			CustomKeywords.'excelKeywords.WriteExcel.demoKey'(url,  'Server response is ' + statusCode, 'Passed', 'DCIO Open end funds', rowIndex, FundNameSite, FundName, ManagerRows, FundDescription, Objective, StrategyAndProcess, FundFacts, 'N/A', PerformanceAnnual, PerformanceCumulative, AnnualPerf, PerfSnapshot)
			PrevUrl = url
			} else {
            KeywordUtil.markFailed('Server response is ' + statusCode)
			CustomKeywords.'excelKeywords.WriteExcel.demoKey'(url,  'Server response is ' + statusCode, 'Failed', 'DCIO Open end funds', rowIndex, 'page down', 'page down', 'page down', 'page down', 'page down', 'page down', 'page down', 'page down', 'page down', 'page down', 'page down', 'page down')
			WebUI.navigateToUrl(PrevUrl)
			}
			rowIndex = rowIndex + 1
        }
		} else {
			String url = WebUI.getUrl(FailureHandling.CONTINUE_ON_FAILURE)
			KeywordUtil.markFailed('Share Class dropdown missing')
			CustomKeywords.'excelKeywords.WriteExcel.demoKey'(url,  'Share class dropdown missing', 'Failed', 'DCIO Open end funds', rowIndex, 'page down', 'page down', 'page down', 'page down', 'page down', 'page down', 'page down', 'page down')
			WebUI.navigateToUrl(PrevUrl)
			rowIndex = rowIndex + 1
		}
    }
}
catch (Exception ex) {
    KeywordUtil.markFailed(ex.getMessage())
}