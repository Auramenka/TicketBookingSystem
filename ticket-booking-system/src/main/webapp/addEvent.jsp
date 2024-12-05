<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить мероприятие</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        form {
            background: white;
            max-width: 500px;
            margin: auto;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        input[type="text"],
        input[type="date"],
        textarea,
        select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .button-container {
            display: flex;
            justify-content: center;
            margin-top: 10px;
        }
        input[type="submit"], .btn-back {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 4px;
            cursor: pointer;
            width: 48%;
            margin: 5px;
            text-align: center;
        }
        input[type="submit"]:hover, .btn-back:hover {
            background-color: #218838;
        }
        .btn-back {
            background-color: #007bff;
        }
        .btn-back:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<h1>Добавить мероприятие</h1>
<form action="addEvent" method="POST">
    <label for="name">Название:</label>
    <input type="text" id="name" name="name" required/>

    <label for="description">Описание:</label>
    <textarea id="description" name="description" rows="4" required></textarea>

    <label for="dateStart">Дата начала:</label>
    <input type="date" id="dateStart" name="dateStart" required/>

    <label for="dateEnd">Дата окончания:</label>
    <input type="date" id="dateEnd" name="dateEnd" required/>

    <label for="category">Категория:</label>
    <select id="category" name="category">
        <option value="MOVIES">Кино</option>
        <option value="CONCERTS">Концерты</option>
        <option value="PERFORMANCES">Спектакли</option>
    </select>

    <label for="photo">Фото:</label>
    <input type="text" id="photo" name="photo"/>

    <div class="button-container">
        <input type="submit" value="Добавить"/>
        <a href="/manageEvents" class="btn-back">Назад</a>
    </div>
</form>
</body>
</html>