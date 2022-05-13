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
import customKeywords.UIKeywords as ui

import globalVariables.gv

public class PortalUIKeywords {

	@Keyword
	public static WebDriver getCurrentSessionDriver(){
		WebDriver driver = ui.currentSessionDriver()
		return driver
	}

	@Keyword
	public static TestObject convertXpathtoTestObject(String locator,String ObjectName){
		TestObject to = convertXpathtoTestObject( locator, ObjectName)
		return to
	}

	@Keyword
	public static verifyComponentElements(TestObject[] tom){
		try{
			ui.verifyComponentElements( tom)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
	}

	@Keyword
	public static String isLinkBroken(URL url){
		try{
			return  ui.isLinkBroken( url)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
	}

	@Keyword
	public static void waitForDocLoad(){
		try{
			ui.waitForDocLoad()
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
	}

	@Keyword
	public static void jsClicker(TestObject to){
		try{
			ui.jsClicker( to)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
	}

	@Keyword
	public static void isObjectEnabled(TestObject toObj) {
		try{
			ui.isObjectEnabled( toObj)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
	}

	@Keyword
	public static void verifyIfLinkBroken(TestObject to) {
		try{
			ui.verifyIfLinkBroken( to)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
	}

	@Keyword
	public static verifyForBrokenLinks(TestObject to,String propName, String domain=null){
		try{
			ui.verifyForBrokenLinks( to, propName,  domain)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
	}

	@Keyword
	public static void refreshBrowser(){
		ui.refreshBrowser()
	}

	@Keyword
	public static void browserBack(){
		ui.browserBack()
	}

	/*@Keyword
	 public void scrollToElement(TestObject to){
	 ui.scrollToElement()
	 }*/

	@Keyword
	public static void navigateToPath(String str, String locator=null){
		try{
			ui.navigateToPath( str,  locator)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
	}

	@Keyword
	public static void loadUrl(String Url){
		ui.loadUrl( Url)
	}

	@Keyword
	public static void navigateToPages(String ... elements){
		try{
			ui.navigateToPages( elements)
		}catch(Exception ex){
			System.out.println("Exception occured due to : " +ex.getMessage())
		}
	}
}
