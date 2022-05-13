package com.pmp.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
public class APIResponse<T> {
  long total;
  int pageSize;
  int current;

  int totalPage;
  List<T> data;
  public APIResponse(Page page){
    this.total = page.getTotalElements();
    this.pageSize = page.getPageable().getPageSize();
    this.current = page.getPageable().getPageNumber();
    this.data = page.getContent();
    this.totalPage = page.getTotalPages();
  }
}
