package techery.io.staty.component;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.common.base.Optional;

import butterknife.InjectView;
import butterknife.OnClick;
import techery.io.staty.R;
import techery.io.staty.base.Component;
import techery.io.staty.base.ComponentContainer;
import techery.io.staty.viewmodel.ErrorViewModel;
import techery.io.staty.viewmodel.LoginViewModel;
import techery.io.staty.viewmodel.SimpleViewModel;

public class LoginComponent extends Component<LoginViewModel> {

    @InjectView(R.id.userName)
    EditText userNameEditText;

    @InjectView(R.id.password)
    EditText passwordEditText;

    @InjectView(R.id.errorMessage)
    TextView errorMessageTextView;

    ComponentContainer componentContainer;

    public LoginComponent(LoginViewModel viewModel) {
        super(viewModel);
    }

    @Override
    protected int getViewResource() {
        return R.layout.component_login;
    }

    @Override
    public void inflate(Context context) {
        super.inflate(context);

        componentContainer = new ComponentContainer((android.widget.FrameLayout) getView().findViewById(R.id.container));
    }

    @Override
    public void syncView() {
        userNameEditText.setText(getViewModel().getState().userName());
        passwordEditText.setText(getViewModel().getState().password());

        final Optional<String> error = getViewModel().getState().error();

        if (error.isPresent()) {
            errorMessageTextView.setText(error.get());
            componentContainer.show(new ErrorComponent(new ErrorViewModel()));
        } else {
            errorMessageTextView.setText("");
            componentContainer.show(new SimpleComponent(new SimpleViewModel()));
        }
    }

    @OnClick(R.id.loginButton)
    public void onLogin(Button button) {
        getViewModel().login(userNameEditText.getText().toString(), passwordEditText.getText().toString());
    }
}
