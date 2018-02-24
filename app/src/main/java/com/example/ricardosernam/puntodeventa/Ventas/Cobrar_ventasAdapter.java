package com.example.ricardosernam.puntodeventa.Ventas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.ricardosernam.puntodeventa.R;

import java.util.ArrayList;

public class Cobrar_ventasAdapter extends RecyclerView.Adapter <Cobrar_ventasAdapter.Productos_ventasViewHolder>{  ///adaptador para la seccion de cobrar
    private ArrayList<Cobrar_ventas_class> itemsCobrar;
    private Context context;

    public Cobrar_ventasAdapter(Context context, Activity activity, ArrayList<Cobrar_ventas_class> itemsCobrar) {  ///recibe el arrayCobrar como parametro
        this.itemsCobrar=itemsCobrar;
        this.context=context;
    }

    public class Productos_ventasViewHolder extends RecyclerView.ViewHolder {    ////clase donde van los elementos del cardview
        public TextView nombreP, tipoD;
        public Button eliminarArt,eliminarCompra ;
        public CheckBox descuento;

        public Productos_ventasViewHolder(View v) {
            super(v);
            nombreP = (TextView) v.findViewById(R.id.TVnombreProductoCobrar);  ///cardviews donde va el nombre del producto
            eliminarArt = v.findViewById(R.id.BtnEliminarArt);
            eliminarCompra = v.findViewById(R.id.BtnEliminarCompra);
            descuento=v.findViewById(R.id.CBDescuento);
            tipoD=v.findViewById(R.id.TVtipoDescuento);
        }
    }
    @Override
    public int getItemCount() {
        return itemsCobrar.size();
    }

    @Override
    public Productos_ventasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_cobrar, parent, false);////mencionamos el archivo del layout
        return new Productos_ventasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final Productos_ventasViewHolder holder, final int position) {  ////mencionamos que se hara con los elementos del cardview
        holder.nombreP.setText(itemsCobrar.get(position).getSeleccionado());
        final FragmentManager manager = ((Activity) context).getFragmentManager();
        holder.eliminarArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemsCobrar.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,itemsCobrar.size());
                if(itemsCobrar.isEmpty()){
                    manager.beginTransaction().remove(manager.findFragmentById(R.id.LOcobrar)).commit();
                }
            }
        });
        //if(holder.descuento.isChecked()) {
            holder.descuento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                   if(b) {
                       final AlertDialog.Builder tipoDescuento= new AlertDialog.Builder(context);
                       tipoDescuento.setTitle("Descuentos");
                       tipoDescuento.setMessage("Seleccion un tipo de descuento");
                       tipoDescuento.setCancelable(false);
                       tipoDescuento.setPositiveButton("Normal  (%)", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface tipoDescuento, int id) {
                               holder.tipoD.setText("Normal (%)");

                               tipoDescuento.dismiss();
                           }
                       });
                       tipoDescuento.setNegativeButton("Especial (%)", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface tipoDescuento, int id) {
                               holder.tipoD.setText("Especial (%)");
                               tipoDescuento.dismiss();
                           }
                       });
                       tipoDescuento.show();
                   }
                   else{
                       holder.tipoD.setText(" ");
                   }
                }
            });
    }
}