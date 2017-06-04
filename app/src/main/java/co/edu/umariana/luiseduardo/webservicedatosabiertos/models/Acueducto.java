package co.edu.umariana.luiseduardo.webservicedatosabiertos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by luiseduardo on 22/05/17.
 */

public class Acueducto {

    @SerializedName("e_mail_o_telefono_de_contacto")
    @Expose
    private String eMailOTelefonoDeContacto;
    @SerializedName("fuente_de_captaci_n")
    @Expose
    private String fuenteDeCaptaciN;
    @SerializedName("n")
    @Expose
    private String n;
    @SerializedName("n_de_ususarios")
    @Expose
    private String nDeUsusarios;
    @SerializedName("nombre_del_acueducto")
    @Expose
    private String nombreDelAcueducto;
    @SerializedName("representante_legal")
    @Expose
    private String representanteLegal;
    @SerializedName("ubicaci_n_vereda")
    @Expose
    private String ubicaciNVereda;

    public String getEMailOTelefonoDeContacto() {
        return eMailOTelefonoDeContacto;
    }

    public void setEMailOTelefonoDeContacto(String eMailOTelefonoDeContacto) {
        this.eMailOTelefonoDeContacto = eMailOTelefonoDeContacto;
    }

    public String getFuenteDeCaptaciN() {
        return fuenteDeCaptaciN;
    }

    public void setFuenteDeCaptaciN(String fuenteDeCaptaciN) {
        this.fuenteDeCaptaciN = fuenteDeCaptaciN;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getNDeUsusarios() {
        return nDeUsusarios;
    }

    public void setNDeUsusarios(String nDeUsusarios) {
        this.nDeUsusarios = nDeUsusarios;
    }

    public String getNombreDelAcueducto() {
        return nombreDelAcueducto;
    }

    public void setNombreDelAcueducto(String nombreDelAcueducto) {
        this.nombreDelAcueducto = nombreDelAcueducto;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public String getUbicaciNVereda() {
        return ubicaciNVereda;
    }

    public void setUbicaciNVereda(String ubicaciNVereda) {
        this.ubicaciNVereda = ubicaciNVereda;
    }

}