package fr.NicolasMasson.CalculPretImmobilier;



import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import android.graphics.Typeface;

public class Main extends Activity {
	
	
	RelativeLayout mainRR;
	View mainView;
	EditText txtMontant;
	EditText txtPourcentage;
	EditText txtMensualite;
	EditText txtDuree;
	TextView lblSummary;
	TextView lblSummary2;
	TextView lblSummary3;
	TextView lblSummary4;
	TextView lblSummary5;
	
	String Newline=System.getProperty("line.separator");
	double numPourcentage, numMensualite;
	double montant, taux, mensualite, duree;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

        Button btnCalcul = (Button) findViewById(R.id.btnCalcul);
        Button btnClear = (Button) findViewById(R.id.btnClear);
        
        mainRR = (RelativeLayout) findViewById(R.id.relativeLayout);
        mainView = (View) (getWindow().getDecorView().findViewById(R.layout.activity_main));
        txtMontant = (EditText) findViewById(R.id.txtMontant);
        txtPourcentage = (EditText) findViewById(R.id.txtPourcentage);
        txtMensualite = (EditText) findViewById(R.id.txtMensualite);
        txtDuree = (EditText) findViewById(R.id.txtDuree);
        lblSummary = (TextView) findViewById(R.id.lblSummary);
        lblSummary2 = (TextView) findViewById(R.id.lblSummary2);
        lblSummary3 = (TextView) findViewById(R.id.lblSummary3);
        lblSummary4 = (TextView) findViewById(R.id.lblSummary4);
        lblSummary5 = (TextView) findViewById(R.id.lblSummary5);
        lblSummary.setId(R.id.lblSummary);
        lblSummary2.setId(R.id.lblSummary2);
        lblSummary3.setId(R.id.lblSummary3);
        lblSummary4.setId(R.id.lblSummary4);
        lblSummary5.setId(R.id.lblSummary5);
        
       
        
        initParams();
        
         	 
        
        btnCalcul.setOnClickListener(new View.OnClickListener(){
        	public void onClick(final View v) {
        		String emptyString = "";
        		int nbChampsVides = 0;
        		if (txtDuree.getText().toString().equals(emptyString)){
        			nbChampsVides++;
        		}
        		if (txtMensualite.getText().toString().equals(emptyString)){
        			nbChampsVides++;
        		}
        		if (txtMontant.getText().toString().equals(emptyString)){
        			nbChampsVides++;
        		}
        		if (txtPourcentage.getText().toString().equals(emptyString))
        		{   
        			nbChampsVides++;
        		}

        		//Si on a plus de 1 champs vide on ne lance pas le process car manque de données
        		if (nbChampsVides > 1){
        			Toast.makeText(Main.this, "Données insufisantes pour faire le calcul", Toast.LENGTH_LONG).show(); 
        		}
        		else
        		{
        			clearResult();
        			updateResult();
        		}
        	}
        	
        });
        
        btnClear.setOnClickListener(new View.OnClickListener(){
        	public void onClick(final View v) {
        		clearResult();
        		txtDuree.setText("");
        		txtMontant.setText("");
        		txtMensualite.setText("");
        		txtPourcentage.setText("");
        		
            }
        	
        }        
     	);
        
