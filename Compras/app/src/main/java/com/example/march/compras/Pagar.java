package com.example.march.compras;

import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Pagar extends AppCompatActivity {

    private TextView textView,user;
    private int idPro,idUs;
    private String us;
    public  String respp;
    ImageView fotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagar);

        textView=(TextView) findViewById(R.id.textView3);
        user=(TextView)findViewById(R.id.lblUse);
        fotos=(ImageView)findViewById(R.id.foto);

        String prod=getIntent().getStringExtra("Productos");
        textView.setText(prod);

        us=getIntent().getExtras().getString("user3");
        user.setText(us);

        idPro= Integer.parseInt(prod.substring(0,1));

        cargarFoto();
    }
    public void cargarFoto(){
        if(idPro==1){
            fotos.setImageResource(R.drawable.completo);
        }
        if(idPro==2){
            fotos.setImageResource(R.drawable.lomito);
        }
        if (idPro==3){
            fotos.setImageResource(R.drawable.papafritas);
        }
        if (idPro==4){
            fotos.setImageResource(R.drawable.sushi);
        }
    }

    public void ingresar(View v) {
        new soapId().execute(us);
    }

    private void validar() {
        Intent intent = new Intent(this, Lista.class);
        intent.putExtra("user4", getIntent().getExtras().getString("user3"));
        startActivity(intent);
    }

    private class soapId extends AsyncTask<String,String,String> {
        static final String NAMESPACE="http://Service.Burgos.cl/";
        static final String METHODNAME="getIdLogin";
        static final String URL="http://localhost:8080/LoginService/LoginService?WSDL";
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
                pagar(Integer.parseInt(response.toString()));

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
    public void pagar(int idL) {
        new soapPagar().execute(idL,idPro);
    }

    private class soapPagar extends AsyncTask<Integer,Integer,Integer> {
        static final String NAMESPACE="http://Service.Burgos.cl/";
        static final String METHODNAME="crearCompra";
        static final String URL="https://10.0.2.2:8080/CompraService/CompraService?WSDL";
        static final String SOAP_ACTION=NAMESPACE+METHODNAME;
        @Override
        protected Integer doInBackground(Integer... params) {
            int resultado=0;
            SoapObject request = new SoapObject(NAMESPACE,METHODNAME);
            request.addProperty("idLo",params[0]);
            request.addProperty("idPro",params[1]);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(URL);
            try {
                transporte.call(SOAP_ACTION,envelope);
                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                respp=response.toString();
                //idUs=Integer.parseInt(response.toString());
                validar();

                Log.d("respxx2",response.toString());
            }catch (Exception e){
                Log.d("eXXX2",e.getMessage());
            }
            return resultado;
        }
    }
}
