package techery.io.staty.component;

import techery.io.staty.R;
import techery.io.staty.base.Component;
import techery.io.staty.viewmodel.SimpleViewModel;

public class SimpleComponent extends Component<SimpleViewModel> {

    public SimpleComponent(SimpleViewModel viewModel) {
        super(viewModel);
    }

    @Override
    protected int getViewResource() {
        return R.layout.component_simple;
    }

    @Override
    public void syncView() {

    }
}
