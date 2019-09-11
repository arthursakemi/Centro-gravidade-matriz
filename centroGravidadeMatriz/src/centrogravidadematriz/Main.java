package centrogravidadematriz;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) throws Exception {
        double[][] input = input();
        int[] centro;

        printVetor(somaLinhas(input));
        System.out.println("");
        printVetor(somaColunas(input));
        System.out.println("");

        centro = centroGravidade(input);

        for (int i = 0; i < 2; i++) {
            System.out.println(centro[i] + " ");
        }

    }

    public static double[][] input() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        String[] tString = br.readLine().split(" ");
        int[] t = new int[2];

        for (int i = 0; i < 2; i++) {
            t[i] = Integer.parseInt(tString[i]);
        }

        String[][] matrizString = new String[t[0]][t[1]];
        double[][] input = new double[matrizString.length][matrizString[0].length];

        for (int i = 0; i < matrizString.length; i++) {
            matrizString[i] = br.readLine().split(" ");
        }

        for (int i = 0; i < matrizString.length; i++) {
            for (int j = 0; j < matrizString[i].length; j++) {
                input[i][j] = Double.parseDouble(matrizString[i][j]);
            }
        }

        return input;
    }

    public static void print(double[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static void printVetor(double[] vetor) {
        for (int i = 0; i < vetor.length; i++) {
            System.out.printf("%.1f ", vetor[i]);
        }
    }

    public static double[] somaLinhas(double[][] matriz) {
        double[] somaLinhas = new double[matriz.length];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                somaLinhas[i] += matriz[i][j];
            }
        }
        return somaLinhas;
    }

    public static double[] somaColunas(double[][] matriz) {
        double[] somaColunas = new double[matriz[0].length];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                somaColunas[j] += matriz[i][j];
            }
        }
        return somaColunas;
    }

    public static int[] centroGravidade(double[][] matriz) {
        int[] centro = new int[2];
        centro[0] = linhaCentro(somaLinhas(matriz));
        centro[1] = linhaCentro(somaColunas(matriz));
        return centro;
    }

    public static int linhaCentro(double[] linha) {
        int x = 0;
        double temp;
        double diferenca = 0;
        double superior = 0;
        double inferior = 0;

        for (int i = 1; i < linha.length - 2; i++) {
            for (int j = 0; j < linha.length; j++) {
                if (j < i) {
                    superior += linha[j];
                }
                if (j > i) {
                    inferior += linha[j];
                }
            }
            temp = superior > inferior ? superior - inferior : inferior - superior;

            if (i == 1) {
                diferenca = temp;
            } else if (temp < diferenca) {
                diferenca = temp;
            }
            x = i;
        }
        return x;
    }

}
