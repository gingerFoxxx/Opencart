package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import widgets.SearchResults;

public class HomePage {
    protected WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    By searchField = By.cssSelector("input.form-control.input-lg");
    By searchBtn = By.cssSelector("button.btn.btn-default.btn-lg");

    public SearchResults searchProducts(String text){
        driver.findElement(searchField).click();
        driver.findElement(searchField).sendKeys(text);
        driver.findElement(searchBtn).click();
        return new SearchResults(driver);
    }
    public SearchResults searchProductsEnter(String text){
        driver.findElement(searchField).click();
        driver.findElement(searchField).sendKeys(text + Keys.ENTER);
        return new SearchResults(driver);
    }

}
