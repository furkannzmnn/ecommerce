FROM logstash:8.1.1

USER root
ENV ELASTICSEARCH_HOST "elasticsearch:9200"

COPY ./logstash.conf /etc/logstash/conf.d/logstash.conf
RUN cat /etc/logstash/conf.d/logstash.conf | sed "s/ELASTICSEARCH_HOST/$ELASTICSEARCH_HOST/" > /tmp/logstash.conf.tmp
RUN mv /tmp/logstash.conf.tmp /etc/logstash/conf.d/logstash.conf

EXPOSE 5044:5044

CMD ["logstash", "-f", "/etc/logstash/conf.d/logstash.conf", "--config.reload.automatic"]