/* 
 * Assignment 2 - Web Engineering
 * John Villegas (c3476534)
 * Paul Cabanas (c3481070)
 */

package com.example.assign2.Entities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

import com.password4j.Argon2Function;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Argon2;

public class PasswordSecurity {
    // Generates a random salt (double value between 0 and 1)
    public static Double generateSalt() {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextDouble(0, 1);
    }

    // Hashes the passwor using Argon2
    public static String hashPassword(String password, Double salt) {

        // Declare an instance of Argon2 class
        Argon2Function argon2 = Argon2Function.getInstance(15, 2, 1, 32, Argon2.ID);

        // Generating a hash
        Hash hash = Password.hash(password)
                .addSalt(salt.toString())
                .with(argon2);

        return hash.getResult();
    }

    // Verify password
    public static boolean verifyPassword(String passwordToVerify, Student student) {
        if (hashPassword(passwordToVerify, student.getPasswordSalt()).equals(student.getPasswordHash()))

            return true;

        return false;
    }

    public static String hashPassword(String password, String salt) {
        try {
            String toHash = password + salt;
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(toHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
