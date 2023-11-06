/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prueba;

/**
 *
 * @author at1DAM2
 */
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author at1DAM2
 */


public class Prueba {
public static void main(String[] args){
        
        JFrame miframe = new JFrame("hola mundo");
        
        miframe.setSize(300, 300);
        
        JButton miboton = new JButton("saludar");
        JButton miboton2 = new JButton("bostezar");
        JButton miboton3 = new JButton("despedir");
        JButton miboton4 = new JButton("pegar");
        
        
        miframe.add(miboton);
        miframe.add(miboton2);
        miframe.add(miboton3);
        miframe.add(miboton4);
        
        miframe.setVisible(true);
        
        
        
        
    }
   
    
}

 
