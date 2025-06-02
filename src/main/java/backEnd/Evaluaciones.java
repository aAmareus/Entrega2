package backEnd;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Evaluaciones extends JFrame {
    private DefaultListModel<String> modeloEvaluaciones;
    private JList<String> listaEvaluaciones;
    private JButton btnRealizarEvaluacion, btnCerrar;
    
    public Evaluaciones() {
        setTitle("Evaluaciones Disponibles");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(600, 400);
        setLayout(new BorderLayout());
        
        configurarInterfaz();
        cargarEvaluaciones();
        
        setVisible(true);
    }
    
    public void configurarInterfaz() {
        modeloEvaluaciones = new DefaultListModel<>();
        listaEvaluaciones = new JList<>(modeloEvaluaciones);
        JScrollPane scroll = new JScrollPane(listaEvaluaciones);
        
        btnRealizarEvaluacion = new JButton("Realizar Evaluacion");
        btnCerrar = new JButton("Cerrar");
        
        btnRealizarEvaluacion.addActionListener(e -> realizarEvaluacion());
        btnCerrar.addActionListener(e -> dispose());
        
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnRealizarEvaluacion);
        panelBotones.add(btnCerrar);
        
        add(new JLabel("Seleccione una evaluacion para continuar: "), BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
    
    private void cargarEvaluaciones() {
        modeloEvaluaciones.clear();
        List<String> evaluaciones = TemporalDataBase.getEvaluaciones();
        
        for (String eval : evaluaciones) {
            modeloEvaluaciones.addElement(eval);
        }
        
        revalidate();
        repaint();
    }
    
    private void realizarEvaluacion() {
        String seleccion = listaEvaluaciones.getSelectedValue();
        if (seleccion == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una evaluación primero.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        List<String> preguntas = TemporalDataBase.getPreguntasDeEvaluacion(seleccion);
        if (preguntas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Esta evaluación no tiene preguntas.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            new RealizarEvaluacion(seleccion);
        }
    }
}

