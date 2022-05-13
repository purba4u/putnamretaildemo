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
import customKeywords.PortalTestingKeywords as ptk
import customKeywords.UIKeywords as uik
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.By

try {
//	WebUI.openBrowser("https://putnam.com/individual/")
	ptk.loginToPortal(GlobalVariable.individualHomePage, "individual", 
			GlobalVariable.indUserName, GlobalVariable.IndPassword)
	uik.navigateToPath("Confirmations and statements")
	uik.navigateToPath("View/Edit options","iframe_//iframe")
	
	//uik.navigateToPath("Mailing options and alerts","iframe_//iframe")
	WebDriver driver  = uik.getCurrentSessionDriver()
	driver.switchTo().frame(driver.findElement(By.xpath("//iframe/parent::td/iframe")))
	CustomKeywords.'customKeywords.PortalKeywords.waitForJsfPageLoad'(findTestObject('IndividualInvestor/PageSpecificObjects/Frames/spMailingOptionsHeader'),
		'', 'visible', 60)
	if(WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/Frames/spMailingOptionsHeader')).toLowerCase().contains("mailing options")){
		KeywordUtil.markPassed("Navigated to Mailing options page")
	}else{
		KeywordUtil.markFailed("Navigation to Mailing options: Failed")
	}
}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}