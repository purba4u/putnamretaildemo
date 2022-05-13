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
import com.kms.katalon.core.testobject.ConditionType as ConditionType

try {
    //	WebUI.openBrowser("https://www.putnam.com/individual/")
    ptk.loginToPortal(GlobalVariable.individualHomePage, 'individual', GlobalVariable.indUserName, GlobalVariable.IndPassword)

    uik.navigateToPath('Confirmations and statements')

    //    uik.navigateToPath('Exchanges', 'iframe_//iframe')
    WebDriver driver = uik.getCurrentSessionDriver()

    //driver.switchTo().frame(driver.findElement(By.xpath("//iframe")))
    uik.navigateToPath('Exchanges', 'iframe_//iframe')

    CustomKeywords.'customKeywords.PortalKeywords.waitForJsfPageLoad'(findTestObject('IndividualInvestor/PageSpecificObjects/Frames/dstDocTitle'), 
        '', 'visible', 30)

    //	sleep(20000)
    String actTitle = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/Frames/dstDocTitle')).toLowerCase()

    if (actTitle.contains('exchanges')) {
        KeywordUtil.markPassed('Exchanges page loaded successfully')
    } else {
        KeywordUtil.markFailed(('Title: ' + actTitle) + 'is displayed.')
    }
    
    WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/myAccs/lnkFund_ExchangesPage'))

	driver.switchTo().frame(driver.findElement(By.xpath('//iframe/parent::td/iframe')))
	
	CustomKeywords.'customKeywords.PortalKeywords.waitForJsfPageLoad'(findTestObject('IndividualInvestor/PageSpecificObjects/Frames/dstDocTitle'), 
        '', 'visible', 30)


    actTitle = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/Frames/dstDocTitle')).toLowerCase()

    if (actTitle.contains('exchanges from selection')) {
        KeywordUtil.markPassed('Respective Exchanges link page loaded successfully')
    } else {
        KeywordUtil.markFailed(('Exchanges link:' + actTitle) + 'is displayed.')
    }
}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}

