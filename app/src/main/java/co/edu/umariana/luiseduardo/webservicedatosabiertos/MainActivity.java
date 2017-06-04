package co.edu.umariana.luiseduardo.webservicedatosabiertos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.edu.umariana.luiseduardo.webservicedatosabiertos.datosAPI.DatosOpenAPIService;
import co.edu.umariana.luiseduardo.webservicedatosabiertos.models.Acueducto;
import co.edu.umariana.luiseduardo.webservicedatosabiertos.models.Municipio;
import co.edu.umariana.luiseduardo.webservicedatosabiertos.models.MunicipiosRespuesta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListaMunicipiosAdapter listaMunicipiosAdapter;
    private boolean aptoParaCargar;
    private int offset;
    public final static String TAG="Open Data";
    private ViewGroup linearLayoutDetails;
    private ImageView imageViewExpand;
    private static final int DURATION = 250;
    private Intent context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        linearLayoutDetails = (ViewGroup) findViewById(R.id.linearLayoutDetails);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        listaMunicipiosAdapter = new ListaMunicipiosAdapter(this);
        recyclerView.setAdapter(listaMunicipiosAdapter);

        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, " Fin.");

                            aptoParaCargar = false;
                            offset += 4;
                            obtenerDatosMunicipios();
                        }
                    }
                }
            }
        });
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.datos.gov.co/resource/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        aptoParaCargar = true;
        obtenerDatosMunicipios();
        obtenerDatosAcueducto();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuopciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.opcion1)
        {
            Intent i = new Intent(getApplicationContext(),AcercaDe.class);
            getApplicationContext().startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }

    private void obtenerDatosAcueducto()
    {
        DatosOpenAPIService service = retrofit.create(DatosOpenAPIService.class);
        Call<List<Acueducto>> acueductoRespuestaCall = service.obtenerListaAcueducto();

        acueductoRespuestaCall.enqueue(new Callback<List<Acueducto>>() {
            @Override
            public void onResponse(Call<List<Acueducto>> call, Response<List<Acueducto>> response) {
                if(response.isSuccessful()){
                    List lista = response.body();

                    for(int i=0;i<lista.size();i++)
                    {
                        Acueducto p = (Acueducto) lista.get(i);
                        Log.i(TAG," nombre usuario: "+p.getNDeUsusarios()+" nombre acueducto: "+p.getNombreDelAcueducto());
                    }

                }else
                {
                    Log.e(TAG, "onResponse: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Acueducto>> call, Throwable t) {
                Log.e(TAG," onFailure: "+t.getMessage());
            }
        });
    }

    private void obtenerDatosMunicipios() {
        DatosOpenAPIService service = retrofit.create(DatosOpenAPIService.class);
        final Call<ArrayList<Municipio>> municipioRespuestaCall = service.obtenerListaMunicipios();

        municipioRespuestaCall.enqueue(new Callback<ArrayList<Municipio>>() {
            @Override
            public void onResponse(Call<ArrayList<Municipio>> call, Response<ArrayList<Municipio>> response) {
                if(response.isSuccessful()){
                        ArrayList lista = response.body();
                        listaMunicipiosAdapter.adicionarListaMunicipios(lista);

                }else
                {
                    Log.e(TAG, "onResponse: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Municipio>> call, Throwable t) {
                Log.e(TAG," onFailure: "+t.getMessage());
            }
        });
    }

}


