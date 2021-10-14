package com.academia.crosstrainer.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.academia.crosstrainer.MainActivity;
import com.academia.crosstrainer.R;
import com.academia.crosstrainer.config.ConfiguracaoFirebase;
import com.academia.crosstrainer.model.UserApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private Button btnEnter;
    private UserApp userApp;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        campoEmail = findViewById(R.id.txtMail);
        campoSenha = findViewById(R.id.txtSenha);
        btnEnter = findViewById(R.id.btnAcessar);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = campoEmail.getText().toString();
                String password = campoSenha.getText().toString();
                if(email.isEmpty()){
                    Toast.makeText(LoginActivity.this,
                            "Preencha o e-mail!",
                            Toast.LENGTH_SHORT).show();
                }else if(password.isEmpty()){
                    Toast.makeText(LoginActivity.this,
                            "Preencha a senha!",
                            Toast.LENGTH_SHORT).show();
                }else{
                    userApp = new UserApp();
                    userApp.setEmail(email);
                    userApp.setSenha(password);
                    validateLogin();
                }
            }
        });
    }
    public void validateLogin(){
        autenticacao = ConfiguracaoFirebase.FirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                userApp.getEmail(),
                userApp.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    openScreenMain();
                }else{
                    String ex = "";
                    try {
                        throw task.getException();

                    }catch (FirebaseAuthInvalidUserException e){
                        ex = "Usuário não está cadastrado!";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        ex = "E-mail e senha não corresponde a um usuário cadastrado!";
                    }catch (Exception e){
                        ex = "Erro ao cadastrar o usuário: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this,
                            ex,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void openScreenMain(){
         startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}