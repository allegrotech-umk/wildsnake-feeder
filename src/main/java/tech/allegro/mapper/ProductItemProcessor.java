package tech.allegro.mapper;

import com.google.common.base.Strings;
import org.springframework.batch.item.ItemProcessor;
import tech.allegro.domain.Product;
import tech.allegro.io.twitter.domain.Media;
import tech.allegro.io.twitter.domain.Twitt;

import java.math.BigDecimal;
import java.util.Optional;

public class ProductItemProcessor implements ItemProcessor<Twitt, Product> {
    private final static String dummyUrl = "http://imageStatic/photo";
    private final static int PRODUCT_NAME_BEGIN_INDEX = 0;
    private final static int PRODUCT_NAME_MAX_SIZE = 19;

    @Override
    public Product process(Twitt twitt) throws Exception {

        return Optional
                .of(twitt)
                .filter(this::nonEmptyText)
                .filter(this::nonEmptyImageUrl)
                .map(t -> new Product(
                        getName(twitt.getText()),
                        twitt.getText(),
                        retriveUrlFromTwitt(twitt.getEntities().getMedia().get(0)),
                        BigDecimal.ONE
                ))
                .orElse(null);


    }

    private boolean nonEmptyText(Twitt twitt) {
        return !Strings.isNullOrEmpty(twitt.getText());

    }

    private boolean nonEmptyImageUrl(Twitt twitt) {

        if(!Strings.isNullOrEmpty(twitt.getEntities().getMedia().get(0).getMedia_url()) &&
                !Strings.isNullOrEmpty(twitt.getEntities().getMedia().get(0).getMedia_url_https())) return true;
        return false;
    }
    private String getName(String text) {
        if (text.length() > PRODUCT_NAME_MAX_SIZE) {
            return text.substring(PRODUCT_NAME_BEGIN_INDEX, PRODUCT_NAME_MAX_SIZE);
        }
        return text;
    }

    private String retriveUrlFromTwitt(Media media){
        if( media.getMedia_url_https() != null && !"".equals(media.getMedia_url_https())) return media.getMedia_url_https();

        return media.getMedia_url();
    }
}
