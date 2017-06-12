package da.miguel.cliente_movil;

import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import Modelo.Smartphone;

public class InformacionSmartphone extends AppCompatActivity {

    Smartphone seleccionado;
    int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_smartphone);
        seleccionado = getIntent().getParcelableExtra("telefono");
        idUser = getIntent().getIntExtra("id",0);

        FloatingActionButton reserva = (FloatingActionButton) findViewById(R.id.btnReserva);

        reserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(InformacionSmartphone.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(InformacionSmartphone.this);
                }
                builder.setTitle("Reservar Telefono")
                        .setMessage("Desea hacer una reservacion de este telefono?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                HacerReservacionTask reservacion = new HacerReservacionTask(InformacionSmartphone.this);
                                reservacion.execute();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }
        });

        final TextView TVModelo = (TextView) findViewById(R.id.TVModelo);
        final TextView TVMarca = (TextView) findViewById(R.id.TVMarca);
        final TextView TVDescripcion = (TextView) findViewById(R.id.TVDescripcion);
        final TextView TVColor = (TextView) findViewById(R.id.TVColor);
        final TextView TVPrecio = (TextView) findViewById(R.id.TVPrecio);
        final TextView TVCantidad = (TextView) findViewById(R.id.TVCantidad);

        TVModelo.setText(seleccionado.getModelo());
        TVMarca.setText(seleccionado.getMarca());
        TVDescripcion.setText(seleccionado.getDescripcion());
        TVColor.setText("Colores \nDisponibles: \n"+seleccionado.getColor());
        TVPrecio.setText("$ " + seleccionado.getPrecio());
        if (seleccionado.getCantidad() > 0 ){
         TVCantidad.setText("En Stock!");
        }else{
            TVCantidad.setText("Sin existencias");
        }
    }

    public class HacerReservacionTask extends AsyncTask<Void,Void,Void>{

        private String url = new InicioSesion().URL_IP+"/reservacion/registrar";
        private RequestQueue rqt;
        private StringRequest srqt;
        private Context context;

        public HacerReservacionTask(Context context) {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... params) {
            rqt = Volley.newRequestQueue(context);
            srqt = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("Respuesta del servidor", response);
                            Toast.makeText(context, "Reservacion realizada", Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Error del servidor", error.toString());
                    Toast.makeText(context,"La reservacion no pudo llevarse a cabo. Porfavor intenta de nuevo.",Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams(){
                    Map<String, String> parametros = new HashMap<>();
                    parametros.put("idSmartphone",Integer.toString(seleccionado.getId_smartphone()));
                    parametros.put("idUsuario", Integer.toString(idUser));
                    return parametros;
                }
            };
            rqt.add(srqt);
            return null;
        }
    }
}
