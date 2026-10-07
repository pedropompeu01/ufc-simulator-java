package ufc.model;

import ufc.exception.UfcException;
import java.util.Random;

public class Luta {

    private Lutador desafiado;
    private Lutador desafiante;
    private int rounds;
    private boolean aprovado;
    private String resultado;

    public void marcarLuta(Lutador l1, Lutador l2) throws UfcException {
        if (l1 == l2) {
            throw new UfcException("Erro: Um lutador não pode lutar contra si mesmo!");
        }
        if (!l1.getCategoria().equals(l2.getCategoria())) {
            throw new UfcException("Erro: Luta não aprovada! Categorias diferentes (" + l1.getNome() + ": " + l1.getCategoria() + " vs " + l2.getNome() + ": " + l2.getCategoria() + ")");
        }
        if (l1.getCategoria().equals("Inválido") || l2.getCategoria().equals("Inválido")) {
            throw new UfcException("Erro: Lutador com peso inválido para combate.");
        }

        this.aprovado = true;
        this.desafiado = l1;
        this.desafiante = l2;
        this.rounds = 3;
        this.resultado = "Pendente";
    }

    public void lutar() throws UfcException {
        if (!this.aprovado) {
            throw new UfcException("Esta luta não foi aprovada ou marcada corretamente.");
        }

        System.out.println("\n=== INICIANDO O COMBATE ===");
        this.desafiado.apresentar();
        this.desafiante.apresentar();

        Random aleatorio = new Random();
        int vencedor = aleatorio.nextInt(3);

        System.out.println("\n*** RESULTADO OFICIAL DA LUTA ***");
        switch (vencedor) {
            case 0:
                System.out.println("Empate Técnico!");
                this.desafiado.empatarLuta();
                this.desafiante.empatarLuta();
                this.resultado = "Empate";
                break;
            case 1:
                System.out.println("Nocaute! Vitória de " + this.desafiado.getNome());
                this.desafiado.ganharLuta();
                this.desafiante.perderLuta();
                this.resultado = "Vitória de " + this.desafiado.getNome();
                break;
            case 2:
                System.out.println("Finalização! Vitória de " + this.desafiante.getNome());
                this.desafiado.perderLuta();
                this.desafiante.ganharLuta();
                this.resultado = "Vitória de " + this.desafiante.getNome();
                break;
        }
    }

    public String toCsv() {
        return desafiado.getNome() + ";" + desafiante.getNome() + ";" + rounds + ";" + aprovado + ";" + resultado;
    }

    public Lutador getDesafiado() { return desafiado; }
    public Lutador getDesafiante() { return desafiante; }
    public String getResultado() { return resultado; }
}
