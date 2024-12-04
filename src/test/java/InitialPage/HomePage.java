package InitialPage;

import BasePack.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BaseClass {

    @FindBy(xpath = "//android.widget.EditText")
    WebElement searchBar;
    @FindBy(xpath = "//android.widget.Button[@text=\"\uF002\"]")
    WebElement searchEnter;
    @FindBy(xpath = "//android.widget.TextView[@text=\"MI Mobile\"]")
    WebElement mobileName;
    @FindBy(xpath = "//android.widget.TextView[@text=\" 9,000.00\"]")
    WebElement mobilePrice;
    @FindBy(xpath = "//android.widget.Button[@text=\"ADD\"]")
    WebElement add;

}
