package da.miguel.cliente_movil;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static da.miguel.cliente_movil.R.id.textView;

public class Busqueda extends AppCompatActivity {

    TextView resultado = (TextView)findViewById(R.id.resultado);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_busqueda, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Buscar");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(Busqueda.this, "Enviado", Toast.LENGTH_SHORT).show();
                searchView.setQuery("", false);
                searchView.setIconified(true);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                resultado.setText(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
