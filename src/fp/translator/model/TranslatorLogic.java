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
	String vocA= null;
	String vocB = null;
	Set<String> alreadyAsked = new HashSet();
	Boolean allWordsAsked =false;
	
	List<String> keys;
	int wordsToBeAsked;
	
	
	
	
	
	
	public TranslatorLogic() {
		dictionary = new Dictionary();
		tempData.putAll(dictionary.dictionary);
		
		keys = new LinkedList(dictionary.dictionary.values());
		
		Collections.shuffle(keys);
		
		
		wordsToBeAsked = keys.size();
		vocA=getNextVocA();
	}
	
	
	public String getNextVocA() {
		if(wordsToBeAsked==0) {
			wordsToBeAsked=keys.size();
			return keys.get(keys.size()-1);
		}
	
		wordsToBeAsked--;
		vocA = keys.get(wordsToBeAsked);
		return vocA;
		
		
		
	
		//Vokabel wird aus dem dictionary entfernt, sodass man 
		//nicht zwei mal das selbe wort gefragt wird
	}
	
	
	public Boolean checkTranslation(String guess) {
		vocB=dictionary.getTranslation(vocA);
		if (vocB==guess) return true;
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

	
	
	
	

}