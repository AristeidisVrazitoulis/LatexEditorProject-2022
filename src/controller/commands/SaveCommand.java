package controller.commands;


import view.LatexEditorView;

public class SaveCommand implements Command {
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		LatexEditorView.getInstance().saveToFile();
	}

}
