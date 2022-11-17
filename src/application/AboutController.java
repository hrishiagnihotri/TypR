package application;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class AboutController {
	@FXML
	private Button back;
	@FXML
	private Label hL;		//hyperlinks
	@FXML
	private Label hL2;
	
	public void back(ActionEvent ae) throws IOException {
      
        Main m = new Main();
        m.changeScene("Sample.fxml");										//goes back to Welcome page,user name prompt is skipped
    }
	public void yt(MouseEvent ae) throws IOException, URISyntaxException {  //redirects to URLs when clicked over them
		
		if(Desktop.isDesktopSupported())
	    {
	        
	            Desktop.getDesktop().browse(new URI("https://www.youtube.com/@javacodingcommunity"));
	    }
	}
	public void fi(MouseEvent ae) throws IOException, URISyntaxException {
		
		if(Desktop.isDesktopSupported())
		{
			
			Desktop.getDesktop().browse(new URI("https://www.flaticon.com/"));
		}
	}
}
