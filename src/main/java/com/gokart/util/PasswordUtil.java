package com.gokart.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {
    private static final int SALT_LENGTH = 8; // Reduced from 16 to 8 bytes
    private static final String HASH_ALGORITHM = "SHA-256";
    private static final int HASH_LENGTH = 32; // Limit hash output length
    
    // Generate a random salt
    public static byte[] generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        new SecureRandom().nextBytes(salt);
        return salt;
    }
    
    // Hash password with salt
    public static String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            digest.update(salt);
            byte[] hashedBytes = digest.digest(password.getBytes());
            
            // Truncate hash to fixed length
            byte[] truncatedHash = new byte[HASH_LENGTH];
            System.arraycopy(hashedBytes, 0, truncatedHash, 0, Math.min(hashedBytes.length, HASH_LENGTH));
            
            // Combine salt + truncated hash for storage
            byte[] combined = new byte[salt.length + truncatedHash.length];
            System.arraycopy(salt, 0, combined, 0, salt.length);
            System.arraycopy(truncatedHash, 0, combined, salt.length, truncatedHash.length);
            
            return Base64.getEncoder().encodeToString(combined);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not available", e);
        }
    }
    
    // Verify password against stored hash
    public static boolean verifyPassword(String password, String storedHash) {
        byte[] combined = Base64.getDecoder().decode(storedHash);
        byte[] salt = new byte[SALT_LENGTH];
        System.arraycopy(combined, 0, salt, 0, SALT_LENGTH);
        
        String newHash = hashPassword(password, salt);
        return newHash.equals(storedHash);
    }
}
