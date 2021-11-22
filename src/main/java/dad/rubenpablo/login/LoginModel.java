package dad.rubenpablo.login;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginModel {
	
	private StringProperty server = new SimpleStringProperty();
	private IntegerProperty port = new SimpleIntegerProperty();
	private StringProperty user = new SimpleStringProperty();
	private StringProperty pass = new SimpleStringProperty();
	private BooleanProperty connected = new SimpleBooleanProperty();
	
	
	public final StringProperty serverProperty() {
		return this.server;
	}
	
	public final String getServer() {
		return this.serverProperty().get();
	}
	
	public final void setServer(final String server) {
		this.serverProperty().set(server);
	}
	
	public final IntegerProperty portProperty() {
		return this.port;
	}
	
	public final int getPort() {
		return this.portProperty().get();
	}
	
	public final void setPort(final int port) {
		this.portProperty().set(port);
	}
	
	public final StringProperty userProperty() {
		return this.user;
	}
	
	public final String getUser() {
		return this.userProperty().get();
	}
	
	public final void setUser(final String user) {
		this.userProperty().set(user);
	}
	
	public final StringProperty passProperty() {
		return this.pass;
	}
	
	public final String getPass() {
		return this.passProperty().get();
	}
	
	public final void setPass(final String pass) {
		this.passProperty().set(pass);
	}
	
	public final BooleanProperty connectedProperty() {
		return this.connected;
	}
	
	public final boolean isConnected() {
		return this.connectedProperty().get();
	}
	
	public final void setConnected(final boolean connected) {
		this.connectedProperty().set(connected);
	}
	
	
	
}
