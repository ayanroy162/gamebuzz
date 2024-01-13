<!-- src/main/webapp/WEB-INF/views/registration.jsp -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>PLAYER RESISTRATION FORM</title>
    
</head>
<body>
    <c:if test="${not empty gamesDtos}">
    <form action="/addNewPlayer" method="post" modelAttribute="playerResistrationDto">
    	<label for="name">Player Name:</label>
        <input type="text" id="name" name="name" required />
        <label for="phoneNumber">Phone Number:</label>
        <input type="hidden" id="selectedGameId" name="selectedGameId" />
        <input type="hidden" id="tournamentId" name="tournamentId" value="${tournamentId}"/>
        <input type="text" id="phoneNumber" name="phoneNumber" required />
        <select id="gameSelector" onchange="getGame()">
        	<c:forEach var="games" items="${gamesDtos}">
          	<option value="${games.id}">${games.name}</option>
          	</c:forEach>
        </select>
    <button type="submit">Submit</button>
    </form>
    </c:if>
    
    <c:if test="${not empty tournamentDtos}">
    <form action="/playerRegistrationData" method="post">
    	<input type="hidden" id="selectedTournamentId" name="selectedTournamentId"/>
        <select id="tournamentSelector" onchange="getTournament()">
        <c:forEach var="tournamentDto" items="${tournamentDtos}">
        <option value="${tournamentDto.id}">${tournamentDto.tournamentName}</option>
        </c:forEach>
        </select>
        <button type="submit">Submit</button>
     </form>
     </c:if>
        
    <script type="text/javascript">
    function getTournament(){
    var selectedTournament = document.getElementById("tournamentSelector").value;
    document.getElementById("selectedTournamentId").value = selectedTournament;
    }
    function getGame(){
    var selectedGame = document.getElementById("gameSelector").value;
    document.getElementById("selectedGameId").value = selectedGame;
    }
    </script>
</body>
</html>


