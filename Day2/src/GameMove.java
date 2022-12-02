public enum GameMove {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    private final int score;

    GameMove(int score) {
        this.score = score;
    }

    public int score() {
        return this.score;
    }

    public static GameMove fromPlayerCode(String playerCode) {
        return switch (playerCode) {
            case "A", "X" -> GameMove.ROCK;
            case "B", "Y" -> GameMove.PAPER;
            case "C", "Z" -> GameMove.SCISSORS;
            default -> null;
        };
    }
}
