package controller.commands;


import model.Document;
import model.VersionsManager;
import view.LatexEditorView;

public class EditCommand implements Command {
	@Override
	public void execute() {
		VersionsManager versionsManager=VersionsManager.getInstance();
		Document currentDocument=LatexEditorView.getInstance().getCurrentDocument();
		// Edit Command, saves to RAM
		if(versionsManager.isEnabled()) {
			versionsManager.putVersion(currentDocument);
			currentDocument.changeVersion();
		}
		currentDocument.setContents(LatexEditorView.getInstance().getText());
	
	}

}
