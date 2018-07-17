package clases;

public class ColaPrioridad{
    public class nodo{
        public nodo siguiente=null;
        public nodo anterior=null;
        public Usuario usuario;
        public ColaPrioridad colaUsuariosP;
         
        public nodo(Usuario usuario){              
            this.usuario = usuario;
        }
        
        public nodo(ColaPrioridad colaUsuariosP){              
            this.colaUsuariosP = colaUsuariosP;
        }
    } 
    public int tamaño;
    public nodo primero;
    public nodo ultimo;
    public boolean vacia;
    public String nombreCola = null;
    public int tiempoMinimoAtencion = 0;
    public int tiempoMaximoAtencion = 0;
    public int cantidadTotalFichas=0;
    public int consecutivo=-1;
    
    public ColaPrioridad(String nombreCola) {
        tamaño = 0;
        primero = null;
        ultimo = null;
        vacia = true;
        this.nombreCola = nombreCola;
    }
    
	public void setCantClientes(){
		if (consecutivo ==99)
			consecutivo = 0;
		else
			consecutivo++;
	}

    //Agrega elementos a la cola de prioridad
    public void encolar(ColaPrioridad colasConUsuarios){
        nodo nuevo = new nodo(colasConUsuarios);
        if (tamaño==0){
            primero = ultimo = nuevo;
            tamaño++;
            consecutivo++;
        }
        else{
            nuevo.anterior = ultimo;
            ultimo.siguiente=nuevo;
            ultimo = nuevo;
            tamaño ++;
            consecutivo++;
            
        }
    }
    public void encolar(Usuario usuario){
        nodo nuevo = new nodo(usuario);
        if (tamaño==0){
            primero = ultimo = nuevo;
            tamaño++;
            consecutivo++;
            cantidadTotalFichas++;
            //System.out.println (nuevo.usuario.nombreUsuario );
        }
        else{
            nuevo.anterior = ultimo;
            ultimo.siguiente=nuevo;
            ultimo = nuevo;
            tamaño ++;
            consecutivo++;
            cantidadTotalFichas++;
        }
    }
        
    //Metodo para buscar si una cola de prioridad es igual a otra cola 
    public boolean colasIguales(String cola1, String cola2){
    	if (cola1.equals(cola2))
    		return true;
    	else
    		return false;
    }
        
    //metodo que retorna una cola buscada
    public nodo devolverCola(String colaBuscada){
    	if (tamaño!=0){
            nodo actual= primero;
            while(actual != null){
            	if(colasIguales(actual.colaUsuariosP.nombreCola, colaBuscada))
            		return actual; //System.out.println(actual.colaPrioridad.nombreCola);
                actual = actual.siguiente;
            }
    	}
        return null;
    }
    
    public nodo decolar(){//Devuelve un nodo cola de prioridad donde se encuentra el usuario, actual.usuario
    	nodo actual= null;
        if (tamaño != 0){
            if (tamaño == 1){
            	actual = primero;
                primero = ultimo = null;
                tamaño = 0;
            }
            else{
            	actual = primero;
                primero = primero.siguiente;
                primero.anterior= null;
                tamaño --;
            }
        }
        else{
        System.out.println("No hay elementos");
        }  
        return actual;
    }
    
