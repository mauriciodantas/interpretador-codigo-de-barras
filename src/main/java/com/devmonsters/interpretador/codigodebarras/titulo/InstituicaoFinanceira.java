package com.devmonsters.interpretador.codigodebarras.titulo;

import java.util.Arrays;
import java.util.List;

import com.devmonsters.interpretador.codigodebarras.Interpretador;
import com.devmonsters.interpretador.codigodebarras.titulo.impl.InterpretadorTituloBradesco;
import com.devmonsters.interpretador.codigodebarras.titulo.impl.InterpretadorTituloGenerico;
import com.devmonsters.interpretador.codigodebarras.titulo.impl.InterpretadorTituloHsbc;
import com.devmonsters.interpretador.codigodebarras.titulo.impl.InterpretadorTituloItau;
import com.devmonsters.interpretador.codigodebarras.titulo.impl.InterpretadorTituloSantander;

public enum InstituicaoFinanceira {

    GENERICO("000", InterpretadorTituloGenerico.class),
    BRADESCO("237", InterpretadorTituloBradesco.class),
    HSBC("399", InterpretadorTituloHsbc.class),
    ITAU("341", InterpretadorTituloItau.class),
    SANTANDER("033", InterpretadorTituloSantander.class);

    private final String codigo;
    private final List<Class<?>> interpretadores;

    private InstituicaoFinanceira(final String codigo, final Class<?>... interpretadores) {
        this.codigo = codigo;
        this.interpretadores = Arrays.asList(interpretadores);
    }

    public String getCodigo() {
        return this.codigo;
    }

    /**
     * Percorre a lista de interpretadores internos desta instituicao financeira, instanciando para identificar se e valido para o codigo de barras informado.
     * 
     * @param codigoDeBarras Codigo de barras para interpretar.
     * @return Instancia de intepretador; ou nulo se nao localizar um valido dentro do banco.
     * @throws Exception Erros de instanciacao.
     */
    public Interpretador getInterpretadorCodigoDeBarras(final String codigoDeBarras) throws Exception {
        for (final Class<?> interpretador : this.interpretadores) {
            final Interpretador instanciaInterpretador = (Interpretador) Class.forName(interpretador.getName()).getConstructor(String.class).newInstance(codigoDeBarras);
            if (instanciaInterpretador.isValidoParaInterpretacao()) {
                return instanciaInterpretador;
            }
        }
        return null;
    }

    public static InstituicaoFinanceira valueOfCodigo(final String codigo) {
        if (!codigo.matches("[0-9]{3}")) {
            throw new IllegalArgumentException("O c\u00f3digo da institui\u00e7\u00e3o financeira deve ser 3 d\u00edgitos num\u00e9ricos.");
        }
        for (final InstituicaoFinanceira instituicaoFinanceira : InstituicaoFinanceira.values()) {
            if (instituicaoFinanceira.getCodigo().equals(codigo)) {
                return instituicaoFinanceira;
            }
        }
        return GENERICO;
    }
}