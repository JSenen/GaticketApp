package com.juansenen.gaticketapp.presenter;

import android.content.Context;

import com.juansenen.gaticketapp.contract.AdminListContract;
import com.juansenen.gaticketapp.model.AdminListModel;
import com.juansenen.gaticketapp.view.AdminListActivity;

public class AdminListPresenter implements AdminListContract.presenter {

    private AdminListActivity view;
    private Context context;
    private AdminListModel model;

    public AdminListPresenter(AdminListActivity view, Context context){
        this.view = view;
        this.context = context;
        this.model = new AdminListModel();
    }
}
