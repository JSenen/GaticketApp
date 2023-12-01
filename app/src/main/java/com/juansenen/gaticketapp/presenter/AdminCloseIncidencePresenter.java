package com.juansenen.gaticketapp.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.juansenen.gaticketapp.contract.AdminCloseIncidenceContract;
import com.juansenen.gaticketapp.contract.AdminDetailContract;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.domain.IncidencesHistory;
import com.juansenen.gaticketapp.model.AdminCloseIncidenceModel;
import com.juansenen.gaticketapp.view.AdminCloseIncidenceAcivity;

public class AdminCloseIncidencePresenter implements AdminCloseIncidenceContract.presenter, AdminCloseIncidenceContract.model.modelHisotryListener {

    private AdminCloseIncidenceAcivity view;
    private AdminCloseIncidenceModel model;
    private Context context;

    public AdminCloseIncidencePresenter (AdminCloseIncidenceAcivity view, Context context){
        this.view = view;
        this.context = context;
        this.model = new AdminCloseIncidenceModel();
    }

    public void requestIncidence(String incidenceId) {
        Log.d("TAG","Presenter call model ");
        model.makeRequestIncidence(this, incidenceId);

    }


    public void saveIncidence(IncidencesHistory incidenceToSave) {
        Log.d("TAG","Presenter call model saveIncidenceHisotry  "+incidenceToSave);
        model.saveIncidenceHisotry(this, incidenceToSave);
    }


    @Override
    public void getDataIncidenceSearch(Incidences incidence) {
        view.getDataIncidence(incidence);
    }

    @Override
    public void saveToApiHistory() {

    }

    @Override
    public void deleteAfterSave(String message) {

        view.showSnackBar(message);
    }

    public void deleteIncidence(String incidenceId) {
        Log.d("TAG","Presenter call model deleteIncidenceSaved id= "+incidenceId);
        model.deleteIncidenceSaved(this, incidenceId);
    }

}
