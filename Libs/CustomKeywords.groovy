
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import java.lang.String

import java.util.List

import com.kms.katalon.core.testobject.TestObject

import java.util.Map

import java.net.URL

import org.openqa.selenium.WebElement

import com.applitools.eyes.selenium.Eyes

import com.applitools.eyes.RectangleSize



def static "excelKeywords.WriteExcel.demoKey"(
    	String url	
     , 	String serverResponse	
     , 	String status	
     , 	String sheetName	
     , 	int rowIndex	
     , 	String fundName	
     , 	String fundNameComp	
     , 	String managerRows	
     , 	String fundDescription	
     , 	String objective	
     , 	String strategyAndProcess	
     , 	String fundFacts	
     , 	String expenseRatio	
     , 	String performanceAnnual	
     , 	String performanceCumulative	
     , 	String annualPerf	
     , 	String perfSnapshot	) {
    (new excelKeywords.WriteExcel()).demoKey(
        	url
         , 	serverResponse
         , 	status
         , 	sheetName
         , 	rowIndex
         , 	fundName
         , 	fundNameComp
         , 	managerRows
         , 	fundDescription
         , 	objective
         , 	strategyAndProcess
         , 	fundFacts
         , 	expenseRatio
         , 	performanceAnnual
         , 	performanceCumulative
         , 	annualPerf
         , 	perfSnapshot)
}


def static "customKeywords.PortalTestingKeywords.verifyPrimaryNavigation"() {
    (new customKeywords.PortalTestingKeywords()).verifyPrimaryNavigation()
}


def static "customKeywords.PortalTestingKeywords.loginToPortal"(
    	String strUrl	
     , 	String loginType	
     , 	String userName	
     , 	String passwd	) {
    (new customKeywords.PortalTestingKeywords()).loginToPortal(
        	strUrl
         , 	loginType
         , 	userName
         , 	passwd)
}


def static "customKeywords.PortalTestingKeywords.updateProfile"(
    	String updateType	
     , 	String updatePwd	
     , 	String updateSecurity	) {
    (new customKeywords.PortalTestingKeywords()).updateProfile(
        	updateType
         , 	updatePwd
         , 	updateSecurity)
}

 /**
	 * This will verify forms popup launching properly ex. Login Form, Password Reset Form
	 */ 
def static "customKeywords.PortalTestingKeywords.verifyPopupFormsLaunching"(
    	String formHeaderText	
     , 	boolean closePopupWindow	) {
    (new customKeywords.PortalTestingKeywords()).verifyPopupFormsLaunching(
        	formHeaderText
         , 	closePopupWindow)
}

 /**
	 * Click link and verify new tab opens and close new window
	 */ 
def static "customKeywords.PortalTestingKeywords.verifyNewTabLaunches"(
    	String linkText	
     , 	String newTabTitle	) {
    (new customKeywords.PortalTestingKeywords()).verifyNewTabLaunches(
        	linkText
         , 	newTabTitle)
}

 /**
	 * this method will take funds names as input and compare with values displayed on the page
	 * @param expectedFundNames
	 * @return
	 */ 
def static "customKeywords.PortalTestingKeywords.verifyAllMutualFundNames"(
    	java.util.List<String> expectedFundNames	) {
    (new customKeywords.PortalTestingKeywords()).verifyAllMutualFundNames(
        	expectedFundNames)
}

 /**
	 * Get list of mutual funds displayed
	 * @return
	 */ 
def static "customKeywords.PortalTestingKeywords.getAllMutualFundDetails"() {
    (new customKeywords.PortalTestingKeywords()).getAllMutualFundDetails()
}

 /**
	 * this method will check different sections of the mutual fund details on the page
	 * @return
	 * TODO:Click Links and check if the page is scrolled to respective part of the page.
	 * Rename to "VerifyStickyNavigation"
	 */ 
def static "customKeywords.PortalTestingKeywords.verifyStickyNavigation"(
    	String fundName	) {
    (new customKeywords.PortalTestingKeywords()).verifyStickyNavigation(
        	fundName)
}

 /**
	 * this method will check for different tabs on a page
	 * @Usage use this method if the tabs xapth has li tags which has attribute data-tabref
	 * @param tabNames
	 * @return
	 */ 
def static "customKeywords.PortalTestingKeywords.verifyTabs"(
    	java.util.List<String> tabNames	) {
    (new customKeywords.PortalTestingKeywords()).verifyTabs(
        	tabNames)
}

 /**
	 * this method will take pricing funds names as input and compare with values displayed on the page
	 * @param expectedFundNames
	 * @return
	 */ 
