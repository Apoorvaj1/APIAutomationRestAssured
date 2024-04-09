package org.project.apoorv.DDT;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Datasupplier1 {
    @Test(dataProvider = "data12")
    public void TestLogin(String username,String password){
        System.out.println(username +" :: "+password);
    }
    @DataProvider
    public Object[][] data12(){

        return new Object[][]{
                {"admin","password123"},
                {"admin123","password12345"}
                };
        }
}
