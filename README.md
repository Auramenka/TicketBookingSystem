# Ticket Booking System
**Технологии** <br>
Java EE: сервлеты и JSP страницы для обработки запросов и генерации динамического контента <br>
PostgreSQL: реляционная база данных для хранения данных приложения <br> 
Apache Tomcat: веб-сервер для развертывания и выполнения веб-приложения <br>
Hibernate: ORM фреймворк для работы с базой данных <br>

**Промежуточный план разработки проекта:** <br>
Неделя 1 <br>
26.11 - 27.11 - анализ требований, проектирование схемы бд, настройка сервера <br>
28.11 - создание бд <br>
29.11 - 01.12 - регистрация пользователей <br>

Неделя 2 <br>
02.12 - 03.12 - просмотр доступных мероприятий (краткое содержание мероприятия) <br>
04.12 - 05.12 - просмотр мероприятия с подробным описанием, авторизация <br>
06.12 - 08.12 - панель администратора для добавления и изменения мероприятий <br>

Неделя 3 <br>
09.12 - 11.12 бронирование билета <br> 
12.12 - фильтрация по дате, профиль пользователя (возможность редактирования данных) <br>
13.12 - финальные правки по всему проекту

# Подробное описание функционала:
**Регистрация пользователей** <br>
Эндпоинт: POST /register <br>
Описание: позволяет пользователям зарегистрироваться в системе. Проверяется username и email на уникальность. <br>
Страница для регистрации: /register.jsp <br>
Страница об успешном прохождении регистрации: /registrationSuccess.jsp <br>

**Просмотр доступных мероприятий (краткое содержание мероприятия)** <br>
Эндпоинт: GET /events <br>
Описание: получение списка доступных мероприятий с кратким описанием (фото, даты (с какого по какое число будет мероприятие) и название). Предоставляется возможность выбора категории мероприятия (Кино, Спектакли, Концерты). Каждое мероприятие содержит ссылку на страницу с подробным описанием <br>
Страница для отображения списка мероприятий: /events.jsp <br>

**Просмотр мероприятия с подробным описанием** <br>
Эндпоинт: GET /eventDetail?id={id} <br>
Описание: получение подробной информации о конкретном мероприятии (фото, название, даты (с какого по какое число будет мероприятие), описание мероприятия) <br>
Страница для отображения списка мероприятий: /event-details.jsp <br>

**Авторизация** <br>
Эндпоинт: POST /login <br>
Описание: позволяет пользователям войти в систему. Реализовано разделение ролей (ADMIN, USER) <br>
Страница для авторизации: /login.jsp <br>

Эндпоинт: GET /logout <br>
Описание: позволяет пользователям выйти из системы <br>
Страница с информацией о выходе из системы: /logout.jsp <br>

**Панель администратора для добавления и изменения мероприятий** <br>
Эндпоинт: POST /addEvent <br>
Описание: позволяет администратору добавлять новые мероприятия. Добавление мероприятия реализовано во вкладке Управление мероприятиями (/manageEvents.jsp) <br>
Страница для добавления нового мероприятия: /addEvent.jsp <br>

Эндпоинт: POST /editEvent?id={id} <br>
Описание: позволяет администратору изменять данные о мероприятии. <br>
Страница для редактирования мероприятия: /editEvent.jsp <br>

Эндпоинт: POST /deleteEvent?id={id} <br>
Описание: позволяет администратору удалять мероприятия <br>
Страница для подтверждения удаления мероприятия: /deleteEvent.jsp <br>

**Добавление и удаление сеанса** <br>
Эндпоинт: POST /addSeance <br>
Описание: позволяет администратору добавлять новые сеансы <br>
Страница для добавления нового сеанса: /addSeance.jsp <br>
Страница об успешном создании сеанса: /addSeanceSuccess.jsp <br>

Эндпоинт: POST /deleteSeance?id={id} <br>
Описание:позволяет администратору удалять сеансы <br>
Страница для удаления сеанса: /deleteSeance.jsp <br>

**Удаление пользователя** <br>
Эндпоинт: POST /deleteUser?id={id} <br>
Описание: позволяет администратору удалять пользователей <br>
Страница для удаления пользователя: /deleteUser.jsp <br>

**Просмотр доступных сеансов для конкретного мероприятия** <br>
Эндпоинт: GET /seance?eventId={id} <br>
Описание: позволяет всем пользователям просматривать сеансы для конкретного мероприятия, если роль ADMIN предоставляется возможность добавления сеанса, удаление сеанса <br>
Страница для просмотра всех сеансов: /seances.jsp <br>

**Бронирование билета (выбор сеанса, места и бронирование)** <br>
Эндпоинты: GET /bookSeats?seanceId={id} (для выбора мест), POST /bookSeats (предварительный просмотр заказа), POST /order <br>
Описание: пользователю после выбора сеанса, нажав на время предоставляется выбор места (ряд и номер места), после выбора места пользователь может предварительно ознакомиться с заказом (название мероприятия, дата начала, время начала и выбранные места) <br>
Страницы: /booking.jsp (список мест с возможностью выбора), /orderPreview.jsp (предварительный просмотр заказа), /orderConfirmation.jsp (страница с сообщением об успешном создании заказа) <br>

**Профиль пользователя (возможность редактирования данных)** <br>
Эндпоинт: GET /profile <br>
Описание: получение информации о пользователе (имя пользователя, электронная почта) <br>
Страница для просмотра профиля: /profile.jsp <br>

**Пользователь может редактировать данные** <br>
Эндпоинт: POST /editProfile?id={id} <br>
Описание: обновление информации о пользователе <br>
Страница для редактирования данных: /editProfile.jsp <br>

**Просмотр заказов** <br>
Эндпоинт: GET /order?userId={id} <br>
Описание: пользователь может просматривать свои заказы <br>
Страница для просмотра заказов: /myOrders.jsp <br>

**Фильтрация по дате** <br>
Эндпоинт: GET /events?startDate={dateStart}&endDate={dateEnd} <br>
например: /events?startDate=2024-12-03&endDate=2024-12-31 <br>
Описание: фильтрация по дате, на главной странице необходимо выбрать желаемый промежуток времени (даты) <br>
Страница с примененными фильтрами: /events.jsp

# Запуск проекта:
**1** Склонировать репозиторий git clone https://github.com/Auramenka/TicketBookingSystem.git <br>
**2** Соберите проект (build) <br>
**3** Сделать сборку контейнеров (docker-compose up) <br>
**4** Перейти на главную страницу [http://localhost:8080/events](http://localhost:8080/events)

