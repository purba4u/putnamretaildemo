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
    CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'((GlobalVariable.ENV + GlobalVariable.IndividualPath) + '529/portfolios/688/A')
    WebUI.waitForPageLoad(20)
    
	
	String PrevUrl
	String FundNameSite
	String FundNameComp
	String ManagerRows
	String FundDescription
	String Objective
	String StrategyAndProcess
	String FundFacts
	String ExpenseRatio
    String FundName = "Putnam 529 Age-Based 2001"       
    String url = WebUI.getUrl(FailureHandling.CONTINUE_ON_FAILURE)
            RequestObject ro = new RequestObject()
            ro.setRestRequestMethod('GET')
            ro.setRestUrl(url)
            ResponseObject resp = WS.sendRequest(ro)
            String statusCode = resp.getStatusCode()

            if (statusCode.equals('200')) {
            KeywordUtil.markPassed((url + '  Server response is ') + statusCode)
			FundNameSite = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundName1'), FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.comment(FundNameSite)
			if (!FundNameSite.equals(null)) {
				FundNameSite = FundNameSite.replaceAll('[ ]{2,}\\(.{5}\\)', '')
				if (FundNameSite == FundName) {
					FundNameComp = 'fund name matched'
				} else {
					FundNameComp = 'fund name not matched'
				}
			}	
			ManagerCount = uik.getCurrentSessionDriver().findElements(By.xpath('//*[@id=\'management-team\']//div[@class=\'manager d-flex\']')).size()
			if ((ManagerCount > 0)|(!ManagerCount.equals(null))) {
				ManagerRows = ManagerCount + ' managers assigned to this fund.'
			} else {
				ManagerRows = 'No manager assigned to this fund.'
			}
			WebUI.comment(ManagerRows)
			FundDescription = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundDescription'), FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.comment(FundDescription)
			Objective = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/Objective'), FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.comment(Objective)
			StrategyAndProcess = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/StrategyAndProcess'), FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.comment(StrategyAndProcess)
			FundFacts = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundFacts'), FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.comment(FundFacts)
			ExpenseRatio = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/ExpenseRatio'), FailureHandling.CONTINUE_ON_FAILURE)
			} else {
            KeywordUtil.markFailed('Server response is ' + statusCode)
			}
        }
catch (Exception ex) {
    KeywordUtil.markFailed(ex.getMessage())
}