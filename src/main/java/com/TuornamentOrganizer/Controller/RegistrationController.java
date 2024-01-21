package com.TuornamentOrganizer.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.TuornamentOrganizer.Dto.GameDto;
import com.TuornamentOrganizer.Dto.GamePlayerDto;
import com.TuornamentOrganizer.Dto.LossingPlayerDto;
import com.TuornamentOrganizer.Dto.PlayerResistrationDto;
import com.TuornamentOrganizer.Dto.SwappingPlayerInformationDto;
import com.TuornamentOrganizer.Dto.TournamentDto;
import com.TuornamentOrganizer.Model.Game;
import com.TuornamentOrganizer.Model.IndividualGame;
import com.TuornamentOrganizer.Model.Tournament;
import com.TuornamentOrganizer.Model.UserTable;
import com.TuornamentOrganizer.Repository.UserRepo;
import com.TuornamentOrganizer.Service.IndividualGameService;
import com.TuornamentOrganizer.Service.PlayerService;
import com.TuornamentOrganizer.Service.RoundService;
import com.TuornamentOrganizer.Service.TournamentGameService;
import com.TuornamentOrganizer.Service.TournamentService;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;

@Controller
public class RegistrationController {

	@Autowired
	private TournamentService tournamentService;
	@Autowired
	private IndividualGameService individualGameService;
	@Autowired
	private PlayerService playerService;
	@Autowired
	private RoundService roundService;
	@Autowired
	private TournamentGameService tournamentGameService;
	@Autowired
	private UserRepo userRepo;

