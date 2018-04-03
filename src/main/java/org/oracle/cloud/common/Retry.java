package org.oracle.cloud.common;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer
{
	static private int retryCount = 0;
	
	private int maxRetryCount = 3;
	public boolean retry(ITestResult result)
	{
		 String maxRetryCount1=Config.getEnvDetails("reRun", "maxTryCount");
		 int maximumRetry=Integer.parseInt(maxRetryCount1);
		 System.out.println(maximumRetry);
		// TODO Auto-generated method stub
		if ((result.getStatus()==ITestResult.FAILURE)&&(retryCount < maximumRetry))
		{
            System.out.println("Retrying test " + result.getName() + " with status "
                    + getResultStatusName(result.getStatus()) + " for the " + (retryCount+1) + " time(s).");
            retryCount++;
            return true;
        }
        return false;
	}
	 public String getResultStatusName(int status) {
	    	String resultName = null;
	    	if(status==1)
	    		resultName = "SUCCESS";
	    	if(status==2)
	    		resultName = "FAILURE";
	    	if(status==3)
	    		resultName = "SKIP";
			return resultName;
	    }
}
