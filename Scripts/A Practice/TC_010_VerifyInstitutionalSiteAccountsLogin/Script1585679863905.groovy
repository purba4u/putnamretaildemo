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
	'Navigate to Institutional Site Investment capabilities page to launch the Login form'
    CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'(GlobalVariable.ENV + GlobalVariable.InstitutionalPath)
	
	CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Login')
	
	'Enter Login details'
    uik.getCurrentSessionDriver().findElement(By.xpath('//*[@class=\'modal-content\']//input[@name=\'userID\']')).sendKeys(GlobalVariable.InstUserID)
	uik.getCurrentSessionDriver().findElement(By.xpath('//*[@class=\'modal-content\']//input[@name=\'userPass\']')).sendKeys(GlobalVariable.InstPassword)
	uik.getCurrentSessionDriver().findElement(By.xpath('//*[@class=\'modal-content\']//button[text()=\'Login\']')).click()

    sleep(5000)

    WebUI.waitForPageLoad(20)

    'Confirm if the login is successful, by checking the User name dropdown in the screen'
    TestObject User_Name = findTestObject('Institutional/UserName')

    if (WebUI.waitForElementPresent(User_Name, 10)) {
        KeywordUtil.markPassed('Institutional Login is Successful')
    } else {
        String Error= WebUI.getText(findTestObject('Institutional/LoginError'))
		KeywordUtil.markFailed('Institutional Login is failed. Error:'+Error)
    }
}
catch (Exception ex) {
    KeywordUtil.markFailed(ex.getMessage())
} 