def static "customKeywords.PortalTestingKeywords.verifyAllPricingFundNames"(
    	java.util.List<String> expectedFundNames	) {
    (new customKeywords.PortalTestingKeywords()).verifyAllPricingFundNames(
        	expectedFundNames)
}

 /**
	 * this method will navigate different tabs on a page
	 * @Usage use this method if the tabs xapth has li tags which has attribute data-tabref
	 * <li class="" data-tabref="tab1">::before Variable Trust ::after</li>
	 * @param tabNames
	 * @return
	 */ 
def static "customKeywords.PortalTestingKeywords.navigateToTab"(
    	String tabName	) {
    (new customKeywords.PortalTestingKeywords()).navigateToTab(
        	tabName)
}

 /**
	 * Get all details from table
	 * @usage use this method if html has <table> tag
	 * @return
	 */ 
def static "customKeywords.PortalTestingKeywords.getAllDetailsFromTable"(
    	String uniqueHeaderName	) {
    (new customKeywords.PortalTestingKeywords()).getAllDetailsFromTable(
        	uniqueHeaderName)
}

 /**
	 * This method will check breadcrumb
	 * @param breadCrumbPath specify the path of the breadcrumb
	 * @usage Home>Retirement>Annuities
	 * @return
	 */ 
def static "customKeywords.PortalTestingKeywords.verifyBreadCrumb"(
    	String breadCrumbPath	) {
    (new customKeywords.PortalTestingKeywords()).verifyBreadCrumb(
        	breadCrumbPath)
}

 /**
	 * this method will take funds names as input and compare with values displayed on Annuities page for Variable Trust tab
	 * @param expectedFundNames
	 * @return
	 */ 
def static "customKeywords.PortalTestingKeywords.verifyAnnuitiesFundNames"(
    	java.util.List<String> expectedFundNames	) {
    (new customKeywords.PortalTestingKeywords()).verifyAnnuitiesFundNames(
        	expectedFundNames)
}

 /**
	 * this method will check for search functionality
	 * @param searchText pass the search text
	 * @return
	 *
	 * TODO: Change the name to globalsearch
	 */ 
def static "customKeywords.PortalTestingKeywords.verifyGlobalSearchFunction"(
    	String searchText	) {
    (new customKeywords.PortalTestingKeywords()).verifyGlobalSearchFunction(
        	searchText)
}

 /**
	 * This method will return all the texts from xpath
	 * @usage <li>text1</li><li>text2</li>
	 * @param xpathToList
	 * @return
	 */ 
def static "customKeywords.PortalTestingKeywords.getAllTexts"(
    	String xpathToGetList	) {
    (new customKeywords.PortalTestingKeywords()).getAllTexts(
        	xpathToGetList)
}

 /**
	 * This method will filter the mutual funds based on user input
	 * */ 
def static "customKeywords.PortalTestingKeywords.verifyMutualFundsFilter"(
    	java.util.List<String> assetClasses	
     , 	int annualizedMinPer	
     , 	int annualizedMaxPer	
     , 	String timePeriod	
     , 	int morningStartRating	
     , 	boolean lipperRanking	
     , 	String manager	) {
    (new customKeywords.PortalTestingKeywords()).verifyMutualFundsFilter(
        	assetClasses
         , 	annualizedMinPer
         , 	annualizedMaxPer
         , 	timePeriod
         , 	morningStartRating
         , 	lipperRanking
         , 	manager)
}

 /**
	 * Verify Page Title
	 * Parameter: String
	 * return Type : Null
	 * * Verify the Page Title
	 */ 
def static "customKeywords.PortalTestingKeywords.verifyPageTitle"(
    	String str	) {
    (new customKeywords.PortalTestingKeywords()).verifyPageTitle(
        	str)
}


def static "customKeywords.PortalTestingKeywords.verifyPageName"(
    	String PageName	) {
    (new customKeywords.PortalTestingKeywords()).verifyPageName(
        	PageName)
}

 /**
	 * Verify Page
	 * Parameters : String PageName, String PageTitle
	 *  Return Type : Null
	 *  It will verify the Page Name and Title with given Input
	 */ 
def static "customKeywords.PortalTestingKeywords.verifyPage"(
    	String PageName	
     , 	String PageTitle	) {
    (new customKeywords.PortalTestingKeywords()).verifyPage(
        	PageName
         , 	PageTitle)
}

 /**
	 * Verify Element in container
	 * Parameters: TestObject to(of the container), String Element Name
	 * Return Type : Null
	 * It will verify the element is present in container
	 */ 
def static "customKeywords.PortalTestingKeywords.verifyElementInContainer"(
    	TestObject to	
     , 	String elementName	) {
    (new customKeywords.PortalTestingKeywords()).verifyElementInContainer(
        	to
         , 	elementName)
}

 /**
	 * Verify link opening pdf or jsf page :verifyDocJsflink
	 * Parameters: TestObject to(of the single link), String newPageFormat =Either ".pdf" or ".jsf"
	 * Return Type : Null
	 * It will click on the link and verify new page is ".pdf" or ".jsf"
	 */ 
