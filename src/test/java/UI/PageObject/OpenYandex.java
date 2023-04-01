package UI.PageObject;

import UI.Abstract;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class OpenYandex extends Abstract {

    public OpenYandex (WebDriver driver){
        this.driver = (EventFiringWebDriver) driver;
        PageFactory.initElements(driver, this);
    }

    public String Yandex = "https://yandex.ru/search/?text=поиск&lr=54&search_source=yaru_desktop_common";

    @FindBy(xpath = "//input[@class='input__control mini-suggest__input']")
    public WebElement SearchBar;
    public By SearchBarWait = By.xpath("//input[@class='input__control mini-suggest__input']");

    @FindBy(xpath = "//button[contains(.,'Найти')]")
    public WebElement Search;
    public By SearchWait = By.xpath("//button[contains(.,'Найти')]");
}
