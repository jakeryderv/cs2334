import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class TriangleTest {
	
	// Define the margin of error for double comparisons.
	private static final double EPSILON = 0.001;
	private static final String POLYGONSHAPE = "Triangle";
	
	// Define triangle data for the tests.
	private static final double[][] SIDES = {
			{1, 1, 1},
			{-1, -1, -1},
			{0, 0, 0},
			{4.51, 4.51, 4.51},
			{1, 1, Math.sqrt(2)},
			{1, 2, 3},
			{1, Math.sqrt(3), 2},
			{3, 4, 5},
			{0, 1, 1},
			{5, 6, 10.999}};
	private static final double[][] ANGLES = {
			{60, 60, 60},
			null,
			null,
			{60, 60, 60},
			{45, 45, 90},
			null,
			{30, 60, 90},
			{36.870, 53.130, 90},
			null,
			{0.7053, 0.8463, 178.4484}};
	private static final boolean[] IS_TRIANGLE = {
			true, 
			false,
			false,
			true,
			true,
			false,
			true,
			true,
			false,
			true};
	
	// Define a helper method to check the output of all side getters.
	private static void checkSides(Triangle triangle, double[] expected, 
			String message) {
		assertEquals(expected[0], triangle.getSideA(), EPSILON, message);
		assertEquals(expected[1], triangle.getSideB(), EPSILON, message);
		assertEquals(expected[2], triangle.getSideC(), EPSILON, message);
		assertArrayEquals(expected, triangle.getSides(), EPSILON, message);
	}
	
	// Define a helper method to check the output of all angle getters.
	private static void checkAngles(Triangle triangle, double[] expected, 
			String message) {
		assertEquals(expected[0], triangle.getAngleA(), EPSILON, message);
		assertEquals(expected[1], triangle.getAngleB(), EPSILON, message);
		assertEquals(expected[2], triangle.getAngleC(), EPSILON, message);
		assertArrayEquals(expected, triangle.getAngles(), EPSILON, message);
	}
	
	@Test
	void testConstantDeclaration() {
		try {
			Field field = Triangle.class.getDeclaredField("POLYGONSHAPE");
			int modifiers = field.getModifiers();
			assertTrue(Modifier.isPublic(modifiers));
			assertTrue(Modifier.isStatic(modifiers));
			assertTrue(Modifier.isFinal(modifiers));
		} catch (NoSuchFieldException e) {
			fail();
		}
	}
	
	
	@Test
	void testIsTriangle() {
		for (int idx = 0; idx < SIDES.length; ++idx) {
			double a = SIDES[idx][0];
			double b = SIDES[idx][1];
			double c = SIDES[idx][2];
			boolean valid = IS_TRIANGLE[idx];
			String format = "isTriangle(%.4f, %.4f, %.4f)";
			
			// Test each permutation of the sides.
			assertEquals(valid, Triangle.isTriangle(a, b, c),
					String.format(format, a, b, c));
			assertEquals(valid, Triangle.isTriangle(a, c, b),
					String.format(format, a, c, b));
			assertEquals(valid, Triangle.isTriangle(b, a, c),
					String.format(format, b, a, c));
			assertEquals(valid, Triangle.isTriangle(b, c, a),
					String.format(format, b, c, a));
			assertEquals(valid, Triangle.isTriangle(c, a, b),
					String.format(format, c, a, b));
			assertEquals(valid, Triangle.isTriangle(c, b, a),
					String.format(format, c, b, a));
		}
	}
	
	
	@Test
	void testIsTriangleArray() {
		// Test invalid array inputs.
		assertFalse(Triangle.isTriangle(null));
		assertFalse(Triangle.isTriangle(new double[0]));
		assertFalse(Triangle.isTriangle(new double[1]));
		assertFalse(Triangle.isTriangle(new double[2]));
		assertFalse(Triangle.isTriangle(new double[4]));
		assertFalse(Triangle.isTriangle(new double[5]));
		
		for (int idx = 0; idx < SIDES.length; ++idx) {
			double a = SIDES[idx][0];
			double b = SIDES[idx][1];
			double c = SIDES[idx][2];
			boolean valid = IS_TRIANGLE[idx];
			String format = "isTriangle([%.4f, %.4f, %.4f])";
			
			// Test each permutation of the sides.
			assertEquals(valid, Triangle.isTriangle(new double[] {a, b, c}),
					String.format(format, a, b, c));
			assertEquals(valid, Triangle.isTriangle(new double[] {a, c, b}),
					String.format(format, a, c, b));
			assertEquals(valid, Triangle.isTriangle(new double[] {b, a, c}),
					String.format(format, b, a, c));
			assertEquals(valid, Triangle.isTriangle(new double[] {b, c, a}),
					String.format(format, b, c, a));
			assertEquals(valid, Triangle.isTriangle(new double[] {c, a, b}),
					String.format(format, c, a, b));
			assertEquals(valid, Triangle.isTriangle(new double[] {c, b, a}),
					String.format(format, c, b, a));
		}
	}
	
	@Test
	void testLawOfCosines() {
		for (int idx = 0; idx < SIDES.length; ++idx) {
			if (!IS_TRIANGLE[idx]) {
				continue;
			}
			double a = SIDES[idx][0];
			double b = SIDES[idx][1];
			double c = SIDES[idx][2];
			double alpha = ANGLES[idx][0];
			double beta = ANGLES[idx][1];
			double gamma = ANGLES[idx][2];
			String format = "lawOfCosines(%.4f, %.4f, %.4f)";
			
			// Test each permutation of the sides.
			assertEquals(gamma, Triangle.lawOfCosines(a, b, c), EPSILON,
					String.format(format, a, b, c));
			assertEquals(gamma, Triangle.lawOfCosines(b, a, c), EPSILON,
					String.format(format, b, a, c));
			assertEquals(beta, Triangle.lawOfCosines(a, c, b), EPSILON,
					String.format(format, a, c, b));
			assertEquals(beta, Triangle.lawOfCosines(c, a, b), EPSILON,
					String.format(format, c, a, b));
			assertEquals(alpha, Triangle.lawOfCosines(b, c, a), EPSILON,
					String.format(format, b, c, a));
			assertEquals(alpha, Triangle.lawOfCosines(c, b, a), EPSILON,
					String.format(format, c, b, a));
		}
	}
	
	@Test
	void testDefaultSide() {
		// This try-catch block checks that the field DEFAULT_SIDE is declared 
		// as public, static, and final. The classes used are beyond the scope 
		// of this course, so don't worry about the details.
		try {
			Field field = Triangle.class.getDeclaredField("DEFAULT_SIDE");
			int modifiers = field.getModifiers();
			assertTrue(Modifier.isPublic(modifiers));
			assertTrue(Modifier.isStatic(modifiers));
			assertTrue(Modifier.isFinal(modifiers));
		} catch (NoSuchFieldException exception) {
			fail();
		}
		// Check the value of the constant.
		assertEquals(1.0, Triangle.DEFAULT_SIDE);
	}
	
	@Test
	void testSideEncapsulation() {
		// Check that the side fields are private and non-static.
		String[] fieldNames = {"sideA", "sideB", "sideC"};
		try {
			for (String name : fieldNames) {
				Field field = Triangle.class.getDeclaredField(name);
				int modifiers = field.getModifiers();
				assertTrue(Modifier.isPrivate(modifiers));
				assertFalse(Modifier.isStatic(modifiers));
			}
		} catch (NoSuchFieldException exception) {
			fail();
		}
	}
	
	@Test
	void testDefaultConstructor() {
		Triangle triangle = new Triangle();
		double[] expected = {1, 1, 1};
		String message = "Triangle(1.000, 1.000, 1.000)";
		checkSides(triangle, expected, message);
	}
	
	@Test
	void testThreeArgumentConstructor() {
		for (int idx = 0; idx < SIDES.length; ++idx) {
			double a = SIDES[idx][0];
			double b = SIDES[idx][1];
			double c = SIDES[idx][2];
			double expectedA = 1;
			double expectedB = 1;
			double expectedC = 1;
			if (IS_TRIANGLE[idx]) {
				expectedA = a;
				expectedB = b;
				expectedC = c;
			}
			String format = "Triangle(%.4f, %.4f, %.4f)";
			
			// Test each permutation of the sides.
			Triangle triangle = new Triangle(a, b, c);
			double[] expected = {expectedA, expectedB, expectedC};
			String msg = String.format(format, a, b, c);
			checkSides(triangle, expected, msg);
			
			triangle = new Triangle(a, c, b);
			expected = new double[] {expectedA, expectedC, expectedB};
			msg = String.format(format, a, c, b);
			checkSides(triangle, expected, msg);
			
			triangle = new Triangle(b, a, c);
			expected = new double[] {expectedB, expectedA, expectedC};
			msg = String.format(format, b, a, c);
			checkSides(triangle, expected, msg);
			
			triangle = new Triangle(b, c, a);
			expected = new double[] {expectedB, expectedC, expectedA};
			msg = String.format(format, b, c, a);
			checkSides(triangle, expected, msg);
			
			triangle = new Triangle(c, a, b);
			expected = new double[] {expectedC, expectedA, expectedB};
			msg = String.format(format, c, a, b);
			checkSides(triangle, expected, msg);
			
			triangle = new Triangle(c, b, a);
			expected = new double[] {expectedC, expectedB, expectedA};
			msg = String.format(format, c, b, a);
			checkSides(triangle, expected, msg);
		}
	}
	
	@Test
	void testArrayConstructor() {
		// Test invalid array inputs.
		double[] expected = {1, 1, 1};
		String msg = "Triangle(1.000, 1.000, 1.000)";
		double[] sides = null;
		checkSides(new Triangle(sides), expected, msg);
		checkSides(new Triangle(new double[0]), expected, msg);
		checkSides(new Triangle(new double[1]), expected, msg);
		checkSides(new Triangle(new double[2]), expected, msg);
		checkSides(new Triangle(new double[4]), expected, msg);
		checkSides(new Triangle(new double[5]), expected, msg);		
		
		for (int idx = 0; idx < SIDES.length; ++idx) {
			double a = SIDES[idx][0];
			double b = SIDES[idx][1];
			double c = SIDES[idx][2];
			double expectedA = 1;
			double expectedB = 1;
			double expectedC = 1;
			if (IS_TRIANGLE[idx]) {
				expectedA = a;
				expectedB = b;
				expectedC = c;
			}
			String format = "Triangle([%.4f, %.4f, %.4f])";
			
			// Test each permutation of the sides.
			Triangle triangle = new Triangle(new double[] {a, b, c});
			expected = new double[] {expectedA, expectedB, expectedC};
			msg = String.format(format, a, b, c);
			checkSides(triangle, expected, msg);
			
			triangle = new Triangle(new double[] {a, c, b});
			expected = new double[] {expectedA, expectedC, expectedB};
			msg = String.format(format, a, c, b);
			checkSides(triangle, expected, msg);
			
			triangle = new Triangle(new double[] {b, a, c});
			expected = new double[] {expectedB, expectedA, expectedC};
			msg = String.format(format, b, a, c);
			checkSides(triangle, expected, msg);
			
			triangle = new Triangle(new double[] {b, c, a});
			expected = new double[] {expectedB, expectedC, expectedA};
			msg = String.format(format, b, c, a);
			checkSides(triangle, expected, msg);
			
			triangle = new Triangle(new double[] {c, a, b});
			expected = new double[] {expectedC, expectedA, expectedB};
			msg = String.format(format, c, a, b);
			checkSides(triangle, expected, msg);
			
			triangle = new Triangle(new double[] {c, b, a});
			expected = new double[] {expectedC, expectedB, expectedA};
			msg = String.format(format, c, b, a);
			checkSides(triangle, expected, msg);
		}
	}
	
	@Test
	void testCopyConstructor() {
		// Test invalid input.
		Triangle triangle = null;
		Triangle copy = new Triangle(triangle);
		double[] expected = {1, 1, 1};
		String msg = "Copy of null";
		checkSides(copy, expected, msg);
		
		for (int idx = 0; idx < SIDES.length; ++idx) {
			double a = SIDES[idx][0];
			double b = SIDES[idx][1];
			double c = SIDES[idx][2];
			double expectedA = 1;
			double expectedB = 1;
			double expectedC = 1;
			if (IS_TRIANGLE[idx]) {
				expectedA = a;
				expectedB = b;
				expectedC = c;
			}
			String format = "Copy of Triangle(%.4f, %.4f, %.4f)";
			
			// Test each permutation of the sides.
			triangle = new Triangle(a, b, c);
			copy = new Triangle(triangle);
			expected = new double[] {expectedA, expectedB, expectedC};
			msg = String.format(format, a, b, c);
			checkSides(copy, expected, msg);
			
			triangle = new Triangle(a, c, b);
			copy = new Triangle(triangle);
			expected = new double[] {expectedA, expectedC, expectedB};
			msg = String.format(format, a, c, b);
			checkSides(copy, expected, msg);
			
			triangle = new Triangle(b, a, c);
			copy = new Triangle(triangle);
			expected = new double[] {expectedB, expectedA, expectedC};
			msg = String.format(format, b, a, c);
			checkSides(copy, expected, msg);
			
			triangle = new Triangle(b, c, a);
			copy = new Triangle(triangle);
			expected = new double[] {expectedB, expectedC, expectedA};
			msg = String.format(format, b, c, a);
			checkSides(copy, expected, msg);
			
			triangle = new Triangle(c, a, b);
			copy = new Triangle(triangle);
			expected = new double[] {expectedC, expectedA, expectedB};
			msg = String.format(format, c, a, b);
			checkSides(copy, expected, msg);
			
			triangle = new Triangle(c, b, a);
			copy = new Triangle(triangle);
			expected = new double[] {expectedC, expectedB, expectedA};
			msg = String.format(format, c, b, a);
			checkSides(copy, expected, msg);
		}
	}
	
	@Test
	void testAngleGetters() {
		for (int idx = 0; idx < SIDES.length; ++idx) {
			double a = SIDES[idx][0];
			double b = SIDES[idx][1];
			double c = SIDES[idx][2];
			double alpha = 60;
			double beta = 60;
			double gamma = 60;
			if (IS_TRIANGLE[idx]) {
				alpha = ANGLES[idx][0];
				beta = ANGLES[idx][1];
				gamma = ANGLES[idx][2];
			}
			String format = "Triangle(%.4f, %.4f, %.4f)";
			
			// Test each permutation of the sides.
			Triangle triangle = new Triangle(a, b, c);
			double[] expected = {alpha, beta, gamma};
			String msg = String.format(format, a, b, c);
			checkAngles(triangle, expected, msg);
			
			triangle = new Triangle(a, c, b);
			expected = new double[] {alpha, gamma, beta};
			msg = String.format(format, a, c, b);
			checkAngles(triangle, expected, msg);
			
			triangle = new Triangle(b, a, c);
			expected = new double[] {beta, alpha, gamma};
			msg = String.format(format, b, a, c);
			checkAngles(triangle, expected, msg);
			
			triangle = new Triangle(b, c, a);
			expected = new double[] {beta, gamma, alpha};
			msg = String.format(format, b, c, a);
			checkAngles(triangle, expected, msg);
			
			triangle = new Triangle(c, a, b);
			expected = new double[] {gamma, alpha, beta};
			msg = String.format(format, c, a, b);
			checkAngles(triangle, expected, msg);
			
			triangle = new Triangle(c, b, a);
			expected = new double[] {gamma, beta, alpha};
			msg = String.format(format, c, b, a);
			checkAngles(triangle, expected, msg);
		}
	}
	
	@Test
	void testIndividualSideSetters() {
		double[] sides = {5, 12, 13};
		double[] angles = {22.6199, 67.3801, 90};
		Triangle triangle = new Triangle(sides);
		checkSides(triangle, sides, "");
		checkAngles(triangle, angles, "");
		
		assertFalse(triangle.setSideA(0));
		assertFalse(triangle.setSideB(0));
		assertFalse(triangle.setSideC(0));
		checkSides(triangle, sides, "");
		checkAngles(triangle, angles, "");
		
		assertTrue(triangle.setSideA(sides[0]));
		assertTrue(triangle.setSideB(sides[1]));
		assertTrue(triangle.setSideC(sides[2]));
		checkSides(triangle, sides, "");
		checkAngles(triangle, angles, "");
		
		// For each side, check the bounds of the allowed set range.
		assertFalse(triangle.setSideA(1));
		assertFalse(triangle.setSideA(25));
		assertFalse(triangle.setSideB(8));
		assertFalse(triangle.setSideB(18));
		assertFalse(triangle.setSideC(7));
		assertFalse(triangle.setSideC(18));
		checkSides(triangle, sides, "");
		checkAngles(triangle, angles, "");
		
		// Change sideA.
		assertTrue(triangle.setSideA(10));
		sides = new double[] {10, 12, 13};
		angles = new double[] {46.9456, 61.2643, 71.7900};
		checkSides(triangle, sides, "");
		checkAngles(triangle, angles, "");
		
		// Check the new bounds.
		assertFalse(triangle.setSideA(1));
		assertFalse(triangle.setSideA(25));
		assertFalse(triangle.setSideB(3));
		assertFalse(triangle.setSideB(23));
		assertFalse(triangle.setSideC(2));
		assertFalse(triangle.setSideC(22));
		checkSides(triangle, sides, "");
		checkAngles(triangle, angles, "");
		
		// Change sideB.
		assertTrue(triangle.setSideB(5));
		sides = new double[] {10, 5, 13};
		angles = new double[] {43.6909, 20.2052, 116.1039};
		checkSides(triangle, sides, "");
		checkAngles(triangle, angles, "");
		
		// Check the new bounds.
		assertFalse(triangle.setSideA(8));
		assertFalse(triangle.setSideA(18));
		assertFalse(triangle.setSideB(3));
		assertFalse(triangle.setSideB(23));
		assertFalse(triangle.setSideC(5));
		assertFalse(triangle.setSideC(15));
		checkSides(triangle, sides, "");
		checkAngles(triangle, angles, "");
		
		// Change sideC.
		assertTrue(triangle.setSideC(6));
		sides = new double[] {10, 5, 6};
		angles = new double[] {130.5416, 22.3316, 27.1268};
		checkSides(triangle, sides, "");
		checkAngles(triangle, angles, "");
		
		// Check the new bounds.
		assertFalse(triangle.setSideA(1));
		assertFalse(triangle.setSideA(11));
		assertFalse(triangle.setSideB(4));
		assertFalse(triangle.setSideB(16));
		assertFalse(triangle.setSideC(5));
		assertFalse(triangle.setSideC(15));
		checkSides(triangle, sides, "");
		checkAngles(triangle, angles, "");
	}
	
	@Test
	void testSetSides() {
		Triangle triangle = new Triangle();
		double[] expectedSides = {1, 1, 1};
		double[] expectedAngles = {60, 60, 60};
		
		// Test invalid array inputs.
		assertFalse(triangle.setSides(null));
		checkSides(triangle, expectedSides, "setSides(null)");
		checkAngles(triangle, expectedAngles, "setSides(null)");
		assertFalse(triangle.setSides(new double[0]));
		checkSides(triangle, expectedSides, "setSide(new double[0])");
		checkAngles(triangle, expectedAngles, "setSide(new double[0])");
		assertFalse(triangle.setSides(new double[1]));
		checkSides(triangle, expectedSides, "setSide(new double[1])");
		checkAngles(triangle, expectedAngles, "setSide(new double[1])");
		assertFalse(triangle.setSides(new double[2]));
		checkSides(triangle, expectedSides, "setSide(new double[2])");
		checkAngles(triangle, expectedAngles, "setSide(new double[2])");
		assertFalse(triangle.setSides(new double[4]));
		checkSides(triangle, expectedSides, "setSide(new double[4])");
		checkAngles(triangle, expectedAngles, "setSide(new double[4])");
		assertFalse(triangle.setSides(new double[5]));
		checkSides(triangle, expectedSides, "setSide(new double[5])");
		checkAngles(triangle, expectedAngles, "setSide(new double[5])");
		
		for (int idx = 0; idx < SIDES.length; ++idx) {
			double[] oldSides = expectedSides;
			double[] newSides = SIDES[idx];
			if (IS_TRIANGLE[idx]) {
				expectedSides = newSides;
				expectedAngles = ANGLES[idx];
			}
			String msg = "Change sides from " + Arrays.toString(oldSides) 
					+ " to " + Arrays.toString(newSides);
			assertEquals(IS_TRIANGLE[idx], triangle.setSides(newSides));
			checkSides(triangle, expectedSides, msg);
			checkAngles(triangle, expectedAngles, msg);
		}
	}
	
	@Test
	void testToString() {
		for (int idx = 0; idx < SIDES.length; ++idx) {
			double a = SIDES[idx][0];
			double b = SIDES[idx][1];
			double c = SIDES[idx][2];
			String format = "Triangle(1.0000, 1.0000, 1.0000)";
			if (IS_TRIANGLE[idx]) {
				format = "Triangle(%.4f, %.4f, %.4f)";
			}
			
			// Test each permutation of the sides.
			Triangle triangle = new Triangle(a, b, c);
			assertEquals(String.format(format, a, b, c), triangle.toString());
			triangle = new Triangle(a, c, b);
			assertEquals(String.format(format, a, c, b), triangle.toString());
			triangle = new Triangle(b, a, c);
			assertEquals(String.format(format, b, a, c), triangle.toString());
			triangle = new Triangle(b, c, a);
			assertEquals(String.format(format, b, c, a), triangle.toString());
			triangle = new Triangle(c, a, b);
			assertEquals(String.format(format, c, a, b), triangle.toString());
			triangle = new Triangle(c, b, a);
			assertEquals(String.format(format, c, b, a), triangle.toString());
		}
	}
}
