package tech.allegro.mapper;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import tech.allegro.domain.Product;
import tech.allegro.io.twitter.domain.Twitt;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductItemProcessor implements ItemProcessor<Twitt, Product> {
    private final static String dummyUrl = "http://imageStatic/photo";

    private static final Logger logger = LoggerFactory.getLogger(ProductItemProcessor.class);

    private final static Pattern usdPricePattern = Pattern.compile("USD ([0-9]+\\.?[0-9]*)");
    private final static Pattern priceUsdPattern = Pattern.compile("(\\$*([0-9]+\\.?[0-9]*) USD)|(\\$([0-9]+\\.?[0-9]*)[ ]*(USD)*)|(USD ([0-9]+\\.?[0-9]*))");
    private final static int PRODUCT_NAME_BEGIN_INDEX = 0;
    private final static int PRODUCT_NAME_MAX_SIZE = 19;

    @Override
    public Product process(Twitt twitt) throws Exception {
        System.out.println(twitt.getText());
        Product tmp = Optional
                .of(twitt)
                .filter(this::emptyTwitt)
                .map(p -> new Product(
                        getName(p.getText()),
                        p.getText(),
                        dummyUrl,
                        parsePrice(p.getText())
                ))
                .filter(p -> p.getPrice() != null)
                .orElse(null);
        return tmp;
    }

    private BigDecimal parsePrice(String text) {
        Matcher matcher = priceUsdPattern.matcher(text);
        if (!matcher.find())
        {
            return null;
        }
        String result = matcher.group();
        return new BigDecimal(result.replace("USD", "").replace("$", "").trim());
    }

    private boolean emptyTwitt(Twitt twitt) {
        return !Strings.isNullOrEmpty(twitt.getText());
    }

    private String getName(String text) {
        if (text.length() > PRODUCT_NAME_MAX_SIZE) {
            return text.substring(PRODUCT_NAME_BEGIN_INDEX, PRODUCT_NAME_MAX_SIZE);
        }
        return text;
    }
}
