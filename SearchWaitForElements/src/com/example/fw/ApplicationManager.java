package com.example.fw;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class ApplicationManager {
	
	public WebDriver driver;
	protected String baseUrl;
	public boolean acceptNextAlert = true;
	
	private SearchHelper searchHelper;
	
	
	public ApplicationManager(){
		driver = new FirefoxDriver();
	    baseUrl = "http://barancev.w.pw/";
	    driver.manage().deleteAllCookies();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	}
	

	public void openMainPage() {
		driver.get(baseUrl + "/php4dvd/");
	}
	

	public void stop() {
		driver.quit();
	}


	public SearchHelper getSearchHelper(){
		if (searchHelper == null) {
			searchHelper = new SearchHelper(this);
		}
		return searchHelper;
	}

	
	}
	

	
	
	


