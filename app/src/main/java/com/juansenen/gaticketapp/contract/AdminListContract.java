package com.juansenen.gaticketapp.contract;

import com.juansenen.gaticketapp.domain.Incidences;

import java.util.List;

public interface AdminListContract {

    interface model {
        interface loadIncidencesListener{
            void onLoadIncidencesSuccess(List<Incidences> incidencesList);
            void onLoadIncidencesError(String message);

        }
        void loadIncidencesAdmin(loadIncidencesListener listener, String userId);

    }
    interface presenter {
        void loadAllIncidences();
    }
    interface view {
        void showIncidencesAdmin(List<Incidences> incidencesList);

        void showSnackBar(String message);
    }
}
