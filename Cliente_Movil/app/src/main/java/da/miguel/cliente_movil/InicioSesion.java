package da.miguel.cliente_movil;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Objects;

public class InicioSesion extends AppCompatActivity {
    private EditText campoPassword;
    private AutoCompleteTextView campoEmail;
    private IniciarSesionTask inicioSesion = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        campoPassword = (EditText) findViewById(R.id.password);
        campoEmail = (AutoCompleteTextView) findViewById(R.id.email);
        Button btnLoggin = (Button) findViewById(R.id.btnLoggin);

        btnLoggin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });
        Button btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mostrarCatalogo(v);
            }
        });
    }

    public void mostrarCatalogo(View view){
        Intent i = new Intent(this, CatalogoSmartphones.class);
        startActivity(i);
    }

    private void iniciarSesion(){
            if (inicioSesion != null) {
                return;
            }

            // Reset errors.
            campoEmail.setError(null);
            campoPassword.setError(null);

            // Store values at the time of the login attempt.
            String correo = campoEmail.getText().toString();
            String contrasena = campoPassword.getText().toString();

            boolean cancel = false;
            View focusView = null;

            // Check for a valid password, if the user entered one.
            if (!TextUtils.isEmpty(contrasena) && !isPasswordValid(contrasena)) {
                campoPassword.setError("Contasena muy corta");
                focusView = campoPassword;
                cancel = true;
            }

            // Check for a valid email address.
            if (TextUtils.isEmpty(correo)) {
                campoEmail.setError("Ingresa un correo electronico");
                focusView = campoEmail;
                cancel = true;
            } else if (!isEmailValid(correo)) {
                campoEmail.setError("Correo invalido");
                focusView = campoEmail;
                cancel = true;
            }

            if (cancel) {
                // There was an error; don't attempt login and focus the first
                // form field with an error.
                focusView.requestFocus();
            } else {
                inicioSesion = new IniciarSesionTask(correo, contrasena);
                inicioSesion.execute((String) null);
            }
        }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 6;
    }

    public class IniciarSesionTask extends AsyncTask<String, Void, String>{
        private final String email;
        private final String password;

        public IniciarSesionTask(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getPostDataString(JSONObject params){
            StringBuilder resultado = new StringBuilder();
            boolean primero = true;
            Iterator<String> itr = params.keys();
            while(itr.hasNext()){
                String llave = itr.next();
                try {
                    Object valor = params.get(llave);
                    if(primero ){
                        primero = false;
                    }else{
                        resultado.append("&");
                        try {
                            resultado.append(URLEncoder.encode(llave, "UTF-8"));
                            resultado.append("=");
                            resultado.append(URLEncoder.encode(valor.toString()));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return resultado.toString();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL("http://192.168.1.67:8080/usuario/validar");

                JSONObject postParams = new JSONObject();
                postParams.put("correo",email );
                postParams.put("contraseña",password);
                Log.e("params",postParams.toString());

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(1000);
                connection.setConnectTimeout(1000);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);

                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                writer.write(getPostDataString(postParams));

                writer.flush();
                writer.close();
                os.close();

                int respuesta = connection.getResponseCode();

                if(respuesta == HttpURLConnection.HTTP_OK){
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuffer sb = new StringBuffer();
                    String line = "";

                    while ((line = in.readLine())!=null){
                        sb.append(line);
                        break;
                    }
                    in.close();
                    return sb.toString();
                }else{
                    return new String("falso" + respuesta);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "no conecto";
        }

        @Override
        protected  void onPostExecute(String resultado){
            Toast.makeText(getApplicationContext(), resultado,
                    Toast.LENGTH_LONG).show();
        }
    }

}
