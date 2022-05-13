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
	WebUI.navigateToUrl((GlobalVariable.ENV + GlobalVariable.IndividualPath) + 'annuities/portfolios/344-fixed-account-paa-c')
    //CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'((GlobalVariable.ENV + GlobalVariable.IndividualPath) + 'annuities/portfolios/115-Fixed-Accumulation-Feature')
    WebUI.waitForPageLoad(20)
	String PrevUrl = WebUI.getUrl()
	String ManagerInfo
	String FundNameDropdown
	
	
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
        String url = WebUI.getUrl()
        RequestObject ro = new RequestObject()
        ro.setRestRequestMethod('GET')
        ro.setRestUrl(url)
        ResponseObject resp = WS.sendRequest(ro)
        String statusCode = resp.getStatusCode()

        if (statusCode.equals('200')) {
			String FundName1 = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundName1'), FailureHandling.CONTINUE_ON_FAILURE)
			String FundName2 = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundName3'), FailureHandling.CONTINUE_ON_FAILURE)
			String FullFundNameSite = FundName1 + '   ' + FundName2
            ManagerInfo = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/ManagerName'), FailureHandling.CONTINUE_ON_FAILURE)
			String FundDescription = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/AnnuityFundDescription'), FailureHandling.CONTINUE_ON_FAILURE)
			String Objective = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/StrategyAndProcess'), FailureHandling.CONTINUE_ON_FAILURE)
			String AnnutySalesStory = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/AnnutySalesStory'), FailureHandling.CONTINUE_ON_FAILURE)
			String FundFacts = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundFacts529'), FailureHandling.CONTINUE_ON_FAILURE)
			String PerformanceAnnual = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/AnnuityAnnualPerf'), FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.check(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/cumulativeButton'),
				FailureHandling.CONTINUE_ON_FAILURE)
			String AnnuityCumPerf = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/AnnuityCumPerf'), FailureHandling.CONTINUE_ON_FAILURE)
			String AnnuityAnnPerf = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/AnnuityAnnPerf'), FailureHandling.CONTINUE_ON_FAILURE)
			String AnnuityRestPerf = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/AnnuityRestPerf'), FailureHandling.CONTINUE_ON_FAILURE)
			String AnnuitySalesCharge = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/AnnuitySalesCharge'), FailureHandling.CONTINUE_ON_FAILURE)
			String AnnuityCDSC = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/AnnuityCDSC'), FailureHandling.CONTINUE_ON_FAILURE)
			String Expense = 'Annuity Sales Charge:         ' + AnnuitySalesCharge + '           Annuity CDSC:            ' + AnnuityCDSC
			
			CustomKeywords.'excelKeywords.WriteExcel.demoKey'(url,  'Server response is ' + statusCode, 'Passed', 'Ind Annuity All Adv pref funds', index, FundNameDropdown, FullFundNameSite, ManagerInfo, FundDescription, Objective, AnnutySalesStory, FundFacts, Expense, PerformanceAnnual, AnnuityCumPerf, AnnuityAnnPerf, AnnuityRestPerf)
			PrevUrl = url
        } else {
            KeywordUtil.markFailed('Server response is ' + statusCode)
			CustomKeywords.'excelKeywords.WriteExcel.demoKey'(url,  'Server response is ' + statusCode, 'Failed', 'Ind Annuity All Adv pref funds', index, 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A')
			WebUI.navigateToUrl(PrevUrl)
        }
    }
}
catch (Exception ex) {
    KeywordUtil.markFailed(ex.getMessage())
}