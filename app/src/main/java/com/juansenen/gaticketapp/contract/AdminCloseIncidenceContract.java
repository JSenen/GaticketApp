package com.juansenen.gaticketapp.contract;

import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.domain.IncidencesHistory;
import com.juansenen.gaticketapp.presenter.AdminCloseIncidencePresenter;

public interface AdminCloseIncidenceContract {

    interface model {
        interface modelHisotryListener{
            void getDataIncidenceSearch(Incidences incidence);
            void saveToApiHistory();
            void deleteAfterSave(String message);
        }
        void makeRequestIncidence(modelHisotryListener listener, String incidenceId);
        void saveIncidenceHisotry(modelHisotryListener listener, IncidencesHistory incidencesHistory);
        void deleteIncidenceSaved(modelHisotryListener listener, String incidenceId);

    }
    interface presenter {

    }
    interface view {
        void getDataIncidence(Incidences incidence);
        void showSnackBar(String message);
    }
}
