package dev.fredyhg.desafiostudiosol.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableConfigurationProperties(ScoreProperties.class)
@TestPropertySource(properties = {
        "score.touchDown=6",
        "score.extraTouchDown=1",
        "score.extraTouchDownMax=2",
        "score.fieldGoal=3"
})
class ScorePropertiesTest {

    @Autowired
    private ScoreProperties scoreProperties;

    @Test
    void shouldLoadScoreProperties() {
        assertNotNull(scoreProperties);
        assertEquals(6, scoreProperties.getTouchDown());
        assertEquals(1, scoreProperties.getExtraTouchDown());
        assertEquals(2, scoreProperties.getExtraTouchDownMax());
        assertEquals(3, scoreProperties.getFieldGoal());
    }
}