def static "customKeywords.PortalTestingKeywords.verifyDocJsflink"(
    	TestObject to	
     , 	String newPageFormat	) {
    (new customKeywords.PortalTestingKeywords()).verifyDocJsflink(
        	to
         , 	newPageFormat)
}

 /**
	 * Select given date from Calender : datePicker
	 * Parameters: TestObject calender, String Date
	 * Return Type : Null
	 * It will select the date from calender object
	 * date example : 02-01-1998 dd-yy-yyyy
	 */ 
def static "customKeywords.PortalTestingKeywords.Datepicker"(
    	TestObject calender	
     , 	String Date	) {
    (new customKeywords.PortalTestingKeywords()).Datepicker(
        	calender
         , 	Date)
}

 /**
	 * Close form window : closeFormWindow
	 * Parameters: Null
	 * Return Type : Null
	 * It will close the Form window
	 */ 
def static "customKeywords.PortalTestingKeywords.closeFormWindow"() {
    (new customKeywords.PortalTestingKeywords()).closeFormWindow()
}

 /**
	 * Wait Foe Element : waitForJsfPageLoad
	 * Parameters: TestObject(Optional),Xpath(Optionsl), String waitType, int MaxTimeout
	 * Return Type : Null
	 * It will wait for element to load in given max time out, If It does not find the element in given timeout it will throw wxception
	 */ 
def static "customKeywords.PortalTestingKeywords.waitForJsfPageLoad"(
    	TestObject to	
     , 	String xpath	
     , 	String type	
     , 	int maxTimeout	) {
    (new customKeywords.PortalTestingKeywords()).waitForJsfPageLoad(
        	to
         , 	xpath
         , 	type
         , 	maxTimeout)
}

 /**
	 * Switch to Window : switchToWindow
	 * Parameters: TestObjectto, int MaxTimeout
	 * Return Type : Null
	 * It will click on test object and witch to window
	 */ 
def static "customKeywords.PortalTestingKeywords.switchToWindow"(
    	TestObject to	
     , 	int maxTimeout	) {
    (new customKeywords.PortalTestingKeywords()).switchToWindow(
        	to
         , 	maxTimeout)
}

 /**
	 * Switch to validateDailyPricing :validateDailyPricing(TestObject to)
	 * Parameters: TestObject to
	 * Return Type : Null
	 * It will validate the daily price data present on home pgae with client information page and daily statement page
	 */ 
def static "customKeywords.PortalTestingKeywords.validateDailyPricing"(
    	TestObject to	) {
    (new customKeywords.PortalTestingKeywords()).validateDailyPricing(
        	to)
}


def static "customKeywords.PortalTestingKeywords.verifyDailyPricePageTable"(
    	TestObject to	
     , 	String PageName1	
     , 	String PageName2	) {
    (new customKeywords.PortalTestingKeywords()).verifyDailyPricePageTable(
        	to
         , 	PageName1
         , 	PageName2)
}


def static "customKeywords.PortalTestingKeywords.compareMaps"(
    	java.util.Map<String, String> map1	
     , 	java.util.Map<String, String> map2	) {
    (new customKeywords.PortalTestingKeywords()).compareMaps(
        	map1
         , 	map2)
}


def static "customKeywords.PortalTestingKeywords.clickDailyPriceTab"(
    	TestObject to	
     , 	String tabName	) {
    (new customKeywords.PortalTestingKeywords()).clickDailyPriceTab(
        	to
         , 	tabName)
}


def static "customKeywords.PortalTestingKeywords.searchFunds"(
    	TestObject to	
     , 	TestObject shareClass	
     , 	TestObject tbl	
     , 	String PageName	
     , 	String classValue	
     , 	String FundName	) {
    (new customKeywords.PortalTestingKeywords()).searchFunds(
        	to
         , 	shareClass
         , 	tbl
         , 	PageName
         , 	classValue
         , 	FundName)
}


def static "customKeywords.PortalTestingKeywords.loginToPortal"(
    	String strUrl	
     , 	String loginType	
     , 	String userName	) {
    (new customKeywords.PortalTestingKeywords()).loginToPortal(
        	strUrl
         , 	loginType
         , 	userName)
}


def static "customKeywords.PortalTestingKeywords.updateProfile"(
    	String updateType	
     , 	String updatePwd	) {
    (new customKeywords.PortalTestingKeywords()).updateProfile(
        	updateType
         , 	updatePwd)
}


