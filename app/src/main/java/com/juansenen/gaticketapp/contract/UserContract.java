package com.juansenen.gaticketapp.contract;

import com.juansenen.gaticketapp.domain.Device;
import com.juansenen.gaticketapp.domain.Incidences;

public interface UserContract {

    interface model{
        void findDevice(String selectedOption, String selectedText,DeviceCallback callback);
        void saveIncidence(Incidences incidenceBody, String userId, SaveIncidenceCallback saveIncidenceCallback);
    }
    interface presenter {
        void searchDevice(String selectedOption, String selectedText);
        void createIncidence(Incidences incidenceBody);

    }
    interface view {
        void showSnackBar(String message);
        void onDeviceFound(Device device);
    }

    /**
     * Llamada a la api para buscar dispositivo en caso de seleccionarlo
     */
    interface DeviceCallback {
        void onDeviceFound(Device device);
        void onDeviceNotFound(Device device);
        void onApiError(String errorMessage);
    }
    /**
     * Callback para el resultado del guardado de incidencia
     */
    interface SaveIncidenceCallback {
        void onIncidenceSaved(Incidences incidence);
        void onIncidenceSaveError(String errorMessage);
    }
}