	@GetMapping("/dashboard")
	public String dashBoard(Model model,HttpSession session) {
		List<IndividualGame> individualGames = individualGameService.getAllIndividualGame();
		List<TournamentDto> allTournamentDtos = tournamentService.getAllTournamentDtos();
		model.addAttribute("individualGames", individualGames);
		model.addAttribute("allTournamentDtos", allTournamentDtos);
		model.addAttribute("tournament", new TournamentDto());
		model.addAttribute("playerResistrationDto", new PlayerResistrationDto());
		model.addAttribute("tournamentId", null);
		String userRole = (String) session.getAttribute("userRole");
		Long userId = (Long) session.getAttribute("userId");
		if(StringUtils.isNotEmpty(userRole)) {
			if(userRole.equalsIgnoreCase("admin")) {
				model.addAttribute("adminAcess", "adminAcess");
			}
			if(userRole.equalsIgnoreCase("user")) {
				model.addAttribute("userAcess", "userAcess");
			}
			model.addAttribute("userName", String.valueOf(userRepo.findById(userId).get().getName()));
			model.addAttribute("userRole", String.valueOf(userRepo.findById(userId).get().getRole()));
		}
		return "/dashboard";
	}
	
	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<String> userLogIn(@RequestParam(value = "username", required = true) String userName,
			@RequestParam(value = "userpassword", required = true) String userPassword, HttpSession session) {
		StringBuilder sv = new StringBuilder();
		UserTable user = userRepo.getUserByNameAndPassword(userName, userPassword);
		if (null != user && user.getPassword().equalsIgnoreCase(userPassword)) {
			session.setAttribute("userRole", user.getRole());
			session.setAttribute("userId", user.getId());
			sv.append("Welcome").append(" ").append(user.getName());

		} else {
			sv.append("Sorry, You don't have account, please Create an Account");
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		return new ResponseEntity<>(sv.toString(), headers, HttpStatus.OK);
	}
	
	@PostMapping("/logout")
	@ResponseBody
	public ResponseEntity<String> userLogOut(HttpSession session) {
		String userRole = (String) session.getAttribute("userRole");
		HttpStatus status = HttpStatus.OK;
		if(StringUtils.isNotEmpty(userRole)) {
		session.invalidate();
		}else {
			status = HttpStatus.BAD_REQUEST;
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		return new ResponseEntity<>(new StringBuilder("Logout Successfully !!!").toString(), headers, status);
	}

	@PostMapping("/register-individualGame-dashboard")
	@ResponseBody
	public ResponseEntity<String> registerIndividualGameDashBoardAjax(
			@ModelAttribute("individualGame") String individualGame) {
		StringBuilder sb = new StringBuilder();
		try {
		individualGameService.addNewIndividualGame(individualGame);
		sb.append("Successfully added");
		
		} catch(DataIntegrityViolationException e) {
			sb.append("Game Is Already Resistered");
		}
		sb.append(individualGame);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		return new ResponseEntity<>(sb.toString(), headers, HttpStatus.OK);
	}

	@PostMapping("/register-tournament")
	@ResponseBody
	public ResponseEntity<String> registerTournamentDashBoard(@ModelAttribute("tournament") TournamentDto tournament,
			@RequestParam(value = "selectedIndividualGames", required = false) String[] selectedIndividualGames) {
		StringBuilder sb = new StringBuilder();
		try {
			tournamentService.registerNewTournament(tournament, selectedIndividualGames);
			sb.append("Successfully added");
			sb.append(tournament.getTournamentName());
			sb.append(". Dive in and enjoy the latest addition to our gaming experience!");

		} catch (DataIntegrityViolationException e) {
			sb.append(tournament.getTournamentName()).append(" Is Already Resistered");
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		return new ResponseEntity<>(sb.toString(), headers, HttpStatus.OK);
	}

	
	@PostMapping("/generateRounds")
	@ResponseBody
	public ResponseEntity<String> generateRoundsForGame(@RequestParam(value = "gameId", required = true) String selectedGameId) {
		roundService.generateRounds(selectedGameId);
		StringBuilder sb = new StringBuilder("Successfully generated ");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		return new ResponseEntity<>(sb.toString(), headers, HttpStatus.OK);
	}
	
	@PostMapping("/winplayer")
	@ResponseBody
	public ResponseEntity<String> addOrModifyWinPlayer(@RequestParam(value = "playerId", required = true) String playerId,
			@RequestParam(value = "winplayer", required = true) String winplayer,
			@RequestParam(value = "player2score", required = true) String player2score,
			@RequestParam(value = "player1score", required = true) String player1score) {
		roundService.setWinningPlayer(playerId,winplayer,player1score,player2score);
		StringBuilder sb = new StringBuilder("winplayer added Successfully");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		return new ResponseEntity<>(sb.toString(), headers, HttpStatus.OK);
	}
	
	
	@GetMapping("/getOptionsForTournament")
	@ResponseBody
	public ResponseEntity<List<GamePlayerDto>> getGamesForTournament(@RequestParam(value = "tournamentId", required = true) String tournamentId) {
		Optional<Tournament> tpour = tournamentService.getIndividualTournamet(tournamentId);
		List<GamePlayerDto> dto = new ArrayList<>();
		tpour.get().getGames().forEach(i -> {
			GamePlayerDto gamedt = new GamePlayerDto();
			gamedt.setId(String.valueOf(i.getId()));
			gamedt.setName(i.getName());
			dto.add(gamedt);
		});
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PostMapping("/newPlayer-dashboard")
	@ResponseBody
	public ResponseEntity<String> newPlayerInTournamentDashboard(
			@ModelAttribute("playerResistrationDto") PlayerResistrationDto playerResistrationDto) {
		playerService.addNewPlayer(playerResistrationDto, playerResistrationDto.getGameId());
		StringBuilder sb = new StringBuilder("Successfully added ");
		sb.append(playerResistrationDto.getName());
		sb.append(" ").append("to").append(playerResistrationDto.getGameId());
		sb.append(". Dive in and enjoy the latest addition to our gaming experience!");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		return new ResponseEntity<>(sb.toString(), headers, HttpStatus.OK);
	}
	
	@GetMapping("/roundsDetails")
	public String showAllGamesWithRound(Model model) {
		List<Tournament> tournaments = tournamentService.getAllTournaments();
		tournaments.stream().forEach(i -> roundService.determindTotalNumberOfRounds(i));
		List<TournamentDto> tournamentDtos = tournamentService.getAllTournamentDtos();
		model.addAttribute("tournamentDtos", tournamentDtos);
		model.addAttribute("gameDtos", tournamentDtos.get(1).getGames());
		return "/roundsDetails";
	}

	@PostMapping("/generateRounds-dash")
	public String generateRounds(@RequestParam(value = "selectedGameId", required = true) String selectedGameId) {
		roundService.generateRounds(selectedGameId);
		return "redirect:/roundsDetails";
	}
	
	@GetMapping("/tournamentIndividualGame")
	public String getTournamentIndividualGameDetails(Model model,@RequestParam(value = "tournamentId", required = true) String tournamentId,
			@RequestParam(value = "gameId", required = true) String gameId,
			@RequestParam(value = "roundNumber", required = true) String roundNumber,HttpSession session) {
		GameDto gameDto = tournamentGameService.getGameDetails(tournamentId,gameId,roundNumber);
		model.addAttribute("game",gameDto);
		model.addAttribute("tournamentId",tournamentId);
		String userRole = (String) session.getAttribute("userRole");
		if(StringUtils.isNotEmpty(userRole)) {
			if(userRole.equalsIgnoreCase("admin")) {
				model.addAttribute("adminAcess", "adminAcess");
			}
			if(userRole.equalsIgnoreCase("user")) {
				model.addAttribute("userAcess", "userAcess");
			}
		}
		return "/tournamentIndividualGame";
	}
	
	@GetMapping("/playersForDummyPlayer")
	@ResponseBody
	public ResponseEntity<List<LossingPlayerDto>> getPlayersForDummyPlayer(
			@RequestParam(value = "tournamentId", required = true) String tournamentId,
			@RequestParam(value = "gameId", required = true) String gameId,
			@RequestParam(value = "roundNumber", required = true) String roundNumber) {
		GameDto gameDto = tournamentGameService.getGameDetails(tournamentId, gameId, roundNumber);
		List<LossingPlayerDto> lossPlayerDto = roundService.getLoosingDtos(gameDto);
		return new ResponseEntity<>(lossPlayerDto, HttpStatus.OK);
	}
	
	@PostMapping("/setdummyplayer")
	@ResponseBody
	public ResponseEntity<String> SetDummyPlayer(@RequestParam(value = "playerId", required = true) String playerId,
			@RequestParam(value = "looseplayer", required = true) String playerAsDummy) {
		roundService.setDummyPlayer(playerId,playerAsDummy);
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}
	
	@GetMapping("/swap-player")
	@ResponseBody
	public ResponseEntity<List<SwappingPlayerInformationDto>> getPlayersforSwaping(
			@RequestParam(value = "gameId", required = true) 
			String gameId,@RequestParam(value = "roundId", required = true) 
			String roundId) {
		List<SwappingPlayerInformationDto> playersList = roundService.getPlayersForSwaping(gameId,roundId);
		return new ResponseEntity<>(playersList, HttpStatus.OK);
	}
	
	@PostMapping("/setSwapPlayer")
	@ResponseBody
	public ResponseEntity<String> setSwapPlayers(
			@RequestParam(value = "swapPlayerOne", required = true) 
			String swapPlayerOne,@RequestParam(value = "swapPlayerTwo", required = true) 
			String swapPlayerTwo) {
		if(!swapPlayerOne.equalsIgnoreCase(swapPlayerTwo)) {
		roundService.setPlayersForSwaping(swapPlayerOne,swapPlayerTwo);
		}
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}
}