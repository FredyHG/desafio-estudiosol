package dev.fredyhg.desafiostudiosol.controller;

import dev.fredyhg.desafiostudiosol.request.VerifyRequest;
import dev.fredyhg.desafiostudiosol.response.VerifyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface ScoreCombinationController {

    @Operation(summary = "Verify score combinations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success to verify combinations"),
            @ApiResponse(responseCode = "301", description = "The both teams has a same combinations possible score")
    })
    ResponseEntity<VerifyResponse> verify(VerifyRequest verifyRequest);
}
