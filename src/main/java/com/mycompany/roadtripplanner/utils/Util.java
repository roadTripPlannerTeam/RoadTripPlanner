package com.mycompany.roadtripplanner.utils;

import com.mycompany.roadtripplanner.entities.Stage;

import java.util.Date;

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


    public  String createTitle(Stage stage1 , Stage stage2, Date date1, Date date2) {
        String title = stage1.getName() + stage2.getName()  + date1 + date2;

        return title ;
    }


}
