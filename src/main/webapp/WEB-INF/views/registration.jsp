<!-- src/main/webapp/WEB-INF/views/registration.jsp -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
</head>
<body>

	<h2>All Registration</h2>
		<c:forEach var="tournamentDto" items="${allTournamentDtos}">
		TournamentName - ${tournamentDto.tournamentName}
		Listed games -
		<c:forEach var="gameDto" items="${tournamentDto.games}">
            <li>
                ${gameDto.name}
            </li>
        </c:forEach>
        </c:forEach>
    </form>
    
    
    <h2>New Registration</h2>
    <form action="/register" method="post" modelAttribute="tournament">
        <label for="tournamentName">TournamentName:</label>
        <input type="text" id="tournamentName" name="tournamentName" required />
		<h2>ALL GAMES</h2>
		(Count: ${status.index + 0})
		<c:forEach var="individualGame" items="${individualGames}">
				<label><input type="checkbox" name="selectedIndividualGames" value="${individualGame.id}" />
                ${individualGame.id}-${individualGame.gameType}<br/>
                </label><br/>
                (Count: ${status.index + 1})
    	</c:forEach>
        <button type="submit">Register</button>
    </form>
</body>
</html>
