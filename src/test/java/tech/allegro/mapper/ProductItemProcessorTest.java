package tech.allegro.mapper;

import org.junit.Before;
import org.junit.Test;
import tech.allegro.domain.Product;
import tech.allegro.io.twitter.domain.Twitt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ProductItemProcessorTest {
    private ProductItemProcessor productItemProcessor;

    @Before
    public void setUp() throws Exception {
        productItemProcessor = new ProductItemProcessor();

    }

    @Test
    public void shouldProcessTwitt() throws Exception {
        // Given
        Twitt twitt = new Twitt("texttexttexttexttexttexttexttexttexttext 1 USD");

        // When
        Product result = productItemProcessor.process(twitt);

        // Then
        assertEquals(result.getName(), "texttexttexttexttex");
        assertEquals(result.getDescription(), "texttexttexttexttexttexttexttexttexttext 1 USD");
    }

    @Test
    public void shouldProcessShortTwitt() throws Exception {
        // Given
        Twitt twitt = new Twitt("short twitt 1 USD");

        // When
        Product result = productItemProcessor.process(twitt);

        // Then
        assertEquals(result.getName(), "short twitt 1 USD");
        assertEquals(result.getDescription(), "short twitt 1 USD");
    }

    @Test
    public void shouldSkipNullTwitt() throws Exception {
        // Given
        Twitt twitt = new Twitt(null);

        // When
        Product product = productItemProcessor.process(twitt);

        // then
        assertNull(product);
    }

    @Test
    public void shouldSkipEmptyTwitt() throws Exception {
        // Given
        Twitt twitt = new Twitt("");

        // When
        Product product = productItemProcessor.process(twitt);

        // then
        assertNull(product);
    }
}