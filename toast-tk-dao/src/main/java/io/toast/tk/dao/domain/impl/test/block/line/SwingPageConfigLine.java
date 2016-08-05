package io.toast.tk.dao.domain.impl.test.block.line;

import com.github.jmkgreen.morphia.annotations.Embedded;
import com.github.jmkgreen.morphia.annotations.Entity;

import io.toast.tk.dao.core.report.TestResult;

@Entity(value = "test", noClassnameStored = true)
@Embedded
public class SwingPageConfigLine {

	private String elementName;

	private String type;

	private String locator;

	private TestResult result;

	public TestResult getTestResult() {
		return result;
	}

	public SwingPageConfigLine(
		final String elementName,
		final String type,
		final String locator
	) {
		this.elementName = elementName;
		this.type = type;
		this.locator = locator;
	}

	public SwingPageConfigLine() {

	}

	/**
	 * @param result
	 */
	public void setResult(final TestResult result) {
		this.result = result;
	}

	public String getElementName() {
		return elementName;
	}

	public String getType() {
		return type;
	}

	public String getLocator() {
		return locator;
	}

	public TestResult getResult() {
		return result;
	}

	public void setElementName(final String elementName) {
		this.elementName = elementName;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public void setLocator(final String locator) {
		this.locator = locator;
	}
}