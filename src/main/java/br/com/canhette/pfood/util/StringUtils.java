package br.com.canhette.pfood.util;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class StringUtils {

    public static boolean isEmpty(String str){
        if(str == null){
            return true;
        }

        return str.trim() //remove espaços em branco das pontas
                .length() == 0;
    }

    public static String encrypt(String rawString){
        if(isEmpty(rawString)){
            return null;
        }

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(); //criptografar senha
        return encoder.encode(rawString);
    }
}
