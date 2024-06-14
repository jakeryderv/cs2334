import java.util.Random;

public class RandomPlayer extends Player {
    private Random generator;

    public RandomPlayer(String name) {
        super(name);
        this.generator = new Random();
    }

    @Override
    public int[] getMove(int[] pileSizes) {
        int pileIndex = -1;
        int[] validIndices = new int[pileSizes.length];
        int validCount = 0;

        for (int i = 0; i < pileSizes.length; i++) {
            if (pileSizes[i] > 0) {
                validIndices[validCount++] = i;
            }
        }

        if (validCount > 0) {
            pileIndex = validIndices[generator.nextInt(validCount)];
        }

        if (pileIndex == -1) {
            throw new IllegalArgumentException("No non-empty piles found.");
        }

        int objectsToRemove = 1 + generator.nextInt(pileSizes[pileIndex]);

        return new int[]{pileIndex, objectsToRemove};
    }
}
