package ru.praktikum_services.scooter;

import extensions.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.MainPageScooter;
import pages.OrderPage;

import static utils.ProjectUtils.getFutureDate;

@RunWith(Parameterized.class)
public class OrderTest {
    private final String metroStationName;
    private final String rentPeriod;
    private final String scooterColor;
    private final String button;

    public OrderTest(String metroStationName, String rentPeriod, String scooterColor, String button) {
        this.metroStationName = metroStationName;
        this.rentPeriod = rentPeriod;
        this.scooterColor = scooterColor;
        this.button = button;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Черкизовская", "сутки", "чёрный жемчуг", "topOrderButton"},
                {"Арбатская", "двое суток", "серая безысходность", "bottomOrderButton"},
        };
    }

    private WebDriver driver;
    private MainPageScooter mainPageScooter;

    @Before
    public void setup() {
        driver = WebDriverFactory.get();
        mainPageScooter = new MainPageScooter(driver);
        mainPageScooter.waitForLoadProfileData();
    }
    @Test
    public void orderCheck() {
        mainPageScooter.clickOrderButton(button);
        OrderPage orderPage = new OrderPage(driver);
        orderPage.enterFirstOrderPath("Иван", "Иванов", "Дом номер10", metroStationName, "+79992347698");
        orderPage.pressNextButton();
        orderPage.enterSecondOrderPath(getFutureDate(1), rentPeriod, scooterColor, "comment");
        orderPage.clickFinalOrderButton();
        orderPage.clickYesButton();
        orderPage.checkOrderPopUpVisible();
        orderPage.checkConfirmationOrder();
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
