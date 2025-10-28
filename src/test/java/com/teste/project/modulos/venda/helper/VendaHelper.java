package com.teste.project.modulos.venda.helper;

import com.teste.project.modulos.produtos.dto.VendaDto;
import com.teste.project.modulos.produtos.model.ProdutoVenda;
import com.teste.project.modulos.venda.enums.ETipoPagamento;
import com.teste.project.modulos.venda.model.Venda;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.List;

import static com.teste.project.modulos.endereco.helper.EnderecoHelper.umEndereco;
import static com.teste.project.modulos.usuario.helper.UsuarioHelper.umUsuario;

@UtilityClass
public class VendaHelper {

    public static Venda umaVenda() {
        return new Venda(
                null,
                100.0,
                LocalDateTime.now().withNano(0),
                ETipoPagamento.CARTAO_DEBITO,
                List.of(new ProdutoVenda()),
                umEndereco(),
                umUsuario()
        );
    }

    public static VendaDto umaVendaDto() {
        return new VendaDto(
                1,
                umEndereco(),
                "Allyson"
        );
    }

}
