import java.io.*;

public class TaskOne  {
    private final BufferedReader reader = new BufferedReader(new FileReader("C://Users//Андрей//IdeaProjects//TeorVer//src//resources//input.txt"));
    TableOfDistribution table = Reader.getTableOnFile(reader);
    private double[] marginalX, marginalY;
    public TaskOne() throws IOException {
    }

    public void marginalDistribution() throws IOException{
        String path = "C://Users//Андрей//IdeaProjects//TeorVer//src//resources//marginal_distribution.txt";
        Writer.clearFile(path);
        marginalX = new double[table.getColumns()];
        marginalY = new double[table.getRows()];
        for(int i = 0; i < table.getColumns(); i++){
            double temp = 0;
            for(int j = 0; j < table.getRows(); j++){
                temp += table.getTable()[j][i];
            }
            marginalX[i] = temp;
            Writer.writeTableOnFile(path, temp, ("x" + (i + 1) + " = " + table.getValueOfX()[i] + ":"));
        }

        for(int i = 0; i < table.getRows(); i++){
            double temp = 0;
            for(int j = 0; j < table.getColumns(); j++){
                temp += table.getTable()[i][j];
            }
            marginalY[i] = temp;
            Writer.writeTableOnFile(path, temp, ("y" + (i + 1) + " = " + table.getValueOfY()[i] + ":"));
        }
        reader.close();
    }

    public void conditionalDistribution() throws IOException{
        String path = "C://Users//Андрей//IdeaProjects//TeorVer//src//resources//conditional_distribution.txt";
        Writer.clearFile(path);
        for(int i = 0; i < table.getRows(); i++){
            double[] temp = new double[table.getColumns()];
            for(int j = 0; j < table.getColumns(); j++){
                temp[j] = table.getTable()[i][j]/marginalY[i];
            }
            Writer.writeTableOnFile(path, temp, "e1|e2 = " + (table.getValueOfY()[i]) + ": ");
        }

        for(int i = 0; i < table.getColumns(); i++){
            double[] temp = new double[table.getRows()];
            for(int j = 0; j < table.getRows(); j++){
                temp[j] = table.getTable()[j][i]/marginalX[i];
            }
            Writer.writeTableOnFile(path, temp, "e2|e1 = " + (table.getValueOfX()[i]) + ": ");
        }
    }

    private double[] mathExpectation(){
        double[] array = new double[2];
        for(int i = 0; i < marginalX.length; i++){
            array[0] += marginalX[i] * table.getValueOfX()[i];
        }
        for(int i = 0; i < marginalY.length; i++){
            array[1] += marginalY[i] * table.getValueOfY()[i];
        }
        return array;
    }

    public void writeMathExpectationOfFile() throws IOException{
        String path = "C://Users//Андрей//IdeaProjects//TeorVer//src//resources//math_expectation.txt";
        Writer.clearFile(path);
        Writer.writeTableOnFile(path, mathExpectation(), "математическое ожидание двумерного вектора:\n");
    }

    public void covMatrix() throws IOException{
        String path = "C://Users//Андрей//IdeaProjects//TeorVer//src//resources//cov_matrix.txt";
        Writer.clearFile(path);
        Writer.writeTableOnFile(path, new double[]{dispersion()[0], covariance()}, "");
        Writer.writeTableOnFile(path, new double[]{covariance(), dispersion()[1]}, "");
    }

    public void corMatrix()throws IOException{
        String path = "C://Users//Андрей//IdeaProjects//TeorVer//src//resources//cor_matrix.txt";
        Writer.clearFile(path);
        double cor = covariance()/Math.sqrt(dispersion()[0] * dispersion()[1]);
        Writer.writeTableOnFile(path, new double[]{1, cor}, "");
        Writer.writeTableOnFile(path, new double[]{cor, 1}, "");
    }

    private double covariance(){
        double value = 0;
        for (int i = 0; i < table.getRows(); i++){
            for(int j = 0; j < table.getColumns(); j++){
                value += table.getValueOfY()[i]*table.getValueOfX()[j]*table.getTable()[i][j];
            }
        }
        return value - mathExpectation()[0] * mathExpectation()[1];
    }

    private double[] dispersion() {
        double dispersionOne = 0;
        double dispersionTwo = 0;
        for (int i = 0; i < marginalX.length; i++){
            dispersionOne += marginalX[i] * table.getValueOfX()[i] * table.getValueOfX()[i];
        }
        dispersionOne -= mathExpectation()[0] * mathExpectation()[0];

        for (int i = 0; i < marginalY.length; i++){
            dispersionTwo += marginalY[i] * table.getValueOfY()[i] * table.getValueOfY()[i];
        }
        dispersionTwo -= mathExpectation()[1] * mathExpectation()[1];
        return new double[]{dispersionOne, dispersionTwo};
    }
}
