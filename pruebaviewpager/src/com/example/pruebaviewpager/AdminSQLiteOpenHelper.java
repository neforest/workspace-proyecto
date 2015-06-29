package com.example.pruebaviewpager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;


public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

	public AdminSQLiteOpenHelper(Context context, String nombre, CursorFactory factory, int version){
		super(context, nombre, factory, version);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("create table tb_palabra("
				+ "id_palabra integer primary key autoincrement, "
				+ "nombre_palabra text, "
				+ "significado text, "
				+ "ejemplo text,"
				+ "id_curso integer)");
		
		db.execSQL("create table tb_curso("
				+ "id_curso integer primary key autoincrement, "
				+ "nombrecurso text, "
				+ "descripcion text, "
				+ "id_usuario integer)");
		
		db.execSQL("create table tb_usuario("
				+ "id_usuario integer primary key autoincrement, "
				+ "nombres text, "
				+ "appaterno text, "
				+ "apmaterno text,"
				+ "id_nivel integer)");
		
		db.execSQL("create table tb_nivel("
				+ "id_nivel integer primary key autoincrement, "
				+ "descripcion text, "
				+ "puntosmin integer, "
				+ "puntosmax integer)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
		db.execSQL("drop table if exists tb_palabra");
		db.execSQL("create table tb_palabra("
				+ "id_palabra integer primary key autoincrement, "
				+ "nombre_palabra text, "
				+ "significado text, "
				+ "ejemplo text,"
				+ "id_curso integer)");
		db.execSQL("drop table if exists tb_curso");
		db.execSQL("create table tb_curso("
				+ "id_curso integer primary key autoincrement, "
				+ "nombrecurso text, "
				+ "descripcion text, "
				+ "id_usuario integer)");
		db.execSQL("drop table if exists tb_usuario");
		db.execSQL("create table tb_usuario("
				+ "id_usuario integer primary key autoincrement, "
				+ "nombres text, "
				+ "appaterno text, "
				+ "apmaterno text,"
				+ "puntaje integer,"
				+ "id_nivel integer)");
		db.execSQL("drop table if tb_nivel");
		db.execSQL("create table tb_nivel("
				+ "id_nivel integer primary key autoincrement, "
				+ "descripcion text, "
				+ "puntosmin integer, "
				+ "puntosmax integer)");
				
	}

}
