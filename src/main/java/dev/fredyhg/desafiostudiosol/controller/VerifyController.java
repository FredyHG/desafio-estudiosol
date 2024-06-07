package dev.fredyhg.desafiostudiosol.controller;

import dev.fredyhg.desafiostudiosol.request.VerifyRequest;
import dev.fredyhg.desafiostudiosol.response.VerifyResponse;
import dev.fredyhg.desafiostudiosol.service.VerifyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class VerifyController {

    private final VerifyService verifyService;

    @PostMapping("/verify")
    public ResponseEntity<VerifyResponse> verify(@RequestBody VerifyRequest verifyRequest) {
        log.info("Receive score: {}", verifyRequest.getScore());

        return ResponseEntity.status(HttpStatus.OK).body(verifyService.verify(verifyRequest));
    }
}
