package controller.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

import model.Document;
import model.DocumentManager;
import view.LatexEditorView;

public class LoadCommand implements Command {
	private LatexEditorView latexEditorView=LatexEditorView.getInstance();
	private DocumentManager documentManager=DocumentManager.getInstance();
	@Override
	public void execute() {
		
		String fileContents=documentManager.readDocument(latexEditorView.getFilename());
		latexEditorView.setCurrentDocument(new Document());
		latexEditorView.getCurrentDocument().setContents(fileContents);
		String type =documentManager.findDocumentTemplate(fileContents.trim());
		latexEditorView.setType(type);

	}
	


}
