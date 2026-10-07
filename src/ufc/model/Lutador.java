package ufc.model;

public class Lutador {

    private String nome;
    private String nacionalidade;
    private int idade;
    private double altura;
    private double envergadura;
    private double peso;
    private String categoria;
    private int vitorias;
    private int derrotas;
    private int empates;

    public Lutador(String nome, String nacionalidade, int idade, double altura, double peso, int vitorias, int derrotas, int empates, double envergadura) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.idade = idade;
        this.altura = altura;
        setPeso(peso);
        this.vitorias = vitorias;
        this.derrotas = derrotas;
        this.empates = empates;
        this.envergadura = envergadura;
    }

    public void apresentar() {
        System.out.println("-----------------------------------");
        System.out.println("CHEGOU A HORA! Apresentamos o lutador: " + this.getNome());
        System.out.println("Diretamente de: " + this.getNacionalidade());
        System.out.println("Com " + this.getIdade() + " anos e " + this.getAltura() + "m de altura");
        System.out.println("Pesando: " + this.getPeso() + " Kg | Categoria: " + this.getCategoria());
        System.out.println("Envergadura: " + this.getEnvergadura() + "m");
        System.out.println("Cartel: " + this.getVitorias() + "V - " + this.getDerrotas() + "D - " + this.getEmpates() + "E");
        System.out.println("-----------------------------------");
    }

    public void status() {
        System.out.println(this.getNome() + " | Categoria: Peso " + this.getCategoria());
        System.out.println("Vitórias: " + this.getVitorias() + " | Derrotas: " + this.getDerrotas() + " | Empates: " + this.getEmpates());
    }

    public void ganharLuta() {
        this.vitorias++;
    }

    public void perderLuta() {
        this.derrotas++;
    }

    public void empatarLuta() {
        this.empates++;
    }

    public String toCsv() {
        return nome + ";" + nacionalidade + ";" + idade + ";" + altura + ";" + peso + ";" + vitorias + ";" + derrotas + ";" + empates + ";" + envergadura;
    }

    public static Lutador fromCsv(String linha) {
        String[] p = linha.split(";");
        return new Lutador(p[0], p[1], Integer.parseInt(p[2]), Double.parseDouble(p[3]), Double.parseDouble(p[4]),
                Integer.parseInt(p[5]), Integer.parseInt(p[6]), Integer.parseInt(p[7]), Double.parseDouble(p[8]));
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getNacionalidade() { return nacionalidade; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }
    public double getEnvergadura() { return envergadura; }
    public void setEnvergadura(double envergadura) { this.envergadura = envergadura; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) {
        this.peso = peso;
        setCategoria();
    }
    public String getCategoria() { return categoria; }
    private void setCategoria() {
        if (this.peso < 52.2) {
            this.categoria = "Inválido";
        } else if (this.peso <= 70.3) {
            this.categoria = "Leve";
        } else if (this.peso <= 83.9) {
            this.categoria = "Médio";
        } else if (this.peso < 94.0) {
            this.categoria = "Meio-Pesado";
        } else {
            this.categoria = "Pesado";
        }
    }
    public int getVitorias() { return vitorias; }
    public int getDerrotas() { return derrotas; }
    public int getEmpates() { return empates; }
}
