package controller.commands;

import model.VersionsManager;

public class RollbackToPreviousVersionCommand implements Command {
	@Override
	public void execute() {
		VersionsManager.getInstance().rollback();
	}

}
