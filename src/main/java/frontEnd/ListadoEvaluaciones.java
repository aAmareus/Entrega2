package frontEnd;

import backEnd.*;
import backEnd.TemporalDataBase;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListadoEvaluaciones extends JFrame {
    private JList<String> listaEvaluaciones;
    private DefaultListModel<String> modeloLista;

    public ListadoEvaluaciones() {
        setTitle("Listado de Evaluaciones");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modeloLista = new DefaultListModel<>();
        List<String> evaluaciones = TemporalDataBase.getEvaluaciones();

        if (evaluaciones.isEmpty()) {
            modeloLista.addElement("No hay evaluaciones disponibles.");
        } else {
            for (String evaluacion : evaluaciones) {
                modeloLista.addElement(evaluacion);
            }
        }

        listaEvaluaciones = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaEvaluaciones);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = listaEvaluaciones.getSelectedValue();
                if (seleccion != null && !seleccion.equals("No hay evaluaciones disponibles.")) {
                    new ModificarEvaluacion(seleccion).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una evaluación válida.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(btnModificar, BorderLayout.SOUTH);
    }
}