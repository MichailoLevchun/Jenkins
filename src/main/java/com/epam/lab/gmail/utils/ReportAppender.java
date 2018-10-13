package com.epam.lab.gmail.utils;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.Reporter;

public class ReportAppender extends AppenderSkeleton {

	@Override
	protected void append(final LoggingEvent event) {
		Reporter.log(eventToString(event));
	}

	private String eventToString(final LoggingEvent event) {
		final StringBuilder buff = new StringBuilder(layout.format(event));

		if (layout.ignoresThrowable()) {
			final String[] s = event.getThrowableStrRep();
			if (s != null) {
				for (final String value : s) {
					buff.append(value).append(Layout.LINE_SEP);
				}
			}
		}

		return buff.toString();
	}

	@Override
	public void close() {

	}

	@Override
	public boolean requiresLayout() {
		return true;
	}
}
