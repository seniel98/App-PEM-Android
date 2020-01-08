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

    private Table1Data table1Data;
    private Table2Data table2Data;

    private ArrayList<String> arrayListTable1 = new ArrayList<>();
    private ArrayList<String> cell_line_list = new ArrayList<>();
    private ArrayList<String> gL50_list = new ArrayList<>();
    private ArrayList<String> id_exp_list = new ArrayList<>();


    public GuestPresenter(GuestState state) {
        table1Data = new Table1Data("", "", "", "");
        table2Data = new Table2Data(cell_line_list, gL50_list, id_exp_list);
        viewModel = state;
    }

    /**
     * Method that is linked with the model to read data from database and set the values retrieved
     * to the variables of Table1Data and Table2Data and then pass them to the router
     */
    @Override
    public void callReadData() {
        model.readData(table1Data, table2Data, new Contract.DataCallback() {
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
    public void callGetId() {
        model.getID(table1Data, new Contract.getIDCallback() {
            @Override
            public void getIDError(boolean error, String userID) {
                if (!error){
                    table1Data.setUserIdBiolab(userID);
                    table1Data.setNumIdBiolab(view.get().getID());
                }
            }
        });
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
