# UFC-simulator-java 🥊

Um sistema completo de gerenciamento e simulação de combates de MMA desenvolvido em **Java**, aplicando conceitos avançados de **Orientação a Objetos (POO)**, **Tratamento de Exceções Personalizadas**, **Regras de Negócio Dinâmicas** e **Persistência de Dados em Arquivos**.

---
**Fiz esse sistema como um projeto de hobby, pois gosto bastante de UFC e fiquei imaginando como seria estruturar o ecossistema de lutas, atletas e regras utilizando os conceitos de Programação Orientada a Objetos (POO)**

## 🚀 Funcionalidades do Sistema

- **Cadastro e Listagem de Lutadores:** Cadastro detalhado de atletas (nome, nacionalidade, idade, altura, peso, envergadura e cartel).
- **Categorização Automática de Peso:** O sistema classifica automaticamente o lutador com base no seu peso (Leve, Médio, Meio-Pesado, Pesado ou Inválido).
- **Casamento de Lutas Inteligente:** Validação automática que impede lutas entre categorias diferentes, combate do lutador contra si mesmo ou atletas com pesos inválidos.
- **Simulação de Combate:** Sorteio dinâmico e simulação de resultados (Nocaute, Finalização ou Empate Técnico) com atualização imediata do cartel dos atletas.
- **Persistência de Dados:** Salvamento e carregamento automático dos lutadores cadastrados através de arquivos `.txt` (`lutadores.txt`), garantindo que os dados não sejam perdidos ao fechar o programa.
- **Tratamento Robusto de Exceções:** Uso de exceções personalizadas (`UfcException`) e validações de entrada para evitar quebras por erros do usuário.

---

## 📂 Arquitetura e Estrutura de Pacotes

O projeto é modularizado em pacotes para garantir a separação de responsabilidades (Clean Code / SOLID):

```text
src/
└── ufc/
    ├── model/
    │   ├── Lutador.java       # Entidade de lutadores e regras de peso
    │   └── Luta.java          # Regras de combate e simulação de resultados
    ├── repository/
    │   ├── ListaLutadores.java # Gerenciamento, cadastro e persistência (I/O)
    │   └── ListaLutas.java     # Histórico de combates realizados
    ├── exception/
    │   └── UfcException.java   # Exceções personalizadas de negócio
    └── Main.java              # Interface de menu via console
