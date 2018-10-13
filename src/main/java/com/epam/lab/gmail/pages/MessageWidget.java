package com.epam.lab.gmail.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.lab.gmail.decorator.ElementDecorator;
import com.epam.lab.gmail.elements.Button;
import com.epam.lab.gmail.elements.Label;
import com.epam.lab.gmail.utils.DriverManager;

public class MessageWidget {
	private static Logger LOG = Logger.getLogger(NavigationMenu.class);

	public static final String NOT_IMPORTANT_MESSAGE_CLASS = "pH-A7 a9q";
	public static final String IMPORTANT_MESSAGE_ELEM = ".//*[@class='pH a9q']";

	private WebElement currentContext;

	@FindBy(xpath = ".//*[@class='pG']/div[2]")
	private Button importanceMarker;

	@FindBy(xpath = ".//td[@class='oZ-x3 xY']")
	private Button checkBox;

	@FindBy(xpath = ".//div[@class='yW']/span")
	private Label sender;

	@FindBy(xpath = ".//span[@class='bog']")
	private Label topic;

	@FindBy(xpath = ".//td[@class='xW xY ']/span")
	private Label date;

	public MessageWidget(WebElement elem) {
		LOG.debug("MessageWidget constructor");
		PageFactory.initElements(new ElementDecorator(elem), this);
		this.currentContext = elem;
	}

	public boolean isNotImportant() {
		LOG.debug("isNotImportant method");
		return importanceMarker.getAttribute("class").equals(NOT_IMPORTANT_MESSAGE_CLASS);
	}

	public void clickOnImportantMarker() {
		LOG.debug("clickOnImportantMarker method");
		importanceMarker.scriptClick();
		try {
			new WebDriverWait(DriverManager.getInstance(), 10).until(ExpectedConditions
					.presenceOfNestedElementLocatedBy(currentContext, By.xpath(IMPORTANT_MESSAGE_ELEM)));
		} catch (StaleElementReferenceException e) {
			LOG.info("handle");
		}
		
	}

	public void clickOnMarker() {
		LOG.debug("clickOnMarker method");
		checkBox.scriptClick();
	}

	public String getDate() {
		LOG.debug("getDate method");

		return date.getAttribute("aria-label");
	}

	public String getSender() {
		LOG.debug("getSender method");
		return sender.getContex();
	}

	public String getTopic() {
		LOG.debug("getTopic method");
		return topic.getContex();
	}

}
