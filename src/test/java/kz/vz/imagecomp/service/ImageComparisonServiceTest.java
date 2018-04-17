package kz.vz.imagecomp.service;

import kz.vz.imagecomp.Launcher;
import kz.vz.imagecomp.model.ComparisonResult;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static kz.vz.imagecomp.model.ComparisonResult.Outcome.NOT_SIMILAR;
import static kz.vz.imagecomp.model.ComparisonResult.Outcome.SIMILAR;
import static org.junit.Assert.assertEquals;

public class ImageComparisonServiceTest {

    private static final String PATTERN_FILENAME = "Tree.jpg";
    private static final String SIMILAR_IMAGE_FILENAME = "GoodTree.jpg";
    private static final String NOT_SIMILAR_IMAGE_FILENAME = "BadTree.jpg";


    private ImageComparisonService service;
    private static ClassLoader classLoader = ImageComparisonServiceTest.class.getClassLoader();
    @Before
    public void setUp() {
        service = new ImageComparisonServiceImpl();
    }

    private File loadFileByName(String name) {
        return new File(classLoader.getResource(name).getFile());
    }


    @Test
    public void checkSomeBadPixelsKeepImagesSimilar() {

        ComparisonResult comparisonResult = service.compare(loadFileByName(SIMILAR_IMAGE_FILENAME), loadFileByName(PATTERN_FILENAME));

        assertEquals(comparisonResult.getOutcome(), SIMILAR);
    }

    @Test
    public void checkModifiedImageIsNotSimilar() {
        ComparisonResult comparisonResult = service.compare(loadFileByName(NOT_SIMILAR_IMAGE_FILENAME), loadFileByName(PATTERN_FILENAME));
        assertEquals(comparisonResult.getOutcome(), NOT_SIMILAR);
    }
}
