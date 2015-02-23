package techery.io.staty.base;

import android.widget.FrameLayout;

public class ComponentContainer {
    private final FrameLayout frameLayout;

    private Component currentComponent;

    public ComponentContainer(FrameLayout frameLayout) {
        this.frameLayout = frameLayout;
    }

    public void show(Component component) {
        this.currentComponent = component;

        this.currentComponent.inflate(this.frameLayout.getContext());

        this.frameLayout.removeAllViews();
        this.frameLayout.addView(this.currentComponent.getView());
    }
}
