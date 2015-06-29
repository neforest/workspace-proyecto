package com.example.pruebaviewpager;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.Toast;

public class Dialog_pregunta_opciones extends DialogFragment{
	
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        // Get the layout inflater
	        LayoutInflater inflater = getActivity().getLayoutInflater();
	        
	        
	        setStyle(STYLE_NO_FRAME, 0);        
	        
	        
	        // Inflate and set the layout for the dialog
	        // Pass null as the parent view because its going in the dialog layout
	        builder.setView(inflater.inflate(R.layout.dialog_pregunta_opciones, null))
	        // Add action buttons
	               .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
	                   @Override
	                   public void onClick(DialogInterface dialog, int id) {
	                       // sign in the user ...
	                	   //Toast.makeText(Dialog_pregunta_opciones.this, "YEEEEESSSSSSSSSSS", Toast.LENGTH_LONG);
	                   }
	               })
	               .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       //LoginDialogFragment.this.getDialog().cancel();
	                	   //Dialog_pregunta_opciones.this.getDialog().cancel();
	                   }
	               });      
	        return builder.create();
	}
}
