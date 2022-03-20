package com.base.ecommerce.job.futureJob.sub;

import com.base.ecommerce.job.futureJob.BaseJob;

import java.util.Map;

public class ProductLiveLogJob extends BaseJob {


    @Override
    public void execute(Map<String, Object> params) {
        System.out.println("ProductLiveLogJob");
    }
}
