package techery.io.staty.viewmodel;

import org.immutables.value.Value;

import techery.io.staty.base.ViewModel;

public class SimpleViewModel extends ViewModel<SimpleViewModel.SimpleState> {

    @Override
    public SimpleState getInitialState() {
        return ImmutableSimpleState.builder().build();
    }

    public void startLoading() {

    }

    @Value.Immutable
    public abstract static class SimpleState {
        abstract boolean isLoading();
    }
}
