package com.app.preparatec2;

import com.app.preparatec2.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class About extends Activity {

	public static final String copyright  = "\u00a9";
	TextView labelLeyenda, labelAuthors;
	String leyenda;
	String authors;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);

		labelLeyenda = (TextView)findViewById(R.id.textView1);
		labelAuthors = (TextView)findViewById(R.id.textView2);

		leyenda = "PreparaTEC es una herramienta informática que permite al estudiante prepararse para la prueba de aptitud académica del "
				+ "Tecnológico de Costa Rica. La aplicación contendrá información basada en las guías de preparación, con el fin de brindar "
				+ "al estudiante una noción sobre la naturaleza de los ítems de dicha prueba.";
		
		authors =  "Desarrolladores: \n Jose Ariel Arias Méndez \n Edwin Vásquez Leiva \n José Pablo Fernández \n" + 
		" Luis Alejandro Corrales \n Gabriela Delgado Quesada \n" + copyright + " 2014-2015 PreparaTEC \n Todos los derechos reservados";

		labelLeyenda.setTextColor(Color.parseColor("#414042"));
		labelLeyenda.setText(leyenda);
		
		labelAuthors.setTextColor(Color.parseColor("#414042"));
		labelAuthors.setText(authors);

	}
}
