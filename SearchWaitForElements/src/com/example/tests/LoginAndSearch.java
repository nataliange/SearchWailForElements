package com.example.tests;


import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.testng.annotations.*;


public class LoginAndSearch extends TestBase {
	

@Test
  public void testSearch() throws Exception {
    app.openMainPage();
    app.getSearchHelper().getLoginDataFromFile();
    app.getSearchHelper().submitLogin();
    
    
	//list before search  
   List<Filmdata> FilmsBeforeSearch = app.getSearchHelper().getAllAvailableFilmsBeforeSearch();
   
   //fetch the first title from the whole titles list and use it for search 
   app.getSearchHelper().getTitleBeforeSearch();
   
   //perform search
   app.getSearchHelper().performSearchAndgetFilmsAfterSearch();
    
  //list after search 
    List<Filmdata> FilmsAfterSearch = app.getSearchHelper().performSearchAndgetFilmsAfterSearch();
    
    //check whether the list before search contains/intersects the list after search
    if (CollectionUtils.containsAny(FilmsBeforeSearch,FilmsAfterSearch)) {
		System.out.println("Film from the list displayed");
	} else {
		System.out.println("No intersection found");
	}
    
    //validate search result with entered test data
    app.getSearchHelper().validateSearchResults();
    
}


}
