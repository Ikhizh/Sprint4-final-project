package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static config.WebDriverConfig.WAIT_SECONDS_TIME_OUT;
import static org.hamcrest.CoreMatchers.containsString;

public class OrderPage {
    private WebDriver driver;

    // Заголовок страницы
    private By headerLogoOrderPage = By.className("Header_LogoScooter__3lsAR");

    // Поле ввода имени
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    // Поле ввода Фамилии
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    // Поле ввода адреса
    private By deliveryAdressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле ввода станции метро
    private By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    // Элемент дробдауна станции метро
    private String metroStationDropdown = "//ul[@class='select-search__options']//div[@class='Order_Text__2broi'][text() = '%s']";
    // Поле ввода номера телефона
    private By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //Кнопка далее под секцией
    private By nextButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Поле когда привезти самокат
    private By whenDeliverField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Поле срок аренды
    private By rentalPeriodField = By.className("Dropdown-placeholder");
    // Дропдаун срок аренды
    private String RentalPeriodDropdown = ".//div[@class='Dropdown-menu']//div[@class='Dropdown-option'][text() = '%s']";
    // Чекбокс чёрный жемчуг
    private By blackCheckBox = By.xpath(".//input[@id='black']");
    // чекбокс серая безысходность
    private By greyCheckBox = By.xpath(".//input[@id='grey']");
    // Поле Комментарий для курьера
    private By commentForCourierField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    // Кнопка заказать под фориой заказа
    private By finalOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    // Кнопка ДА
    private By yesButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM'][text()='Да']");
    // Текст заказ оформлен
    private By orderConfirmationText = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");
    // попап
    private By popup = By.xpath(".//div[@class='Order_Modal__YZ-d3']");


    public OrderPage(WebDriver driver) {

        this.driver = driver;
    }

    // Заполнение поля имени пользователя
    public void fillUsername(String userName) {
        driver.findElement(nameField).sendKeys(userName);
    }

    // Заполнение поля фамилии
    public void feelSurnameField(String surName) {
        driver.findElement(surnameField).sendKeys(surName);
    }

    // Заполнение поля Адрес куда привести заказ
    public void fillDeliveryAdressField(String deliveryAdress) {
        driver.findElement(deliveryAdressField).sendKeys(deliveryAdress);
    }

    // Клик поля станция метро
    public void clickMetroStationField() {
        driver.findElement(metroStationField).click();
    }

    // Выбор станции метро из списка
    public void selectMetroStationField(String metroStationName) {
        By metroStationField = By.xpath(String.format(metroStationDropdown, metroStationName));
        driver.findElement(metroStationField).click();
    }

    // Заполнение поля номер телефона
    public void fillPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    // Кликнуть кнопку далее
    public void pressNextButton() {
        driver.findElement(nextButton).click();
    }

    // заполнить поле Поле когда привезти самокат
    public void fillWhenDeliverField(String deliverTime) {
        driver.findElement(whenDeliverField).sendKeys(deliverTime);
        driver.findElement(whenDeliverField).sendKeys(Keys.ENTER);
    }

    // кликнуть на поле срок аренды
    public void clickRentalPeriodField() {
        driver.findElement(rentalPeriodField).click();
    }

    // Вывыбор элемента из списка срока аренды
    public void selectRentalPeriodField(String periodName) {
        By metroStationField = By.xpath(String.format(RentalPeriodDropdown, periodName));
        driver.findElement(metroStationField).click();
    }

    // Кликнуть серый цвет
    public void clickGreyCheckBox() {
        driver.findElement(greyCheckBox).click();
    }

    // Кликнуть черный цвет
    public void clickBlackCheckBox() {
        driver.findElement(blackCheckBox).click();
    }

    // метод выбора цвета самоката
    public void chooseScooterColor(String color) {
        if (color.equals("чёрный жемчуг")) {
            clickBlackCheckBox();
        }
        if (color.equals("серая безысходность")) {
            clickGreyCheckBox();
        }
    }

    // Заполнить поле коментарий для курьера
    public void fillCommentForCourier(String comment) {
        driver.findElement(commentForCourierField).sendKeys(comment);
    }

    // кликнуть кнопку заказать
    public void clickFinalOrderButton() {
        driver.findElement(finalOrderButton).click();
    }

    //  Кликнуть кнопку ДА
    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }

    // дождаться появления текста заказ оформлен
    public void waitForOrderConfirmation() {
        new WebDriverWait(driver, WAIT_SECONDS_TIME_OUT).until(driver -> (driver.findElement(orderConfirmationText).getText() != null
                && !driver.findElement(orderConfirmationText).getText().isEmpty()));
    }

    // Получить текст из подтверждения оформления заказа
    public String getMessageConfirmation() {
        return driver.findElement(orderConfirmationText).getText();
    }

    // Проверить что попап заказ оформлен визибл
    public void checkOrderPopUpVisible() {
        Assert.assertTrue(driver.findElement(popup).isDisplayed());
    }

    // Проверить есть ли текст заказ оформлен
    public void checkConfirmationOrder() {
        String expectedText = "Заказ оформлен";
        String actualText = getMessageConfirmation();
        Assert.assertThat(actualText, containsString(expectedText));
    }

    public void enterFirstOrderPath(String userName, String surName, String deliveryAdress, String metroStationName, String phoneNumber) {
        fillUsername(userName);
        feelSurnameField(surName);
        fillDeliveryAdressField(deliveryAdress);
        clickMetroStationField();
        selectMetroStationField(metroStationName);
        fillPhoneNumber(phoneNumber);
    }

    public void enterSecondOrderPath(String deliverTime, String periodName, String color, String comment) {
        fillWhenDeliverField(deliverTime);
        clickRentalPeriodField();
        selectRentalPeriodField(periodName);
        chooseScooterColor(color);
        fillCommentForCourier(comment);
    }
}
