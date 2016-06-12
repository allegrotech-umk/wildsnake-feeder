package tech.allegro.mapper;

import org.junit.Before;
import org.junit.Test;

public class ProductItemImageProcessorTest {
    private ProductItemProcessor productItemProcessor;

    @Before
    public void setUp() throws Exception {
        productItemProcessor = new ProductItemProcessor();
    }

    @Test
    public void shouldAcceptTwittWitImageServedByHttps() throws Exception {
        //given

        //When

        //then
    }

    @Test
    public void shouldAcceptTwittWithImageServedByHttpOnly() {
        //given

        //when

        //then
    }

    @Test
    public void shouldAcceptTwittWithManyImagesButSavesOnlyFirstImage() {
        //given

        //when

        //then
    }

    @Test
    public void shouldSkipTwittWithoutImage() {
        //given

        //when

        //then
    }
}
