package com.example.pruebaviewpager;

import java.util.ArrayList;
import java.util.List;

import com.example.pruebaviewpager.bean.TbPalabra;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	DrawerLayout drawerLayout;
	ListView listView;

	ActionBarActivity mainActivity = this;

	AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "bd_palabra",
			null, 1);
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// datos iniciales en la base de datos
		inicializarDatos();

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.

		final FragmentManager fm = getSupportFragmentManager();

		mSectionsPagerAdapter = new SectionsPagerAdapter(fm);

		// leemos la base de datos

		SQLiteDatabase db = admin.getReadableDatabase();
		Cursor cursor = db
				.rawQuery(
						"select id_palabra, nombre_palabra, significado, ejemplo from tb_palabra",
						null);

		// Nos aseguramos de que existe al menos un registro
		TbPalabra palabra = null;
		if (cursor.moveToFirst()) {
			// Recorremos el cursor hasta que no haya m�s registros
			do {

				// obtenemos registro por registro:
				palabra = new TbPalabra(cursor.getInt(0), cursor.getString(1),
						cursor.getString(2), cursor.getString(3));

				mSectionsPagerAdapter.addFragment(PlaceholderFragment
						.newInstance(palabra));

			} while (cursor.moveToNext());
		} else {
			System.out.println("noooooooo entro al if");
			Toast.makeText(this, "No se encontro mensaje", Toast.LENGTH_SHORT)
					.show();
		}

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// panel izquierda
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		listView = (ListView) findViewById(R.id.left_drawer_list);

		final String[] opciones = { "Todas las palabras", "Agregar palabras" ,"Prueba diaria"};

		listView.setAdapter(new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				opciones));

		listView.setOnItemClickListener(new ListView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView arg0, View arg1, int arg2,
					long arg3) {
				// Toast.makeText(MainActivity.this, "Item: " + opciones[arg2],
				// Toast.LENGTH_SHORT).show();
				drawerLayout.closeDrawers();

				if (arg2 == 0) {// Todas las palabras
					Intent i = new Intent(mainActivity,
							Activity_fragments.class);
					i.putExtra("opcion", "listapalabras");
					startActivity(i);
				}
				if (arg2 == 1) {// Agregar palabra

					Intent i = new Intent(mainActivity,
							Activity_fragments.class);
					i.putExtra("opcion", "agregarpalabra");
					startActivity(i);
				}
				
				if (arg2 == 2) {// Prueba diaria

				DialogFragment newFragment = new Dialog_pregunta_opciones();
				newFragment.show(getSupportFragmentManager(), "dialogpreguntas");
				
				}
			}
		});

		getActionBar().setDisplayHomeAsUpEnabled(true);

		// mostrar dialog pregunta opciones
		DialogFragment newFragment = new Dialog_pregunta_opciones();
		newFragment.show(getSupportFragmentManager(), "dialogpreguntas");

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (drawerLayout.isDrawerOpen(listView)) {
				drawerLayout.closeDrawers();
			} else {
				drawerLayout.openDrawer(listView);
			}
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void inicializarDatos() {
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
				"bd_palabra", null, 1);

		SQLiteDatabase db = admin.getWritableDatabase();

		int cant = db.delete("tb_palabra", null, null);
		//cant = db.delete("tb_curso", null, null);
		//cant = db.delete("tb_usuario", null, null);
		//cant = db.delete("tb_nivel", null, null);
		if (cant == 1) {
			Toast.makeText(this, "mensaje eliminado", Toast.LENGTH_SHORT)
					.show();
		}

		ContentValues registro = new ContentValues();
		
		//INSERTAR TB_PALABRA
		registro.put("nombre_palabra", "Acoplamiento");
		registro.put(
				"significado",
				"Es la cantidad de relaciones que se establecen entre los m�dulos de un programa.");
		registro.put("ejemplo",
				"El programa tiene alto acoplamiento, no podremos mantenerlo.");
		db.insert("tb_palabra", null, registro);

		registro = new ContentValues();

		registro.put("nombre_palabra", "Herencia");
		registro.put(
				"significado",
				"Es la propiedad que permite que una subclase herede los atributos y los mensajes de una superclase.");
		registro.put("ejemplo", "La clase Empleado hereda de la clase Persona.");
		db.insert("tb_palabra", null, registro);

		registro = new ContentValues();

		registro.put("nombre_palabra", "Escalabilidad");
		registro.put(
				"significado",
				"Es la capacidad de un sistema informatico de adaptarse a un numero de usuarios cada vez mayor, sin perder calidad en los servicios.");
		registro.put(
				"ejemplo",
				"Necesitamos un numero considerable de usuarios para probar la escalabilidad del sistema");
		db.insert("tb_palabra", null, registro);

		registro = new ContentValues();

		registro.put("nombre_palabra", "Hosting");
		registro.put(
				"significado",
				"Servicio que prestan algunas empresas para alojar sitios web por medio de sus servidores.");
		registro.put("ejemplo",
				"Contrata un servicio de Hosting para acceder desplegar la aplicacion.");
		db.insert("tb_palabra", null, registro);

		registro = new ContentValues();

		registro.put("nombre_palabra", "Prototipo");
		registro.put("significado",
				" Versi�n preliminar de un sistema que sirve de modelo para fases posteriores.");
		registro.put(
				"ejemplo",
				"El prototipo del sistema es de mucha ayuda para analizar los detalles de la interfaz de usuario.");
		db.insert("tb_palabra", null, registro);

		
		//INSERTAR TB_CURSO
		
		registro = new ContentValues();

		registro.put("nombrecurso", "Ingenieria de Software");
		registro.put("descripcion","");
		registro.put("id_usuario",1);
		db.insert("tb_curso", null, registro);
		
		
		//INSERTAR TB_USUARIO
		
		registro = new ContentValues();

		registro.put("nombres", "Usuario Nuevo");
		registro.put("appaterno","app");
		registro.put("apmaterno","");
		db.insert("tb_usuario", null, registro);
		
		db.close();
		// Toast.makeText(this, "Se guardo el mensaje",
		// Toast.LENGTH_SHORT).show();

	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		List<Fragment> listaFragmentos;

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
			this.listaFragmentos = new ArrayList<Fragment>();
		}

		public void addFragment(Fragment fragment) {
			listaFragmentos.add(fragment);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			// return PlaceholderFragment.newInstance(position + 1);
			return this.listaFragmentos.get(position);
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			// return 3;
			return this.listaFragmentos.size();
		}

		/*
		 * @Override public CharSequence getPageTitle(int position) { Locale l =
		 * Locale.getDefault(); switch (position) { case 0: return
		 * getString(R.string.title_section1).toUpperCase(l); case 1: return
		 * getString(R.string.title_section2).toUpperCase(l); case 2: return
		 * getString(R.string.title_section3).toUpperCase(l); } return null; }
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			if (position >= getCount()) {
				FragmentManager manager = ((Fragment) object)
						.getFragmentManager();
				FragmentTransaction trans = manager.beginTransaction();
				trans.remove((Fragment) object);
				trans.commit();
			}
		}

		public void removeAll() {
			listaFragmentos.clear();
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ID_PALABRA = "id_palabra";
		private static final String PALABRA = "palabra";
		private static final String SIGNIFICADO = "significado";
		private static final String EJEMPLO = "ejemplo";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(TbPalabra palabra) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ID_PALABRA, palabra.getIdPalabra());
			args.putString(PALABRA, palabra.getNombrePalabra());
			args.putString(SIGNIFICADO, palabra.getSignificado());
			args.putString(EJEMPLO, palabra.getEjemplo());
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_palabra,
					container, false);
			TextView textView = (TextView) rootView
					.findViewById(R.id.section_label);
			textView.setText(getArguments().getString(PALABRA));
			TextView tvsignificado = (TextView) rootView
					.findViewById(R.id.tvsignificado);
			tvsignificado.setText(getArguments().getString(SIGNIFICADO));
			TextView tvejemplo = (TextView) rootView
					.findViewById(R.id.tvejemplo);
			tvejemplo.setText("\"" + getArguments().getString(EJEMPLO) + "\"");

			return rootView;
		}
	}

	// /////////////////////////////OPERACIONES BD
	// ///////////////////////////////

	/*
	 * public void cargar(View view) { AdminSQLiteOpenHelper admin = new
	 * AdminSQLiteOpenHelper(this, "notas", null, 1); SQLiteDatabase db =
	 * admin.getReadableDatabase(); System.out.println("antes de query"); Cursor
	 * fila = db.rawQuery( "select mensaje from mensajes where titulo ='" +
	 * et1.getText().toString() + "'", null); System.out.println("paso query");
	 * if (fila.moveToFirst()) { System.out.println("entro al if");
	 * et2.setText(fila.getString(0)); Toast.makeText(this, "mensaje cargado",
	 * Toast.LENGTH_SHORT).show(); } else {
	 * System.out.println("noooooooo entro al if"); Toast.makeText(this,
	 * "No se encontro mensaje", Toast.LENGTH_SHORT) .show(); }
	 * actualizarLista(); }
	 */
	/*
	 * public void eliminar(View view) { AdminSQLiteOpenHelper admin = new
	 * AdminSQLiteOpenHelper(this, "notas", null, 1); SQLiteDatabase db =
	 * admin.getWritableDatabase(); db.execSQL("drop table if exists mensajes");
	 * db.execSQL("create table mensajes(mensaje text)"); db.close();
	 * et1.setText(""); et2.setText(""); Toast.makeText(this,
	 * "Mensajes eliminados", Toast.LENGTH_SHORT).show(); actualizarLista(); }
	 */
	/*
	 * public void agregar(View view) { Intent i = new Intent(this,
	 * Agregar.class); startActivity(i); }
	 * 
	 * public void detalle(View view, int posicion) { Intent i = new
	 * Intent(this, Detalle.class); System.out.println(((Cursor)
	 * lv1.getItemAtPosition(posicion)) .getString(0));
	 * System.out.println(((Cursor) lv1.getItemAtPosition(posicion))
	 * .getString(1)); int id = Integer.parseInt(((Cursor)
	 * lv1.getItemAtPosition(posicion)) .getString(0)); String mensaje =
	 * ((Cursor) lv1.getItemAtPosition(posicion)) .getString(1);
	 * i.putExtra("mensaje_id", id); i.putExtra("mensaje", mensaje);
	 * System.out.println("paso el putExtra"); startActivity(i); }
	 * 
	 * public void actualizarLista() { AdminSQLiteOpenHelper admin = new
	 * AdminSQLiteOpenHelper(this, "notas", null, 1); SQLiteDatabase db =
	 * admin.getReadableDatabase(); Cursor cursor = db.rawQuery(
	 * "select mensaje_id, mensaje as _id from mensajes", null);
	 * startManagingCursor(cursor); SimpleCursorAdapter sca = new
	 * SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor,
	 * new String[] { "_id" }, new int[] { android.R.id.text1 });
	 * lv1.setAdapter(sca);
	 * 
	 * lv1.setOnItemClickListener(new OnItemClickListener() {
	 * 
	 * @Override public void onItemClick(AdapterView<?> parent, View view, int
	 * posicion, long id) { detalle(view, posicion);
	 * 
	 * }
	 * 
	 * });
	 * 
	 * }
	 */

}
