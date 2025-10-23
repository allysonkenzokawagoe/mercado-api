package com.teste.project.modulos.categoria.helper;

import com.teste.project.modulos.categoria.model.Categoria;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoriaHelper {

    public static Categoria umaCategoria() {
        return new Categoria(
                null,
                "Comida"
        );
    }

}
