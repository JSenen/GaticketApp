package com.juansenen.gaticketapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.juansenen.gaticketapp.R;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.view.UserDetailActivity;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListHolder> {
    private List<Incidences> incidences;
    private Context context;

    public UserListAdapter(Context context, List<Incidences> incidences){
        this.context = context;
        this.incidences = incidences;
    }

    @Override
    public UserListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_user_list_item, parent, false);
        return new UserListHolder(view);
    }
    @Override
    public void onBindViewHolder(UserListHolder holder, int position) {
        holder.incidenceTheme.setText(incidences.get(position).getIncidenceTheme());
        holder.incidendeDate.setText(incidences.get(position).getIncidenceDate());
        holder.incidenceCommit.setText(incidences.get(position).getIncidenceCommit());
        // Configuración dinámica del estado
        configureState(holder, incidences.get(position));
    }

    /**
     * Configura el icono y color de texto del estado segun active o process
     * @param holder
     * @param incidences La incidencia
     */
    private void configureState(UserListHolder holder, Incidences incidences) {
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
        return incidences.size();
    }

    public class UserListHolder extends RecyclerView.ViewHolder {
        public TextView incidenceTheme, incidendeDate, incidenceStatus, incidenceCommit;
        public View parentView;
        public Button seeIncidence;

        public UserListHolder(View view){

            super(view);
            parentView = view;

            incidenceTheme = view.findViewById(R.id.txt_incidenceuser_theme);
            incidendeDate = view.findViewById(R.id.txt_incidenceuser_date);
            incidenceStatus = view.findViewById(R.id.txt_incidenceuser_status);
            incidenceCommit = view.findViewById(R.id.txt_incidenceuser_commit);

            seeIncidence = view.findViewById(R.id.button_user_seeIncidence);
            seeIncidence.setOnClickListener(see -> seeIncidence(getAdapterPosition()));
        }
    }

    private void seeIncidence(int adapterPosition){
        Incidences incidencesToSee = incidences.get(adapterPosition);
        long idIncidence = incidencesToSee.getIncidencesId();
        String incidenceTheme = incidencesToSee.getIncidenceTheme();
        String incidenceCommit = incidencesToSee.getIncidenceCommit();

        //Pasamos los datos de la incidencie a la activity
        Intent intent = new Intent(context, UserDetailActivity.class);
        intent.putExtra("idIncidence", idIncidence);
        intent.putExtra("incidenceTheme",incidenceTheme);
        intent.putExtra("incidenceCommit",incidenceCommit);
        context.startActivity(intent);


    }

}
