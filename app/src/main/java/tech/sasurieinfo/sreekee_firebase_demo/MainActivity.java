package tech.sasurieinfo.sreekee_firebase_demo;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
private EditText editText,editText1;
private Button button,button1;
private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        editText=findViewById(R.id.editText);
        editText1=findViewById(R.id.editText2);
        button=findViewById(R.id.button);
        button1=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=editText.getText().toString();
                String Pass=editText1.getText().toString();
                if(!TextUtils.isEmpty(Email) &&!TextUtils.isEmpty(Pass)){
                    firebaseAuth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                    Toast.makeText(MainActivity.this, "Enter a valid Username and/or password", Toast.LENGTH_LONG).show();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=editText.getText().toString();
                String Pass=editText1.getText().toString();
                if(!TextUtils.isEmpty(Email) &&!TextUtils.isEmpty(Pass)) {
                firebaseAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(MainActivity.this,LoginSuccessActivity.class));
                        }
                        else
                            Toast.makeText(MainActivity.this, "Not a valid user "+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                }
                }
        });
    }
}
