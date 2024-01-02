Serviço para controlar as vagas de estacionamento de um estabelecimento (condomínios, comércios, etc).

Como executar a aplicação:
- Ter o Docker instalado na máquina
- Entrar no diretório raiz do projeto, e pelo terminal e executar o comando docker compose up -d
- Entrar na classe/metodo ParkingServiceApplication.class/main(String[] args)
- Rodar a aplicação clicando no botão Run de sua IDE de preferência

Endereço para a documentação do Swawgger: http://localhost:8080/swagger-ui/index.html

Exemplo de payload:
```java
{
    "parkingSpotNumber": "140",
    "licensePlate": "JJK-1960",
    "carBrand": "Ford",
    "carModel": "Fiesta",
    "carColor": "Branco",
    "responsibleName": "Regis Yamamoto",
    "apartment": "504",
    "block": "2"
}
```

TODO:
- JWT
- Documentação
- Rodar com Docker
- Escrever teste unitário
- Tratar erros com @ControllerAdvice
- Conferir se vai salvar data em UTC no banco
