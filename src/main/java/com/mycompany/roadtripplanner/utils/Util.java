package com.mycompany.roadtripplanner.utils;

public class Util {

    /**
     * Fonction qui permet de calculer un nouveau montant
     * Utiliser pour la table budget
     * @Param double initalAmout, double expense
     * @Return double newAmount
     */
    public static double calculateNewAmount(double initialAmount, double expense){
        double newAmount = initialAmount - expense;
        if(newAmount < 0){
            newAmount = 0;
        }
        System.out.println(newAmount);
        return newAmount;
    }

}
