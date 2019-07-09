package vostore.karaoquebar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.Normalizer;
import java.util.regex.Pattern;

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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        final MyViewHolder itemHolder;
        itemHolder = holder;

        holder.id.setText(Experts.get(position).getId());
        holder.cantor.setText(Experts.get(position).getCantor());
        holder.nomeMusica.setText(Experts.get(position).getNomeMusica());

        holder.itemView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(itemHolder.itemView.getContext(), MapActivity.class);
                Bundle b = new Bundle();

                //get text for current item

                MusicasNacionais letras = new MusicasNacionais();
                String cantor = "";
                String musica = "";

                cantor = Experts.get(position).getCantor();
                musica = Experts.get(position).getNomeMusica();


                String site = "https://www.letras.mus.br/"+cantor+"/"+musica;
                String site2 = deAccent(site);

//                Toast.makeText(context, "Clicado em "+ site, Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(itemHolder.itemView.getContext(),SiteLetras.class);
                intent.putExtra("site",site2);

                itemHolder.itemView.getContext().startActivity(intent);

            }
        });

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
    public static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
    }

