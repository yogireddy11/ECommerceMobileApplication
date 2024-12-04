package InitialPage;

import BasePack.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.testng.Assert.*;

public class LoginPage extends BaseClass {

    @FindBy(id = "com.android.permissioncontroller:id/permission_message")
    WebElement permissionMessage;
    @FindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
    WebElement mediaAllowPermission;
    @FindBy(id = "com.android.permissioncontroller:id/permission_deny_button")
    WebElement mediaDenyPermission;


    @Test(priority = 1 , enabled = true)
    public void enablePermission(){
        driverWait.until(ExpectedConditions.visibilityOf(mediaDenyPermission));
        System.out.println("Permission message : "+permissionMessage.getText());
        if (mediaAllowPermission.isDisplayed() && mediaDenyPermission.isDisplayed()){
            mediaAllowPermission.click();
            logger.info("Permission allow button pressed!!");
        }else{
            logger.warning("Permission Buttons not displaying");
        }
    }
    @FindBy(xpath = "//android.widget.Image[@text=\"logo\"]") WebElement pageLogo;
    @FindBy(xpath = "//android.widget.Button[@text=\"\uF090 LOGIN \"]") WebElement loginBtn;
    @FindBy(xpath = "//android.widget.Button[@text=\"\uF234 REGISTER \"]") WebElement registerBtn;
    @FindBy(xpath = "//android.widget.Button[@text=\"\uF054 SKIP & CONTINUE \"]") WebElement skipAndContinue;

    @Test(priority = 2)
    public void verifyAllButtonsPresent(){
        driverWait.until(ExpectedConditions.visibilityOf(loginBtn));
        boolean allElement = pageLogo.isDisplayed() && loginBtn.isDisplayed() && registerBtn.isDisplayed() && skipAndContinue.isDisplayed();
        assertTrue(allElement);
    }
    @FindBy(xpath = "//android.webkit.WebView[@text=\"login\"]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.widget.EditText")
    WebElement mobileNumTXtBx;
    @FindBy(xpath = "//android.webkit.WebView[@text=\"login\"]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]/android.widget.EditText")
    WebElement passwordTxtBx;
    @FindBy(xpath = "//android.widget.TextView[@text=\"\uF133\"]")
    WebElement passwordVisibleIcon;
    @FindBy(xpath = "//android.widget.Button[@text=\"\uF090 LOGIN\"]") WebElement confirmLogin;
    @FindBy(xpath = "//android.widget.TextView[@text=\" FORGOT PASSWORD ?\"]") WebElement forgotPass;
    @FindBy(xpath = "//android.widget.TextView[@text=\"SIGN UP\"]") WebElement signBtnInLoginBtn;
    @FindBy(xpath = "//android.webkit.WebView[@text=\"login\"]/android.view.View/android.view.View[2]/android.widget.TextView")
    WebElement errorMsg;
    @Test(priority = 3)
    public void tryLoginWithOutRegister(){
        driverWait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        driverWait.until(ExpectedConditions.visibilityOf(mobileNumTXtBx));
        mobileNumTXtBx.sendKeys(properties.getProperty("inCorrectMobile"));
        passwordTxtBx.sendKeys(properties.getProperty("inCorrectPass"));
        confirmLogin.click();
        driverWait.until(ExpectedConditions.visibilityOf(errorMsg));
        logger.info(errorMsg.getText());
        assertEquals(errorMsg.getText(),"\"Username or Password is incorrect. Please try again\"");
    }
    @FindBy(xpath = "//android.widget.TextView[@text=\"Login successfully\"]") WebElement successMsg;
    @Test(priority = 4)
    public void loginAfterRegistration(){
        driverWait.until(ExpectedConditions.visibilityOf(mobileNumTXtBx));
        mobileNumTXtBx.clear();
        mobileNumTXtBx.sendKeys(properties.getProperty("correctUserName"));
        passwordTxtBx.clear();
        passwordTxtBx.sendKeys(properties.getProperty("correctedPassword"));
        confirmLogin.click();
        driverWait.until(ExpectedConditions.visibilityOf(successMsg));
        String getTxt = successMsg.getText();
        assertEquals(getTxt,"Login successfully");
    }

