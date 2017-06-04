package co.edu.umariana.luiseduardo.webservicedatosabiertos.datosAPI;

import java.util.ArrayList;
import java.util.List;

import co.edu.umariana.luiseduardo.webservicedatosabiertos.models.Acueducto;
import co.edu.umariana.luiseduardo.webservicedatosabiertos.models.Municipio;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by luiseduardo on 22/05/17.
 */

public interface DatosOpenAPIService
{
    @GET("pfet-63uw.json")
    Call<ArrayList<Municipio>> obtenerListaMunicipios();

    @GET("8ma2-3xzt.json")
    Call<List<Acueducto>> obtenerListaAcueducto();

}
