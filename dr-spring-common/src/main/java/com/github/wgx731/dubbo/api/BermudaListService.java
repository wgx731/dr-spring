package com.github.wgx731.dubbo.api;

import java.util.List;

import com.github.wgx731.common.pojo.BermudaTriangle;

public interface BermudaListService {

  List<BermudaTriangle> getBermudaList(int size);

}
