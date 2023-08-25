package com.selena.efinalrosalestello;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.snackbar.Snackbar;
import com.selena.efinalrosalestello.databinding.ActivityPrincipalBinding;
import com.selena.efinalrosalestello.fragments.HomeFragment;
public class PrincipalActivity extends AppCompatActivity {

    private ActivityPrincipalBinding binding;
    public static String EMAIL = "EMAIL";
    private String email;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrincipalBinding.inflate(getLayoutInflater());
        sharedPreferences = getSharedPreferences(LoginActivity.SESSION_PREFERENCE, MODE_PRIVATE);
        setContentView(binding.getRoot());
        setSupportActionBar(binding.tbPlayzoom);
        addHomeFragment();
    }

    private void addHomeFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(binding.fcvMain.getId(), new HomeFragment()).commit();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cat_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search){
            Snackbar.make(binding.getRoot(),"Favoritos", Snackbar.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.ic_logout) {
            new AlertDialog.Builder(this)
                    .setTitle("Log out of app")
                    .setMessage("¿Salir de app?")
                    .setPositiveButton("Exit", (dialog, which) -> {
                        sharedPreferences.edit().clear().apply();
                        startActivity(new Intent(this, LoginActivity.class));
                        finish();
                    })
                    .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss())
                    .show();
            return true;
        } else {
            return false;
        }

    }
}