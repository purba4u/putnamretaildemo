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

WebUI.openBrowser('https://www.putnam.com/individual/mutual-funds/')

CustomKeywords.'customKeywords.PortalUIKeywords.waitForDocLoad'()

CustomKeywords.'customKeywords.PortalKeywords.verifyPage'('Putnam Mutual Funds', 'Mutual Funds - Putnam Investments')

CustomKeywords.'customKeywords.PortalKeywords.searchFunds'(findTestObject('IndividualInvestor/PageSpecificObjects/SearchFunds/elSearchBtn'), findTestObject(
        'IndividualInvestor/PageSpecificObjects/SearchFunds/elShareClass'), findTestObject('IndividualInvestor/PageSpecificObjects/SearchFunds/elFundTbl'), 'All Mutual Funds', 'B share', 
    'AMT')

