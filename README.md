# Cadastro

Sistema de cadastro de produtos desenvolvido em **Java 21** aplicando os princípios da **Programação Orientada a Objetos (OOP)**, **Clean Architecture** e **SOLID**.

## 🚀 Tecnologias

- **Java 21** — Records, Switch Expressions, Pattern Matching
- **Maven** — Gerenciamento de dependências e build
- **JUnit 5** — Testes unitários

## 📐 Arquitetura

O projeto segue os princípios **SOLID** e **Clean Architecture**, organizado nas seguintes camadas:

```
src/main/java/com/sistema/cadastro/
├── domain/              # Entidades, enums e regras de negócio
│   ├── entities/        # Records e entidades puras
│   └── enums/           # Enums com lógica encapsulada
├── infrastructure/      # Persistência e integrações externas
│   └── database/        # Implementação do repositório em memória
├── presentation/        # Interfaces com o usuário
│   ├── controller/      # Controlador que orquestra as operações
│   └── menu/            # Menu interativo no console
└── usecases/            # Casos de uso e portas da aplicação
    ├── ports/           # Interfaces (portas) de repositório
    └── product/         # Implementação dos casos de uso
```

## 🧱 Princípios OOP Aplicados

- **Encapsulamento** — Atributos privados com acesso controlado via métodos
- **Polimorfismo** — Enum `TaxaTipo` com método `calcularImposto()` polimórfico
- **Abstração** — Interface `ProdutoRepository` para desacoplamento
- **Injeção de Dependência** — Dependências injetadas manualmente via construtor
- **Repository Pattern** — Abstração de acesso a dados
- **Strategy Pattern** — `TaxaTipo` implementa estratégias de cálculo de imposto

## 📋 Pré-requisitos

- Java 21+
- Maven 3.9+

## ▶️ Como executar

```bash
# Compilar o projeto
mvn clean compile

# Executar os testes
mvn test

# Executar a aplicação via Maven
mvn exec:java -Dexec.mainClass="com.sistema.cadastro.CadastroApplication"

# Ou compilar e executar diretamente
mvn clean package
java -jar target/cadastro-0.0.1-SNAPSHOT.jar
```

## 🧪 Funcionalidades

- **Cadastrar Produto** — Nome, descrição, preço base e tipo de taxa (BASIC, MEDIA, ALTA)
- **Listar Produtos** — Exibe todos os produtos cadastrados com cálculo de imposto e preço final
- **Cálculo de Imposto** — Taxa BASIC (3.0%), MEDIA (8.5%), ALTA (10.5%)

## 📁 Estrutura de Governança

- `.clinerules` — Constituição do projeto com regras de desenvolvimento
- `.cline/` — Memory Bank com documentação do projeto
