package dad.rubenpablo.login;

import java.io.IOException;
import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.net.ftp.FTPClient;

import dad.rubenpablo.App;
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
	
    public GridPane getView() {
		return view;
	}
    
    public LoginModel getModel() {
		return model;
	}
	
    @FXML
    void onConnectAction(ActionEvent event) {
    	FTPClient client = new FTPClient();
    	try {
    		// Intentamos establecer conexión con el servidor FTP
			client.connect(model.getServer(), model.getPort());
			// Intentamos iniciar sesión con los parámetros proporcionados
			if (client.login(model.getUser(), model.getPass())) {
				model.setConnected(true);
				model.setClient(client);
				App.info("Conexión Establecida", "Conexión establecida con éxito", "");
				
				((Stage)connectButton.getScene().getWindow()).close();
			} else {
				App.error("Inicio de sesión inválido", "Credenciales no validadas", "No se ha podido iniciar sesión, compruebe sus credenciales");
			}
			
		} catch (SocketException e) {
			App.error("Error Conexión FTP", "Se ha producido un error en la conexión con el servidor FTP", "Causa: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException ex) {
			App.error("Error Entrada/Salida", "Se ha producido un error relacionado con la entrada/salida", "Causa: " + ex.getMessage());
			ex.printStackTrace();
		}
    	
    }
    
    @FXML
    void onCancelAction(ActionEvent event) {
    	System.out.println("Click en cancelar");
    	Stage escenario = (Stage)cancelButton.getScene().getWindow();
    	escenario.close();
    }
    
    

}
