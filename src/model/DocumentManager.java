package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class DocumentManager {
	private HashMap<String, Document> templates;
	private String[] templateNames= {"reportTemplate","bookTemplate","articleTemplate","letterTemplate"};
	
	private static DocumentManager instance = null; 
	// This constructor is called once at the initialization of the app
	public DocumentManager() {
		templates = new HashMap<String, Document>();
		initializeTemplates();
		initializeEmptyTemplate();
	}
	
	// Singleton Pattern
	public static DocumentManager getInstance() {
		if(instance == null) {
			instance = new DocumentManager();
		}
		return instance;
	}
	
	public void initializeTemplates() {
		for(int i=0;i<templateNames.length;i++) {
			Document document = new Document();
			document.setContents(getContents(templateNames[i]));
			templates.put(templateNames[i], document);
		}
	}
	
	public void initializeEmptyTemplate() {
		Document document = new Document();
		templates.put("emptyTemplate", document);
	}
	
	public String readDocument(String filename) {
		String fileContents = "";
		try {
			Scanner scanner = new Scanner(new FileInputStream(filename));
			while(scanner.hasNextLine()) {
				fileContents = fileContents + scanner.nextLine() + "\n";
			}
			fileContents = fileContents.substring(0,fileContents.length()-1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fileContents;
	}
	
	public Document createDocument(String type) {
		return templates.get(type).clone();
	}
	
	// finds document type in relation to file contents
	public String findDocumentTemplate(String fileContents) {
		String fileHeader=fileContents.split("\n\n")[0].trim();
		
		for(int i=0;i<templateNames.length;i++) {
			String templateHeader=getContents(templateNames[i]).split("\n\n")[0].trim();
			if(templateHeader.equals(fileHeader)) {
				return templateNames[i];
			}
		}
		return "emptyTemplate";
	}
	
	public String getContents(String type) {
		if(type.equals("articleTemplate")){
			return "\\documentclass[11pt,twocolumn,a4paper]{article}\n\n"+

					"\\begin{document}\n"+
					"\\title{Article: How to Structure a LaTeX Document}\n"+
					"\\author{Author1 \\and Author2 \\and ...}\n"+
					"\\date{\\today}\n\n"+

					"\\maketitle\n\n"+

					"\\section{Section Title 1}\n\n"+

					"\\section{Section Title 2}\n\n"+

					"\\section{Section Title.....}\n\n"+

					"\\section{Conclusion}\n\n"+

					"\\section*{References}\n\n"+

					"\\end{document}\n";
		}
		else if(type.equals("bookTemplate")) {
			return "\\documentclass[11pt,a4paper]{book}\n\n"+

					"\\begin{document}\n"+
					"\\title{Book: How to Structure a LaTeX Document}\n"+
					"\\author{Author1 \\and Author2 \\and ...}\n"+
					"\\date{\\today}\n\n"+

					"\\maketitle\n\n"+

					"\\frontmatter\n\n"+

					"\\chapter{Preface}\n"+
					"% ...\n\n"+

					"\\mainmatter\n"+
					"\\chapter{First chapter}\n"+
					"\\section{Section Title 1}\n"+
					"\\section{Section Title 2}\n\n"+

					"\\section{Section Title.....}\n\n"+

					"\\chapter{....}\n\n"+

					"\\chapter{Conclusion}\n\n"+

					"\\chapter*{References}\n\n\n"+


					"\\backmatter\n"+
					"\\chapter{Last note}\n\n"+

					"\\end{document}\n";
		}
		else if(type.equals("letterTemplate")) {
			return "\\documentclass{letter}\n"+
					"\\usepackage{hyperref}\n"+
					"\\signature{Sender's Name}\n"+
					"\\address{Sender's address...}\n"+
					"\\begin{document}\n\n"+

					"\\begin{letter}{Destination address....}\n"+
					"\\opening{Dear Sir or Madam:}\n\n"+

					"I am writing to you .......\n\n\n"+


					"\\closing{Yours Faithfully,}\n"+

					"\\ps\n\n"+

					"P.S. text .....\n\n"+

					"\\encl{Copyright permission form}\n\n"+

					"\\end{letter}\n"+
					"\\end{document}\n";
		}
		else {
			return "\\documentclass[11pt,a4paper]{report}\n\n"+

					"\\begin{document}\n"+
					"\\title{Report Template: How to Structure a LaTeX Document}\n"+
					"\\author{Author1 \\and Author2 \\and ...}\n"+
					"\\date{\\today}\n"+
					"\\maketitle\n\n"+

					"\\begin{abstract}\n"+
					"Your abstract goes here...\n"+
					"...\n"+
					"\\end{abstract}\n\n"+

					"\\chapter{Introduction}\n"+
					"\\section{Section Title 1}\n"+
					"\\section{Section Title 2}\n"+
					"\\section{Section Title.....}\n\n"+

					"\\chapter{....}\n\n"+

					"\\chapter{Conclusion}\n\n\n"+


					"\\chapter*{References}\n\n"+

					"\\end{document}\n";
		}
	}
}
