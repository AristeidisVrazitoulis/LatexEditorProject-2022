package test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import controller.commands.CreateCommand;
import model.Document;
import model.DocumentManager;
import model.VersionsManager;
import view.LatexEditorView;

public class CreateDocumentTest {
	
	private static DocumentManager documentManager;
	private static VersionsManager versionsManager;
	private static LatexEditorView editorView;
	
	@BeforeAll
	public static final void setUp() {
		editorView = new LatexEditorView();
		documentManager = new DocumentManager();
		versionsManager = new VersionsManager();
		
	}
	
	
	@ParameterizedTest
	@ValueSource(strings = {"bookTemplate", "reportTemplate", "articleTemplate", "letterTemplate"})
	public final void testCreateDocument2(String type) {
		Document document = documentManager.createDocument(type);
		versionsManager.setCurrentVersion(document);
		assertEquals(documentManager.getContents(type), document.getContents());
		
	}
	
	
}
