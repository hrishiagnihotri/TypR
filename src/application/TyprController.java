package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class TyprController implements Initializable {										//functional/heart class of the application
	
	private int wordCounter = 0;
	ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	
	@FXML																					//declared multiple elements of FXml
    public Text seconds;
	@FXML
	public Text charcount;
    @FXML
    private Text wordsPerMin;
    @FXML
    private Text accuracy;
    @FXML
    private Text programWord;

    @FXML
    private TextField userWord;

    @FXML
    private ImageView correct;
    @FXML
    private ImageView wrong;
    @FXML
    private ImageView term1;

    @FXML
    private Button playAgain;
    @FXML
    private Button term2;
    @FXML
    private Label remark;

    ArrayList<String> words = new ArrayList<>();										//words array is declared

    public void addToList() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new
                    FileReader("wordsList"));											//wordlist text  file created manually, is read to display on window
            String line = reader.readLine();
            while (line != null) {
                words.add(line);														//each next line string/word of "wordsList" is copied to array named "words"
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toMainMenu(ActionEvent ae) throws IOException {							//when pressed 'Play again' on Screen, heads back to welcome page,not username
        Main m = new Main();
        m.changeScene("sample.fxml");
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		playAgain.setVisible(false);
		remark.setVisible(false);
        playAgain.setDisable(true);
        seconds.setText("60");
        addToList();
        Collections.shuffle(words);														//words jumbled when the game starts,so that user doesnt get same series of words everytime 
        programWord.setText(words.get(wordCounter));
        wordCounter++;
	}
	
	private int first = 1;
	private int timer = 60;
	 public void terminate(ActionEvent ae) throws IOException{							//force termination button created while devloping program and for testing purpose
		 timer=-1;
	 }
    Runnable r = new Runnable() {														//runnable declared for 60secs countdown instance for every some set of statements("timer" changes)
        @Override
        public void run() {
            if (timer > -1) {															//seconds are printed till it reaches 0 seconds on screen
                seconds.setText(String.valueOf(timer));

                timer -= 1;
            }
            
            

            else {
                if (timer == -1) {														//statements to be executed after 1 sec delay after zero seconds
                    userWord.setDisable(true);
                    userWord.setText("Game over");
                    
                    playAgain.setVisible(true);
                    remark.setVisible(true);
                    playAgain.setDisable(false);
                    executor.shutdown();

                }
                
                

          

                timer -= 1;
            }
        }
    };
    
    Runnable fadeCorrect = new Runnable() {												//animations for pngs to appear on screen,duplicated codes for 'wrong' and 'right' icons FOR EASY REFERENCE ONLY
        @Override
        public void run() {
            correct.setOpacity(0);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            correct.setOpacity(50);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            correct.setOpacity(100);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            correct.setOpacity(0);

        }
    };

    Runnable fadeWrong = new Runnable() {
        @Override
        public void run() {
            wrong.setOpacity(0);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wrong.setOpacity(50);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wrong.setOpacity(100);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wrong.setOpacity(0);
        }
    };
    
    public void wremarks(int w) {				//remarks based on wpm
    	if(w>=0 && w<11) {
    		
    		remark.setText("Remarks: "+"\"Average WPM,Trying is never a bad option.\"");
    	}
    	else if(w>=11 && w<21) {
    		remark.setText("Remarks: "+"\"WPM above average,Trying is never a bad option.\"");
    	}
    	else if(w>=21 && w<31) {
    		remark.setText("Remarks: "+"\"Productive WPM,Bruh you good than many.\"");
    	}
    	else if(w>=31 && w<51) {
    		remark.setText("Remarks: "+"\"High speed WPM.Machines are slower than you,CHEERS!\"");
    	}
    	else {
    		remark.setText("Remarks: "+"\"Competitve speed WPM,JODD!\"");
    	}
    }
    public void aremarks(float w) {			//visual changes of accuracy meter on screen
    	if(w>=55 && w<70) {
    		accuracy.setStyle("-fx-fill: yellow;");
    	}
    	else if(w>=70 && w<86) {
    		accuracy.setStyle("-fx-fill: #00ffa1;");
    	}
    	else if(w>=86 && w<101) {
    		accuracy.setStyle("-fx-fill: green;");
    	}
    	else{
    		accuracy.setStyle("-fx-fill: white;");
    	} 	
    	
    }
    
    private int countAll = 0;												//Total words displayed
    private int cpm = 0;
    private int counter = 0;												//for wpm purpose
    public void startGame(KeyEvent ke) {

        									// only called once
        if (first == 1) {					//program starts when first is 1 
            first = 0;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);		//countdown of 1 second started with runnable r for every "timer"(changes) decrement
        }

        if (ke.getCode().equals(KeyCode.ENTER)) {

            String s = userWord.getText();									//string entered by user in field
            String real = programWord.getText();							//string displayed from words
            countAll++;

            // if correct
            if (s.equalsIgnoreCase(real)) {									//COMPARES ,CASE INSENSITIVE
                counter++;
                cpm+=s.length();											//CPM CALCULATION
                wordsPerMin.setText(String.valueOf(counter));				//WPM CALCULATION

                Thread t = new Thread(fadeCorrect);
                t.start();

            }
            else {
                Thread t = new Thread(fadeWrong);
                t.start();
            }
            userWord.setText("");
            accuracy.setText(String.valueOf(Math.round((counter*1.0/countAll)*100)));
            programWord.setText(words.get(wordCounter));
            charcount.setText(String.valueOf(cpm));

            wremarks(counter);
            aremarks((float)(counter*1.0/countAll)*100);
            wordCounter++;													//switching words on screen,next jumbled word 
            
            
        }

    }
}
