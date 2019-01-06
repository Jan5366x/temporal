package de.novacyb.temporal.shared.legacy;

/**
 * Legacy Consumer
 * Created on 06.01.19.
 */
public interface LegacyConsumer<T> {
    void accept(final T value);
}
