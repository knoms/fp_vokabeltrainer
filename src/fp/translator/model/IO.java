package fp.translator.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fp.translator.dic.Dictionary;
import fp.translator.errors.DictionaryException;

public class IO {
	File english = new File("/Users/noah/Documents/Translator/Übersetzer/english.txt");
	File german = new File("/Users/noah/Documents/Translator/Übersetzer/german.txt");
	File french = new File("/Users/noah/Documents/Translator/Übersetzer/french.txt");
	public Set<File> files= new HashSet<>();
	Dictionary dic = null;
	
	public IO(Dictionary dictionary) {
		this.importAllLanguages();
		dic= dictionary;
	}
	
	
		
	
	
	public void importAllLanguages() {	
	 File source = new File("/Users/noah/Documents/Translator/Übersetzer/source.txt");
	 try {
		FileReader fr = new FileReader(source);
		BufferedReader br = new BufferedReader(fr);
		String Line;
				 while((Line= br.readLine()) != null) files.add(new File("/Users/noah/Documents/Translator/Übersetzer/"+Line+".txt"));
				 
	//System.out.println(files.toString()); //test
	} catch (FileNotFoundException e) {
		throw new DictionaryException("Source File was not found");
	} catch (IOException e) {
		e.printStackTrace();
	}
	 
	 
	
	 
	}
	
	public void exportDictionaryToSourceFile() throws IOException {
		FileWriter out1 = new FileWriter(dic.getLanguage1());
		FileWriter out2 = new FileWriter(dic.getLanguage2());
	}
	

}
