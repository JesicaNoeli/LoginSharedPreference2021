package com.example.loginsharedpreference.ui.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.loginsharedpreference.R;
import com.example.loginsharedpreference.model.Usuario;
import com.example.loginsharedpreference.ui.login.MainActivity;
import com.example.loginsharedpreference.ui.login.ViewModelMain;

public class RegistroActivity extends AppCompatActivity {
   private EditText dni, nombre, apellido, email, password;
   private TextView msjerror;
   private Button btGuardar;
   private ViewModelRegistro vmr;
   Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        vmr= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
                .create(ViewModelRegistro.class);
        inicializarVistas();

        vmr.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                dni.setText(usuario.getDni() + "");
                nombre.setText(usuario.getNombre());
                apellido.setText(usuario.getApellido());
                email.setText(usuario.getEmail());
                password.setText(usuario.getPassword());
            }
        });
        Bundle datos = this.getIntent().getExtras();
        if(datos!=null) {

            vmr.mostrar();
        }



    }
    public void inicializarVistas() {
        dni = findViewById(R.id.editDni);
        nombre = findViewById(R.id.editNom);
        apellido = findViewById(R.id.editApell);
        email = findViewById(R.id.edtMail);
        password = findViewById(R.id.edtPass);
        msjerror = findViewById(R.id.msjreg);
        btGuardar= findViewById(R.id.btGuardar);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario us = new Usuario();
                if (!dni.getText().toString().isEmpty() && !nombre.getText().toString().isEmpty() && !apellido.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    us.setDni(Long.parseLong(dni.getText().toString()));
                    us.setNombre(nombre.getText().toString());
                    us.setApellido(apellido.getText().toString());
                    us.setEmail(email.getText().toString());
                    us.setPassword(password.getText().toString());
                    vmr.guardar(us);


                } else {
                    msjerror.setText("Todos los campos deben estar completos");
                }

            }
        });
    }



}