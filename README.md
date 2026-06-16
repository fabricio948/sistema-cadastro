# Cadastro

Sistema de cadastro desenvolvido em **Java 21** aplicando os princípios da **Programação Orientada a Objetos (OOP)** e **Clean Architecture**.

## 🚀 Tecnologias

- **Java 21** — Records, Switch Expressions, Pattern Matching
- **Maven** — Gerenciamento de dependências e build
- **JUnit 5** — Testes unitários

## 📐 Arquitetura

O projeto segue os princípios **SOLID** e **Clean Architecture**, organizado nas seguintes camadas:

```
src/main/java/com/sistema/cadastro/
├── domain/          # Entidades, enums, DTOs e regras de negócio
├── infrastructure/  # Persistência e integrações externas
├── presentation/    # Interfaces com o usuário
└── usecases/        # Casos de uso e portas da aplicação
```

## 🧱 Princípios OOP Aplicados

- **Encapsulamento** — Atributos privados com acesso controlado via getters
- **Herança** — Reuso de comportamento entre entidades
- **Polimorfismo** — Métodos que se comportam de forma diferente conforme o contexto
- **Abstração** — Interfaces e classes abstratas para desacoplamento

## 📋 Pré-requisitos

- Java 21+
- Maven 3.9+

## ▶️ Como executar

```bash
# Compilar o projeto
mvn clean compile

# Executar os testes
mvn test

# Empacotar o projeto
mvn clean package
```
