package kitchenpos.products.application;

import kitchenpos.products.infra.PurgomalumClient;
import kitchenpos.products.tobe.domain.entity.Product;
import kitchenpos.products.tobe.domain.repository.ProductRepository;
import kitchenpos.products.tobe.domain.vo.ProductName;
import kitchenpos.products.tobe.domain.vo.ProductPrice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CreateProductService {
    private final PurgomalumClient purgomalumClient;

    private final ProductRepository productRepository;

    public CreateProductService(
        final PurgomalumClient purgomalumClient,
        final ProductRepository productRepository
    ) {
        this.purgomalumClient = purgomalumClient;
        this.productRepository = productRepository;
    }

    @Transactional
    public Product create(final BigDecimal price, final String name) {
        final ProductPrice productPrice = new ProductPrice(price);
        final ProductName productName = new ProductName(name, purgomalumClient);

        Product product = new Product(productName, productPrice);

        return productRepository.save(product);
    }
}
