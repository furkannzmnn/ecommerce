package com.base.ecommerce.job.futureJob;

import java.util.Map;

public abstract class BaseJob {
    public abstract void execute(Map<String, Object> params);
}
