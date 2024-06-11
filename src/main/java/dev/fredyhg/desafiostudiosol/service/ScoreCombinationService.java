package dev.fredyhg.desafiostudiosol.service;

import dev.fredyhg.desafiostudiosol.controller.request.VerifyRequest;
import dev.fredyhg.desafiostudiosol.controller.response.VerifyResponse;

import java.util.List;
import java.util.Set;

public interface ScoreCombinationService {

    VerifyResponse verifyCombinations(VerifyRequest verifyRequest);

    Set<List<String>> findCombinations(int targetScore);
}
