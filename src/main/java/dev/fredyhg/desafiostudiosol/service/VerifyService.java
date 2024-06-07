package dev.fredyhg.desafiostudiosol.service;

import dev.fredyhg.desafiostudiosol.request.VerifyRequest;
import dev.fredyhg.desafiostudiosol.response.VerifyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class VerifyService {

    public VerifyResponse verify(VerifyRequest verifyRequest) {
        log.info("Init analyse at: {}", LocalDateTime.now());


        HashMap<String, Integer> teamsScore = getTeamsScore(verifyRequest.getScore());

        Integer teamOneScore = teamsScore.get("teamOne");
        Integer teamTwoScore = teamsScore.get("teamTwo");

        int teamOneCombinations = findCombinations(teamOneScore).size();
        int teamTwoCombinations = findCombinations(teamTwoScore).size();

        if(teamOneCombinations == 0 || teamTwoCombinations == 0) {
            return new VerifyResponse(0);
        }

        if(teamOneCombinations > teamTwoCombinations) {
            log.info("First team is greater than second team");
            return new VerifyResponse(teamOneCombinations);
        }

        log.info("Second team is greater than first team");
        return new VerifyResponse(teamTwoCombinations);
    }

    private HashMap<String, Integer> getTeamsScore(String score) {

        String[] stringScores = score.split("x");
        HashMap<String, Integer> scores = new HashMap<>();

        scores.put("teamOne", Integer.parseInt(stringScores[0]));
        scores.put("teamTwo", Integer.parseInt(stringScores[1]));

        return scores;
    }

    public static Set<List<String>> findCombinations(int targetScore) {
        Set<List<String>> result = new HashSet<>();
        findCombinationsHelper(targetScore, 0, new ArrayList<>(), result, false);
        return result;
    }

    private static void findCombinationsHelper(int targetScore, int currentScore, List<String> currentCombination, Set<List<String>> result, boolean lastWasTouchdown) {
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
        findCombinationsHelper(targetScore, currentScore + 6, currentCombination, result, true);
        currentCombination.remove(currentCombination.size() - 1);

        // Tenta adicionar um ponto extra (se o último foi um touchdown)
        if (lastWasTouchdown) {
            currentCombination.add("Extra Point");
            findCombinationsHelper(targetScore, currentScore + 1, currentCombination, result, false);
            currentCombination.remove(currentCombination.size() - 1);

            // Tenta adicionar uma conversão de dois pontos (se o último foi um touchdown)
            currentCombination.add("Extra Two Points");
            findCombinationsHelper(targetScore, currentScore + 2, currentCombination, result, false);
            currentCombination.remove(currentCombination.size() - 1);
        }

        // Tenta adicionar um field goal
        currentCombination.add("Field Goal");
        findCombinationsHelper(targetScore, currentScore + 3, currentCombination, result, false);
        currentCombination.remove(currentCombination.size() - 1);
    }
}