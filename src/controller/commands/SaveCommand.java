package controller.commands;


import model.Document;
import view.LatexEditorView;

public class SaveCommand implements Command {
	@Override
	public void execute() {
		Document document = LatexEditorView.getInstance().getCurrentDocument();
		document.setContents(LatexEditorView.getInstance().getText());
		String filename = LatexEditorView.getInstance().getFilename();
		
		String suffix = filename.split("\\.")[1];
		if(suffix.equals("html"))
		{
			document.saveHTML(filename);
		}else {
			document.save(filename);
		}
	}

}
