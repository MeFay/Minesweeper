import java.util.Scanner;

public class Game {
    private Field field;
    Field fieldPrint = new Field();

    public Game() {
        this.field = new Field();
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        fieldPrint.populateField();

        while (true) {
            System.out.print("   ");
            for (int i = 1; i <= fieldPrint.field.length; i++) {
                System.out.printf("  %-4d", i);
            }
            System.out.println();

            for (int i = 0; i < fieldPrint.field.length; i++) {
                System.out.printf("%-4d", i + 1);

                for (int j = 0; j < fieldPrint.field[i].length; j++) {
                    Square square = fieldPrint.field[i][j];
                    String value = square.getIcon();
                    System.out.print(value + "   ");
                }
                System.out.println("   ");
            }

            System.out.println("\nEnter row and column (example: 3 4): ");
            try {
                int row = scanner.nextInt()-1;
                int col = scanner.nextInt()-1;



                if (row < 0 || row >= field.field.length || col < 0 || col >= field.field[0].length) {
                    throw new OutOfBoundsException("Out of bounds!");
                }

                fieldPrint.checkAround(row, col);
                fieldPrint.exploreField(row, col);

                if (fieldPrint.gameOverCondition()) {
                    fieldPrint.gameOver();
                    break;
                }
            } catch (OutOfBoundsException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        }
    }

}
