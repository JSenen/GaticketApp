package com.juansenen.gaticketapp.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.juansenen.gaticketapp.contract.AdminDetailContract;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.model.AdminDetailModel;
import com.juansenen.gaticketapp.view.AdminDetailActivity;

public class AdminDetailPresenter implements AdminDetailContract.presenter, AdminDetailContract.model.changeIncidencesListener {

    private AdminDetailActivity view;
    private Context context;
    private AdminDetailModel model;

    public AdminDetailPresenter(AdminDetailActivity view, Context context){
        this.view = view;
        this.context = context;
        this.model = new AdminDetailModel();

    }
    public void changeStatusIncidence(long incidenceId, Incidences incidenceBody){
        model.changeStatus(this, incidenceId, incidenceBody);
    }
    @Override
    public void changeStatusOK() {}
    @Override
    public void changeAdminOK() {}

    @Override
    public void getDataIncidence(Incidences incidence) {
        view.showDataIncidence(incidence);
    }


    @Override
    public void changeStatusIncidence(long incidenceId) {

    }

    @Override
    public void changeAdminIncidence(long incidenceId) {

    }

    public void changeAdminIncidence(long incidenceId,Incidences incidenceBody) {

        model.changeAdmin(this, incidenceId, incidenceBody);
    }

    public void getDataIncidence(long incidenceId) {
        model.getDataIncidence(this, incidenceId);
    }
}
