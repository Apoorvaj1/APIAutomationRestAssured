package org.project.apoorv.DDT;

import org.testng.annotations.Test;

public class DDT03_02 {
    @Test(dataProvider = "getData", dataProviderClass = DDT02.class)
    public void testLoginData(String username, String password) {
        System.out.println("UserName - " + username);
        System.out.println("Password - " + password);



    }
}
