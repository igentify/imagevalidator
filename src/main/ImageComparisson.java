package main;// Java Program to compare two images
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

class ImageComparission
{
    public static void main(String[] args)
    {
        BufferedImage srcBufferedImage = null;
        BufferedImage bufferedImageToCompare = null;

        try
        {
//            File fileA = new File("/home / pratik /"+
//                    " Desktop / image1.jpg");
//            File fileB = new File("/home / pratik /"+
//                    " Desktop / image2.jpg");
//            File sourceImage = new File("C:\\Users\\interview\\IdeaProjects\\imagevalidator\\YonatanHarel\\src\\main\\resources\\Tree.jpg");
//            File comaperedImage = null;
//
//            if (args.length < 1) {
//                System.out.println("No Image uploaded. Please try again...");
//            }
//            else {
//                comaperedImage = new File(args[0]);
//            }
//
//            srcBufferedImage = ImageIO.read(sourceImage);
//
//            if (null != comaperedImage) {
//                bufferedImageToCompare = ImageIO.read(comaperedImage);
//            }

            ClassLoader classLoader = ImgDiffPercent.class.getClassLoader();
//            bufferedImageToCompare = ImageIO.read(new File(classLoader.getResource("Lenna100_denim.jpg").getFile()));
            bufferedImageToCompare = ImageIO.read(new File(classLoader.getResource("Lenna100_pixel.jpg").getFile()));
            srcBufferedImage = ImageIO.read(new File(classLoader.getResource("Lenna100.jpg").getFile()));
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        int width1 = srcBufferedImage.getWidth();
        int width2 = bufferedImageToCompare.getWidth();
        int height1 = srcBufferedImage.getHeight();
        int height2 = bufferedImageToCompare.getHeight();
        int differentPixeles = 0;

        if ((width1 != width2) || (height1 != height2))
            System.out.println("Error: Images dimensions"+
                    " mismatch");
        else
        {
            long difference = 0;
            for (int y = 0; y < height1; y++)
            {
                for (int x = 0; x < width1; x++)
                {
                    int rgbA = srcBufferedImage.getRGB(x, y);
                    int rgbB = bufferedImageToCompare.getRGB(x, y);
                    int redA = (rgbA >> 16) & 0xff;
                    int greenA = (rgbA >> 8) & 0xff;
                    int blueA = (rgbA) & 0xff;
                    Color colorA = new Color(redA, greenA, blueA);
                    int redB = (rgbB >> 16) & 0xff;
                    int greenB = (rgbB >> 8) & 0xff;
                    int blueB = (rgbB) & 0xff;
                    Color colorB = new Color(redB, greenB, blueB);

                    String hexColorA = convertColorToHexadeimal(colorA);
                    String hexColorB = convertColorToHexadeimal(colorB);
//                    if (colorA - colorB > )

                    difference += Math.abs(redA - redB);
                    difference += Math.abs(greenA - greenB);
                    difference += Math.abs(blueA - blueB);
//                    if ()
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

            if (percentage == 0) {
                System.out.println("perfect match");
            }
            else if (percentage < 1.5) {
                System.out.println("images are considered as matched (diff percent: " + percentage + ")");
            }
            else {
                System.out.println("images are not matched (diff percent: " + percentage + ")");
            }

//            System.out.println("Difference Percentage-->" +
//                    percentage);
        }
    }

    public static String convertColorToHexadeimal(Color color)
    {
        String hex = Integer.toHexString(color.getRGB() & 0xffffff);
        if(hex.length() < 6)
        {
            if(hex.length()==5)
                hex = "0" + hex;
            if(hex.length()==4)
                hex = "00" + hex;
            if(hex.length()==3)
                hex = "000" + hex;
        }
        hex = "#" + hex;
        return hex;
    }
}