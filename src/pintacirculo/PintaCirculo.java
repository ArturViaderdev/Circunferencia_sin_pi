/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pintacirculo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author arturv
 */
public class PintaCirculo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        creaimagen();
        System.out.println("Imagen creada");
    }
    
     private static void creaimagen() {
        try {
            int alto = 1080;
            int ancho = 1920;
            BufferedImage imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
            pintablanco(imagen, alto, ancho);
            //pintalineas(imagen,alto,ancho);

             pintacirculo(imagen, alto, ancho);
            //colores(imagen, alto, ancho);
            File f = null;
            f = new File("imagen.png");
            ImageIO.write(imagen, "png", f);
        } catch (IOException ex) {
           
        }
    }
     
      private static void pintablanco(BufferedImage imagen, int ancho, int alto) {
        int color = (255 << 24) | (255 << 16) | (255 << 8) | 255;
        for (int y = 0; y < ancho; y++) {
            for (int x = 0; x < alto; x++) {

                try {
                    imagen.setRGB(x, y, color);
                } catch (Exception ex) {
                    System.out.println(x + " " + y);
                }

            }
        }
    }

    private static void pintacirculo(BufferedImage imagen, int ancho, int alto) {
        ArrayList<Double> listax;
        ArrayList<Double> listay;
        listax = new ArrayList<Double>();
        listay = new ArrayList<Double>();
        double centrox, centroy, puntox, puntoy, radio, velocidadx, velocidady, gravedadx, gravedady, velocidadmax;
        int color = (255 << 24) | (0 << 16) | (0 << 8) | 0;
        int medio;

        gravedadx = 0.001;
        gravedady = 0.001;

        centrox = alto / 2;
        centroy = ancho / 2;
        radio = 400;
        puntox = centrox-radio;
        puntoy = centroy-radio;

        velocidadx = 0;
        velocidady = 0;
        boolean haciendo = false;
        boolean puestohaciendo = false;
        boolean sal = false;
        boolean primera = true;
        boolean iniciay = false;
        int cont = 0;
        medio = 0;

        do {
            if (!iniciay) {
                if (puntox >= centrox) {
                    iniciay = true;
                    listax.add(puntox);
                    listay.add(puntoy);
                }
            }

            if (puntox > centrox) {
                velocidadx -= (puntox-centrox)/1000;
                if (!puestohaciendo) {
                    haciendo = true;
                    puestohaciendo = true;
                }

            } else if (puntox < centrox) {
                velocidadx += (centrox-puntox)/1000;
            } else {
                haciendo = true;
                puestohaciendo = true;
            }

            if (iniciay) {
                if (puntoy > centroy) {
                    velocidady -= (puntoy-centroy)/1000;
                    if (!puestohaciendo) {
                        haciendo = true;
                        puestohaciendo = true;
                    }
                } else if (puntoy < centroy) {
                    velocidady += (centroy-puntoy)/1000;
                } else {

                }

            }
       /*     if (haciendo) {
                if (primera) {
                    primera = false;
                } else {
                    velocidadmax = velocidadx;
                    medio = listax.size();
                    haciendo = false;
                }

            } else {
          
 
            }
*/
            puntox += velocidadx;
            puntoy += velocidady;
            if (iniciay) {
       
                listax.add(puntox);
                listay.add(puntoy);
            }
            

            cont++;
        } while (cont < 100000);
        
        for (cont = 0; cont < listax.size(); cont++) {
            imagen.setRGB(listax.get(cont).intValue(), listay.get(cont).intValue(), color);
        }
    }

}
