package com.github.curriculeon;

import com.git_leon.leonium.DirectoryReference;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandler;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerInterface;
import com.git_leon.leonium.browsertools.browserhandler.reporting.BrowserHandlerLayeredLogger;
import com.git_leon.leonium.browsertools.factories.BrowserHandlerFactory;
import com.github.curriculeon.utils.ApplicationProperties;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by leon on 12/22/2020.
 */
public class LandingPageTest {
    private ApplicationProperties applicationProperties;
    private WebDriver driver;
    private static final String reportName = "Site Traversal Reports";
    private BrowserHandlerInterface browserHandler;

    @Before
    public void setup() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        this.applicationProperties = new ApplicationProperties();
        this.driver = BrowserHandlerFactory.PHANTOMJS.getDriver(capabilities);
        this.browserHandler = new BrowserHandlerLayeredLogger(driver, DirectoryReference
                .TARGET_DIRECTORY
                .getFileFromDirectory("Report-" + System.nanoTime() + ".html")
                .getAbsolutePath(),
                "test-" + Long.toHexString(System.nanoTime()));
    }

    @After
    public void teardown() {
        new BrowserHandler(driver).close();
    }

    private void test(String firstName, String lastName) {
        // given
        browserHandler.getOptions().SCREENSHOT_ON_EVENT.setValue(false);

        String expectedListOutput = firstName.concat(" ").concat(lastName);
        LandingPage landingPage = new LandingPage(browserHandler);

        // when
        landingPage.navigateTo();
        landingPage.sendInputToFirstName(firstName);
        landingPage.sendInputToLastName(lastName);
        landingPage.clickSubmitButton();
        landingPage.dismissAlert();
        System.out.println(landingPage.getCustomerNameList());

        // then
        Assert.assertTrue(landingPage.validateNameHasBeenInput(expectedListOutput));
    }

    @Test
    public void test() {
        String applicationOwner = "Leon Hunter";
        test(applicationOwner, String.valueOf(System.nanoTime()));
    }
}
