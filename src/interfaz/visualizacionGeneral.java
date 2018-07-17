package interfaz;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Font;

import clases.ColaPrioridad;
import clases.ListaEnlazada;
import clases.MensajeTexto;
import clases.Usuario;

import java.awt.Component;

import net.miginfocom.swing.MigLayout;

import java.awt.TextArea;
import java.awt.Panel;
import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;

public class visualizacionGeneral {
	public JFrame ventanaPrincipal;
	public JInternalFrame Entregas;
	public JInternalFrame QuioscodeAutoservicio ;
	public JInternalFrame Seguridad ;
	public JInternalFrame Administracion;
	
	public JLabel lbCajasLibresP;
	public JLabel lbCajasLibresNP;
	public JLabel lbPantallaAtendiendo;
	public JLabel lbCantPaquetesSeguridad;
	public JLabel lbCajasLibresS ;
	public JLabel lbTotalCajasP;	
	public JLabel lbTotalCajasNP;	
	public JLabel lbTotalCajaS;
	public JLabel tipoCajaP;
	public JLabel tipoCajaNP;
	public JLabel tipoCajaS;
	public JLabel lbCantClientesP;	
	public JLabel lbCantClientesNP;	
	public JLabel lbCantClientesS;
	JLabel lbSigClienteP;	
	JLabel lbSigClienteNP;	
	JLabel lbSigClienteS;
	JLabel lbSigClienteFichaP;	
	JLabel lbSigClienteFichaNP;	
	JLabel lbSigClienteFichaS;
	
	JLabel totalClientesAtendP;	
	JLabel totalClientesAtendNP;	
	JLabel totalClientesAtendS;
	JLabel lbCantDiscap;	
	JLabel lbCantAdultoM;
	JLabel lbCantEmbarazada;	
	JLabel lbCantRegular;
	
	JLabel lbCantDiscapP;
	JLabel lbCantAdultoMP;	
	JLabel lbCantEmbarazadaP;	
	JLabel lbCantRegularP;
	
	JLabel lbCantDiscapNP;	
	JLabel lbCantAdultoMNP;	
	JLabel lbCantEmbarazadaNP;
	JLabel lbCantRegularNP;
	
	public TextArea cajasNoPerecederos;
	public TextArea cajasPerecederos;
	public TextArea cajaSeguridad;
	TextArea lbFichasP;	
	TextArea lbFichasNP;	
	TextArea lbFichasS;
	TextArea cajasCantUsuariosVentP;	
	TextArea cajasCantUsuariosVentNP;
	
	public JComboBox<String> cbVentanillaP;	
	public JComboBox<String> cbVentanillaNP;
	public JComboBox<String> cbVentLibresP;	
	public JComboBox<String> cbVentLibresNP;	
	public JComboBox<String> cbVentLibresS;
	
	public Usuario clienteAtendido = null;
	public int cantClientesAtendidos;
	public int cantClientesP=0;
	public int cantClientesNoP=0;
	public String implementacionP=null;
	public String implementacionNP=null;
	public String implementacionS=null;
	
	public int cantAtendClientesP=0;
	public int cantAtendClientesS=0;
	public int cantAtendClientesNoP=0;
		
	//-------------------------Colas de prioridad Quiosco----------------------------------------------------------
	public ColaPrioridad colaPerecedero;
			public ColaPrioridad pDiscapacitados ;
			public ColaPrioridad pAdultoMayor ;
			public ColaPrioridad pEmbarazadas ;
			public ColaPrioridad pRegular;

	public ColaPrioridad colaNoPerecedero;
			public ColaPrioridad npDiscapacitados ;
			public ColaPrioridad npAdultoMayor ;
			public ColaPrioridad npEmbarazadas ;
			public ColaPrioridad npRegular ;
			
	public ColaPrioridad colaSeguridad;
			public ColaPrioridad sDiscapacitados ;
			public ColaPrioridad sAdultoMayor ;
			public ColaPrioridad sEmbarazadas ;
			public ColaPrioridad sRegular;
			
	public ListaEnlazada listaVentPerecedero;
	public ListaEnlazada listaVentNoPerecedero;
	public ListaEnlazada listaVentSeguridad;
	
	public JTextField tfNuevoNumVentP;
	public JTextField tfNuevoNumVentNP;
	public JTextField tfNuevoNumVentS;
	
	
	//-------------------------Ventanillas----------------------------------------------------------	

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigInicial frame = new ConfigInicial();
					frame.setVisible(true);
					visualizacionGeneral window = new visualizacionGeneral();
					window.ventanaPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	public void atenderSeguridadHilo(){
	}
	public void configurarVentanillas(int cantidad, ListaEnlazada lista){
		int contador=0;		
		while(contador!= cantidad){
			lista.agregarVentanillas();
			//listaVentPerecedero.agregarVentanillas();
			contador++;
		}
		actualizarDatos();
		actualizarDatosAdm();
	} 
	
	public void atenderClienteNoPerecedero(){
		clienteAtendido = colaNoPerecedero.atenderCliente();
		if(clienteAtendido!= null){
			cantClientesNoP--;
			cantAtendClientesNoP++;
			lbPantallaAtendiendo.setText(clienteAtendido.numFicha+" en "+listaVentNoPerecedero.ocuparVentanilla().agregarUsuarioVentanilla(clienteAtendido));
			
			if(colaNoPerecedero.buscarSiguienteCliente() != null){
				lbSigClienteNP.setText(colaNoPerecedero.buscarSiguienteCliente().nombreUsuario);
				lbSigClienteFichaNP.setText(colaNoPerecedero.buscarSiguienteCliente().numFicha);							
			}
			else{
				lbSigClienteNP.setText("Libre");
				lbSigClienteFichaNP.setText("-");
			}
			cajasCantUsuariosVentNP.setText(listaVentNoPerecedero.clientesAtendidosPorVentanilla());
		}
		lbFichasNP.setText(colaNoPerecedero.buscarFichasTodosCliente());
		
	}
	
