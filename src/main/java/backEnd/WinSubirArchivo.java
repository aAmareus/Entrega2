package backEnd;

import javax.swing.*;
import java.io.*;
import java.awt.*;

public class WinSubirArchivo extends JFrame {
    
    /* Constructor*/
    public WinSubirArchivo(){
        setTitle("Seleccione un archivo");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit.getScreenSize();
        int a = dim.height;
        int w = dim.width;
        
        this.setSize(w, a);
    }
    
    static public void main(String[] args) throws Exception {
        WinSubirArchivo ventana = new WinSubirArchivo();
        JFileChooser file = null;
        file = new JFileChooser();
        file.setCurrentDirectory(new File("."));
        // Seleccionar archivo por defecto
        /* file.setSelectedFile(new File("...")); */
        
        // Para seleccionar files o paths
        file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        file.setFileFilter(new FileChooser());
        
        int res = file.showOpenDialog(ventana); // botones abrir y cancelar
        // int res = file.showSaveDialog(ventana); - Botones Guardar y Cancelar
        // int res = file.showDialog(ventana, "run"); - Botones ejecutar y guardar
        
        if (res== JFileChooser.APPROVE_OPTION) {
            JTextField field = null;
            JLabel lab = null;
            JTextArea area = null;
            File f = file.getSelectedFile();
            String path, name;
            path = f.getPath();
            name = f.getName();
            
            // Mostrar por pantalla
            System.out.println("Selección: " + path + name);
            
            if (f.isDirectory()) {
                field = new JTextField(path + name);
                lab = new JLabel("Carpeta sleccionada");
                ventana.getContentPane().setLayout(new BorderLayout(5, 5));
                ventana.getContentPane().add(BorderLayout.NORTH, lab);
                ventana.getContentPane().add(field);
                ventana.pack();
            } else {
                ventana.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
                area = new JTextArea();
                area.setColumns(30);
                area.setRows(20);
                area.setLineWrap(true); // Salto automatico de linea
                area.setWrapStyleWord(true); // Salto de linea sin cortar la palabra
                JScrollPane desplaz = new JScrollPane(area);
                String cd;
                int i = 1;
                BufferedReader en = new BufferedReader(
                        new InputStreamReader(new FileInputStream(f)));
                while ((cd = en.readLine()) != null) {
                    area.append(cd + "\n");
                    
                    // Se pone el area de texto
                    ventana.getContentPane().add(desplaz);
                    ventana.pack();
                }
                
              ventana.setVisible(true);
              ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        }
        
    }
}
