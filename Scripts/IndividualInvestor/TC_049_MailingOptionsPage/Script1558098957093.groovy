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

    uik.navigateToPath('Mailing options and alerts', 'iframe_//iframe')
	
	TestObject to = new TestObject('to');
	to.addProperty("xpath", ConditionType.EQUALS, '//iframe/parent::td/iframe', true)
	WebUI.switchToFrame(to, 0, FailureHandling.STOP_ON_FAILURE)
	
	ptk.waitForJsfPageLoad(findTestObject('IndividualInvestor/PageSpecificObjects/myAccs/lblSectionHead'), 'present', 60)
    String actTitle = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/myAccs/lblSectionHead')).toLowerCase()

    if (actTitle.contains('mailing options')) {
        KeywordUtil.markPassed('Selection of Mailing options page loaded successfully')
    } else {
        KeywordUtil.markFailed(('Title: ' + actTitle) + 'is displayed.')
    }
}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}