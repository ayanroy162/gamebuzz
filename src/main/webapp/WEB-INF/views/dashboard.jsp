<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GaMeBuzz</title>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<div class="sidebar mt-4">
    </br></br>
     <h2>G</h2>
    <h2>A</h2>
    <h2>M</h2>
    <h2>E</h2>
    <h2>B</h2>
    <h2>U</h2>
    <h2>Z</h2>
    <h2>Z</h2>
</div>
    <div class="container mt-4">
        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-light">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item" style="margin-left: 40px;">
                    <c:choose>
                		<c:when test="${not empty adminAcess || not empty userAcess}">
                        <a class="nav-link" href="#" data-toggle="modal" data-target="#adminLogoutModal">${userName}</a>
                   		</c:when>
                   		<c:otherwise>
                   		<a class="nav-link" href="#" data-toggle="modal" data-target="#adminLoginModal"><strong>Sign Up</strong></a>
                    	</c:otherwise>
                    	</c:choose>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#" data-toggle="modal" data-target="#playerRegistrationModal"> <strong>Join as a Player</strong></a>
                    </li>
                    <li class="nav-item">
                        <c:choose>
                		<c:when test="${not empty adminAcess}">
                        <a class="nav-link" href="#" data-toggle="modal" data-target="#tournamentRegistrationModal"><strong>Tournament Registration</strong></a>
                    	</c:when>
                    	<c:otherwise>
                    	<a class="nav-link" href="#" data-toggle="modal" data-target="#"><strong>Tournament Registration</strong></a>
                    	</c:otherwise>
                    	</c:choose>
                    </li>
                    <li class="nav-item">
                    	<c:choose>
                		<c:when test="${not empty adminAcess}">
                        <a class="nav-link" href="#" data-toggle="modal" data-target="#individualGameRegistrationModal"><strong>Game Registration</strong></a>
                   		</c:when>
                    	<c:otherwise>
                    	<a class="nav-link" href="#" data-toggle="modal" data-target="#"><strong>Game Registration</strong></a>
                    	</c:otherwise>
                    	</c:choose>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#" data-toggle="modal" data-target="#showTournamentDetailsModal"><strong>All Tournaments</strong></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#" data-toggle="modal" data-target="#showPlayersDetailsModal"><strong>Player Showcase</strong></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#" data-toggle="modal" data-target="#showGameDetailsModal"><strong>Games</strong></a>
                    </li>
                     <li class="nav-item dropdown">
           				 <a class="nav-link" href="#" id="navbarDropdown" role="button"
                					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                				<strong>Explore Active Tournaments</strong>
            			</a>
            				<c:if test="${not empty allTournamentDtos}">
                				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    				<c:forEach var="tournamentDto" items="${allTournamentDtos}">
                        				<a class="dropdown-item" href="#" data-toggle="modal" data-target="#tournamentModal_${tournamentDto.tournamentName}">
                            				${tournamentDto.tournamentName}
                        				</a>
                    				</c:forEach>
                				</div>
            				</c:if>
        			</li>
                </ul>
            </div>
        </nav>



				<c:if test="${not empty allTournamentDtos}">
                    <c:forEach var="tournamentDto" items="${allTournamentDtos}">
                        <!-- Modal Code for Each Dropdown Option -->
                        <div class="modal fade" id="tournamentModal_${tournamentDto.tournamentName}" tabindex="-1" role="dialog" aria-labelledby="tournamentModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="tournamentModalLabel">${tournamentDto.tournamentName}</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <!-- Add your modal content here -->
                                        <!-- Example: Display tournament details -->
                                        <!--<p>Tournament ID: ${tournamentDto.id}</p> -->
                                        <!--<p>Start Date: ${tournamentDto.tournamentName}</p> -->
                                        <!-- Add more details as needed -->
                                        <div class="container-body d-inline-flex flex-wrap justify-content-center"
                            title="Click me to trigger an action">
                                <c:forEach var="gameDto" items="${tournamentDto.games}">
                                    <div class="box" data-box-tournament-id="${tournamentDto.id}" data-box-game-id="${gameDto.id}"
                                    data-box-game-roundGenerated="${gameDto.roundGenerated}">${gameDto.name}</div>
                                </c:forEach>
                            </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
            	</c:if>
            
            
            
        <div class="row">
    <div class="col-md-4 text-center">
        <div class="card">
            <div class="card-body" style="border-radius: 0px;">
                <div class="image-container">
                    <img src="/images/caram.jfif" alt="Image 1">
                    <!-- Add more images as needed -->
                </div>
            </div>
        </div>
    </div>
    
    <div class="col-md-4 text-center">
        <div class="card">
            <div class="card-body" style="border-radius: 0px;">
                <div class="image-container">
                    <img src="/images/chess.jfif" alt="Image 2">
                    <!-- Add more images as needed -->
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-4 text-center">
        <div class="card">
            <div class="card-body" style="border-radius: 0px;">
                <div class="image-container">
                    <img src="/images/tennisone.jpg" alt="Image 2">
                    <!-- Add more images as needed -->
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-4 text-center">
        <div class="card">
            <div class="card-body" style="border-radius: 0px;">
                <div class="image-container">
                    <img src="/images/tabletennis.jfif" alt="Image 3">
                    <!-- Add more images as needed -->
                </div>
            </div>
        </div>
    </div>
    
    <div class="col-md-4 text-center">
        <div class="card">
            <div class="card-body" style="border-radius: 0px;">
                <div class="image-container">
                    <img src="/images/ludo.jfif" alt="Image 4">
                    <!-- Add more images as needed -->
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-4 text-center">
        <div class="card">
            <div class="card-body" style="border-radius: 0px;">
                <div class="image-container">
                    <img src="/images/chessone.jpg" alt="Image 4">
                    <!-- Add more images as needed -->
                </div>
            </div>
        </div>
    </div>
 </div>                     
    
        <!-- Individual Game Registration Modal -->
        <div class="modal fade" id="individualGameRegistrationModal" tabindex="-1" role="dialog" aria-labelledby="individualGameRegistrationModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="individualGameRegistrationModalLabel">Game Registration</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form id="gameRegistration" modelAttribute="individualGame">
                    <div class="modal-body">
                        <!-- Add your individual game registration form here -->
                        <!-- For simplicity, I'm just adding a dummy form -->
                            <div class="form-group">
                                <label for="gameName">Game Name:</label>
                                <input type="text" class="form-control" id="individualGame" name="individualGame" required>
                            </div>
                    </div>
                    <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Register</button>
                    </div>
                  </form>
                </div>
            </div>
        </div>
        <!-- Individual Player Registration Modal -->
        
        <div class="modal fade" id="playerRegistrationModal" tabindex="-1" role="dialog" aria-labelledby="playerRegistrationModal" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h5 class="modal-title" id="playerRegistrationModal">Register as a Player</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="closeplayerRegistrationModalBtn">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form:form modelAttribute="playerResistrationDto" id="newPlayerRegistration">
                    <div class="modal-body">
        					<label for="name">Name:</label>
        					<input type="text" class="form-control" id="name" name="name" required>
        					<label for="phoneNumber">Phone Number:</label>
        					<input type="text" class="form-control" id="phoneNumber" name="phoneNumber" required>
    						<label for="tournamentId">Select Tournament:</label>
    						<form:select path="tournamentId" id="tournamentId" class="form-control">
    						<c:if test="${not empty allTournamentDtos}">
    						<form:option value="" label="Select an Tournament"/>
    						<c:forEach var="tournamentDto" items="${allTournamentDtos}">
        					<form:option value="${tournamentDto.id}" label="${tournamentDto.tournamentName}"/>
        					</c:forEach>
        					</c:if>
        						<!-- Add more options as needed -->
    						</form:select>

    						<label for="gameId">Select Game:</label>
    						<form:select path="gameId" id="gameId" class="form-control">
        					<!-- Options for selectedOption2 will be loaded dynamically based on selectedOption1 -->
    						</form:select>
						</div>
						
    						<div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Register</button>
                    </div>
						</form:form>
         			
                </div>
            </div>
        </div>

        <!-- Admin Login Modal -->
        <div class="modal fade" id="adminLoginModal" tabindex="-1" role="dialog" aria-labelledby="adminLoginModal" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="adminLoginModal">USER / ADMIN LOGIN </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                     <form id="userLogin">
                    <div class="modal-body">
                        <!-- Add your individual game registration form here -->
                        <!-- For simplicity, I'm just adding a dummy form -->
                            <div class="form-group">
                                <label for="username">User Name:</label>
                                <input type="text" class="form-control" id="username" name="username" required>
                                <label for="userpassword">Password:</label>
                                <input type="text" class="form-control" id="userpassword" name="userpassword" required>
                            </div>
                        <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Login</button>
                    </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Admin logout Modal -->
        <div class="modal fade" id="adminLogoutModal" tabindex="-1" role="dialog" aria-labelledby="adminLogoutModal" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="adminLogoutModal">${userRole} &nbsp;${userName}</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                        <!-- Add your individual game registration form here -->
                        <!-- For simplicity, I'm just adding a dummy form -->
                        <form id="userLogout">
                            <div class="form-group">
                            <div class="modal-footer">
                    		<button type="submit" class="btn btn-primary">Logout</button>
                    		</div>
                            </div>
                        </form>
                </div>
            </div>
        </div>
        <!-- Tournament Registration Modal -->
        <div class="modal fade" id="tournamentRegistrationModal" tabindex="-1" role="dialog" aria-labelledby="tournamentRegistrationModal" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="tournamentRegistrationModal">Tournament Registration</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form id="tournamentRegistration" modelAttribute="tournament">
                    <div class="modal-body">
                        <!-- Add your individual game registration form here -->
                        <!-- For simplicity, I'm just adding a dummy form -->
                            <div class="form-group">
                                <label for="tournamentName">Tournament Name:</label>
                                <input type="text" class="form-control" id="tournamentName" name="tournamentName" required>
                                <label for="tournamentName">Game Name:</label>
                                <c:forEach var="individualGame" items="${individualGames}">
									<label><input type="checkbox" name="selectedIndividualGames" value="${individualGame.id}" />
                							${individualGame.id}-${individualGame.gameType}<br/>
                					</label>
    							</c:forEach>
                            </div>
                            </div>
                            <div class="modal-footer">
                    		<button type="submit" class="btn btn-primary">Register</button>
                    		</div>
                        </form>
                </div>
            </div>
        </div>
        <!-- Show Tournament Details Modal -->
        <div class="modal fade" id="showTournamentDetailsModal" tabindex="-1" role="dialog" aria-labelledby="showTournamentDetailsModal" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="showTournamentDetailsModal">Tournament Details Modal</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <!-- Add your individual game registration form here -->
                        <!-- For simplicity, I'm just adding a dummy form -->
                        <c:forEach var="tournamentDto" items="${allTournamentDtos}">
								TournamentName - ${tournamentDto.tournamentName}
								Listed games -
						<c:forEach var="gameDto" items="${tournamentDto.games}">
            				<li>
                				 <button type="submit" class="btn btn-primary game-btn">${gameDto.name}</button>
            				</li>
        				</c:forEach>
        				</c:forEach>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Show Players Details Modal -->
        <!-- Show Game Details Modal -->
        <div class="modal fade" id="showGameDetailsModal" tabindex="-1" role="dialog" aria-labelledby="showGameDetailsModal" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="individualGameRegistrationModalLabel">Game Details Modal</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <!-- Add your individual game registration form here -->
                        <!-- For simplicity, I'm just adding a dummy form -->
                        <c:forEach var="value" items="${individualGames}">
                				${value.id}-${value.gameType}<br/>
    					</c:forEach>
                    </div>
                </div>
            </div>
        </div>

