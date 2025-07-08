import java.util.ArrayList;

public abstract class Agente {

    private String nome;
    private int nivel;
    private int vidaAtual;
    private int vidaMaxima;
    private int experiencia;
    private static boolean status;
    private ArrayList<Equipamento> equipamentos = new ArrayList<>();

    public Agente(String nome, int nivel) {
        this.nome = nome;
        this.nivel = nivel;
    }

    public String getNome() {
        return nome;
    }

    public int getNivel() {
        return nivel;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public static boolean isStatus() {
        return status;
    }

    public void setVidaAtual(int vidaAtual) {
        this.vidaAtual = Math.min(vidaAtual, vidaMaxima);
        if(vidaAtual <= 0){
            this.status = false;
            this.vidaAtual = 0;
        }
    }

    public void receberDano(int dano) {
        setVidaAtual(this.vidaAtual - dano);
    }

    public abstract int calcularDano();


    public void ganharExperiencia(int xp) {
        this.experiencia += xp;
        while (this.experiencia >= 100) {
            this.experiencia -= 100;
            this.nivel++;
            this.vidaMaxima = this.nivel * 100;
            this.vidaAtual = this.vidaMaxima;
            System.out.println(nome + " subiu para o nível " + nivel + "!");
        }
    }

    public void adicionarEquipamento(Equipamento equipamento) {
        equipamentos.add(equipamento);
        System.out.println(nome + " recebeu " + equipamento);
    }

    public void listarInventario() {
        if (equipamentos.isEmpty()) {
            System.out.println("Não há equipamentos no inventário.");
        } else {
            for (Equipamento equi : equipamentos) {
                System.out.println(equi);
            }
        }
    }
    @Override
    public String toString() {
        return "Nome: " + nome +
                " | Nível: " + nivel +
                " | Vida: " + vidaAtual +
                " | XP: " + experiencia;
    }
}
