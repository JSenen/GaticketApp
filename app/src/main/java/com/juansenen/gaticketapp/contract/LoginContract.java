package com.juansenen.gaticketapp.contract;

import com.juansenen.gaticketapp.domain.User;

import java.util.List;

public interface LoginContract {

    interface Model{
        interface OnLoadLinesListener{
            void onLoadUserSuccess(User user);
            void onLoadUserError(String message);
        }
        void loadUser(OnLoadLinesListener listener, String userTip, String userPass);
    }
    interface View{
        void showSnckBar(String message);

    }
    interface Presenter{
        void login(String username, String passwrd);
    }
}