def static "customKeywords.PortalTestingKeywords.updateProfile"(
    	String updateType	) {
    (new customKeywords.PortalTestingKeywords()).updateProfile(
        	updateType)
}


def static "customKeywords.PortalTestingKeywords.verifyPage"(
    	String PageName	) {
    (new customKeywords.PortalTestingKeywords()).verifyPage(
        	PageName)
}


def static "customKeywords.PortalTestingKeywords.verifyPage"() {
    (new customKeywords.PortalTestingKeywords()).verifyPage()
}


def static "customKeywords.PortalTestingKeywords.waitForJsfPageLoad"(
    	TestObject to	
     , 	String type	
     , 	int maxTimeout	) {
    (new customKeywords.PortalTestingKeywords()).waitForJsfPageLoad(
        	to
         , 	type
         , 	maxTimeout)
}


def static "customKeywords.PortalTestingKeywords.waitForJsfPageLoad"(
    	String type	
     , 	int maxTimeout	) {
    (new customKeywords.PortalTestingKeywords()).waitForJsfPageLoad(
        	type
         , 	maxTimeout)
}


def static "customKeywords.PortalTestingKeywords.clickDailyPriceTab"(
    	String tabName	) {
    (new customKeywords.PortalTestingKeywords()).clickDailyPriceTab(
        	tabName)
}


def static "customKeywords.PortalTestingKeywords.searchFunds"(
    	TestObject to	
     , 	TestObject shareClass	
     , 	TestObject tbl	
     , 	String PageName	
     , 	String FundName	) {
    (new customKeywords.PortalTestingKeywords()).searchFunds(
        	to
         , 	shareClass
         , 	tbl
         , 	PageName
         , 	FundName)
}


def static "customKeywords.PortalUIKeywords.getCurrentSessionDriver"() {
    (new customKeywords.PortalUIKeywords()).getCurrentSessionDriver()
}


def static "customKeywords.PortalUIKeywords.convertXpathtoTestObject"(
    	String locator	
     , 	String ObjectName	) {
    (new customKeywords.PortalUIKeywords()).convertXpathtoTestObject(
        	locator
         , 	ObjectName)
}


def static "customKeywords.PortalUIKeywords.verifyComponentElements"(
    	TestObject[] tom	) {
    (new customKeywords.PortalUIKeywords()).verifyComponentElements(
        	tom)
}


def static "customKeywords.PortalUIKeywords.isLinkBroken"(
    	URL url	) {
    (new customKeywords.PortalUIKeywords()).isLinkBroken(
        	url)
}


def static "customKeywords.PortalUIKeywords.waitForDocLoad"() {
    (new customKeywords.PortalUIKeywords()).waitForDocLoad()
}


def static "customKeywords.PortalUIKeywords.jsClicker"(
    	TestObject to	) {
    (new customKeywords.PortalUIKeywords()).jsClicker(
        	to)
}


def static "customKeywords.PortalUIKeywords.isObjectEnabled"(
    	TestObject toObj	) {
    (new customKeywords.PortalUIKeywords()).isObjectEnabled(
        	toObj)
}


def static "customKeywords.PortalUIKeywords.verifyIfLinkBroken"(
    	TestObject to	) {
    (new customKeywords.PortalUIKeywords()).verifyIfLinkBroken(
        	to)
}


def static "customKeywords.PortalUIKeywords.verifyForBrokenLinks"(
    	TestObject to	
     , 	String propName	
     , 	String domain	) {
    (new customKeywords.PortalUIKeywords()).verifyForBrokenLinks(
        	to
         , 	propName
         , 	domain)
}


def static "customKeywords.PortalUIKeywords.refreshBrowser"() {
    (new customKeywords.PortalUIKeywords()).refreshBrowser()
}


def static "customKeywords.PortalUIKeywords.browserBack"() {
    (new customKeywords.PortalUIKeywords()).browserBack()
}


def static "customKeywords.PortalUIKeywords.navigateToPath"(
    	String str	
     , 	String locator	) {
    (new customKeywords.PortalUIKeywords()).navigateToPath(
        	str
         , 	locator)
}


def static "customKeywords.PortalUIKeywords.loadUrl"(
    	String Url	) {
    (new customKeywords.PortalUIKeywords()).loadUrl(
        	Url)
}


def static "customKeywords.PortalUIKeywords.navigateToPages"(
    	String[] elements	) {
    (new customKeywords.PortalUIKeywords()).navigateToPages(
        	elements)
}


def static "customKeywords.PortalUIKeywords.verifyForBrokenLinks"(
    	TestObject to	
     , 	String propName	) {
    (new customKeywords.PortalUIKeywords()).verifyForBrokenLinks(
        	to
         , 	propName)
}


