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
import customKeywords.PortalTestingKeywords as ptk
import customKeywords.UIKeywords as uik
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By
import com.kms.katalon.core.testobject.ConditionType
try {

	String yearForFilter = "2017"
		ptk.loginToPortal(GlobalVariable.individualHomePage, 'individual', GlobalVariable.indUserName, GlobalVariable.IndPassword)
	
		uik.navigateToPath('Confirmations and statements')
	
		//WebDriver driver  = uik.getCurrentSessionDriver()

		uik.navigateToPath('Account history (transcripts)', "iframe_//iframe")
		CustomKeywords.'customKeywords.PortalKeywords.waitForJsfPageLoad'(findTestObject('IndividualInvestor/PageSpecificObjects/Frames/dstDocTitle'),
			'', 'visible', 30)
		String actTitle = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/Frames/dstDocTitle')).toLowerCase()
	
		if (actTitle.contains('account history')) {
			KeywordUtil.markPassed('Account history (transcripts) page loaded successfully')
		} else {
			KeywordUtil.markFailed(('Title: ' + actTitle) + 'is displayed.')
		}
		
		uik.navigateToPath('Statements')
		actTitle = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/Frames/dstDocTitle')).toLowerCase()
		
		if (actTitle.contains('confirmations and statements')) {
			KeywordUtil.markPassed('Confirmations and statements page loaded successfully')
		} else {
			KeywordUtil.markFailed(('Title: ' + actTitle) + 'is displayed.')
		}

		
		uik.navigateToPath('Account history (transcripts)')

		WebUI.selectOptionByValue(findTestObject('IndividualInvestor/PageSpecificObjects/Retirement_IndInvestor/AccountHistoryFilterSelect'), yearForFilter, false)
		WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/Retirement_IndInvestor/AccountHistoryFilterBtn'))
		
		String documentYearTdXpath="//td[text()='Document Year']//parent::*//parent::*//tr[*]//td[2]"
		List<String> documentYearList = CustomKeywords.'customKeywords.PortalKeywords.getAllTexts'(documentYearTdXpath)
		
		System.out.println(documentYearList)
		
		for (value in documentYearList) {
			if(!(value.contains('Document Year') || value.contains(yearForFilter))){
				KeywordUtil.markFailed("Filter is not working properly--Expected value in list--"+yearForFilter+"--Actual value in list--"+value)
				break
			}
		}
		
		WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/Retirement_IndInvestor/AccountHistoryFirstRowCheckBox'))
		
		WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/Retirement_IndInvestor/AccountHistoryViewDownloadBtn'))
}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}