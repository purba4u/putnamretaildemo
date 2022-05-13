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

WebUI.comment('Broken link verification test case started')

try {
    'Launching the Weblink'
	
	CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'(GlobalVariable.ENV + GlobalVariable.Individual404)
	
	
    //String str= CustomKeywords.'customKeywords.PortalUIKeywords.isLinkBroken'(new URL(GlobalVariable.ENV + GlobalVariable.Individual404))
	//WebUI.comment("value of '${str}'")

	//if(str.equalsIgnoreCase("ok")){
	//	KeywordUtil.markPassed("Server response is "+str)		
	//} else {
	//	KeywordUtil.markFailed("Server response is "+str)
	//}
	
  }
catch (Exception ex) {
    KeywordUtil.markFailed(ex.getMessage())
} 

WebUI.comment('Test Case Ended')

