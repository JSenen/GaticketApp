package com.juansenen.gaticketapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juansenen.gaticketapp.R;
import com.juansenen.gaticketapp.domain.DataToTransfer;
import com.juansenen.gaticketapp.domain.Incidences;
import com.juansenen.gaticketapp.domain.IncidencesHistory;
import com.juansenen.gaticketapp.view.AdminHistoryDetail;

import java.util.List;

public class AdminHistoryAdapter extends RecyclerView.Adapter<AdminHistoryAdapter.AdminHistoryHolder> {
    private List<IncidencesHistory> incidencesList;
    private Context context;

    public AdminHistoryAdapter(Context context,List<IncidencesHistory> incidencesList){
        this.incidencesList = incidencesList;
        this.context = context;
    }
    @Override
    public AdminHistoryAdapter.AdminHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_admin_history_item, parent, false);
        return new AdminHistoryAdapter.AdminHistoryHolder(view);
    }
    @Override
    public void onBindViewHolder(AdminHistoryAdapter.AdminHistoryHolder holder, int position) {
        holder.theme.setText(incidencesList.get(position).getHistoryTheme());
        holder.commit.setText(incidencesList.get(position).getHistoryCommit());
        holder.date.setText(incidencesList.get(position).getHistoryDateFinish());
    }
    @Override
    public int getItemCount() {
        return incidencesList.size();
    }

    public class AdminHistoryHolder extends RecyclerView.ViewHolder {
        public TextView theme, date, commit;
        public ImageButton buttonDetail;
        public View parentView;


        public AdminHistoryHolder(View view) {

            super(view);
            parentView = view;

            theme = view.findViewById(R.id.txt_history_theme);
            date = view.findViewById(R.id.txt_history_date);
            commit = view.findViewById(R.id.txt_history_commit);

            buttonDetail = view.findViewById(R.id.button_detail_history);
            buttonDetail.setOnClickListener( see -> seeDetailHistory(getAdapterPosition()));

        }

        private void seeDetailHistory(int adapterPosition) {

            //Obtenemos la clase para transferir datos
            DataToTransfer dataToTransfer = DataToTransfer.getInstance();
            //Rellenamos con los datos
            dataToTransfer.setTheme(incidencesList.get(adapterPosition).getHistoryTheme().toString());
            dataToTransfer.setCommit(incidencesList.get(adapterPosition).getHistoryCommit().toString());
            dataToTransfer.setDate(incidencesList.get(adapterPosition).getHistoryDateFinish().toString());
            dataToTransfer.setSolution(incidencesList.get(adapterPosition).getHistorySolution().toString());
            dataToTransfer.setUser(incidencesList.get(adapterPosition).getHistoryTip().toString());

            Intent intent = new Intent(context, AdminHistoryDetail.class);
            context.startActivity(intent);

        }
    }
}
