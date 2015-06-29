package com.example.pruebaviewpager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Fragment_lista extends Fragment {
	ListView lvLista = null;
	EditText etBuscar = null;
	private static final String TEXTO = "texto";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static Fragment_lista newInstance() {
		Fragment_lista fragment = new Fragment_lista();
		Bundle args = new Bundle();
		// args.putString(TEXTO, texto);

		fragment.setArguments(args);
		return fragment;
	}

	public Fragment_lista() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_lista, container,
				false);

		// TextView tvprueba = (TextView) rootView.findViewById(R.id.tvprueba);
		// tvprueba.setText(getArguments().getString(TEXTO));

		lvLista = (ListView) rootView.findViewById(R.id.lvLista);
		etBuscar = (EditText) rootView.findViewById(R.id.etBuscar);

		actualizarLista();

		return rootView;
	}

	// metodos
	public void actualizarLista() {
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity(),
				"bd_palabra", null, 1);
		SQLiteDatabase db = admin.getReadableDatabase();
		Cursor cursor = db
				.rawQuery(
						"select significado, nombre_palabra as _id from tb_palabra order by nombre_palabra",
						null);
		getActivity().startManagingCursor(cursor);
		SimpleCursorAdapter sca = new SimpleCursorAdapter(getActivity(),
				android.R.layout.simple_list_item_2, cursor, new String[] {
						"_id", "significado" }, new int[] { android.R.id.text1,
						android.R.id.text2 });

		lvLista.setAdapter(sca);
		/*
		 * lvLista.setOnItemClickListener(new OnItemClickListener(){
		 * 
		 * @Override public void onItemClick(AdapterView<?> parent, View view,
		 * int posicion, long id) { detalle(view,posicion);
		 * 
		 * }
		 * 
		 * });
		 */

	}

	public void actualizarListaFiltro() {
		String filtro = etBuscar.getText().toString();
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity(),
				"bd_palabra", null, 1);
		SQLiteDatabase db = admin.getReadableDatabase();
		Cursor cursor = db
				.rawQuery(
						"select significado, nombre_palabra as _id from tb_palabra where nombre_palabra LIKE '"
								+ filtro + "%' order by nombre_palabra", null);
		getActivity().startManagingCursor(cursor);
		SimpleCursorAdapter sca = new SimpleCursorAdapter(getActivity(),
				android.R.layout.simple_list_item_2, cursor, new String[] {
						"_id", "significado" }, new int[] { android.R.id.text1,
						android.R.id.text2 });

		lvLista.setAdapter(sca);
		/*
		 * lvLista.setOnItemClickListener(new OnItemClickListener(){
		 * 
		 * @Override public void onItemClick(AdapterView<?> parent, View view,
		 * int posicion, long id) { detalle(view,posicion);
		 * 
		 * }
		 * 
		 * });
		 */

	}
}