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
import internal.GlobalVariable as GlobalVariable

public class PortalTestingKeywords {

	static WebDriver driver = uik.getCurrentSessionDriver()

	//Verifies all the links of the Primary Navigation container.
	@Keyword
	public static void verifyPrimaryNavigation(){
		try{
			uik.navigateToPath("Funds&_H")
			uik.navigateToPath("How we invest&_H")
			uik.navigateToPath("Retirement&_H")
			uik.verifyForBrokenLinks(findTestObject('IndividualInvestor/PageSpecificObjects/Frames/primaryNavigationContainer'),"href")  //changes made by prabhat
			uik.navigateToPath("College savings&_H")
			uik.navigateToPath("Perspectives&_H")
			uik.navigateToPath("Forms&_H")
			uik.navigateToPath("Tax center&_H")

		}catch(Exception e){
			KeywordUtil.markFailed("Exception ocured in primaryNavigation verification:"+e.getMessage())
		}
	}

	@Keyword
	public static void loginToPortal(String strUrl, String loginType, String userName, String passwd=null){
		uik.loadUrl(strUrl)
		uik.waitForDocLoad()
		String locatorStr
		String pwd;

		if(GlobalVariable.IsPasswordPlainText){
			pwd = passwd;
		}else{
			pwd = encryptHelper.decrypt(passwd);
		}
		switch (loginType.toLowerCase()){
			case "individual":
				uik.getCurrentSessionDriver().findElement(By.xpath("//input[@id='userName']")).sendKeys(userName)
				KeywordUtil.logInfo('Inpuuting the username')
				uik.getCurrentSessionDriver().findElement(By.xpath("//input[@id='password']")).sendKeys(pwd)
				KeywordUtil.logInfo('Inpuuting the password')
				uik.getCurrentSessionDriver().findElement(By.xpath("//button[text()='Login']")).click()
				break

			case "financial":
				uik.getCurrentSessionDriver().findElement(By.xpath("//input[@id='primaryEmailInput']")).sendKeys(userName)
				KeywordUtil.logInfo('Inpuuting the username')
				uik.getCurrentSessionDriver().findElement(By.xpath("//button[@id='submit-email-form']")).click()
				break

			case "institutional":
				uik.getCurrentSessionDriver().findElement(By.xpath("//input[@class='form-control upper-case mr-sm-1 mb-1']")).sendKeys(userName)
				KeywordUtil.logInfo('Inpuuting the username')
				uik.getCurrentSessionDriver().findElement(By.xpath("//input[@class='form-control mr-sm-1 mb-1']")).sendKeys(pwd)
				KeywordUtil.logInfo('Inpuuting the password')
				uik.getCurrentSessionDriver().findElement(By.xpath("//buttonp@type='submit' and @class='btn btn-primary btn-block'']")).click()
				break

			default:
				break
		}

		sleep(5000)
		uik.waitForDocLoad()

		//Confirm if the login is successful, by checking the logout link

		TestObject to = uik.convertXpathtoTestObject("//a[text()='Logout']","to")

		if(WebUI.waitForElementPresent(to, 10)){
			KeywordUtil.markPassed(loginType+ "'s Login is Successful")
		}else{
			KeywordUtil.markFailed(loginType+ "'s Login failed.")
		}


	}

	@Keyword
	public static void updateProfile(String updateType, String updatePwd=null, String updateSecurity=null){

		uik.navigateToPath("username-dropdown","id")
		uik.navigateToPath("Update Profile")
		Random rand = new Random()

		switch (updateType){
			case "security":
				String[] security = updateSecurity.split("_")
				String temp = security[0]+rand.nextInt(1000).toString()
				WebUI.setText(findTestObject('IndividualInvestor/PageSpecificObjects/updateProfile/txtSecQuestion'), temp)
				KeywordUtil.logInfo(temp+' stored in security question')
			//WebUI.setText(findTestObject('IndividualInvestor/PageSpecificObjects/updateProfile/txtSecAnswer'),security[1])
				WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/updateProfile/btnSubmit'))
				uik.waitForDocLoad()
				WebUI.waitForElementPresent(findTestObject('IndividualInvestor/PageSpecificObjects/updateProfile/lblProfileUpdated'), 15)
				String str = WebUI.getText(findTestObject('IndividualInvestor/PageSpecificObjects/updateProfile/lblProfileUpdated'))


				if(str.toLowerCase().contains("thank you"))
					KeywordUtil.markPassed("Data updated successfully")
				else KeywordUtil.markFailed("Data update information not received. Please debug.")
				break

			case "updatePwd":
				String[] pwd = updatePwd.split("_")
				WebUI.setText(findTestObject('IndividualInvestor/PageSpecificObjects/updateProfile/txtCurrentPwd'),pwd[0])
				WebUI.setText(findTestObject('IndividualInvestor/PageSpecificObjects/updateProfile/txtNewPwd'),pwd[1])
				WebUI.setText(findTestObject('IndividualInvestor/PageSpecificObjects/updateProfile/txtConfirmPwd'),pwd[1])
				WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/updateProfile/btnSubmit'))
				uik.waitForDocLoad()
				break

			default:
				break


		}



		/*uik.navigateToPath("alerts-tab", 'id')
		 WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/updateProfile/tgBalanceAlerts'))
		 WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/updateProfile/btnSaveChanges'))
		 WebUI.verifyElementPresent(findTestObject('IndividualInvestor/PageSpecificObjects/updateProfile/lblSucessMsg'), 10)*/
	}




	// ---------------------------PuntamSpecifickeywords: 1-------------------------------------

	/**
	 * This will verify forms popup launching properly ex. Login Form, Password Reset Form
	 */
	@Keyword
	public static boolean verifyPopupFormsLaunching(String formHeaderText, boolean closePopupWindow) {
		String xpathValue = "//*[text()='"+formHeaderText+"']"
		TestObject formHeader = new TestObject("FormHeader");
		formHeader.addProperty("xpath", ConditionType.EQUALS, xpathValue, true);

		boolean flag = false
		try {
			WebElement element = WebUI.findWebElement(formHeader);
			flag = element.isDisplayed();
			System.out.println( "Is Form with header--"+formHeaderText+" Loaded--"+flag);
			KeywordUtil.markPassed(formHeaderText+" menu landing page loaded");

			if(closePopupWindow){
				WebUI.delay(5);
				String closePopupXpath = "//button[@class='close']";
				TestObject closePopup = new TestObject("closePopup");
				closePopup.addProperty("xpath", ConditionType.EQUALS, closePopupXpath, true);
				WebUI.findWebElement(closePopup).click();
				WebUI.delay(5);
			}
		} catch (Exception e) {
			KeywordUtil.markFailed(formHeaderText+" menu landing page NOT loaded\n"+e.getMessage())
		}

		return flag;
	}


	/**
	 * Click link and verify new tab opens and close new window
	 */
	@Keyword
	public static boolean verifyNewTabLaunches(String linkText,String newTabTitle) {
		boolean flag = false;
		String link = "//a[contains(text(),'LINK') and @target='_blank']";
		String linkXpath = link.replace('LINK', linkText);
		try{
			TestObject linkTestObject = new TestObject("linkTestObject");
			linkTestObject.addProperty("xpath", ConditionType.EQUALS, linkXpath, true);
			WebUI.findWebElement(linkTestObject).click();
			WebUI.switchToWindowIndex(1);
			WebUI.delay(10);

			String title = WebUI.getWindowTitle();

			if(title.trim().contains(newTabTitle)){
				flag = true;
				KeywordUtil.markPassed("New tab opened and found title-"+newTabTitle+" on the page");
			}else{
				KeywordUtil.markFailed("New tab opened and did not found title-"+newTabTitle+" on the page");
			}
			WebUI.closeWindowIndex(1);
			WebUI.switchToWindowIndex(0);
		} catch(Exception e){
			KeywordUtil.markFailed("New tab opened and did not found title-"+newTabTitle+" on the page");
			flag = false;
		}
		return flag;
	}

	/**
	 * this method will take funds names as input and compare with values displayed on the page
	 * @param expectedFundNames
	 * @return
	 */
	@Keyword
	public static boolean verifyAllMutualFundNames(List<String> expectedFundNames){
		boolean flag = false;
		List<String> actualFundNames = new ArrayList<String>();
		Map<Integer, HashMap<String,String>> actualTableItemsData =new HashMap<Integer, HashMap<String,String>>();
		Set<Integer> keys;
		try{
			actualTableItemsData = getAllMutualFundDetails();
			keys = actualTableItemsData.keySet();
			for (key in keys) {
				System.out.println((actualTableItemsData.get(key)).get("Fund").split("\\n")[0]);
				actualFundNames.add((actualTableItemsData.get(key)).get("Fund").split("\\n")[0]);
			}

			if(actualFundNames.containsAll(expectedFundNames)){
				flag = true;
				KeywordUtil.markPassed("All the fund names present on the Mutual funds page");
			}else{
				KeywordUtil.markFailed("Some of the fund names not matching Mutual funds page\n");
			}
		}catch(Exception e){
			KeywordUtil.markFailed("Someting went wrong while matching mutual fund names\n"+e.getMessage());
		}
		return flag;
	}

	/**
	 * Get list of mutual funds displayed
	 * @return
	 */
	@Keyword
	public static Map<Integer, HashMap<String,String>> getAllMutualFundDetails() {

		String mutualFundsTableHeader = "//div[@id='fund-screener-table']/div[contains(@class,'css-table-header')]";
		String mutualFundsTableItems = "//div[@id='fund-screener-table']/div[contains(@class,'fund-screener-item')]";
		String mutualFundsTableCell = "//div[@id='fund-screener-table']/div[contains(@class,'fund-screener-item') and @data-relation-id='ROW_ID' and @data-column-index='COLUMN_ID']"
		String mutualFundsPlusIcon = "//span[@class='putnamicon putnamicon-plus']"; //This used to find number of rows
		int tableRowCount = 0;
		Map<Integer, Map<String,String>> tableItemsData = new HashMap<Integer, HashMap<String,String>>();
		List<WebElement> tableItems;
		Map<String, Integer> headerIndexMap;
		try{
			TestObject mutualFundsTableItemsTO = new TestObject("mutualFundsTableItemsTO");
			mutualFundsTableItemsTO.addProperty("xpath", ConditionType.EQUALS, mutualFundsTableItems, true);

			TestObject mutualFundsPlusIconTO = new TestObject("mutualFundsPlusIconTO");
			mutualFundsPlusIconTO.addProperty("xpath", ConditionType.EQUALS, mutualFundsPlusIcon, true);

			headerIndexMap = getAllMutualFundsHeaderIndex(mutualFundsTableHeader);

			tableRowCount = WebUI.findWebElements(mutualFundsPlusIconTO,5).size();

			//Get all the table rows
			//			tableItems = WebUI.findWebElements(mutualFundsTableItemsTO, 5);

			String cellValue = "";
			for(int rowIndex=0; rowIndex< tableRowCount; rowIndex++){
				Map<String,String> rowEntry =new HashMap<String,String>();
				Set<String> headerNames = headerIndexMap.keySet();
				//System.out.println("headerNames--"+headerNames);
				for (columnName in headerNames) {
					//System.out.println("columnName--"+headerIndexMap.get(columnName));
					String mutualFundsTableCellRowID = mutualFundsTableCell.replace("ROW_ID", rowIndex.toString());
					String mutualFundsTableCellCoulmnID = mutualFundsTableCellRowID.replace("COLUMN_ID", headerIndexMap.get(columnName).toString());
					TestObject mutualFundsTableCellTO = new TestObject("mutualFundsTableCellTO");
					mutualFundsTableCellTO.addProperty("xpath", ConditionType.EQUALS, mutualFundsTableCellCoulmnID, true);

					cellValue = WebUI.findWebElement(mutualFundsTableCellTO).getText();
					//System.out.println("mutualFundsTableCell--"+mutualFundsTableCell)
					//System.out.println("Cell value--"+cellValue);
					rowEntry.put(columnName,cellValue);
				}
				tableItemsData.put(rowIndex, rowEntry);
			}
		}catch(Exception e){
			KeywordUtil.markFailed("Something went wrong in getAllMutualFunds method\n"+e.getMessage());

		}
		System.out.println("-----------tableItemsData--------\n"+tableItemsData)
		return tableItemsData;
	}

