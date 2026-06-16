# System Patterns & Architecture Progress

## OPERATIONAL PROTOCOL (Mandatory for Cline)
1. **Initialization:** Upon starting any task, check for the existence of a `.git` folder. If absent, execute `git init` immediately.
2. **Context Analysis:** BEFORE generating any code or folder structures, you MUST read the `pom.xml` file.
3. **Classification:** Based on `pom.xml`, identify the project as **[BASIC]** or **[ADVANCED]**. This classification dictates the stack (e.g., skip Redis/RabbitMQ/JWT if not in `pom.xml`).
4. **Self-Documentation:** Whenever a new design pattern or structural change is applied, update this file to reflect the current state.
5. **Stability:** You are responsible for running `mvn clean compile` and `mvn test` before finalizing any changes or performing a `git commit`.

## Applied Design Patterns
- `RestExceptionHandler`: Centralizado na camada `handler` para tratamento global de exceções.
- Padrões `Strategy` e `Factory`: Obrigatórios para regras de negócio variantes ou complexas.

## Git Workflow Check
- Padrão de commits ativo: Conventional Commits (`feat:`, `fix:`, `chore:`, `docs:`, `refactor:`).