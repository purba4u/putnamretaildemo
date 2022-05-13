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

WebUI.comment('Test Case starts')
try{

CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'(GlobalVariable.ENV + GlobalVariable.IndividualMutualFundPricing)

not_run: CustomKeywords.'customKeywords.PortalKeywords.verifyBreadCrumbPage'(findTestObject(null), '')

CustomKeywords.'customKeywords.PortalKeywords.verifyPageTitle'('Mutual Fund Pricing and Performance - Putnam Investments')

CustomKeywords.'customKeywords.PortalUIKeywords.verifyForBrokenLinks'(findTestObject('IndividualInvestor/PageSpecificObjects/PricingAndPerformance/tblPricingFundTable'), 
    'href', '')

CustomKeywords.'customKeywords.PortalKeywords.verifyElementInContainer'(findTestObject('IndividualInvestor/PageSpecificObjects/PricingAndPerformance/tblPricingFundTable'), 
    'AMT-Free Muncipal Fund')

WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/PricingAndPerformance/elFirstFundLink'))

CustomKeywords.'customKeywords.PortalUIKeywords.waitForDocLoad'()

CustomKeywords.'customKeywords.PortalKeywords.verifyPage'('AMT-Free Municipal Fund', 'Putnam AMT-Free Municipal Fund (PPNAX) - Putnam Investments')
}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}

WebUI.comment('Test Case Ended')

WebUI.comment('verify layout')

