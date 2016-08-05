package io.toast.tk.test.runtime;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.toast.tk.core.adapter.ActionAdapterKind;
import io.toast.tk.dao.domain.impl.test.block.ITestPage;
import io.toast.tk.dao.domain.impl.test.block.TestBlock;
import io.toast.tk.dao.domain.impl.test.block.line.TestLine;
import io.toast.tk.runtime.bean.TestLineDescriptor;
import io.toast.tk.runtime.parse.TestParser;

public class TestParserTestCase_1 {

	static StringBuilder scenario = new StringBuilder();

	@BeforeClass
	public static void init() {
		scenario.append("|| scenario || swing ||").append("\n");
		scenario.append("| @swing Saisir *valeur* dans *ChooseApplicationRusDialog.applicationBox* |").append("\n");
		scenario.append("| @service Cliquer sur *ChooseApplicationRusDialog.OK* |").append("\n");
		scenario.append("| @service Cliquer sur *ChooseApplicationRusDialog.FIN* |").append("\n");
		scenario.append("| @toto Cliquer sur *ChooseApplicationRusDialog.FIN* |").append("\n");
		scenario.append("| Cliquer sur *ChooseApplicationRusDialog.KO* |").append("\n");
		scenario.append("| @swing:connector Saisir *valeur* dans *ChooseApplicationRusDialog.applicationBox* |").append("\n");
	}

	@Test
	public void testParserBlocks() throws IllegalArgumentException, IOException {
		TestParser par = new TestParser();
		ITestPage testPage = par.readString(scenario.toString(), null);
		assertEquals(1, testPage.getBlocks().size());
	}

	@Test
	public void testParserBlockType() throws IllegalArgumentException, IOException {
		TestParser par = new TestParser();
		ITestPage testPage = par.readString(scenario.toString(), null);
		assertEquals(true, testPage.getBlocks().get(0) instanceof TestBlock);
	}

	@Test
	public void testParserBlockServiceNameParsing() throws IllegalArgumentException, IOException {
		TestParser par = new TestParser();
		ITestPage testPage = par.readString(scenario.toString(), null);
		TestBlock testBlock = (TestBlock) testPage.getBlocks().get(0);
		assertEquals("swing", testBlock.getFixtureName());
	}

	@Test
	public void testDefaultParserLineFixtureKind() throws IllegalArgumentException, IOException {
		TestParser par = new TestParser();
		ITestPage testPage = par.readString(scenario.toString(), null);
		TestBlock testBlock = (TestBlock) testPage.getBlocks().get(0);
		List<TestLine> blockLines = testBlock.getBlockLines();
		TestLine testLine = blockLines.get(4);
		assertEquals("Cliquer sur *ChooseApplicationRusDialog.KO*", testLine.getTest());
		TestLineDescriptor descriptor = new TestLineDescriptor(testBlock, testLine);
		assertEquals(ActionAdapterKind.swing, descriptor.getTestLineFixtureKind());
	}

	@Test
	public void testServiceParserLineFixtureKind() throws IllegalArgumentException, IOException {
		TestParser par = new TestParser();
		ITestPage testPage = par.readString(scenario.toString(), null);
		TestBlock testBlock = (TestBlock) testPage.getBlocks().get(0);
		List<TestLine> blockLines = testBlock.getBlockLines();
		TestLine testLine = blockLines.get(1);
		assertEquals("@service Cliquer sur *ChooseApplicationRusDialog.OK*", testLine.getTest());
		TestLineDescriptor descriptor = new TestLineDescriptor(testBlock, testLine);
		assertEquals(ActionAdapterKind.service, descriptor.getTestLineFixtureKind());
	}

	@Test
	public void testSwingParserLineFixtureKind() throws IllegalArgumentException, IOException {
		TestParser par = new TestParser();
		ITestPage testPage = par.readString(scenario.toString(), null);
		TestBlock testBlock = (TestBlock) testPage.getBlocks().get(0);
		List<TestLine> blockLines = testBlock.getBlockLines();
		assertEquals("@swing Saisir *valeur* dans *ChooseApplicationRusDialog.applicationBox*", blockLines.get(0)
			.getTest());
		TestLineDescriptor descriptor = new TestLineDescriptor(testBlock, blockLines.get(0));
		assertEquals("", descriptor.getTestLineFixtureName());
	}

	@Test
	public void testSwingParserLineFixtureName() throws IllegalArgumentException, IOException {
		TestParser par = new TestParser();
		ITestPage testPage = par.readString(scenario.toString(), null);
		TestBlock testBlock = (TestBlock) testPage.getBlocks().get(0);
		List<TestLine> blockLines = testBlock.getBlockLines();
		assertEquals("@swing:connector Saisir *valeur* dans *ChooseApplicationRusDialog.applicationBox*", 
				blockLines.get(5).getTest());
		TestLineDescriptor descriptor = new TestLineDescriptor(testBlock, blockLines.get(5));
		assertEquals(ActionAdapterKind.swing, descriptor.getTestLineFixtureKind());
		assertEquals("connector", descriptor.getTestLineFixtureName());
	}


	@AfterClass
	public static void end() {
	}
}
