<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Game Details</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

     <div class="container mt-4">
     <h2 class="text-center" style="letter-spacing: 10px;">${game.name}</h2>
        <div class="card-deck">
            <div class="card">
                <div class="card-body text-center card-details">
                    <p class="card-title ch-space">Total Players</p>
                    <p class="card-text text-size"><strong>${game.numberOfPlayers}</strong></p>
                </div>
            </div>
            <div class="card">
                <div class="card-body text-center card-details">
                    <p class="card-title ch-space">Active State</p>
                    <p class="card-text text-size"><strong>TRUE</strong></p>
                </div>
            </div>
            <div class="card">
                <div class="card-body text-center card-details">
                    <p class="card-title ch-space">Total Rounds</p>
                    <p class="card-text text-size"><strong>${game.numberOfRounds}</strong></p>
                </div>
            </div>
        </div>
        <div class="round-details">
            <div class="text-center mt-4">
            <span class="arrow" id="prevRound" data-roundid="${param.roundNumber}">❮</span>
    		<span class="arrow" id="nextRound" data-roundid="${param.roundNumber}" data-roundmaxnumber="${game.numberOfRounds}">❯</span>
        	</div>
            <table class="round-table mx-auto">
                <thead>
                    <tr>
                        <th>TEAM 1</th>
                        <th>TEAM 2</th>
                        <th>WIN Team</th>
                    </tr>
                </thead>
                <tbody>
                <c:choose>
                <c:when test="${not empty game.rounds}">
                <c:forEach var="round" items="${game.rounds}">
                <c:set var="playingplayers" value="${round.playingPlayer}" />
                <c:if test="${not empty playingplayers}">
                <c:forEach var="player" items="${playingplayers}">
                <c:set var="ajaxAllowed" value="false" />
                    <c:if test="${not empty player.player1 && not empty player.player2 && empty player.winPlayer}">
                        <c:set var="ajaxAllowed" value="true" />
                    </c:if>
                		<tr>
                            <td>
                            <div class="win" data-win-game-id="${game.id}" data-win-tournament-id="${tournamentId}"
                            data-win-player-id="${player.player1}" data-win-player-itemid="${player.id}" data-win-player-ajaxallowed="${ajaxAllowed}"
                            data-win-player-playerone="${player.player1}" data-win-player-playertwo="${player.player2}">
                            ${player.player1} </div></td>
                            <c:choose>
                            <c:when test="${player.player2 eq 'DUMMY_PLAYER'}">
                            <c:set var="playerTwo" value="${player.player2}" />
                            <c:if test="${playerTwo eq 'DUMMY_PLAYER'}">
                            <c:set var="playerTwo" value="Not Yet Decided ..." />
                            </c:if>
                            <td>
                            <div class="dmmyplayer" data-dmmyplayer-game-id="${game.id}" data-dmmyplayer-tournament-id="${tournamentId}"
                            data-dmmyplayer-player-itemid="${player.id}">
                            ${playerTwo}</div></td>
                            </c:when>
                            <c:otherwise>
                            <td>
                            <div class="win" data-win-game-id="${game.id}" data-win-tournament-id="${tournamentId}"
                            data-win-player-id="${player.player2}" data-win-player-itemid="${player.id}" data-win-player-ajaxallowed="${ajaxAllowed}"
                            data-win-player-playerone="${player.player1}" data-win-player-playertwo="${player.player2}">
                            ${player.player2}</div></td>
                            </c:otherwise>
                            </c:choose>
                            <c:if test="${not empty player.winPlayer}">
                                <td>${player.winPlayer}</td>
                            </c:if>
                            <c:if test="${empty player.winPlayer}">
                                <td>
                                    Not Yet Decided ...
                                </td>
                            </c:if>
                        </tr>
                 </c:forEach>
                 </c:if>
                 </c:forEach>
                </c:when>
                <c:otherwise>
                <tr>
                <td colspan="3">
                <c:choose>
                <c:when test="${not empty adminAcess || not empty userAcess}">
                <div class="box" 
                data-box-game-id="${game.id}" data-box-tournament-id="${tournamentId}" >Generate Rounds</div>
                </c:when>
                <c:otherwise>
                Generate Generated
                </c:otherwise>
                </c:choose>
                </td>
                </tr>
                </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>

        <div class="text-center mt-4">
    <span class="arrow" id="prevRound" data-roundid="${param.roundNumber}">❮</span>
    <span class="arrow" id="nextRound" data-roundid="${param.roundNumber}">❯</span>
    <input type="hidden" id="gameId" name="gameId" value="${param.gameId}"/>
    <input type="hidden" id="tournamentId" name="tournamentId" value="${param.tournamentId}"/>
    <input type="number" id="roundId" name="roundId" style="display: none" value="${param.roundNumber}"/>
