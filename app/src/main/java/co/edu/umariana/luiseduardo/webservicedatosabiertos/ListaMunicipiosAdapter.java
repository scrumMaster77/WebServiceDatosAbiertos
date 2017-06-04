package co.edu.umariana.luiseduardo.webservicedatosabiertos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import co.edu.umariana.luiseduardo.webservicedatosabiertos.models.Municipio;
import retrofit2.Retrofit;

/**
 * Created by luiseduardo on 2/06/17.
 */

public class ListaMunicipiosAdapter extends RecyclerView.Adapter<ListaMunicipiosAdapter.ViewHolder>
{
    private static final String TAG = "MUNICIPIO";
    private final Context context;
    private final ArrayList<Municipio> dataset;
    private MainActivity principal;
    MediaPlayer mp;


    public ListaMunicipiosAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ListaMunicipiosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) 
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListaMunicipiosAdapter.ViewHolder holder, final int position) {
        final Municipio p = dataset.get(position);
        holder.txt_municipio.setText(p.getNombreMunicipio());

        holder.btn_informacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AlertDialog alertDialog;
                alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setIcon(R.drawable.icono);
                alertDialog.setTitle("Información del municipio");
                alertDialog.setButton("Visitar Portal web", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id)
                    {
                        webViewResultados();
                    }
                });
                alertDialog.setMessage("°Municipio: " + p.getNombreMunicipio() +
                        "\n" +"\n"+ "°Código DANE: " + p.getCodigoDaneMunicipio()+
                        "\n" +"\n" + "°Alcalde: " + p.getNombreAlcalde() +
                        "\n" +"\n"+ "°Contacto: " + p.getTelefonoDeContacto() +
                        "\n" +"\n"+ "°Correo: " + p.getCorreocontactenos()+
                        "\n" +"\n"+ "°Dirección: " + p.getDirecciN()+
                        "\n" +"\n"+ "°NIT: " + p.getNit()+
                        "\n" +"\n"+ "°Sitio Web: "+p.getPortalweb());
                alertDialog.show();
            }
        });

        holder.btn_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                    //ArrayList<Municipio> data = dataset;
                    Intent intent = new Intent(context, MapsActivity.class);
                    intent.putExtra("nombreMunicipio",p.getNombreMunicipio());
                    //intent.putParcelableArrayListExtra("municipios",data);
                    context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return dataset.size();
    }

    public void adicionarListaMunicipios(ArrayList<Municipio> listaMunicipios) {
        dataset.addAll(listaMunicipios);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txt_municipio;
        private Button btn_informacion;
        private Button btn_mapa;

        public ViewHolder( final View itemView)
        {
            super(itemView);
            txt_municipio = (TextView) itemView.findViewById(R.id.txt_municipio);
            btn_informacion =(Button) itemView.findViewById(R.id.btn_info);
            btn_mapa =(Button) itemView.findViewById(R.id.btn_mapa);
        }
    }
    private void webViewResultados() {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        WebView wv = new WebView(context);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("http://www.ancuya-narino.gov.co/index.shtml");
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view,String url) {
                view.loadUrl(url);

                return true;
            }
        });

        alert.setNegativeButton("Close",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        Dialog d = alert.setView(wv).create();
        d.show();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(d.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.FILL_PARENT;
        lp.height = WindowManager.LayoutParams.FILL_PARENT;
        d.getWindow().setAttributes(lp);
    }
}
