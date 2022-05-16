package waa.project.finalproj.service.impl;

import java.util.UUID;

public class Utility {

    public static String generateToken() {
        StringBuilder token = new StringBuilder();
        return token.append(UUID.randomUUID()).append(UUID.randomUUID()).toString();
    }
}
