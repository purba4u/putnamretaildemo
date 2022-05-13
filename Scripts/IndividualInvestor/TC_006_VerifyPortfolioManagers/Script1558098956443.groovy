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
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

WebUI.comment('Navigate to Portfolio Manager test case started')

try {
    'Launching the Protfolio managwer website'
    CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'(GlobalVariable.ENV + GlobalVariable.IndividualPortfolioMangers)

    CustomKeywords.'customKeywords.PortalKeywords.verifyPageTitle'('Portfolio Managers - Putnam Investments')

    //CustomKeywords.'customKeywords.PortalKeywords.verifyBreadCrumbPage'(findTestObject('IndividualInvestor/PageSpecificObjects/PortFolioManager/breadcrumb'),'Home>Mutual funds>Portfolio Managers')

    'this is failing bcoz of Forbidden response, need to click or see another apporaoch\r\n'
    CustomKeywords.'customKeywords.PortalUIKeywords.verifyForBrokenLinks'(findTestObject('IndividualInvestor/PageSpecificObjects/PortFolioManager/elImagesPortfolioManagers'), 
        'data-href', 'https://www-uat.putnam.com')

    CustomKeywords.'customKeywords.PortalKeywords.verifyElementInContainer'(findTestObject('IndividualInvestor/PageSpecificObjects/PortFolioManager/elPortfoliomangersName'), 
        'Shep Perkins CFA')
}
catch (Exception ex) {
    KeywordUtil.markFailed(ex.getMessage())
} 

WebUI.comment('Test Case Ended')

