package kz.vz.imagecomp.model;

public class ComparisonResult {
    public enum Outcome {
        SIMILAR, NOT_SIMILAR
    }

    private double similarity;
    private Outcome outcome;

    public ComparisonResult(double similarity, Outcome outcome) {
        this.similarity = similarity;
        this.outcome = outcome;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    @Override
    public String toString() {
        return String.format("The images are %s - their similarity is %f", outcome, similarity);
    }
}
