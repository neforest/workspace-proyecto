package com.example.blocknotas;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Agregar extends Activity{

	public EditText et1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agregar);
		
		et1=(EditText) findViewById(R.id.texto);
		
		
	}
	
	public void guardar(View view) {
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "notas",
				null, 1);
		SQLiteDatabase db = admin.getWritableDatabase();
		ContentValues registro = new ContentValues();
		
		registro.put("mensaje", et1.getText().toString());
		db.insert("mensajes", null, registro);
		db.close();
		Toast.makeText(this, "Se guardo el mensaje", Toast.LENGTH_SHORT)
				.show();
		
		/*
		
		Cursor fila = db.rawQuery("select mensaje from mensajes where titulo='"
				+ et1.getText().toString()+"'", null);
		if (fila.moveToFirst()) {
			registro.put("mensaje",et2.getText().toString());
			int cant=db.update("mensajes", registro, "titulo ='"+et1.getText().toString()+"'", null);
			if(cant==1){
				Toast.makeText(this, "mensaje actualizado", Toast.LENGTH_SHORT).show();
			}
			else{
				Toast.makeText(this, "error en actualizacion...", Toast.LENGTH_SHORT);
			}
		} else {
			//registro.put("titulo", et1.getText().toString());
			registro.put("mensaje", et1.getText().toString());
			db.insert("mensajes", null, registro);
			db.close();
			Toast.makeText(this, "Se guardo el mensaje", Toast.LENGTH_SHORT)
					.show();
		}
		*/
		finish();
	}
	
	
	
}
