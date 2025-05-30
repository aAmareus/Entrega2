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

        // Mostrar el cuadro de diálogo
        int res = fileChooser.showOpenDialog(this);

        if (res == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();

            if (archivoSeleccionado.isDirectory()) {
                mostrarCarpeta(archivoSeleccionado);
            } else {
                mostrarArchivo(archivoSeleccionado);
            }

            this.pack();
        } else {
            dispose(); // Cerrar la ventana si el usuario cancela
        }
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

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                area.append(linea + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }

        JScrollPane scroll = new JScrollPane(area);
        this.setLayout(new BorderLayout());
        this.add(scroll, BorderLayout.CENTER);
    }
}