    @FindBy(xpath = "//android.widget.TextView[@text=\" Select Area\"]") WebElement selectArea;
    @FindBy(xpath = "//android.webkit.WebView[@text=\"login\"]/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View[11]")
    WebElement selectAreaOption;
    @FindBy(xpath = "//android.webkit.WebView[@text=\"login\"]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]/android.widget.EditText")
    WebElement fullName;
    @FindBy(xpath = "//android.webkit.WebView[@text=\"login\"]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[3]/android.widget.EditText")
    WebElement mobile;
    @FindBy(xpath = "//android.webkit.WebView[@text=\"login\"]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[4]/android.widget.EditText")
    WebElement email;
    @FindBy(xpath = "//android.webkit.WebView[@text=\"login\"]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[5]/android.widget.EditText")
    WebElement password;
    @FindBy(xpath = "//android.webkit.WebView[@text=\"login\"]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[6]/android.widget.EditText")
    WebElement confPass;
    @FindBy(xpath = "//android.widget.RadioButton[@resource-id=\"TC\"].") WebElement termsAndConditionsCB;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Terms & Conditions\"]") WebElement termsAndCondition;
    @FindBy(xpath = "//android.view.View[@content-desc=\"BACK\"]") WebElement backBtn;
    @FindBy(xpath = "//android.view.View[@content-desc=\"REGISTER\"]") WebElement registerBtnInSignBtn;

    @Parameters({"name","mobileNum","email","password","confPassword"})
    @Test(priority = 4, enabled = false)
    public void register( @Optional("Yogi Reddy") String fullNameField,
                          @Optional("8374033532") String mobileField,
                          @Optional("reddyyogi8010@gmail.com") String emailField,
                          @Optional("password123") String passwordField,
                          @Optional("password123") String confPasswordField){
        driverWait.until(ExpectedConditions.elementToBeClickable(signBtnInLoginBtn)).click();
        driverWait.until(ExpectedConditions.visibilityOf(registerBtnInSignBtn));
      //  assertFalse(registerBtnInSignBtn.isEnabled(),"initially it is enabled!!!");
        selectArea.click();
        driverWait.until(ExpectedConditions.elementToBeClickable(selectAreaOption)).click();
        driverWait.until(ExpectedConditions.visibilityOf(fullName));
        fullName.sendKeys(fullNameField);
        mobile.sendKeys(mobileField);
        email.sendKeys(emailField);
        password.sendKeys(passwordField);
        confPass.sendKeys(confPasswordField);
        handleRegister();
    }

    private void handleRegister(){
        String getFullName = fullName.getAttribute("text");
        String getMobileName = mobile.getAttribute("text");
        String getEmail = email.getAttribute("text");
        String getPassword = password.getAttribute("text");
        String getConfPass = confPass.getAttribute("text");
        List<String> list  = new ArrayList<>();
        list.add(getFullName);
        list.add(getMobileName);
        list.add(getEmail);
        list.add(getPassword);
        list.add(getConfPass);
        System.out.println(list);
        boolean verifyFields = !getFullName.isEmpty() && !getMobileName.isEmpty() && !getEmail.isEmpty()
                && !getPassword.isEmpty() && !getConfPass.isEmpty();
        if(!verifyFields){
            throw new AssertionError("All fields must be submit");
        }
        boolean validMobileLength = getMobileName.length() == 10;
        if (!validMobileLength) {
            throw new AssertionError("Mobile number must be 10 digits!");
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        boolean validEmail = pattern.matcher(getEmail).matches();
        if (!validEmail) {
            throw new AssertionError("Email format is invalid!");
        }

        boolean passwordsMatch = getPassword.equals(getConfPass);
        if (!passwordsMatch) {
            throw new AssertionError("Passwords do not match!");
        }
        driverWait.until(ExpectedConditions.elementToBeClickable(termsAndConditionsCB)).click();
        registerBtnInSignBtn.click();
        System.out.println("Registration from validate successfully!!!");
    }
}
