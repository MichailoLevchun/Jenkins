package com.epam.lab.gmail.bisnes_objects;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.lab.gmail.models.Message;
import com.epam.lab.gmail.pages.GmailMainPage;
import com.epam.lab.gmail.pages.MessageWidget;
import com.epam.lab.gmail.pages.enums.Box;

public class GmailBO {
	public static Logger LOG = Logger.getLogger(GmailBO.class);
	private GmailMainPage mainPage;

	public GmailBO() {
		mainPage = new GmailMainPage();
	}

	public List<Message> getMessageModels() {
		LOG.debug("getMessages method");
		List<Message> messageList = new ArrayList<>();
		for (MessageWidget messWidget : mainPage.getMessagesWidgets()) {
			messageList.add(getMessage(messWidget));
		}
		return messageList;
	}

	public List<Message> markMessagesAsImportant(int messagesToMurkNumber) {
		LOG.debug("markMessagesAsImportant method");
		List<Message> markedMessagesList = new ArrayList<>();
		int markedMessagesNumber = 0;
		for (MessageWidget messageWidget : mainPage.getMessagesWidgets()) {
			if (messageWidget.isNotImportant()) {
				messageWidget.clickOnImportantMarker();
				markedMessagesList.add(getMessage(messageWidget));
				markedMessagesNumber++;
			}
			if (markedMessagesNumber >= messagesToMurkNumber) {
				break;
			}
		}
		return markedMessagesList;
	}
	
	public void markMessagesAsNotImportant(List<Message> messageList) {
		LOG.debug("markMessagesAsNotImportant method");
		int markedMessagesNumber = 0;
		for (MessageWidget messageWidget : mainPage.getMessagesWidgets()) {
			System.out.println(messageList.contains((getMessage(messageWidget))));
			if (messageList.contains((getMessage(messageWidget)))) {
				messageWidget.clickOnImportantMarker();;
				markedMessagesNumber++;
			}
			if (markedMessagesNumber >= messageList.size()) {
				break;
			}
		}
	}

	public void markMessages(int number) {
		LOG.debug("markMessages metod");

		List<MessageWidget> messageWidgets = mainPage.getMessagesWidgets();
		for (int i = 0; i < number && i < messageWidgets.size(); i++) {
			LOG.info("inside cycle".toUpperCase());
			messageWidgets.get(i).clickOnMarker();
		}
	}

	public void openImportantMesssagesList() {
		LOG.debug("openImportantMesssagesList metod");
		mainPage.navigationMenu().clikOnImportant();
	}

	public void openBinMesssagesList() {
		LOG.debug("openBinMesssagesList metod");
		mainPage.navigationMenu().clickOnBin();
		mainPage = new GmailMainPage();

	}

	public void moveToBox(Box box) {
		mainPage.topEditMenu().clickMoveTo(box);
	}



	public void deleteMessages(List<Message> listToDelete) {
		LOG.debug("deleteMessages method");
		int markedMessagesNumber = 0;
		for (MessageWidget messWidget : mainPage.getMessagesWidgets()) {
			if (listToDelete.contains(getMessage(messWidget))) {
				messWidget.clickOnMarker();
				markedMessagesNumber++;
			}
			if (markedMessagesNumber >= listToDelete.size()) {
				break;
			}
		}
		mainPage.topEditMenu().clickDelete();
	}

	public boolean arePresent(List<Message> messagesList) {
		LOG.debug("arePresent method");
		List<Message> presentMessagesList = getMessageModels();
		boolean isPresent = false;
		for (Message message : messagesList) {
			isPresent = presentMessagesList.contains(message);
			if (isPresent) {
				break;
			}
		}
		return isPresent;
	}
	
	public void undoOperation() {
		mainPage.clickUndo();
		mainPage = new GmailMainPage();
	}

	private Message getMessage(MessageWidget widget) {
		LOG.debug("getMessage method");
		return new Message(widget.getSender().trim(), widget.getTopic().trim(), widget.getDate().trim());
	}

}
