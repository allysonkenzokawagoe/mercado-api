package com.teste.project.modulos.comum.dto;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageRequest implements Pageable{

    private static final int DEFAULT_PAGE = 0;
    private static final String ORDER_ASC = "DESC";
    private static final int DEFAULT_SIZE_PAGE = 10;
    private static final String DEFAULT_ORDER_BY = "id";

    private int page;
    private int size;
    private String orderBy;
    private String orderDirection;

    public PageRequest() {
        this.page = DEFAULT_PAGE;
        this.size = DEFAULT_SIZE_PAGE;
        this.orderBy = DEFAULT_ORDER_BY;
        this.orderDirection = ORDER_ASC;
    }

    @Override
    public int getPageNumber() {
        return page;
    }

    @Override
    public int getPageSize() {
        return size;
    }

    @Override
    public long getOffset() {
        return (long) page * size;
    }

    @Override
    public Sort getSort() {
        return Sort.by(Sort.Direction.fromString(this.orderDirection), this.orderBy);
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    public boolean isOrderDirectionAsceding() {
        return Sort.Direction.fromString(this.orderDirection).isAscending();
    }

    public <T extends EntityPathBase<?>> OrderSpecifier<?> getOrderSpecifier(T entity) {
        var order = "ASC".equalsIgnoreCase(orderDirection) ? Order.ASC : Order.DESC;
        var path = Expressions.path(entity.getClass(), entity, orderBy);
        return new OrderSpecifier(order, path);
    }
}
