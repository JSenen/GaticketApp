package com.juansenen.gaticketapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.juansenen.gaticketapp.R;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.view.AdminDetailActivity;

import java.util.List;

public class AdminListAdapter extends RecyclerView.Adapter<AdminListAdapter.AdminListHolder> {

    private List<Incidences> incidencesList;
    private Context context;

    public AdminListAdapter(Context context, List<Incidences> incidencesList){
        this.context = context;
        this.incidencesList = incidencesList;
    }
    @Override
    public AdminListAdapter.AdminListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_admin_list_item, parent, false);
        return new AdminListAdapter.AdminListHolder(view);
    }
    @Override
    public void onBindViewHolder(AdminListAdapter.AdminListHolder holder, int position) {
        holder.incidenceTheme.setText(incidencesList.get(position).getIncidenceTheme());
        holder.incidendeDate.setText(incidencesList.get(position).getIncidenceDate());
        holder.incidenceCommit.setText(incidencesList.get(position).getIncidenceCommit());
        // Configuración dinámica del estado
        configureState(holder, incidencesList.get(position));

    }
    /**
     * Configura el icono y color de texto del estado segun active o process
     * @param holder
     * @param incidences La incidencia
     */
    private void configureState(AdminListAdapter.AdminListHolder holder, Incidences incidences) {
        // Determina el estado y configura dinámicamente el icono, color y texto
        if (incidences.getIncidenceStatus().equals("active")) {
            holder.incidenceStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_report_problem_24, 0, 0, 0);
            holder.incidenceStatus.setTextColor(ContextCompat.getColor(context, R.color.status_active));
        } else if (incidences.getIncidenceStatus().equals("process")) {
            holder.incidenceStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_schedule_24, 0, 0, 0);
            holder.incidenceStatus.setTextColor(ContextCompat.getColor(context, R.color.status_in_process));
        }
    }
    @Override
    public int getItemCount() {
        return incidencesList.size();
    }

    public class AdminListHolder extends RecyclerView.ViewHolder {
        public TextView incidenceTheme, incidendeDate, incidenceStatus, incidenceCommit;
        public ImageButton buttonDetail;
        public View parentView;

        public AdminListHolder(View view){

            super(view);
            parentView = view;

            incidenceTheme = view.findViewById(R.id.txt_incidenceadmin_theme);
            incidendeDate = view.findViewById(R.id.txt_incidenceadmin_date);
            incidenceStatus = view.findViewById(R.id.txt_incidenceadmin_status);
            incidenceCommit = view.findViewById(R.id.txt_incidenceadmin_commit);

            //Boton ver detalles incidencia
            buttonDetail = view.findViewById(R.id.button_detail_incidence);
            buttonDetail.setOnClickListener(see -> seeDetailIncidence(getAdapterPosition()));


        }
    }

    private void seeDetailIncidence(int adapterPosition) {
        Incidences incidence = incidencesList.get(adapterPosition);
        //Enviamos a la Activity y le pasamos el id de la incidencia
        Intent intent = new Intent(context, AdminDetailActivity.class);
        intent.putExtra("incidenceId", incidence.getIncidencesId());
        context.startActivity(intent);

    }

}
