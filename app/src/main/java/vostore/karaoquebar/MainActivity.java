package vostore.karaoquebar;

import android.content.Intent;
import android.net.Uri;
import android.provider.AlarmClock;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnBuscar;
    private Button expertsNacionais, expertsInternacionais, btnVoltar;
    private LinearLayout linearBuscarPalestrante;
    private EditText buscarPalestrante;
    private Button buscar;
    private RecyclerView mResultList;
    private DatabaseReference reference, reference2;
    RecyclerView recyclerView, recyclerView2;
    final int ITEM_LOAD_COUNT = 21;
    int total_item = 0, last_visible_item;

    Button btn_home, btn_voltar;
    ArrayList<MusicasNacionais> list;
    ArrayList<MusicasNacionais> list2;
    MyAdapterNacionais adapter;
    MyAdapterNacionais adapter2;
    boolean isLoading = false, isMaxData = false;

    String last_node = "", last_key = "";
    LinearLayoutManager layoutManager;
    String nomeparaBuscar;


    private EditText btnBuscarTodas, buscarEdit;
    private Button btnCatalogoNacional, btnCatalogoInternacional, btnTelefone, btnRedesSociais, btnLocalizacao;
    private LinearLayout linearMenu, linearTodas;
    private RelativeLayout linearBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainn);


        recyclerView2 = (RecyclerView) findViewById(R.id.id_recycler2);
        buscarEdit = findViewById(R.id.buscar_id);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));


        //Cast
        btnCatalogoInternacional = findViewById(R.id.btn_internacional_id);
        btnCatalogoNacional = findViewById(R.id.btn_nacional_id);
        btnTelefone = findViewById(R.id.btn_telefone_id);
        btnRedesSociais = findViewById(R.id.btn_redes_sociais_id);
        btnLocalizacao = findViewById(R.id.btn_localizacao);
        linearMenu = findViewById(R.id.linear_menu);
        linearTodas = findViewById(R.id.linear_todas_musicas);
        linearBackground = findViewById(R.id.linearBackground);


        //Onclick9

        //Fazendo cast dos botões Experts

        buscarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference2 = FirebaseDatabase.getInstance().getReference().child("AllMusic");
                Query query1 = reference2;
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
                                if (buscarEdit.getText().toString().length() > 0) {
                                    linearMenu.setVisibility(View.GONE);
                                    linearTodas.setVisibility(View.VISIBLE);

                                    nomeparaBuscar = buscarEdit.getText().toString();
                                    String up = nomeparaBuscar.toUpperCase();

                                    Query query2 = reference2.orderByChild("nomeMusica").startAt(up);

                                    query2.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            list2 = new ArrayList<MusicasNacionais>();
                                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                                MusicasNacionais p = dataSnapshot1.getValue(MusicasNacionais.class);
                                                list2.add(p);
                                            }

                                            adapter2 = new MyAdapterNacionais(MainActivity.this, list2);
                                            recyclerView2.setAdapter(adapter2);


                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                } else {
                                    linearMenu.setVisibility(View.VISIBLE);
                                    linearTodas.setVisibility(View.GONE);
                                }
                            }


                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(MainActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnCatalogoNacional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MusicaNacional.class);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });
        btnCatalogoInternacional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MusicaInternacional.class);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        btnLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Maps.class);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
        btnTelefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertContact("Karaoke Bar", "karaokebar@gmail.com");

            }
        });
        btnRedesSociais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RedesSociais.class);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });


    }

    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void insertContact(String name, String email) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, "4132251463");
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
