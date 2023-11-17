package com.juansenen.gaticketapp.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Clase con las funciones para el cifrado y descifrado de las contraseñas
 */
public class PasswordUtil {

    // Método para hashear la contraseña en Android
    public static String hashPassword(String plainPassword) {
        // 10 es la intensidad por defecto del hash
        return BCrypt.withDefaults().hashToString(10, plainPassword.toCharArray());
    }

    // Método para verificar si la contraseña ingresada coincide con la versión hasheada almacenada
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(plainPassword.toCharArray(), hashedPassword);
        return result.verified;
    }
}
