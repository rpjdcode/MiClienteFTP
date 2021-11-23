package dad.rubenpablo.fichero;

import org.apache.commons.net.ftp.FTPFile;


import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FicheroFTP {
	
	private StringProperty name = new SimpleStringProperty();
	private LongProperty size = new SimpleLongProperty();
	private ObjectProperty<TipoFichero> type = new SimpleObjectProperty<TipoFichero>();
	private ObjectProperty<FTPFile> ftpFile = new SimpleObjectProperty<FTPFile>();
	
	public FicheroFTP(FTPFile fichero) {
		setName(fichero.getName());
		setSize(fichero.getSize());
		setType(fichero.isDirectory() ? TipoFichero.DIRECTORIO : TipoFichero.ARCHIVO);
		setFtpFile(fichero);
	}
	
	public final StringProperty nameProperty() {
		return this.name;
	}
	
	public final String getName() {
		return this.nameProperty().get();
	}
	
	public final void setName(final String name) {
		this.nameProperty().set(name);
	}
	
	public final LongProperty sizeProperty() {
		return this.size;
	}
	
	public final long getSize() {
		return this.sizeProperty().get();
	}
	
	public final void setSize(final long size) {
		this.sizeProperty().set(size);
	}
	
	public final ObjectProperty<TipoFichero> typeProperty() {
		return this.type;
	}
	
	public final TipoFichero getType() {
		return this.typeProperty().get();
	}
	
	public final void setType(final TipoFichero type) {
		this.typeProperty().set(type);
	}

	public final ObjectProperty<FTPFile> ftpFileProperty() {
		return this.ftpFile;
	}
	

	public final FTPFile getFtpFile() {
		return this.ftpFileProperty().get();
	}
	

	public final void setFtpFile(final FTPFile ftpFile) {
		this.ftpFileProperty().set(ftpFile);
	}
	
	
	
	
}
