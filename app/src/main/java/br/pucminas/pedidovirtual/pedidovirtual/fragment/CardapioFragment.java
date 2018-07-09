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
import br.pucminas.pedidovirtual.pedidovirtual.activity.EstabelecimentoActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.MaskTransformation;

public class CardapioFragment extends Fragment {

    @BindView(R.id.RV_prato)
    RecyclerView RV_prato;

    @BindView(R.id.IV_estabelecimento)
    ImageView IV_estabelecimento;
    @BindView(R.id.TV_endereco)
    TextView TV_endereco;
    @BindView(R.id.TV_mesa)
    TextView TV_mesa;
    @BindView(R.id.TV_nome)
    TextView TV_nome;

    private EstabelecimentoActivity activity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cardapio, container, false);

        ButterKnife.bind(this, view);

        activity = (EstabelecimentoActivity) getActivity();
        loadUI();


        return view;
    }

    private void loadUI() {
        try {
            String rua = activity.getEstabelecimento().getRua();
            String numero = activity.getEstabelecimento().getNumero();
            String bairro = activity.getEstabelecimento().getBairro();
            String cidade = activity.getEstabelecimento().getCidade();
            String estado = activity.getEstabelecimento().getEstado();

            Locale brazil = new Locale("pt", "br");
            TV_endereco.setText(String.format(brazil, "%1$s, %2$s - %3$s, %4$s/%5$s ", rua, numero, bairro, cidade, estado));
            TV_mesa.setText(String.valueOf(activity.getMesa().getMesa()));
            TV_nome.setText(activity.getEstabelecimento().getNome());

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