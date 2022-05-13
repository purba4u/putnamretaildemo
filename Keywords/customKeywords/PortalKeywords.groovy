package customKeywords

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import globalVariables.gv
import internal.GlobalVariable
import customKeywords.UIKeywords as uik
import customKeywords.EncryptHelper as encryptHelper
import com.google.common.collect.Multimap
import com.google.common.collect.ArrayListMultimap
import customKeywords.PortalTestingKeywords as portal



public class PortalKeywords {
	@Keyword
	public static void verifyPrimaryNavigation(){
		try{
			portal.verifyPrimaryNavigation()
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
	}

	@Keyword
	public static void loginToPortal(String strUrl, String loginType, String userName, String passwd=null){
		try{
			portal.loginToPortal( strUrl,  loginType,  userName,  passwd)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
	}

	@Keyword
	public static void updateProfile(String updateType, String updatePwd=null, String updateSecurity=null){
		try{
			portal.updateProfile( updateType,  updatePwd,  updateSecurity)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
	}

	@Keyword
	public static boolean verifyPopupFormsLaunching(String formHeaderText, boolean closePopupWindow) {
		boolean flag
		try{
			flag = portal.verifyPopupFormsLaunching( formHeaderText,  closePopupWindow)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
		return flag;
	}

	@Keyword
	public static boolean verifyNewTabLaunches(String linkText,String newTabTitle) {
		boolean flag
		try{
			flag = portal.verifyNewTabLaunches( linkText,  newTabTitle)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
		return flag;
	}

	@Keyword
	public static  boolean verifyAllMutualFundNames(List<String> expectedFundNames){
		boolean flag
		try{
			flag = portal.verifyAllMutualFundNames( expectedFundNames)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
		return flag;
	}

	@Keyword
	public static  Map<Integer, HashMap<String,String>> getAllMutualFundDetails() {
		Map<Integer, HashMap<String,String>> tableItemsData = new HashMap<Integer, HashMap<String,String>>()
		try{
			tableItemsData = portal.getAllMutualFundDetails()
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
		return tableItemsData
	}

	@Keyword
	public static boolean verifyStickyNavigation(String fundName){
		boolean flag
		try{
			flag = portal.verifyStickyNavigation( fundName)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
		return flag;
	}

	@Keyword
	public static boolean verifyTabs(List<String> tabNames){
		boolean flag
		try{
			flag = portal.verifyTabs( tabNames)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
		return flag;
	}

	@Keyword
	public static boolean verifyAllPricingFundNames(List<String> expectedFundNames){
		boolean flag
		try{
			flag= portal.verifyAllPricingFundNames( expectedFundNames)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
		return flag
	}

	@Keyword
	public static void navigateToTab(String tabName){
		try{
			portal.navigateToTab( tabName)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
	}

	@Keyword
	public static Map<Integer, HashMap<String,String>> getAllDetailsFromTable(String uniqueHeaderName) {
		Map<Integer, HashMap<String,String>> tableItemsData = new HashMap<Integer, HashMap<String,String>>()
		try{
			tableItemsData = portal.getAllDetailsFromTable(uniqueHeaderName)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
		return tableItemsData
	}

	@Keyword
	public static boolean verifyBreadCrumb(String breadCrumbPath){
		boolean flag
		try{
			flag= portal.verifyBreadCrumb( breadCrumbPath)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
		return flag
	}

	@Keyword
	public static boolean verifyAnnuitiesFundNames(List<String> expectedFundNames){
		boolean flag
		try{
			flag= portal.verifyAnnuitiesFundNames( expectedFundNames)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
		return flag
	}

	@Keyword
	public static boolean verifyGlobalSearchFunction(String searchText){
		boolean flag
		try{
			flag= portal.verifyGlobalSearchFunction( searchText)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
		return flag
	}

	@Keyword
	public static List<String> getAllTexts(String xpathToGetList){
		List<String> lst
		try{
			lst = portal.getAllTexts( xpathToGetList)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
		return lst
	}

	@Keyword
	public static boolean verifyMutualFundsFilter(List<String> assetClasses, int annualizedMinPer, int annualizedMaxPer, String timePeriod,	int morningStartRating, boolean lipperRanking, String manager){
		boolean flag
		try{
			flag = portal.verifyMutualFundsFilter( assetClasses, annualizedMinPer, annualizedMaxPer, timePeriod,	morningStartRating, lipperRanking, manager)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
		return flag
	}


	//Prabhat
	@Keyword
	public static void verifyPageTitle(String str){
		try{
			portal.verifyPageTitle( str)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}

	}

	@Keyword
	public static void verifyPageName(String PageName){
		try{
			portal.verifyPageName(PageName)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())}
	}

	@Keyword
	public static void verifyPage(String PageName=null,String PageTitle=null){
		portal.verifyPage( PageName, PageTitle)
	}

	@Keyword
	public static void verifyElementInContainer(TestObject to, String elementName){
		try{
			portal.verifyElementInContainer( to,  elementName)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())}
	}

	@Keyword
	public static void verifyDocJsflink(TestObject to,String newPageFormat){
		try{
			portal.verifyDocJsflink( to, newPageFormat)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())}
	}

	@Keyword
	public static void Datepicker(TestObject calender,String Date){
		try{
			portal.Datepicker( calender, Date)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())}
	}

	@Keyword
	public static void closeFormWindow(){
		try{
			portal.closeFormWindow()
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())}
	}

	@Keyword
	public static void waitForJsfPageLoad(TestObject to = null, String xpath= null, String type, int maxTimeout){
		try{
			portal.waitForJsfPageLoad( to ,  xpath,  type,  maxTimeout)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())}
	}

	@Keyword
	public static void switchToWindow(TestObject to,int maxTimeout){
		try{
			portal.switchToWindow( to, maxTimeout)

		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())}
	}

	@Keyword
	public static void validateDailyPricing(TestObject to){
		try{
			portal.validateDailyPricing( to)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())}
	}



	@Keyword
	public static void verifyDailyPricePageTable(TestObject to,String PageName1,String PageName2){
		try{
			portal.verifyDailyPricePageTable( to, PageName1,PageName2)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())}
	}

	@Keyword
	public static void compareMaps(Map<String, String> map1,Map<String, String> map2){
		try{
			portal.compareMaps( map1, map2)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())}
	}


	@Keyword

	public static void searchFunds(TestObject to,TestObject shareClass, TestObject tbl,String PageName,String classValue= null,String FundName){
		try{
			portal.searchFunds( to, shareClass,  tbl, PageName, classValue, FundName)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())}

	}


}
