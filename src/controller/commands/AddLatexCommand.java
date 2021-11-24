package controller.commands;


import java.util.HashMap;

import model.Document;
import model.VersionsManager;
import view.LatexEditorView;

public class AddLatexCommand implements Command  {
	private static AddLatexCommand instance=null; 
	private LatexEditorView latexEditorView=LatexEditorView.getInstance();
	private String after;
	private String before;
	private String type;
	
	private HashMap<String, String> mapCommands = new HashMap<String, String>();
	
	private String[] commandNames = {"chapter","section","subsection","subsubsection","enumerate","itemize","table","figure"};
	private String[] commandValues = {"\n\\chapter{...}"+"\n", "\n\\section{...}"+"\n","\n\\subsection{...}"+"\n","\n\\subsubsection{...}"+"\n",
		"\\begin{enumerate}\n"+"\\item ...\n"+"\\item ...\n"+"\\end{enumerate}\n", "\\begin{itemize}\n"+
		"\\item ...\n"+
		"\\item ...\n"+
		"\\end{itemize}\n",
		
		"\\begin{table}\n"+
		"\\caption{....}\\label{...}\n"+
		"\\begin{tabular}{|c|c|c|}\n"+
		"\\hline\n"+
		"... &...&...\\\\\n"+
		"... &...&...\\\\\n"+
		"... &...&...\\\\\n"+
		"\\hline\n"+
		"\\end{tabular}\n"+
		"\\end{table}\n",
		
		"\\begin{figure}\n"+
		"\\includegraphics[width=...,height=...]{...}\n"+
		"\\caption{....}\\label{...}\n"+
		"\\end{figure}\n"};
	
	public AddLatexCommand() {
		// Initiate HashMap
		for(int i=0;i<commandNames.length;i++) {
			mapCommands.put(commandNames[i], commandValues[i]);
		}
	}
	
	@Override
	public void execute() {
		String contents=before +mapCommands.get(type)+after;	
		latexEditorView.setText(contents);
	}
	
	public void setBefore(String text) {
		this.before=text;
	}
	
	public void setAfter(String text) {
		this.after=text;
	}
	
	public void setType(String type) {
		this.type=type;
	}
	// Singleton pattern
	public static AddLatexCommand getInstance() {
		if(instance==null) {
			instance=new AddLatexCommand();
		}
		return instance;
	}
	
	public HashMap<String, String> getMap(){
		return mapCommands;
	}
	
}
