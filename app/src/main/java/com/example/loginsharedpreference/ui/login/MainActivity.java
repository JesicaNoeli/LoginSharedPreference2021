package com.example.loginsharedpreference.ui.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginsharedpreference.R;
import com.example.loginsharedpreference.model.Usuario;
import com.example.loginsharedpreference.ui.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {
private Button btLogin, btReg;
private EditText editEmail, editPass;
private TextView msjam;
private ViewModelMain vm;
Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelMain.class);
        inicializar();

        vm.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                usuarioBuscar();
            }
        });

        vm.getMsj().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String mensaje) {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Advertencia")
                        .setMessage(mensaje)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });

    }

    private void inicializar(){
        btLogin= findViewById(R.id.btLogin);
        btReg= findViewById(R.id.btReg);
        editPass= findViewById(R.id.editPass);
        editEmail= findViewById(R.id.editEmail);
        msjam= findViewById(R.id.msjam);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.Ingresar(editEmail.getText().toString(), editPass.getText().toString());

            }
        });
        btReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.Registrarse();
            }
        });


    }
    public void usuarioBuscar(){
        Intent intent = new Intent(this, RegistroActivity.class);
        intent.putExtra("login","verUs");
        startActivity(intent);
    }



}
