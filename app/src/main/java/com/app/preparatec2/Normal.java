package com.app.preparatec2;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.*;

import java.util.Locale;
import java.util.Random;
import java.io.IOException;

import com.app.preparatec2.R;

import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.database.SQLException;


public class Normal extends Activity implements  DialogInterface.OnClickListener {
	List<Pregunta> quesList;
	List<Pregunta> questInfo;

	TextView txtQuestion;
	Button butA,butB,butC,butD,butE;
	String respuestaUsr;
	int score=0;
	Pregunta currentQ;
	int qid=0;
	int counterQuest = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_normal);

		DbHelper db=new DbHelper(this);

		db = new DbHelper(this);
		try {

			db.createDataBase();

		} catch (IOException ioe) {

			throw new Error("Unable to create database");

		}
		try {
			db.openDataBase();
		}catch(SQLException sqle){

			throw sqle;
		}


		quesList=db.getItemInfo();
		questInfo=db.getItems();
		quesList = prepareQuestion(questInfo,quesList);


		long seed = System.nanoTime();
		Collections.shuffle(quesList, new Random(seed));

		currentQ=quesList.get(qid);


		txtQuestion=(TextView)findViewById(R.id.textView1);

		butA=(Button)findViewById(R.id.button1);
		butB=(Button)findViewById(R.id.button2);
		butC=(Button)findViewById(R.id.button3);
		butD=(Button)findViewById(R.id.button4);
		butE=(Button)findViewById(R.id.button5);

		setQuestionView();
		

		butA.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				respuestaUsr= "1";
				verifAns();
			} 
		});

		butB.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				respuestaUsr= "2";
				verifAns();
			} 
		});

		butC.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				respuestaUsr= "3";
				verifAns();
			} 
		});

		butD.setOnClickListener(new View.OnClickListener() {
			@Override

			public void onClick(View v) {
				respuestaUsr= "4";
				verifAns();
			} 
		});

		butE.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				respuestaUsr= "5";
				verifAns();
			} 
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.normal, menu);
		return true;
	}




	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void setQuestionView()
	{
		txtQuestion.setTextColor(Color.parseColor("#414042"));

		txtQuestion.setText(currentQ.getQUESTION());
		butA.setText(currentQ.getOpcA());
		butA.setTextColor(Color.parseColor("#414042"));

		butB.setText(currentQ.getOpcB());
		butB.setTextColor(Color.parseColor("#414042"));
		butC.setText(currentQ.getOpcC());
		butC.setTextColor(Color.parseColor("#414042"));
		butD.setText(currentQ.getOpcD());
		butD.setTextColor(Color.parseColor("#414042"));
		butE.setText(currentQ.getOpcE());
		butE.setTextColor(Color.parseColor("#414042"));
		
		qid++;
		counterQuest++;
	}


	public void verifAns() {
		
		if(currentQ.getANSWER().equals(respuestaUsr) && counterQuest!=20)
		{
			score++;
			Toast.makeText(getApplicationContext(),"Respuesta Correcta" , Toast.LENGTH_SHORT).show();
			currentQ=quesList.get(qid);
			setQuestionView();

		}

		else {
			Toast.makeText(getApplicationContext(),"Respuesta Incorrecta" , Toast.LENGTH_SHORT).show();
			currentQ=quesList.get(qid);
			setQuestionView();

		}
		
		if(counterQuest==20){
			int finScore=(score*100)/20;
			AlertDialog ad = new AlertDialog.Builder(this)
			.setMessage("Su calificación es: " + Integer.toString(finScore))
			.setIcon(R.drawable.ic_launcher)
			.setTitle("Fin de la Práctica")
			//.setNegativeButton("Salir", this)
			.setPositiveButton("Menú Principal", this)
			//.setNeutralButton("Cancel", this)
			.setCancelable(false)
			.create();
			ad.show();

		}
		else{
			currentQ=quesList.get(qid);
			setQuestionView();
		}


	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		switch(which){
		case DialogInterface.BUTTON_POSITIVE: 
			//Devuelve Menu Principal
			finish();
			System.exit(0);
			break;
		default:
			break;
		}
	}

	private List<Pregunta> prepareQuestion(List<Pregunta> pItems,List<Pregunta> pItemInfo ) {

		List<Pregunta> result = new ArrayList<Pregunta>();
		for ( int i = 0; i < pItems.size(); i ++ ) {
			Pregunta tmp = new Pregunta();
			tmp.setID(pItems.get(i).getID());
			tmp.setQUESTION(pItems.get(i).getQUESTION());
			tmp.setOpcA(pItemInfo.get(i).getOpcA());
			tmp.setOpcB(pItemInfo.get(i).getOpcB());
			tmp.setOpcC(pItemInfo.get(i).getOpcC());
			tmp.setOpcD(pItemInfo.get(i).getOpcD());
			tmp.setOpcE(pItemInfo.get(i).getOpcE());
			tmp.setANSWER(pItemInfo.get(i).getANSWER());
			result.add(tmp);
		}

		return result;

	}
	
}
