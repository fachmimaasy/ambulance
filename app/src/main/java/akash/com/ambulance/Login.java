package akash.com.ambulance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {


    EditText username,pass;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        username=(EditText) findViewById(R.id.username);
        pass=(EditText) findViewById(R.id.password);

        mAuth=FirebaseAuth.getInstance();

        progress=new ProgressDialog(this);






    }

    public void loginCreate(View view){


        String email=username.getText().toString();
        String password= pass.getText().toString();


        progress.setMessage("Loging in....");
        progress.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {

                            progress.dismiss();
                            Toast.makeText(Login.this, "failed",
                                    Toast.LENGTH_SHORT).show();
                        }

                        else{

                            progress.dismiss();

                            Intent main=new Intent(Login.this,MainActivity.class);
                            startActivity(main);


                        }


                        // ...
                    }
                });


    }




}