	public void atenderClientePerecedero(){
		clienteAtendido = colaPerecedero.atenderCliente();
		if(clienteAtendido!= null){
			cantClientesP--;
			cantAtendClientesP++;
			lbPantallaAtendiendo.setText(clienteAtendido.numFicha+" en "+listaVentPerecedero.ocuparVentanilla().agregarUsuarioVentanilla(clienteAtendido));
			if(colaPerecedero.buscarSiguienteCliente()!= null){
				lbSigClienteP.setText(colaPerecedero.buscarSiguienteCliente().nombreUsuario);
				lbSigClienteFichaP.setText(colaPerecedero.buscarSiguienteCliente().numFicha);
			}
			else{
				lbSigClienteP.setText("Libre");
				lbSigClienteFichaP.setText("-");
			}
			cajasCantUsuariosVentP.setText(listaVentPerecedero.clientesAtendidosPorVentanilla());
		}
		lbFichasP.setText(colaPerecedero.buscarFichasTodosCliente());
	}
	
	public void actualizarDatosAdm(){
		lbCantClientesP.setText(cantClientesP+"");
		lbCantClientesNP.setText(cantClientesNoP+"");
		lbCantClientesS.setText(cantClientesAtendidos+"");
	}
	
	public void actualizarDatos(){
		//Actualiza los cuadros donde aparecen las ventanillas y sus estados
		cajasNoPerecederos.setText(listaVentNoPerecedero.recorrerLista());
		cajasPerecederos.setText(listaVentPerecedero.recorrerLista());
		cajaSeguridad.setText(listaVentSeguridad.recorrerLista());
		lbTotalCajasP.setText(listaVentPerecedero.tamaño+"");
		lbTotalCajasNP.setText(listaVentNoPerecedero.tamaño+"");
		lbTotalCajaS.setText(listaVentSeguridad.tamaño+"");
		totalClientesAtendNP.setText(cantAtendClientesNoP+"");
		totalClientesAtendP.setText(cantAtendClientesP+"");
		totalClientesAtendS.setText(cantAtendClientesS+"");	
		lbCantClientesS.setText(cantClientesAtendidos+"");
		lbCantPaquetesSeguridad.setText(cantClientesAtendidos+"");
		
		
		listaVentNoPerecedero.actual = listaVentNoPerecedero.primero;
		cbVentanillaNP.removeAllItems();
		cbVentLibresNP.removeAllItems();
		
		cbVentLibresNP.addItem("");
		cbVentLibresNP.addItem("Todos");
		while(listaVentNoPerecedero.actual != null){	 
		   if(listaVentNoPerecedero.actual.estado.equals("ocupada")){
			   cbVentanillaNP.addItem(listaVentNoPerecedero.actual.nombreVentanilla);
		   }
		   else
			   cbVentLibresNP.addItem(listaVentNoPerecedero.actual.nombreVentanilla);
		   listaVentNoPerecedero.actual = listaVentNoPerecedero.actual.siguiente;
		}
				
		listaVentSeguridad.actual = listaVentSeguridad.primero;
		cbVentLibresS.removeAllItems();
		
		cbVentLibresS.addItem("");
		cbVentLibresS.addItem("Todos");

		
		listaVentPerecedero.actual = listaVentPerecedero.primero;
		cbVentanillaP.removeAllItems();
		cbVentLibresP.removeAllItems();
		
		cbVentLibresP.addItem("");
		cbVentLibresP.addItem("Todos");
		while(listaVentPerecedero.actual != null){	 
		   if(listaVentPerecedero.actual.estado.equals("ocupada")){
			   cbVentanillaP.addItem(listaVentPerecedero.actual.nombreVentanilla);
		   }
		   else
			   cbVentLibresP.addItem(listaVentPerecedero.actual.nombreVentanilla);
		   listaVentPerecedero.actual = listaVentPerecedero.actual.siguiente;
		}
	    
		lbCajasLibresP.setText(listaVentPerecedero.ventanillasLibres+"");
		lbCajasLibresNP.setText(listaVentNoPerecedero.ventanillasLibres+"");
		lbCajasLibresS.setText(listaVentSeguridad.ventanillasLibres+"");		
	}
	
	public visualizacionGeneral() {
		initialize();
	}

