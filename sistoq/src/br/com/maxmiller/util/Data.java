/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maxmiller.util;

/**
 *
 * @author max
 */
public class Data {

    public static String getNomeMes(int mes) {
        String nomeMes = "";
        switch (mes) {
            case 1:
                nomeMes = "Jan";
                break;
            case 2:
                nomeMes = "Fev";
                break;
            case 3:
                nomeMes = "Mar";
                break;
            case 4:
                nomeMes = "Abr";
                break;
            case 5:
                nomeMes = "Mai";
                break;
            case 6:
                nomeMes = "Jun";
                break;
            case 7:
                nomeMes = "Jul";
                break;
            case 8:
                nomeMes = "Ago";
                break;
            case 9:
                nomeMes = "Set";
                break;
            case 10:
                nomeMes = "Out";
                break;
            case 11:
                nomeMes = "Nov";
                break;
            case 12:
                nomeMes = "Dez";
                break;
        }
        return nomeMes;
    }
}
