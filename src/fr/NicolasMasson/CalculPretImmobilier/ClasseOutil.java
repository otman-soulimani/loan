package fr.NicolasMasson.CalculPretImmobilier;

import java.util.ArrayList;
import java.util.List;


public class ClasseOutil {
	
	
	private class outilCalcul {
		double resteDu, interets, capitalAmorti = 0;
	}
	
	private double montant, taux, mensualite, duree;
	
	public double getMontant() {
		return montant;
	}

	public double getTaux() {
		return taux;
	}

	public double getMensualite() {
		return mensualite;
	}

	public double getDuree() {
		return duree;
	}

	private List<Double> interetParAnnee = new ArrayList<Double>();
	private List<Double> amortissementParAnnee = new ArrayList<Double>();
	private List<Double> resteDuParAnnee = new ArrayList<Double>();
	
	
	public ClasseOutil(double unMontant, double unTaux, double uneMensualite, double uneDuree){
		this.montant = unMontant;
		this.taux = unTaux;
		this.mensualite = uneMensualite;
		this.duree = uneDuree;
		this.getParametresManquants();
		
		this.lancerProcess();
		
	}
	
	public void resetAttributes(double unMontant, double unTaux, double uneMensualite, double uneDuree){
		this.montant = unMontant;
		this.taux = unTaux;
		this.mensualite = uneMensualite;
		this.duree = uneDuree;
		
		this.lancerProcess();
	}
	
	
	private void lancerProcess(){
		double totalInteret = 0.0 , totalAmmortissement = 0.0;
		outilCalcul resultProcess = new outilCalcul();
		resultProcess.resteDu = this.montant;
		//Pour chaque année
		if (duree == 0){
			while ((resultProcess.resteDu > resultProcess.capitalAmorti))
	    	{
	    		resultProcess = calculInteret(resultProcess.resteDu);
	    		totalInteret += resultProcess.interets;
	    		totalAmmortissement += resultProcess.capitalAmorti;
	    	}
			interetParAnnee.add(totalInteret);
			amortissementParAnnee.add(totalAmmortissement);
			resteDuParAnnee.add(resultProcess.resteDu);
		}
		else
		{
	    	for (int j = 0; j < duree; j++)
	    	{
		    	for (int i = 0; i < 12; i++){
		    		resultProcess = calculInteret(resultProcess.resteDu);
		    		totalInteret += resultProcess.interets;
		    		totalAmmortissement += resultProcess.capitalAmorti;
		    	}
		    	interetParAnnee.add(totalInteret);
		    	totalInteret = 0.0;
		    	amortissementParAnnee.add(totalAmmortissement);
		    	resteDuParAnnee.add(resultProcess.resteDu);
		    	totalAmmortissement = 0.0;
	    	}
		}
	}
	
	public double getTotalInteret(){
		double totalInteret = 0.0;
		for(int i = 0; i < interetParAnnee.size(); i++){
			totalInteret += interetParAnnee.get(i);
		}
		return totalInteret;
	}
	
	public double getTotalAmortissement(){
		double totalAmortissement = 0.0;
		for(int i = 0; i < amortissementParAnnee.size(); i++){
			totalAmortissement += amortissementParAnnee.get(i);
		}
		return totalAmortissement;
	}
	
	public List<Double> getResteDuParAnnee(){
		return resteDuParAnnee;
	}
	
	public List<Double> getInteretParAnnee(){
		return interetParAnnee;
	}
	
	public List<Double> getAmortissementParAnnee(){
		return amortissementParAnnee;
	}
	
	
	
	
	private outilCalcul calculInteret (double resteDu){
    	outilCalcul lCalcul = new outilCalcul();
    	double pourcentage = (double)taux / 100.0;
    	lCalcul.interets = (double)((resteDu * pourcentage) / 12);
    	lCalcul.capitalAmorti = mensualite - lCalcul.interets;
    	lCalcul.resteDu = resteDu - lCalcul.capitalAmorti;
    	return lCalcul;
    }
	
	private void processDuree(){
		//Initialisation
		duree = 0;
		outilCalcul resultProcess = new outilCalcul();
		resultProcess.resteDu = this.montant;
		resultProcess.capitalAmorti = 0;
		int nbMois = 0;
		
		resultProcess = calculInteret(resultProcess.resteDu);
		nbMois++;
		
		if (resultProcess.interets >= this.mensualite){
			duree = (int)99;
		}
		else
		{
			//Pour chaque année
	    	while ((resultProcess.resteDu > resultProcess.capitalAmorti) && ((nbMois / 12) <= 99))
	    	{
	    		resultProcess = calculInteret(resultProcess.resteDu);
	    		nbMois++;
	    	}
	    	// On ajoute une année qui ne sera pas complète
	    	duree = ((int) nbMois / 12);
		}
    	
	}
	
	private ClasseOutil getParametresManquants(){
		
		if (mensualite <= 0){
			mensualite = (montant * (double)taux / 100.0 / 12) / (1 - Math.pow((1 +  (double)taux / 100.0 / 12),-duree*12));
		}
		
		if (montant <= 0){
			montant = (mensualite*(Math.pow((1+(double)taux / 100.0 / 12),duree*12)-1))/( ((double)taux / 100.0 / 12)*(Math.pow((1+((double)taux / 100.0 / 12)),duree*12)));
		}
		
		if (duree <= 0){
			this.processDuree();
		}
		
		
		return this;
	}
	
}
