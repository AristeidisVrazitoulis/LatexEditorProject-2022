package controller.commands;


import model.Document;
import model.VersionsManager;
import view.LatexEditorView;

public class EditCommand implements Command {
	@Override
	public void execute() {
		VersionsManager versionsManager = VersionsManager.getInstance();
		Document currentDocument = LatexEditorView.getInstance().getCurrentDocument();
		currentDocument.setContents(LatexEditorView.getInstance().getText());
		// Edit Command, saves to RAM
		if(versionsManager.isEnabled()) {
			versionsManager.putVersion(currentDocument);
			currentDocument.changeVersion();
		}
		
	
	}

}
