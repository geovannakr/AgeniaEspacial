import java.util.ArrayList;

public class Missao {
    private static int contador = 1;
    private String nome;
    private int id;
    private int dificuldade;
    private ArrayList<Agente> missaoParticipantes;
    private boolean status;

    public Missao(String nome, int dificuldade) {
        this.nome = nome;
        this.dificuldade = Math.max(1,Math.min(dificuldade, 3));
        this.missaoParticipantes = new ArrayList<>();
        this.status = false;
    }

    public String isStatus() {
        return status ? "Concluída" : "Pendente";
    }

    public ArrayList<Agente> getMissaoParticipantes() {
        return missaoParticipantes;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public boolean adicionarAgente(Agente agente) {
        if(status) {
            System.out.println("Agente adicionado na missão!");
            return false;
        }

        if(!Agente.isStatus()){
            System.out.println("O agente morreu e não pode participar");
            return false;
        }

        if(missaoParticipantes.contains(agente)) {
            System.out.println("Agente ja está na missao");
            return false;
        }
        missaoParticipantes.add(agente);
        return true;
    }

    public boolean removerAgente(Agente agente) {
        if(status) {
            System.out.println("A missão já foi concluída com sucesso!");
            return false;
        }
        return missaoParticipantes.remove(agente);
    }

    public boolean validarParticipante() {
        return missaoParticipantes.size() >= (dificuldade+1);
    }

    public void iniciarMissao() {
        if(status) {
            System.out.println("Missão concluída com sucesso!");
            return;
        }

        if(!validarParticipante()){
            System.out.println("O número de participantes não é suficiente para iniciar missão!");
            return;
        }

        System.out.println("Iniciando missão...\nNome do agente: " + nome + "\nID: " + id + "\nDificuldade: " + dificuldade);

        boolean alterar = true;
        for(Agente agente : missaoParticipantes) {
            int dano = agente.calcularDano();
            System.out.println("Agente: " + agente.getNome() + " dano causado: " + dano);

            int xp = dificuldade * 50;
            agente.ganharExperiencia(xp);

            Equipamento equipamento = alterar ? new KitReparacao() : new CanhaoPlasma();
            agente.adicionarEquipamento(equipamento);
            equipamento.aplicar(agente);

            alterar = !alterar;
        }

        this.status = true;
        System.out.println("A missão " + nome + " foi concluída com sucesso!");
    }

    @Override
    public String toString() {
    return super.toString() + String.format("Id: " + id + "\nNome: " + nome + "\nDificuldade: " + dificuldade + "\nStatus: " + status + "Participantes: " + missaoParticipantes);
    }
}