package com.welab.backend_user.secret.hash;

public class SecureHashUtils {
    public static String hash(String message) {
        // TODO: message -> SHA-1 또는SHA-256 hash
        return message;
    }
    public static boolean matches(String message, String hashedMessage) {
        String hashed = hash(message);
        return hashed.equals(hashedMessage);
    }
}