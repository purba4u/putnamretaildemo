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

WebUI.comment('DCIO Site Navigation test case started')

try {
    'Launching DCIO website'
    CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'(GlobalVariable.ENV + GlobalVariable.DCIOPath)

    'Verifying menus and submenus links and capturing the response code'
    CustomKeywords.'customKeywords.PortalUIKeywords.verifyForBrokenLinks'(findTestObject('DCIO/NavigationContainer'), 'href', 
        '')
	
	'Verifying hidden links under Fat nav and capturing the response code'
	CustomKeywords.'customKeywords.PortalUIKeywords.verifyForBrokenLinks'(findTestObject('DCIO/HiddenNavContainer'),
		'href', '')
}
catch (Exception ex) {
    KeywordUtil.markFailed(ex.getMessage())
} 

WebUI.comment('Test Case Ended')

