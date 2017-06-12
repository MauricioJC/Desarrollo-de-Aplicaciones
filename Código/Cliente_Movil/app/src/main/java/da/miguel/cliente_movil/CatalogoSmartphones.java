package da.miguel.cliente_movil;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import Adapters.SmartphoneAdapter;
import Modelo.Smartphone;


public class CatalogoSmartphones extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ListView listaSmartphones;
    private SwipeRefreshLayout refreshLayout;
    private ArrayList<Smartphone> lista;
    public Context contexto = this;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_smartphones);

        final int idUser = getIntent().getIntExtra("id", 0);
        listaSmartphones = (ListView) findViewById(R.id.listaSmartphones);
        lista = new ArrayList<>();
        listaSmartphones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Smartphone telefono = (Smartphone) parent.getItemAtPosition(position);
                Intent intent = new Intent(CatalogoSmartphones.this, InformacionSmartphone.class);
                intent.putExtra("telefono", telefono);
                intent.putExtra("id", idUser);
                startActivity(intent);
            }
        });

        final DescargarCatalogoTask tarea = new DescargarCatalogoTask(listaSmartphones, this, lista);
        tarea.execute();
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refrescarTabla);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        refreshLayout.setRefreshing(false);
                        tarea.doInBackground();
                    }
                }, 3000);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_busqueda, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) searchItem.getActionView();
        mSearchView.setQueryHint("Search...");
        mSearchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        ArrayList<Smartphone> resultado = new ArrayList<Smartphone>();
        CharSequence sequence = query.toLowerCase();
        for (Smartphone i : lista) {
            if (i.getModelo().toLowerCase().contains(sequence) || i.getMarca().toLowerCase().contains(sequence)) {

                resultado.add(i);
            }
        }
        setTabla(resultado);


        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        ArrayList<Smartphone> resultado = new ArrayList<Smartphone>();
        CharSequence sequence = newText.toLowerCase();
        for (Smartphone i : lista) {
            if (i.getModelo().toLowerCase().contains(sequence) || i.getMarca().toLowerCase().contains(sequence)) {

                resultado.add(i);
            }
        }
        setTabla(resultado);


        return false;
    }

    @Override
    public void onBackPressed() {
        setTabla(lista);
        super.onBackPressed();
    }

    private void setTabla(ArrayList<Smartphone> telefonos) {
        SmartphoneAdapter adapter = new SmartphoneAdapter(this, telefonos);
        listaSmartphones.setAdapter(adapter);
    }

    public class DescargarCatalogoTask extends AsyncTask<Void, Boolean, Boolean> {

        //private ArrayList<Smartphone> lista;
        private ListView lvSmartphones;
        private CatalogoSmartphones context;
        private String url = new InicioSesion().URL_IP + "/smartphone/lista";
        private RequestQueue rqt;
        private StringRequest srqt;

        public DescargarCatalogoTask(ListView lv, CatalogoSmartphones context, ArrayList<Smartphone> listas) {
            lvSmartphones = lv;
            this.context = context;
            //this.lista = listas;
        }


        @Override
        protected Boolean doInBackground(Void... params) {
            boolean resultados = false;
            //lista = new ArrayList<Smartphone>();

            rqt = Volley.newRequestQueue(context);
            srqt = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("Cadena Json", response);
                            try {
                                JSONArray jsonSmartphones = new JSONArray(response);
                                for (int i = 0; i < jsonSmartphones.length(); i++) {
                                    JSONObject jsonSmartphone = null;
                                    try {
                                        jsonSmartphone = jsonSmartphones.getJSONObject(i);
                                        Smartphone telefono = new Smartphone();
                                        telefono.setId_smartphone(jsonSmartphone.getInt("idSmartphone"));
                                        telefono.setMarca(jsonSmartphone.getString("marca"));
                                        telefono.setModelo(jsonSmartphone.getString("modelo"));
                                        telefono.setDescripcion(jsonSmartphone.getString("descripcion"));
                                        telefono.setColor(jsonSmartphone.getString("color"));
                                        telefono.setPrecio(jsonSmartphone.getInt("precio"));
                                        telefono.setCantidad(jsonSmartphone.getInt("cantidad"));
                                        if (!lista.contains(telefono)) {
                                            lista.add(telefono);
                                        }


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                setTabla(lista);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } finally {

                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Error del servidor", error.toString());
                }
            }
            );

            rqt.add(srqt);
            return resultados;
        }
    }


}

