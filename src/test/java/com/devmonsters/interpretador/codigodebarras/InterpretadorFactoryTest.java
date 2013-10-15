package com.devmonsters.interpretador.codigodebarras;

import org.junit.Assert;
import org.junit.Test;

import com.devmonsters.interpretador.codigodebarras.arrecadacao.InterpretadorArrecadacao;
import com.devmonsters.interpretador.codigodebarras.titulo.impl.InterpretadorTituloBancoDoBrasilConvenio7Posicoes;
import com.devmonsters.interpretador.codigodebarras.titulo.impl.InterpretadorTituloBancoDoBrasilNossoNumero11Posicoes;
import com.devmonsters.interpretador.codigodebarras.titulo.impl.InterpretadorTituloBancoDoBrasilNossoNumero17Posicoes;
import com.devmonsters.interpretador.codigodebarras.titulo.impl.InterpretadorTituloBradesco;
import com.devmonsters.interpretador.codigodebarras.titulo.impl.InterpretadorTituloCaixaEconomicaFederalNossoNumero11Posicoes;
import com.devmonsters.interpretador.codigodebarras.titulo.impl.InterpretadorTituloCaixaEconomicaFederalNossoNumero16Posicoes;
import com.devmonsters.interpretador.codigodebarras.titulo.impl.InterpretadorTituloGenerico;
import com.devmonsters.interpretador.codigodebarras.titulo.impl.InterpretadorTituloHsbc;
import com.devmonsters.interpretador.codigodebarras.titulo.impl.InterpretadorTituloItau;
import com.devmonsters.interpretador.codigodebarras.titulo.impl.InterpretadorTituloSantander;

public class InterpretadorFactoryTest {

    @Test(expected = NullPointerException.class)
    public void nullPointerSeParametroNulo() throws Exception {
        new InterpretadorFactory().getInterpretador(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void oCodigoDeBarrasDevePossuir44Digitos() throws Exception {
        new InterpretadorFactory().getInterpretador("399955672000011190028433067799");
    }

    @Test
    public void pagamentoDeArrecadacao() throws Exception {
        Assert.assertEquals(InterpretadorArrecadacao.class, new InterpretadorFactory().getInterpretador("85850000001045402941309401000000130151769700").getClass());
    }

    @Test
    public void bancoDesconhecidoDeveRetornarGenerico() throws Exception {
        Assert.assertEquals(InterpretadorTituloGenerico.class, new InterpretadorFactory().getInterpretador("99995567200001119002843306779912340123456001").getClass());
    }

    @Test
    public void banco399deveRetornarHSBC() throws Exception {
        Assert.assertEquals(InterpretadorTituloHsbc.class, new InterpretadorFactory().getInterpretador("39995567200001119002843306779912340123456001").getClass());
    }

    @Test
    public void banco237deveRetornarBradesco() throws Exception {
        Assert.assertEquals(InterpretadorTituloBradesco.class, new InterpretadorFactory().getInterpretador("23796565100000658712657090008068809100000120").getClass());
    }

    @Test
    public void banco341deveRetornarItau() throws Exception {
        Assert.assertEquals(InterpretadorTituloItau.class, new InterpretadorFactory().getInterpretador("34196568300043759501572273150081234987655000").getClass());
    }

    @Test
    public void banco033deveRetornarSantander() throws Exception {
        Assert.assertEquals(InterpretadorTituloSantander.class, new InterpretadorFactory().getInterpretador("03391569400000842649123456772000010239510102").getClass());
    }

    @Test
    public void banco104deveRetornarCaixaEconomicaFederalNossoNumero11PosicoesCarteiraRapida() throws Exception {
        Assert.assertEquals(InterpretadorTituloCaixaEconomicaFederalNossoNumero11Posicoes.class, new InterpretadorFactory().getInterpretador("10493567800000380009000004369025500300012340").getClass());
    }

    @Test
    public void banco104deveRetornarCaixaEconomicaFederalNossoNumero11PosicoesCarteiraRapidaSemRegistro() throws Exception {
        Assert.assertEquals(InterpretadorTituloCaixaEconomicaFederalNossoNumero11Posicoes.class, new InterpretadorFactory().getInterpretador("10492563800001113838210881967099600312345678").getClass());
    }

    @Test
    public void banco104deveRetornarCaixaEconomicaFederalNossoNumero16Posicoes() throws Exception {
        Assert.assertEquals(InterpretadorTituloCaixaEconomicaFederalNossoNumero16Posicoes.class, new InterpretadorFactory().getInterpretador("10491567300000146811234503698700000010238279").getClass());
    }

    @Test
    public void banco104SemInterpretadorEspecificoDeveRetornarInterpretadorGenerico() throws Exception {
        Assert.assertEquals(InterpretadorTituloGenerico.class, new InterpretadorFactory().getInterpretador("10497571500000297682110130999200040291810255").getClass());
    }

    @Test
    public void banco001deveRetornarBancoDoBrasilNossoNumero11Posicoes() throws Exception {
        Assert.assertEquals(InterpretadorTituloBancoDoBrasilNossoNumero11Posicoes.class, new InterpretadorFactory().getInterpretador("00196565100000658712657090008068809100000120").getClass());
    }

    @Test
    public void banco001deveRetornarBancoDoBrasilNossoNumero17Posicoes() throws Exception {
        Assert.assertEquals(InterpretadorTituloBancoDoBrasilNossoNumero17Posicoes.class, new InterpretadorFactory().getInterpretador("00192433400003818174568230456823090000000821").getClass());
    }

    @Test
    public void banco001deveRetornarBancoDoBrasilConvenio7Posicoes() throws Exception {
        Assert.assertEquals(InterpretadorTituloBancoDoBrasilConvenio7Posicoes.class, new InterpretadorFactory().getInterpretador("00199442700000270500000001535175000021679818").getClass());
    }

    @Test
    public void aPartirDaLinhaDigitavelIdentificarInterpretadorItau() throws Exception {
        final Interpretador interpretador = new InterpretadorFactory().getInterpretador("34191751654801754297711099270008158430000015480");
        Assert.assertEquals("34191584300000154801751648017542971109927000", interpretador.getCodigoDeBarras());
        Assert.assertEquals(InterpretadorTituloItau.class, interpretador.getClass());
    }

    @Test
    public void aPartirDaLinhaDigitavelIdentificarInterpretadorBradesco() throws Exception {
        final Interpretador interpretador = new InterpretadorFactory().getInterpretador("23790665059113282099303011830001258750000033400");
        Assert.assertEquals("23792587500000334000665091132820990301183000", interpretador.getCodigoDeBarras());
        Assert.assertEquals(InterpretadorTituloBradesco.class, interpretador.getClass());
    }
}