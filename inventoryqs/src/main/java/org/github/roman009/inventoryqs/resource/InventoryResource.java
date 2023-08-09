package org.github.roman009.inventoryqs.resource;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.github.roman009.common.dto.Order;
import org.github.roman009.common.dto.Product;
import org.github.roman009.inventoryqs.service.ProductService;

import java.util.List;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InventoryResource {

    @Inject
    ProductService productService;

    @GET
    public Uni<List<Product>> getAll() {
        return productService.getAllProducts();
    }

    @POST
    public Uni<Order> createOrder(Order order) {
        return productService.createOrder(order);
    }

    @DELETE
    public Uni<Order> revertOrder(Order order) {
        return productService.revertOrder(order);
    }

    @POST
    @Path("/products/")
    public Uni<Product> addProduct(Product product) {
        return productService.addProduct(product);
    }

}
