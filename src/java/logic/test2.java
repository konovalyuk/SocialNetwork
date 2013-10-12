/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import com.bionic.socialnetwork.locale.Localisation;
import java.util.Locale;

/**
 *
 * @author admin
 */
public class test2 {
    public static void main(String[] args) {
        Locale l = new Locale("ru");
    Localisation loc = new Localisation(l);
        System.out.println(loc.WELCOME);
    }    
}
