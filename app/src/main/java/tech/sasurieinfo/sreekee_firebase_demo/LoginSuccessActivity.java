package tech.sasurieinfo.sreekee_firebase_demo;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class LoginSuccessActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        editText=findViewById(R.id.editText3);
        button=findViewById(R.id.button3);
        firebaseAuth=FirebaseAuth.getInstance();
        final String userID=firebaseAuth.getCurrentUser().getUid();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("users");//users will be the name of the db
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editText.getText().toString();
                if(!TextUtils.isEmpty(name)){
                    HashMap hashMap=new HashMap();
                    hashMap.put("name",name);
                    databaseReference.child(userID).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if(task.isSuccessful())
                                Toast.makeText(LoginSuccessActivity.this, "data successfully added", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                    Toast.makeText(LoginSuccessActivity.this, "enter name", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
