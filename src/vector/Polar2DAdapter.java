package vector;

public class Polar2DAdapter implements IVector, IPolar2D{
    private Vector2D srcVector;

    public Polar2DAdapter(Vector2D vector){
        this.srcVector = vector;
    }

    @Override
    public double getAngle() {
        return Math.atan(this.getComponents()[1] / this.getComponents()[0]);
    }

    @Override
    public double abs() {
        return this.srcVector.abs();
    }

    @Override
    public double cdot(IVector vector) {
        return this.srcVector.cdot(vector);
    }

    @Override
    public double[] getComponents() {
        return this.srcVector.getComponents();
    }
}
