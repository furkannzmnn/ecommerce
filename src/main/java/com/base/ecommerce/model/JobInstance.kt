package com.base.ecommerce.model

import javax.persistence.*

@Entity
@Table(name = "batch_job_instance")
data class JobInstance constructor(
    @Id
    @GeneratedValue
    @Column(name = "job_instance_id")
    var id: Long? = null,
    var jobName: String? = null,
    var jobParameters: String? = null,
    var status: String? = null,
    var startTime: Long? = null,
    var endTime: Long? = null,
    var exitCode: String? = null,
    var exitMessage: String? = null,
    var lastUpdated: Long? = null
){
}