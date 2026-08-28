import java.util.Scanner;

public class SistemaVotacao {

    // Constantes
    static final int MAX_CANDIDATOS = 5;
    static final int TOTAL_TURMAS = 3;
    static final int MAX_VOTANTES_POR_TURMA = 10;

    // Scanner
    static Scanner scanner = new Scanner(System.in);

    // Arrays dos candidatos
    static int[] numerosCandidatos = new int[MAX_CANDIDATOS];
    static String[] nomesCandidatos = new String[MAX_CANDIDATOS];
    static int[] votosCandidatos = new int[MAX_CANDIDATOS];

    // Matriz de votos por turma
    static int[][] votosPorTurma =
            new int[TOTAL_TURMAS][MAX_VOTANTES_POR_TURMA];

    // Quantidade de votos por turma
    static int[] quantidadeVotosTurma =
            new int[TOTAL_TURMAS];

    // Quantidade de candidatos cadastrados
    static int quantidadeCandidatos = 0;

    // ETAPA 5 - Leitura segura de números
    static int lerInteiro(String mensagem) {

        while (true) {

            System.out.print(mensagem);

            if (scanner.hasNextInt()) {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            }

            System.out.println("Entrada inválida. Digite um número.");
            scanner.nextLine();
        }
    }

    // ETAPAS 6 e 7 - Cadastro de candidatos
    static void cadastrarCandidatos() {

        // Impede cadastrar novamente
        if (quantidadeCandidatos > 0) {
            System.out.println("Os candidatos já foram cadastrados.");
            return;
        }

        int quantidade;

        // Validação da quantidade de candidatos
        do {

            quantidade = lerInteiro(
                    "Quantidade de candidatos entre 1 e 5: "
            );

            if (quantidade < 1 || quantidade > MAX_CANDIDATOS) {
                System.out.println("Quantidade inválida.");
            }

        } while (quantidade < 1 || quantidade > MAX_CANDIDATOS);


        // ETAPA 7 - Cadastro dos números dos candidatos
        for (int i = 0; i < quantidade; i++) {

            int numero;

            while (true) {

                numero = lerInteiro(
                        "\nNúmero do candidato " + (i + 1) + ": "
                );

                // O número precisa ser maior que zero
                if (numero <= 0) {
                    System.out.println(
                            "O número deve ser maior que zero."
                    );
                    continue;
                }

                // Verifica se o número já existe
                boolean numeroRepetido = false;

                for (int j = 0; j < i; j++) {

                    if (numerosCandidatos[j] == numero) {
                        numeroRepetido = true;
                        break;
                    }
                }

                // Se estiver repetido, pede novamente
                if (numeroRepetido) {
                    System.out.println(
                            "Esse número já está cadastrado."
                    );
                    continue;
                }

                // Número válido
                break;
            }

            // Guarda o número do candidato
            numerosCandidatos[i] = numero;
        }
    }

    // PROGRAMA PRINCIPAL
    public static void main(String[] args) {

        int opcao;

        do {

            System.out.println("\n===== SISTEMA DE VOTAÇÃO =====");
            System.out.println("1 - Cadastrar candidatos");
            System.out.println("2 - Iniciar votação");
            System.out.println("3 - Exibir resultado");
            System.out.println("4 - Exibir matriz de votos");
            System.out.println("5 - Sair");

            opcao = lerInteiro("Opção: ");

            switch (opcao) {

                case 1:
                    cadastrarCandidatos();
                    break;

                case 2:
                    System.out.println("Votação selecionada.");
                    break;

                case 3:
                    System.out.println("Resultado selecionado.");
                    break;

                case 4:
                    System.out.println("Matriz selecionada.");
                    break;

                case 5:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 5);

        scanner.close();
    }
}