	private void initialize() {	
		ventanaPrincipal = new JFrame();
		ventanaPrincipal.setResizable(false);
		ventanaPrincipal.setBounds(0, 0, 1321, 725);
		ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//-------------------------------------------------------------------------------------------------
		JTextField tfNombreUsuario;
		JTextField lbCorreo;
		JTextField tfNumCel;
		QuioscodeAutoservicio = new JInternalFrame("Quiosco de Autoservicio");
		QuioscodeAutoservicio.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		JPanel Quisco = new JPanel();
		Quisco.setLayout(null);
		
		JLabel label = new JLabel("Nombre del usuario");
		label.setBounds(10, 11, 149, 24);
		Quisco.add(label);
		
		JLabel label_1 = new JLabel("Correo electronico");
		label_1.setBounds(10, 46, 128, 24);
		Quisco.add(label_1);
		
		JLabel label_2 = new JLabel("Tipo de paquete");
		label_2.setBounds(10, 132, 115, 24);
		Quisco.add(label_2);
		
		JComboBox cbTipoPaq = new JComboBox();
		cbTipoPaq.setModel(new DefaultComboBoxModel(new String[] {"Perecedero", "No perecedero"}));
		cbTipoPaq.setBounds(148, 134, 198, 20);
		Quisco.add(cbTipoPaq);
		
		JComboBox cbCategoriasUsuarios = new JComboBox();
		cbCategoriasUsuarios.setModel(new DefaultComboBoxModel(new String[] {"Persona con discapacidad", "Adulto mayor", "Mujer embarazada", "Cliente regular"}));
		cbCategoriasUsuarios.setSelectedIndex(3);
		cbCategoriasUsuarios.setBounds(148, 103, 198, 20);
		Quisco.add(cbCategoriasUsuarios);
		
		JLabel label_3 = new JLabel("Tipo de usuario");
		label_3.setBounds(10, 101, 115, 24);
		Quisco.add(label_3);
		
		tfNombreUsuario = new JTextField();
		tfNombreUsuario.setColumns(10);
		tfNombreUsuario.setBounds(148, 13, 198, 20);
		Quisco.add(tfNombreUsuario);
		
		lbCorreo = new JTextField();
		lbCorreo.setColumns(10);
		lbCorreo.setBounds(148, 46, 198, 20);
		Quisco.add(lbCorreo);
		
		JButton btnPedirFicha = new JButton("Solicitar Ficha");

		ventanaPrincipal.getContentPane().setLayout(new MigLayout("", "[378.00px][381.00px][526.00px]", "[335.00px][287.00px][212.00]"));
		btnPedirFicha.setBounds(115, 183, 115, 23);
		Quisco.add(btnPedirFicha);
		
		tfNumCel = new JTextField();
		tfNumCel.setColumns(10);
		tfNumCel.setBounds(148, 77, 198, 20);
		Quisco.add(tfNumCel);
		
		JLabel lblNmDeTelfono = new JLabel("Tel\u00E9fono");
		lblNmDeTelfono.setBounds(10, 75, 115, 24);
		Quisco.add(lblNmDeTelfono);
		GroupLayout groupLayout = new GroupLayout(QuioscodeAutoservicio.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(Quisco, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(Quisco, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		QuioscodeAutoservicio.getContentPane().setLayout(groupLayout);
		ventanaPrincipal.getContentPane().add(QuioscodeAutoservicio, "cell 0 0,grow");
		//-------------------------------------------------------------------------------------------------
		Entregas = new JInternalFrame("Entregas");
		JPanel blue = new JPanel();
		GroupLayout groupLayout_1 = new GroupLayout(Entregas.getContentPane());
		groupLayout_1.setHorizontalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addComponent(blue, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
		);
		groupLayout_1.setVerticalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout_1.createSequentialGroup()
					.addComponent(blue, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
					.addContainerGap())
		);
		blue.setLayout(null);
		JLabel lblPerecederos = new JLabel("Perecederos");
		lblPerecederos.setForeground(new Color(0, 0, 139));
		lblPerecederos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPerecederos.setBounds(10, 11, 127, 14);
		blue.add(lblPerecederos);
		JLabel lblNoPerecederos = new JLabel("No Perecederos");
		lblNoPerecederos.setForeground(new Color(0, 0, 139));
		lblNoPerecederos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNoPerecederos.setBounds(181, 11, 127, 14);
		blue.add(lblNoPerecederos);
		
		cajasPerecederos = new TextArea();
		cajasPerecederos.setEditable(false);
		cajasPerecederos.setBounds(10, 56, 165, 200);
		blue.add(cajasPerecederos);
		
		cajasNoPerecederos = new TextArea();
		cajasNoPerecederos.setEditable(false);
		cajasNoPerecederos.setBounds(181, 56, 181, 200);
		blue.add(cajasNoPerecederos);
		
		JButton btnAtenderP = new JButton("Atender");
		btnAtenderP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//if((listaVentPerecedero.ventanillasLibres>0)&& (cantClientesP>0)){
				if(listaVentPerecedero.ventanillasLibres>0){
					atenderClientePerecedero();
				}
				actualizarDatos();
				actualizarDatosAdm();
				clienteAtendido = null;
			}
		});
		btnAtenderP.setBounds(22, 280, 135, 23);
		blue.add(btnAtenderP);
		
		JButton btnAtenderLibP = new JButton("Liberar y Atender");
		btnAtenderLibP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object cajaOcup1 =  cbVentanillaP.getSelectedItem();
		        String cajaOcup = String.valueOf(cajaOcup1);
	        	clienteAtendido= listaVentPerecedero.obtenerVentanilla(cajaOcup).sacarUsuarioVentanilla();
		        listaVentPerecedero.liberarVentanilla(cajaOcup).sacarUsuarioVentanilla();

