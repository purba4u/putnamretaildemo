package customKeywords

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.security.cert.CertificateException
import java.security.cert.X509Certificate

import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.builtin.getCSSValueKeyword

import globalVariables.gv


/**
 * @author navbysai
 *
 */

class UIKeywords {

	public static String resp = ''

	@Keyword
	public static WebDriver getCurrentSessionDriver(){
		return DriverFactory.getWebDriver()
	}

	@Keyword
	public static TestObject convertXpathtoTestObject(String locator,String ObjectName){
		TestObject newTestObject = new TestObject(ObjectName)
		newTestObject.addProperty("xpath", ConditionType.EQUALS, locator,true)
		return newTestObject
	}

	/**
	 * verify link presence on the page
	 * Owner: Arun
	 */
	@Keyword
	public def verifyLinkExistOnPage(String linkText) {
		boolean flag = false;
		String link = "//a[contains(text(),'LINK')]";
		String linkXpath = link.replace('LINK', linkText);

		try {
			TestObject linkTestObject = new TestObject("linkTestObject");
			linkTestObject.addProperty("xpath", ConditionType.EQUALS, linkXpath, true);
			flag = WebUI.findWebElement(linkTestObject).isDisplayed();
			if(flag){
				KeywordUtil.markPassed(linkText+" displayed on the page");
			}else{
				KeywordUtil.markFailed(linkText+" NOT displayed on the page");
			}
		} catch (WebElementNotFoundException e) {
			KeywordUtil.markFailed("Something went wrong while finding link--"+linkText+"\n"+e.getMessage());
		} catch (Exception e) {
			KeywordUtil.markFailed("Something went wrong while finding link--"+linkText+"\n"+e.getMessage());
		}
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
	@Keyword
	public static verifyComponentElements(TestObject[] tom){

		for(TestObject to in tom){
			try{
				if(WebUI.verifyElementPresent(to, gv.default_wait, FailureHandling.OPTIONAL)){
					WebUI.scrollToElement(to, gv.default_wait, FailureHandling.OPTIONAL)
					KeywordUtil.markPassed(to.getObjectId()+"Element is available in the given Component")
				}else{
					KeywordUtil.markFailed(to.getObjectId()+'Element is NOT available in the given Component')
				}
			}catch(Exception ex){
				KeywordUtil.markFailed(to.getObjectId()+'Exception occured: Element is NOT available in the given Component'+ex.getMessage())
			}
		}
	}


	public static trustAllCerts = [
		new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return null
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
			}
		}
	] as TrustManager[]


	@Keyword
	public static String isLinkBroken(URL url){
		String response = "";
		CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
		trustAllHttpsCertificates()

		if (url.toString().contains('https')){
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			try {
				connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML/ like Gecko) Chrome/23.0.1271.95 Safari/537.11");
				connection.connect();
				response = connection.getResponseMessage();
				//print(response)
				connection.disconnect();
				return response;
			}
			catch(Exception exp) {
				return exp.getMessage();
			}
		}else{
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			try {
				connection.connect();
				response = connection.getResponseMessage();
				connection.disconnect();
				return response;
			}
			catch(Exception exp) {
				return exp.getMessage();
			}
		}
	}

	@Keyword
	public static void waitForDocLoad(){

		ExpectedCondition<Boolean> expectation = new
				ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver driver) {
						driver = getCurrentSessionDriver();
						return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
					}
				};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(getCurrentSessionDriver(), 30);
			wait.until(expectation);
		} catch (Throwable error) {
		}
	}


	private static void trustAllHttpsCertificates() throws Exception {
		TrustManager[] trustAllCerts = new TrustManager[10];
		TrustManager tm = new miTM();
		trustAllCerts[0] = tm;
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, null);
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	}

	static class miTM implements TrustManager,X509TrustManager {
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(X509Certificate[] certs) {
			return true;
		}

		public void checkServerTrusted(X509Certificate[] certs, String authType)
		throws CertificateException {
			return;
		}

		public void checkClientTrusted(X509Certificate[] certs, String authType)
		throws CertificateException {
			return;
		}
	}

	public class BlindTrustManager implements X509TrustManager {

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public void checkClientTrusted(X509Certificate[] chain, String authType)
		throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
		throws CertificateException {
		}
	}

	@Keyword
	public static void jsClicker(TestObject to) {
		try{
			WebElement element = WebUiCommonHelper.findWebElement(to,30)
			WebUI.scrollToElement(to, gv.default_wait,FailureHandling.OPTIONAL)
			WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element))
		}catch(Exception ex){
			KeywordUtil.markFailed('Exception occured: jsClicker: '+ex.getMessage())
		}
	}


	@Keyword
	public static void isObjectEnabled(TestObject toObj) {
		List<WebElement> lst=WebUiCommonHelper.findWebElements(toObj,10)
		for(WebElement we :lst){
			if(we.isEnabled()) {
				KeywordUtil.markPassed(toObj.getObjectId()+":Element is Enabled")
			}
			else {
				KeywordUtil.markFailed(toObj.getObjectId()+":Element is Disabled")
			}
		}
	}


	@Keyword
	//Updated By Prabhat specifc to property
	public static void verifyIfLinkBroken(TestObject to){
		try{
			if(WebUI.verifyElementPresent(to, 10)){
				WebElement element = WebUiCommonHelper.findWebElement(to, 10)
				String str = element.getAttribute('href')


				if(str==null || str ==''||str=='null'){
					KeywordUtil.markFailed('href value is: '+str+', hence marking it as warning. Please relook')
				}else if(str!='#' || !str.contains('mailto:') || !str.contains('javascript:void')){
					resp = isLinkBroken(new URL(str))
					if(resp in [
						'OK',
						'Found',
						'Redirect',
						'Request denied'
					]) {
						KeywordUtil.markPassed(str+ ' Link not Broken: HTTP Response is : '+'\''+resp +'\'')
					}else {
						KeywordUtil.markFailed(str+' is broken link, HTTP response is:'+'\''+resp +'\'')
					}
				}
			}else{
				KeywordUtil.markFailed(to.getObjectId()+' is not present on the page to check if the link is broken')
			}
		}catch(Exception ex){
			KeywordUtil.markFailed('Exception occured: verifyIfLinkBroken: '+ex.getMessage())
		}
	}

	// The Keyword is used to accept the Webelement as a parameter instead of TestObject
	@Keyword
	public static void verifyIfLinkisBroken(WebElement element){
		try{
			//WebElement element = WebUiCommonHelper.findWebElement(to, 10)
			String str = element.getAttribute('href')
			if(str==null || str ==''||str=='null'){
				KeywordUtil.markFailed('href value is: '+str+', hence marking it as warning. Please relook')
			}else if(str!='#' || !str.contains('mailto:') || !str.contains('javascript:void')){
				resp = isLinkBroken(new URL(str))
				if(resp in [
					'OK',
					'Found',
					'Redirect',
					'Request denied'
				]) {
					KeywordUtil.markPassed(str+ ' Link not Broken: HTTP Response is : '+'\''+resp +'\'')
				}else {
					KeywordUtil.markFailed(str+' is broken link, HTTP response is:'+'\''+resp +'\'')
				}

			}
		}catch(Exception ex){
			KeywordUtil.markFailed('Exception occured: verifyIfLinkBroken: '+ex.getMessage())
		}
	}

	@Keyword
	public static void verifyIfImgRendered(TestObject to){
		try{
			if(WebUI.verifyElementPresent(to, 10)){
				WebElement element = WebUiCommonHelper.findWebElement(to, 10)
				String str = element.getAttribute('src')
				if(str==null || str ==''||str=='null'){
					KeywordUtil.markFailed('href value is: '+str+', hence marking it as warning. Please relook')
				}else if(str!='#' || !str.contains('mailto:') || !str.contains('javascript:void')){
					resp = isLinkBroken(new URL(str))

					if(resp in [
						'OK',
						'Found',
						'Redirect',
						'Moved Permanently'
					]) {
						KeywordUtil.markPassed('Image Rendered: HTTP Response of : '+str+ ' is '+'\''+resp +'\'')
					}else{
						KeywordUtil.markFailed('Image rendering failed, HTTP response is:'+'\''+resp +'\'')
					}
				}
			}else{
				KeywordUtil.markFailed(to.getObjectId()+' is not present on the page to check if the Image is rendered')
			}
		}catch(Exception ex){
			KeywordUtil.markFailed('Exception occured: verifyIfImgRendered'+ex.getMessage())
		}
	}

	//The Keyword is used to accept the Webelement as a parameter instead of TestObject
	@Keyword
	public static void verifyIfImgRender(WebElement element)
	{
		try{
			//WebElement element = WebUiCommonHelper.findWebElement(to, 10)
			String str = element.getAttribute('src')
			if(str==null || str ==''||str=='null'){
				KeywordUtil.markFailed('href value is: '+str+', hence marking it as warning. Please relook')
			}else if(str!='#' || !str.contains('mailto:') || !str.contains('javascript:void')){
				resp = isLinkBroken(new URL(str))

				if(resp in [
					'OK',
					'Found',
					'Redirect',
					'Moved Permanently'
				]) {
					KeywordUtil.markPassed('Image Rendered: HTTP Response of : '+str+ ' is '+'\''+resp +'\'')
				}else{
					KeywordUtil.markFailed('Image rendering failed, HTTP response is:'+'\''+resp +'\'')
				}
			}

		}catch(Exception ex){
			KeywordUtil.markFailed('Exception occured: verifyIfImgRendered'+ex.getMessage())
		}
	}

	@Keyword
	public static verifyForBrokenLinks(TestObject to,String propName, String domain=null){
		List resultList = new ArrayList();
		int list = 0
		String response = null;
		try{
			List linkList = new ArrayList();
			if(WebUI.verifyElementPresent(to, 10)){
				linkList.addAll(WebUiCommonHelper.findWebElements(to, 15))
				list=linkList.size()
				/*if(linkList.size() < GlobalVariable.size)
				 list=linkList.size()
				 else
				 list = GlobalVariable.size*/

				for (int i=0; i<list; i++){
					WebElement element = linkList[i]
					String str = element.getAttribute(propName)
					System.out.println("======="+ str+"=======")
					if(str==null || str ==''||str=='null'){
						KeywordUtil.markWarning('href value is: '+str+', hence marking it as warning. Please relook')
					}else if(str!='#' || !str.contains('mailto:') || !str.contains('javascript:void')){
						if(!domain.isEmpty()){
							str =domain+str}
						try{
							response = isLinkBroken(new URL(str))
							if(response in [
								'OK',
								'Found',
								'Redirect',
								'Moved Permanently'
							]){
								KeywordUtil.markPassed(str+ ' Link not Broken: HTTP Response is : '+'\''+response +'\'')
							}else{
								KeywordUtil.markFailed(str+ ':Failed due to response:'+'\''+response +'\'')
								resultList.add(str+ ':Failed due to response:'+'\''+response +'\'')
							}
						}catch(Exception ex){
							resultList.add(str+ ':Exception occured due to reason:'+ex.getMessage())
							KeywordUtil.markFailed(str+ ':Exception occured due to reason:'+ex.getMessage())
						}
					}
				}
			}else{
				KeywordUtil.markFailed(to.getObjectId()+' is not present on the page')
			}
		}catch(Exception e){
			KeywordUtil.markFailed('Exception occured at verifyForBrokenLinks: '+e.getMessage())
		}finally{
			if(resultList.size()>0){
				KeywordUtil.markFailed("Total broken links in:"+to.getObjectId().toString()+':'+resultList.size().toString())
			}
		}
	}

	@Keyword
	public static void verifyImagesRendering(TestObject imgElement, String domain=null) {
		List finalList = new ArrayList();
		List urlList = new ArrayList();
		List resultList = new ArrayList();
		try{
			List elementList = new ArrayList();
			if(WebUI.verifyElementPresent(imgElement, 10)){
				elementList.addAll(WebUiCommonHelper.findWebElements(imgElement, 10));
				for (WebElement element : elementList) {
					String str = element.getAttribute('src')
					if(str != null && str!='#' && !str.contains('mailto:') && !str.contains('javascript:void')) {
						finalList.add(element);
						if(!str.contains('http')){
							urlList.add(domain+str)
						}else {
							urlList.add(str)
						}
					}else{
						KeywordUtil.markWarning('SRC attribute of the image is null. Element\'s alt property: '+element.getAttribute('alt'))
					}
				}

				for(url in urlList){
					try{
						resp = isLinkBroken(new URL(url))
						if(resp.equals('OK')){
							KeywordUtil.markPassed('Image rendered on UI for url:'+url+'___'+resp)
						}else{
							KeywordUtil.markWarning(url+ 'Image rendering failed due to reason:'+resp)
							resultList.add(url+ ':Failed due to reason:'+resp)
						}
					}catch(Exception ex){
						resultList.add(url+ ':Exception occured due to reason:'+ex.getMessage())
					}
				}
			}else{
				KeywordUtil.markFailed(imgElement.getObjectId()+' is not present on the page')
			}
		}catch(Exception e){
			KeywordUtil.markWarning('Exception occured at verifyImagesRendering: '+e.getMessage())
			resultList.add('Exception occured at verifyImagesRendering: '+e.getMessage())
		}
		finally{
			if(resultList.size()>0){
				KeywordUtil.markFailed("Total Images not rendered in the page:"+resultList.size().toString())
			}
		}
	}

	@Keyword
	public static void refreshBrowser() {
		KeywordUtil.logInfo("Refreshing")
		WebDriver webDriver = DriverFactory.getWebDriver()
		webDriver.navigate().refresh()
		KeywordUtil.markPassed("Refresh successfully")
	}

	@Keyword
	public static void browserBack(){
		KeywordUtil.logInfo("Navigating to back page")
		WebUI.back()
		WebUI.waitForPageLoad(gv.default_wait, FailureHandling.OPTIONAL);
	}

	@Keyword
	public static  void scrollToElement(TestObject to) {
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement element = WebUiCommonHelper.findWebElement(to)
		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor) driver)
					.executeScript("arguments[0].scrollIntoView(true);", element);
		}
	}

	/**
	 * Click element
	 * @param to Katalon test object
	 */
	@Keyword
	def clickElement(TestObject to) {
		try {
			WebElement element = WebUiCommonHelper.findWebElement(to);
			KeywordUtil.logInfo("Clicking element")
			element.click()
			KeywordUtil.markPassed("Element has been clicked")
		} catch (WebElementNotFoundException e) {
			KeywordUtil.markFailed("Failed: Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("Exception: Failed to click on element"+e.getMessage())
		}
	}

	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */
	@Keyword
	public static List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUiCommonHelper.findWebElement(table,10)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
	}


	@Keyword
	public static getProperty(TestObject to){
		WebElement elem = WebUiCommonHelper.findWebElement(to)
		return elem.getCssValue();
	}

	//-----Putnam
	/**
	 *
	 * @param str = 'Funds&_H > All Mutal Funds&_C>asfasfd>asdfasfdasfd>'
	 * navigagteTopath("PutnamInvestments","alt")
	 */
	@Keyword
	public static void navigateToPath(String str, String locator=null){
		WebDriver driver = DriverFactory.getWebDriver()
		try{
			String[] pathArray = str.split('>')
			for(path in pathArray){
				path = path.trim()
				String locatorStr = ""

				if(path.contains('&_')){
					if(locator!=null && !locator.isEmpty()){
						//if(locator.length()!=0){
						locatorStr = "//a[@"+locator+"='"+path.substring(0, path.lastIndexOf('&_'))+"']"
					}else{
						locatorStr = "//a[contains(text(), '"+path.substring(0, path.lastIndexOf('&_'))+"')]"
					}
				}else{
					if(locator!=null && locator.contains('iframe')){
						TestObject to = new TestObject('to');
						to.addProperty("xpath", ConditionType.EQUALS, locator.split('_')[1], true)
						WebUI.switchToFrame(to, 0, FailureHandling.STOP_ON_FAILURE)
						locatorStr ="//a[contains(text(),'"+path+"')]"
					}
					else if(locator!=null){
						locatorStr = "//a[@"+locator+"='"+path+"']"
					}else{
						locatorStr ="//a[contains(text(),'"+path+"')]"
					}
				}
				try{
					WebElement anchor= driver.findElement(By.xpath(locatorStr))
					int i=0;
					while(!anchor.isDisplayed()){
						sleep(1000)
						i=i++
						if(i>60) {
							KeywordUtil.markFailed("Element we are searching for not found"+path)
							break;
						}
					}
					if(path.endsWith('&_H') || path.endsWith('&_h')) {
						Actions builder = new Actions(driver);
						Actions hoverOverLink = builder.moveToElement(anchor);
						hoverOverLink.perform();
						KeywordUtil.logInfo("Hovering on link:"+locatorStr)
					}else{
						anchor.click();
						KeywordUtil.logInfo("Clicked on link:"+locatorStr)
						waitForDocLoad();
						resp = isLinkBroken(new URL(WebUI.getUrl()))
						if (resp=='OK' || resp == 'ok')
							KeywordUtil.markPassed("Given page loaded successfuly:"+WebUI.getUrl()+" with response 200 OK")
						else
							KeywordUtil.markWarning("Given page's response is:"+ resp)

						//Checks the Title of the page
						if(path.contains("&_T")){
							if(WebUI.getWindowTitle().toString().toLowerCase().contains(str.toLowerCase()))
								KeywordUtil.markPassed("The page title matched with that of the navigated link")
							else
								KeywordUtil.markWarning("Page Title is:"+WebUI.getWindowTitle()+" which is not matching with that of: "+str)
						}
					}
				}catch(Exception ex){
					KeywordUtil.markFailed("Exception occured: "+ex.getMessage())
				}
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Exception occured in navigateToPath method: "+e.getMessage())
		}
	}


	/**
	 * Loads the given url and checks if the page is loaded successfully.
	 * Marks pass if loads successfully, marks warning if not 200 OK.
	 * @param Url
	 */
	@Keyword
	public static void loadUrl(String Url){
		try{
			resp = isLinkBroken(new URL(Url))
			if (resp=='OK' || resp == 'ok'){
				WebUI.navigateToUrl(Url)
				waitForDocLoad()
				KeywordUtil.markPassed("Given page loaded successfuly:"+Url+" with response 200 OK")
			}
			else{
				KeywordUtil.markWarning("Given page's response is:"+ resp)}
		}catch (Exception e) {
			KeywordUtil.markFailed("Exception occured in loading given url : "+e.getMessage())
		}
	}

	@Keyword
	public static void navigateTo(String ... elements) {
		try{
			for(String element: elements) {

				if (element.contains('http')){

					WebUI.navigateToUrl(element, FailureHandling.STOP_ON_FAILURE)
					//WebUI.waitForPageLoad(gv.default_wait)
					waitForDocLoad()
					WebDriver driver = DriverFactory.getWebDriver()
					if (driver.getTitle().contains("Certificate") || driver.getTitle().contains("Secure"))
						driver.get("javascript:document.getElementById('overridelink').click();")

					if(WebUI.verifyElementPresent(findTestObject("Object Repository/Not found page/txterror"),
					5, FailureHandling.OPTIONAL))
					{
						KeywordUtil.markFailed('URL is navigated to Page Not found')
					}
					String resp = ''
					resp = isLinkBroken(new URL(element))
					if(resp in [
						'OK',
						'Found',
						'Redirect',
						'Request denied'
					]) {
						KeywordUtil.markPassed(element+ ' Link not Broken: HTTP Response is : '+'\''+resp +'\'')
					}else {
						KeywordUtil.markFailed(element+' is broken link, HTTP response is:'+'\''+resp +'\'')
					}
				}else if(element.endsWith('_H') || element.endsWith('_h')) {
					TestObject to = findTestObject(element.substring(0, element.lastIndexOf('_')))
					WebUI.scrollToElement(to, gv.default_wait, FailureHandling.OPTIONAL)
					WebUI.waitForElementVisible(to, gv.default_wait,FailureHandling.OPTIONAL)
					WebUI.mouseOver(findTestObject(element.substring(0, element.lastIndexOf('_'))), FailureHandling.OPTIONAL);
					KeywordUtil.logInfo('Hovered on Element: '+element)
				} else if (element != null || element != ''){
					TestObject to = WebUI.waitForElementClickable(findTestObject(element), gv.default_wait, FailureHandling.STOP_ON_FAILURE)
					WebUI.scrollToElement(to, gv.default_wait, FailureHandling.OPTIONAL)
					WebUI.click(findTestObject(element),10, FailureHandling.OPTIONAL);
					WebUI.waitForPageLoad(gv.default_wait)
					KeywordUtil.logInfo('Element Clicked: '+element)
				} else if(element == null || element == '') {
					KeywordUtil.logInfo("Element passed is null, please pass the valid element to \'navigate to page\'")
				}
			}
		}catch(Exception ex){
			KeywordUtil.markFailed('Exception occured during the navigation:'+ex.getMessage())
		}
	}






	@Keyword
	public static void navigateToPages(String ... elements)
	{
		try{
			for(String element: elements)
			{
				if (element.contains('http'))
				{
					//WebUI.navigateToUrl(element, FailureHandling.STOP_ON_FAILURE)
					//WebUI.waitForPageLoad(gv.default_wait)
					waitForDocLoad()
					WebDriver driver = DriverFactory.getWebDriver()
					if (driver.getTitle().contains("Certificate") || driver.getTitle().contains("Secure"))
						driver.get("javascript:document.getElementById('overridelink').click();")

					if(WebUI.verifyElementPresent(findTestObject("Object Repository/Not found page/txterror"),
					15, FailureHandling.OPTIONAL))
					{
						KeywordUtil.markFailed('URL is navigated to Page Not found')
					}
				}
				else if(element.endsWith('_H') || element.endsWith('_h')) {
					TestObject to = findTestObject(element.substring(0, element.lastIndexOf('_')))
					WebUI.scrollToElement(to, gv.default_wait, FailureHandling.OPTIONAL)
					WebUI.waitForElementVisible(to, gv.default_wait,FailureHandling.OPTIONAL)
					WebUI.mouseOver(findTestObject(element.substring(0, element.lastIndexOf('_'))), FailureHandling.OPTIONAL);
					KeywordUtil.logInfo('Hovered on Element: '+element)
				}
				else if (element != null || element != ''){
					TestObject to = WebUI.waitForElementClickable(findTestObject(element), gv.default_wait, FailureHandling.STOP_ON_FAILURE)
					WebUI.scrollToElement(to, gv.default_wait, FailureHandling.OPTIONAL)
					WebUI.click(findTestObject(element),10, FailureHandling.OPTIONAL);
					WebUI.waitForPageLoad(gv.default_wait)
					KeywordUtil.logInfo('Element Clicked: '+element)
				}
				else
				{
					KeywordUtil.logInfo("Element passed is null, please pass the valid element to \'navigate to page\'")
				}
			}
		}
		catch(Exception ex)
		{
			KeywordUtil.markFailed('Exception occured during the navigation:'+ex.getMessage())
		}

	}
}
