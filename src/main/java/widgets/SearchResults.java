package widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResults {
    protected WebDriver driver;
    public SearchResults(WebDriver driver) {
        this.driver = driver;
    }

    By products = By.className("product-thumb"); //div[@class='product-thumb']
    By searchField = By.id("input-search"); //input[@id='input-search']
    By searchBtn = By.id("button-search");
    By productsTitle = By.xpath("//div[@class='product-thumb']/div[2]/div[@class='caption']/h4/a");
    By textSearchResult = By.xpath("//div[@id='content']/p[2]");
    By categoryDropdown = By.xpath("//div[@id='content']/div[1]/div[2]/select[@class='form-control']");

    // 1. Get list of products
    private List<WebElement> getListedProducts() {
        return driver.findElements(products);
    }
    // 2. We got all products title
    private List<WebElement> getListedProductsTitle() {
        return driver.findElements(productsTitle);
    }
    // 3. Find the names of each product in search list and compare if
    // the name of the product = a specific name, return this productTitle
    private WebElement findProductTitle(String productName){
        List<WebElement> productsTitle = getListedProductsTitle();
        for(WebElement productTitle : productsTitle){
            String title = productTitle.getText();
            if(productName.equals(title)){
                //System.out.println("Search results: " + title);
                return productTitle;
            }
        }
        return null;
    }
    // 4. Do we have a specific product's name in search results?
    public boolean isProductTitleListed(String productName) {
        return findProductTitle(productName) != null;
    }
    // 5. Get each product's titles to assert search and result names are equal.
    public Object getTitle(String productName) {
        WebElement productTitle = findProductTitle(productName);
        return productTitle.getText();
    }
    // 6. Find the names of each product in search list and compare if
    //search text contained in the product's name, return this productTitle
    private WebElement findPartOfProductTitle(String text){
        List<WebElement> productsTitle = getListedProductsTitle();
        for(WebElement productTitle : productsTitle){
            String title = productTitle.getText();
            System.out.println("Search results 2: " + title);
            if(title.contains(text)){
                return productTitle;
            }
        }
        return null;
    }
    // 7. Is the search text contained in the product's names in search results?
    public boolean isPartOfProductTitleListed(String productName) {
        return findPartOfProductTitle(productName) != null;
    }
    // 8. Get the number of products
    public Object getNumberOfListedProducts() {
        return getListedProducts().size();
    }

    // 9. Get text if search result is empty
    public String getTextSearchResult(){
        String textResult = driver.findElement(textSearchResult).getText();
        //System.out.println("Text: " + textResult);
        return textResult;
    }
    //10. Find dropdown element with options
    private Select findDropdownCategory(){
        return new Select(driver.findElement(categoryDropdown));
    }
    //11. Select one of the options
    public void selectFromCategory(String value){ //String option
        findDropdownCategory().selectByValue(value);//selectByVisibleText(option);
    }
    //12. Get selected options to assert quantity
    public List<String> getSelectedOption(){
        List<WebElement> selectedElements =
                findDropdownCategory().getAllSelectedOptions();
        var selectedOption = selectedElements.stream().map(e->e.getText()).collect(Collectors.toList());
        //System.out.println(selectedOption);
        return selectedOption;
    }
    //13. Search products method by name and category
    public void searchByNameAndCategory(String productName, String value){ //String option
        driver.findElement(searchField).click();
        driver.findElement(searchField).sendKeys(productName);
        selectFromCategory(value); //(option)
        driver.findElement(searchBtn).click();
    }
    //14. Search products method by category
    public void searchByCategory( String value){ //String option
        selectFromCategory(value); //(option)
        driver.findElement(searchBtn).click();
    }
}