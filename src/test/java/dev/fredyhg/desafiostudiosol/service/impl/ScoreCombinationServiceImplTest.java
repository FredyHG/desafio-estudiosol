package dev.fredyhg.desafiostudiosol.service.impl;

import dev.fredyhg.desafiostudiosol.exception.SameCombinationsException;
import dev.fredyhg.desafiostudiosol.factory.VerifyRequestFactory;
import dev.fredyhg.desafiostudiosol.properties.ScoreProperties;
import dev.fredyhg.desafiostudiosol.request.VerifyRequest;
import dev.fredyhg.desafiostudiosol.response.VerifyResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ScoreCombinationServiceImplTest {

    @InjectMocks
    ScoreCombinationServiceImpl scoreCombinationService;

    @Mock
    ScoreProperties scoreProperties;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(scoreProperties.getTouchDown()).thenReturn(6);
        when(scoreProperties.getExtraTouchDown()).thenReturn(1);
        when(scoreProperties.getExtraTouchDownMax()).thenReturn(2);
        when(scoreProperties.getFieldGoal()).thenReturn(3);
    }

    @Test
    void shouldVerifyScoreCombinationsSuccessfully() {

        VerifyResponse verifyResponse = scoreCombinationService.verifyCombinations(VerifyRequestFactory.createValidVerifyRequest());

        assertNotNull(verifyResponse);
        assertTrue(verifyResponse.getCombinations() > 0);
    }

    @Test
    void shouldThrowExceptionForSameScoreCombinations() {

        VerifyRequest sameCombinationsVerifyRequest = VerifyRequestFactory.createSameCombinationsVerifyRequest();

        SameCombinationsException sameCombinationsException = assertThrows(SameCombinationsException.class,
                () -> scoreCombinationService.verifyCombinations(sameCombinationsVerifyRequest));

        assertEquals("The both teams has a same score combination", sameCombinationsException.getMessage());
    }

    @Test
    void shouldReturnZeroCombinationsForInvalidRequest() {
        VerifyResponse verifyResponse = scoreCombinationService.verifyCombinations(VerifyRequestFactory.createInvalidVerifyRequest());

        assertNotNull(verifyResponse);
        assertEquals(0, verifyResponse.getCombinations());
    }
}