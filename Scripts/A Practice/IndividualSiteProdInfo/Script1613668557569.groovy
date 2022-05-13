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
    'Launching Individual website'
    CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'(GlobalVariable.ENV + GlobalVariable.IndividualPath)

    'Navigate to Fund screener page'
    CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Products')

    WebUI.waitForPageLoad(20)

    'Click the first fund on Fund screener page'
    WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/FundScreener/FundScreenerTable_FirstFund'))

    WebUI.waitForPageLoad(20)

    'On the first fund prod info page, capture the Fund selection dropdown contents'
    NoOfFunds = WebUI.getNumberOfTotalOption(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundDropDown'))

    String AllFundName = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundDropDown'))

    List<String> AllFundList = AllFundName.split('\\r?\\n' //Remove CRLF from each dropdown entry
        )

    'Select fund from dropdown 1 by 1 and verify the Fund data'
    for (int index = 1; index <= (NoOfFunds - 1); index++) {
        'Select fund from dropdown'
        WebUI.selectOptionByIndex(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundDropDown'), index)

        WebUI.waitForPageLoad(20)

        'Capture selected fund name from dropdown and save it as variable for verification'
        String FundName = AllFundList[index].toString()
        WebUI.comment(FundName)

        'Verify the breadcrumb on the Fund page'
        WebUI.verifyElementPresent(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/Breadcrumb'), 20)

        'Verify the Share class dropdown display'
        WebUI.verifyElementPresent(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/ShareClass'), 20)
		String AllShareClass = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/ShareClass'))
  
        'Verify the Fund Description display'
        WebUI.verifyElementPresent(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundDescription'), 20)
		String FundDescription = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundDescription'))
		WebUI.comment(FundDescription)

        'Verify the Highlights tab is present'
        WebUI.verifyElementPresent(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/HighlightsTab'), 20)

        'Verify the Literature tab is present'
        WebUI.verifyElementPresent(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/LiteratureTab'), 20)

        'Verify the Performance tab is present'
        WebUI.verifyElementPresent(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/PerformanceTab'), 20)

        'Verify the Holdings tab is present'
        WebUI.verifyElementPresent(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/HoldingsTab'), 20)

        'Verify the Expenses tab is present'
        WebUI.verifyElementPresent(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/ExpenseTab'), 20)

        'Verify the Objective section is present'
        WebUI.verifyElementPresent(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/Objective'), 20)
		String FundObjective = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/Objective'))
		WebUI.comment(FundObjective)

        'Verify the Strategy and Process columns are present'
        WebUI.verifyElementPresent(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/StrategyAndProcess'), 
            20)

        'Verify the Fund price section is present'
        WebUI.verifyElementPresent(findTestObject('IndividualInvestor/PageSpecificObjects/ProdInfo/FundPrice'), 20)
    }
}
catch (Exception ex) {
    KeywordUtil.markFailed(ex.getMessage())
} 

WebUI.comment('Test Case Ended')

