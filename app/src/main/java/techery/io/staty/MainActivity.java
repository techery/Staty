package techery.io.staty;

import android.os.Bundle;

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

import techery.io.staty.base.ComponentActivity;
import techery.io.staty.base.ViewModel;
import techery.io.staty.component.LoginComponent;
import techery.io.staty.viewmodel.ImmutableLoginState;
import techery.io.staty.viewmodel.LoginViewModel;

public class MainActivity extends ComponentActivity {

    DB snappydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            snappydb = DBFactory.open(this);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }

        LoginViewModel.LoginState state = null;
        try {
            state = snappydb.getObject("state", LoginViewModel.LoginState.class);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }

        LoginViewModel viewModel = new LoginViewModel(state);

        setRootComponent(new LoginComponent(viewModel));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        try {
            final LoginViewModel.LoginState state = (LoginViewModel.LoginState) getRootComponent().getViewModel().getState();

            snappydb.put("state", state);

            final ImmutableLoginState state2 = snappydb.getObject("state", ImmutableLoginState.class);

            snappydb.close();

        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }
}
