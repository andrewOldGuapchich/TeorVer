import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TaskOne taskOne = new TaskOne();
        taskOne.marginalDistribution();
        taskOne.conditionalDistribution();
        taskOne.writeMathExpectationOfFile();
        taskOne.covMatrix();
        taskOne.corMatrix();
    }
}
