package com.app.preparatec2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.database.SQLException;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.os.*;

public class Dare_Screen extends Activity implements View.OnClickListener,DialogInterface.OnClickListener {
	List<Question_Template> quesList;
	List<Question_Template> questInfo;

	int score=0;
	int qid=0;
	Question_Template currentQ;
	TextView txtQuestion, cronomTextView;
	Button butA,butB,butC,butD,butE;
	String respuestaUsr;
	int time = 100000;
	CountDownTimer cronom;
	int counterQuest=0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_retate);


		DB_Driver db=new DB_Driver(this);

		db = new DB_Driver(this);
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
		cronomTextView=(TextView)findViewById(R.id.cronomTextV);

		butA=(Button)findViewById(R.id.button1);
		butB=(Button)findViewById(R.id.button2);
		butC=(Button)findViewById(R.id.button3);
		butD=(Button)findViewById(R.id.button4);
		butE=(Button)findViewById(R.id.button5);


		setQuestionView();

		final CountDownTimer cronom = new CountDownTimer(time, 1000) {
			
			public void onTick(long millisUntilFinished) {
				String tmp=String.valueOf(millisUntilFinished/1000);
				
				if(millisUntilFinished/1000<=10) {
					cronomTextView.setTextColor(Color.parseColor("#FF0000"));
				}
				else {
				cronomTextView.setTextColor(Color.parseColor("#FFFFFF"));
				}
				cronomTextView.setText(tmp);
			}

			public void onFinish() {
				this.start();
				try{
					currentQ=quesList.get(qid);
					setQuestionView();

				}
				catch(Exception e){
					this.cancel();
					/*Intent intent = new Intent(Reto.this, ResultActivity.class);
					Bundle b = new Bundle();
					b.putInt("score", score);
					intent.putExtras(b); 
					startActivity(intent);
					finish();
					 */
					//Toast.makeText(getApplicationContext(),Integer.toString(score) , Toast.LENGTH_SHORT).show();
				}

			}
		};
		cronom.start();

		butA.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				respuestaUsr= butA.getText().toString();
				verifAns(cronom);

			} 
		});

		butB.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				respuestaUsr= butB.getText().toString();
				verifAns(cronom);


			} 
		});

		butC.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				respuestaUsr= butC.getText().toString();
				verifAns(cronom);
			} 
		});

		butD.setOnClickListener(new View.OnClickListener() {
			@Override

			public void onClick(View v) {
				respuestaUsr= butD.getText().toString();
				verifAns(cronom);

			} 
		});

		butE.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				respuestaUsr= butE.getText().toString();
				verifAns(cronom);

			} 
		});






	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.normal, menu);
		return true;
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


	public void verifAns(CountDownTimer pCronom){
		
		if(counterQuest==20) {			
			int finScore=(score*100)/20;
			pCronom.cancel();
			AlertDialog ad = new AlertDialog.Builder(this)
			.setMessage("Su calificación es: " + Integer.toString(finScore))
			.setIcon(R.drawable.ic_launcher)
			.setTitle("Fin de la Práctica")
			.setPositiveButton("Menú Principal", this)
			//.setNeutralButton("Cancel", this)
			.setCancelable(false)
			.create();
			ad.show();
			
		}
		else{
			//pCronom.cancel();
			//pCronom.start();
			//currentQ=quesList.get(qid);
			//setQuestionView();
			
			if(currentQ.getANSWER().equals(respuestaUsr) && counterQuest!=20)
			{
				score++;
				Toast.makeText(getApplicationContext(),"Respuesta Correcta" , Toast.LENGTH_SHORT).show();
				//pCronom.cancel();
				//pCronom.start();
				//currentQ=quesList.get(qid);
				//setQuestionView();
				
			}
			else {
				Toast.makeText(getApplicationContext(),"Respuesta Incorrecta" , Toast.LENGTH_SHORT).show();
				//currentQ=quesList.get(qid);
				//setQuestionView();

			}
			pCronom.cancel();
			pCronom.start();
			currentQ=quesList.get(qid);
			setQuestionView();
		}
	}

	private List<Question_Template> prepareQuestion(List<Question_Template> pItems,List<Question_Template> pItemInfo ) {

		List<Question_Template> result = new ArrayList<Question_Template>();
		for ( int i = 0; i < pItems.size(); i ++ ) {
			Question_Template tmp = new Question_Template();
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
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		switch(which){
		case DialogInterface.BUTTON_POSITIVE: 
			//Devuelve Home_Screen Principal
			finish();
			System.exit(0);
			break;
		default:
			// nada
			break;
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}


}
