package com.timelapser.business;


import com.xuggle.mediatool.*;
import com.xuggle.xuggler.IRational;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;


/**
 * Created by dtritus on 04/08/15.
 */
public class Lapser {

    private static final String inputFolderName = "inputFiles";
    private static final String outputFilename = "timeLapse.mp4";

    public static void main(String[] args) {

        File[] folder = new File(inputFolderName).listFiles();
        Arrays.sort(folder);
        BufferedImage auxFrame = null;
        BufferedImage scaledImage = null;
        Graphics graphs = null;
        IMediaWriter mediaWriter = ToolFactory.makeWriter("timeLapsedVideo.mp4");
        long startTime = System.nanoTime();
        IRational fps = IRational.make(1, 24);
        Boolean firstStep = true;

        int frameCount = 0;

        try {
            for (File fileEntry : folder) {
                System.out.println("Processing image " + (frameCount+1));
                auxFrame = ImageIO.read(fileEntry);

//                if(firstStep) {
                    scaledImage = new BufferedImage(auxFrame.getWidth()/4, auxFrame.getHeight()/4, auxFrame.getType());
//                    mediaWriter.addVideoStream(0, 0, IRational.make(1, 24), auxFrame.getWidth()/4, auxFrame.getHeight()/4);
//                    ImageIO.write(auxFrame, "jpeg", new File("scaled/scaledPicture" + frameCount + " .jpeg"));
//                    firstStep = false;
//                }

                graphs = scaledImage.createGraphics();
                graphs.drawImage(auxFrame, 0, 0, auxFrame.getWidth() / 4, auxFrame.getHeight() / 4, null);
                graphs.dispose();
                ImageIO.write(scaledImage, "jpeg", new File("scaled/scaledPicture" + frameCount + " .jpeg"));
//
//                mediaWriter.encodeVideo(0,
//                        scaledImage,
//                        System.nanoTime() - startTime,
//                        TimeUnit.NANOSECONDS);
                frameCount++;
//                if(frameCount > 100) break;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        mediaWriter.close();
        System.out.println("The job has finished succesfully");

        System.out.println("Total time elapsed: " + (System.nanoTime() - startTime));
    }

    public static BufferedImage convertToType(BufferedImage sourceImage, int targetType) {
        BufferedImage image;
        // if the source image is already the target type, return the source image
        if (sourceImage.getType() == targetType) {
            image = sourceImage;
        }
        // otherwise create a new image of the target type and draw the new image
        else {
            image = new BufferedImage(sourceImage.getWidth(),
                    sourceImage.getHeight(), targetType);
            image.getGraphics().drawImage(sourceImage, 0, 0, null);
        }
        return image;
    }
}
