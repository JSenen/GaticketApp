package com.juansenen.gaticketapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juansenen.gaticketapp.R;
import com.juansenen.gaticketapp.domain.Incidences;

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
        holder.incidenceStatus.setText(incidences.get(position).getIncidenceStatus());
    }

    @Override
    public int getItemCount() {
        return incidences.size();
    }

    public class UserListHolder extends RecyclerView.ViewHolder {
        public TextView incidenceTheme, incidendeDate, incidenceStatus;
        public View parentView;

        public UserListHolder(View view){

            super(view);
            parentView = view;

            incidenceTheme = view.findViewById(R.id.txt_incidenceuser_theme);
            incidendeDate = view.findViewById(R.id.txt_incidenceuser_date);
            incidenceStatus = view.findViewById(R.id.txt_incidenceuser_status);
        }
    }

}
