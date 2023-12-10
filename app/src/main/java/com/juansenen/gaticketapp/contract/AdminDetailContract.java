package com.juansenen.gaticketapp.contract;

import com.google.android.material.textfield.TextInputEditText;
import com.juansenen.gaticketapp.domain.Department;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.domain.Messages;
import com.juansenen.gaticketapp.presenter.AdminDetailPresenter;

import java.util.List;

public interface AdminDetailContract {

    interface model{
        interface changeIncidencesListener {
            void changeStatusOK();
            void changeAdminOK();
            void getDataIncidence(Incidences incidence);
            void getDepartmentDat(Department department);
            void getMessagesOK(List<Messages> messagesList);
            void sendMessageToApi(Messages messageBody);
            void incidenceActiveAgain();

        }
        void changeStatus(changeIncidencesListener listener, long idIncidence, Incidences incidencesBody);
        void changeAdmin(changeIncidencesListener listener, long idIncidence, Incidences incidenceBody);
        void getDataIncidence(changeIncidencesListener listener, long incidenceId);
        void getDepartmentofUSer(changeIncidencesListener listener, long userId);
        void getAllMessages(changeIncidencesListener listener, long idIncidence);
        void sendMessage(changeIncidencesListener listener, int adminId, long incidenceId, Messages messageBody);
        void reactivate(changeIncidencesListener listener, long incidenceId, Incidences incidenceBody);

    }
    interface view {
        void showDataIncidence(Incidences incidence);
        void showDataDepartment(Department department);
        void showAllMessages(List<Messages> messagesList);
        void messageSendOk();

        void changeIncidence();
    }
    interface presenter {
        void changeStatusIncidence(long incidenceId);
        void changeAdminIncidence(long incidenceId);
        void getDataIncidence(long incidenceId);

        void requestDepartmentUser(long userId);
        
    }
}
