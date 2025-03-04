package com.eshop.sonny.service.service_Impl;

import com.eshop.sonny.Events.OrderCreatedEvent;
import com.eshop.sonny.QuerySpecification.QueryOrderSpecification;
import com.eshop.sonny.QuerySpecification.Spacifications.OrderSpecification;
import com.eshop.sonny.dto.Request.OrderRequestDto;
import com.eshop.sonny.mapper.mappersClasses.OrderMapper;
import com.eshop.sonny.model.Buyer;
import com.eshop.sonny.model.OrderedProducts;
import com.eshop.sonny.model.Orders;
import com.eshop.sonny.model.Product;
import com.eshop.sonny.repository.BuyerRepository;
import com.eshop.sonny.repository.OrdersRepository;
import com.eshop.sonny.repository.ProductRepository;
import com.eshop.sonny.service.OrdersService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Transactional
public class OrdersServiceImp implements OrdersService {

    private final OrderMapper orderMapper;
    private final BuyerRepository buyerRepository;
    private final OrdersRepository ordersRepository;
    private final ProductRepository productRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final QueryOrderSpecification queryOrderSpecification;


    public boolean createOrder(OrderRequestDto orderRequestDto) {
        Buyer buyer = buyerRepository.findByUserName(orderRequestDto.getUserName());
        if (buyer == null) {
            throw new RuntimeException("Buyer not found");
        }

        Orders order = OrderMapper.OrderRequestToOrder(orderRequestDto, buyer);

        // Ensure enough product stock before proceeding
        List<Product> productsToPurchase = checkAndReduceStock(order);

        // Save updated product inventory
        productRepository.saveAll(productsToPurchase);

        // Calculate order total price including taxation
        BigDecimal taxAmount = calculateTax(buyer.getCart().getTotalPrice());
        order.setTotal_price(BigDecimal.valueOf(buyer.getCart().getTotalPrice()).add(taxAmount));

        // Start taking action for the payment method


        // Trigger order event
        // shiping notification will notfy admin
        eventPublisher.publishEvent(new OrderCreatedEvent(this, order.getId()));

        // Update order details
        order.setStatus("created");
        order.setCreated_at(LocalDateTime.now());

        ordersRepository.save(order);

        return true;
    }

    private List<Product> checkAndReduceStock(Orders order) {
        List<Product> updatedProducts = new ArrayList<>();

        for (OrderedProducts orderedProduct : order.getOrderedProducts()) {
            Product product = productRepository.findByIdWithLock(orderedProduct.getProductId().getId());

            if (product.getInventoryCount() < orderedProduct.getQuantity()) {
                throw new RuntimeException("Product out of stock: " + product.getProductName());
            }

            product.setInventoryCount(product.getInventoryCount() - orderedProduct.getQuantity());
            updatedProducts.add(product);
        }
        return updatedProducts;
    }

    private BigDecimal calculateTax(double totalAmount) {
        // Example: 10% tax rate
        return BigDecimal.valueOf(totalAmount * 0.1);
    }




    @Override
    public boolean cancelOrder(String userName, Long orderId) {

        Buyer buyer = buyerRepository.findByUserName(userName);
        if(buyer == null) throw new RuntimeException("Buyer not found");
        Orders order = ordersRepository.findById(orderId).orElseThrow(
            () -> new RuntimeException("Order not found")
        );
        if(
            order.getBuyer().getId().equals(buyer.getId())
           )
        {
            // we need to make the status enumed
            order.setStatus("Cancelled");
            ordersRepository.save(order);
            // start taking action for the refund

            return true;

        }
        throw new RuntimeException("Order not found for the buyer");
    }



    public List<Orders> getFilteredOrders(
                OrderSpecification orderSpecification
            ) {
        Specification<Orders> spec = queryOrderSpecification.filterOrders(
                orderSpecification
        );
        return ordersRepository.findAll(spec);
    }


}
