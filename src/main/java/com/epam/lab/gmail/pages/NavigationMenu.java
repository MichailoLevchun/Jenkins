package com.epam.lab.gmail.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.lab.gmail.decorator.ElementDecorator;
import com.epam.lab.gmail.elements.Button;
import com.epam.lab.gmail.utils.DriverManager;

public class NavigationMenu {
	private static Logger LOG = Logger.getLogger(NavigationMenu.class);

	@FindBy
	private Button inboxItem;

	@FindBy(css = "span.CJ")
	private Button moreItem;

	@FindBy(css = "div.TN.GLujEb.aHS-bns")
	private Button importantItem;
	
	@FindBy(css = "div.TN.GLujEb.aHS-bnx")
	private Button binItem;

	public NavigationMenu() {
		LOG.debug("NavigationMenu constructor");
		PageFactory.initElements(new ElementDecorator(DriverManager.getInstance()), this);
	}

	public void clikOnImportant() {
		LOG.debug("clikOnImportant method");
		if(!importantItem.isDisplayed()) {
			moreItem.click();
		}
		importantItem.click();
		waitUtilBoxLoaded();
	}

	public void clikOnMore() {
		LOG.debug("clikOnMore method");
		moreItem.click();
	}
	
	public void clickOnBin() {
		LOG.debug("clickOnBin method");
		if(!binItem.isDisplayed()) {
			moreItem.click();
		}
		binItem.actionClick();
		waitUtilBoxLoaded();
	}

	private void waitUtilBoxLoaded() {
		LOG.debug("waitUntilBoxLoaded method");
		try {
			new WebDriverWait(DriverManager.getInstance(), 3)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//div[@class='vY']")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		new WebDriverWait(DriverManager.getInstance(), 10)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//div[@class='vY nq']")));

	}

}
