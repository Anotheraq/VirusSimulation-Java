package vector;

public class Vector3DDecorator implements IVector{
    private IVector srcVector;
    private double z;

    public Vector3DDecorator(Vector2D vector, double z){
        this.srcVector = vector;
        this.z = z;
    }

    public Vector3DDecorator cross(IVector vector){
        double[] components = getComponents();
        double[] vecComponents = vector.getComponents();
        double vecZ = 0;
        if (vecComponents.length == 3) {
            vecZ = vecComponents[2];
        }

        double x = components[1] * vecZ - components[2] * vecComponents[1];
        double y = components[2] * vecComponents[0] - components[0] * vecZ;
        double z = components[0] * vecComponents[1] - components[1] * vecComponents[0];

        return new Vector3DDecorator(new Vector2D(x, y), z);
    }

    IVector getSrcV(){
        return this.srcVector;
    }

    @Override
    public double abs() {
        double x = srcVector.getComponents()[0];
        double y = srcVector.getComponents()[1];
        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public double cdot(IVector vector) {
        double[] vecComponents = vector.getComponents();
        double[] components = getComponents();
        double temp = 0;
        for (int i = 0; i < components.length; i++) {
            temp += components[i] * vecComponents[i];
        }
        return temp;
    }

    @Override
    public double[] getComponents() {
        return new double[] {srcVector.getComponents()[0], srcVector.getComponents()[1], z};

    }
}
