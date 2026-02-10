package com.example.march.compras;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Cesar on 12/10/2015.
 */
public class LenguajeListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final int[] integers;
    private String dato;
    private int id;

    public LenguajeListAdapter(Activity context, String[] itemname, int[] integers) {
        super(context, R.layout.fila_lista, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.integers=integers;
    }

    public View getView(int posicion, View view, ViewGroup parent){

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.fila_lista,null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.texto_principal);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        //TextView etxDescripcion = (TextView) rowView.findViewById(R.id.texto_secundario);

        dato=itemname[posicion];
        id=Integer.parseInt(dato.substring(0,1));

        txtTitle.setText(itemname[posicion]);
        //imageView.setImageResource(integers[posicion]);
        //etxDescripcion.setText("Description "+itemname[posicion]);
        if(id==1){
            imageView.setImageResource(R.drawable.completo);
        }if (id==2){
            imageView.setImageResource(R.drawable.lomito);
        }if(id==3){
            imageView.setImageResource(R.drawable.papafritas);
        }if (id==4){
            imageView.setImageResource(R.drawable.sushi);
        }

        return rowView;
    }

}
