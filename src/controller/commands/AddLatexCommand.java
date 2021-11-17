package controller.commands;


import model.Document;
import model.VersionsManager;
import view.LatexEditorView;

public class AddLatexCommand implements Command  {
	
	@Override
	public void execute() {
//		// TODO Auto-generated method stub
//		VersionsManager versionsManager=VersionsManager.getInstance();
//		Document currentDocument=LatexEditorView.getInstance().getCurrentDocument();
//		// Edit Command, saves to RAM
//		if(versionsManager.isEnabled()) {
//			versionsManager.putVersion(currentDocument);
//			currentDocument.changeVersion();
//		}
//		currentDocument.setContents(LatexEditorView.getInstance().getText());
//		LatexEditorView.getInstance().saveContents();
		
		//Avoid duplicate code
		EditCommand edit = new EditCommand();
		edit.execute();
		
	}
}
