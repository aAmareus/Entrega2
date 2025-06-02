package backEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class RealizarEvaluacion extends JFrame {

    private String nombreEvaluacion;
    private List<String> preguntas;
    private int preguntaActual = 0;
    private int respuestasCorrectas = 0;

    private JLabel lblEnunciado;
    private JRadioButton[] opciones;
    private JButton btnNext, btnEnd;
    private ButtonGroup grupoOpciones;

    public RealizarEvaluacion(String nombreEvaluacion) {
        this.nombreEvaluacion = nombreEvaluacion;
        this.preguntas = TemporalDataBase.getPreguntasDeEvaluacion(nombreEvaluacion);

        setTitle("Evaluacion: " + nombreEvaluacion);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(600, 400);
        setLayout(new BorderLayout());

        configurarInterfaz();
        mostrarPregunta();

        setVisible(true);
    }

    public void configurarInterfaz() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        lblEnunciado = new JLabel("", JLabel.CENTER);
        panelPrincipal.add(lblEnunciado, BorderLayout.NORTH);

        JPanel panelOpciones = new JPanel(new GridLayout(4, 1));
        grupoOpciones = new ButtonGroup();
        opciones = new JRadioButton[4];

        for (int i = 0; i < 4; i++) {
            opciones[i] = new JRadioButton();
            grupoOpciones.add(opciones[i]);
            panelOpciones.add(opciones[i]);
        }

        panelPrincipal.add(panelOpciones, BorderLayout.CENTER);

        btnNext = new JButton("Siguiente");
        btnEnd = new JButton("Finalizar");

        btnNext.addActionListener(e -> verificarRespuesta());
        btnEnd.addActionListener(e -> finalizarEvaluacion());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnNext);
        panelBotones.add(btnEnd);

        add(panelPrincipal, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void mostrarPregunta() {
        if (preguntaActual < preguntas.size()) {
            String pregunta = preguntas.get(preguntaActual); // 🔹 Ahora obtenemos un String en lugar de un objeto Pregunta
            lblEnunciado.setText(pregunta); // 🔹 Mostrar directamente el enunciado

            // 🔹 Asegurar que haya alternativas separadas correctamente (debe coincidir con el formato del archivo)
            String[] alternativas = pregunta.split(";"); // 🔹 Ajusta esto según cómo estén almacenadas las alternativas
            for (int i = 0; i < 4; i++) {
                opciones[i].setText(alternativas[i]);
                opciones[i].setSelected(false);
            }

            btnNext.setEnabled(true);
            btnEnd.setEnabled(preguntaActual == preguntas.size() - 1);
        }
    }

    private void verificarRespuesta() {
        String pregunta = preguntas.get(preguntaActual); // 🔹 Ahora es un String
        String[] partesPregunta = pregunta.split(";"); // 🔹 Ajusta según el formato del archivo
        String respuestaCorrecta = partesPregunta[partesPregunta.length - 1]; // 🔹 Última parte es la respuesta correcta

        for (JRadioButton opcion : opciones) {
            if (opcion.isSelected() && opcion.getText().equals(respuestaCorrecta)) {
                respuestasCorrectas++;
                break;
            }
        }

        preguntaActual++;
        if (preguntaActual < preguntas.size()) {
            mostrarPregunta();
        } else {
            finalizarEvaluacion();
        }
    }

    private void finalizarEvaluacion() {
        JOptionPane.showMessageDialog(this, "Evaluación Finalizada. \nRespuestas correctas: " + respuestasCorrectas + " / " + preguntas.size(), "Resultados", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
