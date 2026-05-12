📋 Tarefas API
API REST para gerenciamento de tarefas, desenvolvida com Java e Spring Boot como projeto de estudo pessoal.

🛠️ Tecnologias
TecnologiaVersãoJava17Spring Boot4.0.6Spring Web MVC-Spring Data JPA-Spring Validation-Lombok-PostgreSQL16Docker & Docker Compose-MavenWrapper incluso

✅ Pré-requisitos
Antes de rodar o projeto, certifique-se de ter instalado:

JDK 17+
Docker e Docker Compose


Não é necessário instalar Maven separadamente — o projeto já inclui o Maven Wrapper (mvnw).


🚀 Como rodar o projeto
1. Clone o repositório
bashgit clone https://github.com/seu-usuario/tarefas_api.git
cd tarefas_api
2. Suba o banco de dados com Docker
bashdocker compose up -d
Isso inicializa um container PostgreSQL 16 com as seguintes configurações:
ParâmetroValorContainertarefas_dbBancotarefas_dbUsuáriopostgresSenhapostgresPorta5432Volumetarefas_data
3. Rode a aplicação
Linux/macOS:
bash./mvnw spring-boot:run
Windows:
bashmvnw.cmd spring-boot:run
Ou pela IDE (IntelliJ IDEA):
Abra a classe TarefasApiApplication.java e clique no botão Run ▶.
A API estará disponível em: http://localhost:8080

⚙️ Configuração
As configurações da aplicação ficam em src/main/resources/application.properties.
Exemplo de configuração para o banco local (Docker):
propertiesspring.datasource.url=jdbc:postgresql://localhost:5432/tarefas_db
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

O ddl-auto=update faz o Hibernate criar/atualizar as tabelas automaticamente ao iniciar a aplicação.


📁 Estrutura do projeto
tarefas_api/
│
├── docker-compose.yml
├── pom.xml
├── mvnw / mvnw.cmd
│
└── src/
    └── main/
        ├── java/com/caio/tarefas_api/
        │   │
        │   ├── TarefasApiApplication.java      # Ponto de entrada da aplicação
        │   │
        │   ├── controller/
        │   │   └── TarefaController.java        # Endpoints REST
        │   │
        │   ├── services/
        │   │   └── TarefaService.java           # Regras de negócio
        │   │
        │   ├── repository/
        │   │   └── TarefaRepository.java        # Acesso ao banco via JPA
        │   │
        │   ├── model/
        │   │   ├── Tarefa.java                  # Entidade JPA (tabela `tarefas`)
        │   │   └── StatusTarefa.java            # Enum de status da tarefa
        │   │
        │   └── dto/
        │       ├── TarefaRequestDTO.java        # Corpo de entrada das requisições
        │       └── TarefaResponseDTO.java       # Corpo de saída das respostas
        │
        └── resources/
            └── application.properties

📡 Endpoints da API
Base URL: http://localhost:8080

POST /tarefas — Criar tarefa
Request body:
json{
  "titulo": "Estudar Spring Boot",
  "descricao": "Revisar anotações e fazer exercícios práticos",
  "status": "PENDENTE"
}
Response 201 Created:
json{
  "id": 1,
  "titulo": "Estudar Spring Boot",
  "descricao": "Revisar anotações e fazer exercícios práticos",
  "status": "PENDENTE"
}

GET /tarefas — Listar todas as tarefas
Response 200 OK:
json[
  {
    "id": 1,
    "titulo": "Estudar Spring Boot",
    "descricao": "Revisar anotações e fazer exercícios práticos",
    "status": "PENDENTE"
  },
  {
    "id": 2,
    "titulo": "Configurar Docker",
    "descricao": "Subir banco com Docker Compose",
    "status": "CONCLUIDA"
  }
]

GET /tarefas/{id} — Buscar tarefa por ID
Exemplo: GET /tarefas/1
Response 200 OK:
json{
  "id": 1,
  "titulo": "Estudar Spring Boot",
  "descricao": "Revisar anotações e fazer exercícios práticos",
  "status": "PENDENTE"
}
Response 404 Not Found — quando o ID não existe.

GET /tarefas/status?status={STATUS} — Listar tarefas por status
Exemplo: GET /tarefas/status?status=PENDENTE
Response 200 OK:
json[
  {
    "id": 1,
    "titulo": "Estudar Spring Boot",
    "descricao": "Revisar anotações e fazer exercícios práticos",
    "status": "PENDENTE"
  }
]

PUT /tarefas/{id} — Atualizar tarefa
Exemplo: PUT /tarefas/1
Request body:
json{
  "titulo": "Estudar Spring Boot",
  "descricao": "Concluído!",
  "status": "CONCLUIDA"
}
Response 200 OK:
json{
  "id": 1,
  "titulo": "Estudar Spring Boot",
  "descricao": "Concluído!",
  "status": "CONCLUIDA"
}

DELETE /tarefas/{id} — Deletar tarefa
Exemplo: DELETE /tarefas/1
Response 204 No Content — sem corpo na resposta.

📊 Enum StatusTarefa
ValorDescriçãoPENDENTETarefa ainda não iniciadaEM_ANDAMENTOTarefa em progressoCONCLUIDATarefa finalizada
Fluxo esperado:
PENDENTE → EM_ANDAMENTO → CONCLUIDA

🧪 Testando a API
Você pode usar qualquer cliente HTTP:

Postman
Insomnia
HTTPie
cURL (exemplos abaixo)

Exemplos com cURL
bash# Criar uma tarefa
curl -X POST http://localhost:8080/tarefas \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Minha tarefa","descricao":"Descrição aqui","status":"PENDENTE"}'

# Listar todas
curl http://localhost:8080/tarefas

# Buscar por ID
curl http://localhost:8080/tarefas/1

# Listar por status
curl "http://localhost:8080/tarefas/status?status=PENDENTE"

# Atualizar
curl -X PUT http://localhost:8080/tarefas/1 \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Minha tarefa","descricao":"Atualizada","status":"CONCLUIDA"}'

# Deletar
curl -X DELETE http://localhost:8080/tarefas/1

🐛 Problemas comuns
Porta 5432 já em uso
bash# Verifique se já tem um PostgreSQL rodando localmente
sudo lsof -i :5432
# Pare o serviço ou mude a porta no docker-compose.yml
docker compose não reconhecido
Use docker-compose (com hífen) em versões mais antigas do Docker:
bashdocker-compose up -d
Erro de conexão com o banco ao iniciar
Certifique-se de que o container está de pé antes de rodar a aplicação:
bashdocker compose ps

👤 Autor
Caio Braz
GitHub • LinkedIn

📄 Licença
Este projeto é de uso livre para fins de estudo.
