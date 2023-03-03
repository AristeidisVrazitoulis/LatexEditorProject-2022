package controller.commands;

import model.Document;
import model.DocumentManager;
import model.VersionsManager;
import view.LatexEditorView;

public class CreateCommand implements Command {
	private DocumentManager documentManager;
	private LatexEditorView latexEditorView=LatexEditorView.getInstance();
	public CreateCommand(DocumentManager documentManager) {
		this.documentManager = documentManager;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

		String type =  latexEditorView.getType();
		Document document = documentManager.createDocument(type);
		latexEditorView.setCurrentDocument(document);
	}

}
