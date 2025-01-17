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
адреса, по которым после запуска доступны

- kotiki-service:

http://localhost:8181

- БД (postgres):

http://localhost:15432

- S3 storrage:

http://localhost:8000

! для того, чтобы создать бакет и добавить туда картинки с косметиками, нужно запустить скрипт s3_script.rb В ТОЙ ЖЕ ДИРРЕКТОРИИ, где этот скрипт лежит. Предварительно нужно [установить ruby+devkit](https://www.ruby-lang.org/en/documentation/installation/), а также гем aws-sdk:

```
gem install aws-sdk
```
далее В ТОЙ ЖЕ ДИРРЕКТОРИИ, где лежит скрипт (web-kotiki/s3) запускаем 

```
ruby s3_script.rb
```

теперь картинкa с индексом 'url-key' доступна по адресу http://localhost:8000/kotiki/url-key

подключение к базе данных (пароль password):
```
psql -h localhost -p 15432 -U username -d postgres
```
