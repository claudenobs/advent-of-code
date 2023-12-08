package ch.stormwind.aoc;

public abstract class BaseAocProcessor<T> implements AocProcessor<T> {

    protected static final SimpleLogger LOGGER = new SimpleLogger(2);
    protected static final ResourceReader RESOURCES = new ResourceReader();

    protected final Id id;

    protected BaseAocProcessor(Id id) {
        this.id = id;
    }

    public Id id() {
        return id;
    }
}
