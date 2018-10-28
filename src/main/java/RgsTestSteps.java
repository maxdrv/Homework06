import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class RgsTestSteps extends CommonSteps {

    private static final String BASE_RGS_URL = "http://www.rgs.ru";

    private static final String insuranceNavButtonXpath = "//*[@id='main-navbar-collapse']//a[contains(text(), 'Страхование')]";

    private static final By navBarLocator = By.className("container-rgs-main-menu-links");

    private static final By calculateOnlineAbroadTripsButtonBy = By.xpath("//*[@class='thumbnail-footer']//a[contains(text(), 'Рассчитать ')]");

    private static final String categoryFormat = "//div[@class = 'grid rgs-main-menu-links']//a[contains(text(), '%s')]";

    private static final String tripsMultipleButtonXpath = "//button[@type='button']//span[contains(text(), 'Несколько')]";
    private static final String tripsOneButtonXpath = "//button[@type='button' and contains(text(),'Одна поездка') ]";

    private static final String destinationRegionInputXpath = "//*[@class = 'form-control-multiple-autocomplete-actual-input tt-input']";

    private static final String destinationCountrySelectXpath = "//select[@id='ArrivalCountryList']";

    private static final String dateOfFirstTripInputXpath = "//input[@data-test-name ='FirstDepartureDate']";

    private static final String noMoreThan90DaysButtonXpath = "//*[@class = 'form-group clearfix']//label[contains(text(), 'Не более 90 дней')]";
    private static final String noMoreThan30DaysButtonXpath = "//*[@class = 'form-group clearfix']//label[contains(text(), 'Не более 30 дней')]";

    private static final String nameLastNameInputXpath = "//div[@data-test-name = 'InsuredPerson']//input[@data-test-name = 'FullName']";
    private static final String dateOfBirthInputXpath = "//div[@data-test-name = 'InsuredPerson']//input[@data-test-name = 'BirthDate']";

    private static final String planningActiveRestButtonXpath = "//div[@class = 'toggle off toggle-rgs' and @data-toggle = 'toggle']";

    private static final String personalDataButtonXpath = "//input[@type = 'checkbox' and @data-test-name = 'IsProcessingPersonalDataToCalculate' ]";

    private static final String calculateInsuranceButtonXpath = "//button[@type = 'submit' and contains(., 'Рассчитать')]";

    public RgsTestSteps() {
        BASE_URL = BASE_RGS_URL;
    }

    public void openInsuranceNavBar() {             // step 2. Выбрать пункт меню – Страхование
        click(insuranceNavButtonXpath);       // находит элемент и совершает клик по нему
        waitForVisible(navBarLocator);              // ожидаем появления меню навигации
    }


    public void choseInsuranceCategory(String categoryName) {           // step 3. Путешествия – Страхование выезжающих за рубеж
        By categoryLocator = By                                         // находит категорию страхования указанную в categoryName
                .xpath(String.format(categoryFormat, categoryName));
        click(categoryLocator);                                         // клик по элементу
    }

    public void culculateOnlineAbroadsTripsButton() {       // step 4. Нажать рассчитать – Онлайн
        scrollToElement(calculateOnlineAbroadTripsButtonBy);      // скролим до элемента, что бы вытащить его из под context-bar
        click(calculateOnlineAbroadTripsButtonBy);                // нахордит кнопку и кликает по ней
    }

    public void checkAbroadTripsHeader(String expectedText) {               // step 5. Проверить наличие заголовка – Страхование выезжающих за рубеж
        WebElement header = findByXpath("//*[@class='page-header']");       // находит заголовок страницы
        checkElementText(header, expectedText);                             // сравнивает тест заголовка с указанным
    }


    public void tripsMultipleButton() {
        waitForVisible(tripsMultipleButtonXpath);
        clickJS(tripsMultipleButtonXpath);
    }

    public void destinationRegionInput(String regionName) {
        waitForVisible(destinationRegionInputXpath);
        typeText(destinationRegionInputXpath, regionName);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).perform();
    }

    public void destinationCountrySelect(String countryName) {
        waitForVisible(destinationCountrySelectXpath);
        Select choseCountry = new Select(findByXpath(destinationCountrySelectXpath));
        choseCountry.selectByVisibleText(countryName);
    }

    public void dateOfFirstTripInput(String dateOfFirstTrip) {
        waitForVisible(dateOfFirstTripInputXpath);
        scrollToElement(dateOfBirthInputXpath);
        click(dateOfFirstTripInputXpath);
        typeText(dateOfFirstTripInputXpath, dateOfFirstTrip);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();
    }

    public void noMoreThan90DaysButton() {
        waitForVisible(noMoreThan90DaysButtonXpath);
        scrollToElement(noMoreThan90DaysButtonXpath);
        click(noMoreThan90DaysButtonXpath);
    }

    public void nameLastNameAndBirthDateInput(String fullName, String date) {
        waitForVisible(nameLastNameInputXpath);
        click(nameLastNameInputXpath);
        typeText(nameLastNameInputXpath, fullName);

        waitForVisible(dateOfBirthInputXpath);
        click(dateOfBirthInputXpath);
        typeText(dateOfBirthInputXpath, date);
    }

    public void planningActiveRestButton() {
        waitForVisible(planningActiveRestButtonXpath);
        click(planningActiveRestButtonXpath);
    }

    public void personalDataButton() {

        scrollToElement(personalDataButtonXpath);
        click(personalDataButtonXpath);
    }

    public void calculateInsuranceButton() {
        waitForVisible(calculateInsuranceButtonXpath);
        scrollToElement(calculateInsuranceButtonXpath);
        click(calculateInsuranceButtonXpath);
    }

    private static final String multipleTripsXpath = "//div[@class = 'summary-row' and contains(., 'Условия страхования')]//span[@class = 'summary-value']";
    private static final String regionXpath = "//span[@data-bind = 'foreach: countries']/strong[1]";
    private static final String personXpath = "//div[@class = 'summary-row' and contains(., 'Застрахованный')]//span[@class = 'summary-value']/strong[1]";
    private static final String dateOfBirthXpath = "//div[@class = 'summary-row' and contains(., 'Застрахованный')]//span[@class = 'summary-value']/span[1]/strong[1]";
    private static final String activeRestXpath = "//div[@class = 'summary-row']//span[@class = 'summary-value' and contains(., 'включая активный отдых')]/span[@class = 'text-bold']";

    public void checkValues(String multipleTrips, String region, String person, String date, String activeRest) {
        waitForVisible(multipleTripsXpath);
        Assert.assertEquals("Значение условия страхования не соотвествует  ожидаемому",
                multipleTrips, findByXpath(multipleTripsXpath).getText());

        waitForVisible(regionXpath);
        Assert.assertEquals("Имя региона не соотвествует  ожидаемому",
                region, findByXpath(regionXpath).getText());

        waitForVisible(personXpath);
        Assert.assertEquals("Имя пользователя не соотвествует  ожидаемому",
                person, findByXpath(personXpath).getText());

        waitForVisible(dateOfBirthXpath);
        Assert.assertEquals("Дата рождения пользователя не соотвествует  ожидаемому",
               date, findByXpath(dateOfBirthXpath).getText());

        waitForVisible(activeRestXpath);
        Assert.assertEquals("Значение активного отдыха не соотвествует  ожидаемому",
                activeRest, findByXpath(activeRestXpath).getText());

    }

}
