package vostore.karaoquebar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;



public class MyAdapterNacionais extends RecyclerView.Adapter<MyAdapterNacionais.MyViewHolder> {

    Context context;
    ArrayList<MusicasNacionais> Experts;

    public MyAdapterNacionais(Context c , ArrayList<MusicasNacionais> p)
    {
        context = c;
        Experts = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_musicas,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(Experts.get(position).getId());
        holder.cantor.setText(Experts.get(position).getCantor());
        holder.nomeMusica.setText(Experts.get(position).getNomeMusica());


    }

    @Override
    public int getItemCount() {
        return Experts.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView cantor,nomeMusica,id;
        ImageView profilePic;
        Button btn;
        public MyViewHolder(View itemView) {
            super(itemView);
            nomeMusica = (TextView) itemView.findViewById(R.id.musica_id);
            cantor = (TextView) itemView.findViewById(R.id.cantor_id);
            id = (TextView) itemView.findViewById(R.id.cod_id);
           // btn = (Button) itemView.findViewById(R.id.checkDetails);
        }

        }
    }

