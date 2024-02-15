package com.prueba.tecnica.application.services;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;

@Service
public class EncriptService {
    private final String secretKey = "e2a4b91d8d4f7a369ae541876b9c3f8e"; // Clave compartida
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Método para cifrar un objeto JSON con una clave compartida
    public String encryptJson(Object data) throws Exception {
        String jsonData = objectMapper.writeValueAsString(data);
        return encrypt(jsonData);
    }

    // Método para descifrar un objeto JSON con una clave compartida
    public <T> T decryptJson(String encryptedData, Class<T> valueType) throws Exception {
        String decryptedData = decrypt(encryptedData);
        return objectMapper.readValue(decryptedData, valueType);
    }

    // Método para cifrar datos con una clave compartida
    private String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Método para descifrar datos con una clave compartida
    private String decrypt(String encryptedData) throws Exception {
        // Cipher cipher = Cipher.getInstance("AES");
        System.out.println("LlegaACa");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        System.out.println("LlegaCipher");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES/CBC/PKCS5Padding");
        System.out.println("Algo de un llave");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        System.out.println("Inicia Creo");
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }
}
