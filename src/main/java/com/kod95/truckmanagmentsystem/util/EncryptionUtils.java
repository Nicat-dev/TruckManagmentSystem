package com.kod95.truckmanagmentsystem.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class EncryptionUtils {
    private final String ALGORITHM;
    private final String SECRET_KEY;

    // Constructor injection is recommended
    public EncryptionUtils(@Value("${encode-algorithm}") String algorithm,
                           @Value("${encryption.secret-key}") String secretKey) {
        this.ALGORITHM = algorithm;
        this.SECRET_KEY = secretKey;
    }


    // Encrypt method
    public String encrypt(String data) throws Exception {
        if (SECRET_KEY == null) {
            throw new IllegalArgumentException("SECRET_KEY is not set.");
        }

        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    // Decrypt method
    public String decrypt(String encryptedData) throws Exception {
        if (SECRET_KEY == null) {
            throw new IllegalArgumentException("SECRET_KEY is not set.");
        }

        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        return new String(cipher.doFinal(decodedData));
    }
}