def static "customKeywords.PortalUIKeywords.navigateToPath"(
    	String str	) {
    (new customKeywords.PortalUIKeywords()).navigateToPath(
        	str)
}


def static "customKeywords.PortalKeywords.verifyPrimaryNavigation"() {
    (new customKeywords.PortalKeywords()).verifyPrimaryNavigation()
}


def static "customKeywords.PortalKeywords.loginToPortal"(
    	String strUrl	
     , 	String loginType	
     , 	String userName	
     , 	String passwd	) {
    (new customKeywords.PortalKeywords()).loginToPortal(
        	strUrl
         , 	loginType
         , 	userName
         , 	passwd)
}


def static "customKeywords.PortalKeywords.updateProfile"(
    	String updateType	
     , 	String updatePwd	
     , 	String updateSecurity	) {
    (new customKeywords.PortalKeywords()).updateProfile(
        	updateType
         , 	updatePwd
         , 	updateSecurity)
}


def static "customKeywords.PortalKeywords.verifyPopupFormsLaunching"(
    	String formHeaderText	
     , 	boolean closePopupWindow	) {
    (new customKeywords.PortalKeywords()).verifyPopupFormsLaunching(
        	formHeaderText
         , 	closePopupWindow)
}


def static "customKeywords.PortalKeywords.verifyNewTabLaunches"(
    	String linkText	
     , 	String newTabTitle	) {
    (new customKeywords.PortalKeywords()).verifyNewTabLaunches(
        	linkText
         , 	newTabTitle)
}


def static "customKeywords.PortalKeywords.verifyAllMutualFundNames"(
    	java.util.List<String> expectedFundNames	) {
    (new customKeywords.PortalKeywords()).verifyAllMutualFundNames(
        	expectedFundNames)
}


def static "customKeywords.PortalKeywords.getAllMutualFundDetails"() {
    (new customKeywords.PortalKeywords()).getAllMutualFundDetails()
}


def static "customKeywords.PortalKeywords.verifyStickyNavigation"(
    	String fundName	) {
    (new customKeywords.PortalKeywords()).verifyStickyNavigation(
        	fundName)
}


def static "customKeywords.PortalKeywords.verifyTabs"(
    	java.util.List<String> tabNames	) {
    (new customKeywords.PortalKeywords()).verifyTabs(
        	tabNames)
}


def static "customKeywords.PortalKeywords.verifyAllPricingFundNames"(
    	java.util.List<String> expectedFundNames	) {
    (new customKeywords.PortalKeywords()).verifyAllPricingFundNames(
        	expectedFundNames)
}


def static "customKeywords.PortalKeywords.navigateToTab"(
    	String tabName	) {
    (new customKeywords.PortalKeywords()).navigateToTab(
        	tabName)
}


def static "customKeywords.PortalKeywords.getAllDetailsFromTable"(
    	String uniqueHeaderName	) {
    (new customKeywords.PortalKeywords()).getAllDetailsFromTable(
        	uniqueHeaderName)
}


def static "customKeywords.PortalKeywords.verifyBreadCrumb"(
    	String breadCrumbPath	) {
    (new customKeywords.PortalKeywords()).verifyBreadCrumb(
        	breadCrumbPath)
}


def static "customKeywords.PortalKeywords.verifyAnnuitiesFundNames"(
    	java.util.List<String> expectedFundNames	) {
    (new customKeywords.PortalKeywords()).verifyAnnuitiesFundNames(
        	expectedFundNames)
}


def static "customKeywords.PortalKeywords.verifyGlobalSearchFunction"(
    	String searchText	) {
    (new customKeywords.PortalKeywords()).verifyGlobalSearchFunction(
        	searchText)
}


def static "customKeywords.PortalKeywords.getAllTexts"(
    	String xpathToGetList	) {
    (new customKeywords.PortalKeywords()).getAllTexts(
        	xpathToGetList)
}


def static "customKeywords.PortalKeywords.verifyMutualFundsFilter"(
    	java.util.List<String> assetClasses	
     , 	int annualizedMinPer	
     , 	int annualizedMaxPer	
     , 	String timePeriod	
     , 	int morningStartRating	
     , 	boolean lipperRanking	
     , 	String manager	) {
    (new customKeywords.PortalKeywords()).verifyMutualFundsFilter(
        	assetClasses
         , 	annualizedMinPer
         , 	annualizedMaxPer
         , 	timePeriod
         , 	morningStartRating
         , 	lipperRanking
         , 	manager)
}


def static "customKeywords.PortalKeywords.verifyPageTitle"(
    	String str	) {
    (new customKeywords.PortalKeywords()).verifyPageTitle(
        	str)
}


