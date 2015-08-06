package com.timelapser.business;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dtritus on 04/08/15.
 */
public class InputReader {

    public static int imagesProcessed = 0;

    public static List<BufferedImage> getPictures(String folderName){
        List<BufferedImage> result = new ArrayList<BufferedImage>();
        File[] folder = new File(folderName).listFiles();
        Arrays.sort(folder);
        if(folder != null) {
            for (File fileEntry : folder) {
                try {
                    result.add(ImageIO.read(fileEntry));
                    imagesProcessed++;
                    System.out.println("Processed " + fileEntry.getPath().toString());
                    if(imagesProcessed > 10) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("ES NULL");
        }
        return result;
    }
}
