package search;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTests;
import widgets.SearchResults;

public class SearchTests extends BaseTests {

    @Test (groups = {"positive"}, description = "Search by a full match on the name of a product")
    public void searchForProductFullMatch(){
        String productName = "Samsung SyncMaster 941BW";
        SearchResults searchResults = homePage.searchProductsEnter(productName);
        Assert.assertTrue(searchResults.isProductTitleListed(productName), productName + " is not in search results");
        Assert.assertEquals(searchResults.getTitle(productName), productName, productName + " is not in search results");
        Assert.assertEquals(searchResults.getNumberOfListedProducts(), 1, "Number of search results: ");
    }
    @Test (groups = {"positive"}, description = "Search by inclusion on the part of the name")
    public void searchForProductByInclusion(){
        String text = "iP";
        String productName = "iPod Touch";
        SearchResults searchResults = homePage.searchProductsEnter(text);
        Assert.assertTrue(searchResults.isProductTitleListed(productName), productName + " is not in search results");
        Assert.assertEquals(searchResults.getTitle(productName),productName,productName + " is not in search results");
        Assert.assertTrue(searchResults.isPartOfProductTitleListed(text), text + " is not in search results");
        Assert.assertEquals(searchResults.getNumberOfListedProducts(), 5,"Number of search results: ");
    }
    @Test (groups = {"positive"}, description = "Search with empty result")
    public void searchForProductEmptyResults(){
        String productName = "8790";
        String textResult = "Your shopping cart is empty!";
        SearchResults searchResults = homePage.searchProducts(productName);
        Assert.assertEquals(searchResults.getTextSearchResult(), textResult, "incorrect");
        Assert.assertFalse(searchResults.isProductTitleListed(productName), productName + " is in search results");
    }

    @Test(groups = {"negative"}, description = "Search by name and category")
    public void searchForProductsByNameAndCategory(){
        String option = "      Monitors";
        String space = " ";
        String value = "28";
        String productName = "Samsung SyncMaster 941BW";
        SearchResults searchResults = homePage.searchProducts(space);
        searchResults.searchByNameAndCategory(productName,value); // option
        searchResults.selectFromCategory(value); //option
        var selectedOptions = searchResults.getSelectedOption();
        Assert.assertEquals(selectedOptions.size(),1, "Selected options: ");
        Assert.assertTrue(selectedOptions.contains(option), "Option doesn't contain " + option );
        Assert.assertTrue(searchResults.isProductTitleListed(productName), productName + " doesn't listed");
    }
    @Test(groups = {"positive"}, description = "Search by category")
    public void searchForProductsByCategory(){
        String option = "      Monitors";
        String space = " ";
        String value = "28";
        String productName = "Samsung SyncMaster 941BW";
        SearchResults searchResults = homePage.searchProducts(space);
        searchResults.searchByCategory(value); // option
        searchResults.selectFromCategory(value); //option
        var selectedOptions = searchResults.getSelectedOption();
        Assert.assertEquals(selectedOptions.size(),1, "Selected options: ");
        Assert.assertTrue(selectedOptions.contains(option), "Option doesn't contain " + option );
        Assert.assertTrue(searchResults.isProductTitleListed(productName), productName + " doesn't listed");
    }
}
