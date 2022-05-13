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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
try {
    //WebUI.openBrowser('https://www.putnam.com/individual/')

    ptk.loginToPortal(GlobalVariable.individualHomePage, 'individual', GlobalVariable.indUserName, GlobalVariable.IndPassword)

    uik.navigateToPath('Confirmations and statements')
	
	//Switch to iframe
//	CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Confirmations and statements','iframe_//iframe')
	WebDriver driver  = uik.getCurrentSessionDriver()
	driver.switchTo().frame(driver.findElement(By.xpath("//iframe")))
	CustomKeywords.'customKeywords.PortalKeywords.waitForJsfPageLoad'(findTestObject('IndividualInvestor/PageSpecificObjects/Frames/dstDocTitle'), 
        '', 'visible', 0)

    uik.verifyForBrokenLinks(findTestObject('IndividualInvestor/PageSpecificObjects/Frames/accScoreContainer'),"href")
	
	WebUI.switchToDefaultContent();
}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}

