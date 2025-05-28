package frontEnd;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import backEnd.SubirArchivos;

public class Entrega2 extends JFrame {
    
    public JMenu options;
    public JMenuItem inicio, evaluaciones, modificar, exit;
    public JMenuBar navbar;
    //public JLabel intro;
    public JButton startBtn;
    public JPanel panel;
    
    //Constructor
    public Entrega2() {
        
        // Ventana.
        this.setTitle("Evualuapp | tu aplicación");
        this.setSize(620, 350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centrar la ventana
        this.setResizable(false);
        
        // Building the items.
        this.inicio = new JMenuItem("Menú principal");
        this.evaluaciones = new JMenuItem("Evaluaciones");
        this.modificar = new JMenuItem("Modificaciones");
        this.exit = new JMenuItem("Salir");
        
        // Building Menu.
        this.options = new JMenu("Opciones");
        this.options.add(inicio);
        this.options.addSeparator();
        this.options.add(evaluaciones);
        this.options.add(modificar);
        this.options.addSeparator();
        this.options.add(exit);
        
        // Building NavBar
        this.navbar = new JMenuBar();
        this.navbar.add(options);
        this.setJMenuBar(navbar);
        this.navbar.setBackground(new Color(250, 250, 250));
        
        
        this.startBtn = new JButton("Comenzar");
        
        //Estilización del botón
        this.startBtn.setBounds(260, 110, 100, 30);         //Colocar el botón al centro de la pantalla.
        this.startBtn.setBackground(new Color(1, 24, 216)); // Color de fondo del botón.
        this.startBtn.setForeground(Color.WHITE);
        this.panel = new JPanel();
        
        this.panel.setLayout(null);
        
        this.panel.add(startBtn);
        this.add(panel);
        
        // BACKEND
        /*
        codigo subir archivo
        leer archivo
        mostrar archivo
        opcion de agregarlo o no a la evaluacion
        
        */
        
        // FRONTEND
        /*
            el estilazo
        */
        
        // Cerrar la aplicacion
        this.exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        // Abrir la segunda ventana
        this.startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SubirArchivos ventana = new SubirArchivos();
                ventana.setTitle("Seleccione los archivos");
                ventana.setVisible(true);
                ventana.setSize(700, 400);
            }
        });
        
        this.modificar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                SubirArchivos ventana = new SubirArchivos();
                ventana.setTitle("Seleccione los archivos");
                ventana.setVisible(true);
                ventana.setSize(700, 400);
            }
        });
        
    }
    
    public static void main(String[] args) {
        Entrega2 app = new Entrega2();
        app.setVisible(true);
    }
}
