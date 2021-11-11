package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import controller.commands.CreateCommand;
import controller.commands.EditCommand;
import model.Document;
import model.DocumentManager;
import model.VersionsManager;
import view.LatexEditorView;

class CommandsTest {
	
	private static DocumentManager documentManager;
	private static VersionsManager versionsManager;
	private static LatexEditorView editorView;
	// Holds all types of documents
	private static Document[] documents = new Document[4];
		
	// Before each test we create once all 4 document types
	@BeforeAll
	public static void initializeDocuments() {
		String[] types  = {"bookTemplate", "reportTemplate", "articleTemplate", "letterTemplate"};
		
		editorView = LatexEditorView.getInstance();
		documentManager = new DocumentManager();
		versionsManager = new VersionsManager();
		
		editorView.setVersionsManager(versionsManager);
		
		for(int i = 0; i < documents.length; i++) {
			Document document = documentManager.createDocument(types[i]);
			versionsManager.setCurrentVersion(document);
			documents[i] = document;
			System.out.println(types[i]);
		}
		
	}
	
	
	// Appends text for a change
	// Test for every type of document
	@ParameterizedTest
	@ValueSource(ints = {0,1,2,3})
	public final void  testEditContent(int i) {
		// Randomly choose book template for testing. Works for other types as well.
		Document sampleDocument = documents[i];
		versionsManager.setCurrentVersion(sampleDocument);
				
		String smallChange = "this is a small change";
		String actualOutput = sampleDocument.getContents() + smallChange;
				
		EditCommand editCommand = new EditCommand();	
		editorView.setText(actualOutput);
		editCommand.execute();
				
		assertEquals(actualOutput, sampleDocument.getContents());
	}
	
	

	
	
	// Test Load Document Command
	@ParameterizedTest
	@ValueSource(ints = {0,1,2,3,4,5})
	public final void testLoadDocument(int i) {
		String filename = i+".tex";
		editorView.setFilename(filename);
		
		// execute load
		versionsManager.loadFromFile();
				
		assertEquals(editorView.getCurrentDocument().getContents(), readFileContents(filename));	
		
	}
	
	
	// Test Save Document Command
	@Test
	public final void testSaveDocument() {
		
		String filename = "book.tex";
		
		// Book
		Document book = documents[0];
		versionsManager.setCurrentVersion(book);
		
		editorView.setFilename(filename);
		
		versionsManager.saveToFile();				
		assertEquals(editorView.getCurrentDocument().getContents(), readFileContents(filename));	
		
	}
	
	
	
	
	// helper method
	private String readFileContents(String filePath) 
	{
		String fileContents = "";
		try 
		{
			Scanner scanner = new Scanner(new FileInputStream(filePath));
			while(scanner.hasNextLine()) {
				fileContents = fileContents + scanner.nextLine() + "\n";
			}
			fileContents = fileContents.substring(0,fileContents.length()-1);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		return fileContents;
	}
	
	
	
}
