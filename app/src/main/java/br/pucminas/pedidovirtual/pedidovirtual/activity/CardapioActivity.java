package br.pucminas.pedidovirtual.pedidovirtual.activity;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.widget.Toast;

import br.pucminas.pedidovirtual.pedidovirtual.R;
import br.pucminas.pedidovirtual.pedidovirtual.Utils.PersistenceUtil;
import br.pucminas.pedidovirtual.pedidovirtual.fragment.CardapioFragment;
import br.pucminas.pedidovirtual.pedidovirtual.fragment.EstabelecimentoFragment;
import br.pucminas.pedidovirtual.pedidovirtual.model.Estabelecimento;
import br.pucminas.pedidovirtual.pedidovirtual.model.Mesa;
import butterknife.ButterKnife;
import io.realm.Realm;

public class CardapioActivity extends AppCompatActivity {

    private static int CURRENT_FRAGMENT;
    private Estabelecimento estabelecimento;
    private Mesa mesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);

        ButterKnife.bind(this);

//        if (getIntent().hasExtra("qrcode")) {
            loadCardapio();
            CURRENT_FRAGMENT = 2;
            loadFragment(new CardapioFragment());
//        }
    }


    private void loadFragment(Fragment fragment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            fragment.setEnterTransition(new Slide(Gravity.END));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FC_fragment, fragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    private void loadCardapio() {
        Realm realm = Realm.getInstance(PersistenceUtil.realmConfig());
        mesa = realm.where(Mesa.class).equalTo("idQRCode", getIntent().getIntExtra("qrcode", 0)).findFirst();

        if (mesa != null) {
            estabelecimento = realm.where(Estabelecimento.class).equalTo("idEstabelecimento", mesa.getIdEstabelecimento()).findFirst();
        } else {
            Toast.makeText(this, R.string.erro_carregar_estabelecimento, Toast.LENGTH_LONG).show();
        }
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public Mesa getMesa() {
        return mesa;
    }

}
