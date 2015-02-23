package techery.io.staty.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.ButterKnife;

public abstract class Component<VM extends ViewModel> implements StatefulController.StateChangeListener {
    final VM viewModel;
    View view;

    public Component(VM viewModel) {
        this.viewModel = viewModel;
        this.viewModel.setStateChangeListener(this);
    }

    public void inflate(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.view = inflater.inflate(getViewResource(), null);

        ButterKnife.inject(this, view);
    }

    protected abstract int getViewResource();

    public VM getViewModel() {
        return viewModel;
    }

    public View getView() {
        return view;
    }

    public abstract void syncView();

    @Override
    public void onStateChange() {
        if (getView() != null) {
            getView().post(new Runnable() {
                @Override
                public void run() {
                    syncView();
                }
            });
        }
    }
}