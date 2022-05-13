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
import customKeywords.PortalTestingKeywords as ptk
import customKeywords.UIKeywords as uik
import customKeywords.PortalTestingKeywords as psk
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

try {
    CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'(GlobalVariable.ENV + GlobalVariable.MutualFundsPath)

    WebUI.waitForPageLoad(5)

    //CustomKeywords.'customKeywords.PortalKeywords.verifyMutualFundsFilter'(['Absolute Return','Blend'], 0, 0, '', 0, false, '')
//	CustomKeywords.'customKeywords.PortalKeywords.verifyMutualFundsFilter'(['Absolute Return','Blend'], 0, 0, '', 0, false, '')
//    CustomKeywords.'customKeywords.PortalKeywords.verifyMutualFundsFilter'([], 0, 10, '', 0, false, '')
//	CustomKeywords.'customKeywords.PortalKeywords.verifyMutualFundsFilter'([], 0, 10, '5 years', 0, false, '')
	CustomKeywords.'customKeywords.PortalKeywords.verifyMutualFundsFilter'([], 0, 0, '', 4, false, '')
	WebUI.back()
	CustomKeywords.'customKeywords.PortalKeywords.verifyMutualFundsFilter'([], 0, 0, '', 0, false, 'Albert Chan, CFA')


}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}