    public nodo decolarSeguridad(){//Devuelve un nodo cola de prioridad donde se encuentra el usuario, actual.usuario
    	nodo actual = primero;
        if (tamaño != 0){
            if (tamaño == 1){
                primero = ultimo = null;
                tamaño = 0;
                return actual;
            }
            else{
        		if(actual.usuario.tipoPaquete.equals("Perecedero")){//Cuand perecedero es el primero
                    primero = primero.siguiente;
                    primero.anterior= null;
                    tamaño --;
                    return actual;
        		}
        		        		       		
        		else{
        			while(actual != null){//Cuand perecedero esta en medio
        				if( (actual==ultimo) &&(actual.usuario.tipoPaquete.equals("Perecedero"))){//Si actual es el ultimo REVISAR
                			ultimo = actual.anterior;
                			actual.anterior.siguiente= null;
                			actual.anterior= null;
                			tamaño --;
                			return actual;
                		}
        				else if(actual.usuario.tipoPaquete.equals("Perecedero")){
                            actual.anterior.siguiente= actual.siguiente;
                            actual.siguiente.anterior = actual.anterior; //Revisar
                            tamaño --;
                            return actual;
        				}
        				actual = actual.siguiente;
                	}
        		}
        		
        		
            }
    		//Elimina el primero de la lista, de no perecederos.
    		actual = primero;
            primero = primero.siguiente;
            primero.anterior= null;
            tamaño --;
            return actual;
        }
        else{
        System.out.println("No hay elementos");
        }  
        return actual;
    }
    
    
    public nodo devolverUsuarioSeguridad(){//Devuelve un nodo cola de prioridad donde se encuentra el usuario, actual.usuario
    	nodo actual = primero;
        if (tamaño != 0){
            if (tamaño == 1){
                return actual;
            }
            else{
        		if(actual.usuario.tipoPaquete.equals("Perecedero")){//Cuand perecedero es el primero
                    return actual;
        		}
        		else if((actual.siguiente.equals(null))&&(actual.usuario.tipoPaquete.equals("Perecedero"))){//Si actual es el ultimo
        			return actual;
        		}        		       		
        		else{
        			while(actual != null){//Cuand perecedero esta en medio
        				if(actual.usuario.tipoPaquete.equals("Perecedero")){
                            return actual;
        				}
        				actual = actual.siguiente;
                	}
        		}
            }
    		//Elimina el primero de la lista, de no perecederos.
    		actual = primero;
            return actual;
        }
        else{
        System.out.println("No hay elementos");
        }  
        return actual;
    }
    
    //Metodo para atender las fichas, donde se aplica la cola de prioridad.
    public Usuario atenderCliente(){
    	if (tamaño!=0){
			if ( devolverCola("Persona con discapacidad").colaUsuariosP.tamaño>0 ){
				return devolverCola("Persona con discapacidad").colaUsuariosP.decolar().usuario;
        	} 		
        	else if (devolverCola("Adulto mayor").colaUsuariosP.tamaño>0){ 
        		return devolverCola("Adulto mayor").colaUsuariosP.decolar().usuario;
        	}
    		else if (devolverCola("Mujer embarazada").colaUsuariosP.tamaño>0){
    			return devolverCola("Mujer embarazada").colaUsuariosP.decolar().usuario;
        	}
        	else if (devolverCola("Cliente regular").colaUsuariosP.tamaño>0){
        		return devolverCola("Cliente regular").colaUsuariosP.decolar().usuario;
        	}
    	}
    	return null;
    }
    
    public Usuario atenderClienteSeguridad(){
    	if (tamaño!=0){
			if ( devolverCola("Persona con discapacidad").colaUsuariosP.tamaño>0 ){
				return devolverCola("Persona con discapacidad").colaUsuariosP.decolarSeguridad().usuario;
        	} 		
        	else if (devolverCola("Adulto mayor").colaUsuariosP.tamaño>0){ 
        		return devolverCola("Adulto mayor").colaUsuariosP.decolarSeguridad().usuario;
        	}
    		else if (devolverCola("Mujer embarazada").colaUsuariosP.tamaño>0){
    			return devolverCola("Mujer embarazada").colaUsuariosP.decolarSeguridad().usuario;
        	}
        	else if (devolverCola("Cliente regular").colaUsuariosP.tamaño>0){
        		return devolverCola("Cliente regular").colaUsuariosP.decolarSeguridad().usuario;
        	}
    	}
    	return null;
    }
    public Usuario buscarSiguienteClienteSeguridad(){
    	if (tamaño!=0){
			if ( devolverCola("Persona con discapacidad").colaUsuariosP.tamaño>0 ){
				return devolverCola("Persona con discapacidad").colaUsuariosP.devolverUsuarioSeguridad().usuario;
        	} 		
        	else if (devolverCola("Adulto mayor").colaUsuariosP.tamaño>0){ 
        		return devolverCola("Adulto mayor").colaUsuariosP.devolverUsuarioSeguridad().usuario;
        	}
    		else if (devolverCola("Mujer embarazada").colaUsuariosP.tamaño>0){
    			return devolverCola("Mujer embarazada").colaUsuariosP.devolverUsuarioSeguridad().usuario;
        	}
        	else if (devolverCola("Cliente regular").colaUsuariosP.tamaño>0){
        		return devolverCola("Cliente regular").colaUsuariosP.devolverUsuarioSeguridad().usuario;
        	}
    	}
    	return null;
    }
        
