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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

WebUI.comment('Test Case Started')
try{

//CustomKeywords.'customKeywords.PortalKeywords.launchPutnamSite'('https://www.putnam.com/individual/mutual-funds/fund-documents/')
CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'(GlobalVariable.ENV + GlobalVariable.IndividualMutualFundsFundsDocument)

not_run: CustomKeywords.'customKeywords.PortalKeywords.verifyBreadCrumbPage'(findTestObject(null), '')

CustomKeywords.'customKeywords.PortalKeywords.verifyPageTitle'('Prospectuses and fund documents - Putnam Investments')

CustomKeywords.'customKeywords.PortalUIKeywords.verifyForBrokenLinks'(findTestObject('IndividualInvestor/PageSpecificObjects/ProspectsAndFundDocuments/tblProspectFundTable'), 
    'href', '')

WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/ProspectsAndFundDocuments/elFirstFundLink'))

CustomKeywords.'customKeywords.PortalUIKeywords.waitForDocLoad'()

CustomKeywords.'customKeywords.PortalKeywords.verifyPage'('AMT-Free Municipal Fund', 'Putnam AMT-Free Municipal Fund (PPNAX) - Putnam Investments')

CustomKeywords.'customKeywords.PortalUIKeywords.browserBack'()

CustomKeywords.'customKeywords.PortalKeywords.verifyDocJsflink'(findTestObject('IndividualInvestor/PageSpecificObjects/ProspectsAndFundDocuments/elFirstDocLink'), 
    '.pdf')

CustomKeywords.'customKeywords.PortalUIKeywords.browserBack'()

CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Distribution rates&_c', '')

CustomKeywords.'customKeywords.PortalUIKeywords.waitForDocLoad'()

CustomKeywords.'customKeywords.PortalKeywords.verifyPage'('Fund distribution rates', 'Fund distribution rates - Putnam Investments')

CustomKeywords.'customKeywords.PortalUIKeywords.browserBack'()

CustomKeywords.'customKeywords.PortalKeywords.verifyPageTitle'('Prospectuses and fund documents - Putnam Investments')
}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}
WebUI.comment('Test Case Ended')
