package com.juansenen.gaticketapp.contract;

import com.juansenen.gaticketapp.domain.Messages;
import com.juansenen.gaticketapp.domain.RespuestaModel;

import java.util.List;

public interface UserDetailContract {
    interface model {
    
        interface listenerMessages{
            void onSuccess(List<Messages> messages);
            void onError();
            void sendOK(Messages messages);
            void sendFault();
        }
        void getMessagesIncidence(listenerMessages listener, long incidenceId);
        void sendUserMessage(listenerMessages listener,long userId, long incidenceId, Messages messageBody);


    }
    interface presenter {

    }
    interface view {
        void showAllMessages(List<Messages> messages);

        void sendMenssageOk();
    }
}
