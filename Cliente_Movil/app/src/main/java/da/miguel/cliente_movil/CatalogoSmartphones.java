package da.miguel.cliente_movil;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import Modelo.Smartphone;

public class CatalogoSmartphones extends AppCompatActivity {

    private ListView listaSmartphones;
    private ArrayList<Smartphone> lista;
    public Context contexto = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_smartphones);
        listaSmartphones = (ListView) findViewById(R.id.listaSmartphones);
        lista = new ArrayList<>();

        new DescargarSmartphonesTask(listaSmartphones, this).execute();
    }

}
