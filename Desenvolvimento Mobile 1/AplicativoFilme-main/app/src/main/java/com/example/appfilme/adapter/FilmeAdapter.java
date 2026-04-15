package com.example.appfilme.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appfilme.R;
import com.example.appfilme.database.DBHelper;
import com.example.appfilme.model.Filme;
import com.example.appfilme.uii.DetalhesActivity;

import java.util.List;

public class FilmeAdapter extends RecyclerView.Adapter<FilmeAdapter.ViewHolder> {

    private List<Filme> lista;
    private Context context;

    public FilmeAdapter(Context context, List<Filme> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_filme, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Filme filme = lista.get(position);


        holder.titulo.setText(filme.title);

        String notaFormatada = String.format(java.util.Locale.US, "%.2f", filme.vote_average);
        holder.txtNota.setText("Nota: " + notaFormatada);

        holder.itemView.setOnLongClickListener(v -> {

            DBHelper db = new DBHelper(context);
            db.deletar(filme.title);

            lista.remove(position);
            notifyItemRemoved(position);

            return true;
        });


        String url = "https://image.tmdb.org/t/p/w500" + filme.poster_path;

        Glide.with(context)
                .load(url)
                .into(holder.imgPoster);


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetalhesActivity.class);
            intent.putExtra("titulo", filme.title);
            intent.putExtra("sinopse", filme.overview);
            intent.putExtra("nota", filme.vote_average);
            intent.putExtra("poster", filme.poster_path);

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titulo;
        ImageView imgPoster;
        TextView txtNota;

        public ViewHolder(View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.txtTitulo);
            imgPoster = itemView.findViewById(R.id.imgPoster);
            txtNota = itemView.findViewById(R.id.txtNota);
        }
    }
}