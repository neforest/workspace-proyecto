package com.example.pruebaviewpager;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_fragments extends ActionBarActivity {

	DrawerLayout drawerLayout;
	ListView listView;
	FragmentManager FM;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragments);

		//etBuscar=((EditText)findViewById(R.id.etBuscar));
		
		FM = getSupportFragmentManager();
		FragmentTransaction FT = FM.beginTransaction();
		Fragment fragment = null;
		String tag = null;
		Bundle bundle = getIntent().getExtras();
		if (bundle.getString("opcion").equals("agregarpalabra")) {
			fragment = Fragment_palabra_nueva.newInstance();
			tag = "tagNueva";
		} else {
			fragment = Fragment_lista.newInstance();
			tag = "tagLista";
		}

		FT.add(R.id.fragment_container, fragment, tag);
		FT.commit();

		// panel izquierda
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		listView = (ListView) findViewById(R.id.left_drawer_list);

		final String[] opciones = { "Todas las palabras", "Agregar palabras",
				"Atras" };

		listView.setAdapter(new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				opciones));

		listView.setOnItemClickListener(new ListView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView arg0, View arg1, int arg2,
					long arg3) {
				// Toast.makeText(Activity_fragments.this, "Item: " +
				// opciones[arg2],
				// Toast.LENGTH_SHORT).show();
				drawerLayout.closeDrawers();

				if (arg2 == 0) {// lista palabras
					FragmentTransaction FT = FM.beginTransaction();
					FT.replace(R.id.fragment_container,
							Fragment_lista.newInstance(), "tagLista");
					FT.commit();

				}
				if (arg2 == 1) {// agregar palabra
					FragmentTransaction FT = FM.beginTransaction();
					FT.replace(R.id.fragment_container, Fragment_palabra_nueva
							.newInstance(), "tagNueva");
					FT.commit();

				}
				if (arg2 == 2) {// Atras

					finish();

				}

			}
		});

		getActionBar().setDisplayHomeAsUpEnabled(true);

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

	public void guardar(View view) {
		FM = getSupportFragmentManager();
		((Fragment_palabra_nueva) FM.findFragmentByTag("tagNueva")).guardar();
		finish();
	}

	public void actualizarListaFiltro(View view) {
		FM = getSupportFragmentManager();
		((Fragment_lista) FM.findFragmentByTag("tagLista")).actualizarListaFiltro();
		
	}
	
}
