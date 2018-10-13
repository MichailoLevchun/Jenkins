package com.epam.lab.gmail;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.uncommons.reportng.HTMLReporter;

import com.epam.lab.gmail.bisnes_objects.GmailBO;
import com.epam.lab.gmail.bisnes_objects.LoginBO;
import com.epam.lab.gmail.listeners.LoggerListener;
import com.epam.lab.gmail.models.Message;
import com.epam.lab.gmail.models.User;
import com.epam.lab.gmail.providers.CustomDataProvider;
import com.epam.lab.gmail.utils.DriverManager;
import com.epam.lab.gmail.utils.DriverPropertisLoader;

@Listeners({LoggerListener.class,HTMLReporter.class})
public class GmailTestNegative {
	public static final String PROPERTIES_FILE_URL = "resources/driver_config.properties";
	public static User user;

	public static Logger LOG = Logger.getLogger(GmailTestNegative.class);

	@BeforeClass
	public void setUp() throws Exception {
		LOG.info("setUp");
		DriverPropertisLoader.load(PROPERTIES_FILE_URL);
	}

	@Test(dataProviderClass = CustomDataProvider.class, dataProvider = "getUsers")
	public void markAndDelete(User user) {

		LOG.info(user.toString());

		LoginBO loginBO = new LoginBO();
		
		loginBO.loginAs(user);

		GmailBO gmailBo = new GmailBO();

		List<Message> markedMessagesList = gmailBo.markMessagesAsImportant(2);
		
		LOG.info("Marked Messages" + markedMessagesList.toString());

		assertNotNull(markedMessagesList);

		gmailBo.openImportantMesssagesList();

		gmailBo.deleteMessages(markedMessagesList);

		assertTrue(gmailBo.arePresent(markedMessagesList));
		
		gmailBo.undoOperation();
		
		gmailBo.markMessagesAsNotImportant(markedMessagesList);

	}

	@AfterMethod
	public void after() {
		DriverManager.closeDriver();
	}

}
