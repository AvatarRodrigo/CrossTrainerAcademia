package com.academia.crosstrainer.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.academia.crosstrainer.R;
import com.academia.crosstrainer.config.ConfiguracaoFirebase;
import com.academia.crosstrainer.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail,  campoPassword, campoPassword2;
    private Button botaoCadastrar;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoNome = findViewById(R.id.txtName);
        campoEmail = findViewById(R.id.txtMail);
        campoPassword  = findViewById(R.id.txtSenha);
        campoPassword2 = findViewById(R.id.txtSenha2);
        botaoCadastrar = findViewById(R.id.btnCadastrar);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = campoNome.getText().toString();
                String email = campoEmail.getText().toString();
                String password = campoPassword.getText().toString();
                String password2 = campoPassword2.getText().toString();

                //Validação
                if(name.isEmpty()){
                    Toast.makeText(CadastroActivity.this,
                            "Preencha o nome!",
                            Toast.LENGTH_SHORT).show();
                }else if(email.isEmpty()){
                    Toast.makeText(CadastroActivity.this,
                            "Preencha o e-mail!",
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
                    usuario = new Usuario();
                    usuario.setNome(name);
                    usuario.setEmail(email);
                    usuario.setSenha(password);
                    cadastrarUsuario();
                }

            }
        });

    }
    public void cadastrarUsuario(){
        autenticacao = ConfiguracaoFirebase.FirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this,
                            "SUCESSO ao cadastrar o usuário!",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CadastroActivity.this,
                            "Erro ao cadastrar o usuário!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}