import java.io.BufferedReader;
import java.io.IOException;

public class Reader {
    public static TableOfDistribution getTableOnFile(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        int rows = sizeOfTable(line)[0];
        int columns = sizeOfTable(line)[1];
        TableOfDistribution matrix = new TableOfDistribution(rows, columns);
        line = reader.readLine();
        matrix.setValueOfX(getTableWithX(line, columns));
        int k = 0;
        double[] arrayY = new double[rows];
        line = reader.readLine();
        while (line != null){
            String[] temp = line.split(" ");
            arrayY[k] = Double.parseDouble(temp[0]);
            for(int i = 1; i < temp.length; i++)
                matrix.getTable()[k][i - 1] = Double.parseDouble(temp[i]);
            line = reader.readLine();
            k++;
        }
        matrix.setValueOfY(arrayY);
        return matrix;
    }

    private static double[] getTableWithX(String line, int size){
        double[] array = new double[size];
        for(int i = 0; i < line.split(" ").length; i++)
            array[i] = Double.parseDouble(line.split(" ")[i]);
        return array;
    }

    private static int[] sizeOfTable(String line){
        int rows = Integer.parseInt(line.split(" ")[0]);
        int columns = Integer.parseInt(line.split(" ")[1]);
        return new int[]{rows, columns};
    }
}
