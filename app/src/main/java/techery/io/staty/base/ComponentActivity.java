package techery.io.staty.base;

import android.support.v7.app.ActionBarActivity;

public class ComponentActivity extends ActionBarActivity {

    Component rootComponent;

    public Component getRootComponent() {
        return rootComponent;
    }

    public void setRootComponent(Component rootComponent) {
        this.rootComponent = rootComponent;
        this.rootComponent.inflate(this);

        setContentView(this.rootComponent.getView());
    }
}
