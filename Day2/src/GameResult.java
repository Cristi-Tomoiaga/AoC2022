public enum GameResult {
    WIN(6),
    LOSE(0),
    DRAW(3);

    private final int score;

    GameResult(int score) {
        this.score = score;
    }

    public int score() {
        return this.score;
    }

    public static GameResult fromPlayerCode(String playerCode) {
        return switch (playerCode) {
            case "X" -> GameResult.LOSE;
            case "Y" -> GameResult.DRAW;
            case "Z" -> GameResult.WIN;
            default -> null;
        };
    }
}
