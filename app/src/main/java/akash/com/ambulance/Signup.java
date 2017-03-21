package akash.com.ambulance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {


    EditText email,pass,conpass,name;


    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    private FirebaseAuth.AuthStateListener mAuthListener;



    ProgressDialog progress;

    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



        email=(EditText) findViewById(R.id.email);
        pass=(EditText) findViewById(R.id.pass);
        conpass=(EditText) findViewById(R.id.confirmpass);
        name=(EditText) findViewById(R.id.name);

        login=(Button) findViewById(R.id.login);


        progress=new ProgressDialog(this);


        mAuth=FirebaseAuth.getInstance();

        mDatabase= FirebaseDatabase.getInstance().getReference().child("user");








    }


    public void loginCreate(View view){





        //Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();


        final String email_str=email.getText().toString();
        final String pass_str=pass.getText().toString();
        final String name_str=name.getText().toString();









        progress.setMessage("Registering...");
        progress.show();


       mAuth.createUserWithEmailAndPassword(email_str,pass_str).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {




                if(task.isSuccessful()){


                     String user_id=mAuth.getCurrentUser().getUid();



                          DatabaseReference currentUser= mDatabase.child(user_id);
                          currentUser.child("name").setValue(name_str);
                          currentUser.child("email").setValue(email_str);




                    progress.dismiss();
                  Intent main= new Intent(Signup.this,MainActivity.class);
                    startActivity(main);


                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();


                }
                else{


                    progress.dismiss();

                    Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();

                }




            }
        });

    }


}
