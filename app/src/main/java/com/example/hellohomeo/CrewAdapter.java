package com.example.hellohomeo;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.ViewHolder> {
    List<CrewMembersModalClass> crewList = new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View crewView = LayoutInflater.from(parent.getContext()).inflate(R.layout.crew_card_design,parent,false);
        ViewHolder viewHolder = new ViewHolder(crewView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CrewMembersModalClass currentCrew = crewList.get(position);
        holder.crewName.setText(currentCrew.getName());
        holder.wikiLink.setText(currentCrew.getWikipedia());
        holder.agency.setText(currentCrew.getAgency());
        holder.status.setText(currentCrew.getStatus());

        Picasso.get().load(currentCrew.getImage()).fit().into(holder.crewImage);


    }

    public CrewAdapter(List<CrewMembersModalClass> crewList) {
        this.crewList = crewList;
    }

    @Override
    public int getItemCount() {
        return crewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView crewName, agency, status, wikiLink;
        ImageView crewImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            crewName = itemView.findViewById(R.id.crewNameText);
            agency = itemView.findViewById(R.id.agencyText);
            status = itemView.findViewById(R.id.statusText);
            wikiLink = itemView.findViewById(R.id.wikiLinkTextView);
            //wikiLink.setMovementMethod(LinkMovementMethod.getInstance());
            crewImage = itemView.findViewById(R.id.crewImage);
        }
    }
}
