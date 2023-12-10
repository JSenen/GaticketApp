package com.juansenen.gaticketapp.contract;

import com.juansenen.gaticketapp.domain.IncidencesHistory;

import java.util.List;

public interface AdminHistoryContract {

    interface model{
        interface historyListener{
            void onLoadHistory(List<IncidencesHistory> incidencesHistoryList);
        }
        void loadHistory(historyListener listener);
    }
    interface presenter {

    }
    interface view {
        void showIncidencesList(List<IncidencesHistory> historyList);
    }
}