		        if(clienteAtendido!=null){
		        	//clienteAtendido.horaIngreso="hora en que entra aqui";
		        	colaSeguridad.devolverCola(clienteAtendido.tipoUsuario).colaUsuariosP.encolar(clienteAtendido);
		        	//Aqui meto lo de los hilos
		        	lbFichasS.setText(colaSeguridad.buscarFichasTodosClienteS());
		        	cantClientesAtendidos++;
		        	lbCantPaquetesSeguridad.setText(cantClientesAtendidos+"");
		        }      
		        atenderClientePerecedero();
		        actualizarDatos();
		        actualizarDatosAdm();
		        clienteAtendido = null;
			}
		});
		btnAtenderLibP.setBounds(22, 348, 135, 23);
		blue.add(btnAtenderLibP);

		JButton btnAtenderNP = new JButton("Atender");
		btnAtenderNP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(listaVentNoPerecedero.ventanillasLibres>0){
					atenderClienteNoPerecedero();
				}
				actualizarDatos();
				actualizarDatosAdm();
				clienteAtendido = null;
			}
		});
		btnAtenderNP.setBounds(193, 280, 135, 23);
		blue.add(btnAtenderNP);
		
		JButton btnAtenderLibNP = new JButton("Liberar y Atender");
		btnAtenderLibNP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object cajaOcup1 =  cbVentanillaNP.getSelectedItem();
		        String cajaOcup = String.valueOf(cajaOcup1);
	        	clienteAtendido= listaVentNoPerecedero.obtenerVentanilla(cajaOcup).sacarUsuarioVentanilla();
	        	listaVentNoPerecedero.liberarVentanilla(cajaOcup).sacarUsuarioVentanilla();
			        
		        if(clienteAtendido!=null){
		        	//clienteAtendido.horaIngreso="hola en que entra aqui";
		        	colaSeguridad.devolverCola(clienteAtendido.tipoUsuario).colaUsuariosP.encolar(clienteAtendido);
		        	lbFichasS.setText(colaSeguridad.buscarFichasTodosClienteS());
		        	cantClientesAtendidos++;
		        	lbCantPaquetesSeguridad.setText(cantClientesAtendidos+"");
		        }
		        atenderClienteNoPerecedero();
		        actualizarDatos();
		        actualizarDatosAdm();
		        clienteAtendido = null;
			}
		});
		
		btnAtenderLibNP.setBounds(193, 348, 135, 23);
		blue.add(btnAtenderLibNP);
		
		JLabel lblCajasLibres = new JLabel("Cajas libres:");
		lblCajasLibres.setBounds(10, 37, 83, 13);
		blue.add(lblCajasLibres);
		
		JLabel lblCajasLibres_1 = new JLabel("Cajas libres:");
		lblCajasLibres_1.setBounds(181, 37, 76, 13);
		blue.add(lblCajasLibres_1);
		
		lbCajasLibresP = new JLabel("0");
		lbCajasLibresP.setBounds(103, 36, 34, 14);
		blue.add(lbCajasLibresP);
		
		lbCajasLibresNP = new JLabel("0");
		lbCajasLibresNP.setBounds(287, 36, 34, 14);
		blue.add(lbCajasLibresNP);
		
		cbVentanillaP = new JComboBox();
		cbVentanillaP.setBounds(22, 314, 135, 20);
		blue.add(cbVentanillaP);
		
		cbVentanillaNP = new JComboBox();
		cbVentanillaNP.setBounds(193, 314, 135, 20);
		blue.add(cbVentanillaNP);
		Entregas.getContentPane().setLayout(groupLayout_1);
		ventanaPrincipal.getContentPane().add(Entregas, "cell 1 0 1 2,grow");
		
		//------------------------------------------------------------------------------------------------------
		Administracion = new JInternalFrame("Administraci\u00F3n");
		ventanaPrincipal.getContentPane().add(Administracion, "cell 2 0 1 3,grow");
		Administracion.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		JPanel panelSeguridad = new JPanel();
		panelSeguridad.setLayout(null);
		Administracion.getContentPane().add(panelSeguridad);
		
		JLabel lblEstadoDeLas = new JLabel("Estado de las colas");
		lblEstadoDeLas.setForeground(new Color(0, 0, 139));
		lblEstadoDeLas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEstadoDeLas.setBounds(10, 11, 167, 14);
		panelSeguridad.add(lblEstadoDeLas);
		
		JLabel lblPerecederos_1 = new JLabel("Perecederos");
		lblPerecederos_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPerecederos_1.setBounds(113, 28, 90, 14);
		panelSeguridad.add(lblPerecederos_1);
		
		JLabel lblNoPerecederos_1 = new JLabel("No Perecederos");
		lblNoPerecederos_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNoPerecederos_1.setBounds(228, 28, 114, 14);
		panelSeguridad.add(lblNoPerecederos_1);
		
		JLabel lb = new JLabel("Cajas:");
		lb.setFont(new Font("Tahoma", Font.BOLD, 11));
		lb.setBounds(10, 53, 39, 14);
		panelSeguridad.add(lb);
		
		lbTotalCajasP = new JLabel("0");
		lbTotalCajasP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbTotalCajasP.setBounds(137, 53, 22, 14);
		panelSeguridad.add(lbTotalCajasP);
		
		lbTotalCajasNP = new JLabel("0");
		lbTotalCajasNP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbTotalCajasNP.setBounds(252, 53, 22, 14);
		panelSeguridad.add(lbTotalCajasNP);
		
		lbTotalCajaS = new JLabel("0");
		lbTotalCajaS.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbTotalCajaS.setBounds(383, 53, 13, 14);
		panelSeguridad.add(lbTotalCajaS);
		
		JLabel lblSeguridad_1 = new JLabel("Seguridad");
		lblSeguridad_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSeguridad_1.setBounds(365, 23, 69, 25);
		panelSeguridad.add(lblSeguridad_1);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTipo.setBounds(10, 74, 39, 14);
		panelSeguridad.add(lblTipo);
		
		tipoCajaP = new JLabel("\"\"");
		tipoCajaP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tipoCajaP.setBounds(137, 74, 67, 14);
		panelSeguridad.add(tipoCajaP);
		
		tipoCajaNP = new JLabel("\"\"");
		tipoCajaNP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tipoCajaNP.setBounds(252, 74, 80, 14);
		panelSeguridad.add(tipoCajaNP);
		
		tipoCajaS = new JLabel("\"\"");
		tipoCajaS.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tipoCajaS.setBounds(383, 74, 71, 14);
		panelSeguridad.add(tipoCajaS);
		
		JLabel lblClientes = new JLabel("Clientes espera:");
		lblClientes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblClientes.setBounds(10, 92, 101, 14);
		panelSeguridad.add(lblClientes);
		
		lbCantClientesP = new JLabel("0");
		lbCantClientesP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbCantClientesP.setBounds(137, 92, 67, 14);
		panelSeguridad.add(lbCantClientesP);
		
		lbCantClientesNP = new JLabel("0");
		lbCantClientesNP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbCantClientesNP.setBounds(252, 92, 80, 14);
		panelSeguridad.add(lbCantClientesNP);
		
		lbCantClientesS = new JLabel("0");
		lbCantClientesS.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbCantClientesS.setBounds(383, 92, 71, 14);
		panelSeguridad.add(lbCantClientesS);
		
		lbSigClienteP = new JLabel("Libre");
		lbSigClienteP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbSigClienteP.setBounds(137, 110, 105, 14);
		panelSeguridad.add(lbSigClienteP);
		
		lbSigClienteNP = new JLabel("Libre");
		lbSigClienteNP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbSigClienteNP.setBounds(252, 110, 121, 14);
		panelSeguridad.add(lbSigClienteNP);
		
		lbSigClienteS = new JLabel("Libre");
		lbSigClienteS.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbSigClienteS.setBounds(383, 110, 87, 14);
		panelSeguridad.add(lbSigClienteS);
		
		JLabel lblClientesSiguiente = new JLabel("Siguiente cliente:");
		lblClientesSiguiente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblClientesSiguiente.setBounds(10, 110, 117, 14);
		panelSeguridad.add(lblClientesSiguiente);
		
		lbSigClienteFichaP = new JLabel("-");
		lbSigClienteFichaP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbSigClienteFichaP.setBounds(137, 127, 105, 14);
		panelSeguridad.add(lbSigClienteFichaP);
		
		lbSigClienteFichaNP = new JLabel("-");
		lbSigClienteFichaNP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbSigClienteFichaNP.setBounds(252, 127, 121, 14);
		panelSeguridad.add(lbSigClienteFichaNP);
		
		lbSigClienteFichaS = new JLabel("-");
		lbSigClienteFichaS.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbSigClienteFichaS.setBounds(383, 127, 87, 14);
		panelSeguridad.add(lbSigClienteFichaS);
		
		lbFichasP = new TextArea();
		lbFichasP.setEditable(false);
		lbFichasP.setBounds(113, 147, 90, 87);
		panelSeguridad.add(lbFichasP);
		
		JLabel lblNFicha = new JLabel("N\u00B0 Ficha:");
		lblNFicha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNFicha.setBounds(10, 127, 117, 14);
		panelSeguridad.add(lblNFicha);
		
		JLabel lblFichasEnLa = new JLabel("Fichas en la cola");
		lblFichasEnLa.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFichasEnLa.setBounds(10, 165, 101, 25);
		panelSeguridad.add(lblFichasEnLa);
		
		lbFichasNP = new TextArea();
		lbFichasNP.setEditable(false);
		lbFichasNP.setBounds(228, 147, 90, 87);
		panelSeguridad.add(lbFichasNP);
		
		lbFichasS = new TextArea();
		lbFichasS.setEditable(false);
		lbFichasS.setBounds(356, 147, 90, 87);
		panelSeguridad.add(lbFichasS);
		
		JLabel lblAdministracinDeVentanillas = new JLabel("Administraci\u00F3n de Ventanillas");
		lblAdministracinDeVentanillas.setForeground(new Color(0, 0, 139));
		lblAdministracinDeVentanillas.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAdministracinDeVentanillas.setBounds(10, 250, 242, 14);
		panelSeguridad.add(lblAdministracinDeVentanillas);
		
		JLabel lblPerecederos_2 = new JLabel("Perecederos:");
		lblPerecederos_2.setVerticalAlignment(SwingConstants.TOP);
		lblPerecederos_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPerecederos_2.setBounds(10, 287, 101, 19);
		panelSeguridad.add(lblPerecederos_2);
		
		tfNuevoNumVentP = new JTextField();
		tfNuevoNumVentP.setText("0");
		tfNuevoNumVentP.setBounds(148, 286, 47, 20);
		panelSeguridad.add(tfNuevoNumVentP);
		tfNuevoNumVentP.setColumns(10);
		
		JLabel lblNoPerecederos_2 = new JLabel("No Perecederos:");
		lblNoPerecederos_2.setVerticalAlignment(SwingConstants.TOP);
		lblNoPerecederos_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNoPerecederos_2.setBounds(10, 305, 101, 19);
		panelSeguridad.add(lblNoPerecederos_2);
		
		tfNuevoNumVentNP = new JTextField();
		tfNuevoNumVentNP.setText("0");
		tfNuevoNumVentNP.setColumns(10);
		tfNuevoNumVentNP.setBounds(148, 304, 47, 20);
		panelSeguridad.add(tfNuevoNumVentNP);
		
		JLabel lblSeguridad_2 = new JLabel("Seguridad:");
		lblSeguridad_2.setVerticalAlignment(SwingConstants.TOP);
		lblSeguridad_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSeguridad_2.setBounds(10, 323, 101, 19);
		panelSeguridad.add(lblSeguridad_2);
		
		tfNuevoNumVentS = new JTextField();
		tfNuevoNumVentS.setText("0");
		tfNuevoNumVentS.setColumns(10);
		tfNuevoNumVentS.setBounds(148, 322, 47, 20);
		panelSeguridad.add(tfNuevoNumVentS);
		
		JButton btnActualizarNuevasVent = new JButton("Agregar");
		btnActualizarNuevasVent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Actualiza la cant de ventanillas de perecederos
				String strCantPerecederos = tfNuevoNumVentP.getText();
				int cantPerecederos = Integer.parseInt(strCantPerecederos);		
								
				//Actualiza la cant de ventanillas de no perecederos
				String strCantNoPerecederos = tfNuevoNumVentNP.getText();
				int cantNoPerecederos = Integer.parseInt(strCantNoPerecederos);		
				
				//Actualiza la cant de ventanillas de seguridad perecederos
				String strCantSeguridad = tfNuevoNumVentS.getText();
				int cantSeguridad = Integer.parseInt(strCantSeguridad);	
				
				String datosVentana = "Perecedero: "+cantPerecederos+
						"\nNo Perecedero: "+cantNoPerecederos+
						"\nSeguridad: "+cantSeguridad;
				JOptionPane.showMessageDialog(panelSeguridad, datosVentana+"\nLa configuración ha sido guardada exitosamente");
				
				configurarVentanillas(cantNoPerecederos,listaVentNoPerecedero);
				configurarVentanillas(cantPerecederos,listaVentPerecedero);
				configurarVentanillas(cantSeguridad,listaVentSeguridad);
				
				tfNuevoNumVentP.setText("0");
				tfNuevoNumVentNP.setText("0");
				tfNuevoNumVentS.setText("0");
			}
		});
		btnActualizarNuevasVent.setBounds(38, 352, 108, 23);
		panelSeguridad.add(btnActualizarNuevasVent);
		
		JLabel lblAgregarVentanillas = new JLabel("Agregar ventanillas:");
		lblAgregarVentanillas.setVerticalAlignment(SwingConstants.TOP);
		lblAgregarVentanillas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAgregarVentanillas.setBounds(42, 265, 117, 19);
		panelSeguridad.add(lblAgregarVentanillas);
		
		JLabel lblEliminarVentanillas = new JLabel("Eliminar ventanillas:");
		lblEliminarVentanillas.setVerticalAlignment(SwingConstants.TOP);
		lblEliminarVentanillas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEliminarVentanillas.setBounds(281, 265, 117, 19);
		panelSeguridad.add(lblEliminarVentanillas);
		
		cbVentLibresP = new JComboBox();
		cbVentLibresP.setBounds(281, 287, 149, 20);
		panelSeguridad.add(cbVentLibresP);
		
		cbVentLibresNP = new JComboBox();
		cbVentLibresNP.setBounds(281, 305, 149, 20);
		panelSeguridad.add(cbVentLibresNP);
		
		cbVentLibresS = new JComboBox();
		cbVentLibresS.setBounds(281, 321, 149, 20);
		panelSeguridad.add(cbVentLibresS);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object cajaLibNP1 =  cbVentLibresNP.getSelectedItem();
		        String cajaLibNP = String.valueOf(cajaLibNP1);
		        
		        Object cajaLibP1 =  cbVentLibresP.getSelectedItem();
		        String cajaLibP = String.valueOf(cajaLibP1);
		        
		        Object cajaOcupS1 =  cbVentLibresS.getSelectedItem();
		        String cajaOcupS = String.valueOf(cajaOcupS1);
		        
		        if(cajaLibNP.equals("Todos")){
		        	listaVentNoPerecedero.eliminarTodasVentanillas();
		        }
		        else if(cajaLibNP!=""){
		        	listaVentNoPerecedero.eliminarVentanilla(cajaLibNP);
		        }     
						        
		        if(cajaLibP.equals("Todos")){
		        	listaVentPerecedero.eliminarTodasVentanillas();
		        }
		        else if(cajaLibP!=""){
		        	listaVentPerecedero.eliminarVentanilla(cajaLibP);
		        }	
		        		        
		        if(cajaOcupS.equals("Todos")){
		        	listaVentSeguridad.eliminarTodasVentanillas();
		        }
		        else if(cajaOcupS!=""){
		        	listaVentSeguridad.eliminarVentanilla(cajaOcupS);
		        }
		        
				JOptionPane.showMessageDialog(panelSeguridad, "\nSe ha eliminado exitosamente la ventanilla");
		        actualizarDatos();
		        actualizarDatosAdm();
				
			}
		});
		btnEliminar.setBounds(317, 352, 101, 23);
		panelSeguridad.add(btnEliminar);
		
		JLabel lblEstadsticas = new JLabel("Estad\u00EDsticas");
		lblEstadsticas.setForeground(new Color(0, 0, 139));
		lblEstadsticas.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEstadsticas.setBounds(10, 386, 242, 14);
		panelSeguridad.add(lblEstadsticas);
		
		JLabel label_8 = new JLabel("Perecederos");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_8.setBounds(252, 430, 71, 14);
		panelSeguridad.add(label_8);
		
		JLabel label_9 = new JLabel("No Perecederos");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_9.setBounds(252, 446, 80, 14);
		panelSeguridad.add(label_9);
		
		JLabel label_10 = new JLabel("Seguridad");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_10.setBounds(252, 459, 69, 25);
		panelSeguridad.add(label_10);
		
		JLabel lblClientesAtendidos = new JLabel("Clientes atendidos:");
		lblClientesAtendidos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblClientesAtendidos.setBounds(252, 411, 117, 14);
		panelSeguridad.add(lblClientesAtendidos);
		
		totalClientesAtendP = new JLabel("0");
		totalClientesAtendP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		totalClientesAtendP.setBounds(333, 430, 22, 14);
		panelSeguridad.add(totalClientesAtendP);
		
		totalClientesAtendNP = new JLabel("0");
		totalClientesAtendNP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		totalClientesAtendNP.setBounds(333, 446, 22, 14);
		panelSeguridad.add(totalClientesAtendNP);
		
		totalClientesAtendS = new JLabel("0");
		totalClientesAtendS.setFont(new Font("Tahoma", Font.PLAIN, 11));
		totalClientesAtendS.setBounds(333, 464, 20, 14);
		panelSeguridad.add(totalClientesAtendS);
		
		JLabel lblTotalDeFichas = new JLabel("Fichas dispensadas por tipo de usuario");
		lblTotalDeFichas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalDeFichas.setBounds(11, 411, 223, 14);
		panelSeguridad.add(lblTotalDeFichas);
		
		JLabel lblDiscapacitados = new JLabel("Discapacitados");
		lblDiscapacitados.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDiscapacitados.setBounds(10, 446, 71, 14);
		panelSeguridad.add(lblDiscapacitados);
		
		JLabel lblEmbarazadas = new JLabel("Adulto Mayor");
		lblEmbarazadas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEmbarazadas.setBounds(10, 462, 80, 14);
		panelSeguridad.add(lblEmbarazadas);
		
		JLabel lblEmbarazada = new JLabel("Embarazada");
		lblEmbarazada.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEmbarazada.setBounds(10, 480, 80, 14);
		panelSeguridad.add(lblEmbarazada);
		
		JLabel lblRegular = new JLabel("Regular");
		lblRegular.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblRegular.setBounds(10, 497, 80, 14);
		panelSeguridad.add(lblRegular);
		
		lbCantDiscap = new JLabel("0");
		lbCantDiscap.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbCantDiscap.setBounds(91, 446, 24, 14);
		panelSeguridad.add(lbCantDiscap);
		
		lbCantAdultoM = new JLabel("0");
		lbCantAdultoM.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbCantAdultoM.setBounds(91, 462, 24, 14);
		panelSeguridad.add(lbCantAdultoM);
		
		lbCantEmbarazada = new JLabel("0");
		lbCantEmbarazada.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbCantEmbarazada.setBounds(91, 480, 24, 14);
		panelSeguridad.add(lbCantEmbarazada);
		
		lbCantRegular = new JLabel("0");
		lbCantRegular.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbCantRegular.setBounds(91, 497, 24, 14);
		panelSeguridad.add(lbCantRegular);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotal.setBounds(74, 430, 47, 14);
		panelSeguridad.add(lblTotal);
		
		JLabel lblPerecedero = new JLabel("P");
		lblPerecedero.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPerecedero.setBounds(149, 430, 30, 14);
		panelSeguridad.add(lblPerecedero);
		
		lbCantDiscapP = new JLabel("0");
		lbCantDiscapP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbCantDiscapP.setBounds(149, 446, 24, 14);
		panelSeguridad.add(lbCantDiscapP);
		
		lbCantAdultoMP = new JLabel("0");
		lbCantAdultoMP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbCantAdultoMP.setBounds(149, 462, 24, 14);
		panelSeguridad.add(lbCantAdultoMP);
		
		lbCantEmbarazadaP = new JLabel("0");
		lbCantEmbarazadaP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbCantEmbarazadaP.setBounds(149, 480, 24, 14);
		panelSeguridad.add(lbCantEmbarazadaP);
		
		JLabel lbCantRegularP = new JLabel("0");
		lbCantRegularP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbCantRegularP.setBounds(149, 497, 24, 14);
		panelSeguridad.add(lbCantRegularP);
		
		JLabel lblNp = new JLabel("NP");
		lblNp.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNp.setBounds(203, 430, 34, 14);
		panelSeguridad.add(lblNp);
		
		lbCantDiscapNP = new JLabel("0");
		lbCantDiscapNP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbCantDiscapNP.setBounds(207, 446, 24, 14);
		panelSeguridad.add(lbCantDiscapNP);
		
		lbCantAdultoMNP = new JLabel("0");
		lbCantAdultoMNP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbCantAdultoMNP.setBounds(207, 462, 24, 14);
		panelSeguridad.add(lbCantAdultoMNP);
		
		lbCantEmbarazadaNP = new JLabel("0");
		lbCantEmbarazadaNP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbCantEmbarazadaNP.setBounds(207, 480, 24, 14);
		panelSeguridad.add(lbCantEmbarazadaNP);
		
		lbCantRegularNP = new JLabel("0");
		lbCantRegularNP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbCantRegularNP.setBounds(207, 497, 24, 14);
		panelSeguridad.add(lbCantRegularNP);
		
		JLabel lblClientesAtendidosPor = new JLabel("Clientes atendidos por ventanilla");
		lblClientesAtendidosPor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblClientesAtendidosPor.setBounds(237, 489, 233, 14);
		panelSeguridad.add(lblClientesAtendidosPor);
		
		JLabel lblPerecedero_1 = new JLabel("Perecedero");
		lblPerecedero_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPerecedero_1.setBounds(241, 508, 101, 14);
		panelSeguridad.add(lblPerecedero_1);
		
		JLabel lblNoPerecedero = new JLabel("No Perecedero");
		lblNoPerecedero.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNoPerecedero.setBounds(372, 508, 128, 14);
		panelSeguridad.add(lblNoPerecedero);
		
		cajasCantUsuariosVentP = new TextArea();
		cajasCantUsuariosVentP.setEditable(false);
		cajasCantUsuariosVentP.setBounds(237, 528, 128, 114);
		panelSeguridad.add(cajasCantUsuariosVentP);
		
		cajasCantUsuariosVentNP = new TextArea();
		cajasCantUsuariosVentNP.setEditable(false);
		cajasCantUsuariosVentNP.setBounds(372, 528, 138, 114);
		panelSeguridad.add(cajasCantUsuariosVentNP);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(Color.BLACK);
		textPane.setForeground(Color.MAGENTA);
		textPane.setEnabled(false);
		textPane.setEditable(false);
		textPane.setBounds(230, 415, 3, 230);
		panelSeguridad.add(textPane);
		Administracion.setVisible(true);
		
		//-------------------------------------------------------------------------------------------------
		Seguridad = new JInternalFrame("Seguridad");
		ventanaPrincipal.getContentPane().add(Seguridad, "cell 0 1 1 2,grow");
		
		JPanel red = new JPanel();
		GroupLayout groupLayout_2 = new GroupLayout(Seguridad.getContentPane());
		groupLayout_2.setHorizontalGroup(
			groupLayout_2.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout_2.createSequentialGroup()
					.addComponent(red, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(49, Short.MAX_VALUE))
		);
		groupLayout_2.setVerticalGroup(
			groupLayout_2.createParallelGroup(Alignment.LEADING)
				.addComponent(red, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
		);
		red.setLayout(null);
		
		cajaSeguridad = new TextArea();
		cajaSeguridad.setEditable(false);
		cajaSeguridad.setBounds(10, 10, 177, 227);
		red.add(cajaSeguridad);
		
		JLabel lblSeguridad = new JLabel("Seguridad");
		lblSeguridad.setForeground(new Color(0, 0, 139));
		lblSeguridad.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSeguridad.setBounds(205, 10, 103, 22);
		red.add(lblSeguridad);
		
		JLabel label_7 = new JLabel("Cajas libres:");
		label_7.setBounds(205, 44, 83, 13);
		red.add(label_7);
		
		lbCajasLibresS = new JLabel("0");
		lbCajasLibresS.setBounds(298, 43, 34, 14);
		red.add(lbCajasLibresS);
		
		JButton button = new JButton("Atender");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(listaVentSeguridad.ventanillasLibres>0){
					clienteAtendido = colaSeguridad.atenderClienteSeguridad();
					if(clienteAtendido!= null){
						cantClientesAtendidos--;
						cantAtendClientesS++;
						lbPantallaAtendiendo.setText(clienteAtendido.numFicha+" en "+listaVentSeguridad.ocuparVentanilla().agregarUsuarioVentanilla(clienteAtendido));
						if(colaSeguridad.buscarSiguienteClienteSeguridad()!= null){
							
							lbSigClienteS.setText(colaSeguridad.buscarSiguienteClienteSeguridad().nombreUsuario);
							lbSigClienteFichaS.setText(colaSeguridad.buscarSiguienteClienteSeguridad().numFicha);
						}
						else{
							lbSigClienteS.setText("Libre");
							lbSigClienteFichaS.setText("-");
						}
					}
				}
				actualizarDatos();
				actualizarDatosAdm();
				clienteAtendido = null;
				lbFichasS.setText(colaSeguridad.buscarFichasTodosClienteS());
			}
				
		});
		button.setBounds(205, 81, 138, 23);
		red.add(button);
		
		JLabel lblPaquetes = new JLabel("Paquetes en espera");
		lblPaquetes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPaquetes.setBounds(216, 190, 127, 22);
		red.add(lblPaquetes);
		
		lbCantPaquetesSeguridad = new JLabel("0");
		lbCantPaquetesSeguridad.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbCantPaquetesSeguridad.setBounds(261, 215, 41, 22);
		red.add(lbCantPaquetesSeguridad);
		Seguridad.getContentPane().setLayout(groupLayout_2);
		Seguridad.setVisible(true);
		//-------------------------------------------------------------------------------------------------
		JInternalFrame pantalla = new JInternalFrame("Pantalla");
		ventanaPrincipal.getContentPane().add(pantalla, "cell 1 2,grow");
		pantalla.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		Panel panelPantalla = new Panel();
		panelPantalla.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Atendiendo cliente:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(27, 11, 216, 22);
		panelPantalla.add(lblNewLabel);
		pantalla.getContentPane().add(panelPantalla);
		
		lbPantallaAtendiendo = new JLabel("");
		lbPantallaAtendiendo.setForeground(Color.RED);
		lbPantallaAtendiendo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbPantallaAtendiendo.setBounds(27, 45, 336, 42);
		panelPantalla.add(lbPantallaAtendiendo);
		pantalla.setVisible(true);
		//-----------------------------------------------------------------------------------------------------------
		Entregas.setVisible(true);
		QuioscodeAutoservicio.setVisible(true);
		
		btnPedirFicha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Object tipoPaquete1 =  cbTipoPaq.getSelectedItem();
		        String tipoPaquete = String.valueOf(tipoPaquete1);
				Object tipoUsuario1 =  cbCategoriasUsuarios.getSelectedItem();
		        String tipoUsuario = String.valueOf(tipoUsuario1);
		       
				Usuario cliente = new Usuario(tfNombreUsuario.getText(), lbCorreo.getText(), tfNumCel.getText(), tipoUsuario, tipoPaquete);
			
				String datosCliente = "Usuario: "+cliente.nombreUsuario+
						"\nEmail: "+cliente.correo+
						"\nTeléfono: "+cliente.numTelefono+
						"\nTipo de usuario: "+cliente.tipoUsuario+
						"\nTipo de paquete: "+cliente.tipoPaquete;
				
				if (tipoPaquete.equals("Perecedero")){	
					colaPerecedero.devolverCola(tipoUsuario).colaUsuariosP.encolar(cliente);
					cantClientesP++;
					colaPerecedero.consecutivo++;
					
					cliente.numFicha = cliente.setNumFicha(colaPerecedero.devolverCola(tipoUsuario).colaUsuariosP.consecutivo);
					datosCliente = datosCliente+"\nSu número de ficha es "+cliente.numFicha;
					JOptionPane.showMessageDialog(Quisco,datosCliente );
					lbSigClienteP.setText(colaPerecedero.buscarSiguienteCliente().nombreUsuario);
					lbSigClienteFichaP.setText(colaPerecedero.buscarSiguienteCliente().numFicha);
					lbFichasP.setText(colaPerecedero.buscarFichasTodosCliente());
				}	
				else{
					colaNoPerecedero. devolverCola(tipoUsuario).colaUsuariosP.encolar(cliente);
					cantClientesNoP++;
					colaNoPerecedero.consecutivo++;
					cliente.numFicha = cliente.setNumFicha(colaNoPerecedero. devolverCola(tipoUsuario).colaUsuariosP.consecutivo);
					datosCliente = datosCliente+"\nSu número de ficha es "+cliente.numFicha;
					JOptionPane.showMessageDialog(Quisco,datosCliente );
					lbSigClienteNP.setText(colaNoPerecedero.buscarSiguienteCliente().nombreUsuario);
					lbSigClienteFichaNP.setText(colaNoPerecedero.buscarSiguienteCliente().numFicha);
					lbFichasNP.setText(colaNoPerecedero.buscarFichasTodosCliente());
				}
				actualizarDatosAdm();
				
				int discapAtendidos= pDiscapacitados.cantidadTotalFichas+npDiscapacitados.cantidadTotalFichas;
				int adultoMayAtendidos = pAdultoMayor.cantidadTotalFichas+npAdultoMayor.cantidadTotalFichas;
				int embarazadaAtendidas = pEmbarazadas.cantidadTotalFichas+npEmbarazadas.cantidadTotalFichas;
				int regularesAtendidos = pRegular.cantidadTotalFichas + npRegular.cantidadTotalFichas;
				lbCantDiscap.setText(discapAtendidos+"");
				lbCantAdultoM.setText(adultoMayAtendidos+"");
				lbCantEmbarazada.setText(embarazadaAtendidas+"");
				lbCantRegular.setText(regularesAtendidos+"");
				
				lbCantDiscapP.setText(pDiscapacitados.cantidadTotalFichas+"");
				lbCantAdultoMP.setText(pAdultoMayor.cantidadTotalFichas+"");
				lbCantEmbarazadaP.setText(pEmbarazadas.cantidadTotalFichas+"");
				lbCantRegularP.setText(pRegular.cantidadTotalFichas+"");
				
				lbCantDiscapNP.setText(npDiscapacitados.cantidadTotalFichas+"");
				lbCantAdultoMNP.setText(npAdultoMayor.cantidadTotalFichas+"");
				lbCantEmbarazadaNP.setText(npEmbarazadas.cantidadTotalFichas+"");
				lbCantRegularNP.setText(npRegular.cantidadTotalFichas+"");

				//Datos cliente aqui se agrega lo del msj
				String numTel= "+506"+cliente.numTelefono;
				datosCliente= "Gracias por utilizar CourierTEC\n"+datosCliente;
				//System.out.print(datosCliente);
				MensajeTexto.getInstance().enviarMensaje(datosCliente, numTel);
			}
		});		

	}
}
