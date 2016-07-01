package com.synaptix.toast.dao.domain.impl.test.block.line;

import java.util.Objects;

import com.github.jmkgreen.morphia.annotations.Embedded;
import com.github.jmkgreen.morphia.annotations.Entity;
import com.synaptix.toast.core.report.TestResult;
import com.synaptix.toast.dao.domain.api.test.IRunnableTest;

@Entity(value = "test", noClassnameStored = true)
@Embedded
public class TestLine implements IRunnableTest {

	private String test;

	private String expected;

	@Embedded
	private TestResult testResult;

	/**
	 * Test comment
	 */
	private String comment;

	private long executionTime = 0;

	public TestLine() {
	}

	public TestLine(
		final String test,
		final String expected,
		final String comment
	) {
		this.test = test;
		this.expected = expected;
		this.comment = comment;
	}

	@Override
	public TestResult getTestResult() {
		return testResult;
	}

	@Override
	public void setTestResult(final TestResult testResult) {
		this.testResult = testResult;
	}

	public String getTest() {
		return test;
	}

	public void setTest(final String test) {
		this.test = test;
	}

	public String getExpected() {
		return expected;
	}

	public void setExpected(final String expected) {
		this.expected = expected;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(final String comment) {
		this.comment = comment;
	}

	@Override
	public long getExecutionTime() {
		return executionTime;
	}

	public void setExcutionTime(final long executionTime){
		this.executionTime = executionTime;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(test);
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
		      return true;
	    }
	    if (obj == null) {
	      return false;
	    }
		return obj.getClass() == this.getClass() ? Objects.equals(test, ((TestLine) obj).test) : false;
	}
}