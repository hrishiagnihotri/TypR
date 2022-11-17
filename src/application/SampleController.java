package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SampleController implements Initializable{
	
	@FXML
	private Label user1;			//declared textlabel of username
	@FXML
	private Button buttbout;		//declared Button-about to proceed to 'about' section(NOT NECCESARY TO DECLARE BUTTON WHEN ACTION IS HEADED IN FXML)
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		File newFile = new File("username.txt");
        if (newFile.length() != 0) {
            Scanner reader = null;
            try {
                reader = new Scanner(newFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String data = reader.nextLine();
            user1.setText("Hi,"+data);				//checks for username file and displays hi+username
	}
        											//else part not constructed coz if file is not found or created ,"hi,user" is displayed as styled in fxml file already
	
}
	public void about(ActionEvent abt) throws IOException {
        Main m = new Main();

                m.changeScene("about.fxml");		//heads to about section when clicked-(action event of buttbout )
            }										//refer AboutController.java
        
	public void playGame(ActionEvent ae) throws IOException {
        Main m = new Main();

            try {
                m.changeScene("typr.fxml");			//heads to actual game when START button is pressed(refer TyprController.java)
            } catch (IOException e) {
                e.printStackTrace();
            }
        
        
    }
}