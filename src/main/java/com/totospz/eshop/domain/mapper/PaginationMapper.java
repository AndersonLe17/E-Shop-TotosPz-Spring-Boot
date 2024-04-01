package com.totospz.eshop.domain.mapper;

import com.totospz.eshop.config.response.PaginationResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.data.domain.Page;

import java.net.URI;
import java.net.URISyntaxException;

public class PaginationMapper {

    public static <T> PaginationResponse paginationResponseMapper(T data, Page page, HttpServletRequest request) throws URISyntaxException {
        URI uri = new URI(request.getRequestURI().concat(request.getQueryString() != null ? "?" + request.getQueryString() : ""));
        return PaginationResponse.builder()
                .data(data)
                .totalDocs((int) page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getNumber())
                .prevPage(page.hasPrevious() ? page.getNumber() - 1 : null)
                .nextPage(page.hasNext() ? page.getNumber() + 1 : null)
                .hasPrevPage(page.hasPrevious())
                .hasNextPage(page.hasNext())
                .prevLink(page.hasPrevious() ? new URIBuilder(uri).setParameter("page", String.valueOf(page.getNumber() - 1)).build() : null)
                .nextLink(page.hasNext() ? new URIBuilder(uri).setParameter("page", String.valueOf(page.getNumber() + 1)).build() : null)
                .build();
    }

}
