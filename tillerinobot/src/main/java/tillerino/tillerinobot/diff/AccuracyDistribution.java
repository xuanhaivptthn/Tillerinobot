package tillerino.tillerinobot.diff;

import java.util.function.IntToDoubleFunction;

import lombok.Value;

import org.apache.commons.lang3.tuple.Pair;
import org.tillerino.osuApiModel.OsuApiScore;

@Value
public class AccuracyDistribution {
	final int x300;
	final int x100;
	final int x50;
	final int miss;
	
	public static AccuracyDistribution get(int allObjects, int misses, double acc) {
		int best300s = getBest300s(allObjects, misses, acc);
		int best100s = getBest100s(allObjects, best300s, misses, acc).getLeft();
		return new AccuracyDistribution(best300s, best100s, allObjects - best300s - best100s - misses, misses);
	}

	static Pair<Integer, Double> bisect(int min, int max, IntToDoubleFunction fun, double target) {
		double minVal = fun.applyAsDouble(min);
		if(minVal >= target || min == max) {
			return Pair.of(min, minVal);
		}
		double maxVal = fun.applyAsDouble(max);
		if(maxVal <= target) {
			return Pair.of(max, maxVal);
		}
		/*
		 * invariant: minVal < target, maxVal > target, min != max
		 */
		for(;;) {
			if(max == min + 1) {
				if(Math.abs(minVal - target) < Math.abs(maxVal - target)) {
					return Pair.of(min, minVal);
				}
				return Pair.of(max, maxVal);
			}
			int center = (max + min) / 2;
			double centerVal = fun.applyAsDouble(center);
			if(centerVal <= target) {
				min = center;
				minVal = centerVal;
			} else {
				max = center;
				maxVal = centerVal;
			}
		}
	}

	static Pair<Integer, Double> getBest100s(int allObjects, int x300, int misses, double acc) {
		return bisect(
				0,
				allObjects - x300 - misses,
				x -> OsuApiScore.getAccuracy(x300, x, allObjects - x300 - x
						- misses, misses), acc);
	}

	static int getBest300s(int allObjects, int misses, double acc) {
		/*
		 * Why does this work?
		 *
		 * _50s are uniquely determined by _300s and _50s.
		 * => _50s becomes slack variable.
		 * => Solution polytope is _300s + _100s <= allObjects - misses.
		 * (Tip: draw this. It's 2D)
		 * Figure out analytical, continuous solution.
		 * => Discrete solution must be within +/- _300s, +/- 5 * _100s bounding box.
		 * Make sure that 0 <= _300s <= allObjects - misses.
		 *
		 * Search for _100s can be optimized as well if need be.
		 */
		int guess = (int) Math.round((misses + 6 * allObjects * acc - allObjects) / 5);
		int best = 0;
		double bestError = Double.POSITIVE_INFINITY;
		for (int i = Math.max(0, guess - 1); i <= Math.min(allObjects - misses, guess + 1); i++) {
			double error = Math.abs(acc - getBest100s(allObjects, i, misses, acc).getRight());
			if (error < bestError) {
				best = i;
				bestError = error;
			}
		}
		return best;
	}
}
