package com.devmonsters.interpretador.codigodebarras.titulo.impl;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.devmonsters.interpretador.codigodebarras.titulo.AbstractInterpretadorTituloTest;
import com.devmonsters.interpretador.codigodebarras.titulo.InstituicaoFinanceira;
import com.devmonsters.interpretador.codigodebarras.titulo.Moeda;

public class InterpretadorTituloSantanderTest extends AbstractInterpretadorTituloTest {

    @Override
    protected InstituicaoFinanceira getInstituicaoFinanceira() {
        return InstituicaoFinanceira.SANTANDER;
    }

    @Test
    public void interpretarBoletoTeste() {
        final InterpretadorTituloSantander leitorSantander = new InterpretadorTituloSantander("03391569400000842649095363672000010239510102");
        Assert.assertEquals("03391569400000842649095363672000010239510102", leitorSantander.getCodigoBarras());
        Assert.assertEquals("033", leitorSantander.getBanco());
        Assert.assertEquals(Moeda.REAL, leitorSantander.getMoeda());
        Assert.assertEquals(1, leitorSantander.getDigitoAutoConferenciaCodigoBarras());
        Assert.assertEquals(5694, leitorSantander.getFatorVencimento());
        Assert.assertEquals(new BigDecimal("842.64"), leitorSantander.getValor());
        Assert.assertEquals("9095363672000010239510102", leitorSantander.getCampoLivre());

        Assert.assertEquals(9, leitorSantander.getFixo());
        Assert.assertNull(leitorSantander.getAgencia());
        Assert.assertEquals("0953636", leitorSantander.getContaCobranca());
        Assert.assertEquals("7200001023951", leitorSantander.getNossoNumero());
        Assert.assertEquals(0, leitorSantander.getIOS());
        Assert.assertEquals("102", leitorSantander.getCodigoCarteira());
        Assert.assertTrue(leitorSantander.isContaCobrancaRastreavel());
    }
}