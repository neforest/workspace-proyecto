package com.example.blocknotas;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	public EditText et1;
	
	public ListView lv1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et1 = (EditText) findViewById(R.id.texto);
		
		lv1=(ListView) findViewById(R.id.lista);
		
				
		actualizarLista();
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}



	/*public void cargar(View view) {
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "notas",
				null, 1);
		SQLiteDatabase db = admin.getReadableDatabase();
		System.out.println("antes de query");
		Cursor fila = db.rawQuery(
				"select mensaje from mensajes where titulo ='"
						+ et1.getText().toString() + "'", null);
		System.out.println("paso query");
		if (fila.moveToFirst()) {
			System.out.println("entro al if");
			et2.setText(fila.getString(0));
			Toast.makeText(this, "mensaje cargado", Toast.LENGTH_SHORT).show();
		} else {
			System.out.println("noooooooo entro al if");
			Toast.makeText(this, "No se encontro mensaje", Toast.LENGTH_SHORT)
					.show();
		}
		actualizarLista();
	}*/
/*
	public void eliminar(View view) {
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "notas",
				null, 1);
		SQLiteDatabase db = admin.getWritableDatabase();
		db.execSQL("drop table if exists mensajes");
		db.execSQL("create table mensajes(mensaje text)");
		db.close();
		et1.setText("");
		et2.setText("");
		Toast.makeText(this, "Mensajes eliminados", Toast.LENGTH_SHORT).show();
		actualizarLista();
	}
*/
	public void agregar(View view){
		Intent i=new Intent(this,Agregar.class);
		startActivity(i);
	}
	
	public void detalle(View view,int posicion){
		Intent i=new Intent(this,Detalle.class);
		System.out.println(((Cursor)lv1.getItemAtPosition(posicion)).getString(0));
		System.out.println(((Cursor)lv1.getItemAtPosition(posicion)).getString(1));
		int id=Integer.parseInt(((Cursor)lv1.getItemAtPosition(posicion)).getString(0));
		String mensaje=((Cursor)lv1.getItemAtPosition(posicion)).getString(1);
		i.putExtra("mensaje_id", id);
		
		i.putExtra("mensaje",mensaje);
		System.out.println("paso el putExtra");
		startActivity(i);
	}
	
	public void actualizarLista(){
		AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,"notas",null,1);
		SQLiteDatabase db=admin.getReadableDatabase();
		Cursor cursor=db.rawQuery("select mensaje_id, mensaje as _id from mensajes",null);
		startManagingCursor(cursor);
		SimpleCursorAdapter sca=new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{"_id"}, new int[]{android.R.id.text1});
		lv1.setAdapter(sca);
		
		lv1.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int posicion,
					long id) {
				detalle(view,posicion);
				
			}
			
		});
		
	}
	
}
