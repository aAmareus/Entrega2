package frontEnd;

import javax.swing.*;
import java.awt.*;

import backEnd.Modificaciones;
import backEnd.Evaluaciones;
import backEnd.WinSubirArchivo;

public class Entrega2 extends JFrame {
    
    // Asignando atributos de la clase
    public JMenu inicio, evaluaciones, preguntas, modificar, exit;
    public JMenuBar navbar;
    public JLabel maintext;
    
    private java.util.List<JFrame> openWindow = new java.util.ArrayList<>();     // java.util.List<> Sirve para almacenar objetos de un solo tipo dentro de una lista.
                                                                                // En este caso, guardaremos datos de tipo JFrame.
    // Constructor de la clase
    public Entrega2() {
       
        // Configurando la ventana
        this.setTitle("Evaluapp | Entrega 2");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
       
        // Building menu bar items
        this.inicio = new JMenu("Inicio");
        this.evaluaciones = new JMenu("Evaluaciones");
        this.preguntas = new JMenu("Preguntas");
        this.modificar = new JMenu("Modificar");
        this.exit = new JMenu("Salir");
       
        // Building Navbar
        this.navbar = new JMenuBar();
        this.navbar.add(inicio);
        this.navbar.add(evaluaciones);
        this.navbar.add(preguntas);
        this.navbar.add(modificar);
        this.navbar.add(exit);
        this.setJMenuBar(navbar);
        this.navbar.setBackground(new Color(250, 250, 250));
        
        // Configurando el texto principal
        this.maintext = new JLabel("<html>Bienvenido e Evaluapp. <br> Por favor selecciona una de las opciones de arriba para continuar.</html>", SwingConstants.CENTER);
        this.maintext.setVerticalAlignment(SwingConstants.CENTER);
        this.maintext.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(maintext);
       
        // Crear Event Listener para los menús
       
        // MouseListener es para hacer click directamente en el menú sin necesidad de desplegar opciones.
       
        /**
         * Se debe utilizar esta función, ya que el objetoo JMenu no acepta ActionListener, por eso recurrimos a esta función.
         */
        this.evaluaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                Evaluaciones pruebas = new Evaluaciones();
                pruebas.setVisible(true);
                pruebas.setSize(700, 400);
                openWindow.add(pruebas); //Al abrir el JFrame, lo añadimos a la lista creada anteriormente.
            }
        });
       
        this.modificar.addMouseListener(new java.awt.event.MouseAdapter() {
           @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                Modificaciones mod = new Modificaciones();
                mod.setVisible(true);
                mod.setSize(700, 400);
                openWindow.add(mod);  //Al abrir el JFrame, lo añadimos a la lista creada anteriormente.
            }
        });
       
        this.preguntas.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                WinSubirArchivo upFile = new WinSubirArchivo();
                upFile.setVisible(true);
                upFile.setSize(900, 600);
                openWindow.add(upFile);
            }
        });
        
        
        /**
         
         VERIFICAR PORQUE NO SE MUESTRA EN PANTALLA
         * 
         */
        
        this.inicio.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                for(JFrame ventana : openWindow) { // Esto, es una forma abreviada de decir: Para cada objeto ventana de tipo JFrame dentro de la lista, haz lo siguiente.
                    ventana.dispose(); // Esta linea, hace que cada ventana dentro de la lista se cierre.
                }
                openWindow.clear();
            }
        });
        
        this.exit.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Está a punto de cerrar la aplicación.", "¡Atención!", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }   
        });
    }
   
    public static void main(String[] args) {
        Entrega2 app = new Entrega2();
        app.setVisible(true);
    }
}
