package com.example.fw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;






import com.example.tests.Filmdata;

public class SearchHelper extends HelperBase{


	public String firstTitle;

	public SearchHelper(ApplicationManager manager) {
		super(manager);

	}


	public void getTitleBeforeSearch() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(manager.driver,5 );
		List<WebElement> allFilmsTitles = manager.driver.findElements(By.cssSelector("div#container div.title"));
		for (int i = 0; i < 1; i++) {
			wait.until(ExpectedConditions.visibilityOfAllElements(allFilmsTitles));
			firstTitle = manager.driver.findElement(By.cssSelector("div#container div.title")).getText();
			manager.driver.findElement(By.id("q")).clear();
			manager.driver.findElement(By.id("q")).sendKeys(firstTitle);
		}
	}
	
//////////////////////////////////////////
	public List<Filmdata> getAllAvailableFilmsBeforeSearch() {
		List<Filmdata> films = new ArrayList<Filmdata>();
		WebDriverWait wait = new WebDriverWait(manager.driver,30 );
		List<WebElement> allFilmsTitles = manager.driver.findElements(By.cssSelector("div#container div.title")); 		
		for (WebElement element : allFilmsTitles) {
			wait.until(ExpectedConditions.visibilityOfAllElements(allFilmsTitles));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div#container div.title")));
			
			Filmdata film = new Filmdata();
			film.filmTitle = element.getText();
			films.add(film);		
	
				}		
		
		return films;
	}

	public List<Filmdata> performSearchAndgetFilmsAfterSearch() {
		List<Filmdata> films = new ArrayList<Filmdata>();
		WebDriverWait wait = new WebDriverWait(manager.driver,5 );		
		List<WebElement> allFilmsTitles = manager.driver.findElements(By.cssSelector("div#container div.title"));
		
		WebElement webElement = manager.driver.findElement(By.id("q"));
	    webElement.sendKeys(Keys.TAB);
	    webElement.sendKeys(Keys.ENTER);
			
		wait.until(ExpectedConditions.stalenessOf(manager.driver.findElement(By.cssSelector("div#container div.title"))));
		
		allFilmsTitles = manager.driver.findElements(By.cssSelector("div#container div.title"));
		
		
		for (WebElement element : allFilmsTitles) {
			allFilmsTitles = manager.driver.findElements(By.cssSelector("div#container div.title"));
			for(int i=0; i<4;i++) 
				try { manager.driver.findElements(By.cssSelector("div#container div.title"));
				break; 
				} catch(StaleElementReferenceException e) 
				{ e.toString(); System.out.println("catching staleness :" + e.getMessage()); } 
			
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div#container div.title")));
			
			Filmdata film = new Filmdata();
			film.filmTitle = element.getText();
			films.add(film);		
	
				}	
		
		return films;
	}


	public void validateSearchResults() {
			
			WebDriverWait wait = new WebDriverWait(manager.driver,30 );
			List<WebElement> allFilmsTitles = manager.driver.findElements(By.cssSelector("div#container div.title")); 		
					
				if (allFilmsTitles == null || allFilmsTitles.size() < 1) {
					String element = manager.driver.findElement(By.cssSelector("div#results div.content")).getText();
					if(!element.contains("No movies where found.")){
					System.out.println("No coincidence in the whole list found");	
					}
					
				} else {
					for (WebElement element : allFilmsTitles) {
						wait.until(ExpectedConditions.visibilityOfAllElements(allFilmsTitles));
						wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div#container div.title")));

						if (element.getText().equals(firstTitle)) {
							System.out.println("Film found");
							break;
						} 
							}
				}
				
	}

	public void getLoginDataFromFile() {
		File file = new File("application.properties");
		
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		manager.driver.findElement(By.id("username")).clear();
		manager.driver.findElement(By.id("username")).sendKeys(prop.getProperty("username"));
	    manager.driver.findElement(By.name("password")).clear();
	    manager.driver.findElement(By.name("password")).sendKeys(prop.getProperty("password"));
		
	}

	public void submitLogin() {
		manager.driver.findElement(By.name("submit")).click();
	}

}
