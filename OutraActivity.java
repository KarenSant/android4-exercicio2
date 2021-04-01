package matc89.exercicio2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class OutraActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outra);
        Bundle bundle = getIntent().getExtras();
     
        if(bundle != null){
            String nome = bundle.getString("nome");
            editText.setText(nome);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("meuapp", "onSaveInstanceState");
        outState.putString("nome", editText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("meuapp", "onRestoreInstanceState");
        String edit = savedInstanceState.getString("nome");
        if(edit != null){
            editText.setText(edit);
        }
    }

    public void cancelar(View v){
        finish();
    }

    public void confirmar(View v){

        Intent intent = new Intent();
        intent.putExtra("nome", editText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();

    }
}