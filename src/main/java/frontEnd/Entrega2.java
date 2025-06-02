package frontEnd;

import javax.swing.*;
import backEnd.*;
import backEnd.TemporalDataBase;
import java.util.*;

public class Entrega2 extends JFrame {

    private JMenuBar menuBar;

    public Entrega2() {
        setTitle("Gestión de Evaluaciones");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        configurarMenu();

        setVisible(true);
    }

    private void configurarMenu() {
        menuBar = new JMenuBar();

        JMenu menuEvaluaciones = new JMenu("Evaluaciones");
        JMenuItem crearEvaluacion = new JMenuItem("Crear Nueva Evaluación");
        JMenuItem visualizarEvaluaciones = new JMenuItem("Visualizar Evaluaciones");

        crearEvaluacion.addActionListener(e -> crearNuevaEvaluacion());
        visualizarEvaluaciones.addActionListener(e -> mostrarEvaluaciones());

        menuEvaluaciones.add(crearEvaluacion);
        menuEvaluaciones.add(visualizarEvaluaciones);

        JMenu menuPreguntas = new JMenu("Preguntas");
        JMenuItem subirArchivo = new JMenuItem("Subir Archivo");

        subirArchivo.addActionListener(e -> abrirWinSubirArchivo());

        menuPreguntas.add(subirArchivo);

        JMenu menuModificar = new JMenu("Modificar");
        JMenuItem modificarEvaluacion = new JMenuItem("Modificar Evaluacion");

        modificarEvaluacion.addActionListener(e -> modificarEvaluacion());
        menuModificar.add(modificarEvaluacion);
                
        menuBar.add(menuEvaluaciones);
        menuBar.add(menuPreguntas);
        menuBar.add(menuModificar);

        setJMenuBar(menuBar);

        //TemporalDataBase.agregarEvaluacion("PruebaManual");
        //System.out.println("Evaluaciones después de agregar manualmente: " + TemporalDataBase.getEvaluaciones());
    }

    private void crearNuevaEvaluacion() {
        String nombreEvaluacion = JOptionPane.showInputDialog(this, "Ingrese el nombre de la evaluación:");
        System.out.println("Valor capturado en Entrega2: " + nombreEvaluacion);

        if (nombreEvaluacion != null && !nombreEvaluacion.trim().isEmpty()) {
            TemporalDataBase.agregarEvaluacion(nombreEvaluacion.trim());
            System.out.println("Evaluaciones después de agregar desde Entrega2: " + TemporalDataBase.getEvaluaciones());
        } else {
            JOptionPane.showMessageDialog(this, "Debe ingresar un nombre válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirWinSubirArchivo() {
        new WinSubirArchivo();
    }

    private void modificarEvaluacion() {
        new ListadoEvaluaciones().setVisible(true);
    }

    private void mostrarEvaluaciones() {
        new Evaluaciones();
    }

    public static void main(String[] args) {
        new Entrega2();
    }

}
