package controller.commands;


import model.Document;
import view.LatexEditorView;

public class SaveCommand implements Command {
	@Override
	public void execute() {
		Document document = LatexEditorView.getInstance().getCurrentDocument();
		document.setContents(LatexEditorView.getInstance().getText());
		document.save(LatexEditorView.getInstance().getFilename());
	}

}
