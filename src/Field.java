public class Field {
    Square[][] field = new Square[9][9];
    private int bombProbability = 35;

    public void populateField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++)
                field[i][j] = new Square(false);
        }
        placeBomb();
    }

    public void placeBomb() {
        for (Square[] squares : field) {
            for (Square square : squares) {
                int chanceBombPlaced = (int) Math.round(Math.random() * 100);
                if (!square.isBomb() && chanceBombPlaced <= bombProbability) {
                    square.setBomb(true);
                }
            }
        }
    }



    public int checkAround(int x, int y) {
        int bombsCount = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int newX = x + i;
                int newY = y + j;

                if (isValidCoordinate(newX, newY) && field[newX][newY].isBomb()) {
                    bombsCount++;
                }
            }
        }

        field[x][y].setBombsAround(bombsCount);
        field[x][y].setRevealed(true);
        return bombsCount;
    }



    public void exploreField(int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int newX = x + i;
                int newY = y + j;

                if (newX < 0 || newX > (field.length - 1) || newY < 0 || newY > (field.length - 1)) {
                    continue;
                }


                Square currentField = field[newX][newY];

                if (currentField.getBombsAround() > 0) {
                    currentField.setRevealed(true);
                    return;
                }

                if (!currentField.isBomb() && !currentField.isRevealed()) {
                    currentField.setRevealed(true);
                    checkAround(newX, newY);

                    if (currentField.getBombsAround() == 0) {
                        exploreField(newX, newY);
                    }
                }
            }
        }
    }

    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < field.length && y >= 0 && y < field[0].length;
    }

    public boolean gameOverCondition() {
        for (Square[] line : field) {
            for (Square square : line) {
                if (square.isBomb() && square.isRevealed()) {
                    return true;  // Game over if a bomb is revealed
                }
            }
        }
        return false;
    }

    public void gameOver() {
        System.out.println("Game Over! Revealing all bombs:");
        for (Square[] line : field) {
            for (Square square : line) {
                if (square.isBomb()) {
                    square.setRevealed(true);
                    System.out.print(square.getIcon() + " | ");
                } else {
                    System.out.print(" ~ | ");
                }
            }
            System.out.println();
        }
    }
}