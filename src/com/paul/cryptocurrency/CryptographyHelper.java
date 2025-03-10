/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paul.cryptocurrency;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

/**
 *
 * @author h4ck3r
 */
public class CryptographyHelper {
    public static String generateHash(String data){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            
            //Convert bytes to hexadecimal
            StringBuffer hexadecimalString = new StringBuffer();
            
            for(int i = 0; i < hash.length; i++) {
                String hexadecimal = Integer.toHexString(0xff & hash[i]);
                if(hexadecimal.length() == 1) hexadecimalString.append("0");
                hexadecimalString.append(hexadecimal);
            }
            
            return hexadecimalString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static KeyPair ellipticCurveCrypto() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec params = new ECGenParameterSpec("secp192k1");
            keyPairGenerator.initialize(params,secureRandom);
            KeyPair keypair = keyPairGenerator.genKeyPair();
            return keypair; 
        }catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //lets use ECDSA Signature and return the result
    public static byte[] applyECDSASignature(PrivateKey privateKey, String input) {
        Signature signature;
        byte[] output = new byte[0];
        try{
           signature = Signature.getInstance("ECDSA", "BC");
           signature.initSign(privateKey);
           byte[] strByte = input.getBytes();
           signature.update(strByte);
           byte[] realSignature = signature.sign();
           output = realSignature;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return output;
    }
    
    //checks whether the given transaction belongs to the sender based on the signature
    public static boolean verifyECDSASignature(PublicKey publicKey, String data, byte[] signature) {
        try {
            Signature ecdsaSignature = Signature.getInstance("ECDSA", "BC");
            ecdsaSignature.initVerify(publicKey);
            ecdsaSignature.update(data.getBytes());
            return ecdsaSignature.verify(signature);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    } 
    
    
}
