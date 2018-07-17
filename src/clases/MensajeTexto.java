package clases;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MensajeTexto {
	
	private String usuario;
	private String clave;
	private String emisor;
	private String mensaje;
	private String numeros;
	private String url;
	private static MensajeTexto smsService = new MensajeTexto();
	
	private MensajeTexto(){
		try{
			usuario = "username=" + URLEncoder.encode("fpalma2008@estudiantec.cr", "UTF-8");
			clave = "&hash=" + URLEncoder.encode("nPcYcEU9oMA-mykjR2PXflszIXdq7C1jFnnmYmydR5", "UTF-8");
			emisor = "&sender=" + URLEncoder.encode("CourierTEC", "UTF-8");
			mensaje = "&message=";
			numeros = "&numbers=";
			url = "http://api.textlocal.com/send/?"+usuario+clave;
		} catch (Exception e) {}
	}
	
	public void enviarMensaje(String mensaje, String numeros) {
		try{
			establecerURL(mensaje,numeros);
			URL url = new URL(this.url);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.getInputStream();
			
		} catch (Exception e) {}
	}
	
	private void establecerURL(String mensaje, String numeros){
		try{
			mensaje =  this.mensaje + URLEncoder.encode(mensaje, "UTF-8");
			numeros =  this.numeros + URLEncoder.encode(numeros, "UTF-8");
			url += numeros + mensaje + emisor;
			//System.out.println(url);
		} catch (Exception e) {}
	}
	
	public static MensajeTexto getInstance(){
		return smsService;
	}
}