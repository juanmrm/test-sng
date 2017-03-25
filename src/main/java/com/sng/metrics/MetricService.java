package com.sng.metrics;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Service;

import com.sng.dto.ResultInfo;
import com.sng.sender.MessageStatus;

/**
 * Almacena las metricas de los:
 * encolados
 * enviados
 * fallidos
 * @author juanmiguel
 *
 */
@Service
public class MetricService {

	private ConcurrentMap<MessageStatus, Integer> metrics;
	 
    public MetricService() {
        this.metrics = new ConcurrentHashMap<MessageStatus, Integer>();
        metrics.put(MessageStatus.SUCCESS, 0);
        metrics.put(MessageStatus.FAILED, 0);
        metrics.put(MessageStatus.TOQUEUE, 0);
    }
     
    /**
     * Actualiza la metrica correspondiente.
     * @param res
     */
    public void updateMetrics(ResultInfo res) {
    	Integer statusCount = metrics.get(res.getStatus());
       	metrics.put(res.getStatus(), statusCount + 1);
    }

	public ConcurrentMap<MessageStatus, Integer> getMetrics() {
		return metrics;
	}

}
