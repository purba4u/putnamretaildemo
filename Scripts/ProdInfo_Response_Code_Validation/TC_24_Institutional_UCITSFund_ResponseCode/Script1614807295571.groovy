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
    //CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'((GlobalVariable.ENV + GlobalVariable.InstitutionalPath) + 'ucits/funds/799-putnam-european-high-yield-fund/E')
    WebUI.navigateToUrl((GlobalVariable.ENV + GlobalVariable.InstitutionalPath) + 'ucits/funds/799-putnam-european-high-yield-fund/E')
	WebUI.waitForPageLoad(20)
	TestObject AgreementForm = findTestObject('Institutional/IntAgreementFormButton')
	if (WebUI.waitForElementPresent(AgreementForm, 10)) {
		WebUI.click(AgreementForm)
		sleep(5000)
		WebUI.waitForPageLoad(20)
	}
	TestObject UcitsAgreementForm = findTestObject('Institutional/UCITSAgreementButton')
	if (WebUI.waitForElementPresent(UcitsAgreementForm, 10)) {
		WebUI.click(UcitsAgreementForm)
		sleep(5000)
		WebUI.waitForPageLoad(20)
	}
	TestObject LoginFrom = findTestObject('Institutional/EmailForm')
	if (WebUI.waitForElementPresent(LoginFrom, 10)) {
		uik.getCurrentSessionDriver().findElement(By.xpath('//input[@id=\'email_address\']')).sendKeys(GlobalVariable.AdvisorEmail)
		uik.getCurrentSessionDriver().findElement(By.xpath('//*[@id=\'institutional-relaxed-access-modal\']//button[@type=\'submit\']')).click()
		sleep(5000)
		WebUI.waitForPageLoad(20)
	}
	String PrevUrl = WebUI.getUrl()
    'On the ucits fund prod info page, capture the Fund selection dropdown contents'
    NoOfFunds = WebUI.getNumberOfTotalOption(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundDropDown'))

    String AllFundName = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundDropDown'))

    List<String> AllFundList = AllFundName.split('\\r?\\n' //Remove CRLF from each dropdown entry
        )
		
	int rowIndex = 1	
    'Select fund from dropdown'
    for (int index = 1; index <= (NoOfFunds - 1); index++) {
        WebUI.selectOptionByIndex(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundDropDown'), index, 
            FailureHandling.CONTINUE_ON_FAILURE)

        WebUI.waitForPageLoad(20)

        'On the prod info page, capture the Share class selection dropdown contents'
        NoOfShareClass = WebUI.getNumberOfTotalOption(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/ShareClass'), 
            FailureHandling.CONTINUE_ON_FAILURE)

        String AllShareClass = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/ShareClass'), 
            FailureHandling.CONTINUE_ON_FAILURE)

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
			CustomKeywords.'excelKeywords.WriteExcel.demoKey'(url,  'Server response is ' + statusCode, 'Passed', 'Inst UCITS Funds', rowIndex, 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A')
			PrevUrl = url
			} else {
            KeywordUtil.markFailed('Server response is ' + statusCode)
			CustomKeywords.'excelKeywords.WriteExcel.demoKey'(url,  'Server response is ' + statusCode, 'Failed', 'Inst UCITS Funds', rowIndex, 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A')
			WebUI.navigateToUrl(PrevUrl)
			}
			rowIndex = rowIndex + 1
        }
    }
}
catch (Exception ex) {
    KeywordUtil.markFailed(ex.getMessage())
}