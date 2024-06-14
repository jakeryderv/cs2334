import java.util.function.BooleanSupplier;
import java.util.Arrays;

public class Triangle {
	// constant declaration
	public static final String POLYGONSHAPE = "Triangle";
    public static final double DEFAULT_SIDE = 1.0;
    private double sideA;
    private double sideB;
    private double sideC;
    
 // Default Constructor
    public Triangle() {
        this.sideA = DEFAULT_SIDE;
        this.sideB = DEFAULT_SIDE;
        this.sideC = DEFAULT_SIDE;
    }

    // Three-Argument Constructor
    public Triangle(double a, double b, double c) {
    	if (isTriangle(a,b,c)) {
		    this.sideA = validateSide(a);
		    this.sideB = validateSide(b);
		    this.sideC = validateSide(c);
    	}
    	else {
    		this.sideA = DEFAULT_SIDE;
    		this.sideB = DEFAULT_SIDE;
    		this.sideC = DEFAULT_SIDE;
    	}
    }

    // Array Constructor
    public Triangle(double[] sides) {
        // Validate the sides array
        if (sides == null || sides.length != 3) {
            // If invalid, set default values
            this.sideA = 1;
            this.sideB = 1;
            this.sideC = 1;
        } else {
            // If valid, set the sides from the array
            this.sideA = sides[0];
            this.sideB = sides[1];
            this.sideC = sides[2];

            // Additional validation for a valid triangle
            if (!isValidTriangle()) {
                // If not a valid triangle, set default values
                this.sideA = 1;
                this.sideB = 1;
                this.sideC = 1;
            }
        }

        // Additional logic if needed...
    }
    private boolean isValidTriangle() {
        return (sideA + sideB > sideC) && (sideA + sideC > sideB) && (sideB + sideC > sideA);
    }

    // Copy Constructor
    public Triangle(Triangle other) {
        if (other != null && isTriangle(other.sideA,other.sideB,other.sideC)) {
            this.sideA = other.sideA;
            this.sideB = other.sideB;
            this.sideC = other.sideC;
        } else {
            // If input is null, set default values
            this.sideA = 1;
            this.sideB = 1;
            this.sideC = 1;
        }

        // Additional logic if needed...
    }

    // Private helper method to validate and ensure sides are positive
    private double validateSide(double side) {
        return side > 0 ? side : DEFAULT_SIDE;
    }
    ///////////////////////////////// end of constructors //////////////////////////////////////////////////////////////////////////////////////////////
 // Getters for sides
    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double getSideC() {
        return sideC;
    }

    // Getters for angles
    public double getAngleA() {
        return calculateAngleA(sideA, sideB, sideC);
    }

    public double getAngleB() {
        return calculateAngleB(sideA, sideB, sideC);
    }

    public double getAngleC() {
        return calculateAngleC(sideA, sideB, sideC);
    }

    // Helper method to get all sides as an array
    public double[] getSides() {
        return new double[]{sideA, sideB, sideC};
    }

    // Helper method to get all angles as an array
    public double[] getAngles() {
        return new double[]{getAngleA(), getAngleB(), getAngleC()};
    }

    // Private methods for calculating angles
    private double calculateAngleA(double a, double b, double c) {
    	System.out.println(Math.toDegrees(Math.acos((b * b + c * c - a * a) / (2 * b * c))));
        return Math.toDegrees(Math.acos((b * b + c * c - a * a) / (2 * b * c)));
    }

    private double calculateAngleB(double a, double b, double c) {
    	System.out.println(Math.toDegrees(Math.acos((a * a + c * c - b * b) / (2 * a * c))));
        return Math.toDegrees(Math.acos((a * a + c * c - b * b) / (2 * a * c)));
    }

    private double calculateAngleC(double a, double b, double c) {
    	System.out.println(Math.toDegrees(Math.acos((a * a + b * b - c * c) / (2 * a * b))));
        return Math.toDegrees(Math.acos((a * a + b * b - c * c) / (2 * a * b)));
    }
    /////////////////////////////////// ned of getters for sides ///////////////////////////////////////////////////////////////////////////////
    /// side setters
    public boolean setSideA(double sideA) {
        if (isValidSide(sideA, sideB, sideC)) {
            this.sideA = sideA;
            return true;
        }
        return false;
    }

    public boolean setSideB(double sideB) {
        if (isValidSide(sideA, sideB, sideC)) {
            this.sideB = sideB;
            return true;
        }
        return false;
    }

    public boolean setSideC(double sideC) {
        if (isValidSide(sideA, sideB, sideC)) {
            this.sideC = sideC;
            return true;
        }
        return false;
    }

    // Helper method to check the triangle inequality
    private boolean isValidSide(double a, double b, double c) {
        return (a + b > c) && (b + c > a) && (a + c > b);
    }
///////////////////////////////////////////////////// end of ind side setters / /////////////////////////////////////////////////////////
    
    // isTriangle series
    public static boolean isTriangle(double sideA, double sideB, double sideC) {
        return (sideA + sideB > sideC) && (sideA + sideC > sideB) && (sideB + sideC > sideA);
    }
    public static boolean isTriangle(double[] sides) {
        if (sides == null || sides.length != 3) {
            return false;
        }

        double sideA = sides[0];
        double sideB = sides[1];
        double sideC = sides[2];

        return (sideA + sideB > sideC) && (sideA + sideC > sideB) && (sideB + sideC > sideA);
    }
    
    /////////////////////////////////// end of isTrinagle / ////////////////////////////////////////////////////////////////////////////
    
    // law of cosines
    public static double lawOfCosines(double a, double b, double c) {
        if (!isTriangle(a, b, c)) {
            throw new IllegalArgumentException("Invalid side lengths for a triangle");
        }

        // Calculate the angle using the Law of Cosines
        return Math.toDegrees(Math.acos((a * a + b * b - c * c) / (2 * a * b)));
    }
    
    ////////////////////////////// end of law of cosines /////////////////////////////////////////////////////////
    
    // set sides
    public boolean setSides(double[] sides) {
        if (sides == null || sides.length != 3) {
            return false; // Invalid input
        }

        double a = sides[0];
        double b = sides[1];
        double c = sides[2];

        if (!isTriangle(a, b, c)) {
            return false; // Invalid side lengths for a triangle
        }

        sideA = a;
        sideB = b;
        sideC = c;

        return true;
    }

    // Other methods...

    // Helper method to calculate angles based on sides
    //
    
    ///////////////////////////////////// ned of set sides ////////////////////////////////////////////////////////
    
    
    // toSTring test
    @Override
    public String toString() {
        return String.format("Triangle(%.4f, %.4f, %.4f)", sideA, sideB, sideC);
    }

	

	
    
}

