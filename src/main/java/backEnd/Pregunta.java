package backEnd;

public class Pregunta {
    
    private String enunciado;
    private String alternativas;
    private int tax;
    
    
    public Pregunta(String enunciado, String alternativas, int tax) {
        this.enunciado = enunciado;
        this.alternativas = alternativas;
        this.tax = tax;
    }
    
    public String getEnunciado() {
        return enunciado;
    }
    
    public String getAlternativas() {
        return alternativas;
    }
    
    public int getTaxonomia() {
        return tax;
    }
    
    @Override
    public String toString() {
        return "Enunciado: " + enunciado + "\nalternativas: " + alternativas + "\nNivel" + tax;
    }
    
}
