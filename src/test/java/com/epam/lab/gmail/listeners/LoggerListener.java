package com.epam.lab.gmail.listeners;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class LoggerListener implements ITestListener {
	public static Logger LOG = Logger.getLogger(LoggerListener.class);

	@Override
	public void onTestStart(ITestResult result) {
		LOG.info(String.format("Test : %s , Method : %s, Started", result.getName(), result.getMethod()));

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		LOG.info(String.format("Test : %s , Method : %s passed successful", result.getName(),
				result.getMethod()));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		LOG.warn(String.format("Test : %s , Method : %s failed in", result.getName(), result.getMethod()));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		LOG.info(String.format("Test : %s , Method : %s was skipped ", result.getName(), result.getMethod()));
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		LOG.warn(String.format("Test : %s , Method : %s failed  within success precentage: %s  ",
				result.getName(), result.getMethod(),result.getTestContext().getFailedButWithinSuccessPercentageTests().getAllResults().toString()));
	}

	@Override
	public void onStart(ITestContext context) {
		LOG.info(String.format("Starting tests : %s, Output directory : %s, Test Suite : %s",
				context.getName(), context.getOutputDirectory(), context.getSuite()));
	}

	@Override
	public void onFinish(ITestContext context) {
		LOG.info(String.format("Finish tests : %s, Output directory : %s, Test Suite : %s",
				context.getName(), context.getOutputDirectory(), context.getSuite()));
	}

}
