package vostore.karaoquebar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class MusicaNacional extends AppCompatActivity {


    private Button btnBuscar;
    private Button expertsNacionais,expertsInternacionais,btnVoltar;
    private LinearLayout linearBuscarPalestrante;
    private EditText buscarPalestrante,buscarEdit;
    private Button buscar;
    private RecyclerView mResultList;
    private DatabaseReference reference,reference2;
    RecyclerView recyclerView,recyclerView2;
    final int ITEM_LOAD_COUNT = 21;
    int total_item = 0, last_visible_item;

    Button btn_home,btn_voltar;
    ArrayList<MusicasNacionais> list;
    ArrayList<MusicasNacionais> list2;
    MyAdapterNacionais adapter;
    MyAdapterNacionais adapter2;
    boolean isLoading = false, isMaxData = false;

    String last_node="",last_key="";
    String nomeparaBuscar;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musica_nacional);

//        btn_home = findViewById(R.id.btn_home);
//        btn_voltar = findViewById(R.id.btn_voltar);
        recyclerView2 = (RecyclerView) findViewById(R.id.id_recycler2);
        buscarEdit = findViewById(R.id.buscar_id);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        //Fazendo cast dos botões Experts


        reference2 = FirebaseDatabase.getInstance().getReference().child("Musica");
        Query query1 = reference2.orderByChild("categoria").equalTo("1");
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {

                buscarEdit.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        String stringBranco = "";
                        if (buscarEdit.getText().length() > 0 )
                        {
                            nomeparaBuscar = buscarEdit.getText().toString();
                            String up =            nomeparaBuscar.toUpperCase();

                            Query query2 = reference2.orderByChild("nomeMusica").startAt(up);

                            query2.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    list2 = new ArrayList<MusicasNacionais>();
                                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                        MusicasNacionais p = dataSnapshot1.getValue(MusicasNacionais.class);
                                        list2.add(p);
                                    }

                                    adapter2 = new MyAdapterNacionais(MusicaNacional.this, list2);
                                    recyclerView2.setAdapter(adapter2);


                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });


                        }
                        else{
                            Toast.makeText(MusicaNacional.this, "Digite o nome da musica nesse campo", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MusicaNacional.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(MusicaNacional.this, MainActivity.class);
        startActivity(intent);
        finish();


    }
}
