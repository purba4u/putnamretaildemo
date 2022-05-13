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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

try{

'Navigate to the Perspectives page'	
CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'(GlobalVariable.ENV + GlobalVariable.PerspectivePath)

'Verify breadcrumb element present on the Perspectives page'
WebUI.verifyElementPresent(findTestObject('IndividualInvestor/PageSpecificObjects/Retirement_IndInvestor/PerspectiveBlog_Breadcrumb'), 10)

'Verify the browser tab name for the Perspective page'
CustomKeywords.'customKeywords.PortalKeywords.verifyPageTitle'('Putnam Perspectives - Putnam Investments')

'Capture the name of the highlights blog post'
String headding = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/Retirement_IndInvestor/PerspectiveBlog_FirstBlogHeadding'))

'Verify the response code of the highlights blog post link'
CustomKeywords.'customKeywords.PortalUIKeywords.verifyForBrokenLinks'(findTestObject('IndividualInvestor/PageSpecificObjects/Retirement_IndInvestor/PerspectiveBlog_FirstBlogLink'),
	'href', '')

'Click the highlights blog link'
WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/Retirement_IndInvestor/PerspectiveBlog_FirstBlogLink'))

'Verify the blog name captured in above step on the highlights blog page'
WebUI.verifyTextPresent(headding, false)
}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}