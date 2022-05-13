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
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

WebUI.comment('Test Case Starts')

try {
    WebUI.acceptAlert()

    ptk.loginToPortal(GlobalVariable.individualHomePage, 'individual', GlobalVariable.indUserName, GlobalVariable.IndPassword)

    //CustomKeywords.'customKeywords.PortalKeywords.navigateToLink'('all accounts')
    WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/Retirement_IndInvestor/AllAccountsLink'))

    WebUI.delay(2)

    List<String> accountsAtHomePage = CustomKeywords.'customKeywords.PortalKeywords.getAllTexts'('//p[@class=\'left account-type\']//span[@class=\'account-title\']')

    CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('My accounts&_H>PUTNAM ACCOUNTS')

    WebUI.delay(2)

    CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Daily statements', 'iframe_//iframe')

    WebUI.delay(2)

    List<String> accountsAtDailyStatementPage = CustomKeywords.'customKeywords.PortalKeywords.getAllTexts'('//table[@class=\'bgtable\']//td//a')

    List<String> accountsAtDailyStatementPageUpperCase = new ArrayList<String>()

    for (def ele : accountsAtDailyStatementPage) {
        accountsAtDailyStatementPageUpperCase.add(ele.toUpperCase())
    }
    
    boolean flag = false

    for (def ele : accountsAtHomePage) {
        if (!(accountsAtDailyStatementPageUpperCase.contains(ele))) {
            flag = false

            System.out.println('############################' + ele)

            break
        } else {
            flag = true
        }
    }
    
    if (flag) {
        KeywordUtil.markPassed('Account names are matching both at Home page and Daily statement page')
    } else {
        KeywordUtil.markFailed('Account names are NOT matching both at Home page and Daily statement page')
    }
    
    WebUI.switchToDefaultContent()

    CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('My accounts&_H')

    WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/Retirement_IndInvestor/AccountDropDown'))

    WebUI.delay(2)

    List<String> accountsAtDropDown = CustomKeywords.'customKeywords.PortalKeywords.getAllTexts'('//a[text()=\'PUTNAM ACCOUNTS\']//following-sibling::ul//li')

    List<String> accountsAtDropDownUpperCase = new ArrayList<String>()

    for (def ele : accountsAtDropDown) {
        accountsAtDropDownUpperCase.add(ele.toUpperCase())
    }
    
    flag = false

    for (def ele : accountsAtHomePage) {
        if (!(accountsAtDropDownUpperCase.contains(ele))) {
            flag = false

            break
        } else {
            flag = true
        }
    }
    
    if (flag) {
        KeywordUtil.markPassed('Account names are matching both at Home page and Accounts drop down')
    } else {
        KeywordUtil.markFailed('Account names are NOT matching both at Home page and  Accounts drop down')
    }
}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}
WebUI.comment('Test Case Ends')

