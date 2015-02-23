package techery.io.staty.base;

public abstract class ViewModel<State> extends StatefulController<State> {
    protected ViewModel() {
        setState(getInitialState());
    }
}