        lblSummary3.setOnClickListener(new View.OnClickListener(){
	        public void onClick(final View v){
	        	Intent intent;
	        	intent = new Intent(Main.this, Recapitulatif.class);
	        	intent.putExtra("montant", Main.this.montant);
	        	intent.putExtra("taux", Main.this.taux);
	        	intent.putExtra("mensualite", Main.this.mensualite);
	        	intent.putExtra("duree", Main.this.duree);
	            startActivity(intent);
	        }
        });
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, Menu.NONE, "A propos").setIcon(R.drawable.questionmark);
        return true;
    }
    
    
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case 1:
                intent = new Intent(Main.this, APropos.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }
    
    private void clearResult(){
    		lblSummary.setText("");
    		lblSummary2.setText("");    		
    		lblSummary3.setText("");    		
    		lblSummary4.setText("");    		
    		lblSummary5.setText("");    		
    }
    
    private void initParams(){
    	
    	Typeface tf = Typeface.create("", Typeface.BOLD);
    	lblSummary.setTypeface(tf);
    	lblSummary3.setTypeface(tf);
    	lblSummary4.setTypeface(tf);
    	
    	android.widget.RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    	lp.addRule(RelativeLayout.BELOW, R.id.btnCalcul);
    	lblSummary.setLayoutParams(lp);
    	
    	android.widget.RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    	lp2.addRule(RelativeLayout.BELOW, lblSummary.getId());
    	lblSummary2.setLayoutParams(lp2);
    	
    	android.widget.RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    	lp3.addRule(RelativeLayout.BELOW, lblSummary5.getId());

    	lblSummary3.setLayoutParams(lp3);
    	
    	android.widget.RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    	lp4.addRule(RelativeLayout.BELOW, lblSummary2.getId());

    	lblSummary4.setLayoutParams(lp4);
    	
    	android.widget.RelativeLayout.LayoutParams lp5 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    	lp5.addRule(RelativeLayout.BELOW, lblSummary4.getId());

    	lblSummary5.setLayoutParams(lp5);
    	
    	
    }
    
    private void updateResult(){    	
    	String emptyString = "";
    	 if (! txtMontant.getText().toString().equals(emptyString)){
    		 this.montant = Double.valueOf(txtMontant.getText().toString());    		 
    	 }
    	 else
    	 {
    		 this.montant = 0.0;
    	 }
    	 
    	 
    	 if (! txtPourcentage.getText().toString().equals(emptyString)){
    		 this.taux = Double.valueOf(txtPourcentage.getText().toString());
    	 }
    	 else
    	 {
    		 this.taux = 0.0;
    	 }
    	 
    	 
    	 if (! txtMensualite.getText().toString().equals(emptyString)){
    		 this.mensualite = Double.valueOf(txtMensualite.getText().toString());    		 
    	 }
    	 else
    	 {
    		 this.mensualite = 0.0;
    	 }
    	 
    	 
    	 if (! txtDuree.getText().toString().equals(emptyString)){
    		 this.duree = Double.valueOf(txtDuree.getText().toString());
    	 }
    	 else
    	 {
    		 this.duree = 0.0;
    	 }
    	 
    	ClasseOutil calcul;
    	calcul = new ClasseOutil(this.montant, this.taux, this.mensualite, this.duree);
    	
    	this.montant = calcul.getMontant();
    	this.taux = calcul.getTaux();
    	this.mensualite = calcul.getMensualite();
    	this.duree = calcul.getDuree();
    	
    	DecimalFormat df = new DecimalFormat("#.#");   
    	
	//Premier texte (en tête des paramètres)
    	lblSummary.setText("Paramètres pris en comptes : ");
    	lblSummary.setTextColor(Color.DKGRAY);
    //Deuxieme texte (affichage des paramètres)
    	lblSummary2.setText(lblSummary2.getText() + "Total du montant emprunté : " + String.valueOf(df.format(calcul.getMontant())) + " € " + Newline);
    	lblSummary2.setText(lblSummary2.getText() + "Taux d'intéret : " + String.valueOf(df.format(calcul.getTaux())) + " % / An " + Newline);
    	lblSummary2.setText(lblSummary2.getText() + "Menusalités : " + String.valueOf(df.format(calcul.getMensualite())) + " € / Mois " + Newline);
    	lblSummary2.setText(lblSummary2.getText() + "Durée : " + String.valueOf(df.format(calcul.getDuree())) + " Années ");
    	
    	if (calcul.getDuree() == 99){
    		Toast.makeText(Main.this, "Montant de mensualité inférieur aux intérets du par mois, impossible de faire le calcul", Toast.LENGTH_LONG).show();
    	}
    	else
    	{
	    	lblSummary4.setText("Résumé :");
	    	lblSummary4.setTextColor(Color.DKGRAY);
	    	
	    	lblSummary5.setText(lblSummary5.getText() + "Total interet du crédit : " + df.format(calcul.getTotalInteret()) + " € " + Newline);
	    	lblSummary5.setText(lblSummary5.getText() + "Total capital amorti : " + df.format(calcul.getTotalAmortissement()) + " € ");

    		lblSummary3.setText("Voir details");
    		lblSummary3.setTextColor(Color.RED);
    	}
    }
    
   
    
  
    
}
