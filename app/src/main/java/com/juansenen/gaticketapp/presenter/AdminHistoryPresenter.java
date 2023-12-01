package com.juansenen.gaticketapp.presenter;

import android.content.Context;

import com.juansenen.gaticketapp.contract.AdminHistoryContract;
import com.juansenen.gaticketapp.domain.IncidencesHistory;
import com.juansenen.gaticketapp.model.AdminHistoryModel;
import com.juansenen.gaticketapp.view.AdminHistoryActivity;

import java.util.List;

public class AdminHistoryPresenter implements AdminHistoryContract.presenter, AdminHistoryContract.model.historyListener {

    private AdminHistoryModel model;
    private AdminHistoryActivity view;
    private Context context;

    public AdminHistoryPresenter(AdminHistoryActivity view, Context context){
        this.view = view;
        this.context = context;
        this.model = new AdminHistoryModel();
    }

    public void loadAllHistory() {
        model.loadHistory(this);
    }

    @Override
    public void onLoadHistory(List<IncidencesHistory> incidencesHistoryList) {
        view.showIncidencesList(incidencesHistoryList);

    }
}
