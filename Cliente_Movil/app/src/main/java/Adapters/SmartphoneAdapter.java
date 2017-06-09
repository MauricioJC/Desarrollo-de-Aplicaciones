package Adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Modelo.Smartphone;
import da.miguel.cliente_movil.R;

/**
 * Created by nosel_000 on 31/05/2017.
 */

public class SmartphoneAdapter extends BaseAdapter {

    private Context context;
    private  ArrayList<Smartphone> arrayList;

    public SmartphoneAdapter(Context context, ArrayList<Smartphone> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout_smartphone,parent,false);
        }

        TextView modelo = (TextView) convertView.findViewById(R.id.textModelo);
        TextView precio = (TextView) convertView.findViewById(R.id.textPrecio);
        TextView marca = (TextView) convertView.findViewById(R.id.textMarca);

        modelo.setText(arrayList.get(position).getModelo());
        precio.setText("$ "+Integer.toString(arrayList.get(position).getPrecio()));
        marca.setText(arrayList.get(position).getMarca());
        return convertView;
    }

    /*public SmartphoneAdapter(Context context, int resource, List<Smartphone> objects){
        super(context,resource,objects);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){
        View view = convertView;
        Smartphone telefono = getItem(position);

        if(view == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.activity_catalogo_smartphones,null);
        }

        if(telefono != null){

            TextView colMarca = (TextView) view.findViewById(R.id.colMarca);
            TextView colModelo = (TextView) view.findViewById(R.id.colModelo);
            TextView colPrecio = (TextView) view.findViewById(R.id.colPrecio);

            colMarca.setText(telefono.getMarca());
            colModelo.setText(telefono.getModelo());
            colModelo.setText(telefono.getPrecio());

        }
        return view;
    }*/
}
