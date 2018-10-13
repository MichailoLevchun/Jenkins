package com.epam.lab.gmail.pages.enums;

public enum Box {
	RUBBISH(".//*[@act='9']"),INBOX(".//*[@act='8']");
	
	private String xpath;
	
	private Box(String selector) {
		setSelector(selector);
	}

	public String getSelector() {
		return xpath;
	}

	private void setSelector(String selector) {
		this.xpath = selector;
	}

}
