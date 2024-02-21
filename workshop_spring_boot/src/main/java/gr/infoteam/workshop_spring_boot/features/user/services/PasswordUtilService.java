package gr.infoteam.workshop_spring_boot.features.user.services;

import gr.infoteam.workshop_spring_boot.features.user.User;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;

@Service
public class PasswordUtilService {

    public void encryptPassword(User user) {
        var hash = computePasswordHash(user.getPassword());
        final var hexString = getHashedPasswordToString(hash);
        user.setPassword(hexString);
    }

    private byte[] computePasswordHash(String password) {
        var messageDigest = getMessageDigestForSha256();
        return messageDigest.digest(password.getBytes());
    }

    private String getHashedPasswordToString(byte[] hash) {
        StringBuilder hexString = new StringBuilder();

        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }

    private MessageDigest getMessageDigestForSha256() {
        try {
            final String algorithm = "SHA-256";
            return MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
