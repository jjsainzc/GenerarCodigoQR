/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package decodificarcodigoqr;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DecodificarCodigoQR {

    public DecodificarCodigoQR() {
    }

    public static String decodificarQR(String ruta)  {
        Reader lector = new MultiFormatReader();
        File ubicacionImagen = new File(ruta);
        BufferedImage imagen;

        if (ubicacionImagen.exists()) {
            try {
                imagen = ImageIO.read(ubicacionImagen);
                LuminanceSource fuente = new BufferedImageLuminanceSource(imagen);
                BinaryBitmap mapaBits = new BinaryBitmap(new HybridBinarizer(fuente));
                Result resultado = lector.decode(mapaBits);
                return resultado.getText();
            } catch (    IOException | NotFoundException | ChecksumException | FormatException ex) {
                return ex.toString();
            }
        }
        return "No existe el archivo";
    }
    
    public static void main (String[] args)  {
        System.out.println(decodificarQR(System.getProperty("user.home")+"/Desktop/demo.png"));
    }
    
}