</div>
        
        <div class="modal fade" id="msgModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    	<div class="modal-dialog" role="document">
        		<div class="modal-content">
                        <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    	</div>
            			<div class="modal-body">
                			<!-- Modal content goes here -->
                			<p id="modalContent"></p>
                			<form id="subModelData">
                            <div class="form-group">
                                <label for="player1score"><p id="modalplayer1"></p></label>
                                <input type="text" class="form-control" id="player1score" name="player1score" required>
                                <label for="player2score"><p id="modalplayer2"></p></label>
                                <input type="text" class="form-control" id="player2score" name="player2score" required>
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </div>
               				</form>
            			</div>
        		</div>
    	</div>
    	</div>
    
    <div class="modal fade" id="msgModelNotAllowed" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    	<div class="modal-dialog" role="document">
        	<div class="modal-content">
                        <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    	</div>
            			<div class="modal-body">
                			<!-- Modal content goes here -->
                			<p id="modalContentNotAllowed"></p>
            			</div>
        	</div>
    	</div>
    	</div>
    
    <div class="modal fade" id="dummyplayermodel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    	<div class="modal-dialog" role="document">
        			<div class="modal-content">
                        <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                   		 </div>
            			<div class="modal-body">
                <!-- Modal content goes here -->
                			<form id="dummyplayer">
                				<label for="looseplayer">Select Payer:</label>
    							<select id="looseplayer" name="looseplayer">
        					<!-- Options for selectedOption2 will be loaded dynamically based on selectedOption1 -->
    							</select>

    							<input type="submit" value="Submit" class="btn btn-primary"/>
							</form>
            			</div>
        			</div>
    	</div>
    
    </div>



    <!-- Bootstrap JS and Popper.js -->
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
    <!-- Add your JavaScript scripts here -->
    <script>
    $('.box').click(function () {
        // Get the data-box-id attribute value to identify the clicked box
        var gameId = $(this).data('box-game-id');
        var tournamentId = $(this).data('box-tournament-id');
        var roundId = $("#roundId").val();
         var apiUrl = '/tournamentIndividualGame?gameId=' + gameId + '&tournamentId=' + tournamentId + '&roundNumber=' + roundId;
		//window.location.href = apiUrl;
		$.ajax({
                type: "POST",
                url: '/generateRounds?gameId=' + gameId + '&tournamentId=' + tournamentId,
                contentType: "application/x-www-form-urlencoded",
                dataType: "text",
                success: function (data) {
                window.location.href = apiUrl;
                },
                error: function (error) {
                    console.error("Error:", error);
                }
            });
    });
        
        $(document).ready(function() {
        // Click event for navigating to the previous round
        var roundId = $("#roundId").val();
        if (roundId === null || roundId.trim() === '') {
        	$("#roundId").attr("roundId",1);
            console.log("Input is null or empty");
        }
        $("#prevRound").on("click", function() {
        	var gameId = $("#gameId").val();
        	var tournamentId = $("#tournamentId").val();
        	var roundId = parseInt($("#roundId").val())-1;
        	 if (roundId >= 0) {
        	var geturl = '/tournamentIndividualGame?gameId=' + gameId + '&tournamentId=' + tournamentId + '&roundNumber=' + roundId;
            window.location.href = geturl;
            }
        });

        // Click event for navigating to the next round
        $("#nextRound").on("click", function() {
            var maxround = $(this).data("roundmaxnumber");
        	var gameId = $("#gameId").val();
        	var gameId = $("#gameId").val();
        	var tournamentId = $("#tournamentId").val();
        	var roundId = parseInt($("#roundId").val())+1;
        	var geturl = '/tournamentIndividualGame?gameId=' + gameId + '&tournamentId=' + tournamentId + '&roundNumber=' + roundId;
            window.location.href = geturl;
        });
        
        $('.win').click(function () {
        // Get the data-box-id attribute value to identify the clicked box
        var targetElement = $("#subModelData");
		var clickedElement = $(this);

    // Get the data attributes for the clicked element
    var gameId = clickedElement.data("win-game-id");
    var tournamentId = clickedElement.data("win-tournament-id");
    var winplayer = clickedElement.data("win-player-id");
    var playerId = clickedElement.data("win-player-itemid");
    var player1 = clickedElement.data("win-player-playerone");
    var player2 = clickedElement.data("win-player-playertwo");
    var ajaxvalid = clickedElement.data("win-player-ajaxallowed");

	console.log("Data Object:", clickedElement.data());

        var roundId = $("#roundId").val();
        if(ajaxvalid || ajaxvalid==='true'){
        var textData = 'Player Id' + winplayer +' is set as wining player';
        var player1score = player1 + 'SCORE';
        var player2score = player2 + 'SCORE';
        $("#modalContent").text(textData);
        $("#modalplayer1").text(player1score);
        $("#modalplayer2").text(player2score);
         var posturl = '/winplayer';
         var geturl = '/tournamentIndividualGame?gameId=' + gameId + '&tournamentId=' + tournamentId + '&roundNumber=' + roundId;
         targetElement.attr("data-posturl", posturl);
         targetElement.attr("data-geturl", geturl);
         targetElement.attr("data-playerid", playerId);
         targetElement.attr("data-winplayer", winplayer);
         
         $('#msgModel').modal('show');
         
        }else{
        $("#modalContentNotAllowed").text("Operation is not allowed");
        $('#msgModelNotAllowed').modal('show');
        }
		
    });
    $("#subModelData").submit(function () {
    		event.preventDefault();
    		var modalElement = $("#msgModel");
    		var clickedElement = $(this);
    		var posturl = clickedElement.data("posturl");
            var geturl = clickedElement.data("geturl");
            var playerId = clickedElement.data("playerid");
            var winplayer = clickedElement.data("winplayer");
            var formData = $(this).serialize();
            formData += '&playerId=' + playerId + '&winplayer=' + winplayer;
            $('#msgModel').modal('hide');
            $.ajax({
                type: "POST",
                url: posturl,
                data: formData,
                contentType: "application/x-www-form-urlencoded",
                dataType: "text",
                success: function (data) {
                     window.location.href = geturl;
                },
                error: function (error) {
                    console.error("Error:", error);
                }
            });
        });
        
        $('.dmmyplayer').click(function () {
        // Get the data-box-id attribute value to identify the clicked box
        var targetElement = $("#dummyplayer");
		var clickedElement = $(this);
    	var gameId = clickedElement.data("dmmyplayer-game-id");
    	var tournamentId = clickedElement.data("dmmyplayer-tournament-id");
    	var playerId = clickedElement.data("dmmyplayer-player-itemid");
		console.log("Data Object:", clickedElement.data());
            var posturl = '/playersForDummyPlayer?gameId=' + gameId + '&tournamentId=' + tournamentId + '&roundNumber=' + roundId;
         	var geturl = '/tournamentIndividualGame?gameId=' + gameId + '&tournamentId=' + tournamentId + '&roundNumber=' + roundId;

                $.getJSON(posturl, function(data) {
                $('#dummyplayermodel').modal('show');
                    // Update options in the second dropdown dynamically
                    var dropdown2 = $('#looseplayer');
                    dropdown2.empty();  // Clear existing options
                    
                    // Add new options based on the data received
                    $.each(data, function(index, option) {
                        dropdown2.append($('<option>').val(option.name).text(option.name));
                    });
                    
                    targetElement.attr("data-posturl", posturl);
         			targetElement.attr("data-geturl", geturl);
         			targetElement.attr("data-playerid", playerId);
                })
                .fail(function(error) {
                    console.error('Error fetching options:', error);
                });
		
    });
    
    $("#dummyplayer").submit(function () {
    		event.preventDefault();
    		var clickedElement = $(this);
    		var posturl = clickedElement.data("posturl");
            var geturl = clickedElement.data("geturl");
            var playerId = clickedElement.data("playerid");
            var playerAsDummy = $(this).data("looseplayer");
            var formData = $(this).serialize();
            formData += '&playerId=' + playerId ;
            $('#dummyplayermodel').modal('hide');
            $.ajax({
                type: "POST",
                url: "/setdummyplayer",
                data: formData,
                contentType: "application/x-www-form-urlencoded",
                dataType: "text",
                success: function (data) {
                     window.location.href = geturl;
                },
                error: function (error) {
                    console.error("Error:", error);
                }
            });
        });
    
        
    });

    </script>

</body>
</html>