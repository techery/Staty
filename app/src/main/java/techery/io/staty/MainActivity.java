package techery.io.staty;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.common.base.Optional;

import org.immutables.value.Value;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {

    public static abstract class StatefulController<State> {

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

    public static abstract class ViewModel<State> extends StatefulController<State> {

    }

    public static abstract class Component<VM extends ViewModel> implements StatefulController.StateChangeListener {
        final VM viewModel;
        final View view;

        public Component(VM viewModel, View view) {

            this.view = view;
            ButterKnife.inject(this, view);

            this.viewModel = viewModel;
            this.viewModel.setStateChangeListener(this);
        }

        public VM getViewModel() {
            return viewModel;
        }

        public View getView() {
            return view;
        }

        public abstract void syncView();

        @Override
        public void onStateChange() {
            getView().post(new Runnable() {
                @Override
                public void run() {
                    syncView();
                }
            });
        }
    }

    public static abstract class BaseState {
        public abstract Optional<String> error();
    }

    public static class LoginViewModel extends ViewModel<LoginViewModel.State> {

        @Override
        public State getInitialState() {
            return ImmutableState.builder().userName("").password("").build();
        }

        public void login(String userName, String password) {
            ImmutableState.Builder builder = ImmutableState.builder();

            builder.userName(userName);
            builder.password(password);

            if (!userName.equalsIgnoreCase("zorg")) {
                builder.error("Invalid login or password");
            }

            setState(builder.build());
        }

        @Value.Immutable
        public static abstract class State extends BaseState {
            public abstract String userName();
            public abstract String password();
        }
    }

    public static class LoginComponent extends Component<LoginViewModel> {

        @InjectView(R.id.userName)
        EditText userNameEditText;

        @InjectView(R.id.password)
        EditText passwordEditText;

        @InjectView(R.id.errorMessage)
        TextView errorMessageTextView;

        public LoginComponent(LoginViewModel viewModel, View view) {
            super(viewModel, view);
        }

        @Override
        public void syncView() {
            userNameEditText.setText(getViewModel().getState().userName());
            passwordEditText.setText(getViewModel().getState().password());

            if (getViewModel().getState().error().isPresent()) {
                errorMessageTextView.setText(getViewModel().getState().error().get());
            } else {
                errorMessageTextView.setText("");
            }
        }

        @OnClick(R.id.loginButton)
        public void onLogin(Button button) {
            getViewModel().login(userNameEditText.getText().toString(), passwordEditText.getText().toString());
        }
    }

    LoginComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginViewModel viewModel = new LoginViewModel();

        component = new LoginComponent(viewModel, findViewById(R.id.container));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
