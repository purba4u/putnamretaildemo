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

try {
	'Launching DCIO PV page'
    CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'(GlobalVariable.ENV + GlobalVariable.DCIOPath + "/tools/planvisualizer/")

	'Perform email login'
    uik.getCurrentSessionDriver().findElement(By.xpath('//input[@id=\'primaryEmailInput\']')).sendKeys(GlobalVariable.InstUserID)
    uik.getCurrentSessionDriver().findElement(By.xpath('//button[@id=\'submit-email-form\']')).click()
    sleep(5000)
    WebUI.waitForPageLoad(20)

    'PV Login'
	WebUI.click(findTestObject('DCIO/PVLoginRegistrationButton')) 
	//WebUI.sendKeys((findTestObject('DCIO/PVLoginEmail')), (GlobalVariable.InstUserID)) 
	WebUI.sendKeys((findTestObject('DCIO/PVLoginPassword')), (GlobalVariable.InstPassword))
	WebUI.click(findTestObject('DCIO/PVLoginFormButton'))
	sleep(5000)
	WebUI.waitForPageLoad(20)
	
	'Confirm if the PV login is successful, by checking PV tool Container'
	TestObject PVToolFrame = findTestObject('DCIO/PVToolFrame')

	if (WebUI.waitForElementPresent(PVToolFrame, 10)) {
		KeywordUtil.markPassed('PV Login is Successful')
	} else {
		String Error= WebUI.getText(findTestObject('DCIO/PVLoginError'))
		KeywordUtil.markFailed('PV Login is failed. Error:'+Error)
	}
	
}
catch (Exception ex) {
    KeywordUtil.markFailed(ex.getMessage())
} 

