package vector;

public class Polar2DInheritance extends Vector2D{

    public Polar2DInheritance(double x, double y) {
        super(x, y);
    }

    public double getAngle(){
        return Math.atan(getComponents()[1] / getComponents()[0]);
    }
}