</div>

    <!-- Modals (Placeholders) -->
    <!-- Admin Login Modal -->
    <!-- Tournament Registration Modal -->
    <!-- Show Tournament Details Modal -->
    <!-- Show Players Details Modal -->
    <!-- Show Game Details Modal -->
    
    <div class="modal fade" id="msgModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <!-- Modal content goes here -->
                <p id="modalContent"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" id="closeModalBtn">Close</button>
            </div>
        </div>
    </div>
</div>

    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    function getTournament(){
    var selectedTournament = document.getElementById("tournamentSelector").value;
    document.getElementById("selectedTournamentId").value = selectedTournament;
    document.getElementById("playerResGameData").submit();
    }
    function getGame(){
    var selectedGame = document.getElementById("gameSelector").value;
    document.getElementById("selectedGameId").value = selectedGame;
    }
    function openModal(tournamentName) {
            $('#tournamentModal_' + tournamentName).modal('show');
        }
    </script>
    <script>
    
    $('.box').click(function () {
        // Get the data-box-id attribute value to identify the clicked box
        var gameId = $(this).data('box-game-id');
        var tournamentId = $(this).data('box-tournament-id');
        var isroundgenerated = $(this).data('data-box-game-roundGenerated');
        var apiUrl = '/tournamentIndividualGame?gameId=' + gameId + '&tournamentId=' + tournamentId + '&roundNumber=' + 1;
		window.location.href = apiUrl;
    });
    
    // jQuery
    $(document).ready(function() {
    
       // var hiddenInputValue = $('#tournamentId').val();

       // if (hiddenInputValue) {
       //     $('#playerRegistrationModal').modal('show');
       //     console.log('Hidden input has a value:', hiddenInputValue);
      //  } 
        
         $("#closeModalBtn").click(function () {
            $('#msgModel').modal('hide');
            var newLocation = "http://localhost:8080/dashboard";
            window.location.href = newLocation;
             setTimeout(function () {
                // Force reload with the new location
                location.reload(true); // The 'true' argument forces a reload from the server, bypassing the cache
            }, 100);
        });
        $("#closeplayerRegistrationModalBtn").click(function () {
            $('#playerRegistrationModal').modal('hide');
            var newLocation = "http://localhost:8080/dashboard";
            window.location.href = newLocation;
             setTimeout(function () {
                // Force reload with the new location
                location.reload(true); // The 'true' argument forces a reload from the server, bypassing the cache
            }, 100);
        });
        
        $("#gameRegistration").submit(function (event) {
            // Prevent the default form submission
            event.preventDefault();
            var formData = $(this).serialize();
            $.ajax({
                type: "POST",
                url: "/register-individualGame-dashboard",
                data: formData,
                contentType: "application/x-www-form-urlencoded",
                dataType: "text",
                success: function (data) {
                     $("#modalContent").text(data);
					$('#individualGameRegistrationModal').modal('hide');
                    // Open the modal
                    $('#msgModel').modal('show');
                },
                error: function (error) {
                    console.error("Error:", error);
                }
            });
        });
         $("#userLogin").submit(function (event) {
            // Prevent the default form submission
            event.preventDefault();
            var formData = $(this).serialize();
            $.ajax({
                type: "POST",
                url: "/login",
                data: formData,
                contentType: "application/x-www-form-urlencoded",
                dataType: "text",
                success: function (data) {
                     $("#modalContent").text(data);
					$('#adminLoginModal').modal('hide');
                    // Open the modal
                    $('#msgModel').modal('show');
                },
                error: function (error) {
                    console.error("Error:", error);
                }
            });
        });
        
            $("#tournamentRegistration").submit(function (event) {
            // Prevent the default form submission
            event.preventDefault();
            var formData = $(this).serialize();
            $.ajax({
                type: "POST",
                url: "/register-tournament",
                data: formData,
                contentType: "application/x-www-form-urlencoded",
                dataType: "text",
                success: function (data) {
                     $("#modalContent").text(data);
					$('#tournamentRegistrationModal').modal('hide');
                    // Open the modal
                    $('#msgModel').modal('show');
                },
                error: function (error) {
                    console.error("Error:", error);
                }
            });
        });
        
        $("#newPlayerRegistration").submit(function (event) {
            // Prevent the default form submission
            event.preventDefault();
            var formData = $(this).serialize();
            $.ajax({
                type: "POST",
                url: "/newPlayer-dashboard",
                data: formData,
                contentType: "application/x-www-form-urlencoded",
                dataType: "text",
                success: function (data) {
                     $("#modalContent").text(data);
					$('#playerRegistrationModal').modal('hide');
                    // Open the modal
                    $('#msgModel').modal('show');
                },
                error: function (error) {
                    console.error("Error:", error);
                }
            });
        });
        
        $("#userLogout").submit(function (event) {
            // Prevent the default form submission
            event.preventDefault();
            logout();
            });
        
    });
    
