package clases;

import java.time.LocalDateTime;

public class Usuario {
	
	public String nombreUsuario;
	public String correo;
	public String tipoUsuario;
	public String tipoPaquete; //Perecedero o no
	public String numFicha;
	public String numTelefono;
	public LocalDateTime horaIngreso;
	public LocalDateTime horaSalida;
	
	public Usuario(String nombre, String correo, String telefono, String tipoUsuario, String tipoPaquete){
		this.nombreUsuario = nombre;
		this.correo = correo;
		this.numTelefono = telefono;
		this.tipoUsuario = tipoUsuario;
		this.tipoPaquete = tipoPaquete;

	}
	
	public String setNumFicha(int consecutivo){
		String ficha= asignarSiglasTipoPaquete()+"-"+asignarSiglasCategoria()+"-"+consecutivo;
		return ficha;
	}
	
	public String asignarSiglasTipoPaquete(){
		if (tipoPaquete.equals("Perecedero"))
    		return "P";
    	else
    		return "NP";
	}
	
	public String asignarSiglasCategoria(){
		if (tipoUsuario.equals("Persona con discapacidad")){  
    		return "D";
    	}
    	else if (tipoUsuario.equals("Adulto mayor")){ 
    		return "M";
    	}
    	else if (tipoUsuario.equals("Mujer embarazada")){ 
    		return "E";
    	}
    	else if (tipoUsuario.equals("Cliente regular")){
    		return "R";
    	}
		return null;
	}
}
