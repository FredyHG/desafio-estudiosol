package dev.fredyhg.desafiostudiosol.service.impl;

import dev.fredyhg.desafiostudiosol.exception.InvalidScoreFormatException;
import dev.fredyhg.desafiostudiosol.exception.SameCombinationsException;
import dev.fredyhg.desafiostudiosol.properties.ScoreProperties;
import dev.fredyhg.desafiostudiosol.controller.request.VerifyRequest;
import dev.fredyhg.desafiostudiosol.controller.response.VerifyResponse;
import dev.fredyhg.desafiostudiosol.service.ScoreCombinationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScoreCombinationServiceImpl implements ScoreCombinationService {

    private final ScoreProperties scoreProperties;

    public VerifyResponse verifyCombinations(VerifyRequest verifyRequest) {
        log.info("Init analyse at: {}", LocalDateTime.now());

        HashMap<String, Integer> teamsScore = getTeamsScore(verifyRequest.score());

        Integer teamOneScore = teamsScore.get("teamOne");
        Integer teamTwoScore = teamsScore.get("teamTwo");

        int teamOneCombinations = findCombinations(teamOneScore).size();
        int teamTwoCombinations = findCombinations(teamTwoScore).size();

        log.info("Ending analyse at: {}", LocalDateTime.now());

        if(teamOneCombinations == 0 || teamTwoCombinations == 0) {
            return new VerifyResponse(0);
        }

        if(teamOneCombinations == teamTwoCombinations) {
            throw new SameCombinationsException("The both teams has a same score combination");
        }

        if(teamOneCombinations > teamTwoCombinations) {
            log.info("First team is greater than second team");
            return new VerifyResponse(teamOneCombinations);
        }

        log.info("Second team is greater than first team");
        return new VerifyResponse(teamTwoCombinations);
    }



    public Set<List<String>> findCombinations(int targetScore) {
        Set<List<String>> result = new HashSet<>();
        List<String> currentCombination = new ArrayList<>();

        findCombinationsHelper(targetScore, 0, currentCombination, result, false);

        return result;
    }

    private HashMap<String, Integer> getTeamsScore(String score) {

        HashMap<String, Integer> scores = new HashMap<>();

        try {
            String[] stringScores = score.split("x");

            scores.put("teamOne", Integer.parseInt(stringScores[0]));
            scores.put("teamTwo", Integer.parseInt(stringScores[1]));
        } catch (Exception e) {
            throw new InvalidScoreFormatException("Invalid format of score: '" + score + "'. Expected format: 'teamOneScorexteamTwoScore' (e.g., '3x15').");
        }

        return scores;
    }

    private void findCombinationsHelper(int targetScore,
                                        int currentScore,
                                        List<String> currentCombination,
                                        Set<List<String>> result,
                                        boolean lastWasTouchdown) {

        if (currentScore == targetScore) {
            // Ordena a combinação antes de adicionar ao conjunto para evitar duplicatas
            List<String> sortedCombination = new ArrayList<>(currentCombination);

            sortedCombination.sort(String::compareTo);
            result.add(sortedCombination);

            return;
        }

        if (currentScore > targetScore) {
            return;
        }

        // Tenta adicionar um touchdown
        currentCombination.add("Touchdown");

        findCombinationsHelper(targetScore, currentScore + scoreProperties.getTouchDown(), currentCombination, result, true);
        currentCombination.remove(currentCombination.size() - 1);

        // Tenta adicionar um ponto extra (se o último foi um touchdown)
        if (lastWasTouchdown) {

            currentCombination.add("Extra Point");

            findCombinationsHelper(targetScore, currentScore + scoreProperties.getExtraTouchDown(), currentCombination, result, false);
            currentCombination.remove(currentCombination.size() - 1);

            // Tenta adicionar uma conversão de dois pontos (se o último foi um touchdown)
            currentCombination.add("Extra Two Points");

            findCombinationsHelper(targetScore, currentScore + scoreProperties.getExtraTouchDownMax(), currentCombination, result, false);
            currentCombination.remove(currentCombination.size() - 1);
        }

        // Tenta adicionar um field goal
        currentCombination.add("Field Goal");

        findCombinationsHelper(targetScore, currentScore + scoreProperties.getFieldGoal(), currentCombination, result, false);
        currentCombination.remove(currentCombination.size() - 1);
    }
}