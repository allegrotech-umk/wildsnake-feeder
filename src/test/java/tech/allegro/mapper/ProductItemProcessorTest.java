package tech.allegro.mapper;

import org.junit.Before;
import org.junit.Test;
import tech.allegro.domain.Product;
import tech.allegro.io.twitter.domain.Entities;
import tech.allegro.io.twitter.domain.Media;
import tech.allegro.io.twitter.domain.Twitt;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ProductItemProcessorTest {
    private ProductItemProcessor productItemProcessor;

    @Before
    public void setUp() throws Exception {
        productItemProcessor = new ProductItemProcessor();

    }

    @Test
    public void shouldProcessTwitt() throws Exception {
        // Given
        Twitt twitt = new Twitt("texttexttexttexttexttexttexttexttexttext", new Entities(Arrays.asList(new Media("url","url"))) );

        // When
        Product result = productItemProcessor.process(twitt);

        // Then
        assertEquals(result.getName(), "texttexttexttexttex");
        assertEquals(result.getDescription(), "texttexttexttexttexttexttexttexttexttext");
    }

    @Test
    public void shouldProcessShortTwitt() throws Exception {
        // Given
        Twitt twitt = new Twitt("short twitt", new Entities(Arrays.asList(new Media("url","url"))));

        // When
        Product result = productItemProcessor.process(twitt);

        // Then
        assertEquals(result.getName(), "short twitt");
        assertEquals(result.getDescription(), "short twitt");
    }

    @Test
    public void shouldThrowExceptionOnNullTwitt() throws Exception {
        // Given
        Twitt twitt = new Twitt(null, new Entities(Arrays.asList(new Media("url","url"))));

        // When
        productItemProcessor.process(twitt);
    }

    @Test
    public void shouldThrowExceptionOnEmptyTwitt() throws Exception {
        // Given
        Twitt twitt = new Twitt("", new Entities(Arrays.asList(new Media("url","url"))));

        // When
        productItemProcessor.process(twitt);
    }
}