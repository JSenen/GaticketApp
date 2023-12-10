package com.juansenen.gaticketapp.contract;

import com.juansenen.gaticketapp.domain.Incidences;

import java.util.List;

public interface UserListContract {

    interface model{
        interface loadIncidencesListener{
            void loadIncidencesSuccess(List<Incidences> incidencesList);
            void loadIncidencesError(String message);
        }
        void loadIncidences(loadIncidencesListener listener, String userId);

    }
    interface presenter{
        void loadAllIncidences();
    }
    interface view {
        void showIncidencesUser(List<Incidences> incidencesList);
    }
}
