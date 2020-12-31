package com.github.curriculeon;

import com.git_leon.leonium.browsertools.WebPage;
import com.git_leon.leonium.browsertools.With;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerInterface;
import com.github.curriculeon.utils.ApplicationProperties;
import com.github.git_leon.Keys;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leon on 12/22/2020.
 */
public class LandingPage extends WebPage {
    private ApplicationProperties applicationProperties = new ApplicationProperties();
    private By byInputFirstName = With.tagAttributeValue("input", "name", "fname");
    private By byInputLastName = With.tagAttributeValue("input", "name", "lname");
    private By byButtonSubmit = With.tagAttributeValue("button", "type", "submit");
    private By byUnorderedListCustomerList = By.cssSelector("#customer-list > ul:nth-child(2) > li");


    public LandingPage(WebDriver driver) {
        super(driver);
    }


    public LandingPage(BrowserHandlerInterface browserHandler) {
        super(browserHandler);
    }

    @Override
    public void navigateTo() {
        Integer webApplicationPortNumber = applicationProperties.getConfig().getInt("server-port");
        getBrowserHandler().navigateTo("http://localhost:".concat(webApplicationPortNumber.toString()));
    }

    public void sendInputToFirstName(String input) {
        getBrowserHandler().sendKeys(byInputFirstName, input);
    }

    public void sendInputToLastName(String input) {
        getBrowserHandler().sendKeys(byInputLastName, input);
    }

    public void clickSubmitButton() {
        getBrowserHandler().getWebEntity(byButtonSubmit).click();
    }

    public List<String> getCustomerNameList() {
        List<String> list = new ArrayList<>();
        getBrowserHandler()
                .getElements(byUnorderedListCustomerList)
                .forEach(element -> list.add(element.getText().toString()));
        return list;
    }

    public Boolean validateNameHasBeenInput(String nameToSeek) {
        return getCustomerNameList()
                .stream()
                .anyMatch(name -> name.equalsIgnoreCase(nameToSeek));
    }

    public Alert getAlert() {
        return getBrowserHandler()
                .getDriver()
                .switchTo()
                .alert();
    }

    public void dismissAlert() {
        try {
            getAlert().dismiss();
        } catch (Exception ignored1) {
            try {
                getAlert().accept();
            } catch (Exception ignored2) {
                getAlert().sendKeys(Keys.ESCAPE.toString());
            }
        }
    }
}
