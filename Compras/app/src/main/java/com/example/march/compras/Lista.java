package com.example.march.compras;

import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Lista extends AppCompatActivity {

    private static final String NAMESPACE="http://Service.Burgos.cl/";
    private static final String METHODNAME="getListaCom";
    private static final String URL="https://10.0.2.2:8080/CompraService/CompraService?WSDL";
    private static final String SOAP_ACTION=NAMESPACE+METHODNAME;

    private TextView us2;
    private String user;
    private String idu;
    private boolean boolres = false;
    private ListView lista;
    ArrayAdapter<ClProductos> adapter;

    private int[] imag={R.drawable.completo,R.drawable.lomito,R.drawable.papafritas,R.drawable.sushi};
    private int num;
    //private String lenguajeProgramacion[]=new String[num];
    LenguajeListAdapter adapter2;

    public  String respp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        lista=(ListView)findViewById(R.id.listaC);

        us2=(TextView)findViewById(R.id.textView6);
        user=getIntent().getStringExtra("user4");
        us2.setText(user);

        new soapIdU().execute(user);
        new consumirAsysPa().execute();

    }
    private class soapIdU extends AsyncTask<String,String,String> {
        static final String NAMESPACE="http://Service.Burgos.cl/";
        static final String METHODNAME="getIdLogin";
        static final String URL="https://10.0.2.2:8080/LoginService/LoginService?WSDL";
        static final String SOAP_ACTION=NAMESPACE+METHODNAME;
        @Override
        protected String doInBackground(String... params) {
            String resultado="";

            SoapObject request = new SoapObject(NAMESPACE,METHODNAME);
            request.addProperty("user",params[0]);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(URL);
            try {
                transporte.call(SOAP_ACTION,envelope);
                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                respp=response.toString();
                //idUs=Integer.parseInt(response.toString());
                idUsuario(response.toString());

                Log.d("respxx2",response.toString());
            }catch (Exception e){
                Log.d("eXXX2",e.getMessage());
            }
            return resultado;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            respp=s;
        }
    }

    private void idUsuario(String s) {
        idu=s;
    }

    private class consumirAsysPa extends android.os.AsyncTask<Void,Void,Void>{

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
    public boolean invaceWS(){
        boolean res=false;
        try {
            EditText texto=(EditText)findViewById(R.id.txtUsuario);
            SoapObject request= new SoapObject(NAMESPACE,METHODNAME);
            request.addProperty("idLo",Integer.parseInt(idu));
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
            num=listadoLog.size();
            String lenguajeProgramacion[]=new String[num];
            int n=0;
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


}
