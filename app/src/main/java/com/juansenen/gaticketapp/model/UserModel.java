package com.juansenen.gaticketapp.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.juansenen.gaticketapp.api.GaticketApi;
import com.juansenen.gaticketapp.api.GaticketApiInterface;
import com.juansenen.gaticketapp.contract.UserContract;
import com.juansenen.gaticketapp.domain.Device;
import com.juansenen.gaticketapp.domain.Incidences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserModel implements UserContract.model {
    /**
     * Buscar dispositivo
     *
     * @param selectedOption Opcion selecciona en el dropdownitem de la pantalla del usuario
     * @param selectedText Texto que contiene el dropdown item
     * @param callback Llamada a la API
     */
    @Override
    public void findDevice(String selectedOption, String selectedText, UserContract.DeviceCallback callback) {
        GaticketApiInterface gaticketApiInterface = GaticketApi.buildInstancce();
        Call<List<Device>> callDevice = gaticketApiInterface.getDeviceBySerial(selectedText);

        if (selectedOption.equals("Numero de serie")) {
            Log.d("TAG", "loadDevice: Haciendo llamada a la API device S/N " + selectedText);
            //Pasamos el serial number
            callDevice = gaticketApiInterface.getDeviceBySerial(selectedText);
        } else if (selectedOption.equals("MAC")) {
            Log.d("TAG", "loadDevice: Haciendo llamada a la API device MAC " + selectedText);
            //Pasamos la MAC
            callDevice = gaticketApiInterface.getDeviceByMac(selectedText);

        } else if (selectedOption.equals("---")) {
            //Pasamos un dato nulo
            selectedText = "null";
            callDevice = gaticketApiInterface.getDeviceByMac(selectedText);

        }
        callDevice.enqueue(new Callback<List<Device>>() {
                @Override
                public void onResponse(Call<List<Device>> call, Response<List<Device>> response) {
                    if (response.isSuccessful()) {
                        List<Device> deviceList = response.body();
                        if (deviceList != null && !deviceList.isEmpty()) {
                            Device deviceSearch = deviceList.get(0);
                            Log.d("TAG", "Device Found: " + deviceSearch);
                            callback.onDeviceFound(deviceSearch);
                        } else {
                            Log.d("TAG", "No devices found in the response");
                            Device deviceSearch = new Device();
                            deviceSearch = null;
                            callback.onDeviceNotFound(deviceSearch);
                        }
                    } else {
                        Log.d("TAG", "API call not successful, code: " + response.code());
                    }
                }


                @Override
                public void onFailure(Call<List<Device>> call, Throwable t) {

                }
            });

    }

    /**
     * Grabar incidencia
     *
     * @param incidenceBody Cuerpo de la incidencia que se va a grabar
     * @param userId Numero Id del usuario
     */
    @Override
    public void saveIncidence(Incidences incidenceBody, String userId, UserContract.SaveIncidenceCallback saveIncidenceCallback) {
        Log.d("TAG", "saveIncidence: Save incidence ");
        Log.d("TAG", "saveIncidence: userId " + userId);

        GaticketApiInterface gaticketApiInterface = GaticketApi.buildInstancce();
        Call<Incidences> saveNewIncidence = gaticketApiInterface.addIncidence(userId,incidenceBody);

        saveNewIncidence.enqueue(new Callback<Incidences>() {
            @Override
            public void onResponse(Call<Incidences> call, Response<Incidences> response) {
                if (response.body() != null ) {
                    Incidences incidences = response.body();
                    String message = "Incidencia enviada";
                    saveIncidenceCallback.onIncidenceSaved(incidences);

                    Log.d("TAG", "device Load: " + response.code());

                }
            }

            @Override
            public void onFailure(Call<Incidences> call, Throwable t) {

            }
        });
    }
}
