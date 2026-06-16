# 📦 Sistema de Cadastro de Produtos

![Java](https://img.shields.io/badge/Java-21-%23ED8B00?logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9+-C71A36?logo=apache-maven&logoColor=white)
![Build](https://img.shields.io/badge/build-passing-brightgreen)

> **Java Puro com Orientação a Objetos e recursos modernos do Java 21 — sem frameworks!**

---

## 🎯 Objetivo do Projeto

Este projeto foi desenvolvido com o propósito de demonstrar a **força do Java puro** na construção de sistemas corporativos, utilizando **exclusivamente a linguagem Java 21** e seus recursos modernos, **sem dependência de frameworks** como Spring Boot.

O foco está em:
- **Código limpo e desacoplado** seguindo princípios **SOLID** e **Clean Architecture**
- **Recursos modernos do Java 21** como Records, Switch Expressions e Pattern Matching
- **Injeção de dependência manual** sem frameworks
- **Design Patterns** aplicados na prática (Strategy, Repository)

---

## 🚀 Tecnologias

| Tecnologia | Versão | Finalidade |
|------------|--------|------------|
| **Java** | 21 | Records, Switch Expressions, Pattern Matching, Stream API |
| **Maven** | 3.9+ | Build e gerenciamento de dependências |
| **JUnit 5** | 5.11+ | Testes unitários |

---

## ✨ Recursos Modernos do Java 21 Aplicados

### 📌 Records — Imutabilidade e Concisão

```java
public record ProdutoRecord(
    UUID id,
    String nome,
    String descricao,
    double precoBase,
    TaxaTipo taxa,
    LocalDate dataCadastro
) {
    public double obterValorDoImposto() {
        return taxa.calcularImposto(precoBase);
    }
}
```

### 📌 Switch Expressions — Expressividade

```java
TaxaTipo taxa = switch (tipoTaxa.toUpperCase()) {
    case "BASIC" -> TaxaTipo.BASIC;
    case "MEDIA" -> TaxaTipo.MEDIA;
    case "ALTA" -> TaxaTipo.ALTA;
    default -> throw new IllegalArgumentException("Tipo de taxa inválido!");
};
```

### 📌 Strategy Pattern com Enum — Polimorfismo Puro

```java
public enum TaxaTipo {
    BASIC { public double calcularImposto(double preco) { return preco * 0.03; } },
    MEDIA { public double calcularImposto(double preco) { return preco * 0.085; } },
    ALTA  { public double calcularImposto(double preco) { return preco * 0.105; } };

    public abstract double calcularImposto(double preco);
}
```

### 📌 Stream API — Programação Funcional

```java
produtos.stream()
    .filter(p -> p.precoBase() > 100)
    .map(ProdutoRecord::obterPrecoFinal)
    .forEach(System.out::println);
```

---

## 📐 Arquitetura

O projeto segue **Clean Architecture** com separação clara de responsabilidades:

```
src/main/java/com/sistema/cadastro/
├── domain/              # 🧠 Núcleo do negócio
│   ├── entities/        # Records e entidades puras
│   └── enums/           # Enums com lógica encapsulada (Strategy Pattern)
├── infrastructure/      # 🗄️ Persistência
│   └── database/        # Implementação do repositório em memória
├── presentation/        # 🖥️ Interface com o usuário
│   ├── controller/      # Controlador que orquestra as operações
│   └── menu/            # Menu interativo no console
└── usecases/            # ⚙️ Casos de uso da aplicação
    ├── ports/           # Interfaces (portas) de repositório
    └── product/         # Implementação dos casos de uso
```

### 🔗 Fluxo da Aplicação

```
ConsoleMenu → ProdutoController → UseCase → Repository (ArrayList)
```

---

## 🧱 Princípios OOP e SOLID Aplicados

| Princípio | Aplicação |
|-----------|-----------|
| **S** — Single Responsibility | Cada classe tem uma única responsabilidade bem definida |
| **O** — Open/Closed | `TaxaTipo` permite novas taxas sem modificar código existente |
| **L** — Liskov Substitution | Interfaces garantem substituição de implementações |
| **I** — Interface Segregation | `ProdutoRepository` com métodos específicos |
| **D** — Dependency Inversion | Dependências injetadas via construtor, nunca instanciadas |

### Design Patterns

- **Strategy Pattern** — `TaxaTipo` enum com estratégias de cálculo de imposto
- **Repository Pattern** — `ProdutoRepository` abstrai o acesso a dados
- **Dependency Injection** — Injeção manual via construtor (sem frameworks)

---

## 📋 Pré-requisitos

- Java 21+ ([Download](https://adoptium.net/))
- Maven 3.9+ ([Download](https://maven.apache.org/download.cgi))

---

## ▶️ Como executar

```bash
# 1. Compilar o projeto
mvn clean compile

# 2. Executar os testes
mvn test

# 3. Executar a aplicação
mvn exec:java -Dexec.mainClass="com.sistema.cadastro.CadastroApplication"

# 4. Ou compilar e executar o JAR
mvn clean package
java -jar target/cadastro-0.0.1-SNAPSHOT.jar
```

---

## 🧪 Funcionalidades

| Funcionalidade | Descrição |
|----------------|-----------|
| **Cadastrar Produto** | Nome, descrição, preço base e tipo de taxa |
| **Listar Produtos** | Exibe todos os produtos com imposto e preço final |
| **Cálculo de Imposto** | BASIC (3.0%), MEDIA (8.5%), ALTA (10.5%) |

---

## 📁 Estrutura de Governança

- `.clinerules` — Constituição do projeto com regras de desenvolvimento
- `.cline/` — Memory Bank com documentação do projeto (projectbrief, techContext, systemPatterns)

---

## 📜 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
