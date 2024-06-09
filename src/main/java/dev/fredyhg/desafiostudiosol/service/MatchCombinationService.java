package dev.fredyhg.desafiostudiosol.service;

import dev.fredyhg.desafiostudiosol.request.VerifyRequest;
import dev.fredyhg.desafiostudiosol.response.VerifyResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface MatchCombinationService {

    VerifyResponse verifyCombinations(VerifyRequest verifyRequest);

    Set<List<String>> findCombinations(int targetScore);
}
