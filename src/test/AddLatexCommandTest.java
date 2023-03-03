package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import controller.commands.AddLatexCommand;
import model.Document;
import model.DocumentManager;
import model.VersionsManager;
import view.LatexEditorView;

class AddLatexCommandTest {


	
	private static DocumentManager documentManager = DocumentManager.getInstance();
	private static LatexEditorView editorView=LatexEditorView.getInstance();
	
	
	private HashMap<String,String> mapCommands;	
	
	// Book test takes all commands
	@ParameterizedTest
	@ValueSource(strings = {"chapter","section","subsection","subsubsection","enumerate","itemize","table","figure"})
	public final void testBookCommands(String commandName) {
		Document book = documentManager.createDocument("bookTemplate");
		editorView.setCurrentDocument(book);
		int randomSplit = 20;
		
		AddLatexCommand addCommand = AddLatexCommand.getInstance();
		mapCommands = addCommand.getMap();
		
		String before = book.getContents().substring(0,randomSplit);
		String after = book.getContents().substring(randomSplit);
		String targetText = before + mapCommands.get(commandName) + after;
		
		addCommand.setType(commandName);
		addCommand.setBefore(before);
		addCommand.setAfter(after);
		addCommand.execute();
		assertEquals(targetText, editorView.getText());
		
	}
	
	
	// Article test
	@ParameterizedTest
	@ValueSource(strings = {"section","subsection","subsubsection","enumerate","itemize","table","figure"})
	public final void testArticleCommands(String commandName) {
		Document article = documentManager.createDocument("articleTemplate");
		editorView.setCurrentDocument(article);
		int randomSplit = 20;
		
		AddLatexCommand addCommand = AddLatexCommand.getInstance();
		mapCommands = addCommand.getMap();
		
		String before = article.getContents().substring(0,randomSplit);
		String after = article.getContents().substring(randomSplit);
		String targetText = before + mapCommands.get(commandName) + after;
		
		
		addCommand.setType(commandName);
		addCommand.setBefore(before);
		addCommand.setAfter(after);
		addCommand.execute();
		assertEquals(targetText, editorView.getText());
	}

	// takes all commands
	@ParameterizedTest
	@ValueSource(strings = {"chapter","section","subsection","subsubsection","enumerate","itemize","table","figure"})
	public final void testReportCommands(String commandName) {
		Document book = documentManager.createDocument("reportTemplate");
		editorView.setCurrentDocument(book);
		int randomSplit = 20;
		
		AddLatexCommand addCommand = AddLatexCommand.getInstance();
		mapCommands = addCommand.getMap();
		
		String before = book.getContents().substring(0,randomSplit);
		String after = book.getContents().substring(randomSplit);
		String targetText = before + mapCommands.get(commandName) + after;
		
		
		addCommand.setType(commandName);
		addCommand.setBefore(before);
		addCommand.setAfter(after);
		addCommand.execute();
		assertEquals(targetText, editorView.getText());
	}
	
	
	
	
	
	

}
