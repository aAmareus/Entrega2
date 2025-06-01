package backEnd;

import javax.swing.*;
import java.io.*;
import java.awt.*;

public class WinSubirArchivo extends JFrame {

    public WinSubirArchivo() {
        setTitle("Seleccione un archivo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setFileFilter(new FileChooser());
        fileChooser.setMultiSelectionEnabled(true); // para seleccionar multiples archivos

        // Mostrar el cuadro de diálogo
        int res = fileChooser.showOpenDialog(this);

        if (res == JFileChooser.APPROVE_OPTION) {
            File[] archivosSeleccionados = fileChooser.getSelectedFiles();

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            
            
            for (File archivo : archivosSeleccionados) {
                if (archivo.isDirectory()) {
                    panel.add(new JLabel("Carpeta: " + archivo.getAbsolutePath()));
                } else {
                    Pregunta pregunta = leerPreguntaDesdeArchivo(archivo);
                    if (pregunta != null) {
                        panel.add(new JLabel("<html><pre>" + pregunta.toString() + "</pre></html>")); 
                        agregarPreguntaAEvaluacion(pregunta);
                    } else {
                        panel.add(new JLabel("Archivo inválido: " + archivo.getName()));
                    }
                }
            }
            
            JScrollPane scrollPane = new JScrollPane(panel);
            this.setLayout(new BorderLayout());
            this.add(scrollPane, BorderLayout.CENTER);
            this.pack();
        } else {
            dispose();
        }
    }
    
    private Pregunta leerPreguntaDesdeArchivo(File archivo) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String enunciado = reader.readLine();
                String alternativas = reader.readLine();
                String taxStr = reader.readLine();
                
                if(enunciado != null && alternativas != null && taxStr != null) {
                    int nivel = Integer.parseInt(taxStr.replace("\"", "").trim());
                    return new Pregunta(
                            enunciado.replace("\"", ""),
                            alternativas.replace("\"", ""),
                            nivel
                    );
                }
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Nivel de taxonomía inválido en el archivo: " + archivo.getName(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al leer: " + getName(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return null;
    }
    
    private void agregarPreguntaAEvaluacion(Pregunta p) {
        System.out.println("Agregada: " + p.getEnunciado());
    }
    
    private void mostrarCarpeta(File carpeta) {
        JTextField field = new JTextField(carpeta.getAbsolutePath());
        JLabel label = new JLabel("Carpeta seleccionada:");
        this.setLayout(new BorderLayout(5, 5));
        this.add(label, BorderLayout.NORTH);
        this.add(field, BorderLayout.CENTER);
    }

    private void mostrarArchivo(File archivo) {
        JTextArea area = new JTextArea();
        area.setColumns(40);
        area.setRows(20);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
    }
}
