package com.example.loginmvc.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.loginmvc.R;
import com.example.loginmvc.controller.UsuarioController;
import com.example.loginmvc.model.Usuario;

public class MainActivity extends AppCompatActivity {
    private EditText email, senha;
    private Button entrar, criar;

    Usuario usuario;
    UsuarioController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initComponents();

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //O usuario clicou no botao entrar. Primeiro verifico se ele digitou todos os campos:
                boolean camposValidados = validaCampos();

                if(validaCampos()) {
                    //instancio um usuario e um usuarioControler
                    usuario    = new Usuario();
                    controller = new UsuarioController(getApplicationContext());

                    //pego o que o usuario digitou e passo para variaveis locais
                    String user = usuario.getUserEmail().toString();
                    String pass = usuario.getPassword().toString();

                    //passo das variaveis locais para o objeto usuario
                    usuario.setUserEmail(user);
                    usuario.setPassword(pass);

                    //verifico se o usuario e senha existem na tabela
                    boolean isCheckUser = controller.usuarioeSenha(user, pass);

                    //se nao existir envio uma mensagem de usuario nao cadastrado
                    if(!isCheckUser){
                        mensagem("Usuario Ainda Nao Cadastrado");
                    }else{
                        //caso contrario mando ele para a pagina principal HomeActivity
                        Intent home = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(home);
                    }
                }else{
                    mensagem("Preencha todos os campos para entrar");
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initComponents() {
        email  = findViewById(R.id.edt_email);
        senha  = findViewById(R.id.edt_senha);
        entrar = findViewById(R.id.sign_in);
        criar  = findViewById(R.id.sign_up);
    }

    private void mensagem(String texto) {
        Toast.makeText(MainActivity.this, texto, Toast.LENGTH_SHORT).show();
    }

    private boolean validaCampos() {
        boolean camposValidados = true;
        if (email.getText().toString().equals("") ){
            camposValidados = false;
            email.setError("Digite o email");
            email.requestFocus();
        }
        if (senha.getText().toString().equals("") ){
            camposValidados = false;
            senha.setError("Digite a senha");
            senha.requestFocus();
        }
        return camposValidados;
    }
}