package ufc.repository;

import ufc.model.Lutador;
import java.io.*;
import java.util.ArrayList;

public class ListaLutadores {
    private ArrayList<Lutador> lutadores = new ArrayList<>();
    private final String ARQUIVO = "lutadores.txt";

    public ListaLutadores() {
        carregarDoArquivo();
        if (lutadores.isEmpty()) {
            cadastrarPadrao();
        }
    }

    private void cadastrarPadrao() {
        lutadores.add(new Lutador("Max Holloway", "Havaí", 33, 1.80, 70.3, 27, 8, 0, 1.75));
        lutadores.add(new Lutador("Charles Oliveira", "Brasil", 35, 1.78, 70.0, 36, 11, 0, 1.90));
        lutadores.add(new Lutador("Israel Adesanya", "Nigéria", 36, 1.93, 83.8, 24, 5, 0, 2.03));
        lutadores.add(new Lutador("Caio Borralho", "Brasil", 32, 1.87, 83.0, 17, 2, 0, 1.90));
        lutadores.add(new Lutador("Magomed Ankalaev", "Rússia", 33, 1.90, 93.0, 21, 2, 1, 1.91));
        lutadores.add(new Lutador("Alex Pereira", "Brasil", 38, 1.93, 93.1, 13, 3, 0, 2.00));
        salvarNoArquivo();
    }

    public void adicionar(Lutador l) {
        lutadores.add(l);
        salvarNoArquivo();
    }

    public ArrayList<Lutador> getLutadores() {
        return lutadores;
    }

    public Lutador buscarPorIndice(int index) {
        if (index >= 0 && index < lutadores.size()) {
            return lutadores.get(index);
        }
        return null;
    }

    public void salvarNoArquivo() {
        try (PrintStream ps = new PrintStream(new FileOutputStream(ARQUIVO))) {
            for (Lutador l : lutadores) {
                ps.println(l.toCsv());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar lutadores: " + e.getMessage());
        }
    }

    public void carregarDoArquivo() {
        File f = new File(ARQUIVO);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            lutadores.clear();
            String linha;
            while ((linha = br.readLine()) != null) {
                lutadores.add(Lutador.fromCsv(linha));
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar lutadores: " + e.getMessage());
        }
    }
}