</script>

<script>
        $(document).ready(function() {
            $('#tournamentId').change(function() {
            	if($('#tournamentId').val()){
                updateOptions();
                }else {
                var dropdown2 = $('#gameId');
                dropdown2.empty();  // Clear existing options
                }
            });

            function updateOptions() {
                var tournamentId = $('#tournamentId').val();
                
                // Perform an AJAX call to fetch options for selectedOption2 based on selectedOption1
                // For simplicity, let's assume you have a controller method that returns JSON with options
                var url = '/getOptionsForTournament?tournamentId=' + tournamentId;

                $.getJSON(url, function(data) {
                    // Update options in the second dropdown dynamically
                    var dropdown2 = $('#gameId');
                    dropdown2.empty();  // Clear existing options
                    
                    // Add new options based on the data received
                    $.each(data, function(index, option) {
                        dropdown2.append($('<option>').val(option.id).text(option.name));
                    });
                })
                .fail(function(error) {
                    console.error('Error fetching options:', error);
                });
            }
        });
    </script>
    
    <script>
    var inactivityTimeout;

    function resetInactivityTimeout() {
        clearTimeout(inactivityTimeout);
        inactivityTimeout = setTimeout(logout, 50000); // Auto logout after 20 seconds of inactivity
    }

    function logout() {
        // Perform logout (send an Ajax request to your logout endpoint)
        $.ajax({
                type: "POST",
                url: "/logout",
                contentType: "application/x-www-form-urlencoded",
                dataType: "text",
                success: function (data) {
                     $("#modalContent").text(data);
                    	$('#msgModel').modal('show');
                },
                error: function (error) {
                    console.error("Error:", error);
                }
            });
    }

    $(document).on("mousemove keypress", function () {
        resetInactivityTimeout();
    });

    resetInactivityTimeout(); // Initial setup
</script>
</body>
</html>
