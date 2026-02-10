package com.example.march.compras;


import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class Comprar extends AppCompatActivity{
    private static final String NAMESPACE="http://Service.Burgos.cl/";
    private static final String METHODNAME="getListaPro";
    private static final String URL="https://10.0.2.2:8080/ProductosService/ProductosService?WSDL";
    private static final String SOAP_ACTION=NAMESPACE+METHODNAME;

    private boolean boolres = false;
    private ListView lista;
    ArrayAdapter<ClProductos> adapter;
    private TextView us;

    private int[] imag={R.drawable.completo,R.drawable.lomito,R.drawable.papafritas,R.drawable.sushi};
    //private String lenguajeProgramacion[]=new String[4];
    LenguajeListAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar);
        us=(TextView)findViewById(R.id.textView4);
        lista=(ListView)findViewById(R.id.lista);

        String user=getIntent().getStringExtra("user2").toString();
        us.setText(user);

        new consumirAsys().execute();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String valor= parent.getItemAtPosition(position).toString();
                Intent nuevoform =new Intent(view.getContext() ,Pagar.class);
                nuevoform.putExtra("Productos",valor);
                nuevoform.putExtra("user3", us.getText().toString().trim());
                startActivity(nuevoform);
            }
        });

    }

    public void consumir(View view){
        new consumirAsys().execute();
    }

    public boolean invaceWS(){
        boolean res=false;
        try {
            EditText texto=(EditText)findViewById(R.id.txtUsuario);
            SoapObject request= new SoapObject(NAMESPACE,METHODNAME);
            //request.addProperty("codnivel",Integer.parseInt(texto.getText().toString().trim()));
            SoapSerializationEnvelope sobre= new SoapSerializationEnvelope(SoapEnvelope.VER11);
            //sobre.dotNet=true;
            sobre.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(URL);
            transporte.call(SOAP_ACTION,sobre); //Llamada
            Vector<?> responseVector=null;
            SoapObject soapObject=null;
            List<ClProductos> listadoLog= new ArrayList<>();

            if(sobre.getResponse() instanceof Vector){
                responseVector = (Vector<?>) sobre.getResponse(); //Almacenar en vector
            }else{
                soapObject=(SoapObject)sobre.getResponse();
            }
            if (responseVector!=null){
                int count =responseVector.size();
                for (int i =0;i<count;i++){//cada registro encontrado
                    SoapObject test=(SoapObject)responseVector.get(i);
                    listadoLog.add(leerSoap(test));
                }
            }else{
                if(soapObject!=null){
                    listadoLog.add(leerSoap(soapObject));
                }
            }

            int num=listadoLog.size();
            int n=0;
            String lenguajeProgramacion[]=new String[num];
            for (int i = 0; i<listadoLog.size(); i++){
                lenguajeProgramacion[n]=listadoLog.get(i).toString();
                n=n+1;
            }
            adapter2=new LenguajeListAdapter(this,lenguajeProgramacion,imag);
            //adapter = new ArrayAdapter<ClProductos>(this,android.R.layout.simple_list_item_1,listadoLog);
            res=true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ERROR",e.getMessage());
        }
        return res;
    }

    private ClProductos leerSoap(SoapObject soapObj){
        String id = soapObj.getProperty("id").toString();
        String nombre =soapObj.getProperty("nombre").toString();
        String descripcion =soapObj.getProperty("descripcion").toString();
        String valor = soapObj.getProperty("valor").toString();
        ClProductos p = new ClProductos(Integer.parseInt(id),nombre,descripcion,Integer.parseInt(valor));
        return p;
    }


    private class consumirAsys extends android.os.AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            boolres=invaceWS();
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            if(boolres){
                lista.setAdapter(adapter2);
                System.out.println("Actualizando..");
            }
            Toast.makeText(getApplicationContext(),"Finalizado...",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPreExecute(){
            Toast.makeText(getApplicationContext(),"Descargando...",Toast.LENGTH_SHORT).show();
        }
    }
}
