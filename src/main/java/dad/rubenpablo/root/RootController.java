package dad.rubenpablo.root;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.rubenpablo.App;
import dad.rubenpablo.login.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController implements Initializable{

	// Login Controller
	private LoginController loginController = new LoginController();
	private Stage loginStage;
	
	// Model
	private RootModel model = new RootModel();
	
	@FXML
	private Label pathLabel;
	
	@FXML
	private MenuBar menu;
	
	@FXML
	private Menu serverMenu;
	
	@FXML
	private MenuItem connectItem;

	@FXML
	private MenuItem disconnectItem;

	@FXML
	private TableView<?> filesTable;
	
	@FXML
	private TableColumn<?, ?> nameColumn;

	@FXML
	private TableColumn<?, ?> sizeColumn;

	@FXML
	private TableColumn<?, ?> typeColumn;
	
	@FXML
	private Button downloadButton;


	@FXML
	private GridPane view;

	public RootController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootController.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginStage = new Stage(StageStyle.DECORATED);
		loginStage.setTitle("Iniciar conexión");
		loginStage.setScene(new Scene(loginController.getView()));
		
		
		
		connectItem.disableProperty().bind(loginController.getModel().connectedProperty());
		disconnectItem.disableProperty().bind(loginController.getModel().connectedProperty().not());
		
		model.clientProperty().bind(loginController.getModel().clientProperty());
		
		loginController.getModel().clientProperty().addListener((o, ov, nv) -> {
			System.out.println("Antes valía " + ov + " y ahora valgo " + nv);
		});
		
	}
	
	@FXML
	void onConnectServerAction(ActionEvent event) {
		loginStage.show();
	}

	@FXML
	void onDisconnectServerAction(ActionEvent event) {
		try {
			loginController.getModel().getClient().disconnect();
			loginController.getModel().setConnected(false);
			loginController.getModel().setClient(null);
			App.info("Desconexión FTP",
					"Desconectado del servidor",
					"Se ha desconectado del servidor " + loginController.getModel().getServer() + " satisfactoriamente");
		} catch (IOException e) {
			App.error("Error", "Error de desconexión FTP", "Causa del error: " + e.getCause());
		}
	}
	
	public GridPane getView() {
		return view;
	}



}
