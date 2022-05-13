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
    WebUI.navigateToUrl((GlobalVariable.ENV + GlobalVariable.IndividualPath) + '529/portfolios/722-putnam-529-growth/A')
    WebUI.waitForPageLoad(20)
	String PrevUrl = WebUI.getUrl()
    'Capture the Fund selection dropdown contents'
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
			if ((PrevUrl != url)&&(index != 1)) {
				RequestObject ro = new RequestObject()
				ro.setRestRequestMethod('GET')
				ro.setRestUrl(url)
				ResponseObject resp = WS.sendRequest(ro)
				String statusCode = resp.getStatusCode()
				if (statusCode.equals('200')) {
					KeywordUtil.markPassed((url + '  Server response is ') + statusCode)
					CustomKeywords.'excelKeywords.WriteExcel.demoKey'(url,  'Server response is ' + statusCode, 'Passed', 'Ind 529 funds', rowIndex, 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A')
					PrevUrl = url
				} else {
					KeywordUtil.markFailed('Server response is ' + statusCode)
					CustomKeywords.'excelKeywords.WriteExcel.demoKey'(url,  'Server response is ' + statusCode, 'Failed', 'Ind 529 funds', rowIndex, 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A')
					WebUI.navigateToUrl(PrevUrl)
				}
				rowIndex = rowIndex + 1
			} else {
				KeywordUtil.markFailed('Same url as Previous URL, not able to select next fund')
				CustomKeywords.'excelKeywords.WriteExcel.demoKey'(url,  'Same url as Previous URL, not able to select next fund', 'Failed', 'Ind 529 funds', rowIndex, 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A')
			}
        }
    }
}
catch (Exception ex) {
    KeywordUtil.markFailed(ex.getMessage())
}