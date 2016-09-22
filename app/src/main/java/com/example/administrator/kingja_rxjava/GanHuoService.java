package com.example.administrator.kingja_rxjava;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 项目名称：物联网城市防控(警用版)
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/5/12 16:37
 * 修改备注：
 */
public interface GanHuoService {
    @GET("{day}")
    Call<GanHuoApi> loadNet(@Path("day") int day);
}
