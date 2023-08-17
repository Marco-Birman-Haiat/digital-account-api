package com.myprojects.digital.account.utils;

import com.myprojects.digital.account.exceptions.InvalidInputException;

import java.util.InputMismatchException;

public class BrSocialSecurity {
    private String value;

    public BrSocialSecurity(String documentInput) {
        if (!isSocialSeurityfValid(documentInput)) {
         throw new InvalidInputException("cpf is not valid");
        }
        this.value = documentInput;
    }

    private boolean isSocialSeurityfValid(String documentInput) {
        if (documentInput.equals("00000000000") ||
                documentInput.equals("11111111111") ||
                documentInput.equals("22222222222") || documentInput.equals("33333333333") ||
                documentInput.equals("44444444444") || documentInput.equals("55555555555") ||
                documentInput.equals("66666666666") || documentInput.equals("77777777777") ||
                documentInput.equals("88888888888") || documentInput.equals("99999999999") ||
                (documentInput.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do documentInput em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(documentInput.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(documentInput.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == documentInput.charAt(9)) && (dig11 == documentInput.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

    public static String socialSecurityFormat(String documentInput) {
        return(documentInput.substring(0, 3) + "." + documentInput.substring(3, 6) + "." +
                documentInput.substring(6, 9) + "-" + documentInput.substring(9, 11));
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
