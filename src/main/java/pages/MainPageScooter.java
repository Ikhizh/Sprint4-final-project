package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static config.WebDriverConfig.WAIT_SECONDS_TIME_OUT;
import static org.hamcrest.CoreMatchers.containsString;

public class MainPageScooter {
    private WebDriver driver;

    // Заголовок страницы
    private By headerLogo = By.className("Header_Logo__23yGT");

    //Кнопка Заказать на верху страницы
    private By topOrderButton = By.xpath(".//button[@class = 'Button_Button__ra12g']");

    // Кнопка заказать внизу страницы
    private By bottomOrderButton = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    // Первая стрелка : Сколько это стоит? И как оплатить?
    private String arrow = "//div[@id='accordion__heading-%d'][text()='%s']";
    // Текст под первой стрелкой
    private String underArrowText = "accordion__panel-%d";

    public MainPageScooter(WebDriver driver) {
        this.driver = driver;
    }

    // метод ожидания прогрузки лого страницы
    public void waitForLoadProfileData() {
        new WebDriverWait(driver, WAIT_SECONDS_TIME_OUT).until(driver -> (driver.findElement(headerLogo).getText() != null
                && !driver.findElement(headerLogo).getText().isEmpty()));
    }

    // метод для нажатия на кнопку Заказать вверху страницы
    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }
    // метод для нажатия на кнопку внизу страницы
    public void clickBottomOrderButton() {
        WebElement bottomOrderButton = driver.findElement(this.bottomOrderButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()", bottomOrderButton);
        bottomOrderButton.click();
    }
    // Метод для выбора кнопки Заказать
    public void clickOrderButton(String button){
        if (button.equals("topOrderButton")) {
            clickTopOrderButton();
        }
        if (button.equals("bottomOrderButton")) {
            clickBottomOrderButton();
        }
    }

    // Метод для нажатия на стрелку
    public void pressArrow(int numbArrow, String question) {
        WebElement arrow = driver.findElement(By.xpath(String.format(this.arrow, numbArrow, question)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()", arrow);
        js.executeScript("arguments[0].click()", arrow);
    }

    // Метод для проверки видимости элемента
    public String getArrowText(int numbUnderArrowText) {
        String formatedArrow = String.format(underArrowText, numbUnderArrowText);
        return driver.findElement(By.id(formatedArrow)).getText();
    }

    // Метод который проверяет открылась ли информация под стрелочкой
    public void checkArrowOpened(int numbUnderArrowText) {
        String formatedArrow = String.format(underArrowText, numbUnderArrowText);
        Assert.assertTrue(driver.findElement(By.id(formatedArrow)).isDisplayed());
    }

    // метод проверки актуальности текста под 1 стрелкой
    public void checkArrowText(int numbUnderArrowText, String expectedText) {
        String actualText = getArrowText(numbUnderArrowText);
        Assert.assertThat(actualText, containsString(expectedText));
    }
}