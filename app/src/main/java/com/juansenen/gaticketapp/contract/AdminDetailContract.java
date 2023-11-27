package com.juansenen.gaticketapp.contract;

import com.juansenen.gaticketapp.domain.Department;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.presenter.AdminDetailPresenter;

public interface AdminDetailContract {

    interface model{


        interface changeIncidencesListener {
            void changeStatusOK();
            void changeAdminOK();
            void getDataIncidence(Incidences incidence);
            void getDepartmentDat(Department department);
        }
        void changeStatus(changeIncidencesListener listener, long idIncidence, Incidences incidencesBody);
        void changeAdmin(changeIncidencesListener listener, long idIncidence, Incidences incidenceBody);
        void getDataIncidence(changeIncidencesListener listener, long incidenceId);

        void getDepartmentofUSer(changeIncidencesListener listener, long userId);

    }
    interface view {
        void showDataIncidence(Incidences incidence);
        void showDataDepartment(Department department);
    }
    interface presenter {
        void changeStatusIncidence(long incidenceId);
        void changeAdminIncidence(long incidenceId);
        void getDataIncidence(long incidenceId);

        void requestDepartmentUser(long userId);
    }
}
