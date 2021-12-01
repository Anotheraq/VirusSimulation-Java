package vector;

public class Vector3DInheritance extends Vector2D{
    private double z;

    public Vector3DInheritance(double x, double y, double z){
        super(x,y);
        this.z = z;
    }

    public IVector getSrcV(){
        return new Vector3DInheritance(getComponents()[0], getComponents()[1], this.z);
    }

    public Vector3DInheritance cross(IVector vector){
        double[] components = getComponents();
        double[] vecComponents = vector.getComponents();
        double vecZ = 0;
        if (vecComponents.length == 3) {
            vecZ = vecComponents[2];
        }

        double x = components[1] * vecZ - components[2] * vecComponents[1];
        double y = components[2] * vecComponents[0] - components[0] * vecZ;
        double z = components[0] * vecComponents[1] - components[1] * vecComponents[0];

        return new Vector3DInheritance(x, y, z);
    }

    @Override
    public double abs() {
        double x = super.getComponents()[0];
        double y = super.getComponents()[1];
        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public double cdot(IVector vector) {
        double[] vecComponents = vector.getComponents();
        double[] components = super.getComponents();
        double temp = 0;
        for (int i = 0; i < components.length; i++) {
            temp += components[i] * vecComponents[i];
        }
        return temp;
    }

    @Override
    public double[] getComponents() {
        return  new double[] {super.getComponents()[0], super.getComponents()[1], z};
    }
}
