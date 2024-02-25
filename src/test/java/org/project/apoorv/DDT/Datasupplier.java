package org.project.apoorv.DDT;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Datasupplier {
    @Test(dataProvider = "dp1")
    public void TestLogin(String s){
        System.out.println(s);
    }
    @DataProvider
    public String[] dp1(){
        String[] name = new String[]{
                "Apoorv",
                "Jain",
                "Agrawal"
        };
        return name;
    }
}
