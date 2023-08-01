package com.orange.qa.util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer {

	// create counter for retry attempts
	int countForRetryAttempts = 0;

	// set max number of retry attempts
	int maxRetryAttempts = 3;

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub

		if (!result.isSuccess()) {
			if (countForRetryAttempts < maxRetryAttempts) {
				countForRetryAttempts++;
				return true;
			}
		}

		return false;
	}

}
