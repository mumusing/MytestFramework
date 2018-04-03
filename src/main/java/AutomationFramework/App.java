package AutomationFramework;

import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.oracle.cloud.common.Config;
import org.oracle.cloud.common.UserInfo;
import org.oracle.cloud.common.UserList;
import org.oracle.cloud.reports.Report;
import org.oracle.cloud.reports.Status;

import com.oracle.cloud.utils.DateHelper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//       System.out.println( "Hello World!" );
//       UserList userInfo=new UserList();
//	   String	resultsPath=System.getProperty("user.dir")+"/report/"+ "Run"+DateHelper.getCurrentDateTime("yyyyMMdd")+DateHelper.getCurrentDateTime("HHmmss");
//       System.out.println(resultsPath);
//       System.out.println(System.getProperty("user.dir"));
//       
//      String env= Config.getEnvDetails("aut", "env");
//      UserInfo user=userInfo.getUser(env);
//      System.out.println(user.getUserName());
//      System.out.println(user.getPassWord());
//      WebDriver driver=new FirefoxDriver();
//     driver.get("https://www.facebook.com/");
//     Report.startExtentReport("TestExtent");
//     Report.log(Status.PASS, "Pass", driver);
//     Report.log(Status.INFO, "Pass", driver);
//     Report.log(Status.INFO, "Pass", driver);
//     Report.log(Status.PASS, "Pass", driver);
//     Report.endExtentReport();
//     Report.endExtentTest();
    	
    	
    	System.out.println(System.getProperty("user.dir")+"\\"+"drivers\\chromedriver.exe");
    }
}
