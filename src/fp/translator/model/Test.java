package fp.translator.model;

import java.io.IOException;

import fp.translator.dic.Dictionary;

public class Test {

	public static void main(String[] args) throws IOException {
		
		TranslatorLogic Tl = new TranslatorLogic();
		System.out.println(Tl.keys.size());
		System.out.println(Tl.keys.toString());
		System.out.println(Tl.getDictionary().dictionary.size());
		for (int i=0;i<6;i++) {
		System.out.println(Tl.getNextVocA());
		}
		Tl.switchLanguages();
		/*for (int i=0;i<6;i++) {
			
			System.out.println(Tl.getNextVocA());
			
			}*/
		System.out.println(Tl.alreadyAsked);
		
		
	}

}
