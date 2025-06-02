package frontEnd;

import backEnd.TemporalDataBase;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ModificarEvaluacion extends JFrame {

    private String evaluacionSeleccionada;
    private DefaultListModel<String> modeloGlobal, modeloEvaluacion;
    private JList<String> listaGlobal, listaEvaluacion;
    private JButton btnAgregar, btnRemover, btnCerrar;

    public ModificarEvaluacion(String evaluacionSeleccionada) {
        System.out.println("Se abrió ModificarEvaluacion con evaluación: " + evaluacionSeleccionada);
        this.evaluacionSeleccionada = evaluacionSeleccionada;

        setTitle("Modificar Evaluación: " + evaluacionSeleccionada);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(700, 500);
        setLayout(new BorderLayout());

        configurarInterfaz();
        cargarListas(); // Cargar datos al iniciar

        setVisible(true);
    }

    private void configurarInterfaz() {
        JPanel panelCentral = new JPanel(new GridLayout(1, 2));

        modeloGlobal = new DefaultListModel<>();
        listaGlobal = new JList<>(modeloGlobal);
        panelCentral.add(new JScrollPane(listaGlobal));

        modeloEvaluacion = new DefaultListModel<>();
        listaEvaluacion = new JList<>(modeloEvaluacion);
        panelCentral.add(new JScrollPane(listaEvaluacion));

        JPanel panelBotones = new JPanel();
        btnAgregar = new JButton("Agregar");
        btnRemover = new JButton("Remover");
        btnCerrar = new JButton("Cerrar");

        btnAgregar.addActionListener(e -> agregarPregunta());
        btnRemover.addActionListener(e -> removerPregunta());
        btnCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnAgregar);
        panelBotones.add(btnRemover);
        panelBotones.add(btnCerrar);

        add(new JLabel("🔹 Preguntas Generales"), BorderLayout.WEST);
        add(new JLabel("🔹 Preguntas en '" + evaluacionSeleccionada + "'"), BorderLayout.EAST);
        add(panelCentral, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarListas() {
        modeloGlobal.clear();
        List<String> preguntasGlobales = TemporalDataBase.getPreguntasGlobales();
        for (String pregunta : preguntasGlobales) {
            modeloGlobal.addElement(pregunta);
        }

        modeloEvaluacion.clear();
        List<String> preguntasEvaluacion = TemporalDataBase.getPreguntasDeEvaluacion(evaluacionSeleccionada);
        for (String pregunta : preguntasEvaluacion) {
            modeloEvaluacion.addElement(pregunta);
        }

        revalidate();
        repaint();
    }

    private void agregarPregunta() {
        List<String> preguntasDisponibles = TemporalDataBase.getPreguntasGlobales();

        if (preguntasDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❌ No hay preguntas disponibles en el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "🔹 Seleccione una pregunta para agregar:",
                "Agregar Pregunta",
                JOptionPane.PLAIN_MESSAGE,
                null,
                preguntasDisponibles.toArray(),
                preguntasDisponibles.get(0)
        );

        if (seleccion != null) {
            TemporalDataBase.agregarPreguntaAEvaluacion(evaluacionSeleccionada, seleccion);
            cargarListas();
        }
    }

    private void removerPregunta() {
        String seleccion = listaEvaluacion.getSelectedValue();
        if (seleccion == null) {
            JOptionPane.showMessageDialog(this, "⚠️ Selecciona una pregunta para remover.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        TemporalDataBase.eliminarPreguntaDeEvaluacion(evaluacionSeleccionada, seleccion);
        cargarListas();
    }
}
