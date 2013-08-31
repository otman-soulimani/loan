package fr.NicolasMasson.CalculPretImmobilier;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import fr.NicolasMasson.CalculPretImmobilier.R.drawable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.app.Activity;

import android.net.Uri;
import android.view.View;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;


public class Recapitulatif extends Activity {
	
	
	double numPourcentage, numMensualite;
	double montant, taux, mensualite, duree;
	List<Double> interetParAnnee = new ArrayList<Double>();
	List<Double> amortissementParAnnee = new ArrayList<Double>();
	List<Double> resteDuParAnnee = new ArrayList<Double>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table_result);
		
		Bundle extras = getIntent().getExtras();
		this.montant = extras.getDouble("montant");
		this.taux = extras.getDouble("taux");
		this.mensualite = extras.getDouble("mensualite");
		this.duree = extras.getDouble("duree");
		
		
		TableLayout tl = (TableLayout) findViewById(R.id.table_layout);
		
		ClasseOutil calcul;
    	calcul = new ClasseOutil(this.montant, this.taux, this.mensualite, this.duree);
    	
    	interetParAnnee = calcul.getInteretParAnnee();
    	amortissementParAnnee = calcul.getAmortissementParAnnee();
    	resteDuParAnnee = calcul.getResteDuParAnnee();
    	
    	DecimalFormat df = new DecimalFormat("#,##0.0#");  
    	
    	
    	
    
    	for (int i = 0; i < interetParAnnee.size(); i++)
    	{
    		

   
    		 TableRow tr = new TableRow(this);
             tr.setId(100+i);
             tr.setLayoutParams(new LayoutParams(
                     LayoutParams.FILL_PARENT,
                     LayoutParams.WRAP_CONTENT)); 

	        
	        // Colone année
	        TextView lblColAnnee = new TextView(this);
	        lblColAnnee.setId((200+i*10)+1);
	        lblColAnnee.setText(String.valueOf(i+1));
	        lblColAnnee.setTextColor(Color.RED);
	        lblColAnnee.setBackgroundResource(drawable.cell_row);
	        lblColAnnee.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 15));
	        tr.addView(lblColAnnee);
	        
	        // Colone Capital
	        TextView lblColCapital = new TextView(this);
	        lblColCapital.setId((200+i*10)+2);
	        lblColCapital.setText(df.format(amortissementParAnnee.get(i)).replace(","," "));
	        lblColCapital.setTextColor(Color.BLACK);
	        lblColCapital.setBackgroundResource(drawable.cell_row);
	        lblColCapital.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 30));
	        tr.addView(lblColCapital);
	        
	        //Colone Interets
	        TextView lblColInterets = new TextView(this);
	        lblColInterets.setId((200+i*10)+3);
	        lblColInterets.setText(df.format(interetParAnnee.get(i)).replace(","," "));
	        lblColInterets.setTextColor(Color.BLACK);
	        lblColInterets.setBackgroundResource(drawable.cell_row);
	        lblColInterets.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 25));
	        tr.addView(lblColInterets);
	        
	        // Colone reste Du
	        TextView lblColResteDu = new TextView(this);
	        lblColResteDu.setId((200+i*10)+4);
	        lblColResteDu.setText(df.format(resteDuParAnnee.get(i)).replace(","," "));
	        lblColResteDu.setTextColor(Color.BLACK);
	        lblColResteDu.setBackgroundResource(drawable.cell_row);
	        lblColResteDu.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 30));
	        tr.addView(lblColResteDu);
	        
	        
	        
	        tl.addView(tr);
	        
    	}
    	
    	
    	
    	
	
	}

}