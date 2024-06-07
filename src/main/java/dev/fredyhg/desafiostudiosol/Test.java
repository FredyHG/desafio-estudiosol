//package dev.fredyhg.desafiostudiosol;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public class Test {
//    public static void main(String[] args) {
//        int targetScore = 76; // Exemplo de número alvo
//        Set<List<String>> combinations = findCombinations(targetScore);
//
//
//        // Imprime as combinações
//        for (List<String> combination : combinations) {
//            System.out.println(combination);
//        }
//    }
//
//    public static Set<List<String>> findCombinations(int targetScore) {
//        Set<List<String>> result = new HashSet<>();
//        findCombinationsHelper(targetScore, 0, new ArrayList<>(), result, false);
//        return result;
//    }
//
//    private static void findCombinationsHelper(int targetScore, int currentScore, List<String> currentCombination, Set<List<String>> result, boolean lastWasTouchdown) {
//        if (currentScore == targetScore) {
//            // Ordena a combinação antes de adicionar ao conjunto para evitar duplicatas
//            List<String> sortedCombination = new ArrayList<>(currentCombination);
//            sortedCombination.sort(String::compareTo);
//            result.add(sortedCombination);
//            return;
//        }
//
//        if (currentScore > targetScore) {
//            return;
//        }
//
//        // Tenta adicionar um touchdown
//        currentCombination.add("Touchdown");
//        findCombinationsHelper(targetScore, currentScore + 6, currentCombination, result, true);
//        currentCombination.remove(currentCombination.size() - 1);
//
//        // Tenta adicionar um ponto extra (se o último foi um touchdown)
//        if (lastWasTouchdown) {
//            currentCombination.add("Ponto Extra");
//            findCombinationsHelper(targetScore, currentScore + 1, currentCombination, result, false);
//            currentCombination.remove(currentCombination.size() - 1);
//
//            // Tenta adicionar uma conversão de dois pontos (se o último foi um touchdown)
//            currentCombination.add("Conversão de Dois Pontos");
//            findCombinationsHelper(targetScore, currentScore + 2, currentCombination, result, false);
//            currentCombination.remove(currentCombination.size() - 1);
//        }
//
//        // Tenta adicionar um field goal
//        currentCombination.add("Field Goal");
//        findCombinationsHelper(targetScore, currentScore + 3, currentCombination, result, false);
//        currentCombination.remove(currentCombination.size() - 1);
//    }
//}
