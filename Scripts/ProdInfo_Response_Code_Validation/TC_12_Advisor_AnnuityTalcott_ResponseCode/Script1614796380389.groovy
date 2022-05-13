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
    //CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'((GlobalVariable.ENV + GlobalVariable.AdvisorPath) + 'annuities/portfolios/115-Fixed-Accumulation-Feature')
    WebUI.navigateToUrl((GlobalVariable.ENV + GlobalVariable.AdvisorPath) + 'annuities/portfolios/129-George-Putnam-Balanced-Fund-Subaccount')
	WebUI.waitForPageLoad(20)
	TestObject LoginFrom = findTestObject('DCIO/LoginForm')
	if (WebUI.waitForElementPresent(LoginFrom, 10)) {
		uik.getCurrentSessionDriver().findElement(By.xpath('//input[@id=\'primaryEmailInput\']')).sendKeys(GlobalVariable.AdvisorEmail)
		uik.getCurrentSessionDriver().findElement(By.xpath('//button[@id=\'submit-email-form\']')).click()
		sleep(5000)
		WebUI.waitForPageLoad(20)
	}
	String PrevUrl = WebUI.getUrl()
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

        String url = WebUI.getUrl()

        RequestObject ro = new RequestObject()

        ro.setRestRequestMethod('GET')

        ro.setRestUrl(url)

        ResponseObject resp = WS.sendRequest(ro)

        String statusCode = resp.getStatusCode()

        if (statusCode.equals('200')) {
            KeywordUtil.markPassed((url + '  Server response is ') + statusCode)
			CustomKeywords.'excelKeywords.WriteExcel.demoKey'(url,  'Server response is ' + statusCode, 'Passed', 'Adv Annuity Talcott funds', index, 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A')
			PrevUrl = url
        } else {
            KeywordUtil.markFailed('Server response is ' + statusCode)
			CustomKeywords.'excelKeywords.WriteExcel.demoKey'(url,  'Server response is ' + statusCode, 'Failed', 'Adv Annuity Talcott funds', index, 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A')
			WebUI.navigateToUrl(PrevUrl)
        }
    }
}
catch (Exception ex) {
    KeywordUtil.markFailed(ex.getMessage())
}