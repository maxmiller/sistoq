/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maxmiller.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Max
 */
public class Criptografia {

    public static String SHA_1(String mensagem) throws NoSuchAlgorithmException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(mensagem.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            String retornaSenha = hash.toString(16);
            return retornaSenha;
        } catch (NoSuchAlgorithmException ex) {
            throw  ex;
        }
    }
}
