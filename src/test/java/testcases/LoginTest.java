package testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginTest extends CommonMethods {

    @Test(groups = "regression")
    public void adminLogin() {
        LoginPage login = new LoginPage();
        sendText(login.usernameBox, ConfigReader.getPropertyValue("username"));
        sendText(login.passwordBox, ConfigReader.getPropertyValue("password"));
        click(login.loginBtn);

        //assertion
        DashBoardPage dashBoardPage = new DashBoardPage();
        Assert.assertTrue(dashBoardPage.welcomeMessage.isDisplayed());
    }

    @DataProvider
    public Object[][] invalidData() {
        Object[][] data = {
                {"James", "123!", "Invalid credentials"}, //Username is correct, Password is incorrect
                {"Admin1", "Hum@nhrm123", "Invalid credentials"}, //Username is incorrect, Password is correct
                {"Admin", "", "Password cannot be empty"}, //Username is correct, Password cannot be empty
                {"", "Hum@nhrm123", "Username cannot be empty"} //Username cannot be empty, Password is correct
        };
        return data;
    }

    @Test(dataProvider = "invalidData")
    public void invalidLoginErrorMessageValidation(String username, String password, String message) {
        LoginPage loginPage=new LoginPage();
        loginPage.login(username, password);

        String actualError=loginPage.errorMessage.getText();
        Assert.assertEquals(actualError,message,"Error message does not match");

    }
}
