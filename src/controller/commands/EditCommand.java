package controller.commands;

import model.VersionsManager;

public class EditCommand implements Command {

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		VersionsManager.getInstance().saveContents();
	}

}
