package dad.rubenpablo;

import java.util.Optional;

import dad.rubenpablo.root.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class App extends Application {

	private RootController rootController;
	public static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		rootController = new RootController();
		
		Scene rootScene = new Scene(rootController.getView(), 300, 300);
		
		primaryStage.setTitle("MiClienteFTP");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/imgs/ftp-icon-32x32.png")));
		primaryStage.setScene(rootScene);
		primaryStage=primaryStage;
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}
	
	/** Método que se utilizará para mostrar los errores a través de una alerta **/
	public static void error(String title, String header, String content) {
		Alert error = new Alert(AlertType.ERROR);
		Stage errorStage = (Stage)error.getDialogPane().getScene().getWindow();
		errorStage.getIcons().add(new Image(App.class.getResourceAsStream("/imgs/ftp-icon-32x32.png")));
		error.setTitle(title);
		error.setHeaderText(header);
		error.setContentText(content);
		error.showAndWait();
	}
	
	/** Método que se utilizará para mostrar la información a través de una alerta **/
	public static void info(String title, String header, String content) {
		Alert info = new Alert(AlertType.INFORMATION);
		Stage infoStage = (Stage)info.getDialogPane().getScene().getWindow();
		infoStage.getIcons().add(new Image(App.class.getResourceAsStream("/imgs/ftp-icon-32x32.png")));
		info.setTitle(title);
		info.setHeaderText(header);
		info.setContentText(content);
		info.showAndWait();
	}
	
	/** Método que se utilizará para mostrar un diálogo de confirmación a través de una alerta **/
	public static boolean confirm(String title, String header, String content) {
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		Stage confirmStage = (Stage)confirm.getDialogPane().getScene().getWindow();
		confirmStage.getIcons().add(new Image(App.class.getResourceAsStream("/imgs/ftp-icon-32x32.png")));
		confirm.setTitle(title);
		confirm.setHeaderText(header);
		confirm.setContentText(content);
		Optional<ButtonType> result = confirm.showAndWait();
		return (result.get() == ButtonType.YES);
	}
	
	

}
