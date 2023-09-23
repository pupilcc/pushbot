package com.pupilcc.pushbot.utils;

import lombok.SneakyThrows;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * WorkflowUtils
 *
 * @author pupilcc
 * @since 2023-09-23
 */
public class WorkflowUtils {
    /**
     * Validating payloads from GitHub
     * <a href="https://docs.github.com/en/webhooks/using-webhooks/securing-your-webhooks">GitHub</a>
     *
     * @param secret
     * @param header
     * @param payload
     * @return
     */
    @SneakyThrows
    public static boolean verifySignature(String secret, String header, String payload) {
        String[] parts = header.split("=");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid header format: " + header);
        }
        String sigHex = parts[1];

        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec key = new SecretKeySpec(keyBytes, "HmacSHA256");

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(key);

        byte[] dataBytes = payload.getBytes(StandardCharsets.UTF_8);
        byte[] computedSigBytes = mac.doFinal(dataBytes);

        byte[] sigBytes = hexToBytes(sigHex);

        return Arrays.equals(computedSigBytes, sigBytes);
    }

    private static byte[] hexToBytes(String hex) {
        int len = hex.length() / 2;
        byte[] bytes = new byte[len];

        for (int i = 0; i < hex.length(); i += 2) {
            String c = hex.substring(i, i + 2);
            byte b = (byte) Integer.parseInt(c, 16);
            bytes[i / 2] = b;
        }

        return bytes;
    }

//    public static void main(String[] args) {
//        String secret = "It's a Secret to Everybody";
//        String header = "sha256=757107ea0eb2509fc211221cce984b8a37570b6d7586c22c46f4379c8b043e17";
//        String payload = "Hello, World!";
//
//        boolean isValid = verifySignature(secret, header, payload);
//        System.out.println("Signature is valid: " + isValid);
//    }
}
