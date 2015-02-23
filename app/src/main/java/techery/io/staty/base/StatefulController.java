package techery.io.staty.base;

import java.lang.ref.WeakReference;

public abstract class StatefulController<State> {

    public interface StateChangeListener {
        public void onStateChange();
    }

    State state;

    public abstract State getInitialState();

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;

        if (stateChangeListener.get() != null) {
            stateChangeListener.get().onStateChange();
        }
    }

    WeakReference<StateChangeListener> stateChangeListener = new WeakReference<StateChangeListener>(null);

    public void setStateChangeListener(StateChangeListener stateChangeListener) {
        this.stateChangeListener = new WeakReference<StateChangeListener>(stateChangeListener);
    }
}