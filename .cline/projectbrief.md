# Resumo do Projeto
## Objetivo Principal
Desenvolver o back-end aplicando Clean Architecture, conforme definido no `.clinerules`.

## Escopo Atual e Recursos
A complexidade do projeto é determinada pela análise do `pom.xml`:
- **Projeto Simples:** Foco em Controller, Service, Repository e DTOs, mantendo SOLID e Clean Code.
- **Projeto Avançado:** Ativação automática de JWT (Segurança), Redis (Cache) e RabbitMQ (Mensageria).

## Requisitos Ativos
- Validar todas as entradas na camada `controller` via Spring Validation.
- Toda resposta de erro deve seguir o formato unificado do `RestExceptionHandler`.