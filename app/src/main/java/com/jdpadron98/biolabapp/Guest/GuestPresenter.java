package com.jdpadron98.biolabapp.Guest;

import com.jdpadron98.biolabapp.Contract;
import com.jdpadron98.biolabapp.Table1Data;
import com.jdpadron98.biolabapp.Table2Data;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class GuestPresenter implements GuestContract.Presenter {

    public static String TAG = GuestPresenter.class.getSimpleName();

    private WeakReference<GuestContract.View> view;
    private GuestViewModel viewModel;
    private GuestContract.Model model;
    private GuestContract.Router router;

    private ArrayList<String> arrayListTable1 = new ArrayList<>();
    private ArrayList<String> cell_line_list = new ArrayList<>();
    private ArrayList<String> gL50_list = new ArrayList<>();
    private ArrayList<String> id_exp_list = new ArrayList<>();


    public GuestPresenter(GuestState state) {
        viewModel = state;
    }

    /**
     *
     */
    @Override
    public void interactWithModel() {
        final Table1Data table1Data = new Table1Data("", "", "", "");
        final Table2Data table2Data = new Table2Data(cell_line_list, gL50_list, id_exp_list);
        table1Data.setNum_id_biolab(view.get().getID());
        model.readData(table1Data, table2Data, new Contract.DataCallback() {
            @Override
            public void getIDError(boolean error){
                if(!error){
                }

            }
            @Override
            public void getDataError(boolean error) {
                if (!error) {
                    arrayListTable1.add(table1Data.getId_sample());
                    arrayListTable1.add(table1Data.getSmiles());
                    arrayListTable1.add(table1Data.getSoluble());
                    arrayListTable1.add(table1Data.getNotes());

                    cell_line_list = table2Data.getCell_line_list();
                    gL50_list = table2Data.getgL50_list();
                    id_exp_list = table2Data.getId_exp_list();


                    router.goToTable1(table1Data.getAllID(), arrayListTable1, cell_line_list, gL50_list, id_exp_list);
                    System.out.println(arrayListTable1);
                    System.out.println(cell_line_list);
                } else {
                    router.displayNoDataFound();
                    view.get().disableProgressBar();
                }
            }
        });
    }


    @Override
    public void fetchData() {
        // Log.e(TAG, "fetchData()");

        // use passed state if is necessary
        /*GuestState state = router.getDataFromPreviousScreen();
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
*/
    }


    @Override
    public void injectView(WeakReference<GuestContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(GuestContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(GuestContract.Router router) {
        this.router = router;
    }
}
