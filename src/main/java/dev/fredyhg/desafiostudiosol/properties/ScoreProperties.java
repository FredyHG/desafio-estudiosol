package dev.fredyhg.desafiostudiosol.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "score")
@Getter
@Setter
@Component
public class ScoreProperties {
    private Integer touchDown;
    private Integer extraTouchDown;
    private Integer extraTouchDownMax;
    private Integer fieldGoal;
}
