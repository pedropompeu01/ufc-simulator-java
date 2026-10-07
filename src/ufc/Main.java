package ufc;

import ufc.exception.UfcException;
import ufc.model.Luta;
import ufc.model.Lutador;
import ufc.repository.ListaLutas;
import ufc.repository.ListaLutadores;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static ListaLutadores gerenciarLutadores = new ListaLutadores();
    static ListaLutas gerenciarLutas = new ListaLutas();

    public static void main(String[] args) {
        int opcao = 0;

        do {
            System.out.println("\n==========================================");
            System.out.println("         UFC MANAGEMENT SYSTEM            ");
            System.out.println("==========================================");
            System.out.println("1 - Listar Lutadores Cadastrados");
            System.out.println("2 - Cadastrar Novo Lutador");
            System.out.println("3 - Consultar Status Detalhado");
            System.out.println("4 - Marcar e Realizar Combate");
            System.out.println("5 - Ver Histórico de Lutas Realizadas");
            System.out.println("6 - Sair");

            opcao = lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    listarLutadores();
                    break;
                case 2:
                    cadastrarLutador();
                    break;
                case 3:
                    consultarStatus();
                    break;
                case 4:
                    realizarLuta();
                    break;
                case 5:
                    listarHistorico();
                    break;
                case 6:
                    System.out.println("Salvando dados e encerrando o sistema...");
                    gerenciarLutadores.salvarNoArquivo();
                    break;
                default:
                    System.out.println("Opção inválida! Escolha entre 1 e 6.");
            }
        } while (opcao != 6);
    }

    static int lerInt(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas números inteiros.");
                scanner.nextLine();
            }
        }
    }

    static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                double valor = scanner.nextDouble();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número válido (ex: 70.5).");
                scanner.nextLine();
            }
        }
    }

    static void listarLutadores() {
        System.out.println("\n--- LISTA DE LUTADORES ---");
        for (int i = 0; i < gerenciarLutadores.getLutadores().size(); i++) {
            Lutador l = gerenciarLutadores.getLutadores().get(i);
            System.out.println("[" + i + "] " + l.getNome() + " | Peso: " + l.getCategoria() + " (" + l.getPeso() + "kg) | Cartel: " + l.getVitorias() + "V-" + l.getDerrotas() + "D");
        }
    }

    static void cadastrarLutador() {
        System.out.println("\n--- CADASTRO DE NOVO LUTADOR ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Nacionalidade: ");
        String nac = scanner.nextLine();
        int idade = lerInt("Idade: ");
        double altura = lerDouble("Altura (ex: 1.80): ");
        double peso = lerDouble("Peso em kg (ex: 70.5): ");
        double envergadura = lerDouble("Envergadura (ex: 1.90): ");
        int vitorias = lerInt("Vitórias iniciais: ");
        int derrotas = lerInt("Derrotas iniciais: ");
        int empates = lerInt("Empates iniciais: ");

        Lutador novo = new Lutador(nome, nac, idade, altura, peso, vitorias, derrotas, empates, envergadura);
        gerenciarLutadores.adicionar(novo);
        System.out.println("Lutador cadastrado com sucesso e salvo no arquivo!");
    }

    static void consultarStatus() {
        listarLutadores();
        int idx = lerInt("Digite o número do lutador para ver o status: ");
        Lutador l = gerenciarLutadores.buscarPorIndice(idx);
        if (l != null) {
            System.out.println();
            l.status();
        } else {
            System.out.println("Lutador não encontrado.");
        }
    }

    static void realizarLuta() {
        listarLutadores();
        int i1 = lerInt("Escolha o número do Desafiado: ");
        int i2 = lerInt("Escolha o número do Desafiante: ");

        Lutador l1 = gerenciarLutadores.buscarPorIndice(i1);
        Lutador l2 = gerenciarLutadores.buscarPorIndice(i2);

        if (l1 != null && l2 != null) {
            try {
                Luta luta = new Luta();
                luta.marcarLuta(l1, l2);
                luta.lutar();
                gerenciarLutadores.salvarNoArquivo();
                gerenciarLutas.adicionarLuta(luta);
            } catch (UfcException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Índices inválidos.");
        }
    }

    static void listarHistorico() {
        System.out.println("\n--- HISTÓRICO DE LUTAS DO SISTEMA ---");
        if (gerenciarLutas.getHistorico().isEmpty()) {
            System.out.println("Nenhuma luta realizada nesta sessão.");
            return;
        }
        for (int i = 0; i < gerenciarLutas.getHistorico().size(); i++) {
            Luta l = gerenciarLutas.getHistorico().get(i);
            System.out.println((i + 1) + ". " + l.getDesafiado().getNome() + " vs " + l.getDesafiante().getNome() + " -> Resultado: " + l.getResultado());
        }
    }
}
