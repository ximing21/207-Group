package interface_adapter.switch_view;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_project.AddProjectViewModel;
import use_case.switch_view.SwitchViewOutputBoundary;

public class SwitchViewPresenter implements SwitchViewOutputBoundary {
    private final AddProjectViewModel addProjectViewModel;
    private final ViewManagerModel viewManagerModel;

    public SwitchViewPresenter(AddProjectViewModel addProjectViewModel, ViewManagerModel viewManagerModel) {
        this.addProjectViewModel = addProjectViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        //Switch to addProject View
        viewManagerModel.setActiveView(addProjectViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
