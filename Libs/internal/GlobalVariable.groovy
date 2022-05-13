package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object ENV
     
    /**
     * <p></p>
     */
    public static Object IndividualPath
     
    /**
     * <p></p>
     */
    public static Object PerspectivePath
     
    /**
     * <p></p>
     */
    public static Object MutualFundsPath
     
    /**
     * <p></p>
     */
    public static Object WhitePaperPath
     
    /**
     * <p></p>
     */
    public static Object InfographicsPath
     
    /**
     * <p></p>
     */
    public static Object CollegeSavingsNeedToKnow
     
    /**
     * <p></p>
     */
    public static Object CollegeSavingsDailyPricing
     
    /**
     * <p></p>
     */
    public static Object CollegeSavingsFourYearActionPlan
     
    /**
     * <p></p>
     */
    public static Object CollegeSavingsOptions
     
    /**
     * <p></p>
     */
    public static Object IndividualPortfolioMangers
     
    /**
     * <p></p>
     */
    public static Object IndividualMutualFundPricing
     
    /**
     * <p></p>
     */
    public static Object IndividualMutualFundsFundsDocument
     
    /**
     * <p></p>
     */
    public static Object IndividualMutualFundsHistoricalPricing
     
    /**
     * <p></p>
     */
    public static Object IndividualMutualFundsCdCalculator
     
    /**
     * <p></p>
     */
    public static Object IndividualMutualFundsClosedEndFunds
     
    /**
     * <p></p>
     */
    public static Object IndividualTaxCenter
     
    /**
     * <p></p>
     */
    public static Object IndividualForms
     
    /**
     * <p></p>
     */
    public static Object individualHomePage
     
    /**
     * <p></p>
     */
    public static Object RetiermentAnnuties
     
    /**
     * <p></p>
     */
    public static Object indUserName
     
    /**
     * <p></p>
     */
    public static Object IndPassword
     
    /**
     * <p></p>
     */
    public static Object key
     
    /**
     * <p></p>
     */
    public static Object advUserName
     
    /**
     * <p></p>
     */
    public static Object HomePage
     
    /**
     * <p></p>
     */
    public static Object AdvisorPath
     
    /**
     * <p></p>
     */
    public static Object DCIOPath
     
    /**
     * <p></p>
     */
    public static Object InstitutionalPath
     
    /**
     * <p></p>
     */
    public static Object Individual404
     
    /**
     * <p></p>
     */
    public static Object AdvisorEmail
     
    /**
     * <p></p>
     */
    public static Object AdvisorVisionID
     
    /**
     * <p></p>
     */
    public static Object AdvisorVisionPassword
     
    /**
     * <p></p>
     */
    public static Object AdvisorFV
     
    /**
     * <p></p>
     */
    public static Object Inst_InvestmentCapabilities
     
    /**
     * <p></p>
     */
    public static Object InstEmail
     
    /**
     * <p></p>
     */
    public static Object InstUserID
     
    /**
     * <p></p>
     */
    public static Object InstPassword
     
    /**
     * <p></p>
     */
    public static Object ENV_PROD
     
    /**
     * <p></p>
     */
    public static Object pwd
     
    /**
     * <p></p>
     */
    public static Object instUserName
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
            selectedVariables += TestCaseMain.getParsedValues(RunConfiguration.getOverridingParameters())
    
            ENV = selectedVariables['ENV']
            IndividualPath = selectedVariables['IndividualPath']
            PerspectivePath = selectedVariables['PerspectivePath']
            MutualFundsPath = selectedVariables['MutualFundsPath']
            WhitePaperPath = selectedVariables['WhitePaperPath']
            InfographicsPath = selectedVariables['InfographicsPath']
            CollegeSavingsNeedToKnow = selectedVariables['CollegeSavingsNeedToKnow']
            CollegeSavingsDailyPricing = selectedVariables['CollegeSavingsDailyPricing']
            CollegeSavingsFourYearActionPlan = selectedVariables['CollegeSavingsFourYearActionPlan']
            CollegeSavingsOptions = selectedVariables['CollegeSavingsOptions']
            IndividualPortfolioMangers = selectedVariables['IndividualPortfolioMangers']
            IndividualMutualFundPricing = selectedVariables['IndividualMutualFundPricing']
            IndividualMutualFundsFundsDocument = selectedVariables['IndividualMutualFundsFundsDocument']
            IndividualMutualFundsHistoricalPricing = selectedVariables['IndividualMutualFundsHistoricalPricing']
            IndividualMutualFundsCdCalculator = selectedVariables['IndividualMutualFundsCdCalculator']
            IndividualMutualFundsClosedEndFunds = selectedVariables['IndividualMutualFundsClosedEndFunds']
            IndividualTaxCenter = selectedVariables['IndividualTaxCenter']
            IndividualForms = selectedVariables['IndividualForms']
            individualHomePage = selectedVariables['individualHomePage']
            RetiermentAnnuties = selectedVariables['RetiermentAnnuties']
            indUserName = selectedVariables['indUserName']
            IndPassword = selectedVariables['IndPassword']
            key = selectedVariables['key']
            advUserName = selectedVariables['advUserName']
            HomePage = selectedVariables['HomePage']
            AdvisorPath = selectedVariables['AdvisorPath']
            DCIOPath = selectedVariables['DCIOPath']
            InstitutionalPath = selectedVariables['InstitutionalPath']
            Individual404 = selectedVariables['Individual404']
            AdvisorEmail = selectedVariables['AdvisorEmail']
            AdvisorVisionID = selectedVariables['AdvisorVisionID']
            AdvisorVisionPassword = selectedVariables['AdvisorVisionPassword']
            AdvisorFV = selectedVariables['AdvisorFV']
            Inst_InvestmentCapabilities = selectedVariables['Inst_InvestmentCapabilities']
            InstEmail = selectedVariables['InstEmail']
            InstUserID = selectedVariables['InstUserID']
            InstPassword = selectedVariables['InstPassword']
            ENV_PROD = selectedVariables['ENV_PROD']
            pwd = selectedVariables['pwd']
            instUserName = selectedVariables['instUserName']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
