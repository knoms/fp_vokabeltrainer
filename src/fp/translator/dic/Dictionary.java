package fp.translator.dic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import fp.translator.errors.DictionaryException;
import fp.translator.model.IO;

public class Dictionary {
	public Map<String, String> dictionary = new HashMap<>();
	String language1=null;
	String language2=null;
	public int size;
	public Set<File> files= new HashSet<>();
	

	
	
	/*File source = new File("/Users/noah/Documents/Translator/Übersetzer/source.txt");
	File english = new File("/Users/noah/Documents/Translator/Übersetzer/english.txt");
	File german = new File("/Users/noah/Documents/Translator/Übersetzer/german.txt");
	File french = new File("/Users/noah/Documents/Translator/Übersetzer/french.txt");*/
	


	public Dictionary() {
		
		try {
			size=0;
			this.fill();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	private void fill() throws IOException  {
		this.language1=("Deutsch");
		this.language2=("English");
		addNewVocabulary("Hund", "dog");
		addNewVocabulary("Katze", "cat");
		addNewVocabulary("Pferd", "horse");
		addNewVocabulary("Oktopus", "octopus");
		addNewVocabulary("Vogel", "bird");
		addNewVocabulary("Affe", "monkey");
		
		
		
		}

	public void addNewVocabulary(String german, String english) {
		
		if (!dictionary.containsKey(german) || !dictionary.containsValue(english)) {
			dictionary.put(german, english);
			size++;
		} else throw new DictionaryException("Wort bereits vorhanden");
		
	}
	
	public String getLanguage1() {
		return language1;
	}

	public void setLanguage1(String language1) {
		this.language1 = language1;
	}

	public String getLanguage2() {
		return language2;
	}

	public void setLanguage2(String language2) {
		this.language2 = language2;
	}

	public String getTranslation(String input) {
		if (dictionary.containsKey(input)) {
			String translation = dictionary.get(input);
			return translation;
		} else
			throw new DictionaryException("Wort nicht im Wörterbuch vorhanden");
	}
	public Set keySet() {
		return dictionary.keySet();
	}
	public String removeEntry(String voc) {
		if(dictionary.containsKey(voc)) {
		dictionary.remove(voc);
		}
		else throw new DictionaryException();
		return voc;
	}
	public int getSize() {
		return size;
	}
	
	public void refill(Map map) {
		dictionary.putAll(map);
	}
	
	
}
