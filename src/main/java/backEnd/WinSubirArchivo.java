package backEnd;

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class WinSubirArchivo extends JFrame {

    private DefaultListModel<String> modeloPreguntas;
    private JList<String> listaPreguntas;
    private JButton btnCerrar;

    // Constructor
    public WinSubirArchivo() {
        setTitle("Subir archivo de Preguntas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(600, 400);

        JPanel panel = new JPanel(new BorderLayout());

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileFilter(new FileChooser());

        int res = fileChooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            File[] archivosSeleccionados = fileChooser.getSelectedFiles();

            for (File archivo : archivosSeleccionados) {
                List<String> preguntasLeidas = leerPreguntasDesdeArchivo(archivo);
                for (String pregunta : preguntasLeidas) {
                    TemporalDataBase.guardarPreguntaGlobal(pregunta);
                }

            }
        }

        modeloPreguntas = new DefaultListModel<>();
        listaPreguntas = new JList<>(modeloPreguntas);
        actualizarListaPreguntas();

        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());

        panel.add(new JLabel("Preguntas cargadas (Aún no asignadas a una evaluación:"), BorderLayout.NORTH);
        panel.add(new JScrollPane(listaPreguntas), BorderLayout.CENTER);
        panel.add(btnCerrar, BorderLayout.SOUTH);

        add(panel);
        pack();
        setVisible(true);
    }

    private List<String> leerPreguntasDesdeArchivo(File archivo) {
        List<String> preguntas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String enunciado;
            while ((enunciado = reader.readLine()) != null) {
                enunciado = enunciado.trim();
                if (enunciado.isEmpty()) {
                    continue;
                }

                List<String> alternativas = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    alternativas.add(reader.readLine().trim());
                }

                String respuesta = reader.readLine().trim();
                String preguntaCompleta = enunciado + ";" + String.join(",", alternativas) + ";" + respuesta;
                preguntas.add(preguntaCompleta);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + archivo.getName() + "\nDetalles: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return preguntas;
    }

    private void actualizarListaPreguntas() {
        modeloPreguntas.clear();
        List<String> preguntas = TemporalDataBase.getPreguntasGlobales(); // 🔹 Ahora devuelve `List<String>`
        for (String pregunta : preguntas) { // 🔹 Iteramos sobre `String`, no `Pregunta`
            modeloPreguntas.addElement(pregunta);
        }
        revalidate();
        repaint();
        System.out.println("Preguntas generales cargadas: " + preguntas);
    }

}
