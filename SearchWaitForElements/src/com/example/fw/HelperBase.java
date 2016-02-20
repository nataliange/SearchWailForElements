package com.example.fw;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;

import com.example.tests.TestBase;

public abstract class HelperBase {
	
	protected ApplicationManager manager; 
	
	public HelperBase(ApplicationManager manager) {
		this.manager = manager;
	}
	
	public String closeAlertAndGetItsText(TestBase testBase) {
	    try {
	      Alert alert = manager.driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (manager.acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	    	manager.acceptNextAlert = true;
	    }
	  }

	public boolean isAlertPresent(TestBase testBase) {
	    try {
	    	manager.driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	public boolean isElementPresent(TestBase testBase, By by) {
	    try {
	    	manager.driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }
	
	public void type(By locator, String text) {
		manager.driver.findElement(locator).clear();
		manager.driver.findElement(locator).sendKeys(text);
	}

}
