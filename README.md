# MoneyFlowBackend

## Авторы

**ФИО:** Зотов Артем Игоревич  
**Университет:** ВлГУ им. А.Г и Н.Г. Столетовых, ИИТЭ  
**Город проживания:** Владимир  

## Основной функционал

### 1) Роуты
- `GET /ping` → HTML: `PONG`
- `GET /health` → JSON: `{ "status": "HEALTHY" }`
- `GET /list` → HTML-таблица с погодой из PostgreSQL
- `POST /add` → добавление записи `{ city, temperature }`

### 2) База данных
- PostgreSQL, таблица: `weather(city TEXT, temperature INT)`
- При старте добавляются базовые данные ['Moscow', 20)

### 3) Docker-окружение
- Backend
- PostgreSQL
- Nginx (reverse proxy на порт 80)

### 4) CI/CD
- GitHub Actions: автоматическая сборка и тест при push в `main`
- Сборка backend, docker-образа и docker-compose


## Стек технологий

- Язык: Java 17
- Фреймворк: Spring Boot 2.7
- База данных: PostgreSQL (через Docker)
- CI: GitHub Actions
- REST API
- Reverse Proxy: Nginx
- Сборка: Maven
- Контейнеризация: Docker + Compose

## Примеры запросов

curl http://localhost/ping
# PONG
curl http://localhost/health
# {"status": "HEALTHY"}
curl http://localhost/list
# HTML-таблица с погодой
curl -X POST http://localhost/add \
     -H "Content-Type: application/x-www-form-urlencoded" \
     -d "city=Vladimir&temperature=25"
