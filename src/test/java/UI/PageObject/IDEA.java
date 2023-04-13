package UI.PageObject;

import UI.Abstract;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class IDEA extends Abstract {

    public IDEA (WebDriver driver){
        this.driver = (EventFiringWebDriver) driver;
        PageFactory.initElements(driver, this);
    }

    public String Idea = "https://www.jetbrains.com/idea/";

    public By DeveloperTools = By.xpath("//nav[@class='_mainMenu_259yv wt-col-inline']/div[@data-test-marker='Developer Tools']");
    public By WebStorm = By.xpath("(//h5[contains(.,'IDEs')])[1]/following-sibling::div//div[@class='_mainSubmenuSubColumns__column_kut92 _mainSubmenuSubColumns__column--6_tg83s'][2]/div/div[last()]");
    public By WebStormCss = By.cssSelector(".wt-offset-top-12");


}
