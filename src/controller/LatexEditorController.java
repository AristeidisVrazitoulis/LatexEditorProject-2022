package controller;

import java.util.HashMap;

import controller.commands.AddLatexCommand;
import controller.commands.ChangeVersionsStrategyCommand;
import controller.commands.Command;
import controller.commands.CommandFactory;
import controller.commands.CreateCommand;
import controller.commands.DisableVersionsManagementCommand;
import controller.commands.EditCommand;
import controller.commands.EnableVersionsManagementCommand;
import controller.commands.LoadCommand;
import controller.commands.RollbackToPreviousVersionCommand;
import controller.commands.SaveCommand;
import model.VersionsManager;

public class LatexEditorController{
	private HashMap<String, Command> commands;
	private String [] commandsNames={"addLatex","changeVersionsStrategy","create","disableVersionsManagement","edit",
			"enableVersionsManagement","load","rollbackToPreviousVersion","save"};
	
	public LatexEditorController(VersionsManager versionsManager) {
		CommandFactory commandFactory = new CommandFactory(versionsManager);
		commands = new HashMap<String, Command>(); 
		
		for(String command:commandsNames) {
			commands.put(command, commandFactory.createCommand(command));
		}
		
		
	}
	
	public void enact(String command) {
		commands.get(command).execute();
	}
	
	public Command getCommand(String commandName) {
		return commands.get(commandName);
	}
}
