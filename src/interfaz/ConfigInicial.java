package interfaz;

import java.awt.EventQueue;
import  javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import interfaz.visualizacionGeneral;
import clases.ColaPrioridad;
import clases.ListaEnlazada;
import javax.swing.ButtonGroup;

public class ConfigInicial extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNumVentP;
	private JTextField tfNumVentNP;
	private JTextField tfNumVentSeg;
	private JCheckBox ckBoxCPS ;
	private JCheckBox ckBoxHeapSeg ;
	private JCheckBox ckBoxCPNP ;
	private JCheckBox ckBoxHeapNP;
	private JCheckBox ckBoxHeapP;
	private JCheckBox ckBoxCPP;
	
	public visualizacionGeneral window = new visualizacionGeneral();
	private final ButtonGroup grupoPerecederos = new ButtonGroup();
	private final ButtonGroup grupoNP = new ButtonGroup();
	private final ButtonGroup grupoSeguridad = new ButtonGroup();
	private JTextField tfTiempoMinimo;
	private JTextField tfTiempoMaximo;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigInicial frame = new ConfigInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public ConfigInicial() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1260, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblBienvenidoACouriertec = new JLabel("Bienvenido a CourierTEC");
		lblBienvenidoACouriertec.setFont(new Font("Segoe Print", Font.BOLD, 43));
		lblBienvenidoACouriertec.setBounds(362, 22, 550, 54);
		panel.add(lblBienvenidoACouriertec);
		
		JLabel lblConfiguracinInicial = new JLabel("Configuraci\u00F3n Inicial");
		lblConfiguracinInicial.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblConfiguracinInicial.setBounds(124, 120, 235, 47);
		panel.add(lblConfiguracinInicial);
		
		JLabel lblSeleccioneLaImplementacin = new JLabel("Indique la cantidad de ventanillas que desea habilitar para cada m\u00F3dulo as\u00ED como el sistema de manejo de prioridades en atenci\u00F3n de clientes a utilizar:");
		lblSeleccioneLaImplementacin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSeleccioneLaImplementacin.setBounds(123, 169, 985, 50);
		panel.add(lblSeleccioneLaImplementacin);
		
		JButton btnAceptarConfigInicial = new JButton("Aceptar");
		btnAceptarConfigInicial.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAceptarConfigInicial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(contentPane, "La configuración ha sido guardada exitosamente");
				
				//--configurar prioridades y ventanas
				window.listaVentPerecedero = new ListaEnlazada("Perecedero");
				window.listaVentNoPerecedero = new ListaEnlazada("No Perecedero");
				window.listaVentSeguridad = new ListaEnlazada("Seguridad");
				// Perecederos
				String strCantPerecederos = tfNumVentP.getText();
				int cantPerecederos = Integer.parseInt(strCantPerecederos);		
				
				window.configurarVentanillas(cantPerecederos,window.listaVentPerecedero);
								
		        if(ckBoxCPP.isSelected() ){
		        	window.colaPerecedero = new ColaPrioridad("Perecedero");
		    			window.pDiscapacitados = new ColaPrioridad("Persona con discapacidad");
		    			window.pAdultoMayor = new ColaPrioridad("Adulto mayor");
		    			window.pEmbarazadas = new ColaPrioridad("Mujer embarazada");
		    			window.pRegular = new ColaPrioridad("Cliente regular");
		    			
				    	window.colaPerecedero.encolar(window.pDiscapacitados);
				    	window.colaPerecedero.encolar(window.pAdultoMayor);
				    	window.colaPerecedero.encolar(window.pEmbarazadas);
				    	window.colaPerecedero.encolar(window.pRegular);
				    	window.tipoCajaP.setText("Cola Prioridad");
		        }
		        else{
		          //Arbboles
		        	System.out.println("no sirve p");
		        	window.tipoCajaP.setText("Árbol Heap");
		        }
		        
		        //No perecederos
				String strCantNoPerecederos = tfNumVentNP.getText();
				int cantNoPerecederos = Integer.parseInt(strCantNoPerecederos);
				
				window.configurarVentanillas(cantNoPerecederos,window.listaVentNoPerecedero);//Funcion que crea las ventanillas segun la cantidad indicada y la lista
				
		        if(ckBoxCPNP.isSelected() ){
			    	window.colaNoPerecedero = new ColaPrioridad("No perecedero");
		    	    	window.npDiscapacitados = new ColaPrioridad("Persona con discapacidad");
				    	window.npAdultoMayor = new ColaPrioridad("Adulto mayor");
				    	window.npEmbarazadas = new ColaPrioridad("Mujer embarazada");
				    	window.npRegular = new ColaPrioridad("Cliente regular");
						
						window.colaNoPerecedero.encolar(window.npDiscapacitados);
						window.colaNoPerecedero.encolar(window.npAdultoMayor);
						window.colaNoPerecedero.encolar(window.npEmbarazadas);
						window.colaNoPerecedero.encolar(window.npRegular);
						window.tipoCajaNP.setText("Cola Prioridad");
		        }
		        else{
		          //Arbboles
		        	System.out.println("no sirve np");
		        	window.tipoCajaNP.setText("Árbol Heap");
		        }
				
		        //Seguridad
				String strCantSeguridad = tfNumVentSeg.getText();
				int cantSeguridad= Integer.parseInt(strCantSeguridad);
				
				String strTiempoMax = tfTiempoMaximo.getText();
				int tiempoMax= Integer.parseInt(strTiempoMax);
				String strTiempoMin = tfTiempoMinimo.getText();
				int tiempoMin= Integer.parseInt(strTiempoMin);
				
				window.configurarVentanillas(cantSeguridad,window.listaVentSeguridad );
				window.colaSeguridad.tiempoMaximoAtencion = tiempoMax;
				window.colaSeguridad.tiempoMinimoAtencion = tiempoMin;
				
		        if(ckBoxCPS.isSelected() ){
		        	window.colaSeguridad = new ColaPrioridad("Seguridad");
		        			window.sDiscapacitados = new ColaPrioridad("Persona con discapacidad");
			    			window.sAdultoMayor = new ColaPrioridad("Adulto mayor");
			    			window.sEmbarazadas = new ColaPrioridad("Mujer embarazada");
			    			window.sRegular = new ColaPrioridad("Cliente regular");
					    	window.colaSeguridad.encolar(window.sDiscapacitados);
					    	window.colaSeguridad.encolar(window.sAdultoMayor);
					    	window.colaSeguridad.encolar(window.sEmbarazadas);
					    	window.colaSeguridad.encolar(window.sRegular);
					    	window.tipoCajaS.setText("Cola Prioridad");
		        }
		        else{
		          //Arbboles
		        	System.out.println("no sirve seg");
		        	window.tipoCajaS.setText("Árbol Heap");
		        }
		        window.cajasCantUsuariosVentNP.setText(window.listaVentNoPerecedero.clientesAtendidosPorVentanilla());
		        window.cajasCantUsuariosVentP.setText(window.listaVentPerecedero.clientesAtendidosPorVentanilla());
		        window.actualizarDatos();
				setVisible(false);
				window.ventanaPrincipal.setVisible(true);
			}
		});
		btnAceptarConfigInicial.setBounds(564, 509, 139, 38);
		panel.add(btnAceptarConfigInicial);
		
		JLabel label = new JLabel("Perecederos:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(124, 237, 108, 47);
		panel.add(label);
		tfNumVentP = new JTextField();
		tfNumVentP.setColumns(10);
		tfNumVentP.setBounds(232, 249, 86, 29);
		panel.add(tfNumVentP);
		
		JLabel label_1 = new JLabel("No perecederos:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(514, 237, 128, 47);
		panel.add(label_1);
		
		tfNumVentNP = new JTextField();
		tfNumVentNP.setColumns(10);
		tfNumVentNP.setBounds(652, 249, 86, 29);
		panel.add(tfNumVentNP);
		
		JLabel label_2 = new JLabel("Seguridad:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(931, 237, 94, 47);
		panel.add(label_2);
		
		tfNumVentSeg = new JTextField();
		tfNumVentSeg.setColumns(10);
		tfNumVentSeg.setBounds(1022, 249, 86, 29);
		panel.add(tfNumVentSeg);
		
		ckBoxCPS = new JCheckBox("Colas de prioridad");
		grupoSeguridad.add(ckBoxCPS);
		ckBoxCPS.setSelected(true);
		ckBoxCPS.setBounds(931, 291, 177, 23);
		panel.add(ckBoxCPS);
		
		ckBoxHeapSeg = new JCheckBox("\u00C1rbol Heap");
		grupoSeguridad.add(ckBoxHeapSeg);
		ckBoxHeapSeg.setBounds(931, 317, 97, 23);
		panel.add(ckBoxHeapSeg);
		
		ckBoxCPNP = new JCheckBox("Colas de prioridad");
		grupoNP.add(ckBoxCPNP);
		ckBoxCPNP.setSelected(true);
		ckBoxCPNP.setBounds(514, 291, 190, 23);
		panel.add(ckBoxCPNP);
		
		ckBoxHeapNP = new JCheckBox("\u00C1rbol Heap");
		grupoNP.add(ckBoxHeapNP);
		ckBoxHeapNP.setBounds(514, 317, 97, 23);
		panel.add(ckBoxHeapNP);
		
		ckBoxHeapP = new JCheckBox("\u00C1rbol Heap");
		grupoPerecederos.add(ckBoxHeapP);
		ckBoxHeapP.setBounds(124, 317, 97, 23);
		panel.add(ckBoxHeapP);
		
		ckBoxCPP = new JCheckBox("Colas de prioridad");
		grupoPerecederos.add(ckBoxCPP);
		ckBoxCPP.setSelected(true);
		ckBoxCPP.setBounds(124, 291, 169, 23);
		panel.add(ckBoxCPP);
		
		JLabel lblUnRangoDe = new JLabel("Indique el rango de tiempo para la atenci\u00F3n de los paquetes:");
		lblUnRangoDe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUnRangoDe.setBounds(123, 363, 985, 50);
		panel.add(lblUnRangoDe);
		
		JLabel lblTiempoMnimo = new JLabel("Tiempo m\u00EDnimo:");
		lblTiempoMnimo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTiempoMnimo.setBounds(124, 415, 169, 47);
		panel.add(lblTiempoMnimo);
		
		tfTiempoMinimo = new JTextField();
		tfTiempoMinimo.setColumns(10);
		tfTiempoMinimo.setBounds(303, 427, 86, 29);
		panel.add(tfTiempoMinimo);
		
		JLabel lblTiempoMximo = new JLabel("Tiempo m\u00E1ximo:");
		lblTiempoMximo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTiempoMximo.setBounds(514, 415, 156, 47);
		panel.add(lblTiempoMximo);
		
		tfTiempoMaximo = new JTextField();
		tfTiempoMaximo.setColumns(10);
		tfTiempoMaximo.setBounds(711, 427, 86, 29);
		panel.add(tfTiempoMaximo);
	}
}
