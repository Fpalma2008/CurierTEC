package clases;

public class ListaEnlazada {
	    public class nodo{
	        public nodo siguiente=null;
	        public String nombreVentanilla;
	        public Usuario usuario; 
	        public String estado = "libre";
	        public int cantAtendidos=0;
	        
	        public String agregarUsuarioVentanilla(Usuario usuario){
		           this.usuario= usuario;
		           cantAtendidos++;
		           return nombreVentanilla;
		    }
	        public Usuario sacarUsuarioVentanilla(){
		           return usuario;
		    }
	    }  
	    public nodo primero;
	    public nodo ultimo;
	    public nodo actual;
	    public int tamaño;
	    public String nombreLista;
	    public int ventanillasCreadas=0;
	    public int ventanillasLibres=0;
	        
	    //Constructor
	    public ListaEnlazada(String nombre){
	        primero=ultimo=actual=null;
	        tamaño=0;
	        nombreLista = nombre;
	    }
	    
	    public String formatoNombreVentanilla(){
	    	String codigo = nombreLista+" #"+ventanillasCreadas;
	    	return codigo;
	    }
	    
	    public void agregarVentanillas(){
	        nodo nuevo = new nodo();
	        if(tamaño ==0){
	           primero = ultimo = nuevo;
	        }	       
	        else{
	           ultimo.siguiente=nuevo;
	           ultimo = nuevo;
	        }
	        tamaño ++;
	        ventanillasCreadas++;
	        nuevo.nombreVentanilla= formatoNombreVentanilla();
	    }
	    
	   public String recorrerLista(){
		   actual= primero;
		   String texto="";
		   ventanillasLibres=0;
		   while(actual != null){
			   texto= texto+actual.nombreVentanilla+" "+actual.estado+"\n";	 
			   if(actual.estado.equals("libre")){
				   ventanillasLibres++;
			   }
			   actual = actual.siguiente;
			}
		   return texto;
	   }
	   
	   public nodo liberarVentanilla(String nombre){
		   actual= primero;
		   while(actual != null){
			   if(actual.nombreVentanilla.equals(nombre)){
				   actual.estado = "libre";
				   ventanillasLibres++;
				   return actual;
			   }
			   else
				   actual = actual.siguiente;
		   }
		   return actual;
	   }
	   
	   public void eliminarTodasVentanillas(){
		   primero=ultimo=actual=null;	
		   tamaño=0;
		   ventanillasCreadas=0;
		   
	   }
	   
	   public void eliminarVentanilla(String nombre){
		   actual= primero;
		   if(primero == ultimo){
			   primero=ultimo=actual=null;
		       tamaño=0;
		   }
		   else{
			   if(actual.nombreVentanilla.equals(nombre)){//Elimina si es el primero en la lista
				   primero = actual.siguiente;
				   actual.siguiente = null;
				   tamaño--;
			   }
			   else{
				   while(actual.siguiente != null){
					   if(actual.siguiente.nombreVentanilla.equals(nombre)){;
						   if(actual.siguiente == ultimo){//Si es el ultimo
							   ultimo = actual;
							   actual.siguiente=null;
							   tamaño--;
							   
						   }
						   else{ //Si en medio
							   actual.siguiente= actual.siguiente.siguiente;							 
							   tamaño--;
						   }
					   }
					   else{
						   actual = actual.siguiente;
					   }
				   }
			   }
		   }
	   }
	   
	   public nodo obtenerVentanilla(String nombre){
		   actual= primero;
		   while(actual != null){
			   if(actual.nombreVentanilla.equals(nombre)){
				   return actual;
			   }
			   else
				   actual = actual.siguiente;
		   }
		   return actual;
	   }
	   
	   public nodo ocuparVentanilla(){
		   actual= primero;
		   while(actual != null){
			   if(actual.estado.equals("libre")){
				   actual.estado = "ocupada";
				   ventanillasLibres--;
				   return actual;
			   }
			   else
				   actual = actual.siguiente;
			}
		   return actual;
	   }
	   
	   public void imprimir(){
		   actual= primero;
		   while(actual != null){
			//   System.out.println("------------------------------------------------");
			   System.out.println("Imprimir en le "+actual.nombreVentanilla);
			   actual = actual.siguiente;
			}
		   System.out.println("fin");
		    
	   }
	   
		public String clientesAtendidosPorVentanilla(){
			   actual= primero;
			   String texto = "";
			   while(actual != null){
				   texto = texto+ actual.nombreVentanilla+": "+actual.cantAtendidos+"\n";
				   actual = actual.siguiente;
			   }
			   return texto;
		}
	/**
	 public static void main(String[] args) {
		 ListaEnlazada l1= new ListaEnlazada("Perecedero");
		 Usuario u = new Usuario("faf", "", "", "", "");
		 l1.agregarVentanillas();
		 l1.agregarVentanillas();
		 l1.agregarVentanillas();
		 l1.agregarVentanillas();
		 l1.imprimir();
	 }*/
	 
}
   

 
	    
	 
	

