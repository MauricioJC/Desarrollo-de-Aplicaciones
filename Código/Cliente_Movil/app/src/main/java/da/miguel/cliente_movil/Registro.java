package da.miguel.cliente_movil;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    private EditText campoPassword;
    private AutoCompleteTextView campoEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        campoEmail = (AutoCompleteTextView) findViewById(R.id.txtCorreo);
        campoPassword = (EditText) findViewById(R.id.txtContrasena);

        Button btnRegistro = (Button) findViewById(R.id.btnRegistro);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegistrarUsusario tarea = new RegistrarUsusario(Registro.this);
                tarea.execute();
            }
        });


    }

    public class RegistrarUsusario extends AsyncTask<Void,Void,Void>{

        String correo = campoEmail.getText().toString();
        String contrasena = campoPassword.getText().toString();
        String url = new InicioSesion().URL_IP + "/usuario/crear";
        Context context;
        private RequestQueue rqt;
        private StringRequest srqt;

        public RegistrarUsusario(Context context) {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... params) {
            rqt = Volley.newRequestQueue(context);
            srqt = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("rta_servidor", response);
                            if(response.trim().equals("true")){
                                Toast.makeText(context,"Registro exitoso",Toast.LENGTH_LONG).show();
                                InicioSesionScreen();
                            }else{
                                Toast.makeText(context,"No se pudo realizar el registro, intenta nuevamente.",Toast.LENGTH_LONG).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Error _ servidor", error.toString());
                }
            }) {
                @Override
                protected Map<String, String> getParams(){
                    Map<String, String> parametros = new HashMap<>();
                    parametros.put("correo",correo);
                    parametros.put("contraseña",contrasena);

                    return parametros;
                }
            };

            rqt.add(srqt);
            return null;
        }


    }
    public void InicioSesionScreen(){
        Intent i = new Intent(this, InicioSesion.class);
        startActivity(i);
    }
}
