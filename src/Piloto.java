public class Piloto extends Agente {
    private int reflexo;

    public Piloto(String nome, int nivel, int reflexo) {
        super(nome, nivel);
        if(reflexo <=0) throw new IllegalArgumentException("Reflexo deve ser positivo");
        this.reflexo = reflexo;
    }
    public int getReflexo() {
        return reflexo;
    }

    @Override
    public int calcularDano(){
        return reflexo * getNivel();
    }

    @Override
    public String toString() {
        return super.toString() + "\nReflexo = " + reflexo;
    }
}
