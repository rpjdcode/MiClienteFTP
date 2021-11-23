package dad.rubenpablo;

import java.util.Optional;

import dad.rubenpablo.root.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class App extends Application {

	private RootController rootController;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		rootController = new RootController();
		
		Scene escenarioLogin = new Scene(rootController.getView());
		
		primaryStage.setTitle("Iniciar conexión");
		primaryStage.setScene(escenarioLogin);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}
	
	public static void error(String title, String header, String content) {
		Alert error = new Alert(AlertType.ERROR);
		error.setTitle(title);
		error.setHeaderText(header);
		error.setContentText(content);
		error.showAndWait();
	}
	
	public static void info(String title, String header, String content) {
		Alert info = new Alert(AlertType.INFORMATION);
		info.setTitle(title);
		info.setHeaderText(header);
		info.setContentText(content);
		info.showAndWait();
	}
	
	public static boolean confirm(String title, String header, String content) {
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		confirm.setTitle(title);
		confirm.setHeaderText(header);
		confirm.setContentText(content);
		Optional<ButtonType> result = confirm.showAndWait();
		return (result.get() == ButtonType.YES);
	}

}
