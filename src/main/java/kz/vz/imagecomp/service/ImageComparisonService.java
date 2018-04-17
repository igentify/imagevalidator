package kz.vz.imagecomp.service;

import kz.vz.imagecomp.model.ComparisonResult;

import java.io.File;

/**
 * The service to compare images basing on the algorithm found here
 * https://www.geeksforgeeks.org/image-processing-java-set-14-comparison-two-images/
 *
 * The service compares two images and gives it's mark (whether they are similar or not)
 * basing on euristic similarity threshold
 */
public interface ImageComparisonService {
    ComparisonResult compare(File target, File pattern);
}
