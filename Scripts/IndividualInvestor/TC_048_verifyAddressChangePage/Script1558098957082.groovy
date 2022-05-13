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
import customKeywords.PortalTestingKeywords as ptk
import customKeywords.UIKeywords as uik
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By
import com.kms.katalon.core.testobject.ConditionType

try {
    ptk.loginToPortal(GlobalVariable.individualHomePage, 'individual', GlobalVariable.indUserName, GlobalVariable.IndPassword)

    uik.navigateToPath('Confirmations and statements')
	WebUI.waitForPageLoad(5)
    uik.navigateToPath('Address change', 'iframe_//iframe')

	ptk.waitForJsfPageLoad(findTestObject('IndividualInvestor/PageSpecificObjects/Frames/dstDocTitle'), 'visible', 60)
    String actTitle = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/Frames/dstDocTitle')).toLowerCase()

    if (actTitle.contains('address change')) {
        KeywordUtil.markPassed('Address change page loaded successfully')
    } else {
        KeywordUtil.markFailed(('Title: ' + actTitle) + 'is displayed.')
    }
	
	

	WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/myAccs/chkBoxAddressChange'))
	
	WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/myAccs/btnChangeAddress'))
	
	to = new TestObject('to');
	to.addProperty("xpath", ConditionType.EQUALS, '//iframe/parent::td/iframe', true)
	WebUI.switchToFrame(to, 10, FailureHandling.STOP_ON_FAILURE)
	
	ptk.waitForJsfPageLoad(findTestObject('IndividualInvestor/PageSpecificObjects/Frames/dstDocTitle'), null, 'present', 60)
	
	if(WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/Frames/dstDocTitle')).toLowerCase().contains('address change request')){
		KeywordUtil.markPassed("Navigated to address change requeset page successfully")
	}else KeywordUtil.markFailed("Navigation to address change requeset page failed")
	
}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}