	/**
	 * This method will get us table header name and its coulmn index number from the table
	 * @param tableHeaderXpath
	 * @param startIndexOfColumn this paramter to specify whether start of column should be zero or one
	 * @return
	 */
	public static Map<String, Integer> getAllHeaderIndex(String tableHeaderXpath, int startIndexOfColumn){
		Map<String, Integer> headerIndexMap = new HashMap<String, Integer>();
		//		String tableHeader = tableHeaderXpath;
		List<WebElement> headerElements;
		try{
			TestObject tableHeaderTO = new TestObject("tableHeaderTO");
			tableHeaderTO.addProperty("xpath", ConditionType.EQUALS, tableHeaderXpath, true);

			//Get all the header
			headerElements = WebUI.findWebElements(tableHeaderTO, 5);
			int index=startIndexOfColumn;
			for (header in headerElements) {
				System.out.println(header.getText());
				if(header.getText()!=null && header.getText()!='' && header.getText()!=' ')
				{
					headerIndexMap.put(header.getText(), index);
					index++;
				}else{
					index++;
				}
			}
		}catch(Exception e){
			KeywordUtil.markFailed("Some thing went wrong in getAllHeaderIndex method\n"+e.getMessage());
			System.out.println("Some thing went wrong in getAllHeaderIndex method\n"+e.getMessage());
		}
		System.out.println("headerIndexMap------"+headerIndexMap);
		return headerIndexMap;
	}

	/**
	 * This method will get us table header name and its coulmn index number from the table
	 * @param tableHeaderXpath
	 * @return
	 */
	public static Map<String, Integer> getAllMutualFundsHeaderIndex(String tableHeaderXpath){
		Map<String, Integer> headerIndexMap = new HashMap<String, Integer>();
		String mutualFundsTableHeader = tableHeaderXpath;
		List<WebElement> headerElements;
		try{
			TestObject mutualFundsTableHeaderTO = new TestObject("mutualFundsTableHeaderTO");
			mutualFundsTableHeaderTO.addProperty("xpath", ConditionType.EQUALS, mutualFundsTableHeader, true);

			//Get all the header
			headerElements = WebUI.findWebElements(mutualFundsTableHeaderTO, 5);
			int index=0;
			for (header in headerElements) {
				System.out.println(header.getText());
				if(header.getText()!=null && header.getText()!='' && header.getText()!=' ')
				{
					headerIndexMap.put(header.getText(), index);
					index++;
				}else{
					index++;
				}
			}
		}catch(Exception e){
			KeywordUtil.markFailed("Some thing went wrong in getAllMutualFundsHeaderIndex method\n"+e.getMessage());
			System.out.println("Some thing went wrong in getAllMutualFundsHeaderIndex method\n"+e.getMessage());
		}
		System.out.println("headerIndexMap------"+headerIndexMap);
		return headerIndexMap;
	}

	/**
	 * this method will check different sections of the mutual fund details on the page
	 * @return
	 * TODO:Click Links and check if the page is scrolled to respective part of the page.
	 * Rename to "VerifyStickyNavigation"
	 */
	@Keyword
	public static boolean verifyStickyNavigation(String fundName){
		boolean flag = false;
		List<String> listOfXpathsToVerify = new ArrayList<String>();
		listOfXpathsToVerify.add("//h1[contains(text(),'"+fundName+"')]");
		listOfXpathsToVerify.add("//div[@id='breadcrumb']//select[@name='fund']");
		listOfXpathsToVerify.add("//div[@id='breadcrumb']//select[@name='shareClass']");
		listOfXpathsToVerify.add("//a[text()='Highlights']");
		//		listOfXpathsToVerify.add("//a[text()='Literature']");
		listOfXpathsToVerify.add("//a[text()='Performance']");
		listOfXpathsToVerify.add("//a[text()='Holdings']");
		listOfXpathsToVerify.add("//a[text()='Expenses']");

		try{
			for (xpath in listOfXpathsToVerify) {
				TestObject mutualFundsDetailsGenricTO = new TestObject("mutualFundsDetailsGenricTO");
				mutualFundsDetailsGenricTO.addProperty("xpath", ConditionType.EQUALS, xpath, true);
				if(!WebUI.verifyElementVisible(mutualFundsDetailsGenricTO)){
					flag = false;
					KeywordUtil.markFailed("Fund details page NOT displayed properly, missing element -----"+xpath);
					break;
				}else{
					flag = true;
				}
			}

			if(flag){
				KeywordUtil.markPassed("Fund details page is properly displayed");
			}
		}catch(Exception e){
			flag = false
			KeywordUtil.markFailed("Something went wrong while checking the fund details page\n"+e.getMessage());
		}
		return flag

	}


	/**
	 * this method will check for different tabs on a page
	 * @Usage use this method if the tabs xapth has li tags which has attribute data-tabref
	 * @param tabNames
	 * @return
	 */
	@Keyword
	public static boolean verifyTabs(List<String> tabNames){
		boolean flag = false;
		String tabPlaceHolder = "//li[@data-tabref and text()='PLACEHOLDER']";
		String tabh1PlaceHolder = "//li[@data-tabref]/h1[text()='PLACEHOLDER']";

		try{

			for (tabName in tabNames) {
				String tabXpath = tabPlaceHolder.replace('PLACEHOLDER', tabName);

				TestObject tabTO = new TestObject("tabTO");
				tabTO.addProperty("xpath", ConditionType.EQUALS, tabXpath, true);

				flag = (WebUI.findWebElements(tabTO, 30).size()!=0) ? true : false
				if(flag){
					KeywordUtil.markPassed("Tab with name--"+tabName+"--Displayed");
				}else{
					String tabh1Xpath = tabh1PlaceHolder.replace('PLACEHOLDER', tabName);
					TestObject tabh1TO = new TestObject("tabh1TO");
					tabh1TO.addProperty("xpath", ConditionType.EQUALS, tabh1Xpath, true);

					System.out.println("We could not find tab with xpath-"+tabXpath+"----trying with other xpath--"+tabh1Xpath)
					flag = (WebUI.findWebElements(tabh1TO, 30).size()!=0) ? true : false
					if(flag){
						KeywordUtil.markPassed("Tab with name--"+tabName+"--Displayed");
					}else{
						KeywordUtil.markFailed("Tab with name--"+tabName+"--NOT Displayed");
					}
				}
			}
		}catch(Exception e){
			flag = false
			KeywordUtil.markFailed("Something went wrong while finding tab\n"+e.getMessage());
		}
		return flag
	}

	/**
	 * this method will take pricing funds names as input and compare with values displayed on the page
	 * @param expectedFundNames
	 * @return
	 */
	@Keyword
	public static boolean verifyAllPricingFundNames(List<String> expectedFundNames){
		boolean flag = false;
		List<String> actualFundNames = new ArrayList<String>();
		Map<Integer, HashMap<String,String>> actualTableItemsData =new HashMap<Integer, HashMap<String,String>>();
		Set<Integer> keys;
		try{
			//			actualTableItemsData = getAllPricingFundDetails();
			actualTableItemsData = getAllDetailsFromTable("Cusip");
			keys = actualTableItemsData.keySet();
			for (key in keys) {
				//				System.out.println((actualTableItemsData.get(key)).get("Fund"));
				actualFundNames.add((actualTableItemsData.get(key)).get("Fund"));
			}

			if(actualFundNames.containsAll(expectedFundNames)){
				flag = true;
				KeywordUtil.markPassed("All the pricing fund names present on the Mutual funds page");
			}else{
				KeywordUtil.markFailed("Some of the pricing fund names not matching pricing fund names on the page\n");
			}
		}catch(Exception e){
			KeywordUtil.markFailed("Someting went wrong while matching pricing fund names\n"+e.getMessage());
		}
		return flag;
	}


	/**
	 * this method will navigate different tabs on a page
	 * @Usage use this method if the tabs xapth has li tags which has attribute data-tabref
	 * <li class="" data-tabref="tab1">::before Variable Trust ::after</li>
	 * @param tabNames
	 * @return
	 */
	@Keyword
	public static void navigateToTab(String tabName){

		String tabPlaceHolder = "//li[@data-tabref and text()='PLACEHOLDER']";

		try{

			String tabXpath = tabPlaceHolder.replace('PLACEHOLDER', tabName);

			TestObject tabTO = new TestObject("tabTO");
			tabTO.addProperty("xpath", ConditionType.EQUALS, tabXpath, true);

			WebUI.findWebElement(tabTO).click();
			KeywordUtil.markPassed("Clickied on tab--"+tabName);

		}catch(Exception e){
			KeywordUtil.markFailed("Something went wrong while clcking tab--"+tabName+"\n"+e.getMessage());
		}
	}

	/**
	 * Get all details from table
	 * @usage use this method if html has <table> tag
	 * @return
	 */
	@Keyword
	public static Map<Integer, HashMap<String,String>> getAllDetailsFromTable(String uniqueHeaderName) {

		String tableHeaderPlaceHolder = "//th[contains(text(),'HEADER_NAME')]//parent::tr//th";
		String tableItemsPlaceHolder = "//th[contains(text(),'HEADER_NAME')]//ancestor::table//tbody//tr";
		String tableCellPlaceHolder = "//th[contains(text(),'HEADER_NAME')]//ancestor::table//tbody//tr[ROW_ID]//td[COLUMN_ID]"

		String tableHeaderXpath = tableHeaderPlaceHolder.replace("HEADER_NAME", uniqueHeaderName);
		String tableItemsXpath = tableItemsPlaceHolder.replace("HEADER_NAME", uniqueHeaderName);
		String tableCellXpath = tableCellPlaceHolder.replace("HEADER_NAME", uniqueHeaderName);

		int tableRowCount = 0;
		Map<Integer, Map<String,String>> tableItemsData = new HashMap<Integer, HashMap<String,String>>();
		List<WebElement> tableItems;
		Map<String, Integer> headerIndexMap;
		try{
			TestObject tableItemsTO = new TestObject("tableItemsTO");
			tableItemsTO.addProperty("xpath", ConditionType.EQUALS, tableItemsXpath, true);

			headerIndexMap = getAllHeaderIndex(tableHeaderXpath, 1);

			tableRowCount = WebUI.findWebElements(tableItemsTO, 5).size();
			System.out.println("Row count---------"+tableRowCount)
			String cellValue = "";
			for(int rowIndex=1; rowIndex<= tableRowCount; rowIndex++){
				Map<String,String> rowEntry =new HashMap<String,String>();
				Set<String> headerNames = headerIndexMap.keySet();
				//System.out.println("headerNames--"+headerNames);
				for (columnName in headerNames) {
					//System.out.println("columnName--"+headerIndexMap.get(columnName));
					String pricingFundsTableCellRowID = tableCellXpath.replace("ROW_ID", rowIndex.toString());
					String pricingFundsTableCellCoulmnID = pricingFundsTableCellRowID.replace("COLUMN_ID", headerIndexMap.get(columnName).toString());
					TestObject pricingFundsTableCellTO = new TestObject("pricingFundsTableCellTO");
					pricingFundsTableCellTO.addProperty("xpath", ConditionType.EQUALS, pricingFundsTableCellCoulmnID, true);

					cellValue = WebUI.findWebElement(pricingFundsTableCellTO).getText();
					//					System.out.println("cellValue----"+cellValue)
					rowEntry.put(columnName,cellValue);
				}
				tableItemsData.put(rowIndex, rowEntry);
			}
		}catch(Exception e){
			KeywordUtil.markFailed("Something went wrong in getAllDetailsFromTable method\n"+e.getMessage());

		}
		System.out.println("-----------tableItemsData--------\n"+tableItemsData)
		return tableItemsData;
	}

