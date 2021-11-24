package dad.miclienteftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class MainExample {
	
	public static void main(String [] args) {
		try {
			
			// instanciar el cliente FTP
			FTPClient cliente = new FTPClient();
			
			// conectar con el servidor FTP
			cliente.connect("ftp.rediris.es", 21);
			
			// iniciar sesión anónimo (login)
			cliente.login("", "");
			
			// cambiar el directorio actual en el servidor
			cliente.changeWorkingDirectory("/debian/dists");
			
			// recuperar un listado de los ficheros y directorios del directorio actual del servidor
			FTPFile [] ficheros = cliente.listFiles();
			
			// recorrer el listado de archivos recuperados
			System.out.println("Listar archivos del servidor:");
			System.out.println("Nombre / Tamaño  / Tipo");
			System.out.println("==================");
			for (FTPFile fichero : ficheros) {
				// mostrar el nombre, el tamaño (bytes) y el tipo (0 = fichero, 1 = directorio) de cada fichero/directorio recuperado
				String tipo = "";
				switch (fichero.getType()) {
				case FTPFile.DIRECTORY_TYPE: tipo = "Directorio"; break;
				case FTPFile.FILE_TYPE: tipo = "Fichero"; break;
				case FTPFile.SYMBOLIC_LINK_TYPE: tipo = "Enlace"; break;
				default: tipo = "Desconocido"; break;
				}
				
				System.out.println(fichero.getName() + " / " + fichero.getSize() + " bytes / " + tipo);
				
				
			}
			System.out.println();
			
			// recuperar el nombre del directorio actual
			String directorioActual = cliente.printWorkingDirectory();
			System.out.println("Directorio actual: " + directorioActual);

			// cambiar el directorio padre en el servidor
			cliente.changeWorkingDirectory("..");

			// descargar un fichero
			File descarga = new File("welcome.msg");
			FileOutputStream flujo = new FileOutputStream(descarga);
			cliente.retrieveFile("welcome.msg", flujo);
			flujo.flush();
			flujo.close();
			
			// desconectar del servidor
			cliente.disconnect();
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}