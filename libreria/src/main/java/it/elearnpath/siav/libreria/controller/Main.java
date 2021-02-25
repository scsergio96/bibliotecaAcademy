package it.elearnpath.siav.libreria.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        String prova = "ciao";

        Optional<String> s = Optional.of(prova).map(String::toUpperCase);

        System.out.println();
    }

}
