package com.epam.lab.gmail.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.lab.gmail.decorator.ElementDecorator;
import com.epam.lab.gmail.elements.Button;
import com.epam.lab.gmail.pages.enums.Box;
import com.epam.lab.gmail.utils.DriverManager;

public class EditMenu {
	private static Logger LOG = Logger.getLogger(NavigationMenu.class);

	@FindBy(xpath = "//div[@class='ar9 T-I-J3 J-J5-Ji']")
	private List<Button> deleteButtons;

	@FindBy(css = "div.T-I.J-J5-Ji.T-I-Js-IF.ar7.ns.T-I-ax7.L3")
	private List<Button> moveToButtons;

	public EditMenu() {
		LOG.debug("TopEditMenu constructor");
		PageFactory.initElements(new ElementDecorator(DriverManager.getInstance()), this);
	}

	public void clickDelete() {
		LOG.debug("delete menthod");
		for (Button deleteButton : deleteButtons) {
			if (deleteButton.isDisplayed()) {
				deleteButton.click();
			}
		}
	}
	
	public void clickMoveTo(Box box) {
		for (Button moveTo : moveToButtons) {
			if (moveTo.isDisplayed()) {
				moveTo.click();
				Button button = new Button(DriverManager.getInstance().findElement(By.xpath(box.getSelector())));
				button.actionClick();
			}
		}
	}

}