	/**
	 * This method will check breadcrumb
	 * @param breadCrumbPath specify the path of the breadcrumb
	 * @usage Home>Retirement>Annuities
	 * @return
	 */
	@Keyword
	public static boolean verifyBreadCrumb(String breadCrumbPath){
		boolean flag = false;
		String breadCrumbXapth = "//div[@id='breadcrumb']";
		String breadCrumbLink = "//div[@id='breadcrumb']//a[text()='LINK']";
		String[] breadCrumbExpectedItems = breadCrumbPath.split('>');
		String[] breadCrumbActualItems =[];
		System.out.println("breadCrumbPathExpected-"+breadCrumbExpectedItems);

		TestObject breadCrumbTO = new TestObject("breadCrumbTO");
		breadCrumbTO.addProperty("xpath", ConditionType.EQUALS, breadCrumbXapth, true);
		try{
			String breadCrumbText = WebUI.findWebElement(breadCrumbTO).getText();
			System.out.println(breadCrumbText);
			breadCrumbActualItems = breadCrumbText.split('\\n');

			for(int index=0; index< breadCrumbExpectedItems.size()-1; index++) {
				String breadCrumbLinkXapth = breadCrumbLink.replace("LINK", breadCrumbExpectedItems[index]);
				TestObject breadCrumbItemTO = new TestObject("breadCrumbItemTO");
				breadCrumbItemTO.addProperty("xpath", ConditionType.EQUALS, breadCrumbLinkXapth, true);
				if(!WebUI.findWebElement(breadCrumbTO).isDisplayed())
				{
					flag = false;
					KeywordUtil.markFailed("Did not find link-"+breadCrumbExpectedItems[index]+" under breadcrumb\n");
					break;
				}else{
					flag = true;
				}
			}
			//Checking last text from expected and actual breadcrumb
			if(flag){
				if((breadCrumbExpectedItems[breadCrumbExpectedItems.size()-1]).equalsIgnoreCase(breadCrumbActualItems[breadCrumbActualItems.size()-1])){
					flag = true;
				}else{
					flag = false;
					KeywordUtil.markFailed("Did not find last expected text-"+breadCrumbExpectedItems[breadCrumbExpectedItems.size()-1]+" but actual last text-"+breadCrumbActualItems[breadCrumbActualItems.size()-1]+" under breadcrumb\n");
				}
			}

			if(flag){
				KeywordUtil.markPassed("Breadcrumb verification successfull-"+breadCrumbPath+"\n");
			}

		}catch(Exception e){
			flag = false
			KeywordUtil.markFailed("Something went wrong in verifyBreadCrumb method\n"+e.getMessage());
		}

		return flag;
	}

	/**
	 * this method will take funds names as input and compare with values displayed on Annuities page for Variable Trust tab
	 * @param expectedFundNames
	 * @return
	 */
	@Keyword
	public static boolean verifyAnnuitiesFundNames(List<String> expectedFundNames){
		boolean flag = false;
		List<String> actualFundNames = new ArrayList<String>();
		Map<Integer, HashMap<String,String>> actualTableItemsData =new HashMap<Integer, HashMap<String,String>>();
		Set<Integer> keys;
		try{
			actualTableItemsData = getAllDetailsFromTable("Fund name")
			keys = actualTableItemsData.keySet();
			for (key in keys) {
				System.out.println((actualTableItemsData.get(key)).get("Fund name").split("\\n")[0]);
				actualFundNames.add((actualTableItemsData.get(key)).get("Fund name").split("\\n")[0]);
			}

			if(actualFundNames.containsAll(expectedFundNames)){
				flag = true;
				KeywordUtil.markPassed("All the fund names present on the Annuities funds page");
			}else{
				KeywordUtil.markFailed("Some of the fund names not matching Annuities funds page\n");
			}
		}catch(Exception e){
			flag = false
			KeywordUtil.markFailed("Someting went wrong while matching Annuities fund names\n"+e.getMessage());
		}
		return flag;
	}

	/**
	 * this method will check for search functionality
	 * @param searchText pass the search text
	 * @return
	 *
	 * TODO: Change the name to globalsearch
	 */
	@Keyword
	public static boolean verifyGlobalSearchFunction(String searchText){
		boolean flag = false;
		String searchImageXpath = "//a[@class='nav-link start-search']";
		String searchButtonXpath = "//button[text()='Search']";
		String searchTextBoxXpath = "//span[text()='What are you looking for?']//parent::label/preceding-sibling::input";
		String searchResultsXpath = "//div[@id='search-results-list']//ul[@id='result-list']//li"


		TestObject searchImageTO = new TestObject("searchImageTO");
		searchImageTO.addProperty("xpath", ConditionType.EQUALS, searchImageXpath, true);

		TestObject searchButtonTO = new TestObject("searchButtonTO");
		searchButtonTO.addProperty("xpath", ConditionType.EQUALS, searchButtonXpath, true);

		TestObject searchTextBoxTO = new TestObject("searchTextBoxTO");
		searchTextBoxTO.addProperty("xpath", ConditionType.EQUALS, searchTextBoxXpath, true);

		TestObject searchResultsTO = new TestObject("searchResultsTO");
		searchResultsTO.addProperty("xpath", ConditionType.EQUALS, searchResultsXpath, true);

		try{
			WebUI.findWebElement(searchImageTO).click();
			WebUI.delay(2);
			WebUI.findWebElement(searchButtonTO).isDisplayed();
			WebUI.findWebElement(searchTextBoxTO).click();
			WebUI.findWebElement(searchTextBoxTO).sendKeys(searchText);
			WebUI.delay(5);


			//List<WebElement> searchFundsResults = WebUI.findWebElements(searchFundsResultsTO, 5);

			WebUI.findWebElement(searchButtonTO).click();
			List<WebElement> searchResults = WebUI.findWebElements(searchResultsTO, 5);
			if(searchResults.size() > 0){
				flag = true;
				KeywordUtil.markPassed("Search functionality working fine without any issues\n");
			}else{
				flag = false;
				KeywordUtil.markFailed("Search functionality not working proper\n");
			}
		}catch(Exception e){
			KeywordUtil.markFailed("Someting went wrong while verifying search functionality\n"+e.getMessage());
		}

		return flag;
	}

	/**
	 * This method will return all the texts from xpath
	 * @usage <li>text1</li><li>text2</li>
	 * @param xpathToList
	 * @return
	 */
	@Keyword
	public static List<String> getAllTexts(String xpathToGetList){
		List<String> listOfTexts = new ArrayList<String>();
		TestObject listTO = new TestObject("listTO");
		listTO.addProperty("xpath", ConditionType.EQUALS, xpathToGetList, true);
		try{
			List<WebElement> listOfElements = WebUI.findWebElements(listTO, 5);
			for (ele in listOfElements) {
				System.out.println("----"+ele.getText().toString().trim().replaceAll("\n", " "))
				listOfTexts.add(ele.getText().toString().trim().replaceAll("\n", " "));
			}
		}catch(Exception e){
			KeywordUtil.markFailed("Someting went wrong while getting all texts\n"+e.getMessage());
		}
		System.out.println("----------listOfTexts--------\n"+listOfTexts)
		return listOfTexts;
	}

	/**
	 * This method will filter the mutual funds based on user input
	 * */
	@Keyword
	public static boolean verifyMutualFundsFilter(List<String> assetClasses, int annualizedMinPer, int annualizedMaxPer, String timePeriod,
			int morningStartRating, boolean lipperRanking, String manager){
		boolean flag = false;
		String filterIcon = "//span[@class='putnamicon putnamicon-filter2']"
		String assetClassXpath = "//div[@id='asset-class-filters']//a[text()='ASSET_CLASS']//parent::div"
		String annualizedMinPerXpath = "//input[@id='annualized-returns-min']"
		String annualizedMaxPerXpath = "//input[@id='annualized-returns-max']"
		String timePeriodXpath = "//select[@id='time-period-filter']"
		String morningStartRatingXpath = "//span[@id='morningstar-ratings-filter']//span[@value='"+morningStartRating+"']"
		String lipperRankingXpath = "//strong[text()='Lipper Rankings']//parent::*//following-sibling::input"
		String managerXpath = "//select[@id='portfolio-manager-filter']"
		String closePopupXpath = "//a[@id='fund-screener-filter-close']"
		String clearFilterXpath = "//a[@id='fund-screener-filter-clear']"
		Map<String, String> timePeriodVsColName = new HashMap<String, String>()
		timePeriodVsColName.put("10 years/Life","10 yrs. / Life")
		timePeriodVsColName.put("5 years","5 yrs.")
		timePeriodVsColName.put("3 years","3 yrs.")
		timePeriodVsColName.put("1 year","1 yr.")

		Map<Integer, HashMap<String,String>> actualTableItemsData =new HashMap<Integer, HashMap<String,String>>();

		TestObject dynamicTO = new TestObject("dynamicTO")
		try{
			dynamicTO.addProperty('xpath', ConditionType.EQUALS, filterIcon, true)
			WebUI.click(dynamicTO);

			dynamicTO.addProperty('xpath', ConditionType.EQUALS, clearFilterXpath, true)
			WebUI.click(dynamicTO);

			if(assetClasses!=null && assetClasses.size()>=1){
				for (asset in assetClasses) {
					String assetClassXpathReplaced = assetClassXpath.replace("ASSET_CLASS", asset)
					dynamicTO.addProperty('xpath', ConditionType.EQUALS, assetClassXpathReplaced, true)
					WebUI.click(dynamicTO);
				}
			}

			if(annualizedMinPer >= 0){
				dynamicTO.addProperty('xpath', ConditionType.EQUALS, annualizedMinPerXpath, true)
				WebUI.setText(dynamicTO, annualizedMinPer.toString())
			}

			if(annualizedMaxPer > 0){
				dynamicTO.addProperty('xpath', ConditionType.EQUALS, annualizedMaxPerXpath, true)
				WebUI.setText(dynamicTO, annualizedMaxPer.toString())
			}

			if(timePeriod!=null && !timePeriod.isEmpty()){
				dynamicTO.addProperty('xpath', ConditionType.EQUALS, timePeriodXpath, true)
				WebUI.selectOptionByLabel(dynamicTO, timePeriod, false)
				timePeriod = timePeriodVsColName.get(timePeriod)
			}else{
				//Setting time period to default value for further use
				timePeriod = "10 yrs. / Life"
			}

			if(morningStartRating>0 && morningStartRating<6){
				dynamicTO.addProperty('xpath', ConditionType.EQUALS, morningStartRatingXpath, true)
				WebUI.click(dynamicTO)
			}else{
				KeywordUtil.markWarning("Not selecting morning start rating, as user did not pass any value, pass proper value between 1-5")
			}

			if(lipperRanking){
				dynamicTO.addProperty('xpath', ConditionType.EQUALS, lipperRankingXpath, true)
				WebUI.click(dynamicTO)
			}

			if(manager!=null && !manager.isEmpty()){
				dynamicTO.addProperty('xpath', ConditionType.EQUALS, managerXpath, true)
				WebUI.selectOptionByLabel(dynamicTO, manager, false)
			}

			dynamicTO.addProperty('xpath', ConditionType.EQUALS, closePopupXpath, true)
			WebUI.click(dynamicTO)

			//			WebUI.waitForPageLoad(10)
			WebUI.delay(5)
			//verification method starts below
			//This will verify the expected fund category
			actualTableItemsData = getAllMutualFundDetails();

			if(assetClasses!=null && assetClasses.size()>=1){
				verifyAssetClasses(actualTableItemsData, assetClasses)
			}

			if(annualizedMinPer>=0 || annualizedMaxPer>0){
				verifyAnnualizedReturnsFilter(actualTableItemsData, annualizedMinPer, annualizedMaxPer, timePeriod)
			}

			if(morningStartRating>0 && morningStartRating<6){
				String firstFundName = actualTableItemsData.get(0).get('Fund').split('\\n')[0]
				verifyRatingDisplayedOnDetailsPage(firstFundName, morningStartRating)
			}

			if(manager!=null && !manager.isEmpty()){
				String firstFundName = actualTableItemsData.get(0).get('Fund').split('\\n')[0]
				verifyFundMgrDisplayedOnDetailsPage(firstFundName, manager)
			}

		}catch(Exception ex){
			KeywordUtil.markFailed("Something went wrong while filtering mutual funds\n"+ex.getMessage())
			flag = false
		}

		return flag;
	}


