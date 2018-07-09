package br.pucminas.pedidovirtual.pedidovirtual.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import br.pucminas.pedidovirtual.pedidovirtual.R;
import br.pucminas.pedidovirtual.pedidovirtual.activity.CardapioActivity;
import br.pucminas.pedidovirtual.pedidovirtual.activity.EstabelecimentoActivity;
import br.pucminas.pedidovirtual.pedidovirtual.model.Estabelecimento;
import br.pucminas.pedidovirtual.pedidovirtual.model.Mesa;
import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.MaskTransformation;

public class CardapioFragment extends Fragment {

    private CardapioActivity activity;
//    private EstabelecimentoActivity activity;

    @BindView(R.id.IV_estabelecimento)
    ImageView IV_estabelecimento;

//    @BindView(R.id.RV_prato)
//    RecyclerView RV_prato;
//
//    @BindView(R.id.TV_endereco)
//    TextView TV_endereco;
//
//    @BindView(R.id.TV_mesa)
//    TextView TV_mesa;
//
//    @BindView(R.id.TV_nome)
//    TextView TV_nome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cardapio, container, false);

        ButterKnife.bind(this, view);

        activity = (CardapioActivity) getActivity();

        loadUI();

        return view;
    }

    private void loadUI() {
        try {

            Locale brazil = new Locale("pt", "br");

//            TV_mesa.setText(String.valueOf(activity.getMesa().getMesa()));
//            TV_nome.setText(activity.getEstabelecimento().getNome());

            Picasso.get().load(activity.getEstabelecimento().getFoto())
                    .fit()
                    .centerCrop()
                    .transform(new MaskTransformation(activity, R.drawable.ic_shape_circle))
                    .into(IV_estabelecimento);

        } catch (Exception ex) {
            Toast.makeText(activity, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }



}