package co.edu.umariana.luiseduardo.webservicedatosabiertos;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import co.edu.umariana.luiseduardo.webservicedatosabiertos.models.Municipio;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if(status == ConnectionResult.SUCCESS)
        {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

        }else {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, (Activity) getApplicationContext(), 10);
        }

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            String nombreMunucipio = (String) bundle.get("nombreMunicipio");
            String id = nombreMunucipio + "";
        }
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //HABILITAR ZOOM EN EL MAPA
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            String nombreMunicipio = (String) bundle.get("nombreMunicipio");
            //ArrayList municipios = (ArrayList)bundle.get("municipios");
            String id = nombreMunicipio + "";

            //ArrayList<Municipio> municipio = new ArrayList<>();
            //municipio.size();

            if(nombreMunicipio.equals("FUNES"))
            {
                LatLng mun = new LatLng(1.0011675,-77.4576808);
                mMap.addMarker(new MarkerOptions().position(mun).title("Ud se encuetra aqui"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(mun));
            }
            else if (nombreMunicipio.equals("MALLAMA"))
            {
                LatLng mun = new LatLng(1.1408725,-77.8667557);
                mMap.addMarker(new MarkerOptions().position(mun).title("Ud se encuetra aqui"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(mun));
            }
            else if (nombreMunicipio.equals("BARBACOAS"))
            {
                LatLng mun = new LatLng(1.5021461,-78.3529111);
                mMap.addMarker(new MarkerOptions().position(mun).title("Ud se encuetra aqui"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(mun));
            }
            else if (nombreMunicipio.equals("EL ROSARIO"))
            {
                LatLng mun = new LatLng(1.433723,-77.6554943);
                mMap.addMarker(new MarkerOptions().position(mun).title("Ud se encuetra aqui"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(mun));
            }
            else if (nombreMunicipio.equals("ILES"))
            {
                LatLng mun = new LatLng(0.9689115,-77.5233636);
                mMap.addMarker(new MarkerOptions().position(mun).title("Ud se encuetra aqui"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(mun));
            }

        }
    }
}
