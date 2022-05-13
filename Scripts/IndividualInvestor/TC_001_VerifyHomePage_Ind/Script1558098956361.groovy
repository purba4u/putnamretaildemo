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

try{
CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'(GlobalVariable.ENV + GlobalVariable.IndividualPath)

CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Login')

CustomKeywords.'customKeywords.PortalKeywords.verifyPopupFormsLaunching'('Investor Log in', true)

CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Funds')

int dataRows = findTestData('MutualFunds').getRowNumbers()

List<String> fundNames = new ArrayList<String>()

for (int index = 1; index <= dataRows; index++) {
    fundNames.add(findTestData('MutualFunds').getValue('FundNames', index))
}

CustomKeywords.'customKeywords.PortalKeywords.verifyAllMutualFundNames'(fundNames)

//CustomKeywords.'customKeywords.PortalKeywords.verifyFooter'()
//Footer is covered under TC_028

//WebUI.closeBrowser()

}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}