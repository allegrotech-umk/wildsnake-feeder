package tech.allegro.mapper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tech.allegro.domain.Product;
import tech.allegro.io.twitter.domain.Twitt;

import java.math.BigDecimal;

public class ProductItemPriceProcessorTest {
    private ProductItemProcessor productItemProcessor;

    @Before
    public void setUp() throws Exception {
        productItemProcessor = new ProductItemProcessor();
    }

    @Test
    public void shouldAcceptTwittWithPriceWherePriceIsFirst() throws Exception {
        //given
        Twitt twitt = new Twitt("Elegant Snake Skin Contrast Ankle Boots 49.99 USD Get here: http://bit.ly/1M8ScYj via @outfy");

        //When
        Product product = productItemProcessor.process(twitt);

        //then
        Assert.assertEquals(new BigDecimal("49.99"), product.getPrice());
        }

    @Test
    public void shouldAcceptTwittWithPriceWhereUSDisFirst() throws Exception{
        //given
        Twitt twitt = new Twitt("Elegant Snake Skin Contrast Ankle Boots USD 29.99 Get here: http://bit.ly/1M8ScYj via @outfy");

        //When
        Product product = productItemProcessor.process(twitt);

        //then
        Assert.assertEquals(new BigDecimal("29.99"), product.getPrice());
    }

    @Test
    public void shouldAcceptTwittWithPriceWhereDollarisFirst() throws Exception{
        //given
        Twitt twitt = new Twitt("Elegant Snake Skin Contrast Ankle Boots $ 39.99 USD Get here: http://bit.ly/1M8ScYj via @outfy");

        //When
        Product product = productItemProcessor.process(twitt);

        //then
        Assert.assertEquals(new BigDecimal("39.99"), product.getPrice());
    }

    @Test
    public void shouldIgnoreTwittWithoutPrice() throws Exception {
        //given
        Twitt twitt = new Twitt("Elegant Snake Skin Contrast Ankle Boots Get here: http://bit.ly/1M8ScYj via @outfy");

        //When
        Product product = productItemProcessor.process(twitt);

        //then
        Assert.assertEquals(null, product);
    }

}
