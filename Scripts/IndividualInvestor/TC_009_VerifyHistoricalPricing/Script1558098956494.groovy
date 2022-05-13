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

WebUI.comment('Test case started')

try {
    CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'(GlobalVariable.ENV + GlobalVariable.IndividualMutualFundsHistoricalPricing)

    CustomKeywords.'customKeywords.PortalKeywords.verifyPage'('Historical pricing', 'Historical Mutual Fund Pricing - Putnam Investments')

    WebUI.selectOptionByLabel(findTestObject('IndividualInvestor/PageSpecificObjects/HistoricalPage/elFundName'), 'AMT-Free Municipal Fund', 
        false)

    WebUI.verifyOptionSelectedByLabel(findTestObject('IndividualInvestor/PageSpecificObjects/HistoricalPage/elFundName'), 'AMT-Free Municipal Fund', 
        false, 0)

    WebUI.selectOptionByLabel(findTestObject('IndividualInvestor/PageSpecificObjects/HistoricalPage/elShares'), 'A shares', false)

    WebUI.verifyOptionSelectedByLabel(findTestObject('IndividualInvestor/PageSpecificObjects/HistoricalPage/elShares'), 'A shares', false, 0)

    WebUI.check(findTestObject('IndividualInvestor/PageSpecificObjects/HistoricalPage/elShow2'))

    WebUI.verifyElementChecked(findTestObject('IndividualInvestor/PageSpecificObjects/HistoricalPage/elShow2'), 0)

    CustomKeywords.'customKeywords.PortalKeywords.Datepicker'(findTestObject('IndividualInvestor/PageSpecificObjects/HistoricalPage/elstartDate'), 
        '01-01-2019')

    CustomKeywords.'customKeywords.PortalUIKeywords.waitForDocLoad'()

    CustomKeywords.'customKeywords.PortalKeywords.Datepicker'(findTestObject('IndividualInvestor/PageSpecificObjects/HistoricalPage/elEndDate'), 
        '01-05-2019')

    CustomKeywords.'customKeywords.PortalUIKeywords.waitForDocLoad'()

    WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/HistoricalPage/elGetPrice'))

    CustomKeywords.'customKeywords.PortalUIKeywords.waitForDocLoad'()

    not_run: CustomKeywords.'customKeywords.PortalUIKeywords.verifyComponentElements'([])
}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}

WebUI.comment('Test Case Ended')

