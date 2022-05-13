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
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject

try {
    WebUI.navigateToUrl(GlobalVariable.ENV + '404', 
        FailureHandling.STOP_ON_FAILURE)
    WebUI.waitForPageLoad(20)

    String url = WebUI.getUrl()

    RequestObject ro = new RequestObject()

    ro.setRestRequestMethod('GET')

    ro.setRestUrl(url)

    ResponseObject resp = WS.sendRequest(ro)

    String statusCode = resp.getStatusCode()

    if (statusCode.equals('200')) {
        KeywordUtil.markPassed((url + '  Server response is ') + statusCode)
    } else {
        KeywordUtil.markFailed('Server response is ' + statusCode)
    }
}
catch (Exception ex) {
    KeywordUtil.markFailed(ex.getMessage())
} 

