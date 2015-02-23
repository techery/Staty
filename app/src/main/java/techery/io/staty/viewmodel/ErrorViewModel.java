package techery.io.staty.viewmodel;

import org.immutables.value.Value;

import techery.io.staty.base.ViewModel;

public class ErrorViewModel extends ViewModel<ErrorViewModel.ErrorState> {

    @Override
    public ErrorState getInitialState() {
        return ImmutableErrorState.builder().build();
    }

    @Value.Immutable
    public static class ErrorState {

    }
}
