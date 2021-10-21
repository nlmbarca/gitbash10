package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginPage extends CommonMethods {

    @FindBy(id = "txtUsername")
    public WebElement usernameBox;

    @FindBy(id = "txtPassword")
    public WebElement passwordBox;

    @FindBy(id = "btnLogin")
    public WebElement loginBtn;

    @FindBy(id="spanMessage")
    public WebElement errorMessage;

    public LoginPage(){
        PageFactory.initElements(CommonMethods.driver,this);
    }

    public void login(String username, String password){
        sendText(usernameBox, username);
        sendText(passwordBox,password);
        click(loginBtn);
    }
}
