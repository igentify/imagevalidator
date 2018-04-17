package kz.vz.imagecomp;

import kz.vz.imagecomp.model.ComparisonResult;
import kz.vz.imagecomp.service.ImageComparisonService;
import kz.vz.imagecomp.service.ImageComparisonServiceImpl;

import java.io.File;

public class Launcher {

    // TODO[VZ]: Using some dependency injection framework would be nice
    private static ImageComparisonService service = new ImageComparisonServiceImpl();

    public static void main(String[] args) {
        // TODO[VZ]: The algorithm that I found uses the legacy Java IO API; should change it to NIO.2

        // TODO[VZ]: Suppose that it would be easier with maven resources plugin; will try to change it if I have time
        ClassLoader classLoader = Launcher.class.getClassLoader();
        File pattern = new File(classLoader.getResource("Tree.jpg").getFile());
        File target = new File(classLoader.getResource("TreeGreenerGrass.jpg").getFile());

        ComparisonResult comparisonResult = service.compare(target, pattern);

        System.out.println(comparisonResult);
    }
}
