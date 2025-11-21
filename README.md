# RMS - Solicitar Afiliação (Entrega)

Projeto minimal para o caso de uso **Solicitar Afiliação**.
Inclui:

- Entidade `PedidoAfiliacao` (status mapeado como ENUM/STRING)
- Repositório JPA, Service e Controller REST
- Docker + MySQL init script that creates the expected schema (status VARCHAR)
- Endpoints:
  - POST /api/pedidos  -> criar pedido (body: { "requesterName": "...", "email": "..." })
  - GET  /api/pedidos  -> lista todos
  - GET  /api/pedidos/{id} -> obter por id
  - PUT  /api/pedidos/{id}/status?status=APPROVED -> atualizar status

## Como rodar (recomendado)

1. Remova containers antigos (se existirem) que possam ter mesmo nome:
   ```bash
   docker rm -f rms-app rms-db || true
   ```

2. Construa e suba com Docker Compose:
   ```bash
   docker compose up --build
   ```

3. Testes rápidos com curl (no host):
   ```bash
   # criar pedido
   curl -X POST http://localhost:8080/api/pedidos -H "Content-Type: application/json" -d '{"requesterName":"Minha ONG","email":"contato@ong.org"}'

   # listar
   curl http://localhost:8080/api/pedidos

   # obter por id
   curl http://localhost:8080/api/pedidos/1

   # atualizar status
   curl -X PUT "http://localhost:8080/api/pedidos/1/status?status=APPROVED"
   ```

## Observações importantes
- O script `mysql-init/init.sql` recria o banco `rede_mais_social` e define a tabela `pedido_afiliacao` com `status VARCHAR(255)`. Se você já tem dados, remova o container e o volume antes de rodar para garantir que o script seja aplicado.
- Se preferir não recriar o banco automaticamente, edite/remova `mysql-init/init.sql` e ajuste `spring.jpa.hibernate.ddl-auto` em `application.properties`.
