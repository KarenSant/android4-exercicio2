package matc89.exercicio2;

import android.content.pm.ActivityInfo;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CorrecaoTest {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityRule = new ActivityScenarioRule<>(
            MainActivity.class);

    @Test
    public void checaValoresIniciaisDaPrimeiraTela() {
        onView(withId(R.id.textView))
                .check(matches(withText("Oi!")));
        onView(withId(R.id.btnTrocar))
                .check(matches(withText("Trocar usuário")));
    }

    @Test
    public void checaValoresIniciaisDaSegundaTela() {
        onView(withId(R.id.btnTrocar))
                .perform(click());

        onView(withId(R.id.editText))
                .check(matches(withText("")));

        onView(withId(R.id.btnConfirmar))
                .check(matches(withText("Confirmar")));

        onView(withId(R.id.btnCancelar))
                .check(matches(withText("Cancelar")));
    }

    @Test
    public void checaMensagemAoTrocarNome() {
        onView(withId(R.id.btnTrocar))
                .perform(click());

        onView(withId(R.id.editText))
                .perform(typeText("Sicrano"));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnConfirmar))
                .perform(click());

        onView(withId(R.id.textView))
                .check(matches(withText("Oi, Sicrano!")));
    }

    @Test
    public void checaSeMensagemNaoMudaAoCancelarATrocaDeNome() {
        onView(withId(R.id.btnTrocar))
                .perform(click());

        onView(withId(R.id.editText))
                .perform(typeText("Sicrano"));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnCancelar))
                .perform(click());

        onView(withId(R.id.textView))
                .check(matches(withText("Oi!")));
    }

    @Test
    public void checaSeMensagemNaoMudaAoCancelarATrocaDeNomePeloBotaoBack() {
        onView(withId(R.id.btnTrocar))
                .perform(click());

        onView(withId(R.id.editText))
                .perform(typeText("Sicrano"));

        Espresso.closeSoftKeyboard();
        Espresso.pressBack();

        onView(withId(R.id.textView))
                .check(matches(withText("Oi!")));
    }

    @Test
    public void checaSeCaixaDeTextoMostraUsuarioAtual() {
        onView(withId(R.id.btnTrocar))
                .perform(click());

        onView(withId(R.id.editText))
                .perform(typeText("Beltrano"));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnConfirmar))
                .perform(click());

        onView(withId(R.id.btnTrocar))
                .perform(click());

        onView(withId(R.id.editText))
                .check(matches(withText("Beltrano")));
    }

    @Test
    public void checaSeStringVaziaResultaEmNaoTerUsuarioAtual() {
        onView(withId(R.id.btnTrocar))
                .perform(click());

        onView(withId(R.id.editText))
                .perform(typeText("Beltrano"));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnConfirmar))
                .perform(click());

        onView(withId(R.id.btnTrocar))
                .perform(click());

        onView(withId(R.id.editText))
                .perform(clearText());

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnConfirmar))
                .perform(click());

        onView(withId(R.id.textView))
                .check(matches(withText("Oi!")));
    }

    @Test
    public void checaSeMensagemSeMantemAposRotacao() {
        onView(withId(R.id.btnTrocar))
                .perform(click());

        onView(withId(R.id.editText))
                .perform(typeText("Beltrano"));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnConfirmar))
                .perform(click());

        rotate(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        onView(withId(R.id.textView))
                .check(matches(withText("Oi, Beltrano!")));
    }

    @Test
    public void checaSeUsuarioAtualSeMantemAposRotacao() {
        onView(withId(R.id.btnTrocar))
                .perform(click());

        onView(withId(R.id.editText))
                .perform(typeText("Beltrano"));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnConfirmar))
                .perform(click());

        rotate(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        onView(withId(R.id.btnTrocar))
                .perform(click());

        onView(withId(R.id.editText))
                .check(matches(withText("Beltrano")));
    }

    private void rotate(int orientation) {
        mActivityRule.getScenario().onActivity(new ActivityScenario.ActivityAction<MainActivity>() {
            @Override
            public void perform(MainActivity activity) {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        });
    }
}
