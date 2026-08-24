
import java.util.Scanner;

public class SistemaVotacao {

    static final int MAX_CANDIDATOS = 5;
    static final int TOTAL_TURMAS = 3;
    static final int MAX_VOTANTES_POR_TURMA = 10;

    static Scanner scanner = new Scanner(System.in);
    static int[] numerosCandidatos = new int[MAX_CANDIDATOS];
    static String[] nomesCandidatos = new String[MAX_CANDIDATOS];
    static int[] votosCandidatos = new int[MAX_CANDIDATOS];

    // Matriz de votos por turma
    static int[][] votosPorTurma =
            new int[TOTAL_TURMAS][MAX_VOTANTES_POR_TURMA];

    // Quantidade de votos de cada turma
    static int[] quantidadeVotosTurma =
            new int[TOTAL_TURMAS];

    // Quantidade de candidatos cadastrados
    static int quantidadeCandidatos = 0;


    public static void main(String[] args) {
        System.out.println("Sistema de votação iniciado.");

        scanner.close();
    }
}