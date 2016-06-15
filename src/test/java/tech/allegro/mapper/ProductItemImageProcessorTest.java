package tech.allegro.mapper;

import org.junit.Before;
import org.junit.Test;
import tech.allegro.domain.Product;
import tech.allegro.io.twitter.domain.Entities;
import tech.allegro.io.twitter.domain.Media;
import tech.allegro.io.twitter.domain.Twitt;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ProductItemImageProcessorTest {
    private ProductItemProcessor productItemProcessor;

    @Before
    public void setUp() throws Exception {
        productItemProcessor = new ProductItemProcessor();
    }

    @Test
    public void shouldAcceptTwittWitImageServedByHttps() throws Exception {
        //given
        Twitt twitt = new Twitt("Test Twit", new Entities(Arrays.asList(new Media("http://lorempixel.com/400/200/", "https://ssl.webpack.de/lorempixel.com/400/200/"))));
        //When
        Product result = productItemProcessor.process(twitt);
        //then
        assertEquals(result.getImage_url(), "https://ssl.webpack.de/lorempixel.com/400/200/");
    }

    @Test
    public void shouldAcceptTwittWithImageServedByHttpOnly() throws Exception {
        //given
        Twitt twitt = new Twitt("Test Twit", new Entities(Arrays.asList(new Media("http://lorempixel.com/400/200/", ""))));
        //When
        Product result = productItemProcessor.process(twitt);
        //then
        assertEquals(result.getImage_url(), "http://lorempixel.com/400/200/");
    }

    @Test
    public void shouldAcceptTwittWithManyImagesButSavesOnlyFirstImage() throws Exception {
        //given
        Twitt twitt = new Twitt("Test Twit", new Entities(
                Arrays.asList(
                        new Media("http://lorempixel.com/400/200/", "https://ssl.webpack.de/lorempixel.com/400/200/"),
                        new Media("http://lorempixel.com/500/400/", "https://ssl.webpack.de/lorempixel.com/500/300/"),
                        new Media("http://lorempixel.com/600/700/", "https://ssl.webpack.de/lorempixel.com/600/400/")
                )
        )
        );
        //When
        Product result = productItemProcessor.process(twitt);
        //then
        assertEquals(result.getImage_url(), "https://ssl.webpack.de/lorempixel.com/400/200/");
    }

    @Test
    public void shouldSkipTwittWithoutImage() throws Exception {
        //given
        Twitt twitt = new Twitt("Test Twit", new Entities(Arrays.asList(new Media("", ""))));
        //When
        Product result;
        result = productItemProcessor.process(twitt);
        //then
        assertEquals(result, null);
    }
}