def static "customKeywords.PortalKeywords.verifyPageName"(
    	String PageName	) {
    (new customKeywords.PortalKeywords()).verifyPageName(
        	PageName)
}


def static "customKeywords.PortalKeywords.verifyPage"(
    	String PageName	
     , 	String PageTitle	) {
    (new customKeywords.PortalKeywords()).verifyPage(
        	PageName
         , 	PageTitle)
}


def static "customKeywords.PortalKeywords.verifyElementInContainer"(
    	TestObject to	
     , 	String elementName	) {
    (new customKeywords.PortalKeywords()).verifyElementInContainer(
        	to
         , 	elementName)
}


def static "customKeywords.PortalKeywords.verifyDocJsflink"(
    	TestObject to	
     , 	String newPageFormat	) {
    (new customKeywords.PortalKeywords()).verifyDocJsflink(
        	to
         , 	newPageFormat)
}


def static "customKeywords.PortalKeywords.Datepicker"(
    	TestObject calender	
     , 	String Date	) {
    (new customKeywords.PortalKeywords()).Datepicker(
        	calender
         , 	Date)
}


def static "customKeywords.PortalKeywords.closeFormWindow"() {
    (new customKeywords.PortalKeywords()).closeFormWindow()
}


def static "customKeywords.PortalKeywords.waitForJsfPageLoad"(
    	TestObject to	
     , 	String xpath	
     , 	String type	
     , 	int maxTimeout	) {
    (new customKeywords.PortalKeywords()).waitForJsfPageLoad(
        	to
         , 	xpath
         , 	type
         , 	maxTimeout)
}


def static "customKeywords.PortalKeywords.switchToWindow"(
    	TestObject to	
     , 	int maxTimeout	) {
    (new customKeywords.PortalKeywords()).switchToWindow(
        	to
         , 	maxTimeout)
}


def static "customKeywords.PortalKeywords.validateDailyPricing"(
    	TestObject to	) {
    (new customKeywords.PortalKeywords()).validateDailyPricing(
        	to)
}


def static "customKeywords.PortalKeywords.verifyDailyPricePageTable"(
    	TestObject to	
     , 	String PageName1	
     , 	String PageName2	) {
    (new customKeywords.PortalKeywords()).verifyDailyPricePageTable(
        	to
         , 	PageName1
         , 	PageName2)
}


def static "customKeywords.PortalKeywords.compareMaps"(
    	java.util.Map<String, String> map1	
     , 	java.util.Map<String, String> map2	) {
    (new customKeywords.PortalKeywords()).compareMaps(
        	map1
         , 	map2)
}


def static "customKeywords.PortalKeywords.searchFunds"(
    	TestObject to	
     , 	TestObject shareClass	
     , 	TestObject tbl	
     , 	String PageName	
     , 	String classValue	
     , 	String FundName	) {
    (new customKeywords.PortalKeywords()).searchFunds(
        	to
         , 	shareClass
         , 	tbl
         , 	PageName
         , 	classValue
         , 	FundName)
}


def static "customKeywords.PortalKeywords.loginToPortal"(
    	String strUrl	
     , 	String loginType	
     , 	String userName	) {
    (new customKeywords.PortalKeywords()).loginToPortal(
        	strUrl
         , 	loginType
         , 	userName)
}


def static "customKeywords.PortalKeywords.updateProfile"(
    	String updateType	
     , 	String updatePwd	) {
    (new customKeywords.PortalKeywords()).updateProfile(
        	updateType
         , 	updatePwd)
}


def static "customKeywords.PortalKeywords.updateProfile"(
    	String updateType	) {
    (new customKeywords.PortalKeywords()).updateProfile(
        	updateType)
}


def static "customKeywords.PortalKeywords.verifyPage"(
    	String PageName	) {
    (new customKeywords.PortalKeywords()).verifyPage(
        	PageName)
}


def static "customKeywords.PortalKeywords.verifyPage"() {
    (new customKeywords.PortalKeywords()).verifyPage()
}


def static "customKeywords.PortalKeywords.waitForJsfPageLoad"(
    	TestObject to	
     , 	String type	
     , 	int maxTimeout	) {
    (new customKeywords.PortalKeywords()).waitForJsfPageLoad(
        	to
         , 	type
         , 	maxTimeout)
}


def static "customKeywords.PortalKeywords.waitForJsfPageLoad"(
    	String type	
     , 	int maxTimeout	) {
    (new customKeywords.PortalKeywords()).waitForJsfPageLoad(
        	type
         , 	maxTimeout)
}


def static "customKeywords.PortalKeywords.searchFunds"(
    	TestObject to	
     , 	TestObject shareClass	
     , 	TestObject tbl	
     , 	String PageName	
     , 	String FundName	) {
    (new customKeywords.PortalKeywords()).searchFunds(
        	to
         , 	shareClass
         , 	tbl
         , 	PageName
         , 	FundName)
}


