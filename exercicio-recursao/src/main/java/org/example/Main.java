package org.example;

public class Main {
    public Integer somar(Integer numero,Integer total){
        if (numero == 0) return 1;

        return somar(numero -1, total + numero);
    }
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}