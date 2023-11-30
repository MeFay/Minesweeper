public class Square {
    private boolean isRevealed;
    private boolean isBomb;
    private int bombsAround;

    public Square(boolean isBomb) {
        this.isRevealed = isBomb;
        this.isBomb = isBomb;
        this.bombsAround = 0;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public int getBombsAround() {
        return bombsAround;
    }

    public void setBombsAround(int bombsAround) {
        this.bombsAround = bombsAround;
    }

    public enum SquareState {
        EMPTY,
        BOMB,
        NUMBER,
        UNREVEALED
    }

    public String getIcon() {
        SquareState state=null;

        if (isRevealed) {
            if (!isBomb && bombsAround == 0) {
                state = SquareState.EMPTY;
            } else if (isBomb) {
                state = SquareState.BOMB;
            } else if (bombsAround > 0) {
                state = SquareState.NUMBER;
            }
        } else {
            state = SquareState.UNREVEALED;
        }

        return getIconForState(state);
    }

    private String getIconForState(SquareState state) {
        return switch (state) {
            case EMPTY -> "   ";
            case BOMB ->  "\u001b[1m\u001b[0m\u001b[7mX" + Color.RESET;
            case NUMBER -> getNumberDanger();
            case UNREVEALED -> " ~ ";
        };
    }

    private String getNumberDanger() {
        return switch (bombsAround) {
            case 1 -> Color.BLUE_BRIGHT + " 1 " + Color.RESET;
            case 2 -> Color.CYAN_BRIGHT + " 2 " + Color.RESET;
            case 3 -> Color.GREEN_BRIGHT + " 3 " + Color.RESET;
            case 4 -> Color.YELLOW_BRIGHT + " 4 " + Color.RESET;
            case 5 -> Color.RED_BRIGHT + " 5 " + Color.RESET;
            case 6 -> Color.MAGENTA_BRIGHT + " 6 " + Color.RESET;
            case 7 -> Color.MAGENTA_BRIGHT + " 7 " + Color.RESET;
            case 8 -> Color.MAGENTA_BRIGHT + " 8 " + Color.RESET;
            default -> "  ";
        };
    }
}
