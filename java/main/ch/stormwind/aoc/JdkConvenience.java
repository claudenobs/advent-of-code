package ch.stormwind.aoc;

import java.util.Optional;

/**
 * Fixes for shitty JDK API.
 */
public final class JdkConvenience {
    private JdkConvenience() {
    }

    /**
     * Returns the given nullable as an Optional.
     */
    public static <T> Optional<T> optional(T nullableValue) {
        return Optional.ofNullable(nullableValue);
    }

    /**
     * Returns the given environment-variable
     */
    public static Optional<String> env(String name) {
        return optional(System.getenv(name));
    }

    /**
     * Returns the given property
     */
    public static Optional<String> property(String key) {
        return Optional.ofNullable(System.getProperty(key));
    }
}
