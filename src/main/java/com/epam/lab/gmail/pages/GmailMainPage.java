package com.epam.lab.gmail.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.lab.gmail.elements.Button;
import com.epam.lab.gmail.utils.DriverManager;

public class GmailMainPage {
	private static Logger LOG = Logger.getLogger(GmailMainPage.class);
	private EditMenu topEditMenu;
	private NavigationMenu navigationMenu;

	@FindBy(css = "tr.zA")
	private List<WebElement> messagesBox;
	
	

	public GmailMainPage() {
		LOG.debug("GmailMainPage");

		PageFactory.initElements(DriverManager.getInstance(), this);
		navigationMenu = new NavigationMenu();
		topEditMenu = new EditMenu();
	}

	public NavigationMenu navigationMenu() {
		return navigationMenu;
	}

	public EditMenu topEditMenu() {
		return topEditMenu;
	}

	public List<MessageWidget> getMessagesWidgets() {
		LOG.debug("getMessagesModels");
		List<MessageWidget> messageWidgets = new ArrayList<>(messagesBox.size());
		for (WebElement element : messagesBox) {
			messageWidgets.add(new MessageWidget(element));
		}
		return messageWidgets;
	}
	
	public void clickUndo() {
		Button undoButton = new Button(DriverManager.getInstance().findElement(By.xpath("//*[@id='link_undo']")));
		undoButton.click();
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
