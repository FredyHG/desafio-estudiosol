package dev.fredyhg.desafiostudiosol.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResponseMessage {
    private Integer status;
    private String message;
    private LocalDateTime timestamp;
}
