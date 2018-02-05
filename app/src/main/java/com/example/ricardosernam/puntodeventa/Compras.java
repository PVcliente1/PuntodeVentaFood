package com.example.ricardosernam.puntodeventa;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class Compras extends Fragment{
    private Button escan;
    private EditText codigoBarras;
    private RadioButton RBexistente,RBnuevo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compras, container, false);
        //Econtramos los valores de nuestros Radio Button dentro del XML
        RBexistente=(RadioButton)view.findViewById(R.id.RBexistente);
        RBnuevo=(RadioButton)view.findViewById(R.id.RBnuevo);
        escan = (Button)view.findViewById(R.id.BtnEscanearCodigo);
        codigoBarras = (EditText)view.findViewById(R.id.ETCapturarProducto);

        escan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Escanner.class);//intanciando el activity del scanner
                startActivityForResult(intent,2);//inicializar el activity con RequestCode2
            }
        });

        return view;
    }

    //metodo para obtener resultados
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Codigo 2
        if (requestCode == 2 && data != null) {
            //obtener resultados
            codigoBarras.setText(data.getStringExtra("BARCODE"));
        }
    }

    public void onRadioButtonClicked(View view) {

        // Is the button now checked?

        boolean checked = ((RadioButton) view).isChecked();

        // hacemos un case con lo que ocurre cada vez que pulsemos un botón

        switch(view.getId()) {
            case R.id.RBexistente:
                if (checked)
                    Toast.makeText(getContext(),"HOLA",Toast.LENGTH_LONG).show();
                    break;
            case R.id.RBnuevo:
                if (checked)
                    Toast.makeText(getContext(),"JOTO",Toast.LENGTH_LONG).show();
                    break;
        }
    }


}
