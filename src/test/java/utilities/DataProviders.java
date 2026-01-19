package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    // DataProvider 1
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {

        // taking xl file from testData
        String path = ".\\testData\\Opencart_LoginData.xlsx";

        // creating an object for ExcelUtility
        ExcelUtility xlutil = new ExcelUtility(path);

        int totalrows = xlutil.getRowCount("Sheet1");
        int totalcols = xlutil.getCellCount("Sheet1", 1);

        // created two-dimensional array to store data
        String logindata[][] = new String[totalrows][totalcols];

        // read the data from excel and store in 2D array
        for (int i = 1; i <= totalrows; i++) {     // rows start from 1
            for (int j = 0; j < totalcols; j++) {  // columns start from 0
                logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
            }
        }

        return logindata; // returning two dimensional array
    }
}
