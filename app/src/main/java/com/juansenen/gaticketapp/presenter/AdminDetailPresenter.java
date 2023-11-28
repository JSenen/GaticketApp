package com.juansenen.gaticketapp.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.juansenen.gaticketapp.contract.AdminDetailContract;
import com.juansenen.gaticketapp.domain.Department;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.domain.Messages;
import com.juansenen.gaticketapp.model.AdminDetailModel;
import com.juansenen.gaticketapp.view.AdminDetailActivity;

import java.util.List;

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
    public void getDepartmentDat(Department department) {
        view.showDataDepartment(department);
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

    @Override
    public void requestDepartmentUser(long userId) {
        model.getDepartmentofUSer(this, userId);

    }

    public void getMessages(long incidenceId) {
        Log.d("TAG","Presenter call model getAllMessages( "+ incidenceId + ")");
        model.getAllMessages(this,incidenceId);
    }
    @Override
    public void getMessagesOK(List<Messages> messagesList) {
        Log.d("TAG"," Presenter call to view showMessagesIncidence = "+ messagesList);
        view.showMessagesIncidence(messagesList);
    }
}
