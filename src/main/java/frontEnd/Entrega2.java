package frontEnd;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
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
        
        Container content = getContentPane();
        content.setLayout(null);
        content.add(this.panel);
        
        // Cerrar la aplicacion
        this.exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
    }
    
    public static void main(String[] args) {
        Entrega2 app = new Entrega2();
        app.setVisible(true);
    }
}
