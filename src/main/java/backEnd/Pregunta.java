package backEnd;

public class Pregunta {
    
    private String enunciado;
    private String alternativas;
    private String respuesta;
    private int tax;
    private float time;
    
    
    public Pregunta(String enunciado, String alternativas, String respuesta, int tax, float time) {
        this.enunciado = enunciado;
        this.alternativas = alternativas;
        this.respuesta = respuesta;
        this.tax = tax;
        this.time = time;
    }
    
    public String getEnunciado() {
        return enunciado;
    }
    
    public String getAlternativas() {
        return alternativas;
    }
    
    public String[] getOpcionesSeparadas() {
        return alternativas.split(",");
    }
    
    public String getRespuesta() {
        return respuesta;
    }
    
    public int getTaxonomia() {
        return tax;
    }
    
    public float getTime() {
        return time;
    }
    
    
    
    @Override
    public String toString() {
        return "Enunciado: " + enunciado + "\nalternativas: " + alternativas + "\nRespuesta correcta: " + respuesta + "\nNivel taxonomía: " + tax;
    }
    
}
