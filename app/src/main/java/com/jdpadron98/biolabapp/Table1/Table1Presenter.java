package com.jdpadron98.biolabapp.Table1;


import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class Table1Presenter implements Table1Contract.Presenter {

    public static String TAG = Table1Presenter.class.getSimpleName();

    private WeakReference<Table1Contract.View> view;
    private Table1ViewModel viewModel;
    private Table1Contract.Model model;
    private Table1Contract.Router router;

    private String id_biolab;
    private ArrayList<String> cell_line_list;
    private ArrayList<String> gL50_list;
    private ArrayList<String> id_exp_list;

    public Table1Presenter(Table1State state) {
        viewModel = state;
    }

    @Override
    public void fetchData() {
        // Log.e(TAG, "fetchData()");

        // use passed state if is necessary
        Table1State state = router.getDataFromPreviousScreen();
        if (state != null) {

            // update view and model state
            viewModel.data = state.data;

            // update the view
            view.get().displayData(viewModel);

            return;
        }

        // call the model  
        String data = model.fetchData();

        // set view state
        viewModel.data = data;

        // update the view
        view.get().displayData(viewModel);

    }

    @Override
    public void interactWithModel(){
        router.goToTable2(id_biolab,cell_line_list,gL50_list,id_exp_list);
    }

    @Override
    public String getId_biolab() {
        return id_biolab;
    }
    @Override
    public void setId_biolab(String id_biolab) {
        this.id_biolab = id_biolab;
    }

    @Override
    public ArrayList<String> getCell_line_list() {
        return cell_line_list;
    }
    @Override
    public void setCell_line_list(ArrayList<String> cell_line_list) {
        this.cell_line_list = cell_line_list;
    }
    @Override
    public ArrayList<String> getgL50_list() {
        return gL50_list;
    }
    @Override
    public void setgL50_list(ArrayList<String> gL50_list) {
        this.gL50_list = gL50_list;
    }
    @Override
    public ArrayList<String> getId_exp_list() {
        return id_exp_list;
    }
    @Override
    public void setId_exp_list(ArrayList<String> id_exp_list) {
        this.id_exp_list = id_exp_list;
    }

    @Override
    public void injectView(WeakReference<Table1Contract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(Table1Contract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(Table1Contract.Router router) {
        this.router = router;
    }
}
