package matc89.exercicio2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Minha Activity");

        if (savedInstanceState != null){
            this.nome = savedInstanceState.getString("nome");
        }

        textView = findViewById(R.id.textView);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("meuapp", "onSaveInstanceState");
        outState.putString("nome", nome);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("meuapp", "onRestoreInstanceState");


        String nome = savedInstanceState.getString("nome");
        if(nome != null) {
            textView.setText("Oi, " + nome + "!");
        }
    }

    public void trocar(View v){
        Intent intent = new Intent(this, OutraActivity.class);
        intent.putExtra("nome", getNome());
        startActivityForResult(intent,1234);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1234 && resultCode == RESULT_OK && data != null){

            Bundle bundle = data.getExtras();
            setNome(bundle.getString("nome"));
            if(getNome().isEmpty()){
                textView.setText("Oi!");
            }else{
                textView.setText("Oi, " + getNome() + "!");
            }

        }
    }


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
