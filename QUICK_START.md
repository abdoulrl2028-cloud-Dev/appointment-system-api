# 🚀 QUICK START - Appointment System API

## Iniciar Rapidamente em 5 Minutos

### ✅ Opção 1: Com Maven (Recomendado para Desenvolvimento)

```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/appointment-system-api.git
cd appointment-system-api

# 2. Crie o banco de dados MySQL
mysql -u root -p << EOF
CREATE DATABASE IF NOT EXISTS appointment_system_db;
EOF

# 3. Configure credenciais (src/main/resources/application.properties)
# spring.datasource.username=root
# spring.datasource.password=sua_senha

# 4. Instale dependências e execute
mvn clean install
mvn spring-boot:run

# 5. Acesse http://localhost:8080/swagger-ui.html
```

---

### 🐳 Opção 2: Com Docker Compose (Recomendado para Produção)

```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/appointment-system-api.git
cd appointment-system-api

# 2. Execute Docker Compose
docker-compose up -d

# 3. Aguarde a inicialização (~30 segundos)
docker-compose logs -f appointment_api

# 4. Acesse http://localhost:8080/swagger-ui.html
```

---

## 📝 Primeiros Testes com cURL

### 1. Criar um Usuário
```bash
curl -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Silva",
    "email": "joao@example.com",
    "phone": "(11) 98765-4321",
    "role": "CUSTOMER"
  }'
```

**Resposta Esperada:**
```json
{
  "id": 1,
  "name": "João Silva",
  "email": "joao@example.com",
  "phone": "(11) 98765-4321",
  "role": "CUSTOMER",
  "createdAt": "19/12/2024 10:30:45",
  "updatedAt": "19/12/2024 10:30:45"
}
```

### 2. Criar um Serviço
```bash
curl -X POST http://localhost:8080/api/v1/services \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Corte de Cabelo",
    "description": "Corte clássico profissional",
    "price": 50.00,
    "durationMinutes": 30,
    "active": true
  }'
```

**Resposta Esperada:**
```json
{
  "id": 1,
  "name": "Corte de Cabelo",
  "description": "Corte clássico profissional",
  "price": 50.00,
  "durationMinutes": 30,
  "active": true,
  "createdAt": "19/12/2024 10:31:20",
  "updatedAt": "19/12/2024 10:31:20"
}
```

### 3. Criar um Agendamento
```bash
curl -X POST http://localhost:8080/api/v1/appointments \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "serviceId": 1,
    "appointmentDateTime": "2024-12-25T14:30:00",
    "notes": "Cliente solicitou estilo específico"
  }'
```

**Resposta Esperada:**
```json
{
  "id": 1,
  "userId": 1,
  "userName": "João Silva",
  "serviceId": 1,
  "serviceName": "Corte de Cabelo",
  "appointmentDateTime": "25/12/2024 14:30:00",
  "status": "SCHEDULED",
  "notes": "Cliente solicitou estilo específico",
  "createdAt": "19/12/2024 10:32:00",
  "updatedAt": "19/12/2024 10:32:00"
}
```

### 4. Listar Agendamentos de um Usuário
```bash
curl -X GET http://localhost:8080/api/v1/appointments/user/1 \
  -H "Content-Type: application/json"
```

### 5. Confirmar um Agendamento
```bash
curl -X PATCH http://localhost:8080/api/v1/appointments/1/confirm \
  -H "Content-Type: application/json"
```

---

## 📊 Status de Saúde da API

```bash
# Verificar se a API está rodando
curl http://localhost:8080/swagger-ui.html
```

Você deve ver a página do Swagger. Se não aparecer, verifique:
- Logs: `docker-compose logs appointment_api`
- MySQL está rodando: `docker-compose logs mysql`

---

## 🔧 Troubleshooting

### ❌ Erro: "Connection refused"
```bash
# Certifique-se que MySQL está rodando
docker-compose ps

# Se não aparecer, inicie
docker-compose up -d mysql
```

### ❌ Erro: "Port 8080 already in use"
```bash
# Encontre o processo usando a porta
lsof -i :8080

# Ou mude a porta em docker-compose.yml
# ports:
#   - "8081:8080"
```

### ❌ Erro: "Database connection failed"
```bash
# Verifique as credenciais em application.properties
# ou variáveis de ambiente do docker-compose.yml
```

---

## 📈 Próximas Ações

1. **Explorar a API**
   - Abra http://localhost:8080/swagger-ui.html
   - Teste todos os endpoints
   - Experimente diferentes status de agendamentos

2. **Personalizar para Seu Projeto**
   - Adicione mais campos nas entidades
   - Implemente autenticação
   - Customize validações

3. **Integrar com Frontend**
   - Clone o repositório do frontend
   - Configure CORS em SwaggerConfig
   - Comece a integração

---

## 📚 Links Úteis

- [Documentação Completa](./DOCUMENTACAO.md)
- [Mapa do Projeto](./MAPA_DO_PROJETO.md)
- [Swagger UI Local](http://localhost:8080/swagger-ui.html)
- [OpenAPI Spec](http://localhost:8080/v3/api-docs)

---

## 💡 Dicas de Desenvolvimento

### Recarregar Apenas as Classes (DevTools)
```bash
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.devtools.restart.enabled=true"
```

### Limpar e Recompilar
```bash
mvn clean install -DskipTests
```

### Ver Logs em Tempo Real
```bash
docker-compose logs -f appointment_api
```

### Acessar o Console MySQL
```bash
docker-compose exec mysql mysql -u user -p appointment_system_db
# Senha: password
```

---

## ✅ Checklist Rápido

- [ ] Clonar repositório
- [ ] Instalar dependências (`mvn clean install`)
- [ ] Criar banco de dados (`CREATE DATABASE appointment_system_db`)
- [ ] Iniciar aplicação (`mvn spring-boot:run` ou `docker-compose up`)
- [ ] Acessar Swagger (`http://localhost:8080/swagger-ui.html`)
- [ ] Criar um usuário (POST /api/v1/users)
- [ ] Criar um serviço (POST /api/v1/services)
- [ ] Criar um agendamento (POST /api/v1/appointments)
- [ ] Confirmar agendamento (PATCH /api/v1/appointments/{id}/confirm)

---

**🎉 Pronto! Você está com a Appointment System API rodando localmente!**
