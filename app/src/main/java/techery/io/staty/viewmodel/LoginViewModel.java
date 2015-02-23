package techery.io.staty.viewmodel;

import org.immutables.value.Value;

import techery.io.staty.base.BaseState;
import techery.io.staty.base.ViewModel;

public class LoginViewModel extends ViewModel<LoginViewModel.LoginState> {

    private LoginState initialState;

    public LoginViewModel(LoginState state) {
        this.initialState = state;
    }

    @Override
    public LoginState getInitialState() {
        if (this.initialState != null) {
            return this.initialState;
        }

        return ImmutableLoginState.builder().userName("").password("").build();
    }

    public void login(String userName, String password) {
        ImmutableLoginState.Builder builder = ImmutableLoginState.builder();

        builder.userName(userName);
        builder.password(password);

        if (!userName.equalsIgnoreCase("zorg")) {
            builder.error("Invalid login or password");
        }

        setState(builder.build());
    }

    @Value.Immutable
    public static abstract class LoginState extends BaseState {
        @Value.Parameter
        public abstract String userName();

        @Value.Parameter
        public abstract String password();
    }
}