def static "customKeywords.UIKeywords.getCurrentSessionDriver"() {
    (new customKeywords.UIKeywords()).getCurrentSessionDriver()
}


def static "customKeywords.UIKeywords.convertXpathtoTestObject"(
    	String locator	
     , 	String ObjectName	) {
    (new customKeywords.UIKeywords()).convertXpathtoTestObject(
        	locator
         , 	ObjectName)
}

 /**
	 * verify link presence on the page
	 * Owner: Arun
	 */ 
def static "customKeywords.UIKeywords.verifyLinkExistOnPage"(
    	String linkText	) {
    (new customKeywords.UIKeywords()).verifyLinkExistOnPage(
        	linkText)
}

 /**
	 *
	 * @param too
	 * @param tom
	 * @return
	 *
	 * @Usage for sending two params:
	 * CustomKeywords.'customKeywords.UIKeywords.verifyComponentElements'((([findTestObject('Component/C03 Footer Text/txtPreFooter')
	 , findTestObject('Component/C03 Footer Text/txtFooterHeadLine'), findTestObject('Component/C03 Footer Text/lnkCopyRight')]) as TestObject[]),
	 (([findTestObject('Component/C03 Footer Text/txtBottomText'), findTestObject('Component/C03 Footer Text/lnkContactUsFooter')]) as TestObject[]))
	 *
	 * @Usage for sending one param:
	 * CustomKeywords.'customKeywords.UIKeywords.verifyComponentElements'((([findTestObject('Component/C03 Footer Text/txtPreFooter')
	 , findTestObject('Component/C03 Footer Text/txtFooterHeadLine'), findTestObject('Component/C03 Footer Text/lnkCopyRight')]) as TestObject[]))
	 */ 
def static "customKeywords.UIKeywords.verifyComponentElements"(
    	TestObject[] tom	) {
    (new customKeywords.UIKeywords()).verifyComponentElements(
        	tom)
}


def static "customKeywords.UIKeywords.isLinkBroken"(
    	URL url	) {
    (new customKeywords.UIKeywords()).isLinkBroken(
        	url)
}


def static "customKeywords.UIKeywords.waitForDocLoad"() {
    (new customKeywords.UIKeywords()).waitForDocLoad()
}


def static "customKeywords.UIKeywords.jsClicker"(
    	TestObject to	) {
    (new customKeywords.UIKeywords()).jsClicker(
        	to)
}


def static "customKeywords.UIKeywords.isObjectEnabled"(
    	TestObject toObj	) {
    (new customKeywords.UIKeywords()).isObjectEnabled(
        	toObj)
}


def static "customKeywords.UIKeywords.verifyIfLinkBroken"(
    	TestObject to	) {
    (new customKeywords.UIKeywords()).verifyIfLinkBroken(
        	to)
}


def static "customKeywords.UIKeywords.verifyIfLinkisBroken"(
    	WebElement element	) {
    (new customKeywords.UIKeywords()).verifyIfLinkisBroken(
        	element)
}


def static "customKeywords.UIKeywords.verifyIfImgRendered"(
    	TestObject to	) {
    (new customKeywords.UIKeywords()).verifyIfImgRendered(
        	to)
}


def static "customKeywords.UIKeywords.verifyIfImgRender"(
    	WebElement element	) {
    (new customKeywords.UIKeywords()).verifyIfImgRender(
        	element)
}


def static "customKeywords.UIKeywords.verifyForBrokenLinks"(
    	TestObject to	
     , 	String propName	
     , 	String domain	) {
    (new customKeywords.UIKeywords()).verifyForBrokenLinks(
        	to
         , 	propName
         , 	domain)
}


def static "customKeywords.UIKeywords.verifyImagesRendering"(
    	TestObject imgElement	
     , 	String domain	) {
    (new customKeywords.UIKeywords()).verifyImagesRendering(
        	imgElement
         , 	domain)
}


def static "customKeywords.UIKeywords.refreshBrowser"() {
    (new customKeywords.UIKeywords()).refreshBrowser()
}


def static "customKeywords.UIKeywords.browserBack"() {
    (new customKeywords.UIKeywords()).browserBack()
}


def static "customKeywords.UIKeywords.scrollToElement"(
    	TestObject to	) {
    (new customKeywords.UIKeywords()).scrollToElement(
        	to)
}

 /**
	 * Click element
	 * @param to Katalon test object
	 */ 
def static "customKeywords.UIKeywords.clickElement"(
    	TestObject to	) {
    (new customKeywords.UIKeywords()).clickElement(
        	to)
}

 /**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */ 
