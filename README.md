# Horse App (tema: Cavalo)

Aplicação web Spring Boot com CRUD básico para entidade `Horse` (cavalo).
Tecnologias: Spring Boot, Spring MVC, Spring Data JPA, H2 (in-memory), Thymeleaf.

### Como usar

1. Requisitos:
   - Java 17+
   - Maven

2. Rodar em modo desenvolvimento:
   ```
   mvn spring-boot:run
   ```

3. Acesse no navegador:
   - Lista de cavalos: http://localhost:8080/horses
   - H2 console: http://localhost:8080/h2-console (use `jdbc:h2:mem:horsedb`)

Este projeto já tem um frontend simples com páginas para listar, cadastrar, editar e visualizar cavalo.
