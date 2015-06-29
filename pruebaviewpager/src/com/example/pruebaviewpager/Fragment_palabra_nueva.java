package com.example.pruebaviewpager;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_palabra_nueva extends Fragment {
	EditText etPalabra;
	EditText etDefinicion;
	EditText etEjemplo;
	private static final String TEXTO = "texto";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	/*
	 * public static Fragment_palabra_nueva newInstance(String texto) {
	 * Fragment_palabra_nueva fragment = new Fragment_palabra_nueva(); Bundle
	 * args = new Bundle(); args.putString(TEXTO, texto);
	 * 
	 * fragment.setArguments(args); return fragment; }
	 */
	public static Fragment_palabra_nueva newInstance() {
		Fragment_palabra_nueva fragment = new Fragment_palabra_nueva();

		return fragment;
	}

	public Fragment_palabra_nueva() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_palabra_nueva,
				container, false);

		etPalabra = (EditText) rootView.findViewById(R.id.etPalabra);
		// etPalabra.setText(getArguments().getString(TEXTO));
		etDefinicion = (EditText) rootView.findViewById(R.id.etDefinicion);
		etEjemplo = (EditText) rootView.findViewById(R.id.etEjemplo);

		return rootView;
	}

	public void guardar() {

		System.out.println("guardar: " + etPalabra.getText().toString());

		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity(),
				"bd_palabra", null, 1);
		SQLiteDatabase db = admin.getWritableDatabase();
		ContentValues registro = new ContentValues();

		registro.put("nombre_palabra", etPalabra.getText().toString());
		registro.put("significado", etDefinicion.getText().toString());
		registro.put("ejemplo", etEjemplo.getText().toString());

		db.insert("tb_palabra", null, registro);
		db.close();

		Toast.makeText(getActivity(), "Se guardo el mensaje",
				Toast.LENGTH_SHORT).show();
	

	}

}