	/**
	 * this method will check for category names as per expected
	 * @param actualTableItemsData
	 * @param expectedCategory
	 * @return
	 */
	public static boolean verifyAssetClasses(Map<Integer, HashMap<String,String>> actualTableItemsData, List<String> expectedCategories){
		boolean flag = false;
		List<String> actualCategoryNames = new ArrayList<String>();
		Set<Integer> keys;
		keys = actualTableItemsData.keySet();
		for (key in keys) {
			//System.out.println((actualTableItemsData.get(key)).get("Category"));
			actualCategoryNames.add((actualTableItemsData.get(key)).get("Category"));
		}

		for (category in actualCategoryNames) {
			if(!expectedCategories.contains(category)){
				flag = false;
				KeywordUtil.markFailed("Some of the fund category names not matching, Expected -"+expectedCategories+"  Actual-"+category);
				break
			}else{
				flag = true;
			}
		}
		if(flag){
			KeywordUtil.markPassed("All the fund categories present on the funds page");
		}
		return flag
	}

	//This method will check annualized return filter results
	public static boolean verifyAnnualizedReturnsFilter(Map<Integer, HashMap<String,String>> actualTableItemsData, int annualizedMinPer,int annualizedMaxPer, String timePeriod){
		boolean flag = false;
		List<Double> actualListOfPercentage = new ArrayList<Double>()
		//ArrayList<Double> actualListOfPercentageSorted = new ArrayList<Double>()
		Set<Integer> keys;
		try{
			keys = actualTableItemsData.keySet();
			for (key in keys) {
				actualListOfPercentage.add(Double.parseDouble((actualTableItemsData.get(key)).get(timePeriod).replace("%", "")));
			}

			Collections.sort(actualListOfPercentage)
			//			System.out.println("actualListOfPercentage\n"+actualListOfPercentage)
			if(annualizedMinPer > 0 && annualizedMaxPer > 0){
				if(actualListOfPercentage[0] >= annualizedMinPer && actualListOfPercentage[actualListOfPercentage.size()-1] <= annualizedMaxPer){
					flag = true
				}else{ flag = false }
			}else if(annualizedMinPer >= 0){
				if(actualListOfPercentage[0] >= annualizedMinPer){
					flag = true
				}else{ flag = false }
			}else if(annualizedMaxPer > 0){
				if(actualListOfPercentage[actualListOfPercentage.size()-1] <= annualizedMaxPer){
					flag = true
				}else{ flag = false }
			}
			if(flag){
				KeywordUtil.markPassed("Funds filter worked for annualized filtering");
			}else{
				KeywordUtil.markFailed("Funds filter didnot worked for annualized filtering");
			}
		}catch(Exception ex){
			flag = false
			KeywordUtil.markFailed("Something went wrong in verifyAnnualizedReturnsFilter method\n"+ex.getMessage());
		}
		return flag
	}

	//This method will check rating displayed on the fund details page
	public static boolean verifyRatingDisplayedOnDetailsPage(String fundLinkText, int expectedRating){
		boolean flag = false;
		String overallRatingDisplayed = "//h4[text()='Morningstar Ratings']//following-sibling::div//tbody//th[contains(text(),'Overall')]//parent::tr//td[@class='text-right text-nowrap']//span"
		//Navigate to fund details page from Mutual funds table
		uik.navigateToPath(fundLinkText)

		WebUI.waitForPageLoad(5)
		TestObject dynamicTO = new TestObject("dynamicTO")
		dynamicTO.addProperty('xpath', ConditionType.EQUALS, overallRatingDisplayed, true)
		List<WebElement> ratingElements = WebUI.findWebElements(dynamicTO, 5);
		if(ratingElements.size() >= expectedRating){
			KeywordUtil.markPassed("Expected overall rating matching on fund details page");
		}else{
			KeywordUtil.markFailed("Expected overall rating-"+expectedRating+" NOT matching on the fund details page");
		}
		return flag
	}

	//This method will check fund manager name displayed on the fund details page
	public static boolean verifyFundMgrDisplayedOnDetailsPage(String fundLinkText, String expectedFundManager){
		boolean flag = false;

		uik.navigateToPath(fundLinkText)

		WebUI.waitForPageLoad(5)

		flag = WebUI.verifyTextPresent(expectedFundManager, false)
		if(flag){
			KeywordUtil.markPassed("Expected fund manager-"+expectedFundManager+" displayed on the fund details page");
		}else{
			KeywordUtil.markFailed("Expected fund manager-"+expectedFundManager+" NOT displayed on the fund details page");
		}
		return flag
	}

	//-------------------PutnamSpecificKeywords:2------------------------
	/**
	 * Verify Page Title
	 * Parameter: String
	 * return Type : Null
	 * * Verify the Page Title
	 */

	@Keyword
	public static void verifyPageTitle(String str)
	{
		String title;
		title = WebUI.getWindowTitle()
		if(!str.isEmpty()){
			if(title.trim().startsWith(str.trim())){
				KeywordUtil.markPassed(title+" of the page title is matched")}
			else{
				KeywordUtil.markFailed(title+" of the page title is not matched")
			}
		}else{
			KeywordUtil.logInfo("User as not passed expected PageName")
		}
	}


	/**
	 * Verify Page Name
	 * Parameter: String
	 * return Type : Null
	 * * Verify the Page Name
	 */


	@Keyword
	public static void verifyPageName(String PageName){
		WebElement ele;
		String pageName = "//*[contains(text(),'pagename')]";
		String linkXpath = pageName.replace("pagename", PageName);

		TestObject to= uik.convertXpathtoTestObject(linkXpath,"to")
		try{
			if(WebUI.verifyElementPresent(to, 10)){
				ele = WebUiCommonHelper.findWebElement(to,10)
				if(!PageName.isEmpty()){
					if(PageName.startsWith(ele.getText())){
						KeywordUtil.markPassed(PageName+" of the page Name is matched")}
					else{
						KeywordUtil.markFailed(PageName+" of the page Name is not matched")
					}
				}else{
					KeywordUtil.logInfo("User as not passed expected PageName")
				}
			}

		}catch(Exception ex){
			KeywordUtil.markFailed(':Exception occured due to reason:'+ex.getMessage())
		}
	}

	/**
	 * Verify Page
	 * Parameters : String PageName, String PageTitle
	 *  Return Type : Null
	 *  It will verify the Page Name and Title with given Input
	 */

	@Keyword
	public static void verifyPage(String PageName=null,String PageTitle=null)
	{
		try{
			verifyPageName(PageName)
			verifyPageTitle(PageTitle)


		}catch (Exception ex){
			KeywordUtil.markFailed(PageName+' Page not loaded sucessfully and Exception occured due to reason:'+ex.getMessage())

		}

	}

	/**
	 * Verify Element in container
	 * Parameters: TestObject to(of the container), String Element Name
	 * Return Type : Null
	 * It will verify the element is present in container
	 */

	@Keyword
	public static void verifyElementInContainer(TestObject to, String elementName){

		List<WebElement> List = new ArrayList();
		try{
			if(WebUI.verifyElementPresent(to, 10)){
				List.addAll(WebUiCommonHelper.findWebElements(to, 10));
				for (WebElement wb : List) {
					int i=1
					if(i<=List.size()){
						//print(wb.getText());
						if(elementName.equalsIgnoreCase(wb.getText().trim())){
							KeywordUtil.markPassed('Element Name: '+ wb.getText()+' available in the container ')
							i++;
							break;
						}}else{
						KeywordUtil.markFailed('Element Name: '+ wb.getText()+' not available in the container')
					}
				}
			}
		}catch(Exception e){
			KeywordUtil.markFailed(':Exception occured due to reason:'+e.getMessage())
		}
		finally{
			if(List.size()>0){
				KeywordUtil.markPassed("Total elements present in container on the page"+List.size().toString())
			}
		}

	}

	/**
	 * Verify link opening pdf or jsf page :verifyDocJsflink
	 * Parameters: TestObject to(of the single link), String newPageFormat =Either ".pdf" or ".jsf"
	 * Return Type : Null
	 * It will click on the link and verify new page is ".pdf" or ".jsf"
	 */
	@Keyword
	public static void verifyDocJsflink(TestObject to,String newPageFormat){
		WebElement elem;
		try{
			if(WebUI.verifyElementPresent(to, 10)){
				uik.refreshBrowser()
				elem = WebUiCommonHelper.findWebElement(to,10)
				String path = elem.getAttribute("href");
				String resp = uik.isLinkBroken(new URL(path))
				if(resp.equals('OK')){
					System.out.println("Link is working with response "+resp)
					WebUI.navigateToUrl(path)
					//waitForDocLoad();
					WebUI.waitForPageLoad(gv.mid_wait, FailureHandling.OPTIONAL);
					KeywordUtil.logInfo(elem.getText()+" element link is clicked and opened ")
				}else{
					KeywordUtil.logInfo(elem.getText()+" element link is  not working with response "+path)
				}
			}
			else{
				KeywordUtil.markFailed(to.getObjectId()+" element link is not presenet on the page")
			}
			uik.waitForDocLoad();
			String str = WebUI.getUrl()
			int len = str.length()
			//String substr = str.substring(len-3, len)
			//verifyPageTitle(winTitleName);
			if(str.contains(newPageFormat)){
				KeywordUtil.markPassed(" Element link is clicked and "+newPageFormat+" page is opened and verified")
			}
			else{
				KeywordUtil.markFailed(" Eement link is clicked and "+newPageFormat+" page is  NOT opened ")
			}
		}catch(Exception ex){
			KeywordUtil.markWarning(':Exception occured due to reason:'+ex.getMessage())
		}

	}