    //Metodo para atender las fichas, donde se aplica la cola de prioridad.
    public Usuario buscarSiguienteCliente(){
    	if (tamaño!=0){
			if ( devolverCola("Persona con discapacidad").colaUsuariosP.tamaño>0 ){
				return devolverCola("Persona con discapacidad").colaUsuariosP.primero.usuario;
			}
        	else if (devolverCola("Adulto mayor").colaUsuariosP.tamaño>0){ 
        		return devolverCola("Adulto mayor").colaUsuariosP.primero.usuario;
        	}
    		else if (devolverCola("Mujer embarazada").colaUsuariosP.tamaño>0){
    			return devolverCola("Mujer embarazada").colaUsuariosP.primero.usuario;
        	}
        	else if (devolverCola("Cliente regular").colaUsuariosP.tamaño>0){
        		return devolverCola("Cliente regular").colaUsuariosP.primero.usuario;
        	}
    	}
    	return null;
    }
    public String buscarFichasEnCategoria(String categoria){
    	String clientesCat="";
    	nodo actual = devolverCola(categoria).colaUsuariosP.primero;
    	while (actual!= null){
    		clientesCat = clientesCat+actual.usuario.numFicha+"\n";
			actual = actual.siguiente;
    	}
    	return clientesCat;
    }
    public String buscarFichasTodosCliente(){
    	String clientes="";
    	if (tamaño!=0){
			if ( devolverCola("Persona con discapacidad").colaUsuariosP.tamaño>0 ){
				clientes= clientes+buscarFichasEnCategoria("Persona con discapacidad");
			}
        	if (devolverCola("Adulto mayor").colaUsuariosP.tamaño>0){ 
        		clientes= clientes+buscarFichasEnCategoria("Adulto mayor");
        	}
    		if (devolverCola("Mujer embarazada").colaUsuariosP.tamaño>0){
    			clientes= clientes+buscarFichasEnCategoria("Mujer embarazada");
        	}
        	if (devolverCola("Cliente regular").colaUsuariosP.tamaño>0){
        		clientes= clientes+buscarFichasEnCategoria("Cliente regular");
        	}
    	}
    	return clientes;
    }
    
    public String buscarFichasEnCategoriaS(String categoria){
    	String clientesCat="";
    	nodo actual = devolverCola(categoria).colaUsuariosP.primero;
    	while (actual!= null){
    		if(actual.usuario.tipoPaquete.equals("Perecedero")){
        		clientesCat = clientesCat+actual.usuario.numFicha+"\n";
    		}
    		actual = actual.siguiente;
    	}
    	actual = devolverCola(categoria).colaUsuariosP.primero;
    	while (actual!= null){
    		if(actual.usuario.tipoPaquete.equals("No perecedero")){
        		clientesCat = clientesCat+actual.usuario.numFicha+"\n";
    		}
    		actual = actual.siguiente;
    	}
    	return clientesCat;
    }
    public String buscarFichasTodosClienteS(){
    	String clientes="";
    	if (tamaño!=0){
			if ( devolverCola("Persona con discapacidad").colaUsuariosP.tamaño>0 ){
				clientes= clientes+buscarFichasEnCategoriaS("Persona con discapacidad");
			}
        	if (devolverCola("Adulto mayor").colaUsuariosP.tamaño>0){ 
        		clientes= clientes+buscarFichasEnCategoriaS("Adulto mayor");
        	}
    		if (devolverCola("Mujer embarazada").colaUsuariosP.tamaño>0){
    			clientes= clientes+buscarFichasEnCategoriaS("Mujer embarazada");
        	}
        	if (devolverCola("Cliente regular").colaUsuariosP.tamaño>0){
        		clientes= clientes+buscarFichasEnCategoriaS("Cliente regular");
        	}
    	}
    	return clientes;
    }
    
    public void imprimir(){
        nodo actual= primero;
        System.out.println("Contadores: "+ tamaño);
        while(actual != null){
           // System.out.println("------------------------------------------------");
            System.out.print("nombre usuario: ");
            System.out.println(actual.usuario.nombreUsuario);
            System.out.print("Ficha: ");
            System.out.println(actual.usuario.numFicha);
            actual = actual.siguiente;
        }
    }
}
    