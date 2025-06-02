package backEnd;

import java.util.*;

public class TemporalDataBase {

    private static Map<String, List<String>> evaluaciones = new HashMap<>(); // 🔹 Almacena evaluaciones y sus preguntas
    private static List<String> preguntasGuardadas = new ArrayList<>(); // 🔹 Lista con todas las preguntas del archivo

    /**
     * 🔹 Método para agregar una evaluación
     */
    public static void agregarEvaluacion(String nombre) {
        if (evaluaciones == null) {
            evaluaciones = new HashMap<>();
            System.out.println("🔄 Inicializando 'evaluaciones'.");
        }

        System.out.println("🚀 Agregando evaluación: " + nombre);
        if (!evaluaciones.containsKey(nombre)) {
            evaluaciones.put(nombre, new ArrayList<>());
            System.out.println("✅ Evaluación agregada: " + nombre);
        } else {
            System.out.println("⚠️ La evaluación ya existe.");
        }
    }

    /**
     * 🔹 Método para obtener todas las evaluaciones
     */
    public static List<String> getEvaluaciones() {
        System.out.println("📌 Evaluaciones disponibles: " + evaluaciones.keySet());
        return new ArrayList<>(evaluaciones.keySet());
    }

    /**
     * 🔹 Método para cargar preguntas desde un archivo
     */
    public static void cargarPreguntasDesdeArchivo(List<String> preguntas) {
        if (preguntas != null && !preguntas.isEmpty()) {
            preguntasGuardadas.clear();
            preguntasGuardadas.addAll(preguntas);
            System.out.println("📌 Se han cargado " + preguntasGuardadas.size() + " preguntas.");
        } else {
            System.out.println("⚠️ No se encontraron preguntas en el archivo.");
        }
    }

    /**
     * 🔹 Método para obtener todas las preguntas disponibles
     */
    public static List<String> getPreguntas() {
        System.out.println("📌 Preguntas disponibles: " + preguntasGuardadas);
        return new ArrayList<>(preguntasGuardadas);
    }

    /**
     * 🔹 Método para agregar una pregunta a una evaluación específica
     */
    public static void agregarPreguntaAEvaluacion(String nombreEvaluacion, String pregunta) {
        if (evaluaciones.containsKey(nombreEvaluacion)) {
            evaluaciones.get(nombreEvaluacion).add(pregunta);
            System.out.println("✅ Pregunta agregada a '" + nombreEvaluacion + "': " + pregunta);
        } else {
            System.out.println("❌ ERROR: La evaluación '" + nombreEvaluacion + "' no existe.");
        }
    }

    /**
     * 🔹 Método para eliminar una pregunta de una evaluación
     */
    public static void eliminarPreguntaDeEvaluacion(String nombreEvaluacion, String pregunta) {
        if (evaluaciones.containsKey(nombreEvaluacion) && evaluaciones.get(nombreEvaluacion).contains(pregunta)) {
            evaluaciones.get(nombreEvaluacion).remove(pregunta);
            System.out.println("✅ Pregunta eliminada de '" + nombreEvaluacion + "': " + pregunta);
        } else {
            System.out.println("❌ ERROR: No se encontró la pregunta en '" + nombreEvaluacion + "'.");
        }
    }

    public static List<String> getPreguntasDeEvaluacion(String nombreEvaluacion) {
        if (evaluaciones.containsKey(nombreEvaluacion)) {
            return new ArrayList<>(evaluaciones.get(nombreEvaluacion));
        } else {
            System.out.println("ERROR: Evaluacion: " + nombreEvaluacion + "no encontrada.");
            return new ArrayList<>();
        }
    }

    public static List<String> getPreguntasGlobales() {
        return new ArrayList<>(preguntasGuardadas);
    }

    public static void guardarPreguntaGlobal(String pregunta) {
        if (preguntasGuardadas == null) {
            preguntasGuardadas = new ArrayList<>();
        }
        preguntasGuardadas.add(pregunta);
        System.out.println("✅ Pregunta global almacenada: " + pregunta);
    }
}
