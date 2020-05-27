# Api Fast

As definições da arquitetura, foi baseado nos requisitos não funcionais de disponilidade e escalabilidade. Pensando em uma forma de escalar horizontalmente, foi definido o kafka para processar o volume de requisições. Em resumo o kafka é um mensage broker robusto que tem execução assíncrona, com alta disponibilidade e capacidade de recuperar mensagens em uma possível parada do servidor. 

### Tabelas do Projeto:

* cdr

* cliente
* saldo
* conta
* produto

### Endpoints/serviço gerenciados com o kafka:

* CdrController

No CdrController todas requisições são enviadas ao kafka, nesse processo é armazenado no banco o CDR e em seguida uma nova mensagem é enviado ao kafka para processar o saldo atual do cliente. A consulta do saldo é mantido em cache com redis.  

<ul>
  <li>post - /api/v1/cdr</li>
  <li>delete - /api/v1/cdr/{id}</li>
</ul>

Este service é executado por uma scheduler diária, que envia para o kafka, gerar a conta do mês para pagamento.

* FechamentoContaService


### Tecnologias:

* Spring Boot
* postgres
* zookeeper
* kafka
* Redis
* Lombok
* Swagger
* Junit4
* Model Mapper

Comandos para montar o ambiente:
```
docker-compose up -d 
```
### Documentação - swagger

```
http://localhost:8080/swagger-ui.html
```
### Comandos do Redis

Os comandos abaixo mostrará o cache que à aplicação criou, no serviço do saldo.

```
docker exec -it gotham_redis_1 bash

root@redis:/data# redis-cli -p 6379

127.0.0.1:6379> KEYS *
```
