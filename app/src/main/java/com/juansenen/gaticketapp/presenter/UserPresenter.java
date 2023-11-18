package com.juansenen.gaticketapp.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.juansenen.gaticketapp.contract.UserContract;
import com.juansenen.gaticketapp.domain.Device;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.domain.User;
import com.juansenen.gaticketapp.model.UserModel;
import com.juansenen.gaticketapp.view.UserMainActivity;

public class UserPresenter implements UserContract.presenter {

    private UserModel model;
    private UserContract.view view;
    private Incidences incidence;
    private Context context;
    private String selectedOption, selectedText;

    public UserPresenter(UserContract.view view,Context context, String selectedOption, String selectedText){
        this.view = view;
        this.selectedOption = selectedOption;
        this.selectedText = selectedText;
        this.context = context;
        this.model = new UserModel();

    }

    @Override
    public void searchDevice(String selectedOption, String selectedText) {
        model.findDevice(selectedOption, selectedText,new UserContract.DeviceCallback() {
            @Override
            public void onDeviceFound(Device device) {
                view.onDeviceFound(device);
            }

            @Override
            public void onDeviceNotFound(Device device) {
                view.onDeviceFound(device);
            }

            @Override
            public void onApiError(String errorMessage) {

            }
        });

    }

    @Override
    public void createIncidence(Incidences incidenceBody) {
        Log.d("TAG", "createIncidence: Creating incidence in presenter");
        //recuperamos el user_id para enviarlo
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserData",Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id","");
        model.saveIncidence(incidenceBody, userId, new UserContract.SaveIncidenceCallback() {
            @Override
            public void onIncidenceSaved(Incidences incidence) {
                Log.d("TAG", "createIncidence: Incidence saved successfully");
                String message = "Incidencia enviada";
                view.showSnackBar(message);
            }

            @Override
            public void onIncidenceSaveError(String errorMessage) {
                Log.d("TAG", "createIncidence: Incidence save error - " + errorMessage);
                String message = "ERROR en el envio de incidencia";
                view.showSnackBar(message);
            }
        });

    }
}
