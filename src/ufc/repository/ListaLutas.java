package ufc.repository;

import ufc.model.Luta;
import java.io.*;
import java.util.ArrayList;

public class ListaLutas {
    private ArrayList<Luta> historicoLutas = new ArrayList<>();
    private final String ARQUIVO = "historico_lutas.txt";

    public ListaLutas() {
        carregarDoArquivo();
    }

    public void adicionarLuta(Luta l) {
        historicoLutas.add(l);
        salvarNoArquivo();
    }

    public ArrayList<Luta> getHistorico() {
        return historicoLutas;
    }

    public void salvarNoArquivo() {
        try (PrintStream ps = new PrintStream(new FileOutputStream(ARQUIVO))) {
            for (Luta l : historicoLutas) {
                ps.println(l.toCsv());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar histórico: " + e.getMessage());
        }
    }

    public void carregarDoArquivo() {
        File f = new File(ARQUIVO);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            historicoLutas.clear();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] p = linha.split(";");
                // Estrutura simples de recuperação do histórico se necessário
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar histórico: " + e.getMessage());
        }
    }
}