def static "customKeywords.UIKeywords.getHtmlTableRows"(
    	TestObject table	
     , 	String outerTagName	) {
    (new customKeywords.UIKeywords()).getHtmlTableRows(
        	table
         , 	outerTagName)
}


def static "customKeywords.UIKeywords.getProperty"(
    	TestObject to	) {
    (new customKeywords.UIKeywords()).getProperty(
        	to)
}

 /**
	 *
	 * @param str = 'Funds&_H > All Mutal Funds&_C>asfasfd>asdfasfdasfd>'
	 * navigagteTopath("PutnamInvestments","alt")
	 */ 
def static "customKeywords.UIKeywords.navigateToPath"(
    	String str	
     , 	String locator	) {
    (new customKeywords.UIKeywords()).navigateToPath(
        	str
         , 	locator)
}

 /**
	 * Loads the given url and checks if the page is loaded successfully.
	 * Marks pass if loads successfully, marks warning if not 200 OK.
	 * @param Url
	 */ 
def static "customKeywords.UIKeywords.loadUrl"(
    	String Url	) {
    (new customKeywords.UIKeywords()).loadUrl(
        	Url)
}


def static "customKeywords.UIKeywords.navigateTo"(
    	String[] elements	) {
    (new customKeywords.UIKeywords()).navigateTo(
        	elements)
}


def static "customKeywords.UIKeywords.navigateToPages"(
    	String[] elements	) {
    (new customKeywords.UIKeywords()).navigateToPages(
        	elements)
}


def static "customKeywords.UIKeywords.verifyForBrokenLinks"(
    	TestObject to	
     , 	String propName	) {
    (new customKeywords.UIKeywords()).verifyForBrokenLinks(
        	to
         , 	propName)
}


def static "customKeywords.UIKeywords.verifyImagesRendering"(
    	TestObject imgElement	) {
    (new customKeywords.UIKeywords()).verifyImagesRendering(
        	imgElement)
}


def static "customKeywords.UIKeywords.navigateToPath"(
    	String str	) {
    (new customKeywords.UIKeywords()).navigateToPath(
        	str)
}

 /**
	 * Refresh browser
	 */ 
def static "customKeywords.InstitutionalKeywords.refreshBrowser"() {
    (new customKeywords.InstitutionalKeywords()).refreshBrowser()
}

 /**
	 * Click element
	 * @param to Katalon test object
	 */ 
def static "customKeywords.InstitutionalKeywords.clickElement"(
    	TestObject to	) {
    (new customKeywords.InstitutionalKeywords()).clickElement(
        	to)
}

 /**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */ 
def static "customKeywords.InstitutionalKeywords.getHtmlTableRows"(
    	TestObject table	
     , 	String outerTagName	) {
    (new customKeywords.InstitutionalKeywords()).getHtmlTableRows(
        	table
         , 	outerTagName)
}


def static "com.kms.katalon.keyword.applitools.BasicKeywords.checkElement"(
    	Eyes eyes	
     , 	WebElement element	) {
    (new com.kms.katalon.keyword.applitools.BasicKeywords()).checkElement(
        	eyes
         , 	element)
}


def static "com.kms.katalon.keyword.applitools.BasicKeywords.checkTestObject"(
    	TestObject testObject	
     , 	String testName	) {
    (new com.kms.katalon.keyword.applitools.BasicKeywords()).checkTestObject(
        	testObject
         , 	testName)
}


def static "com.kms.katalon.keyword.applitools.BasicKeywords.checkWindow"(
    	String testName	) {
    (new com.kms.katalon.keyword.applitools.BasicKeywords()).checkWindow(
        	testName)
}


def static "com.kms.katalon.keyword.applitools.EyesKeywords.eyesInit"() {
    (new com.kms.katalon.keyword.applitools.EyesKeywords()).eyesInit()
}


def static "com.kms.katalon.keyword.applitools.EyesKeywords.eyesOpen"(
    	String testName	
     , 	RectangleSize viewportSize	) {
    (new com.kms.katalon.keyword.applitools.EyesKeywords()).eyesOpen(
        	testName
         , 	viewportSize)
}


def static "com.kms.katalon.keyword.applitools.EyesKeywords.eyesClose"(
    	Eyes eyes	) {
    (new com.kms.katalon.keyword.applitools.EyesKeywords()).eyesClose(
        	eyes)
}


def static "com.kms.katalon.keyword.applitools.EyesKeywords.eyesOpenWithBaseline"(
    	String baselineName	
     , 	String testName	
     , 	RectangleSize viewportSize	) {
    (new com.kms.katalon.keyword.applitools.EyesKeywords()).eyesOpenWithBaseline(
        	baselineName
         , 	testName
         , 	viewportSize)
}
