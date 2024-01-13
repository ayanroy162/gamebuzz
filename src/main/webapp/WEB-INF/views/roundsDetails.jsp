<!-- src/main/webapp/WEB-INF/views/registration.jsp -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
</head>
<body>

	<h2>All Details</h2>
		<c:forEach var="tournamentDto" items="${tournamentDtos}">
		TournamentName - ${tournamentDto.tournamentName}
		Listed games -
		<c:forEach var="gameDto" items="${tournamentDto.games}">
            <li>
                ${gameDto.name} </br>
                NUMBER OF ROUNDS - ${gameDto.numberOfRounds} </br>
                <c:forEach var="round" items="${gameDto.rounds}">
                ROUND NUMBER - ${round.roundnumber} </br>
                <c:forEach var="player" items="${round.playingPlayer}">
                TEAM NUMBER - ${player.teamNumber}</br>
                ${player.player1} VS ${player.player2}</br>
                </c:forEach>
                </c:forEach>
            </li>
        </c:forEach>
        </c:forEach>
        
        <h2>GENERATE ROUNDS</h2>
        <form action="/generateRounds" method="post">
    	<label for="name">Tournament Name</label>
        <label for="GAMES">GAMES</label>
        <input type="hidden" id="selectedGameId" name="selectedGameId" />
        <select id="gameSelector" onchange="getGame()">
        	<c:forEach var="gameDto" items="${gameDtos}">
          	<option value="${gameDto.id}">${gameDto.name}</option>
          	</c:forEach>
        </select>
    <button type="submit">Submit</button>
    </form>
    <script type="text/javascript">
    function getGame(){
    var selectedGame = document.getElementById("gameSelector").value;
    document.getElementById("selectedGameId").value = selectedGame;
    }
    </script>
</body>
</html>
