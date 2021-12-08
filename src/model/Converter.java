package model;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Converter {
	
	private HashMap<String,String> texCommandstoHtml; 
	private String [] texCommands= {"chapter","section","subsection","subsubsection",
			"paragraph", "par","begin{description}", "begin{enumerate}", "begin{itemize}","item",
			"emph","title"};
	
	private String[] htmlCommands = {"<h1>","<h2>","<h3>","<h4>","<h5>","<p>","<dl>","<ol>","<ul>","<li>","<em>","<title>"};
 	
	
	public Converter() {
		texCommandstoHtml = new HashMap<String,String>();
		
		for(int i = 0; i < htmlCommands.length; i++)
		{
			texCommandstoHtml.put(texCommands[i], htmlCommands[i]);
		}
	}
	
	
	private String getClosingTag(String tag) {
		String s = ""+tag.charAt(0)+"/"+tag.substring(1);
		return s; 
	}
	
	private String[] extractCommand(String line) {
		String [] commandAndParameter=new String[2];
		String command="";
		String parameter="";
		boolean bracketFound=false;
		for (int i=1;i<line.length();i++) {
			if(line.charAt(i)=='{') 
			{
				bracketFound=true;
			}
			else  if(line.charAt(i)=='}') {
				break;
			}
			else  if(bracketFound)
			{	
				parameter+=line.charAt(i);	
				
			}
		
			else {
				command+=line.charAt(i);	
			}
				
		}
		
		commandAndParameter[0]=command;
		commandAndParameter[1]=parameter;
		return commandAndParameter;
	}
	
	private boolean checkIfCommandExists(String command) {
		for(String texCommand:texCommands) {
			if(texCommand.equals(command)) {
				return true;
			}
		}
		return false;
	}
	
	public String convertToHtml(String text) {
		String contents = "";
		String[] lines = text.split("\n");
		String fullCommand;
		String currentCommand;
		String htmlElement;
		boolean closeNow;
		for(String line: lines) {
			line = line.strip();
			closeNow = true;
			if(line.equals("")) {continue;}
			if(line.charAt(0)!='\\') {
				contents+=line;
				continue;
			}
			// /section{text} -> ["section","text"]
			String [] commandAndparameter= extractCommand(line);
			fullCommand =  commandAndparameter[0];
			//check if should close
			if(fullCommand.equals("end")) {
				fullCommand = "begin"+"{"+commandAndparameter[1]+"}";
				if(!checkIfCommandExists(fullCommand)) {continue;}
				//System.out.println(fullCommand);
				htmlElement= texCommandstoHtml.get(fullCommand);
				contents += getClosingTag(htmlElement);;
				continue;
			}
			if(fullCommand.equals("begin")) {
				fullCommand += "{"+commandAndparameter[1]+"}";
				closeNow = false;
			}
			
			if(!checkIfCommandExists(fullCommand)) {continue;}
			// make the conversion
			contents+=texCommandstoHtml.get(fullCommand);
			if(commandAndparameter[1]!="") {
				contents+=commandAndparameter[1];
			}
			// add text
			if(closeNow) {
				contents += commandAndparameter[1];
				
				htmlElement= texCommandstoHtml.get(fullCommand);
				contents += getClosingTag(htmlElement);;
			}
			
			contents+="\n";
		}	
		
		return contents;
	}
	
	
	


}
