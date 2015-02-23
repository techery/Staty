package techery.io.staty;

import org.immutables.value.Value;

@Value.Immutable
public abstract class State {
    public abstract String userName();
    public abstract String password();

    public abstract Exception error();
}
