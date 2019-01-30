import dictionary.ObjectType;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Current solution is to:
 *  1) Define areas on image (tree area, sky area, grass area)
 *  2) Split source onto sub images.
 *  3) Calculate ratio of needed colors.
 *  4) Compare ratio to expected ration and answer the question.
 */
public class Main {

    @SneakyThrows
    public static void main(String[] args) {

        try(InputStream imageStream = Main.class.getResourceAsStream("Tree.jpg")) {
            BufferedImage bufferedImage = ImageIO.read(imageStream);

            Map<ObjectType, BufferedImage> splittedImages = splitImage(bufferedImage);
            calculateRatio(splittedImages);
        }

    }

    static Map<ObjectType, BufferedImage> splitImage(BufferedImage bufferedImage) {
        Map<ObjectType, BufferedImage> result = new HashMap<>();

        BufferedImage grass = bufferedImage.getSubimage(0, 580, 1050, 120);
        result.put(ObjectType.GRASS, grass);

        return result;
    }

    private static Map<ObjectType, ?> calculateRatio(Map<ObjectType, BufferedImage> splittedImages) {
        Map<ObjectType, ?> result = new HashMap<>();

        for (Map.Entry<ObjectType, BufferedImage> entry : splittedImages.entrySet()) {

            for (int x = 0; x < entry.getValue().getWidth() ; x++) {
                for (int y = 0; y < entry.getValue().getHeight(); y++) {
                    Color color = new Color(entry.getValue().getRGB(x, y));

                    /* Need to find out if color is from green space of colors */
                    if (Color.GREEN.equals(color)) {

                    }

                }
            }

        }

        return result;
    }

}
