package com.devmonsters.interpretador.codigodebarras;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Interface basica para interpretacao.<br>
 * Retorna apenas os dados mais basicos e que permitam ser coletados de titulos e arrecadacoes.
 * 
 * @author Fernando M. Pinheiro
 */
public interface Interpretador {

    /**
     * Retorna o codigo de barras em si, sem alteracoes.
     * 
     * @return Codigo de barras informado.
     */
    String getCodigoDeBarras();

    /**
     * Valida se este codigo de barras e valido para este interpretador, permitindo testar qual sera o mais adequado.
     * 
     * @return Se o codigo de barras e valido para interpretacao.
     */
    boolean isValidoParaInterpretacao();

    TipoDocumento getTipoDocumento();

    BigDecimal getValor();

    Date getDataVencimento();
}