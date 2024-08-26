### Курсовой проект "Сетевой чат"

В проекте созданы два приложения для обмена текстовыми сообщениями по сети с помощью консоли (терминала) между двумя и более пользователями.

1. Первое приложение - сервер чата, ожидает подключения пользователей.\
Серверная части состоит из:

> - ChatServer.java — класс, который описывает логику работы сервера.
> - ClientThread.java — класс, в котором обрабатывается подключение клиента к серверу.

2. Второе приложение - клиент чата, подключается к серверу чата и осуществляет доставку и получение новых сообщений.\
   Клиентская часть состоит из:
> - Client.java — класс, который описывает логику работы клиента.
> - ReadMsg.java — класс, читающий сообщения с сервера в отдельном потоке.
> - WriteMsg.java — класс, отправляющий сообщения на сервер в отдельном потоке.

Все сообщения записываются в file.log как на сервере, так и на клиентах. File.log дополняется при каждом запуске, а также при отправленном или полученном сообщении.

Выход из чата осуществлен по команде exit.

Использован сборщик пакетов gradle.\
Код покрыт unit-тестами.
