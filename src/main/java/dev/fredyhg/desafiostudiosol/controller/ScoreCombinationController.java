package dev.fredyhg.desafiostudiosol.controller;

import dev.fredyhg.desafiostudiosol.controller.request.VerifyRequest;
import dev.fredyhg.desafiostudiosol.controller.response.ResponseMessage;
import dev.fredyhg.desafiostudiosol.controller.response.VerifyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface ScoreCombinationController {

    @Operation(summary = "Verify score combinations", tags = "Verify Combinations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success to verify combinations",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VerifyResponse.class),
                            examples = @ExampleObject(value = "{\"combinations\": 5}"))),
            @ApiResponse(responseCode = "409", description = "The both teams has a same combinations possible score",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"message\": \"The both teams has a same score combination\"}"))),
            @ApiResponse(responseCode = "400", description = "Invalid format of score",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessage.class),
                            examples = @ExampleObject(value = "{\"message\": \"Invalid format of score: '3x15'. Expected format: 'teamOneScorexteamTwoScore' (e.g., '3x15').\"}")))
    })
    @RequestBody(description = "Verify Request", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = VerifyRequest.class),
                    examples = @ExampleObject(value = "{\"score\": \"3x15\"}")))
    ResponseEntity<VerifyResponse> verify(VerifyRequest verifyRequest);
}
