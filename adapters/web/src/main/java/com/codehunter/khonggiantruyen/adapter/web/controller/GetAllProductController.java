package com.codehunter.khonggiantruyen.adapter.web.controller;

import com.codehunter.khonggiantruyen.WebAdapter;
import com.codehunter.khonggiantruyen.adapter.web.api.common.EOrder;
import com.codehunter.khonggiantruyen.adapter.web.api.getallproduct.GetAllProductResponse;
import com.codehunter.khonggiantruyen.adapter.web.api.getallproduct.IGetAllProductApi;
import com.codehunter.khonggiantruyen.adapter.web.mapper.ProductApiMapper;
import com.codehunter.khonggiantruyen.core.port.in.IGetAllProductUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@Slf4j
public class GetAllProductController implements IGetAllProductApi {
    private final IGetAllProductUseCase getAllProductUseCase;
    private final ProductApiMapper productApiMapper;

    @Override
    public GetAllProductResponse getAllProduct(@RequestParam Integer page, @RequestParam Integer size, @RequestParam EOrder order) {
        IGetAllProductUseCase.GetAllProductDataIn dataIn = new IGetAllProductUseCase.GetAllProductDataIn(
                page,
                size,
                IGetAllProductUseCase.GetAllProductDataIn.EOrder.valueOf(order.toString())
        );
        IGetAllProductUseCase.GetAllProductDataOut dataOut = getAllProductUseCase.getAllProduct(dataIn);

        return new GetAllProductResponse(productApiMapper.mapToProductDtoList(dataOut.getProductList()));
    }
}