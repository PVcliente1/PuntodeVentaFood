package com.example.ricardosernam.puntodeventa.____herramientas_app;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ricardosernam.puntodeventa.BaseDeDatosLocal;
import com.example.ricardosernam.puntodeventa.Clientes.Clientes;
import com.example.ricardosernam.puntodeventa.Proveedores.Proveedores;
import com.example.ricardosernam.puntodeventa.R;
import com.example.ricardosernam.puntodeventa._____interfazes.agregado;
import com.example.ricardosernam.puntodeventa._____interfazes.interfazUnidades_OnClick;


public class dialog_fragment_agregar_cliente extends DialogFragment {
    private Button guardar_cliente, cancelar_cliente;
    private EditText ET_clienteNombreNuevo, ET_clienteApellidosNuevo,ET_clienteAliasNuevo,ET_clienteTelefonoNuevo,ET_clienteDireccionNueva;
    //private interfazUnidades_OnClick Interfaz;
    private agregado Interfaz;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            //Interfaz = (interfazUnidades_OnClick) getParentFragment();  ///obtenemos
            Interfaz = (agregado) getParentFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling fragment must implement Callback interface");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment_agregar_cliente, container, false);

        //casting de botones
        guardar_cliente=view.findViewById(R.id.BtnGuardarCliente);
        cancelar_cliente=view.findViewById(R.id.BtnCancelarAgregarCliente);

        //casting de editTexts
        ET_clienteNombreNuevo = view.findViewById(R.id.ET_clienteNombreNuevo);
        ET_clienteApellidosNuevo = view.findViewById(R.id.ET_clienteApellidosNuevo);
        ET_clienteAliasNuevo = view.findViewById(R.id.ET_clienteAliasNuevo);
        ET_clienteTelefonoNuevo = view.findViewById(R.id.ET_clienteTelefonoNuevo);
        ET_clienteDireccionNueva = view.findViewById(R.id.ET_clienteDireccionNueva);

        guardar_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alta();
                dismiss();
                if(Interfaz!=null){  ///notificamos al fragment que se agrego para actualizar el SPINNER EN CLIENTES
                    Interfaz.agregar();
                }
            }
        });
        cancelar_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        getDialog().setTitle("Agregar Cliente");
        return view;

    }

    public void alta()
    {
        BaseDeDatosLocal admin = new BaseDeDatosLocal(this.getContext());
        SQLiteDatabase db = admin.getWritableDatabase();

        //nuevo registro
        ContentValues nuevoRegistro = new ContentValues();
        //agregar info al registro
        nuevoRegistro.put("nombre",ET_clienteNombreNuevo.getText().toString());
        nuevoRegistro.put("apellido", ET_clienteApellidosNuevo.getText().toString());
        nuevoRegistro.put("alias", ET_clienteAliasNuevo.getText().toString());
        nuevoRegistro.put("telefono",ET_clienteTelefonoNuevo.getText().toString());
        nuevoRegistro.put("direccion",ET_clienteDireccionNueva.getText().toString());

        //insertar el nuevo registro
        db.insert("Clientes",null, nuevoRegistro);

        //cerrar la base de datos
        db.close();
    }
}

