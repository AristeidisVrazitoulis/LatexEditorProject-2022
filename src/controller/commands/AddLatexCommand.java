package controller.commands;


import view.LatexEditorView;

public class AddLatexCommand implements Command  {
	
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		LatexEditorView.getInstance().saveContents();
	}

}
