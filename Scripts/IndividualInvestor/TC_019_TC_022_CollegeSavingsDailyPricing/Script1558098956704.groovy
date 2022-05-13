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
try{
CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'(GlobalVariable.ENV + GlobalVariable.CollegeSavingsDailyPricing)

WebUI.verifyTextPresent('PLAN FOR THEIR POTENTIAL', false)

WebUI.verifyTextPresent('Important information:', false)

CustomKeywords.'customKeywords.PortalKeywords.verifyPageTitle'('529 Plan - Putnam Investments')

//CustomKeywords.'customKeywords.PortalKeywords.verifyLinkExistOnPage'('The education savings challenge')

CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('The education savings challenge&_H', null)

//CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Frequently asked questions&_H',null)

//CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Tax benefits&_H',null)

//CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('The right approach&_H',null)

//CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Investment options&_H',null)

//CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Resources&_H',null)

int dataRows = findTestData('PricingFunds').getRowNumbers()

List<String> fundNames = new ArrayList<String>()

for (int index = 1; index <= dataRows; index++) {
    fundNames.add(findTestData('PricingFunds').getValue('FundNames', index))
}

CustomKeywords.'customKeywords.PortalKeywords.verifyAllPricingFundNames'(fundNames)

CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Putnam 529  Age-Based 1998')

CustomKeywords.'customKeywords.PortalKeywords.verifyStickyNavigation'('Putnam 529 Age-Based 1998')

}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}
