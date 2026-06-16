# Contexto Técnico
## Status da Stack Principal
- **Versão do Java:** 21 (Focando em Records e Expressões Switch)
- **Spring Boot:** 3.x
- **Gerenciador de Dependências:** Maven (`pom.xml`)

## Infraestrutura (Docker Compose)
Os seguintes serviços devem ser validados via `pom.xml` antes da execução:
- [ ] **PostgreSQL**: Porta `5432`
- [ ] **Redis**: Porta `6379` (Apenas se presente no pom.xml)
- [ ] **RabbitMQ**: Porta `5672` (AMQP) e `15672` (Gerenciamento) (Apenas se presente no pom.xml)

## Referência de Comandos para Desenvolvedores
- Compilar e checar estabilidade: `mvn clean compile`
- Rodar suíte de testes obrigatória antes do Git Commit: `mvn test`
- Inicializar infraestrutura local: `docker compose up -d`