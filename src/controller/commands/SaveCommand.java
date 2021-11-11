package controller.commands;

import model.VersionsManager;

public class SaveCommand implements Command {
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		VersionsManager.getInstance().saveToFile();
	}

}
