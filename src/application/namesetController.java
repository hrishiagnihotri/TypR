package application;

import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class namesetController {
	@FXML
	private TextField userentry;								//declared field where name is entered.
	
	public void ongame(ActionEvent ae) throws IOException {		//submit button(action) being made invokes 'ongame' method
        String name = userentry.getText();
        FileWriter myObj = new FileWriter("username.txt");		//creates file to store username,rewrites everytime program runs.
        myObj.write(name);
        myObj.close();
        Main m = new Main();
        m.changeScene("Sample.fxml");							//heads to welcome window(refer SampleController.java and fxml file)
    }
	public void enterkey(KeyEvent ae) throws IOException{		//does same function as 'ongame' method when ENTER key is pressed,constructed so to give good experience to user
		if (ae.getCode().equals(KeyCode.ENTER)) {
			String name = userentry.getText();
	        FileWriter myObj = new FileWriter("username.txt");
	        myObj.write(name);
	        myObj.close();
	        Main m = new Main();
	        m.changeScene("Sample.fxml");
		}
	}

}