	/**
	 * Select given date from Calender : datePicker
	 * Parameters: TestObject calender, String Date
	 * Return Type : Null
	 * It will select the date from calender object
	 * date example : 02-01-1998 dd-yy-yyyy
	 */

	@Keyword
	//date example : 02-01-1998 dd-yy-yyyy
	public static void Datepicker(TestObject calender,String Date)throws Exception{
		String expDate = null, calYear = null,datepickerText=null,minYear=null,maxYear=null;
		int expMonth = 0, expYear = 0;
		WebElement datePicker,activeMonth,activeDay;
		List<WebElement> noOfDays=null,noOfMonths=null,noOfYears=null;
		boolean dateNotFound = true;
		List<String> monthList = Arrays.asList("None","Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul","Aug", "Sep", "Oct", "Nov", "Dec");
		//def driver = uik.getCurrentSessionDriver()
		WebElement SelectCalender = WebUiCommonHelper.findWebElement(calender, 20);
		SelectCalender.click()
		String exp_Date= Date.substring(0, 2)
		String exp_Month =Date.substring(3, 5)
		String exp_Year =Date.substring(6, 10)
		expDate = Integer.parseInt(exp_Date);
		expMonth = Integer.parseInt(exp_Month);
		expYear = Integer.parseInt(exp_Year);
		try{
			WebElement datePicker_Heading1 =(driver).findElement(By.xpath("//div[@class='datepicker-days']/table/thead/tr[1]/th[2]"));
			WebDriverWait wait = new WebDriverWait(driver,10);
			WebUI.waitForPageLoad(gv.small_wait, FailureHandling.OPTIONAL);
			datePicker_Heading1.click();
			WebElement datePicker_Heading2 =(driver).findElement(By.xpath("//div[@class='datepicker-months']/table/thead/tr[1]/th[2]"));
			//WebElement el =wait.until(ExpectedConditions.elementToBeClickable(datePicker_Heading2));
			WebUI.waitForPageLoad(gv.small_wait, FailureHandling.OPTIONAL);
			datePicker_Heading2.click();

			while (dateNotFound) {

				WebElement datePicker_Heading3 =(driver).findElement(By.xpath("//div[@class='datepicker-years']/table/thead/tr[1]/th[2]"));
				WebUI.waitForPageLoad(gv.small_wait, FailureHandling.OPTIONAL);
				datepickerText =datePicker_Heading3.getText();
				String[] datepickerYear = datepickerText.split("-");
				minYear =datepickerYear[0];
				maxYear = datepickerYear[1];
				// If current selected month and year are same as expected month
				// and year then go Inside this condition.
				if((expYear >= Integer.parseInt(minYear)) && (expYear<=Integer.parseInt(maxYear)))
				{
					datePicker = (driver).findElement(By.xpath("//div[@class='datepicker-years']/table"));
					noOfYears = datePicker.findElements(By.xpath("//span[contains(@class,'year')]"));
					firstloop:
					for (WebElement year : noOfYears) {
						// Select the date from date picker when condition match.
						if (year.getText().equalsIgnoreCase((String)exp_Year)) {
							year.click();
							Thread.sleep(2000);
							datePicker = (driver).findElement(By.xpath("//div[@class='datepicker-months']/table"));
							noOfMonths = (driver).findElements(By.xpath("//span[@class='month' or @class ='month active'] "));
							System.out.println(" the expected month in int  is : "+noOfMonths.size());
							Thread.sleep(2000);
							firstloop:	for (WebElement month : noOfMonths) {
								/*System.out.println(" the expected month in int  is : "+expMonth);
								 System.out.println(" the expected month is : "+monthList.get(expMonth));
								 System.out.println(" the Actual  month is : "+month.getText());
								 */
								if ((monthList.get(expMonth)).equalsIgnoreCase(month.getText())) {
									month.click();
									WebUI.waitForPageLoad(gv.small_wait, FailureHandling.OPTIONAL);
									datePicker = (driver).findElement(By.xpath("//div[@class='datepicker-days']/table"));
									noOfDays = (driver).findElements(By.xpath("//td[@class='day' or @class ='active day']"));
									WebUI.waitForPageLoad(gv.small_wait, FailureHandling.OPTIONAL);
									for (WebElement cell : noOfDays) {
										if (cell.getText().equalsIgnoreCase(expDate)) {
											cell.click();
											WebUI.waitForPageLoad(gv.small_wait, FailureHandling.OPTIONAL);
											break firstloop ;
										}
									}
								}
							}
						}

					}
					dateNotFound = false;
				}else if (expYear > Integer.parseInt(maxYear)) {
					WebElement Next =(driver).findElement(By.xpath("//div[@class='datepicker-years']/table/thead/tr[1]/th[@class='next']"));
					if(Next.getAttribute("style").equalsIgnoreCase("visibility: visible;"))
					{// Click on next button of date picker.
						Next.click();
					}else {
						//throw new Exception("This is exception")
						KeywordUtil.markWarning(':Exception occured ')
					}
				}
				// If current selected month and year are greater than expected
				// month and year then go Inside this condition.
				else if (expYear < Integer.parseInt(minYear)) {
					WebElement Previous =(driver).findElement(By.xpath("//div[@class='datepicker-years']/table/thead/tr[1]/th[@class='prev']"));
					if(Previous.getAttribute("style").equalsIgnoreCase("visibility: visible;"))
					{
						// Click on pre	vious button of date picker.
						Previous.click();
					}else{
						//throw new Exception("This is exception")
						KeywordUtil.markWarning(':Exception occured ')
					}
				}
			}
		}catch (Exception ex){
			KeywordUtil.markWarning(':Exception occured due to reason:'+ex.getMessage())
		}
	}


	/**
	 * Close form window : closeFormWindow
	 * Parameters: Null
	 * Return Type : Null
	 * It will close the Form window
	 */

	@Keyword
	//TODO
	public static void closeFormWindow(){
		try{
			TestObject to= uik.convertXpathtoTestObject("//button[@type ='button' and text() ='Ã—']","Button")
			if(WebUI.verifyElementPresent(to, 10)){
				WebUiCommonHelper.findWebElement(to,10).click()
			}
		}catch(Exception ex){
			KeywordUtil.markFailed(':Exception occured due to reason:'+ex.getMessage())
		}
	}



	/**
	 * Wait Foe Element : waitForJsfPageLoad
	 * Parameters: TestObject(Optional),Xpath(Optionsl), String waitType, int MaxTimeout
	 * Return Type : Null
	 * It will wait for element to load in given max time out, If It does not find the element in given timeout it will throw wxception
	 */
	@Keyword
	public static void waitForJsfPageLoad(TestObject to = null, String xpath= null, String type, int maxTimeout){
		boolean flag = true;
		boolean check= (xpath!=null && to == null )
		if(check == true ) {
			to = uik.convertXpathtoTestObject(xpath)
		}
		try{
			switch (type) {
				case "visible" :
					flag = WebUI.waitForElementVisible(to, maxTimeout, FailureHandling.STOP_ON_FAILURE)
					break;

				case "present":
					flag =WebUI.waitForElementPresent(to, maxTimeout, FailureHandling.STOP_ON_FAILURE)
					break;

				default:
					flag = WebUI.waitForPageLoad(maxTimeout)
					break;
			}
			if(flag ==false){
				throw new Exception("Element is not "+type+" in given timeout "+maxTimeout)
			}

		}catch(Exception ex){
			KeywordUtil.markFailed("Elemenet is not present on the page  and exception occured due to :: "+ex.getMessage())
		}
	}



	/**
	 * Switch to Window : switchToWindow
	 * Parameters: TestObjectto, int MaxTimeout
	 * Return Type : Null
	 * It will click on test object and witch to window
	 */
	@Keyword

	public static void switchToWindow(TestObject to,int maxTimeout){
		String mainHandle =driver.getWindowHandle();
		WebUI.click(to)
		Set<String> allHandles= driver.getWindowHandles();
		int i =1;
		firstloop:while(i< maxTimeout){
			if(allHandles.size()>1){
				for(String handle : allHandles ){
					if(handle.equalsIgnoreCase(mainHandle)== false){
						driver.switchTo().window(handle)
						break firstloop;
					}
				}
			}else{
				Thread.sleep(1000)}

			if (i == maxTimeout){
				KeywordUtil.markFailed("Window is not switcted to new window on maximum timeout "+maxTimeout )
			}
			i++;
		}
	}

