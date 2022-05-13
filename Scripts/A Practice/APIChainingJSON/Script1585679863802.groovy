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
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.SelectorMethod as SelectorMethod
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import internal.GlobalVariable as GlobalVariable
import groovy.json.JsonSlurper as JsonSlurper

MFResponse= WS.sendRequest(findTestObject('UserRestService/MutualFund'))

def MFslurper= new groovy.json.JsonSlurper()
def MFresults= MFslurper.parseText(MFResponse.getResponseBodyContent())

def DistributionIncome= '$'+MFresults.distributions[0].incDivAmt[0]
def NAV= '$'+MFresults.pricing[0].nav[0]
def NavPercentChange= MFresults.pricing[0].navPercentChange[0]+'%'

WebUI.comment("Value is: "+DistributionIncome)
WebUI.comment("Value is: "+NAV)
WebUI.comment("Value is: "+NavPercentChange)


MFListResponse= WS.sendRequest(findTestObject('UserRestService/FundList'))

def MFListslurper= new groovy.json.JsonSlurper()
def MFListresults= MFListslurper.parseText(MFListResponse.getResponseBodyContent())

List<String> MFFundNames= MFListresults.fundName
int fund_count= MFFundNames.size()
WebUI.comment("Fund List: "+MFFundNames)
WebUI.comment("Fund Count: "+fund_count)