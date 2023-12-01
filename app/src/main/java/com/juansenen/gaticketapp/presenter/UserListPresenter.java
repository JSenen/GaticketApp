package com.juansenen.gaticketapp.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.juansenen.gaticketapp.contract.UserListContract;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.model.UserListModel;
import com.juansenen.gaticketapp.view.UserListActivity;

import java.util.List;

public class UserListPresenter implements UserListContract.presenter, UserListContract.model.loadIncidencesListener {

    private UserListActivity view;
    private UserListModel model;
    private Context context;

    public UserListPresenter(UserListActivity view, Context context){
        this.view = view;
        this.context = context;
        this.model = new UserListModel();
    }

    public void loadAllIncidences() {
        //recuperamos el user_id para enviarlo
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id","");

        model.loadIncidences(this,userId);
    }

    @Override
    public void loadIncidencesSuccess(List<Incidences> incidencesList) {
        view.showIncidencesUser(incidencesList);
    }

    @Override
    public void loadIncidencesError(String message) {

    }
}
