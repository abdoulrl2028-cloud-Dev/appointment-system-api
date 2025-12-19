# 📅 Appointment System API - Documentação Completa

Uma API RESTful completa e robusta para gerenciamento de agendamentos de serviços, desenvolvida com as melhores práticas de desenvolvimento Java e arquitetura de software.

---

## 🚀 Tecnologias Utilizadas

### Backend Framework
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-green?logo=spring-boot&logoColor=white)
[![Spring Boot](https://spring.io/img/projects/spring-boot.svg)](https://spring.io/projects/spring-boot)

### Linguagem
![Java](https://img.shields.io/badge/Java-17-orange?logo=java&logoColor=white)
[![Java](https://www.oracle.com/java/)](https://www.oracle.com/java/)

### Banco de Dados
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql&logoColor=white)
[![MySQL](https://www.mysql.com/)](https://www.mysql.com/)

### ORM
![Hibernate](https://img.shields.io/badge/Hibernate-6.x-red?logo=hibernate&logoColor=white)
[![Hibernate](https://hibernate.org/)](https://hibernate.org/)

### Gerenciador de Dependências
![Maven](https://img.shields.io/badge/Maven-3.x-C71C22?logo=apache-maven&logoColor=white)
[![Maven](https://maven.apache.org/)](https://maven.apache.org/)

### Documentação API
![Swagger/OpenAPI](https://img.shields.io/badge/Swagger%2FOpenAPI-3.0-85EA2D?logo=swagger&logoColor=white)
[![Swagger/OpenAPI](https://swagger.io/)](https://swagger.io/)

### Ferramentas de Desenvolvimento
- **Lombok**: Redução de boilerplate de código
- **Validation**: Validação de dados com Jakarta Bean Validation
- **H2 Database**: Banco de dados em memória para testes

---

## 📁 Estrutura do Projeto

```
src/main/java/com/seuprojeto/appointmentsystem
│
├── controller/                 # Controllers REST
│   ├── AppointmentController   # Gerenciamento de agendamentos
│   ├── UserController          # Gerenciamento de usuários
│   └── ServiceController       # Gerenciamento de serviços
│
├── service/                    # Lógica de negócio
│   ├── AppointmentService      # Operações de agendamento
│   ├── UserService             # Operações de usuário
│   └── ServiceService          # Operações de serviço
│
├── repository/                 # Acesso a dados
│   ├── AppointmentRepository
│   ├── UserRepository
│   └── ServiceRepository
│
├── entity/                     # Entidades JPA
│   ├── Appointment
│   ├── User
│   └── ServiceEntity
│
├── dto/                        # Data Transfer Objects
│   ├── request/
│   │   ├── AppointmentRequestDTO
│   │   ├── UserRequestDTO
│   │   └── ServiceRequestDTO
│   │
│   └── response/
│       ├── AppointmentResponseDTO
│       ├── UserResponseDTO
│       └── ServiceResponseDTO
│
├── exception/                  # Tratamento de exceções
│   ├── BusinessException
│   ├── ResourceNotFoundException
│   ├── ErrorResponse
│   └── GlobalExceptionHandler
│
├── config/                     # Configurações
│   └── SwaggerConfig
│
└── AppointmentSystemApplication.java  # Classe principal
```

---

## 🔧 Instalação e Configuração

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6 ou superior
- MySQL 8.0 ou superior

### Passos de Instalação

1. **Clone o repositório**
```bash
git clone https://github.com/seu-usuario/appointment-system-api.git
cd appointment-system-api
```

2. **Configure o banco de dados**
Crie um banco de dados MySQL:
```sql
CREATE DATABASE appointment_system_db;
```

3. **Configure as propriedades da aplicação**
Edite o arquivo `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/appointment_system_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

4. **Instale as dependências e execute**
```bash
mvn clean install
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

---

## 📚 Documentação da API

### Swagger UI
Acesse a documentação interativa em:
```
http://localhost:8080/swagger-ui.html
```

### OpenAPI Spec
O arquivo OpenAPI está disponível em:
```
http://localhost:8080/v3/api-docs
```

---

## 🔌 Endpoints Principais

### Usuários (Users)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/v1/users` | Criar novo usuário |
| `GET` | `/api/v1/users/{id}` | Obter usuário por ID |
| `GET` | `/api/v1/users/email/{email}` | Obter usuário por email |
| `GET` | `/api/v1/users` | Listar todos os usuários |
| `PUT` | `/api/v1/users/{id}` | Atualizar usuário |
| `DELETE` | `/api/v1/users/{id}` | Deletar usuário |

### Serviços (Services)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/v1/services` | Criar novo serviço |
| `GET` | `/api/v1/services/{id}` | Obter serviço por ID |
| `GET` | `/api/v1/services` | Listar todos os serviços |
| `GET` | `/api/v1/services/active` | Listar serviços ativos |
| `GET` | `/api/v1/services/search?name=` | Buscar serviços por nome |
| `PUT` | `/api/v1/services/{id}` | Atualizar serviço |
| `DELETE` | `/api/v1/services/{id}` | Deletar serviço |

### Agendamentos (Appointments)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/v1/appointments` | Criar novo agendamento |
| `GET` | `/api/v1/appointments/{id}` | Obter agendamento por ID |
| `GET` | `/api/v1/appointments` | Listar todos os agendamentos |
| `GET` | `/api/v1/appointments/user/{userId}` | Obter agendamentos de um usuário |
| `GET` | `/api/v1/appointments/status/{status}` | Obter agendamentos por status |
| `PUT` | `/api/v1/appointments/{id}` | Atualizar agendamento |
| `PATCH` | `/api/v1/appointments/{id}/cancel` | Cancelar agendamento |
| `PATCH` | `/api/v1/appointments/{id}/confirm` | Confirmar agendamento |
| `DELETE` | `/api/v1/appointments/{id}` | Deletar agendamento |

---

## 📝 Exemplos de Requisições

### Criar Usuário
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

### Criar Serviço
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

### Criar Agendamento
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

---

## 🛡️ Tratamento de Exceções

A API implementa tratamento robusto de exceções com respostas padronizadas:

```json
{
  "status": 404,
  "message": "Usuário não encontrado com ID: 999",
  "error": "Resource Not Found",
  "timestamp": "2024-12-19T10:30:00",
  "path": "/api/v1/users/999"
}
```

---

## 🔐 Validação de Dados

A API valida automaticamente todos os dados de entrada:

- ✅ Campos obrigatórios
- ✅ Formato de email válido
- ✅ Valores numéricos positivos
- ✅ Datas futuras para agendamentos
- ✅ Unicidade de email

---

## 📊 Modelo de Dados

### User (Usuário)
```
id: Long (PK)
name: String
email: String (Unique)
phone: String
role: String
createdAt: LocalDateTime
updatedAt: LocalDateTime
```

### ServiceEntity (Serviço)
```
id: Long (PK)
name: String
description: String
price: BigDecimal
durationMinutes: Integer
active: Boolean
createdAt: LocalDateTime
updatedAt: LocalDateTime
```

### Appointment (Agendamento)
```
id: Long (PK)
user: User (FK)
service: ServiceEntity (FK)
appointmentDateTime: LocalDateTime
status: String (SCHEDULED, CONFIRMED, CANCELLED)
notes: String
createdAt: LocalDateTime
updatedAt: LocalDateTime
```

---

## 🧪 Testes

Execute os testes unitários:
```bash
mvn test
```

---

## 🤝 Contribuindo

1. Faça um Fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

---

## 📄 Licença

Distribuído sob a Licença Apache 2.0. Veja o arquivo `LICENSE` para mais detalhes.

---

## 👨‍💻 Autor

**Seu Nome**
- Email: seu.email@example.com
- GitHub: [@seu-usuario](https://github.com/seu-usuario)
- LinkedIn: [seu-perfil](https://linkedin.com/in/seu-perfil)

---

## 📞 Suporte

Para suporte, envie um email para seu.email@example.com ou abra uma issue no repositório.

---

## 🔗 Links Úteis

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [Swagger/OpenAPI Specification](https://swagger.io/)
- [Hibernate Documentation](https://hibernate.org/orm/documentation/)
- [Jakarta Bean Validation](https://beanvalidation.org/)
- [Project Lombok](https://projectlombok.org/)

---

## 📈 Roadmap

- [ ] Implementar autenticação JWT
- [ ] Adicionar autorização baseada em roles
- [ ] Implementar paginação em listagens
- [ ] Adicionar filtros avançados
- [ ] Implementar auditoria de mudanças
- [ ] Adicionar notificações por email
- [ ] Implementar cache distribuído
- [ ] Adicionar testes de integração
- [ ] Deploy em Docker
- [ ] CI/CD Pipeline

---

**⭐ Se este projeto foi útil para você, considere dar uma estrela!**
