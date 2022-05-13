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
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import customKeywords.UIKeywords as uik
import customKeywords.PortalTestingKeywords as ptk

try {
	//WebUI.openBrowser("https://www.putnam.com/individual/")
	ptk.loginToPortal(GlobalVariable.individualHomePage, "individual",
			GlobalVariable.indUserName, GlobalVariable.IndPassword)
	uik.navigateToPath("username-dropdown","id")
	uik.navigateToPath("Update Profile")
	String str = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/Frames/docTitle')).toString().toLowerCase()
	if(str.equals("update profile")){
		KeywordUtil.markPassed("Update profile page loaded with text: "+ str)	
	}else{
		KeywordUtil.markFailed("Update profile page not loaded")
	}
	
	uik.navigateToPath("username-dropdown","id")
	uik.navigateToPath("Logout>Login&_h")
}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}