package dad.rubenpablo.root;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.net.ftp.FTPFile;

import dad.rubenpablo.App;
import dad.rubenpablo.fichero.FicheroFTP;
import dad.rubenpablo.fichero.TipoFichero;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController implements Initializable {

	// Login Controller
	private LoginController loginController = new LoginController();
	private Stage loginStage;

	// Model
	private RootModel model = new RootModel();

	@FXML
	private Label pathLabel;

	@FXML
	private HBox labelBox;

	@FXML
	private MenuBar menu;

	@FXML
	private Menu serverMenu;

	@FXML
	private MenuItem connectItem;

	@FXML
	private MenuItem disconnectItem;

	@FXML
	private TableView<FicheroFTP> filesTable;

	@FXML
	private TableColumn<FicheroFTP, String> nameColumn;

	@FXML
	private TableColumn<FicheroFTP, Number> sizeColumn;

	@FXML
	private TableColumn<FicheroFTP, TipoFichero> typeColumn;

	@FXML
	private Button downloadButton;

	@FXML
	private GridPane view;

	public RootController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootController2.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginStage = new Stage(StageStyle.DECORATED);
		loginStage.setTitle("Iniciar conexión");
		loginStage.setScene(new Scene(loginController.getView()));

		// Tabla
		filesTable.itemsProperty().bind(model.filesProperty());

		connectItem.disableProperty().bind(loginController.getModel().connectedProperty());
		disconnectItem.disableProperty().bind(loginController.getModel().connectedProperty().not());

		model.clientProperty().bind(loginController.getModel().clientProperty());
		model.connectedProperty().bind(loginController.getModel().connectedProperty());

		pathLabel.textProperty().bind(model.currentDirectoryProperty());

//		loginController.getModel().clientProperty().addListener((o, ov, nv) -> {
//			changeFilesList();
//			System.out.println("Antes valía " + ov + " y ahora valgo " + nv);
//		});

		// Tabla
		// Vinculamos itemsProperty de la tabla a la list property de lista de
		// FicheroFTP de nuestro modelo
		filesTable.itemsProperty().bind(model.filesProperty());
		filesTable.setRowFactory(tv -> {
			TableRow<FicheroFTP> row = new TableRow<FicheroFTP>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && !row.isEmpty()) {
					listFile(row.getItem());
				}
			});
			return row;
		});
		// Cell Value Factories
		nameColumn.setCellValueFactory(v -> v.getValue().nameProperty());
		typeColumn.setCellValueFactory(v -> v.getValue().typeProperty());
		sizeColumn.setCellValueFactory(v -> v.getValue().sizeProperty());

		// Listeners
		model.currentDirectoryProperty().addListener((o, ov, nv) -> {
			if (nv == null) {
				model.getFiles().remove(0, model.getFiles().size());
			} else {
				changeFilesList();
			}

		});
	}

	@FXML
	void onConnectServerAction(ActionEvent event) {
		loginStage.showAndWait();
		
		if (model.isConnected()) {
			try {
				model.setCurrentDirectory(model.getClient().printWorkingDirectory());

			} catch (IOException e) {
				App.error("Error Impresión Directorio", "Se produjo un error en pathLabel", "Causa: " + e.getMessage());
			}
		}
	}

	@FXML
	void onDisconnectServerAction(ActionEvent event) {
		try {
			model.getFiles().removeAll(model.getFiles()); // Eliminamos los ficheros almacenados en el modelo
			loginController.getModel().getClient().disconnect(); // Desconectamos el cliente actual
			loginController.getModel().setConnected(false); // La propiedad de conectado será false
			loginController.getModel().setClient(null); // El cliente se limpiará
			model.setCurrentDirectory(null); // No existe directorio actual de trabajo

			App.info("Desconexión FTP", "Desconectado del servidor", "Se ha desconectado del servidor "
					+ loginController.getModel().getServer() + " satisfactoriamente");
		} catch (IOException e) {
			App.error("Error", "Error de desconexión FTP", "Causa del error: " + e.getCause());
		}
	}

	public GridPane getView() {
		return view;
	}

	@FXML
	void onDownloadAction(ActionEvent e) {
		FicheroFTP selected = filesTable.getSelectionModel().getSelectedItem();

		if (selected != null && selected.getType() != TipoFichero.DIRECTORIO) {

			FileChooser chooser = new FileChooser();
			Stage chooserStage = new Stage();
			chooser.setTitle("Guardar fichero");
			chooser.getExtensionFilters().addAll(new ExtensionFilter("Todos los tipos", "*.*"));
			chooser.setInitialFileName(selected.getName());
			
			// Indicamos que almacenaremos el archivo descargado en el indicado en el chooser
			File download = chooser.showSaveDialog(chooserStage); 
			
			try {
				FileOutputStream fos = new FileOutputStream(download);
				model.getClient().retrieveFile(selected.getName(), fos);
				fos.flush();
				fos.close();
				App.info("Éxito", "Fichero guardado", "El fichero se ha descargado y guardado satisfactoriamente en " + download.getAbsolutePath());
			} catch (IOException e1) {
				App.error("Error Entrada/Salida", "Error relacionado con el guardado del fichero",
						"Causa: " + e1.getMessage());
				e1.printStackTrace();
			}
		}

	}

	private void listFile(FicheroFTP file) {
		if (file.getType() == TipoFichero.DIRECTORIO) {
			try {
				model.getClient().changeWorkingDirectory(file.getName());
				model.setCurrentDirectory(model.getClient().printWorkingDirectory());
			} catch (IOException e) {
				App.error("Error", "Error mostrando fichero FTP", "Causa: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private void changeFilesList() {
		try {
			model.filesProperty().removeAll(model.getFiles());
			FTPFile[] filesDir = model.getClient().listFiles();

			for (FTPFile file : filesDir) {
				FicheroFTP ftpFile = new FicheroFTP(file);
				model.filesProperty().add(ftpFile);
			}
		} catch (IOException e) {
			App.error("Error", "Error Listando Ficheros", "Causa: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
