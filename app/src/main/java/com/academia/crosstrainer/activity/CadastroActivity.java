package com.academia.crosstrainer.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.academia.crosstrainer.R;
import com.academia.crosstrainer.config.ConfiguracaoFirebase;
import com.academia.crosstrainer.helper.Base64Custom;
import com.academia.crosstrainer.model.UserApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoPhone, campoPassword, campoPassword2;
    private Button botaoCadastrar;
    private FirebaseAuth autenticacao;
    private UserApp userApp;
    private Spinner campoSexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoNome = findViewById(R.id.txtName);
        campoEmail = findViewById(R.id.txtMail);
        campoPhone = findViewById(R.id.txtPhone);
        campoPassword  = findViewById(R.id.txtSenha);
        campoPassword2 = findViewById(R.id.txtSenha2);
        botaoCadastrar = findViewById(R.id.btnCadastrar);
        campoSexo = (Spinner) findViewById(R.id.cbxSexo);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.sex, R.layout.spinner_item_cadastro);
        campoSexo.setAdapter(adapter);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = campoNome.getText().toString();
                String email = campoEmail.getText().toString();
                String phone = campoPhone.getText().toString();
                String password = campoPassword.getText().toString();
                String password2 = campoPassword2.getText().toString();
                String sexo = campoSexo.getSelectedItem().toString();

                //Validação
                if(name.isEmpty()){
                    Toast.makeText(CadastroActivity.this,
                            "Preencha o nome!",
                            Toast.LENGTH_SHORT).show();
                }else if(sexo.isEmpty()){
                    Toast.makeText(CadastroActivity.this,
                            "Escolha o sexo!",
                            Toast.LENGTH_SHORT).show();
                }else if(email.isEmpty()){
                    Toast.makeText(CadastroActivity.this,
                            "Preencha o e-mail!",
                            Toast.LENGTH_SHORT).show();
                }else if(phone.isEmpty()){
                    Toast.makeText(CadastroActivity.this,
                            "Preencha o número do celular!",
                            Toast.LENGTH_SHORT).show();
                }else if(password.isEmpty()){
                    Toast.makeText(CadastroActivity.this,
                            "Preencha a senha!",
                            Toast.LENGTH_SHORT).show();
                }else if(!password.equals(password2)){
                    Toast.makeText(CadastroActivity.this,
                            "As senhas não conferem!",
                            Toast.LENGTH_SHORT).show();
                }else{
                    userApp = new UserApp();
                    userApp.setNome(name);
                    userApp.setSex(sexo);
                    userApp.setEmail(email);
                    userApp.setCelular(phone);
                    userApp.setSenha(password);
                    cadastrarUser();
                }

            }
        });

    }
    public void cadastrarUser(){
        autenticacao = ConfiguracaoFirebase.FirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                userApp.getEmail(), userApp.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String idUser = Base64Custom.codeBase64(userApp.getEmail());
                    userApp.setIdUser(idUser);
                    userApp.save();
                   finish();
                }else{
                    String ex = "";
                    try {
                        throw task.getException();

                    }catch (FirebaseAuthWeakPasswordException e){
                        ex = "Digite uma senha mais forte!";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        ex = "Por favor, digite um e-mail válido!";
                    }catch (FirebaseAuthUserCollisionException e){
                        ex = "Este e-mail já foi cadastrado!";
                    }catch (Exception e){
                        ex = "Erro ao cadastrar o usuário: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this,
                            ex,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}