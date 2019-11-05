package com.jdpadron98.biolabapp.Table1;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jdpadron98.biolabapp.CardViewAdapter;
import com.jdpadron98.biolabapp.CardViewItem;
import com.jdpadron98.biolabapp.Table1Data;
import com.jdpadron98.biolabapp.R;


import java.io.Serializable;
import java.util.ArrayList;

public class Table1Activity
        extends AppCompatActivity implements Table1Contract.View, Serializable {

    public static String TAG = Table1Activity.class.getSimpleName();

    private WebView wv1;

    private Table1Contract.Presenter presenter;

    private String id_biolab;
    private ArrayList<CardViewItem> cardViewItems;
    private ArrayList<String> arrayList;
    private ArrayList<String> cell_line_list, gL50_list, id_exp_list;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CardViewAdapter mAdapter;


    private Table1Data table1Data;

    private TextView id_biolab_tv, id_sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table1);

        /**
         * Here we retrieve data from the previous activity in the form of arrayList and then its stored in an ArrayList.
         * Data corresponds to the schema:
         * ID_Sample = table1Data.get(0)
         * Smiles = table1Data.get(1)
         * Soluble = table1Data.get(2)
         * Notes = table1Data.get(3)
         * Then all this values are stored in a class object of Table1Data for more comfortable using
         */

        Intent intent = getIntent();
        //ID Biolab
        id_biolab = intent.getStringExtra("id_biolab");
        //Data of table1
        arrayList = intent.getStringArrayListExtra("T1Data");
        //Data of table2
        cell_line_list = intent.getStringArrayListExtra("cell_line_list");
        gL50_list = intent.getStringArrayListExtra("gL50_list");
        id_exp_list = intent.getStringArrayListExtra("id_exp_list");
        //////////////////////////////////////////////////////////////////
        createList();
        buildRecyclerView();
        filterData();

        id_biolab_tv = findViewById(R.id.idbiolabtv);
        id_sample = findViewById(R.id.sample_tv);
        wv1 = findViewById(R.id.moleculeview);

        wv1.getSettings().setBuiltInZoomControls(true);
        table1Data = new Table1Data(arrayList.get(0), arrayList.get(1), arrayList.get(2), arrayList.get(3));
        id_biolab_tv.setText(id_biolab);
        id_sample.setText(table1Data.getId_sample());

        drawStructure();


        // do the setup
        Table1Screen.configure(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // load the data
        //presenter.fetchData();
    }

    @Override
    public void displayData(Table1ViewModel viewModel) {
        //Log.e(TAG, "displayData()");

        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }

    @Override
    public void injectPresenter(Table1Contract.Presenter presenter) {
        this.presenter = presenter;
    }


    /**
     * Method that creates a new webclient and draw the structure of molecule by adding the smiles
     * to the link
     */
    public void drawStructure() {
        wv1.setWebViewClient(new WebViewClient()); //To open the webpage on our APP
        wv1.loadUrl("https://cactus.nci.nih.gov/chemical/structure/" + table1Data.getSmiles() + "/image");


    }

    /**
     * Method that builds the recylcler view
     */

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.listview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CardViewAdapter(cardViewItems);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CardViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Table1Activity.this);
                View mView = getLayoutInflater().inflate(R.layout.custom_data_dialog, null);

                final TextView mcell_line = mView.findViewById(R.id.cell_line_d);
                final TextView mGl50 = mView.findViewById(R.id.gl50_d);
                final TextView soluble = mView.findViewById(R.id.soluble_d);
                final TextView notes = mView.findViewById(R.id.notes_d);
                final TextView id_exp = mView.findViewById(R.id.id_exp_d);

                mcell_line.setText(cell_line_list.get(position));
                mGl50.setText(gL50_list.get(position));
                soluble.setText(table1Data.getSoluble());
                notes.setText(table1Data.getNotes());
                id_exp.setText(id_exp_list.get(position));


                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }

    /**
     * Method that creates a list with the CardViewItems
     */

    public void createList() {
        cardViewItems = new ArrayList<>();
        for (int i = 0; i < cell_line_list.size(); i++) {
            cardViewItems.add(new CardViewItem(cell_line_list.get(i), gL50_list.get(i)));
        }
    }

    /**
     * Method that filters the data of the recycler view by passing a cellLine number
     * @return a bool that says if the filtering was okay or not
     */
    public boolean filterData() {

        SearchView searchView = findViewById(R.id.searchView2);

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setQueryHint("Insert Cell Line");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
