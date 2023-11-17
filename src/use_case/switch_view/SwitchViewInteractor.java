package use_case.switch_view;


public class SwitchViewInteractor implements SwitchViewInputBoundary{
    final SwitchViewOutputBoundary presenter;

    public SwitchViewInteractor(SwitchViewOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.prepareSuccessView();
    }
}