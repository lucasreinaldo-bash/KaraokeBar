package vostore.karaoquebar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnBuscar;
    private Button expertsNacionais,expertsInternacionais,btnVoltar;
    private LinearLayout linearBuscarPalestrante;
    private EditText buscarPalestrante;
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
    LinearLayoutManager layoutManager;

    private EditText btnBuscarTodas;
    private Button btnCatalogoNacional,btnCatalogoInternacional,btnTelefone,btnRedesSociais,btnLocalizacao;
    private LinearLayout linearMenu,linearTodas;
    private RelativeLayout linearBackground;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainn);


        recyclerView2 = (RecyclerView) findViewById(R.id.id_recycler2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));


        //Cast
        btnBuscarTodas = findViewById(R.id.buscar_id);
        btnCatalogoInternacional = findViewById(R.id.btn_internacional_id);
        btnCatalogoNacional = findViewById(R.id.btn_nacional_id);
        btnTelefone = findViewById(R.id.btn_telefone_id);
        btnRedesSociais = findViewById(R.id.btn_redes_sociais_id);
        btnLocalizacao = findViewById(R.id.btn_localizacao);
        linearMenu = findViewById(R.id.linear_menu);
        linearTodas = findViewById(R.id.linear_menu);
        linearBackground = findViewById(R.id.linearBackground);


        //Onclick

        reference2 = FirebaseDatabase.getInstance().getReference().child("Musica");
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<MusicasNacionais>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    MusicasNacionais p = dataSnapshot1.getValue(MusicasNacionais.class);
                    list2.add(p);
                }

                adapter2 = new MyAdapterNacionais(MainActivity.this, list2);
                recyclerView2.setAdapter(adapter2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
        btnCatalogoNacional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MusicaNacional.class);
                startActivity(intent);
                finish();
            }
        });
        btnCatalogoInternacional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MusicaInternacional.class);
                startActivity(intent);
                finish();
            }
        });
        btnLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Maps.class);
                startActivity(intent);
                finish();
            }
        });

        btnBuscarTodas.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if (btnBuscarTodas.isCursorVisible() == true)
                {
                    linearMenu.setVisibility(View.GONE);
                    linearTodas.setVisibility(View.VISIBLE);
                }








            }
        });

        linearBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearMenu.setVisibility(View.VISIBLE);
                linearTodas.setVisibility(View.GONE);
            }
        });


        
    }
}
