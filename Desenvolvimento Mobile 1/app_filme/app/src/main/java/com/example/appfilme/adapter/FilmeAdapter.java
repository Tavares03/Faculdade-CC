package com.example.appfilme.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appfilme.R;
import com.example.appfilme.database.DBHelper;
import com.example.appfilme.model.Filme;
import com.example.appfilme.model.Genero;
import com.example.appfilme.uii.DetalhesActivity;

import java.util.List;
import java.util.Map;

public class FilmeAdapter extends RecyclerView.Adapter<FilmeAdapter.ViewHolder> {

    private Context context;
    private List<Filme> lista;
    private boolean modoFavoritos;
    private Map<Integer, String> mapaGeneros;

    public FilmeAdapter(Context context, List<Filme> lista) {
        this.context = context;
        this.lista = lista;
        this.modoFavoritos = false;
    }

    public FilmeAdapter(Context context, List<Filme> lista, boolean modoFavoritos) {
        this.context = context;
        this.lista = lista;
        this.modoFavoritos = modoFavoritos;
    }

    public void setMapaGeneros(Map<Integer, String> mapa) {
        this.mapaGeneros = mapa;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_filme, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Filme filme = lista.get(position);

        holder.txtTitulo.setText(filme.title);
        holder.txtNota.setText(String.format("%.1f", filme.vote_average));

        // Gêneros
        if (holder.txtGeneros != null && mapaGeneros != null && filme.genre_ids != null && !filme.genre_ids.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for (Integer gid : filme.genre_ids) {
                if (count >= 2) break; // máximo 2 gêneros no card
                String nome = mapaGeneros.get(gid);
                if (nome != null) {
                    if (sb.length() > 0) sb.append(" • ");
                    sb.append(nome);
                    count++;
                }
            }
            holder.txtGeneros.setText(sb.toString());
            holder.txtGeneros.setVisibility(sb.length() > 0 ? View.VISIBLE : View.GONE);
        } else if (holder.txtGeneros != null) {
            holder.txtGeneros.setVisibility(View.GONE);
        }

        if (filme.poster_path != null) {
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w185" + filme.poster_path)
                    .into(holder.imgPoster);
        }

        if (modoFavoritos && holder.btnExcluir != null) {
            holder.btnExcluir.setVisibility(View.VISIBLE);
            holder.btnExcluir.setOnClickListener(v -> {
                int pos = holder.getAdapterPosition();
                if (pos == RecyclerView.NO_ID) return;

                new AlertDialog.Builder(context)
                        .setTitle("Remover favorito")
                        .setMessage("Deseja remover \"" + filme.title + "\" dos favoritos?")
                        .setPositiveButton("Remover", (dialog, which) -> {
                            DBHelper db = new DBHelper(context);
                            db.deletar(filme.title);
                            lista.remove(pos);
                            notifyItemRemoved(pos);
                            notifyItemRangeChanged(pos, lista.size());
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();
            });
        } else if (holder.btnExcluir != null) {
            holder.btnExcluir.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetalhesActivity.class);
            intent.putExtra("id", filme.id);
            intent.putExtra("titulo", filme.title);
            intent.putExtra("sinopse", filme.overview);
            intent.putExtra("nota", filme.vote_average);
            intent.putExtra("poster", filme.poster_path);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return lista != null ? lista.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo, txtNota, txtGeneros;
        ImageView imgPoster;
        Button btnExcluir;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            txtNota = itemView.findViewById(R.id.txtNota);
            txtGeneros = itemView.findViewById(R.id.txtGeneros);
            imgPoster = itemView.findViewById(R.id.imgPoster);
            btnExcluir = itemView.findViewById(R.id.btnExcluir);
        }
    }
}