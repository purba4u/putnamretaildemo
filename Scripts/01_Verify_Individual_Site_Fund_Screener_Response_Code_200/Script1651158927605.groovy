import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject

try {
	WebUI.openBrowser((GlobalVariable.ENV + 'individual/mutual-funds/'), FailureHandling.STOP_ON_FAILURE)
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
	WebUI.closeBrowser()
}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}