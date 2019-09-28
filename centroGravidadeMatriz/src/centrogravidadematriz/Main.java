package centrogravidadematriz;

/*
Grupo:
Arthur Sakemi
Pedro Araujo
Roger Reis
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {

        double[][] input = input();
        int[] centro = centroGravidade(input);

        System.out.println("Centro: " + Arrays.toString(centro));

    }

    public static double[][] input() throws Exception {
        String[][] inputString;
        double[][] input;

        inputString = inputString();
        input = inputConvert(inputString);

        return input;
    }

    public static double[][] inputConvert(String[][] matriz) {
        double[][] input = new double[matriz.length][matriz[0].length];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                input[i][j] = Double.parseDouble(matriz[i][j]);
            }
        }

        return input;
    }

    public static String[][] inputString() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String[][] matriz;
        String[] sizeString;
        int lines;

        sizeString = br.readLine().split(" ");

        lines = Integer.parseInt(sizeString[0]);

        matriz = new String[lines][];

        for (int i = 0; i < matriz.length; i++) {
            matriz[i] = br.readLine().split(" ");
        }
        return matriz;
    }

    public static int[] centroGravidade(double[][] matriz) {
        int[] centro = new int[2];
        centro[0] = linhaCentro(somaLinhas(matriz)) + 1;
        centro[1] = linhaCentro(somaColunas(matriz)) + 1;
        return centro;
    }

    //descobre qual a linha ou coluna que é o centro de gravidade
    public static int linhaCentro(double[] linha) {
        int x = 0;
        double temp;
        double absDif = 0;
        double superior;
        double inferior;

        //loop externo demarca qual das linhas válidas para o centro de gravidade
        //está sendo avaliada;
        for (int i = 1; i < linha.length - 1; i++) {
            superior = 0;
            inferior = 0;

            //loop interno separa e soma as linhas em seus grupos apropriados
            for (int j = 0; j < linha.length; j++) {
                if (j < i) {
                    superior += linha[j];
                }
                if (j > i) {
                    inferior += linha[j];
                }
            }

            temp = Math.abs(superior - inferior);

            //checa se a diferença atual das partes é a menor possível nesse momento
            if (i == 1) {
                absDif = temp;
            } else if (temp < absDif) {
                absDif = temp;
                x = i;
            }

        }
        return x;
    }

    //soma todas as linhas da matriz e as armazena em um vetor
    public static double[] somaLinhas(double[][] matriz) {
        double[] somaLinhas = new double[matriz.length];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                somaLinhas[i] += matriz[i][j];
            }
        }
        return somaLinhas;
    }

    //soma todas as colunas da matriz e as armazena em um vetor
    public static double[] somaColunas(double[][] matriz) {
        double[] somaColunas = new double[matriz[0].length];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                somaColunas[j] += matriz[i][j];
            }
        }
        return somaColunas;
    }

}
