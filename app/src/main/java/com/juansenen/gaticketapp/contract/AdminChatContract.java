package com.juansenen.gaticketapp.contract;

import com.juansenen.gaticketapp.domain.ChatRequest;
import com.juansenen.gaticketapp.domain.RespuestaModel;

public interface AdminChatContract {

    interface model {
        interface OnChatListener {
            void onChatFinish(String contenido);
            void onChatError(String message);
        }
        void talkToChatGPT(OnChatListener listener, ChatRequest chatRequest);
    }
    interface presenter {

    }
    interface view {

        void showResponse(String response);
    }
}
