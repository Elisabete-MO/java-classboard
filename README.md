# Projeto ClassBoard
O ClassBoard é uma aplicação para gestão de informações acadêmicas. Permite cadastrar e gerenciar estudantes, professores e matérias, além de registrar suas associações. Ele foi desenvolvido para demonstrar conceitos de Programação Orientada a Objetos, persistência de dados e práticas recomendadas no desenvolvimento com o Spring Framework.
<p align="center">
     <a alt="Java" href="https://java.com" target="_blank">
        <img src="https://img.shields.io/badge/Java-v21-ED8B00.svg" />
    </a>
    <a alt="Spring Boot" href="https://spring.io/projects/spring-boot" target="_blank">
        <img src="https://img.shields.io/badge/SpringBoot-v3.4.0-lightgreen.
svg" />
    </a>
    <a alt="Hibernate" href="https://hibernate.org/" target="_blank">
        <img src="https://img.shields.io/badge/Hibernate-v6.6.2.Final-blue.
svg" />
    </a>
     <a alt="Maven" href="https://maven.apache.org/index.html" target="_blank">
        <img src="https://img.shields.io/badge/Maven-v4.0.0-CD2335.svg" />
    </a>
    <a alt="H2 database" href="https://www.h2database.com/html/main.html"  target="_blank">
        <img src="https://img.shields.io/badge/H2-v2.3.232-darkblue.svg" />
    </a>
    <a alt="Swagger" href="https://swagger.io/"  
target="_blank">
        <img src="https://img.shields.io/badge/open--API-v2.2.0-brightgreen.
svg" />
    </a>
     <a alt="IntelliJ IDEA" href="https://www.jetbrains.com/idea/" target="_blank">
        <img src="https://img.shields.io/badge/IntelliJ IDEA-v1.18.32-087CFA.svg" />
    </a>
</p>

## Sumário

- [Funcionalidades](#funcionalidades)
- [Diagrama de Classes](#diagrama-de-classes)
- [Endpoints Disponíveis](#endpoints-disponíveis)
- [Acesso ao Projeto](#acesso-ao-projeto)
- [Testes](#testes)
- [Autora](#autora)

---
## Funcionalidades

### Gerenciamento de Alunos, Professores e Aulas/Matérias:
- Cadastro, edição e exclusão de entidades bem como 
  suas distribuições.
---

## Diagrama de Classes
```mermaid
classDiagram
    class Person {
      <<abstract>>
      - Long id
      - String name
      - String email
    }

    class Student {
      - LocalDateTime enrollmentDate
      - List~Subject~ classes
      + getEnrollmentDate(): LocalDateTime
      + getClasses(): List~Subject~
      + setClasses(List~Subject~ classes)
    }

  class Teacher {
    - List~Subject~ classes
    + getClasses(): List~Subject~
    + setClasses(List~Subject~ classes)
  }

    class Subject {
      - Long id
      - SubjectName name
      - String code
      - int credits
      - List~Student~ students
      - List~Teacher~ teachers
      + getName(): SubjectName
      + setName(SubjectName name)
      + getCode(): String
      + setCode(String code)
      + getCredits(): int
      + setCredits(int credits)
      + getStudents(): List~Student~
      + getTeachers(): List~Teacher~    
      }

    class SubjectName {
      <<enumeration>>
      MATH
      SCIENCE
      PHYSICS
      CHEMISTRY
      HISTORY
      GEOGRAPHY
      ENGLISH
      COMPUTER_SCIENCE    }

  Person <|-- Student
  Person <|-- Teacher
  Student "0..*" --> "0..*" Subject : "enrolled in"
  Teacher "0..*" --> "0..*" Subject : "teaches"    
```
---
## Endpoints Disponíveis

1. **Estudantes (/students)**
   * GET /students - Retorna todos os estudantes. 
   * GET /students/{id} - Retorna um estudante por ID.
   * POST /students - Cria um novo estudante.
   * PUT /students/{id} - Atualiza um estudante existente.
   * DELETE /students/{id} - Deleta um estudante.

   <br>
2. **Professores (/teachers)**
   * GET /teachers - Retorna todos os professores.
   * GET /teachers/{id} - Retorna um professor por ID.
   * POST /teachers - Cria um novo professor.
   * PUT /teachers/{id} - Atualiza um professor existente.
   * DELETE /teachers/{id} - Deleta um professor.

   <br>
3. **Classes (/subjects)**
  * GET /subjects - Retorna todos as aulas/matérias.
  * GET /subjects/{id} - Retorna um aula/matéria por ID.
---
## Acesso ao projeto

### Pré-requisitos
* Java 21+
* Maven

### Como executar

1. Clone o repositório.
```bash    
    ` git clone git@github.com:Elisabete-MO/java-classboard.git` 
```
2. Navegue até o diretório do projeto:
```bash
cd java-classboard
```
3. Configure o banco de dados (opcional): 
<br>Por padrão, a aplicação usa o H2 Database em memória.
<br>
<br>
4. Execute o comando:
``` bash
mvn spring-boot:run
```
5. Acesse o console [H2](http://localhost:8080/h2-console) (opcional):
<br> Credenciais padrão: usuário teste, senha teste123.
<br>
<br>
6. Acesse a documentação [Swagger](http://localhost:8080/swagger-ui/index.
   html#/), para visualização dos endpoints e poder testá-los diretamente pelo navegador. Isso facilita a exploração da API 
   sem a necessidade de uma ferramenta externa. para explorar os endpoints.

<img alt="tela do swagger" src="./images/swagger.png" width="60%"/>

---
## Testes
<img alt="tela de cobertura de testes" src="./images/testes.png" width="60%"/>

### Executando os testes pela IDE

1. Certifique-se de que o projeto está aberto em uma IDE compatível.
2. Navegue até a pasta src/test/java.
3. Clique com o botão direito sobre o pacote de testes (ClassBoard).
4. Selecione a opção "Run '_All Tests_'"

### Executando os testes pelo Maven

1. Navegue até o diretório raiz do projeto:
```bash
cd java-classboard
```
2. Execute o seguinte comando:
```bash
mvn test
```

3. O Maven executará todos os testes definidos no projeto e exibirá o resultado no terminal.


---


## Autora
<table>
  <tr>
    <td align="center">
      <a href="https://github.com/Elisabete-MO">
        <img loading="lazy" src="https://avatars.githubusercontent.com/Elisabete-MO?v=4" width="115"/><br />
        <sub><b>Elisabete Oliveira</b></sub>
      </a>
    </td>
  </tr>
</table>
