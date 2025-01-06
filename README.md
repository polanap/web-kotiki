# web-kotiki

запуск сервера:
```
docker-compose up
```
перезапуск сервера, если был изменен код или какие-то конфигурации (пересоздает image) -- команды вводить по очереди, нажимая после каждой enter:
```
docker-compose down
docker-compose up --build
```
для перезапуска
адреса, по которым после запуска доступны

- kotiki-service:

http://localhost:8181

- БД (postgres):

http://localhost:15432

подключение к базе данных (пароль password):
```
psql -h localhost -p 15432 -U username -d postgres
```
