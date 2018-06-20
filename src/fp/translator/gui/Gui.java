package fp.translator.gui;

import fp.translator.dic.Dictionary;
import fp.translator.errors.DictionaryException;
import fp.translator.model.TranslatorLogic;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Gui extends Application {
	private Parent root;
	Label wordToTranslate;
	TextField translation;

	Label totalNumberOfVocabs;
	Label totalNumberOfCorrectWords;
	Label personalBest;
	TranslatorLogic TL = new TranslatorLogic();

	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void init() {

		// LABELS
		Label wordToTranslate;
		TextField translation;
		Label totalNumberOfCorrectWords;
		Label personalBest;
		Label totalNumberOfVocabs;
		Label UserTaskInfo;

		root = createSceneGraph();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Vokabeltrainer");
		Scene scene = new Scene(root, 700, 300);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private Parent createSceneGraph() {
		BorderPane main = new BorderPane();
		FlowPane scoreBoard = new FlowPane();
		String wordToTranslate = new String(TL.getNextVocA());
		TextField translation = new TextField();
		Label totalNumberOfCorrectWords= new Label("totalNumberOfCorrectWords");
		Label personalBest = new Label("personalBest");
		String dictionarySize = Integer.toString(TL.getDictionary().getSize())+  " Woerter im Woerterbuch vorhanden.";
		Label totalNumberOfVocabs= new Label(dictionarySize);
		Label UserTaskInfo= new Label("Bitte Uebersetze folgendes Wort: "+ wordToTranslate);
		Label wantedLanguage = new Label(TL.getDictionary().getLanguage2());
		UserTaskInfo.setPrefSize(700, 100);
		Label topInfoBoard = new Label("Der Vokabeltrainer 1.0");
		topInfoBoard.setFont(new Font("Arial", 30));
		topInfoBoard.setPrefSize(700, 100);
		Button enter = new Button("okay");
		Label translationTest = new Label();
		
		
		FlowPane center = new FlowPane();
		topInfoBoard.setAlignment(Pos.CENTER);
		translationTest.setText(TL.getVocB());
		enter.setOnAction(event-> {
			String guess = translation.getText();
				if(guess.length()==0) {
					topInfoBoard.setText("Es wurde nichts eingegeben");
					
				} 
				
				else { 
						
						if(TL.checkTranslation(guess)) {
							topInfoBoard.setText("RICHTIG");
							TL.getNextVocA();
							UserTaskInfo.setText("Bitte Uebersetze folgendes Wort: " + TL.getVocA());
						}
						else {
							topInfoBoard.setText("VERSUCHE ES NOCHMAL");
						}
					}
				});
		
		
		//MAINTAB
		
		scoreBoard.setPrefSize(200,300);
		
		center.getChildren().addAll(UserTaskInfo,translation, enter, translationTest);
		
		
		//TABS
		TabPane tabPane = new TabPane();
		Tab mainTab = new Tab();
		mainTab.setText("Vokabeltrainer");
		Tab addVocabularyTab = new Tab();
		addVocabularyTab.setText("Neue Vokabeln");
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabPane.getTabs().addAll(mainTab, addVocabularyTab);
		
		
		mainTab.setContent(main);
		scoreBoard.getChildren().addAll(totalNumberOfVocabs, totalNumberOfCorrectWords, personalBest);
		main.setTop(topInfoBoard);
		main.setLeft(scoreBoard);
		main.setCenter(center);
		
		
		
		//2NDTAB
		
		TextField tf1 = new TextField();
		TextField tf2 = new TextField();
		Label wordToAdd1= new Label(TL.getDictionary().getLanguage1());
		Label wordToAdd2= new Label(TL.getDictionary().getLanguage2());
		Label userInfo = new Label("Wort zum Hinzufügen eingeben: ");
		Label sizeOfDictionary = new Label(dictionarySize);
		
		
	
		Button save = new Button("save");
		GridPane secondTabMainPane = new GridPane();
		secondTabMainPane.add(userInfo, 1, 1);
		secondTabMainPane.add(wordToAdd1, 1, 2);
		secondTabMainPane.add(wordToAdd2, 2, 2);
		secondTabMainPane.add(tf1, 1, 3);
		secondTabMainPane.add(tf2, 2, 3);
	
		secondTabMainPane.add(save, 3, 3);
		save.setOnAction(event-> {
			if(tf1.getText().length()>0&&tf2.getText().length()>0) {
				String a = tf1.getText();
				String b = tf2.getText();
				try {
				TL.getDictionary().addNewVocabulary(a, b);
				userInfo.setText(a + " = "+ b + " wurde dem Wörterbuch hinzugefügt");
				} catch(DictionaryException dE) {
					userInfo.setText("Hinzufügen fehlgeschlagen: "+ dE.getMessage());
				}
				
			}
		});
		
		
		
		
	
		//secondTabMainPane.getChildren().addAll(wordToAdd1,wordToAdd1TF, wordToAdd2, wordToAdd2TF, save, sizeOfDictionary);
		addVocabularyTab.setContent(secondTabMainPane);
		//FlowPane
		
		
		
		return tabPane;
		
	}

}
