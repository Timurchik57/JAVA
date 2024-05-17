package UI.PageObject;

import UI.Abstract;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class IDEA extends Abstract {

    public IDEA(WebDriver driver) {
        this.driver = (ChromeDriver) driver;
        PageFactory.initElements(driver, this);
    }

//    public IDEA(WebDriver driver) {
//        Abstract.driver = (RemoteWebDriver) driver;
//        PageFactory.initElements(driver, this);
//    }

    /**
     * URL - главаная страница
     */
    public String Idea = "https://www.jetbrains.com/idea/";

    /**
     * Главаная страница - Developer Tools
     */
    public By DeveloperTools = By.xpath(
            "(//div[@data-test-marker='Developer Tools'])[1]");

    /**
     * Главаная страница - Developer Tools - Название
     */
    public By DeveloperToolsName = By.xpath(
            "(//div[@data-test-marker='Developer Tools'])[1]/button");

    /**
     * Главаная страница - Developer Tools - List IDEs
     */
    public By ListIDEs = By.xpath(
            "(//h5[contains(.,'IDEs')])[1]/following-sibling::div//div[@class='_mainSubmenuSubColumns__column_nujyrk _mainSubmenuSubColumns__column--6_watp9g'][1]/div/div/a/span/span");


    /** Главаная страница - Developer Tools - Все элементы */
    public By LocationDeveloperTools (String str) {
        By element = By.xpath("(//div[@class='_mainSubmenuItem_kg0jc _mainSubmenuItem_l7othf _mainSubmenuItem_ljz82']//span[@class='_mainSubmenuItem__titlePart_1xyfn']/span[contains(.,'"+str+"')])[1]");
        return element;
    }

    /**
     * Главаная страница - WebStorm
     */
    public By WebStorm = By.xpath(
            "(//h5[contains(.,'IDEs')])[1]/following-sibling::div//div[@class='_mainSubmenuSubColumns__column_kut92 _mainSubmenuSubColumns__column--6_tg83s'][2]/div/div[last()]");

    /**
     * Главаная страница - WebStorm - CSS
     */
    public By WebStormCss = By.cssSelector(".wt-offset-top-12");

    /**
     * Значок лупы на главной странице
     */
    public By Search = By.xpath(
            "//input[@placeholder='Ctrl+K for advanced search']");

    /**
     * Поиск на главной странице
     */
    public By OpenSearch = By.xpath(
            "(//button[@aria-label='Open search'])[1]");

    /**
     * Атрибут покупки
     */
    public By Buy = By.xpath(
            "//a[@data-test='site-header-cart-action']");

    /**
     * Главное название
     */
    public By Header = By.xpath(
            "//h1[@class='rs-hero rs-hero_theme_dark wt-offset-top-32']");
}
