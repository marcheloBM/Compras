package com.example.march.compras;

import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Vector;

public class Login extends AppCompatActivity {
    private EditText user,pass;
    private boolean resp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void ingresar(View v) {
        user=(EditText)findViewById(R.id.txtUsuario);
        pass=(EditText)findViewById(R.id.txtContraseña);
        new operacionSoap().execute(user.getText().toString().trim(),pass.getText().toString().trim());
    }

    private class operacionSoap extends AsyncTask<String,String,String>{

        static final String NAMESPACE="http://Service.Burgos.cl/";
        static final String METHODNAME="getLogin";

        static final String URL= "https://10.0.2.2:8080/LoginService/LoginService?WSDL";
        static final String SOAP_ACTION=NAMESPACE+METHODNAME;

        @Override
        protected String doInBackground(String... params) {
            String resultado="";

            SoapObject request = new SoapObject(NAMESPACE,METHODNAME);
            request.addProperty("user",params[0]);
            request.addProperty("pass",params[1]);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(URL);
            try {
                transporte.call(SOAP_ACTION,envelope);
                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

                resp=Boolean.valueOf(response.toString());
                validar();

                Log.d("respxx",response.toString());
            }catch (Exception e){
                Log.d("eXXX",e.getMessage());
            }
            return resultado;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            resp=Boolean.valueOf(s);

        }
    }

    public void validar(){
        if(resp==true){
            Intent intent = new Intent(this, Inicio.class);
            intent.putExtra("user", user.getText().toString().trim());
            startActivity(intent);

            Toast.makeText(this, "Correcto"+resp,Toast.LENGTH_LONG).show();

            Intent ven=new Intent(this,Inicio.class);
            startActivity(ven);
        }else{
            Toast.makeText(this, "Error"+resp,Toast.LENGTH_LONG).show();
        }
    }
    public void salirapli(View v){
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
