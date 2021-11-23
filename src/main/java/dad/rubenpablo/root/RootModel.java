package dad.rubenpablo.root;

import org.apache.commons.net.ftp.FTPClient;

import dad.rubenpablo.fichero.FicheroFTP;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RootModel {
	
	private BooleanProperty connected = new SimpleBooleanProperty();
	private ObjectProperty<FTPClient> client = new SimpleObjectProperty<FTPClient>();
	private StringProperty currentDirectory = new SimpleStringProperty();
	private ListProperty<FicheroFTP> files = new SimpleListProperty<FicheroFTP>(FXCollections.observableArrayList());
	
	public final BooleanProperty connectedProperty() {
		return this.connected;
	}
	
	public final boolean isConnected() {
		return this.connectedProperty().get();
	}
	
	public final void setConnected(final boolean connected) {
		this.connectedProperty().set(connected);
	}
	
	public final ObjectProperty<FTPClient> clientProperty() {
		return this.client;
	}
	
	public final FTPClient getClient() {
		return this.clientProperty().get();
	}
	
	public final void setClient(final FTPClient client) {
		this.clientProperty().set(client);
	}
	
	public final StringProperty currentDirectoryProperty() {
		return this.currentDirectory;
	}
	
	public final String getCurrentDirectory() {
		return this.currentDirectoryProperty().get();
	}
	
	public final void setCurrentDirectory(final String currentDirectory) {
		this.currentDirectoryProperty().set(currentDirectory);
	}

	public final ListProperty<FicheroFTP> filesProperty() {
		return this.files;
	}
	

	public final ObservableList<FicheroFTP> getFiles() {
		return this.filesProperty().get();
	}
	

	public final void setFiles(final ObservableList<FicheroFTP> files) {
		this.filesProperty().set(files);
	}
	
	
	
	
	
}
