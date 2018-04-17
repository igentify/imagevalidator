package kz.vz.imagecomp.service;

import kz.vz.imagecomp.exception.ComparisonException;
import kz.vz.imagecomp.model.ComparisonResult;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static kz.vz.imagecomp.model.ComparisonResult.Outcome.NOT_SIMILAR;
import static kz.vz.imagecomp.model.ComparisonResult.Outcome.SIMILAR;

public class ImageComparisonServiceImpl implements ImageComparisonService {

    // TODO[VZ]: The threshold value is just an example number, should be changed based on some testing
    private static final double DIFFERENCE_THRESHOLD = 1;

    public ComparisonResult compare(File target, File pattern) {
        BufferedImage imgA;
        BufferedImage imgB;

        try {

            imgA = ImageIO.read(target);
            imgB = ImageIO.read(pattern);
        } catch (IOException e) {
            // TODO[VZ]: If we are thinking about making it a user service, properly localized message would be nice
            throw new ComparisonException("Unable to read the image", e);
        }
        int width1 = imgA.getWidth();
        int width2 = imgB.getWidth();
        int height1 = imgA.getHeight();
        int height2 = imgB.getHeight();

        if ((width1 != width2) || (height1 != height2)) {
            // TODO[VZ]: Probably should still compare images somehow
            throw new ComparisonException("Unable to compare images of different dimensions");
        } else {
            long difference = 0;
            for (int y = 0; y < height1; y++) {
                for (int x = 0; x < width1; x++) {
                    int rgbA = imgA.getRGB(x, y);
                    int rgbB = imgB.getRGB(x, y);
                    int redA = (rgbA >> 16) & 0xff;
                    int greenA = (rgbA >> 8) & 0xff;
                    int blueA = (rgbA) & 0xff;
                    int redB = (rgbB >> 16) & 0xff;
                    int greenB = (rgbB >> 8) & 0xff;
                    int blueB = (rgbB) & 0xff;
                    difference += Math.abs(redA - redB);
                    difference += Math.abs(greenA - greenB);
                    difference += Math.abs(blueA - blueB);
                }
            }

            // Total number of red pixels = width * height
            // Total number of blue pixels = width * height
            // Total number of green pixels = width * height
            // So total number of pixels = width * height * 3
            double total_pixels = width1 * height1 * 3;

            // Normalizing the value of different pixels
            // for accuracy(average pixels per color
            // component)
            double avg_different_pixels = difference /
                    total_pixels;

            // There are 255 values of pixels in total
            double percentage = (avg_different_pixels /
                    255) * 100;

            return new ComparisonResult(100 - percentage, percentage > DIFFERENCE_THRESHOLD ? NOT_SIMILAR : SIMILAR);
        }
    }
}
