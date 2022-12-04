public class Section {
    private final long firstId;
    private final long lastId;

    public Section(long firstId, long lastId) {
        this.firstId = firstId;
        this.lastId = lastId;
    }

    public boolean contains(Section other) {
        return firstId <= other.firstId && other.lastId <= lastId;
    }

    public static Section fromString(String sectionString) {
        String[] arguments = sectionString.split("-");
        long firstId = Long.parseLong(arguments[0]);
        long lastId = Long.parseLong(arguments[1]);

        return new Section(firstId, lastId);
    }

    public static boolean overlap(Section firstSection, Section secondSection) {
        return !(firstSection.lastId < secondSection.firstId || secondSection.lastId < firstSection.firstId);
    }
}
