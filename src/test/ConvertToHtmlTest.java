package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.Converter;
import model.Document;
import model.DocumentManager;
import model.VersionsManager;
import view.LatexEditorView;

class ConvertToHtmlTest {
	
	private static DocumentManager documentManager = DocumentManager.getInstance();
	private static VersionsManager versionsManager = VersionsManager.getInstance();
	private static LatexEditorView editorView=LatexEditorView.getInstance();
	private static Document document;
	
	@BeforeAll
	public static void initializeDocuments() {
		document = documentManager.createDocument("reportTemplate");
		editorView.setVersionsManager(versionsManager);
		editorView.setCurrentDocument(document);		
	}
	
	
	@Test
	public final void testHtmlConversion() {
		String targetText = "<title>Report Template: How to Structure a LaTeX Document</title>\n"
				+ "Your abstract goes here......<h1>Introduction</h1>\n"
				+ "<h2>Section Title 1</h2>\n"
				+ "<h2>Section Title 2</h2>\n"
				+ "<h2>Section Title.....</h2>\n"
				+ "<h1>....</h1>\n"
				+ "<h1>Conclusion</h1>\n";
		
		String latexText = document.getContents();
		Converter converter = new Converter();
		
		String convertedText = converter.convertToHtml(latexText);
		assertEquals(targetText, convertedText);
		
	}
}
