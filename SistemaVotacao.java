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

    // Quantidade de candidatos
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


    // ETAPAS 6, 7 e 8 - Cadastro de candidatos
    static void cadastrarCandidatos() {

        if (quantidadeCandidatos > 0) {
            System.out.println("Os candidatos já foram cadastrados.");
            return;
        }

        int quantidade;

        // Validação da quantidade
        do {

            quantidade = lerInteiro(
                    "Quantidade de candidatos entre 1 e 5: "
            );

            if (quantidade < 1 || quantidade > MAX_CANDIDATOS) {
                System.out.println("Quantidade inválida.");
            }

        } while (quantidade < 1 || quantidade > MAX_CANDIDATOS);


        // Cadastro dos candidatos
        for (int i = 0; i < quantidade; i++) {

            int numero;

            // ETAPA 7 - Validação do número
            while (true) {

                numero = lerInteiro(
                        "\nNúmero do candidato " + (i + 1) + ": "
                );

                if (numero <= 0) {
                    System.out.println(
                            "O número deve ser maior que zero."
                    );
                    continue;
                }

                boolean numeroRepetido = false;

                for (int j = 0; j < i; j++) {

                    if (numerosCandidatos[j] == numero) {
                        numeroRepetido = true;
                        break;
                    }
                }

                if (numeroRepetido) {
                    System.out.println(
                            "Esse número já está cadastrado."
                    );
                    continue;
                }

                break;
            }


            // ETAPA 8 - Nome do candidato
            String nome;

            do {

                System.out.print("Nome do candidato: ");
                nome = scanner.nextLine().trim();

                if (nome.isEmpty()) {
                    System.out.println(
                            "O nome não pode ficar vazio."
                    );
                }

            } while (nome.isEmpty());


            // Armazena os dados
            numerosCandidatos[i] = numero;
            nomesCandidatos[i] = nome;
            votosCandidatos[i] = 0;

            quantidadeCandidatos++;
        }

        System.out.println("\nCandidatos cadastrados com sucesso!");
    }


    // ETAPA 9 - Buscar candidato
    static int buscarCandidato(int numero) {

        int indiceEncontrado = -1;

        for (int i = 0; i < quantidadeCandidatos; i++) {

            if (numerosCandidatos[i] == numero) {
                indiceEncontrado = i;
                break;
            }
        }

        return indiceEncontrado;
    }


    // ETAPA 10 - Mostrar candidatos
    static void mostrarCandidatos() {

        System.out.println("\nCandidatos disponíveis:");

        for (int i = 0; i < quantidadeCandidatos; i++) {

            System.out.println(
                    numerosCandidatos[i] + " - " + nomesCandidatos[i]
            );
        }
    }


    // ETAPA 11 - Escolher turma
    static int escolherTurma() {

        int turma;

        do {

            turma = lerInteiro("Informe a turma de 1 a 3: ");

            if (turma < 1 || turma > TOTAL_TURMAS) {
                System.out.println("Turma inválida.");
            }

        } while (turma < 1 || turma > TOTAL_TURMAS);

        int indiceTurma = turma - 1;

        return indiceTurma;
    }


    // ETAPA 12 - Iniciar votação
    static void iniciarVotacao() {

        if (quantidadeCandidatos == 0) {
            System.out.println(
                    "Cadastre os candidatos antes de iniciar a votação."
            );
            return;
        }

        int indiceTurma = escolherTurma();

        if (quantidadeVotosTurma[indiceTurma]
                >= MAX_VOTANTES_POR_TURMA) {

            System.out.println(
                    "Essa turma já atingiu o limite de votantes."
            );
            return;
        }

        mostrarCandidatos();

        System.out.println(
                "\nDigite 0 para encerrar a votação desta turma."
        );

        while (quantidadeVotosTurma[indiceTurma]
                < MAX_VOTANTES_POR_TURMA) {

            int numero = lerInteiro(
                    "\nNúmero do candidato: "
            );

            if (numero == 0) {
                System.out.println("Votação encerrada.");
                break;
            }

            int indiceCandidato = buscarCandidato(numero);

            if (indiceCandidato == -1) {
                System.out.println(
                        "Candidato inexistente. Tente novamente."
                );
                continue;
            }

            int posicaoVoto =
                    quantidadeVotosTurma[indiceTurma];

            votosPorTurma[indiceTurma][posicaoVoto] = numero;

            quantidadeVotosTurma[indiceTurma]++;

            votosCandidatos[indiceCandidato]++;

            System.out.println(
                    "Voto registrado com sucesso."
            );
        }

        if (quantidadeVotosTurma[indiceTurma]
                == MAX_VOTANTES_POR_TURMA) {

            System.out.println(
                    "Limite de 10 votantes atingido."
            );
        }
    }


    // ETAPA 13 - Exibir matriz de votos
    static void exibirMatrizVotos() {

        System.out.println("\n===== MATRIZ DE VOTOS =====");

        // Percorre as turmas
        for (int i = 0; i < TOTAL_TURMAS; i++) {

            System.out.print("Turma " + (i + 1) + ": ");

            // Percorre os votantes
            for (int j = 0; j < MAX_VOTANTES_POR_TURMA; j++) {

                if (j < quantidadeVotosTurma[i]) {

                    System.out.print(
                            votosPorTurma[i][j] + " "
                    );

                } else {

                    System.out.print("- ");
                }
            }

            System.out.println();
        }
    }


    // MAIN
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
                    iniciarVotacao();
                    break;

                case 3:
                    System.out.println("Resultado selecionado.");
                    break;

                case 4:
                    exibirMatrizVotos();
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