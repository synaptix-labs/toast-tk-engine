package com.synaptix.toast.runtime;

public class TestClass {

	public TestClass() {
		String test = null;
		int length = test.length(); // sonar preview should see this error and fail the build

	}

}

