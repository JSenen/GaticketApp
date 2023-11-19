package com.juansenen.gaticketapp.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.juansenen.gaticketapp.contract.AdminListContract;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.model.AdminListModel;
import com.juansenen.gaticketapp.view.AdminListActivity;

import java.util.List;

public class AdminListPresenter implements AdminListContract.presenter, AdminListContract.model.loadIncidencesListener {

    private AdminListActivity view;
    private Context context;
    private AdminListModel model;

    public AdminListPresenter(AdminListActivity view, Context context){
        this.view = view;
        this.context = context;
        this.model = new AdminListModel();
    }

    public void loadAllIncidences() {
        //recuperamos el user_id para enviarlo
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id","");

        model.loadIncidencesAdmin(this,userId);
    }

    @Override
    public void onLoadIncidencesSuccess(List<Incidences> incidencesList) {
        view.showIncidencesAdmin(incidencesList);
    }

    @Override
    public void onLoadIncidencesError(String message) {
        view.showSnackBar(message);

    }
}
