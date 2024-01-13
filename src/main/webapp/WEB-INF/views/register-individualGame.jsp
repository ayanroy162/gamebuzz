<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Individual Game Registration</title>
</head>
<body>
    <h2>User Registration</h2>
    <form action="/register" method="post" modelAttribute="tournament">
        <label for="tournamentName">TournamentName:</label>
        <input type="text" id="tournamentName" name="tournamentName" required />
        <button type="submit">Register</button>
    </form>
</body>
</html>