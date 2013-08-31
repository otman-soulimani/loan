package fr.NicolasMasson.CalculPretImmobilier;





import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;


public class APropos extends Activity {
	Button btnWeb;
	Button btnMail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_propos);
		
		btnWeb = (Button) findViewById(R.id.btnWeb);
		btnMail = (Button) findViewById(R.id.btnMail);
	
	
		btnMail.setOnClickListener(new View.OnClickListener(){
	    	public void onClick(final View v) {
	    		Uri uri = Uri.parse("mailto://nico.masson.66@gmail.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
	    	}
		});
		
		btnWeb.setOnClickListener(new View.OnClickListener(){
	    	public void onClick(final View v) {
	    		Uri uri = Uri.parse("http://nicolas-masson.fr/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
	    	}
		});
	
	}
}