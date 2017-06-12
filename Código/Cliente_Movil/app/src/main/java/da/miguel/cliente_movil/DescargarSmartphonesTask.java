package da.miguel.cliente_movil;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import Adapters.SmartphoneAdapter;
import Modelo.Smartphone;

/**
 * Created by nosel_000 on 31/05/2017.
 */

public class DescargarSmartphonesTask extends AsyncTask<Void, Boolean, Boolean>{

    private ArrayList<Smartphone> lista;
    private ListView lvSmartphones;
    private CatalogoSmartphones context ;
    private String url = new InicioSesion().URL_IP +"/smartphone/lista" ;
    private RequestQueue rqt;
    private StringRequest srqt;

    public DescargarSmartphonesTask(ListView lv, CatalogoSmartphones context){
        lvSmartphones = lv;
        this.context = context;
    }


    @Override
    protected Boolean doInBackground(Void... params) {
        boolean resultados = false;
        lista = new ArrayList<Smartphone>();

        rqt = Volley.newRequestQueue(context);
        srqt = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Si funca");
                        Log.d("Respuesta " ,response);
                        JSONArray jsonSmartphones = null;
                        try {
                            jsonSmartphones = new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for (int i = 0; i < jsonSmartphones.length(); i++) {
                            JSONObject jsonSmartphone = null;
                            try {
                                jsonSmartphone = jsonSmartphones.getJSONObject(i);
                                Smartphone telefono = new Smartphone();
                                telefono.setId_smartphone(jsonSmartphone.getInt("idSmartphone"));
                                telefono.setMarca(jsonSmartphone.getString("marca"));
                                telefono.setCantidad(jsonSmartphone.getInt("cantidad"));
                                telefono.setColor(jsonSmartphone.getString("color"));
                                telefono.setDescripcion(jsonSmartphone.getString("descripcion"));
                                telefono.setModelo(jsonSmartphone.getString("modelo"));
                                telefono.setPrecio(jsonSmartphone.getInt("precio"));
                                lista.add(telefono);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error del servidor", error.toString());
            }
        }
        );

        /*try{

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();

            InputStream is;

            if(conn.getResponseCode()==HttpURLConnection.HTTP_ACCEPTED){
                is = conn.getInputStream();
            }else{
                is = conn.getErrorStream();
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String cad = br.readLine();



            try{
                JSONArray jsonSmartphones = new JSONArray(cad);
                for(int i = 0; i<jsonSmartphones.length();i++){
                    JSONObject jsonSmartphone = jsonSmartphones.getJSONObject(i);

                    Smartphone telefono = new Smartphone();
                    telefono.setId_smartphone(jsonSmartphone.getInt("idSmartphone"));
                    telefono.setMarca(jsonSmartphone.getString("marca"));
                    telefono.setCantidad(jsonSmartphone.getInt("cantidad"));
                    telefono.setColor(jsonSmartphone.getString("color"));
                    telefono.setDescripcion(jsonSmartphone.getString("descripcion"));
                    telefono.setModelo(jsonSmartphone.getString("modelo"));
                    telefono.setPrecio(jsonSmartphone.getInt("precio"));
                    lista.add(telefono);
                    resultados = true;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return resultados;
    }

    @Override
    protected void onPostExecute(Boolean resultados) {
        if(resultados){
            SmartphoneAdapter adapter = new SmartphoneAdapter(context,lista);
            lvSmartphones.setAdapter(adapter);

        }
    }
}
