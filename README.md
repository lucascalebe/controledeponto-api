# Controle de Ponto API :clock4:

### Descrição do projeto :seedling:

API RESTful que faz o controle de registros de ponto de um funcionário.

### STATUS :technologist:
- Momentos[BATIDAS] (Finalizado)
- Testes:
    - BatidaController
    - Integração
    - API
- Documentação pelo Swagger (Finalizado)
- Docker

### Pré-requisitos :thumbsup:

- JDK 11 e Maven
- Banco MySQL
- IntelliJ ou Eclipse, preferencialmente

### Foi utilizado :point_down:

- Postman
- Docker

### Caso ocorra problema para subir o Docker em Windows:
- Clone com: git clone https://github.com/lucascalebe/controledeponto-api.git  --config core.autocrlf=input

### subir aplicação via DOCKER :point_down:

- Iniciar docker no PC
- no diretório do projeto clonado -> ./mvnw package -DskipTests -Pdocker
- após o package -> docker-compose up

### Instalação da aplicação :point_down:

- IntelliJ/Eclipse: Importar como projeto Maven.
- mvn install

### Iniciar aplicação :point_down:

- Rodar a classe ControledepontoApiApplication

### Comando para os testes :point_down:

- ./mvnw verify (teste de integração e API)
OBS: Com docker rodando, pois necessita do banco...

### Documentação da API :speech_balloon:

- Basta acessar '/swagger-ui.html' na porta em uso

## Comportamento da API: :anger:

`POST v1/batidas/`

##### Response

    HTTP/1.1 201 CREATED
    Content-Type: application/json

    "id": 1,
    "dataHora": "2021-11-12T08:00:00",
    "links": [
        {
            "rel": "self",
            "href": "http://localhost:8080/v1/batidas/1"
        },
        {
            "rel": "batidas",
            "href": "http://localhost:8080/v1/batidas{?data}"
        }
    ]
}

`GET v1/batidas{?data}`

##### Response

       HTTP/1.1 200 OK
       Content-Type: application/json
         
       {
        "links": [
          {
            "rel": "self",
            "href": "http://localhost:8080/v1/batidas{?data}"
          }
        ],
        "content": [
          {
            "id": 1,
            "dataHora": "2021-11-12T08:00:00",
            "links": [
              {
                "rel": "self",
                "href": "http://localhost:8080/v1/batidas/1"
              },
              {
                "rel": "batidas",
                "href": "http://localhost:8080/v1/batidas{?data}"
              }
            ]
          }
        ]
      }       


`GET v1/batidas/1`

##### Response

      HTTP/1.1 200 OK
      Content-Type: application/json
      
      {
        "id": 1,
        "dataHora": "2021-11-12T08:00:00",
        "links": [
          {
              "rel": "self",
              "href": "http://localhost:8080/v1/batidas/1"
          },
          {
              "rel": "batidas",
              "href": "http://localhost:8080/v1/batidas{?data}"
          }
        ]
      }
     
    
    