	/**
	 * Switch to validateDailyPricing :validateDailyPricing(TestObject to)
	 * Parameters: TestObject to
	 * Return Type : Null
	 * It will validate the daily price data present on home pgae with client information page and daily statement page
	 */
	@Keyword
	//TODO: Break the method to multiple methods.
	public static void validateDailyPricing(TestObject to){
		String date = null, name = null, amount = null, amtXpath = null, assetTblXpath = null, nameXpath= null, dateXpath = null,tableXpath = null,dailyStatmentXpath = null;
		String assetName=null,assetValue= null,str= null;
		WebElement elAmt= null,elName=null,elDate=null,webTable = null,ele = null,ele1=null,ele2=null,wb = null, elPageTitle = null;
		Map<String,String> tableData= new HashMap<String,String>();
		TestObject elIframe= null,pageelement = null,compStatement=null,home=null;
		//def driver = uik.getCurrentSessionDriver()
		try{
			if(WebUI.verifyElementPresent(to, 10)){
				try{
					int i = 1;
					List<WebElement> list = new ArrayList();
					list.addAll(WebUiCommonHelper.findWebElements(to, 10));
					print("list size is "+list.size())
					//firstloop :for(WebElement wb : list){
					firstloop :for(int n = 0; n <list.size();n++){
						if(n>=1){
							list.clear()
							list.addAll(WebUiCommonHelper.findWebElements(to, 10));

						}
						wb = list.get(n)
						List<WebElement> listSpan = new ArrayList();
						nameXpath = "("+to.findPropertyValue('xpath')+"//p/span[1])["+i+"]"
						dateXpath = "("+to.findPropertyValue('xpath')+"//p/span[2])["+i+"]"
						amtXpath ="("+to.findPropertyValue('xpath')+"//p[2])["+i+"]"
						tableXpath = "("+to.findPropertyValue('xpath')+"//table)["+i+"]"
						elAmt = wb.findElement(By.xpath(amtXpath))
						elName = wb.findElement(By.xpath(nameXpath))
						elDate =  wb .findElement(By.xpath(dateXpath))
						// Capturing the data on Login home page
						name = elName.getText();
						date = elDate.getText();
						amount  = elAmt.getText()
						System.out.println(amount)
						System.out.println(name)
						System.out.println(date)
						//webTable = wb.findElement(By.xpath(tableXpath))
						tableData.clear();
						if(!(amount.equalsIgnoreCase('$0.00'))){
							List<WebElement> rows=driver.findElements(By.xpath(tableXpath+"//tbody/tr"));
							System.out.println(rows.size())
							for(int row =1; row<=rows.size();row++){
								ele1 = driver.findElement(By.xpath(tableXpath+"//tbody/tr["+row+"]/th"));
								assetName = ele1.getText();
								ele2 = driver.findElement(By.xpath(tableXpath+"//tbody/tr["+row+"]/td"));
								assetValue = ele2.getText()
								System.out.println(assetName)
								System.out.println(assetValue)
								tableData.put(assetName, assetValue)
							}
						}

						System.out.println(tableData.size())
						elName.click();  //Clicked on element on Home page
						uik.waitForDocLoad()
						elIframe = uik.convertXpathtoTestObject("//iframe[@id = 'content-frame']","elIframe")
						//Navifate to 2nd Page
						waitForJsfPageLoad(elIframe,null,"present", 60)
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id = 'content-frame']")))
						uik.waitForDocLoad()
						pageelement = uik.convertXpathtoTestObject("//td[@class = 'pagetitle']","pageelement")
						waitForJsfPageLoad(pageelement,null,"visible", 60)
						Thread.sleep(2000)
						elPageTitle = driver.findElement(By.xpath("//td[@class = 'pagetitle']"))
						//Verifying 2nd Page details
						if (elPageTitle.getText().toUpperCase().startsWith("Daily Statements".toUpperCase())){
							System.out.println(elPageTitle.getText()+ " Page title is matched and navigated to 2nd page ")
							KeywordUtil.markPassed(elPageTitle.getText()+ " Page title is matched and navigated to 2nd page ")
						}
						else{
							KeywordUtil.markFailed(elPageTitle.getText()+ " Page title  of 2nd page is not matched ")
						}

						elName= driver.findElement(By.xpath("(//td[@class='charttext']/b)[1]"))
						str = elName.getText().replace("\n", "").replace(" ","").toUpperCase()
						name= name.replace(" ","").toUpperCase()
						if (str.startsWith(name)){
							System.out.println(elName.getText()+ " Name is matched")
							KeywordUtil.markPassed(elName.getText()+ " Name is matched")
						}
						else{
							KeywordUtil.markFailed(elName.getText()+ " Name is not matched")
						}

						WebElement elRepresnt = driver.findElement(By.xpath("(//td[@class='charttext']/b)[2]"))
						if (elRepresnt.getText()!=null){
							System.out.println(elRepresnt.getText()+ " Repersentative detail")
							KeywordUtil.markPassed(elRepresnt.getText()+ " Repersentative detail")
						}else{
							KeywordUtil.markFailed(elRepresnt.getText()+ " Repersentative detail is not availble")
						}

						elAmt= driver.findElement(By.xpath("(//td[@class='charttext']/b)[4]"))
						if (elAmt.getText().equalsIgnoreCase(amount)){
							System.out.println(elAmt.getText()+ " Amount is matched")
							KeywordUtil.markPassed(elAmt.getText()+ " Amount is matched")
						}else{
							KeywordUtil.markFailed(elAmt.getText()+ " Amount is not matched")
						}

						elDate= driver.findElement(By.xpath("//td[@class='charttext' and contains(text(),'"+ date +"')]"))
						if(elDate.getText().contains(date)){
							System.out.println(elDate.getText()+ " Date is matched")
							KeywordUtil.markPassed(elDate.getText()+ " Date is matched")
						}else{
							KeywordUtil.markFailed(elDate.getText()+ " Date is not matched")
						}
						uik.waitForDocLoad()
						dailyStatmentXpath = "//*[@id='handleCompleteDailyStmt']/img/parent::a";
						compStatement = uik.convertXpathtoTestObject(dailyStatmentXpath,"compStatement")
						//Thread.sleep(3000);
						//uik.jsClicker(compStatement) //clicking on Complete statement button

						//uik.waitForDocLoad()
						//Thread.sleep(45000);
						//switchWindow()
						//WebUI.switchToWindowUrl("https://retacct.putnam.com/accountscore/application/dailystatements/accountbalance.jsf")
						uik.waitForDocLoad()
						switchToWindow(compStatement,60)
						TestObject pageelemenDaily = uik.convertXpathtoTestObject("//td[@class = 'pagetitle']","pageelemenDaily")
						waitForJsfPageLoad(pageelemenDaily,null,"visible", 60)
						elPageTitle = driver.findElement(By.xpath("//td[@class = 'pagetitle']"))
						if (elPageTitle.getText().toUpperCase().startsWith("Daily Statements".toUpperCase())){
							System.out.println(elPageTitle.getText()+ " Page title is matched and navigated to daily statment page ")
							KeywordUtil.markPassed(elPageTitle.getText()+ " Page title is matched and navigated to daily statment page")
						}
						else{
							KeywordUtil.markFailed(elPageTitle.getText()+ " Page title is not matched on daily statment page")
						}

						elName= driver.findElement(By.xpath("(//td[@class='charttext']/b)[1]"))
						str = elName.getText().replace("\n", "").replace(" ","").toUpperCase()
						name= name.replace(" ","").toUpperCase()
						if (str.startsWith(name)){
							System.out.println(elName.getText()+ " Name is matched on daily statment page")
							KeywordUtil.markPassed(elName.getText()+ " Name is matched on daily statment page")
						}
						else{
							KeywordUtil.markFailed(elName.getText()+ " Name is not matched on daily statment page")
						}

						elRepresnt = driver.findElement(By.xpath("(//td[@class='charttext']/b)[2]"))
						if (elRepresnt.getText()!=null){
							System.out.println(elRepresnt.getText()+ " Repersentative detail on daily statment page")
							KeywordUtil.markPassed(elRepresnt.getText()+ " Repersentative detail on daily statment page")
						}else{
							KeywordUtil.markFailed(elRepresnt.getText()+ " Repersentative detail is not availble on daily statment page")
						}

						elAmt= driver.findElement(By.xpath("(//td[@class='charttext']/b)[4]"))
						if (elAmt.getText().equalsIgnoreCase(amount)){
							System.out.println(elAmt.getText()+ " Amount is matched on daily statment page")
							KeywordUtil.markPassed(elAmt.getText()+ " Amount is matched on daily statment page")
						}else{
							KeywordUtil.markFailed(elAmt.getText()+ " Amount is not matched on daily statment page")
						}

						elDate= driver.findElement(By.xpath("//td[@class='charttext' and contains(text(),'"+ date +"')]"))
						if(elDate.getText().contains(date)){
							System.out.println(elDate.getText()+ " Date is matched on daily statment page")
							KeywordUtil.markPassed(elDate.getText()+ " Date is matched on daily statment page")
						}else{
							KeywordUtil.markFailed(elDate.getText()+ " Date is not matched on daily statment page")
						}

						for (Map.Entry<String,String> entry : tableData.entrySet()){
							String var = entry.getKey().toUpperCase()
							if(!(amount.equalsIgnoreCase('$0.00'))){
								ele = driver.findElement(By.xpath("//td[@class='charttext' and starts-with(text(), '"+var+"')]"))
								if (ele.getText().equalsIgnoreCase(var)){
									ele = driver.findElement(By.xpath("(//td[@class='charttext' and starts-with(text(), '"+var+"')]/following::td)[1]"))
									if(ele.getText().equalsIgnoreCase(entry.getValue())){
										System.out.println(ele.getText()+ var +" Data is matched in table on Daily statment page")
										KeywordUtil.markPassed(ele.getText()+ var +" Data is matched on daily statment page")
									}
									else{
										KeywordUtil.markFailed(ele.getText()+ var +" Data is not matched on daily statment page")
									}
								}

							}
						}

						WebUI.closeWindowUrl("https://retacct.putnam.com/accountscore/application/dailystatements/accountbalance.jsf");

						WebUI.switchToWindowUrl("https://www.putnam.com/individual/my-accounts/")

						driver.switchTo().defaultContent()
						Thread.sleep(1000);
						dailyStatmentXpath ="//*[@class='nav-link responsive-copy' and @data-responsive-copy-xs ='Home']"
						home = uik.convertXpathtoTestObject(dailyStatmentXpath,"home")
						uik.jsClicker(home)
						uik.waitForDocLoad()
						Thread.sleep(2000)
						KeywordUtil.markPassed("Again back to home")
						waitForJsfPageLoad(findTestObject('IndividualInvestor/PageSpecificObjects/DaikyStatementPage/elShowAccounts'),null,"visible",10)
						Thread.sleep(2000)
						WebUI.mouseOver(findTestObject('IndividualInvestor/PageSpecificObjects/DaikyStatementPage/elShowAccounts'))
						Thread.sleep(1000)
						WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/DaikyStatementPage/elShowAccounts'))
						uik.waitForDocLoad()
						i=i+1;

					}
				}catch(Exception e){
					print (e.getMessage())}
			}
		} catch(Exception ex){
			print (ex.getMessage())}


	}


	/**
	 * get daily price data :validateDailyPricing(TestObject to)
	 * Parameters: TestObject to
	 * Return Type : Null
	 * It will validate the daily price data present on home pgae with client information page and daily statement page
	 */

	public static Multimap<String, String> getDailyPricePageData(TestObject to,String PageName){
		Multimap<String, String> multiMap = ArrayListMultimap.create();
		List<WebElement> rows = new ArrayList();
		List<WebElement> columns = new ArrayList();
		WebElement row= null,column=null,cell=null,cell_second=null,cell_third=null;
		String str= null;
		String tableXpath =to.findPropertyValue('xpath');
		try{
			if(WebUI.verifyElementPresent(to, 10)){
				try{
					//sint i = 1;
					//rows = new ArrayList();
					rows.addAll(driver.findElements(By.xpath(tableXpath+"/tbody/tr")));
					System.out.println("rows size is "+rows.size())
					KeywordUtil.logInfo("rows size is "+rows.size()+" of "+PageName)
					firstloop: for( int i =1;i<=rows.size();i++){
						//row =rows.get(i);
						columns.clear();
						columns.addAll(driver.findElements(By.xpath(tableXpath+"/tbody/tr["+i+"]/td")))
						secondloop: for(int j = 1;j<=columns.size();j++){
							//column= columns.get(j);
							switch (PageName){
								case "dailystatement_1":
									if(j==1){
										switch (i){
											case 1:
												cell = driver.findElement(By.xpath(tableXpath+"/tbody/tr["+i+"]/td["+j+"]//tr/td["+j+"]/a"))
												cell_second = driver.findElement(By.xpath(tableXpath+"/tbody/tr["+i+"]/td["+j+"]//tr/td["+j+"]/span"))
												cell_third = driver.findElement(By.xpath(tableXpath+"/tbody/tr["+i+"]/td["+(j)+"]//tr/td["+(j+2)+"]"))
												j = j+2;
												str = cell.getText()+" "+cell_second.getText()+" "+cell_third.getText()
												break;
											case 2:
												cell = driver.findElement(By.xpath(tableXpath+"/tbody/tr["+i+"]/td["+j+"]"))
												str= cell.getText()
												break;
											case rows.size():
												cell = driver.findElement(By.xpath(tableXpath+"/tbody/tr["+i+"]/td["+j+"]"))
												str= cell.getText()
												break;
											default:
												cell = driver.findElement(By.xpath(tableXpath+"/tbody/tr["+i+"]/td["+j+"]//tr/td[2]/div/b"))
												cell_second = driver.findElement(By.xpath(tableXpath+"/tbody/tr["+i+"]/td["+j+"]//tr/td[2]/div/span"))
												str= cell.getText().trim()+" "+cell_second.getText().trim()
												break;

										}

									}else{
										if((i ==2) || (i==rows.size())){
											cell = driver.findElement(By.xpath(tableXpath+"/tbody/tr["+i+"]/td["+j+"]"))
											str= cell.getText()
										}else{
											cell = driver.findElement(By.xpath(tableXpath+"/tbody/tr["+i+"]/td["+j+"]/div"))
											str= cell.getText()
										}
									}
									break;
								case "dailystatement_2":
									if(j==1){
										if(i==1){

											cell = driver.findElement(By.xpath(tableXpath+"/tbody//tr["+i+"]/td//tr/td["+j+"]"))
											cell_second = driver.findElement(By.xpath(tableXpath+"/tbody//tr["+i+"]/td["+j+"]/span"))
											cell_third = driver.findElement(By.xpath(tableXpath+"/tbody//tr["+i+"]/td//tr/td["+(j+2)+"]"))
											j = j=3;
											str = cell.getText()+" "+cell_second.getText()+" "+cell_third.getText()

										}else{
											cell = driver.findElement(By.xpath(tableXpath+"/tbody//tr["+i+"]/td["+j+"]"))
											//cell_second = driver.findElement(By.xpath(tableXpath+"/tbody/tr["+i+"]/td["+j+"]/span"))
											//str= cell.getText().trim()+" "+cell_second.getText().trim()
										}
									}else{
										cell =  driver.findElement(By.xpath(tableXpath+"/tbody//tr["+i+"]/td["+j+"]"))
										//str= cell.getText()
									}
									str= cell.getText()
									break;
							}
							if((str.indexOf("\n"))!=-1){
								str = str.replace("\n", " ")
							}
							multiMap.put("row"+i,str)
						}
					}

				}catch(Exception ex){
					KeywordUtil.markFailed("Exception occured due to :: "+ex.getMessage())
				}
			}
		}catch(Exception ex){
			KeywordUtil.markFailed("Exception occured due to unable to find test object :: "+ex.getMessage())
		}
		System.out.println(multiMap.size())
		return multiMap;
	}


	@Keyword
	public static void verifyDailyPricePageTable(TestObject to,String PageName1,String PageName2){
		TestObject elIframe= null,pageelement =null,performanceTab = null,rateOfReturnTbl =null,compStatement=null,rateOfReturnTbl_final=null;
		TestObject accountBalance_final=null,allocation_final=null;
		WebElement elPageTitle = null;
		Map<String, String> mapAccountBal  = new HashMap();
		Map<String, String> mapAccountSummary  = new HashMap();
		Map<String, String> mapAlloation = new HashMap();

		Map<String, String> mapAccountBal_Final  = new HashMap();
		Map<String, String> mapAccountSummary_Final = new HashMap();
		Map<String, String> mapAlloation_Final = new HashMap();

		Multimap<String, String> table1 = ArrayListMultimap.create();
		Multimap<String, String> table2 = ArrayListMultimap.create();
		String dailyStatmentXpath= null;

		try{
			if(WebUI.verifyElementPresent(to, 10)){
				try{
					uik.jsClicker(to);
					uik.waitForDocLoad()
					elIframe = uik.convertXpathtoTestObject("//iframe[@id = 'content-frame']","elIframe")
					//Navifate to 2nd Page
					waitForJsfPageLoad(elIframe,null,"present", 60)
					driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id = 'content-frame']")))
					uik.waitForDocLoad()
					pageelement = uik.convertXpathtoTestObject("//td[@class = 'pagetitle']","pageelement")
					waitForJsfPageLoad(pageelement,null,"visible", 60)
					Thread.sleep(2000)
					elPageTitle = driver.findElement(By.xpath("//td[@class = 'pagetitle']"))
					//Verifying 2nd Page details
					if (elPageTitle.getText().toUpperCase().startsWith("Daily Statements".toUpperCase())){
						System.out.println(elPageTitle.getText()+ " Page title is matched and navigated to 2nd page ")
						KeywordUtil.markPassed(elPageTitle.getText()+ " Page title is matched and navigated to 2nd page ")
					}
					else{
						KeywordUtil.markFailed(elPageTitle.getText()+ " Page title  of 2nd page is not matched ")
					}

					mapAccountSummary = getDailyPriceTabData(null,"Account Summary")
					System.out.println(mapAccountSummary.size())
					KeywordUtil.logInfo("Account Summary tab is clicked and data is captured")

					mapAccountBal = getDailyPriceTabData(null,"Account Balances")
					System.out.println(mapAccountBal.size())
					KeywordUtil.logInfo("Account Balances tab is clicked and data is captured")

					mapAlloation = getDailyPriceTabData(null,"Allocation")
					System.out.println(mapAlloation.size())
					KeywordUtil.logInfo("Allocation tab is clicked and data is captured")

					clickDailyPriceTab(null,"Transactions")
					uik.waitForDocLoad()
					TestObject transactionTab = uik.convertXpathtoTestObject("(//*[@id='_idJsp0']//table)[19]/tbody//tr[4]/td[3]//b/a","transactionTab")
					waitForJsfPageLoad(transactionTab,null,"visible", 60)
					Thread.sleep(3000)
					String str =driver.findElement(By.xpath("(//*[@id='_idJsp0']//table)[19]/tbody//tr[4]/td[3]//b/a")).getAttribute("href")
					String resp = uik.isLinkBroken(new URL(str))
					if(resp.equals('OK')){
						KeywordUtil.markPassed('element rendered on UI for url:'+str+'___'+resp)
					}else{
						KeywordUtil.markWarning(str+ ': element rendering failed due to reason:'+resp)

					}
					KeywordUtil.logInfo("Transactions tab is clicked and random Link is verified")


					performanceTab = uik.convertXpathtoTestObject("//*[@id='_idJsp0:NavToPerformance' and text()='Performance']","performanceTab")
					waitForJsfPageLoad(performanceTab,null,"visible", 60);
					WebUI.mouseOver(performanceTab)
					WebUI.click(performanceTab)
					rateOfReturnTbl =  uik.convertXpathtoTestObject("((//*[@id='_idJsp0']//table)[16]//table)[1]","rateOfReturnTbl")
					waitForJsfPageLoad(rateOfReturnTbl,null,"visible", 60);
					Thread.sleep(3000)
					table1 = getDailyPricePageData(rateOfReturnTbl,PageName1)
					System.out.println(table1)

					KeywordUtil.logInfo("Performance tab is clicked and random Link is verified")

					dailyStatmentXpath = "//*[@id='handleCompleteDailyStmt']/img/parent::a";
					compStatement = uik.convertXpathtoTestObject(dailyStatmentXpath,"compStatement")
					WebUI.mouseOver(compStatement)
					//WebUI.click(compStatement)
					//uik.jsClicker(compStatement) //clicking on Complete statement button
					//uik.waitForDocLoad()
					switchToWindow(compStatement,60)
					//Thread.sleep(60000);
					//switchWindow()
					//WebUI.switchToWindowUrl("https://retacct.putnam.com/accountscore/application/dailystatements/performance.jsf")
					uik.waitForDocLoad()
					elPageTitle = driver.findElement(By.xpath("//td[@class = 'pagetitle']"))
					TestObject pageelemenDaily = uik.convertXpathtoTestObject("//td[@class = 'pagetitle']","pageelemenDaily")
					waitForJsfPageLoad(pageelemenDaily,null,"visible", 60)

					if (elPageTitle.getText().toUpperCase().startsWith("Daily Statements".toUpperCase())){
						System.out.println(elPageTitle.getText()+ " Page title is matched and navigated to daily statment page ")
						KeywordUtil.markPassed(elPageTitle.getText()+ " Page title is matched and navigated to daily statment page")
					}
					else{
						KeywordUtil.markFailed(elPageTitle.getText()+ " Page title is not matched on daily statment page")
					}

					rateOfReturnTbl_final =  uik.convertXpathtoTestObject("(//*[@id='_idJsp0']/table[8]//table)[1]","rateOfReturnTbl_final")
					waitForJsfPageLoad(rateOfReturnTbl_final,null,"present", 60);
					table2 = getDailyPricePageData(rateOfReturnTbl_final,PageName2)

					KeywordUtil.logInfo("capture Account Balance data")
					accountBalance_final = uik.convertXpathtoTestObject("//*[@id='_idJsp0']/table[7]","accountBalance_final")
					mapAccountBal_Final=getCompStatementDailyPriceTblData( accountBalance_final, "Account Balances")
					System.out.println(mapAccountSummary_Final.size())

					KeywordUtil.logInfo("comparing account balance data")
					compareMaps(mapAccountBal,mapAccountBal_Final)


					KeywordUtil.logInfo("capture Allocation tale data")
					allocation_final = uik.convertXpathtoTestObject("//*[@id='pie-chart-1']/table/tbody","allocation_final")
					mapAlloation_Final =getCompStatementDailyPriceTblData( allocation_final, "Allocation")

					KeywordUtil.logInfo("comparing allocation table data")
					compareMaps(mapAlloation,mapAlloation_Final)



					KeywordUtil.logInfo("comparison of performancae tabe on page "+PageName1+" and "+ PageName2)
					compareMultiMap(table1,table2);

					WebUI.closeWindowUrl("https://retacct.putnam.com/accountscore/application/dailystatements/performance.jsf");

					WebUI.switchToWindowUrl("https://www.putnam.com/individual/my-accounts/")

					driver.switchTo().defaultContent()
					Thread.sleep(1000);
				}catch(Exception ex){
					KeywordUtil.markFailed("Exception occured due to :: "+ex.getMessage())
				}
			}
		}catch(Exception ex){
			KeywordUtil.markFailed("Exception occured due to unable to find test object :: "+ex.getMessage())
		}
	}



	public static void compareMultiMap(Multimap<String,String> multiMap1,Multimap<String,String> multiMap2){
		List<String> list1 = new ArrayList();
		List<String> list2 = new ArrayList();
		for(Map.Entry<String, Collection<String>> entry : multiMap1.asMap().entrySet() ){
			String key = entry.getKey();
			if(multiMap2.containsKey(key)){
				if(entry.getValue().equals(multiMap2.get(key))) {
					System.out.println("all the values for " + key + " are matched");
					KeywordUtil.markPassed("all the values for " + key + " are matched")
				}else{
					list1.clear();
					list2.clear();
					list1.addAll(entry.getValue());
					list2.addAll(multiMap2.get(key));
					for(int i =0;i<list1.size();i++){
						if( !(list1.get(i).trim().equalsIgnoreCase(list2.get(i).trim()))){
							System.out.println("value is not matched of "+ key+" column "+(i+1));
							KeywordUtil.markFailed("value is not matched of "+ key+" column "+(i+1))

						}
					}
				}
			}
		}
	}




	@Keyword
	public static void compareMaps(Map<String, String> map1,Map<String, String> map2){
		if(map1.size()==map2.size()){
			KeywordUtil.logInfo(" size of both table is matched")
			for(Map.Entry<String, String> entry :map1.entrySet() ){
				String key = entry.getKey();
				if(map2.containsKey(key)){
					//if(map2.get(key).trim().contains(entry.getValue().trim())){
					if((entry.getValue()).trim().contains(map2.get(key).trim())) {
						System.out.println(" values for " + key + " is matched");
						KeywordUtil.markPassed(" values for " + key + " is matched");
					}else{
						System.out.println("value "+entry.getValue() +" is not matched of "+ key);
						KeywordUtil.markFailed("value "+entry.getValue() +" is not matched of "+ key)

					}
				}
			}
		}else{
			KeywordUtil.markFailed("Column size of DailyStatment tab page "+map1.size() +" is not matched with "+map2.size())}
	}





	public static Map<String,String> getDailyPriceTabData(TestObject to= null, String tabName){
		Map<String,String> map = new HashMap<String,String>();
		TestObject table=null,allocationtable;
		List<WebElement> list = new ArrayList();
		List<WebElement> rows = new ArrayList();
		List<WebElement> columns = new ArrayList();
		WebElement ele= null,ele1= null, ele2= null;
		String str= null, resp= null,str1=null;
		if(to ==null){
			to = uik.convertXpathtoTestObject("//*[text()='"+tabName+"']","to")
		}
		try{
			if(WebUI.verifyElementPresent(to, 10)){
				try{
					switch(tabName){
						case "Allocation":
							WebUI.mouseOver(to)
							WebUI.click(to)
						//uik.jsClicker(to);
							uik.waitForDocLoad()
							table = uik.convertXpathtoTestObject("//*[@id='pie-chart-1']/table/tbody","table")
							waitForJsfPageLoad(table,null,"visible", 60)
							Thread.sleep(3000)
							allocationtable = uik.convertXpathtoTestObject("//*[@id='pie-chart-1']/table/tbody",allocationtable)
							map =getDailyPriceAllocationTblData(allocationtable,"firstPage")
							break;

						default :
						//uik.jsClicker(to);
							WebUI.mouseOver(to)
							WebUI.click(to)
							uik.waitForDocLoad()
							if(tabName.equalsIgnoreCase("Account Summary")){
								TestObject yearToDate = uik.convertXpathtoTestObject("//*[@id='_idJsp0:NavToAccountSummaryYTD']","yearToDate")
								waitForJsfPageLoad(yearToDate,null,"visible", 60)
								WebUI.mouseOver(yearToDate)
								WebUI.click(yearToDate)
							}

							table = uik.convertXpathtoTestObject("(//*[@id='_idJsp0']/table//table)[16]/tbody//tr[2]//tr/td[2]","table")
							waitForJsfPageLoad(table,null,"visible", 60)
							Thread.sleep(3000)
							ele = driver.findElement(By.xpath("(//*[@id='_idJsp0']/table//table)[16]/tbody//tr[2]//tr/td[2]"))
							str =driver.findElement(By.xpath("(//*[@id='_idJsp0']/table//table)[16]/tbody//tr[2]//tr/td[2]//a")).getAttribute("href")
							resp = uik.isLinkBroken(new URL(str))
							if(resp.equals('OK')){
								KeywordUtil.markPassed('element rendered on UI for url:'+str+'___'+resp)
							}else{
								KeywordUtil.markWarning(str+ ': element rendering failed due to reason:'+resp)

							}
						//str =driver.findElement(By.xpath("(//*[@id='_idJsp0']/table//table)[16]/tbody//tr[2]//tr/td[2]/b/a")).getText()
							str1 =driver.findElement(By.xpath("(//*[@id='_idJsp0']/table//table)[16]/tbody//tr[2]//tr/td[2]")).getText()
						//str = str.trim()+" "+str1.trim()
							if(str1.indexOf("\n")==-1){
								str1 = str1.replace("\n"," ")
							}
							map.put("Fund Name",str1)
						//map("Fund name/Account number",str.trim()+" "+str1.trim())
							list.addAll(driver.findElements(By.xpath("(//*[@id='_idJsp0']/table//table)[16]/tbody//tr[3]/td")))
							for(int i =1; i<=list.size();i++){
								ele1= driver.findElement(By.xpath("(//*[@id='_idJsp0']/table//table)[16]/tbody//tr[3]/td["+i+"]/b"))
								ele2= driver.findElement(By.xpath("(//*[@id='_idJsp0']/table//table)[16]/tbody//tr[3]/td["+i+"]"))
								str = ele1.getText()
								str1 =ele2.getText()
								if(str.indexOf("\n")!=-1){
									str = str.replace("\n"," ")
								}
								if(str1.indexOf("\n")==-1){
									str1 = str1.replace("\n"," ")
								}
								map.put(str,str1)
							}
							break;
					}
					System.out.println(map.size())
					return map;
				}catch(Exception ex){}
			}
		}catch(Exception ex){
		}

	}


	@Keyword
	public static void clickDailyPriceTab(TestObject to=null, String tabName){
		if(to ==null){
			to = uik.convertXpathtoTestObject("//*[text()='"+tabName+"']","to")
		}
		try{
			if(WebUI.verifyElementPresent(to, 10)){
				try{
					WebUI.mouseOver(to)
					WebUI.click(to)
				}catch(Exception ex){
					KeywordUtil.markFailed("Exception occured due to  :: "+ex.getMessage())
				}
			}
		}catch(Exception ex){
			KeywordUtil.markFailed("Exception occured due to unable to find test object :: "+ex.getMessage())
		}
	}


	public static  Map<String, String> getDailyPriceAllocationTblData(TestObject to,String PageName){

		Map<String, String> map = new HashMap();
		List<WebElement> rows = new ArrayList();
		List<WebElement> columns = new ArrayList();
		WebElement ele = null,ele1=null,ele2=null;
		String tablexpath = null,str= null,resp= null,str1=null;
		try{
			if(WebUI.verifyElementPresent(to, 10)){
				try{
					tablexpath = to.findPropertyValue('xpath')
					rows.addAll(driver.findElements(By.xpath(tablexpath+"/tr")))
					fisrtloop :for(int row =1;row<=rows.size();row++){
						columns.clear()
						columns.addAll(driver.findElements(By.xpath(tablexpath+"/tr["+row+"]/td")))
						secondloop :for(int column = 1; column<columns.size();column++){
							if(PageName.equalsIgnoreCase("finalPage")== true){
								ele1 = driver.findElement(By.xpath(tablexpath+"/tr["+row+"]/td["+column+"]//tr/td"))
								str = ele1.getText().trim()
							}else{
								if(row == rows.size()){
									ele1 = driver.findElement(By.xpath(tablexpath+"/tr["+row+"]/td["+column+"]//tr/td"))
									str = ele1.getText().trim()
								}else{
									ele1 = driver.findElement(By.xpath(tablexpath+"/tr["+row+"]/td["+column+"]//tr/td/a"))
									str = ele1.getText().trim()
								}
							}
							ele2 = driver.findElement(By.xpath(tablexpath+"/tr["+row+"]/td["+(column+1)+"]"))
							str1= ele2.getText().trim()
							column = column+1
							if(ele1.getText().indexOf("\n")!=-1){
								str = ele1.getText().replace("\n"," ")
							}
							if(ele2.getText().indexOf("\n")!=-1){
								str1 = ele2.getText().replace("\n"," ")
							}
							map.put(str,str1)
						}

					}
					System.out.println(map.size())
					return map;
				}catch(Exception ex){
					KeywordUtil.markFailed("Exception occured due to  :: "+ex.getMessage())
				}
			}
		}catch(Exception ex){
			KeywordUtil.markFailed("Exception occured due to unable to find test object :: "+ex.getMessage())
		}
	}


	public static Map<String, String> getCompStatementDailyPriceTblData(TestObject to, String tableName){
		Map<String,String> map = new HashMap();
		List<String> list = new ArrayList();
		WebElement ele= null,ele1= null, ele2= null;
		String str= null,str1=null,tablexpath = null;
		try{
			if(WebUI.verifyElementPresent(to, 10)){
				try{
					switch(tableName){
						case "Account Balances":
							tablexpath = to.findPropertyValue('xpath')
							list.addAll(driver.findElements(By.xpath(tablexpath+"//table/tbody/tr[2]/td")))
							for(int column=1; column<=list.size(); column++){
								ele1= driver.findElement(By.xpath(tablexpath+"//table/tbody/tr[2]/td["+column+"]"))
								ele2= driver.findElement(By.xpath(tablexpath+"//table/tbody/tr[4]/td["+column+"]"))
								str = ele1.getText().trim()
								str1 = ele2.getText().trim()
								if(str.indexOf("\n")!=-1){
									str = str.replace("\n"," ")
								}
								if(str1.indexOf("\n")!=-1){
									str1 = str1.replace("\n"," ")
								}
								map.put(str,str1)
							}
							break;
						case "Account Summary":
							tablexpath = to.findPropertyValue('xpath')
							list.addAll(driver.findElements(By.xpath(tablexpath+"//table/tbody/tr")))
							for(int column=1; column<list.size(); column++){
								ele1= driver.findElement(By.xpath(tablexpath+"//table/tbody/tr[2]/td["+column+"]"))
								ele2= driver.findElement(By.xpath(tablexpath+"//table/tbody/tr[4]/td["+column+"]"))
								str = ele1.getText().trim()
								str1 = ele2.getText().trim()
								if(str.indexOf("\n")!=-1){
									str = str.replace("\n"," ")
								}
								if(str1.indexOf("\n")!=-1){
									str1 = str1.replace("\n"," ")
								}
								map.put(str,str1)
							}
							break;

						case "Allocation":

							map = getDailyPriceAllocationTblData(to,"finalPage")
							break;
					}

					return map;
				}catch(Exception ex){
					KeywordUtil.markFailed("Exception occured due to  :: "+ex.getMessage())}

			}
		}catch(Exception ex){
			KeywordUtil.markFailed("Exception occured due to unable to find test object :: "+ex.getMessage())}
	}



	@Keyword

	public static void searchFunds(TestObject to,TestObject shareClass, TestObject tbl,String PageName,String classValue= null,String FundName){
		String tablexpath = null,strXpath = null,url = null, str = null,str1 = null;
		WebElement ele = null,ele1= null;
		List<String> list = new ArrayList();
		try{
			if(WebUI.verifyElementPresent(to, 10)){
				try{
					if(WebUI.verifyElementPresent(shareClass, 10)){
						WebUI.selectOptionByLabel(shareClass, classValue, false)
						uik.waitForDocLoad()
						Thread.sleep(1000)
						if(WebUI.verifyOptionSelectedByLabel(shareClass,classValue,false,30) == true ){
							KeywordUtil.logInfo(" Class share "+classValue+" is selected")
						}
						WebUI.sendKeys(to, FundName)
						uik.waitForDocLoad()
						uik.verifyForBrokenLinks(tbl,"href", null)
						uik.waitForDocLoad()
						tablexpath = tbl.findPropertyValue("xpath")
						strXpath = tablexpath.replace("/a","/span")
						list.addAll(driver.findElements(By.xpath(tablexpath)))
						System.out.println(list.size())
						KeywordUtil.logInfo("Searched list size is "+list.size())
						if(list.size()>0){
							for(int i =1; i<=list.size();i++){

								ele = ele = driver.findElement(By.xpath("("+tablexpath+")["+i+"]"))
								ele1 = driver.findElement(By.xpath("("+strXpath+")["+i+"]"))
								if ((ele.getText().contains(FundName))||(ele1.getText().contains(FundName))){
									KeywordUtil.markPassed(FundName+" is available in the list")
								}else{
									KeywordUtil.markFailed(FundName+" is not available in the list")
								}

							}
						}else(
							KeywordUtil.logInfo("No fund is available with search fund "+FundName)
							)

						url = driver.findElement(By.xpath(tablexpath)).getAttribute("href")
						str = driver.findElement(By.xpath(tablexpath)).getText()
						WebUI.navigateToUrl(url)
						uik.waitForDocLoad()
						verifyPageName(str)
						TestObject secondPageTo = uik.convertXpathtoTestObject("//*[@id='breadcrumb']/div/form/select[2]","secondPageTo")
						str1 = classValue.substring(0,1)
						str1 = "Class "+str1
						if(WebUI.verifyOptionSelectedByLabel(secondPageTo,str1,false,30) == false ){
							KeywordUtil.markFailed(FundName+" page is not loaded with correct share class category "+classValue)
						}else{
							KeywordUtil.logInfo( str+" page verified successfully ")
						}

					}
				}catch(Exception ex){
					KeywordUtil.markFailed("Exception occured due to  :: "+ex.getMessage())}

			}

		}catch(Exception ex){
			KeywordUtil.markFailed("Exception occured due to unable to find test object :: "+ex.getMessage())}

	}


}