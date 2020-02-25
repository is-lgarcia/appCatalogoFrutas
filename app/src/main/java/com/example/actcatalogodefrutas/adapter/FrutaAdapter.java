package com.example.actcatalogodefrutas.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actcatalogodefrutas.R;
import com.example.actcatalogodefrutas.servicio.Fruta;

import java.util.List;

public class FrutaAdapter extends RecyclerView.Adapter<FrutaAdapter.FrutaViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(FrutaViewHolder holder, int posicion);
    }

    private OnItemClickListener listener;

    public class FrutaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtTitulo, txtDescripcion;
        ImageView imagenTipo;

        public FrutaViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitulo = itemView.findViewById(R.id.txt_titulo);
            txtDescripcion = itemView.findViewById(R.id.txt_descripción);
            imagenTipo = itemView.findViewById(R.id.img_tipo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(this, getAdapterPosition());
        }
    }

    private List<Fruta> frutas;
    private Activity activity;

    public FrutaAdapter(Activity activity, List<Fruta> frutas, OnItemClickListener listener) {
        this.activity = activity;
        this.frutas = frutas;
        this.listener = listener;
    }

    public List<Fruta> getFrutas() {
        return frutas;
    }

    @NonNull
    @Override
    public FrutaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fruta_item_template, parent, false);
        return new FrutaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FrutaViewHolder holder, int position) {
        holder.txtTitulo.setText(getFrutas().get(position).getNombre());
        holder.txtDescripcion.setText(getFrutas().get(position).getDescripcion());
        CardView cardView = holder.itemView.findViewById(R.id.item_list);
        switch (getFrutas().get(position).getTipo()) {
            case SECA:
                cardView.setCardBackgroundColor(ContextCompat.getColor(activity, R.color.colorFrutasSecas));
                holder.imagenTipo.setImageResource(R.drawable.frutos_secos);
                break;
            case TROPICAL:
                cardView.setCardBackgroundColor(ContextCompat.getColor(activity, R.color.colorFrutaTropical));
                holder.imagenTipo.setImageResource(R.drawable.tropicales);
                break;
            case CITRICA:
                cardView.setCardBackgroundColor(ContextCompat.getColor(activity, R.color.colorFrutaCitrica));
                holder.imagenTipo.setImageResource(R.drawable.citricos);
                break;
            case DELBOSQUE:
                cardView.setCardBackgroundColor(ContextCompat.getColor(activity, R.color.colorFrutaDelBosque));
                holder.imagenTipo.setImageResource(R.drawable.bosque);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return frutas.size();
    }


}
