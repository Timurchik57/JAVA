package UI.PageObject;

import UI.Abstract;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class OpenYandex extends Abstract {

    public OpenYandex(WebDriver driver){
        this.driver = (EventFiringWebDriver) driver;
        PageFactory.initElements(driver, this);
    }

    public String Yandex = "https://ya.ru";

    @FindBy(xpath = "//input[@placeholder='найдётся всё']")
    public WebElement SearchBar;
    public By SearchBarWait = By.xpath("//input[@placeholder='найдётся всё']");

    @FindBy(xpath = "//button[contains(.,'Найти')]")
    public WebElement Search;
    public By SearchWait = By.xpath("//button[contains(.,'Найти')]");

    public OpenYandex GetYandex(){
        driver.get(Yandex);
        return this;
    }

    public OpenYandex InputSearchBar(String words){
        SearchBar.sendKeys(words);
        return this;
    }

    public OpenYandex ClickSearch(){
        Search.click();
        return this;
    }

}
