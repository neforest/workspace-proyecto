package com.example.blocknotas;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Detalle extends Activity {
	public EditText et1;
	public int mensajeId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detalle);

		et1 = (EditText) findViewById(R.id.texto);

		Bundle bundle = getIntent().getExtras();
		mensajeId = bundle.getInt("mensaje_id");
		et1.setText(bundle.getString("mensaje"));
	}

	public void guardar(View view) {
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "notas",
				null, 1);
		SQLiteDatabase db = admin.getWritableDatabase();
		ContentValues registro = new ContentValues();

		// Cursor fila =
		// db.rawQuery("select mensaje_id, mensaje from mensajes where mensaje_id='"+
		// mensajeId + "'", null);
		// if (fila.moveToFirst()) {
		registro.put("mensaje", et1.getText().toString());
		int cant = db.update("mensajes", registro, "mensaje_id='" + mensajeId
				+ "'", null);
		if (cant == 1) {
			Toast.makeText(this, "mensaje actualizado", Toast.LENGTH_SHORT)
					.show();
		} else {
			Toast.makeText(this, "error en actualizacion...",
					Toast.LENGTH_SHORT).show();
		}

		finish();
		// }
	}

	public void eliminar(View view) {
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "notas",
				null, 1);
		SQLiteDatabase db = admin.getWritableDatabase();

		int cant = db
				.delete("mensajes", "mensaje_id='" + mensajeId + "'", null);
		if (cant == 1) {
			Toast.makeText(this, "mensaje eliminado", Toast.LENGTH_SHORT)
					.show();
		} else {
			Toast.makeText(this, "error en actualizacion...",
					Toast.LENGTH_SHORT).show();
		}

		finish();
		// }
	}

}
