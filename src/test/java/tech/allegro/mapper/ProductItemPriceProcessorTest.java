package tech.allegro.mapper;

import org.junit.Before;
import org.junit.Test;
import tech.allegro.domain.Product;
import tech.allegro.io.twitter.domain.Twitt;

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

        }

    @Test
    public void shouldAcceptTwittWithPriceWhereUSDisFirst() throws Exception{
        //given

        //When

        //then
    }

    @Test
    public void shouldAcceptTwittWithPriceWhereDollarisFirst() throws Exception{
        //given

        //When

        //then

    }

    @Test
    public void shouldAcceptTwittWithPriceWhereDollarIsFirstAndNoUSD() throws Exception{
        //given

        //When

        //then
    }

    @Test
    public void shouldIgnoreTwittWithoutPrice() throws Exception {
        //given

        //when

        //then
    }

}
