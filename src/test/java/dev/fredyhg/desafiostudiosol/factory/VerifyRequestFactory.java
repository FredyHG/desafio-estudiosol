package dev.fredyhg.desafiostudiosol.factory;

import dev.fredyhg.desafiostudiosol.request.VerifyRequest;

public class VerifyRequestFactory {
    public static VerifyRequest createValidVerifyRequest() {
        return new VerifyRequest("3x15");
    }

    public static VerifyRequest createInvalidVerifyRequest() {
        return new VerifyRequest("5x15");
    }

    public static VerifyRequest createSameCombinationsVerifyRequest() {
        return new VerifyRequest("15x15");
    }
}
