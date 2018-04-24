import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.ColourSpace;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        double faultThreshold = 5;
        double colorDeviationThreshold = 0.002;

        MBFImage originalImage = ImageUtilities.readMBF(new File("src/main/resources/Tree.jpg"));
        MBFImage inputImage = ImageUtilities.readMBF(new File("src/main/resources/Tree.jpg"));


        // check if images are the same colour space
        if (!originalImage.getColourSpace().name().equals(inputImage.getColourSpace().name())) {
            throw new RuntimeException("Images have different colour spaces");
        }

        // check if images are RGB
        if (!inputImage.getColourSpace().name().equals(ColourSpace.RGB.name()) ||
                !originalImage.getColourSpace().name().equals(ColourSpace.RGB.name())) {
            throw new RuntimeException("Images should be RGB");
        }

        double faultCount = 0;

        // check if images matches
        for (int i = 0; i < originalImage.getBounds().getWidth(); i++) {
            for (int j = 0; j < originalImage.getBounds().getHeight(); j++) {
                Float[] originalRGBPixel = originalImage.getPixel(i, j);
                Float[] inputRGBPixel = inputImage.getPixel(i, j);

                double[] RGBRangeBounds = {
                        originalRGBPixel[0] - colorDeviationThreshold,
                        originalRGBPixel[0] + colorDeviationThreshold,

                        originalRGBPixel[1] - colorDeviationThreshold,
                        originalRGBPixel[1] + colorDeviationThreshold,

                        originalRGBPixel[2] - colorDeviationThreshold,
                        originalRGBPixel[2] + colorDeviationThreshold
                };

                if (
                        (inputRGBPixel[0] <= RGBRangeBounds[0] || inputRGBPixel[0] >= RGBRangeBounds[1]) ||
                        (inputRGBPixel[1] <= RGBRangeBounds[2] || inputRGBPixel[1] >= RGBRangeBounds[3]) ||
                        (inputRGBPixel[2] <= RGBRangeBounds[4] || inputRGBPixel[2] >= RGBRangeBounds[5])) {
                    faultCount++;
                    if (faultCount == faultThreshold) {
                        throw new RuntimeException("Images don't match");
                    }
                }
            }
        }

        System.out.println("OK");
    }
}
