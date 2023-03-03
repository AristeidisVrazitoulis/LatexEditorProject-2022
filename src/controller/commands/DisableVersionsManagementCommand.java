package controller.commands;

import model.VersionsManager;

public class DisableVersionsManagementCommand implements Command {
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		VersionsManager.getInstance().disable();
	}

}
