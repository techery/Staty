package techery.io.staty.component;

import techery.io.staty.R;
import techery.io.staty.base.Component;
import techery.io.staty.viewmodel.ErrorViewModel;

public class ErrorComponent extends Component<ErrorViewModel> {

    public ErrorComponent(ErrorViewModel viewModel) {
        super(viewModel);
    }

    @Override
    protected int getViewResource() {
        return R.layout.component_error;
    }

    @Override
    public void syncView() {

    }
}
