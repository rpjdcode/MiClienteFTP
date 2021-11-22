package dad.rubenpablo.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;


public class LoginController implements Initializable{

	
	// Model
	private LoginModel model = new LoginModel();
	
    @FXML
    private HBox buttonsBox;

    @FXML
    private Button cancelButton;

    @FXML
    private Button connectButton;

    @FXML
    private PasswordField passText;

    @FXML
    private TextField portText;

    @FXML
    private TextField svText;

    @FXML
    private TextField userText;

    @FXML
    private GridPane view;
    
    public GridPane getView() {
		return view;
	}

    public LoginController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
    	loader.setController(this);
    	loader.load();
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Bindings.bindBidirectional(svText.textProperty(), model.serverProperty());
		Bindings.bindBidirectional(portText.textProperty(), model.portProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(userText.textProperty(), model.userProperty());
		Bindings.bindBidirectional(passText.textProperty(), model.passProperty());
	}
	
    @FXML
    void onConnectAction(ActionEvent event) {
    	
    	
    }
    
    @FXML
    void onCancelAction(ActionEvent event) {
    	System.out.println("Click en cancelar");
    	Stage escenario = (Stage)cancelButton.getScene().getWindow();
    	escenario.close();
    }
    
    

}
