package fp.translator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

import fp.translator.dic.Dictionary;
import fp.translator.errors.DictionaryException;
import javafx.collections.transformation.SortedList;

public class TranslatorLogic {
	
	Dictionary dictionary=new Dictionary();
	Map<String, String> tempData = new HashMap<>();
	String language1 = dictionary.getLanguage1();
	String language2 = dictionary.getLanguage2();
	private String vocA= null;
	String vocB = null;
	Set<String> alreadyAsked = new HashSet();
	Boolean allWordsAsked =false;
	
	List<String> keys;
	int wordsToBeAsked;
	
	
	
	
	
	
	public TranslatorLogic() {
		dictionary = new Dictionary();
		tempData.putAll(dictionary.dictionary);
		
		keys = new LinkedList();
		keys.addAll(dictionary.dictionary.keySet());
		Collections.shuffle(keys);
		
		
		wordsToBeAsked = keys.size();
		setVocA(getNextVocA());
	}
	
	
	public String getNextVocA() {
		if(wordsToBeAsked==0) {
			wordsToBeAsked=keys.size();
			return keys.get(keys.size()-1);
		}
	
		wordsToBeAsked--;
		setVocA(keys.get(wordsToBeAsked));
		vocB=getDictionary().getTranslation(getVocA());
		return getVocA();
		
		
		
	
		//Vokabel wird aus dem dictionary entfernt, sodass man 
		//nicht zwei mal das selbe wort gefragt wird
	}
	
	public String getVocB() {
		return vocB;
	}
	
	
	public Boolean checkTranslation(String guess) {	
		if (guess.toLowerCase().equals(vocB.toLowerCase())) return true;
		else return false;
	}
	public Dictionary getDictionary() {
		return dictionary;
	}
	
public void switchLanguages() {
		Set<Entry<String, String>> entries= new HashSet();
		entries = dictionary.dictionary.entrySet();
		Map <String, String> tempMap = dictionary.dictionary;
		for(Entry<String, String> entry: entries){
			tempMap.put(entry.getValue(), entry.getKey());
		}
		dictionary.dictionary.putAll(tempMap);
		wordsToBeAsked=keys.size();
	}


public String getVocA() {
	return vocA;
}


public void setVocA(String vocA) {
	this.vocA = vocA;
}

	
	
	
	

}
