package controller.commands;

import model.Document;
import model.DocumentManager;
import model.VersionsManager;

public class CreateCommand implements Command {
	private DocumentManager documentManager;
	
	public CreateCommand(DocumentManager documentManager) {
		this.documentManager = documentManager;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		VersionsManager versionsManager=VersionsManager.getInstance();
		String type =  versionsManager.getType();
		Document document = documentManager.createDocument(type);
		versionsManager.setCurrentVersion(document);
	}

}
