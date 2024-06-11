package dev.fredyhg.desafiostudiosol.controller.impl;

import dev.fredyhg.desafiostudiosol.controller.ScoreCombinationController;
import dev.fredyhg.desafiostudiosol.request.VerifyRequest;
import dev.fredyhg.desafiostudiosol.response.VerifyResponse;
import dev.fredyhg.desafiostudiosol.service.ScoreCombinationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class ScoreCombinationControllerImpl implements ScoreCombinationController {

    private final ScoreCombinationService scoreCombinationService;

    @PostMapping("/verify")
    @Override
    public ResponseEntity<VerifyResponse> verify(@RequestBody VerifyRequest verifyRequest) {
        log.info("Receive score: {}", verifyRequest.score());

        return ResponseEntity.status(HttpStatus.OK).body(scoreCombinationService.verifyCombinations(verifyRequest));
    }
}
