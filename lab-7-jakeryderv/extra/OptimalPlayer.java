public class OptimalPlayer extends Player {

	public OptimalPlayer(String name) {
		super(name);
	}

	@Override
	public int[] getMove(int[] pileSizes) {
		int numTwoOrMore = countNumTwoOrMore(pileSizes);
		if (numTwoOrMore > 1) {
			return getMidgameMove(pileSizes);
		}
		return getEndgameMove(pileSizes, numTwoOrMore);
	}

	// Return the number of piles with two or more objects.
	private static int countNumTwoOrMore(int[] pileSizes) {
		int numTwoOrMore = 0;
		for (int size : pileSizes) {
			if (size > 1) {
				++numTwoOrMore;
			}
		}
		return numTwoOrMore;
	}

	// Return an optimal midgame move if possible.
	private static int[] getMidgameMove(int[] pileSizes) {
		int pilesNimSum = nimSum(pileSizes);
		if (pilesNimSum > 0) {
			return getOptimalMidgameMove(pileSizes, pilesNimSum);
		}
		return getNonoptimalMidgameMove(pileSizes);
	}

	// The nim-sum is defined as the bitwise exclusive OR of two numbers.
	private static int nimSum(int a, int b) {
		return a ^ b;
	}

	// Return the nim-sum of all the pile sizes.
	private static int nimSum(int[] pileSizes) {
		int result = 0;
		for (int size : pileSizes) {
			result = nimSum(result, size);
		}
		return result;
	}

	// Return a move that makes the nim-sum of the pile sizes equal to 0.
	private static int[] getOptimalMidgameMove(int[] pileSizes, int pilesNimSum)
	{
		int index = 0;
		int reduceTo = 0;
		for (; index < pileSizes.length; ++index) {
			reduceTo = nimSum(pileSizes[index], pilesNimSum);
			if (reduceTo < pileSizes[index]) {
				break;
			}
		}
		return new int[] {index, pileSizes[index] - reduceTo};
	}

	// Remove one object from the largest pile.
	private static int[] getNonoptimalMidgameMove(int[] pileSizes) {
		int idxLargest = 0;
		int largest = 0;
		for (int idx = 0; idx < pileSizes.length; ++idx) {
			if (pileSizes[idx] > largest) {
				idxLargest = idx;
				largest = pileSizes[idx];
			}
		}
		return new int[] {idxLargest, 1};
	}

	// The endgame starts when only one pile has more than one object.
	private static int[] getEndgameMove(int[] pileSizes, int numTwoOrMore) {
		if (numTwoOrMore > 0) {
			return getOptimalEndgameMove(pileSizes);
		}
		// All piles have 0 or 1 objects. The remaining moves are equivalent.
		int index = 0;
		for (; index < pileSizes.length; ++index) {
			if (pileSizes[index] == 1) {
				break;
			}
		}
		return new int[] {index, 1};
	}

	// Leave the opponent with an odd number of piles with 1 object.
	private static int[] getOptimalEndgameMove(int[] pileSizes) {
		int numOne = 0;
		int idxLargePile = 0;
		for (int idx = 0; idx < pileSizes.length; ++idx) {
			if (pileSizes[idx] == 1) {
				++numOne;
			} else if (pileSizes[idx] > 1) {
				idxLargePile = idx;
			}
		}
		if (numOne % 2 == 1) {
			return new int[] {idxLargePile, pileSizes[idxLargePile]};
		}
		return new int[] {idxLargePile, pileSizes[idxLargePile] - 1};
	}
}
